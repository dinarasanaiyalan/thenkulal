package com.theankulal.dto;

import java.util.List;

public class ResponseDetails {
	
	private String singerName;
	
	private String musicDriector;
	
	private String typeOfSong;
	
	private String movieName;
	
	private String userId;
	
	private List<SongsDetailsDto> songDetailsDto;

	public String getSingerName() {
		return singerName;
	}

	public void setSingerName(String singerName) {
		this.singerName = singerName;
	}

	public String getMusicDriector() {
		return musicDriector;
	}

	public void setMusicDriector(String musicDriector) {
		this.musicDriector = musicDriector;
	}

	public String getTypeOfSong() {
		return typeOfSong;
	}

	public void setTypeOfSong(String typeOfSong) {
		this.typeOfSong = typeOfSong;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<SongsDetailsDto> getSongDetailsDto() {
		return songDetailsDto;
	}

	public void setSongDetailsDto(List<SongsDetailsDto> songDetailsDto) {
		this.songDetailsDto = songDetailsDto;
	}

}
