package com.project3.yogiaudio.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project3.yogiaudio.dto.common.Criteria;
import com.project3.yogiaudio.dto.music.MusicVideoDTO;

@Mapper
public interface MusicVideoRepository {
	
	//뮤비리스트출력하기
	public List<MusicVideoDTO> mvlistAll(Criteria cri) throws Exception;
	
	//뮤비리스트갯수계산하기
	public int mvlistCount() throws Exception;
	
	//메인페이지 뮤비 출력하기
	public List<MusicVideoDTO> mainMV() throws Exception;
	
	//뮤비리스트(조건)출력하기
	public List<MusicVideoDTO> optionMVlist(Criteria cri) throws Exception;
	
	//뮤비리스트(조건) 카운팅하기
	public Integer optionMVlistCount(Criteria cri) throws Exception;
	
	//뮤비업데이트하기
	public Integer mvUpdate(MusicVideoDTO dto);
	
	
}
