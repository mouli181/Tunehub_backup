package com.kodnest.tunehub.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.repository.SongRepository;
import com.kodnest.tunehub.service.SongService;

@Service
public class SongServiceimpl implements SongService {
	
	@Autowired
	SongRepository sr;

	public String addSong(Song song) {
		sr.save(song);
		return "Song added successfully";
	}

	public boolean linkExists(String link) {
		if(sr.findByLink(link) != null) {
			return true;
		}else {
			return false;
		}
	}

	public List<Song> fetchAllSongs() {
		List<Song> songs = sr.findAll();
		return songs;
	}

	@Override
	public void updateSong(Song song) {

		sr.save(song);
		
	}



	

	
	}
