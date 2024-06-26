package com.project3.yogiaudio.controller.board;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project3.yogiaudio.dto.board.BoardFileDTO;
import com.project3.yogiaudio.dto.board.NoticeDTO;
import com.project3.yogiaudio.dto.board.NoticeUpdateDTO;
import com.project3.yogiaudio.dto.common.PageReq;
import com.project3.yogiaudio.dto.common.PageRes;
import com.project3.yogiaudio.repository.entity.Filedb;
import com.project3.yogiaudio.repository.entity.User;
import com.project3.yogiaudio.repository.entity.board.BoardNotice;
import com.project3.yogiaudio.service.FiledbService;
import com.project3.yogiaudio.service.board.NoticeService;
import com.project3.yogiaudio.util.Define;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board/notice")
public class NoticeController {

	@Autowired
	private HttpSession session;

	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private FiledbService filedbService;

	// http://localhost:80/board/notice/noticeList
	/**
	 * @Method Name : noticeList
	 * @작성일 : 2024. 3. 12.
	 * @작성자 : 노수현
	 * @변경이력 :
	 * @Method 설명 : 공지사항 목록 화면
	 */
	/*
	 * @GetMapping("/noticeList") public String noticeList() {
	 * 
	 * return "board/notice/noticeList"; }
	 */

	/**
	 * @Method Name : noticeList
	 * @작성일 : 2024. 3. 14.
	 * @작성자 : 노수현
	 * @변경이력 :
	 * @Method 설명 : 공지사항 검색&페이징 목록
	 */
	@GetMapping("/noticeList")
	public String noticeList(PageReq pageReq, Model model) {

		// 페이징
		if (pageReq.getPage() <= 0) {
			pageReq.setPage(1); // 페이지가 0 이하일 경우 첫 페이지로 설정한다
		}

		if (pageReq.getSize() <= 0) {
			pageReq.setSize(10); // 페이지 당 보여줄 개수
		}

		PageRes<BoardNotice> pageRes = noticeService.findAllByKeywordwithPasing(pageReq); // 페이징 처리함
		List<BoardNotice> noticeList = pageRes.getContent(); // 내용을 보여줄거다

		User principal = (User) session.getAttribute(Define.PRINCIPAL);
		
		// 페이징 정보를 모델에 추가
		model.addAttribute("noticeList", noticeList); // 프로젝트 마다 다른 코드
		System.out.println("리스트 나와라!!!!!!!!!!!" + noticeList.toString());
		// 공통 코드
		model.addAttribute("page", pageReq.getPage());
		model.addAttribute("size", pageRes.getSize());
		model.addAttribute("totalPages", pageRes.getTotalPages());
		model.addAttribute("startPage", pageRes.getStartPage());
		model.addAttribute("endPage", pageRes.getEndPage());

		
		return "board/notice/noticeList";
	}

	/**
	 * @Method Name : noticeView
	 * @작성일 : 2024. 3. 13.
	 * @작성자 : 노수현
	 * @변경이력 :
	 * @Method 설명 : 공지사항 상세보기 화면
	 */

	@GetMapping("/noticeView/{id}")
	public String noticeView() {

		return "board/notice/noticeView";
	}

	/**
	 * @Method Name : noticeView
	 * @작성일 : 2024. 3. 18.
	 * @작성자 : 노수현
	 * @변경이력 :
	 * @Method 설명 : 공지사항 상세보기 출력
	 */
	@PostMapping("/noticeView/{id}")
	@ResponseBody
	public BoardNotice noticeViewId(@PathVariable(value = "id") int id) {
		System.out.println("아이디아이디 : " + id);

		BoardNotice boardNotice = noticeService.noticeReadById(id);

		System.out.println("엔티티엔티티 : " + boardNotice.toString());

		return boardNotice;
	}

	/**
	 * @Method Name : noticeWrite
	 * @작성일 : 2024. 3. 13.
	 * @작성자 : 노수현
	 * @변경이력 :
	 * @Method 설명 : 공지사항 작성하기 화면
	 */
	@GetMapping("/noticeWrite")
	public String noticeWrite() {
		return "board/notice/noticeWrite";
	}

