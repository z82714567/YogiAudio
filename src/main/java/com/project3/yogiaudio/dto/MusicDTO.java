package com.project3.yogiaudio.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MusicDTO {

	private int musicno;
	private String musictitle;
	private String musicsinger;
	private String musiccontent;
	private String startdate;
	private Timestamp regdate;
	private String filepath;
	private String musicmajor;
	private String musicminor;
	private int musicprice;
	
	
	
	
}