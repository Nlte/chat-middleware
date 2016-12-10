package fr.insalyon.telecom.chat.services;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;

@Component
public class GistService {

	@Autowired
	private MessageBoard messageBoard;

	public String publish() {

		String content = messageBoard.getPosts().toString();

		RestTemplate restTemplate = new RestTemplate();
		GistResponse response = restTemplate.postForObject(
			"/gists",
			new GistRequest(content),
			GistResponse.class
		);

		System.out.println("published return URL"+response.getUrl());
		return response.getUrl();
	  }
}
