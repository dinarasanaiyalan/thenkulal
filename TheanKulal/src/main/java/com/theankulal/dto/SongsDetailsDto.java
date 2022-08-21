package com.theankulal.dto;

public class SongsDetailsDto {
	
	private byte[] songByte;
	
	private String songName;
	
	private String singerName;
	
	private String musicDriector;
	
	private String typeOfSong;
	
	private String movieName;
	
	private String userId;

	public byte[] getSongByte() {
		return songByte;
	}

	public void setSongByte(byte[] songByte) {
		this.songByte = songByte;
	}

	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}

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

}
