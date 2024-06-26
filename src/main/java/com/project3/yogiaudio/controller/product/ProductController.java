package com.project3.yogiaudio.controller.product;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project3.yogiaudio.dto.common.Criteria;
import com.project3.yogiaudio.dto.common.PageVO;
import com.project3.yogiaudio.dto.music.MusicDTO;
import com.project3.yogiaudio.dto.music.MusicVideoDTO;
import com.project3.yogiaudio.repository.entity.User;
import com.project3.yogiaudio.service.FiledbService;
import com.project3.yogiaudio.service.MusicService;
import com.project3.yogiaudio.service.MusicVideoService;
import com.project3.yogiaudio.service.UserService;
import com.project3.yogiaudio.service.product.LikeMusicService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private MusicService musicService;
	@Autowired
	private FiledbService filedbService;
	@Autowired
	private UserService userService;
	@Autowired
	private MusicVideoService musicVideoService;
	@Autowired
	private LikeMusicService likeMusicService;
	
	
	// http://localhost:80/product/main
	//메인페이지 호출하기
	@GetMapping("/main")
	public String productMainGET(HttpSession session,Model model,Criteria cri) throws Exception {
		
		
		PageVO pageVO = new PageVO();
		pageVO.setCri(cri);
		pageVO.setTotalCount(musicService.countdomesticListAll());		
	
		
		
		model.addAttribute("pageVO", pageVO);
		
		
		//메인페이지뮤비출력
		List<MusicVideoDTO> mvlist = musicVideoService.mainMV();
		model.addAttribute("mvlist", mvlist);
		
		
		//국외음악 최신 , 인기순 출력
		List<MusicDTO> anewlist = musicService.newAboardMusicList();
		model.addAttribute("anewlist", anewlist);
		// 인기순 Top10
		List<MusicDTO> alikelist = likeMusicService.readMusicListOrderByLikeCount("국외", 10);
		model.addAttribute("alikelist", alikelist);
		
		
		
		//국내음악 최신 , 인기순 출력
		List<MusicDTO> dnewresult = musicService.newMusicList(cri);
		model.addAttribute("dnewlist", dnewresult);
		// 인기순 Top10
		List<MusicDTO> dlikelist = likeMusicService.readMusicListOrderByLikeCount("국내", 10);
		model.addAttribute("dlikelist",dlikelist);

		// 전체 순위 Top10
		List<MusicDTO> alllikelist = likeMusicService.readMusicListOrderByLikeCount(null, 10);
		model.addAttribute("alllikelist",alllikelist);
		
		
		
		log.debug("메인페이지호출테스트");
		return"product/main";
	}
	
	
	//메인페이지 검색페이지출력
	@GetMapping("/main-search")
	public String mainSearchPageGET(HttpServletRequest request, Criteria cri, Model model) throws Exception {
		
		String searchOption = request.getParameter("searchOption");
		String searchKeyword = request.getParameter("searchKeyword");
		
		
		if (searchOption != null && !searchOption.isEmpty()) {
			cri.setSearchOption(searchOption);
		}
		
		if (searchKeyword != null && !searchKeyword.isEmpty()) {
			cri.setSearchKeyword(searchKeyword);
		}
			
		
		
		
		PageVO pageVO = new PageVO();
		pageVO.setCri(cri);
		pageVO.setTotalCount(musicService.MainSearchCount(cri));
		
		model.addAttribute("pageVO", pageVO);

		
		List<MusicDTO> result = musicService.MainSearch(cri);
		
		model.addAttribute("searchlist", result);
		
		log.debug("admin-user관리 페이지 출력!");
		
		
		
		return "product/mainsearch";
	}
	
	
	
	
	
	
	
	
	// http://localhost:80/product/domestic-music
	//국내음악리스트 호출하기
	@GetMapping("/domestic-music")
	public String productDomesticGET(Model model, Criteria cri,MusicDTO dto) throws Exception {
		
		PageVO pageVO = new PageVO();
		pageVO.setCri(cri);
		pageVO.setTotalCount(musicService.countdomesticListAll());		
		
		model.addAttribute("pageVO", pageVO);
		
		
		//전체
		List<MusicDTO> result = musicService.domesticListAll(cri);
		
		model.addAttribute("domesticlist", result);
		
			
		
		
		log.debug("국내음악페이지출력");
		return "product/domestic";
		
	}
	

	
	
	
	
	//국내음악리스트(조건) 출력하기
	@GetMapping("/domestic-search")
	public String searchDomesticGET(HttpServletRequest request, Criteria cri, Model model) throws Exception{
		
		String searchOption = request.getParameter("searchOption");
		
		if (searchOption != null && !searchOption.isEmpty()) {
			cri.setSearchOption(searchOption);
		}
		
		PageVO pageVO = new PageVO();
		pageVO.setCri(cri);
		pageVO.setTotalCount(musicService.countsearchDmusicList(cri));
		
		model.addAttribute("pageVO", pageVO);

		
		List<MusicDTO> result = musicService.searchDmusicList(cri);
		
		model.addAttribute("domesticlist", result);
		
		log.debug("admin-user관리 페이지 출력!");
		return "product/domesticsearch";
		
	}
	
	
		//국외음악리스트(조건) 출력하기
		@GetMapping("/aboard-search")
		public String searchAboardGET(HttpServletRequest request, Criteria cri, Model model) throws Exception{
			
			String searchOption = request.getParameter("searchOption");
			
			if (searchOption != null && !searchOption.isEmpty()) {
				cri.setSearchOption(searchOption);
			}
			
			PageVO pageVO = new PageVO();
			pageVO.setCri(cri);
			pageVO.setTotalCount(musicService.countsearchAmusicList(cri));
			
			model.addAttribute("pageVO", pageVO);

			
			List<MusicDTO> result = musicService.searchAmusicList(cri);
			
			model.addAttribute("aboardlist", result);
			
			log.debug("admin-user관리 페이지 출력!");
			return "product/aboardsearch";
			
		}
		
	
	
	
	
	
	
	
	
	//국외음악리스트 호출하기
	@GetMapping("/aboard-music")
	public String productAboardGET(Model model, Criteria cri, MusicDTO dto) throws Exception {
		
		PageVO pageVO = new PageVO();
		pageVO.setCri(cri);
		pageVO.setTotalCount(musicService.countaboardListAll());
		
		model.addAttribute("pageVO", pageVO);
		
		List<MusicDTO> result = musicService.aboardListAll(cri);
		
		model.addAttribute("aboardlist", result);
		
		
		log.debug("국외음악페이지출력");
		return "product/aboard";
	}
	
	
	
	//최신음악리스트 전부 호출하기
	@GetMapping("/new-music")
	public String newmusicListAllGET(Model model, Criteria cri, MusicDTO dto) throws Exception {
		
		PageVO pageVO = new PageVO();
		pageVO.setCri(cri);
		pageVO.setTotalCount(musicService.newListAllcount());
		
		model.addAttribute("pageVO", pageVO);
		
		List<MusicDTO> result = musicService.newListAll(cri);
		model.addAttribute("newlist", result);
		
		return "product/newmusic";
		
	}
	
	
	//최신음악리스트(조건) 출력하기
	@GetMapping("/new-search")
	public String newSearchGET(HttpServletRequest request, Criteria cri, Model model) throws Exception {
		
		String searchOption = request.getParameter("searchOption");
		
		if (searchOption != null && !searchOption.isEmpty()) {
			cri.setSearchOption(searchOption);
		}
		
		PageVO pageVO = new PageVO();
		pageVO.setCri(cri);
		pageVO.setTotalCount(musicService.newlistSearchCount(cri));
		
		model.addAttribute("pageVO", pageVO);

		
		List<MusicDTO> result = musicService.newlistSearch(cri);
		
		model.addAttribute("newlist", result);
		
		log.debug("admin-user관리 페이지 출력!");
		
		return "product/newsearch";
	}
	
	
	
	////////////
	
	//인기음악리스트 전부 호출하기
	/**
	  * @Method Name : likeMusicListAllGET
	  * @작성일 : 2024. 3. 25.
	  * @작성자 : 최장호
	  * @변경이력 : 
	  * @Method 설명 : 좋아요 페이지
	  * @param model
	  * @param cri
	  * @param major
	  * @return
	  * @throws Exception
	  */
	@GetMapping("/like-music")
	public String likeMusicListAllGET(Model model, Criteria cri,@RequestParam(name = "major", required = false) String major) throws Exception {
			
			String searchOption = major;
			
			if (searchOption != null && !searchOption.isEmpty()) {
				cri.setSearchOption(searchOption);
			}
			
			PageVO pageVO = new PageVO();
			pageVO.setCri(cri);
			pageVO.setTotalCount(likeMusicService.allCountMusicByMajor(major));
			
			model.addAttribute("pageVO", pageVO);
			
			List<MusicDTO> result = likeMusicService.musicListOrderByLikeCountPaging(cri);
			
			model.addAttribute("likeList", result);
			model.addAttribute("major", major);
		
		return "product/likeMusicListPage";
		
	}
	
	///////////////
	
	
	
	
	
	
	//국내음악상세페이지
	// http://localhost:80/product/domestic-detail?musicno=&musicmajor=
	@GetMapping("/domestic-detail")
	public String domesticDetailGET(@RequestParam(value = "musicno") int musicno, @RequestParam(value = "musicmajor") String musicmajor,@RequestParam(value = "id", required = false) Long id,Model model) {
				

		 if (id != null) {
		        User udetail = userService.findById(id);
		        model.addAttribute("udetail", udetail);
		    }

		    MusicDTO result = musicService.domesticDetail(musicno, musicmajor);
		    model.addAttribute("detail", result);
		    
		    System.out.println("musicno: " + musicno); 
		    System.out.println("musicmajor: " + musicmajor);
		    return "product/domesticdetail";
	}
	
	
	//국외음악상세페이지
	// http://localhost:80/product/aboard-detail?musicno=&musicmajor=
	@GetMapping("/aboard-detail")
	public String aboardDetailGET(@RequestParam(value = "musicno") int musicno, @RequestParam(value = "musicmajor") String musicmajor,@RequestParam(value = "id", required = false) Long id,Model model) {
		
		 if (id != null) {
		        User udetail = userService.findById(id);
		        model.addAttribute("udetail", udetail);
		    }

		    MusicDTO result = musicService.aboardDetail(musicno, musicmajor);
		    model.addAttribute("detail", result);
		    
		    System.out.println("musicno: " + musicno); 
		    System.out.println("musicmajor: " + musicmajor);
		    return "product/aboarddetail";
	}
	
	
	//최신음악 디테일페이지 출력
	@GetMapping("/new-detail")
	public String newDetailGET(@RequestParam(value = "musicno") int musicno,@RequestParam(value = "id", required = false) Long id,Model model) {
		
		 if (id != null) {
		        User udetail = userService.findById(id);
		        model.addAttribute("udetail", udetail);
		    }

		    MusicDTO result = musicService.newDetail(musicno);
		    model.addAttribute("detail", result);
		

		return"product/newdetail";
	}
	
	
	//국내앨범자켓바꾸기 GET
	@GetMapping("/dalbum-update")
	public String albumUpdateGET() {
		log.debug("앨범수정페이지modal 실행!");
		return "product/dalbumupdate";
	}
	
	
	//국외앨범자켓바꾸기GET
	@GetMapping("/aalbum-update")
	public String albumUpdateGET2() {
		log.debug("앨범수정페이지modal 실행2");
		return "product/aalbumupdate";
	}
	
	
	//국내앨범자켓바꾸기 POST
	@PostMapping("/dalbum-update")
	public String albumUpdatePOST(MusicDTO dto) {
		
		String filePath = filedbService.saveFiles(dto.getFiles());
		
		log.debug("앨범수정완료!");
		musicService.albumUpdate(dto, filePath);
		
		return "redirect:/product/domestic-music";
	}
	
	
	  //국외앨범자켓바꾸기 POST
	  @PostMapping("/aalbum-update")
	  public String albumUpdatePOST2(MusicDTO dto) {
			
		String filePath = filedbService.saveFiles(dto.getFiles());
			
		log.debug("앨범수정완료!");
		//서비스
		musicService.albumUpdate(dto, filePath);
		return "redirect:/product/aboard-music";
	 }
	
	
	//국내  음원등록하기 GET
	@GetMapping("/dmusic-update")
	public String musicUpdateGET() {
		log.debug("음원등록페이지modal 실행");
		return "product/dmusicupdate";
	}
	
	
	//국외  음원등록하기 GET
	@GetMapping("/amusic-update")
	public String musicUpdateGET2() {
		log.debug("음원등록페이지modal 실행");
		return "product/amusicupdate";
	}
		
	

	//국내 음원등록하기 POST
	@PostMapping("/dmusic-update")
	public String musicUpdatePOST(MusicDTO dto) {
		
		String fileMusic = filedbService.saveFiles(dto.getFiles());
		String musicSample = filedbService.saveFiles(dto.getFiles());
		
		//서비스
		musicService.musicUpdate(dto, fileMusic, musicSample);
		
		return "redirect:/product/domestic-music";
		
	}
	
	 //국외 음원등록하기 POST
	@PostMapping("/amusic-update")
	public String musicUpdatePOST2(MusicDTO dto) {
			
		String fileMusic = filedbService.saveFiles(dto.getFiles());
		String musicSample = filedbService.saveFiles(dto.getFiles());
			
		//서비스
		musicService.musicUpdate(dto, fileMusic, musicSample);
		
		return "redirect:/product/aboard-music";
			
	}

	
	
	//좋아요 get
	@GetMapping("/likeit")
	public String likeitGET(@RequestParam(value = "musicno") int musicno, @RequestParam(value = "musicmajor") String musicmajor, @RequestParam(value = "id") long id, Model model) {
	    // musicno와 musicmajor을 이용하여 필요한 작업 수행
	    // 이후에 redirect 경로를 반환
		
		return"product/domesticdetail";
	}
	
	//좋아요 post
	@PostMapping("/likeit")
	@ResponseBody
	public String likeitPOST(@RequestParam(value = "musicno") int musicno, @RequestParam(value = "musicmajor") String musicmajor,@RequestParam(value = "id") long id,Model model,MusicDTO dto,HttpSession session) {
		
		 String sessionKey = "liked_" + id + "_" + musicno;
		    if (session.getAttribute(sessionKey) != null) {
		        return "already_liked"; // 이미 좋아요를 클릭한 경우
		    }
		
		  session.setAttribute(sessionKey, true);

		    
		    
		musicService.likeit(dto,musicno, musicmajor);
		return "redirect:/product/domestic-detail";
	}
	
	
	

	
	
	
	
	
}
