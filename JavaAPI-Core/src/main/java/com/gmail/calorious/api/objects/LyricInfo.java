package com.gmail.calorious.api.objects;

public class LyricInfo {
	// Contains song information from TimedLyrics
	private String mainArtist = "";
	private String songName = "";
	private TimedLyrics lyrics = null;
	
	public LyricInfo(TimedLyrics lyrics) {
		this.lyrics = lyrics;
	}
	
	public String getMainArtist() {
		return mainArtist;
	}
	
	public String getSongName() {
		return songName;
	}
	
	public LyricInfo setMainArtist(String mainArtist) {
		this.mainArtist = mainArtist;
		return this;
	}
	
	public LyricInfo setSongName(String songName) {
		this.songName = songName;
		return this;
	}
	
	public TimedLyrics getTimedLyrics() {
		return lyrics;
	}
	
	public LyricInfo updateTimedLyrics(TimedLyrics lyrics) {
		this.lyrics = lyrics;
		return this;
	}
	
}
