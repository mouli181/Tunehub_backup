package com.kodnest.tunehub.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Playlist {
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	int id;
	String playlistname;
	@ManyToMany
	List<Song> songs;
	
	public Playlist() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlaylistname() {
		return playlistname;
	}

	public void setPlaylistname(String playlistname) {
		this.playlistname = playlistname;
	}

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	public Playlist(int id, String playlistname, List<Song> songs) {
		super();
		this.id = id;
		this.playlistname = playlistname;
		this.songs = songs;
	}

	@Override
	public String toString() {
		return "Playlist [id=" + id + ", playlistname=" + playlistname  + "]";
	}
}
