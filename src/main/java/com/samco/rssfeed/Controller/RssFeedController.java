package com.samco.rssfeed.Controller;

import org.springframework.stereotype.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samco.rssfeed.Model.RssFeedResponse;
import com.samco.rssfeed.Service.RssFeedService;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping(path = "/api/v1")
public class RssFeedController {
	
	@Autowired
	RssFeedService rssFeedService;
	
	@GetMapping("/rssFeed")
	public ResponseEntity<String> getRssFeed(){
		List<RssFeedResponse> rssFeedResponse = this.rssFeedService.getRssFeed();
		return ResponseEntity.ok(rssFeedResponse.toString());
	}

}
