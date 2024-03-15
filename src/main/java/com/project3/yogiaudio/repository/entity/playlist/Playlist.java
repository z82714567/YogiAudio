package com.project3.yogiaudio.repository.entity.playlist;

import lombok.Data;

/**
  * @FileName : PlaylistOrder.java
  * @Project : YogiAudio
  * @Date : 2024. 3. 12. 
  * @작성자 : 최장호
  * @변경이력 :
  * @프로그램 설명 : playlist_order 엔티티
  */
@Data
public class Playlist {
	private long id;
	private long userId;
	private String playlistName;
}