	/**
	 * @Method Name : insertNotice
	 * @작성일 : 2024. 3. 13.
	 * @작성자 : 노수현
	 * @변경이력 :
	 * @Method 설명 : 공지사항 작성하기
	 */
	@PostMapping("/noticeWrite")
	public String insertNotice(NoticeDTO noticeDTO) {

		User principal = (User) session.getAttribute(Define.PRINCIPAL);

		noticeDTO.setWriterId(principal.getId());

		System.out.println(noticeDTO.toString());

		int result = noticeService.saveNotice(noticeDTO);

		if (result != 1) {
			// TODO : 예외처리
		}

		return "redirect:/board/notice/noticeList";
	}

	/**
	 * @Method Name : noticeUpdate
	 * @작성일 : 2024. 3. 13.
	 * @작성자 : 노수현
	 * @변경이력 :
	 * @Method 설명 : 공지사항 수정하기 화면
	 */
	@GetMapping("/noticeUpdate/{id}")
	public String noticeUpdate(@PathVariable(value = "id") int id, Model model) {

		NoticeDTO noticeDTO = new NoticeDTO();

		BoardNotice boardNotice = noticeService.noticeReadById(id);

		// id에 맞는 title, content값 세팅
		noticeDTO.setTitle(boardNotice.getTitle());
		noticeDTO.setContent(boardNotice.getContent());

		// qna db값
		String filePath = boardNotice.getFilePath();

		// qna db값을 쉼표를 기준으로 문자열 분리 - 다중 파일
		String[] filePathList = filePath.split(",");

		// OriginFileName, UUID 값 가진 DTO
		List<BoardFileDTO> boardFileDTOList = new ArrayList<>();

		for (String url : filePathList) {
			BoardFileDTO boardFileDTO = new BoardFileDTO(); // BoardFileDTO의 재사용 문제 : 마지막 파일 정보로 모든 boardFileDTOList 요소가
															// 덮어쓰여졌음

			String uuid = url.replace("http://localhost/filedb/get-file/", "");
			Filedb filedb = filedbService.findByUuid(uuid);
			if (filedb != null) { // filedb가 null이 아닌 경우에만 처리
				String originFileName = filedb.getOriginalFileName();
				System.out.println("originFileName추출" + originFileName);
				System.out.println("uuid추출" + uuid);

				boardFileDTO.setFilePath(url);
				boardFileDTO.setOriginFileName(originFileName);
				boardFileDTOList.add(boardFileDTO);
				System.out.println("url추출" + url);
				System.out.println("boardFileDTO추출" + boardFileDTO);
				System.out.println("boardFileDTOList추출" + boardFileDTOList);
			} else {
				// filedb가 null일 경우 처리
				System.out.println("filedb가 null입니다. uuid: " + uuid);
				// 원하는 예외 처리를 추가하세요.
			}
		}

		noticeDTO.setBoardFileDTOList(boardFileDTOList);

		model.addAttribute("noticeDTO", noticeDTO);

		return "board/notice/noticeUpdate";
	}

	/**
	 * @Method Name : noticeUpdate
	 * @작성일 : 2024. 3. 18.
	 * @작성자 : 노수현
	 * @변경이력 :
	 * @Method 설명 : 공지사항 수정하기 출력
	 */
	@PostMapping("/noticeUpdate/{id}")
	@ResponseBody
	public boolean noticeUpdate(@PathVariable(value = "id") int id, NoticeUpdateDTO noticeUpdateDTO) {

		System.out.println("아이디 번호" + id);
		System.out.println("데이터" + noticeUpdateDTO.toString());
		

		boolean result = noticeService.noticeUpdate(id, noticeUpdateDTO);

		return result;
	}

	/**
	 * @Method Name : noticeDelete
	 * @작성일 : 2024. 3. 18.
	 * @작성자 : 노수현
	 * @변경이력 :
	 * @Method 설명 : 공지사항 삭제하기
	 */
	@PostMapping("/noticeDelete/{id}")
	@ResponseBody
	public boolean noticeDelete(@PathVariable(value = "id") int id) {
		boolean result = noticeService.noticeDelete(id);

		return result;
	}

}