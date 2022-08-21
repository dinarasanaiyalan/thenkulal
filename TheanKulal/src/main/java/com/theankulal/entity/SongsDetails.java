package com.theankulal.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SONG_DETAILS")
public class SongsDetails implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SONGS_SEQ")
	@SequenceGenerator(initialValue = 1, allocationSize = 1, sequenceName = "SONGS_SEQ", name = "SONGS_SEQ")
	private Long id;
	
	@Column(name = "SONG_NAME")
	private String songName;
	
	@Column(name = "AUDIO_BYTE")
	private byte[] audioByte;
	
	@Column(name = "SINGER_NAME")
	private String singerName;
	
	@Column(name = "MUSIC_DIRECTOR")
	private String musicDriector;
	
	@Column(name = "SONG_TYPE")
	private String songType;
	
	@Column(name = "MOVIE_NAME")
	private String movieName;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_ID")
	private UserDetails userDetails;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}

	public byte[] getAudioByte() {
		return audioByte;
	}

	public void setAudioByte(byte[] audioByte) {
		this.audioByte = audioByte;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
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

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getSongType() {
		return songType;
	}

	public void setSongType(String songType) {
		this.songType = songType;
	}

}
