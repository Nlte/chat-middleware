package fr.insalyon.telecom.chat.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import fr.insalyon.telecom.chat.services.AuthenticationService;
import fr.insalyon.telecom.chat.services.GistService;
import fr.insalyon.telecom.chat.services.MessageBoard;
import fr.insalyon.telecom.chat.model.Post;

@Controller
public class ChatController {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private GistService gistService;
	
	@Autowired
	private MessageBoard messageBoard;

	@RequestMapping("/")
	public ModelAndView index(HttpSession session) {
		if (session.getAttribute("login") == null) {
			return new ModelAndView("login");
		}
		return new ModelAndView("main")
				.addObject("posts", messageBoard.getPosts())
				.addObject("user", session.getAttribute("login"));
	}

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public String login(
      HttpSession session,
      @RequestParam("login") String login,
      @RequestParam("password") String password) {
		  
		if(authenticationService.authenticate(login,password)){
			System.out.println("user connected");
				session.setAttribute("login", login);
				return "redirect:/";		
		}
		return "redirect:/";
  }
  
  @RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.setAttribute("login", null);
		return "redirect:";
	}

	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public String post(HttpSession session, @RequestParam("message") String message) {
		if (session.getAttribute("login") != null) {
			System.out.println("post added");
			messageBoard.post(new Post((String)session.getAttribute("login"), message));
		}
		return "redirect:/";
	}
	
	
	@RequestMapping("/backup")
	public String backup(HttpSession session) {
		if (session.getAttribute("login") != null) {
			System.out.println("backup asked");
			messageBoard.post(new Post((String)session.getAttribute("login"), "<a href='"+gistService.publish()+"'>new url posted</a>"));
		}
		return "redirect:/";
	}
	
	
}
