package com.kodnest.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kodnest.tunehub.entity.Playlist;
import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.serviceimpl.PlaylistServiceimpl;
import com.kodnest.tunehub.serviceimpl.SongServiceimpl;

@Controller
public class PlaylistController {
	@Autowired
	SongServiceimpl ssl;
	
	@Autowired
	PlaylistServiceimpl ppl;
	
	@PostMapping("/playlist")
	public String playlist(Model model) {
		List<Song> songList = ssl.fetchAllSongs();
		model.addAttribute("songs",songList);
		return "playlist";
	}
	
	@PostMapping("/addplaylist")
	public String addplaylist(@ModelAttribute Playlist playlist) {
		
		// updating the playlist table
		ppl.addplaylist(playlist);
		
		// updating the song table
		List<Song> s = playlist.getSongs();
		for(Song song : s ) {
			song.getPlaylists().add(playlist);
			ssl.updateSong(song);
		}
		return "admin";
	}
	
	@PostMapping("/viewplaylist")
	public String viewplaylist(Model model) {
		List<Playlist> pl = ppl.fetchAllSongs();
		model.addAttribute("playlist",pl);
		return "viewplaylist";
	}
	
}
