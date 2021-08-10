package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;

	//////////////////////// JOINFORM////////////////////////
	@RequestMapping(value = "/joinForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinForm() {

		System.out.println("[UserController.joinForm()]");

		return "user/joinForm";

	}

	//////////////////////// JOIN////////////////////////
	@RequestMapping(value = "/join", method = { RequestMethod.GET, RequestMethod.POST })
	public String join(@ModelAttribute UserVo userVo) {

		System.out.println("[UserController.join()]");

		System.out.println(userVo);

		int count = userService.joinUser(userVo);

		System.out.println(count + "건 가입 성공");

		return "user/joinSuccess";

	}

	//////////////////////// IDCHECK////////////////////////

	@ResponseBody
	@RequestMapping(value = "/idCheck", method = { RequestMethod.GET, RequestMethod.POST })
	public int idCheck(@RequestParam("id") String id) {

		System.out.println("[UserController.idCheck()]");

		System.out.println(id);

		return userService.idCheck(id);

	}

	//////////////////////// LOGINFORM////////////////////////

	@RequestMapping(value = "/loginForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginForm() {

		System.out.println("[UserController.loginForm()]");

		return "user/loginForm";
	}

	//////////////////////// LOGIN////////////////////////

	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("[UserController.login()]");

		UserVo authUser = userService.getUser(userVo);

		if (authUser != null) {

			System.out.println("[LOGIN SUCCEED]");
			session.setAttribute("authUser", authUser);

			return "redirect:/";

		} else {

			System.out.println("[LOGIN FAILED]");

			return "redirect:/user/loginForm?result=fail";

		}
	}
	
	////////////////////////LOGOUT////////////////////////

	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpSession session) {

		System.out.println("[UserController.logout()]");

		session.removeAttribute("authUser");
		session.invalidate();

		return "redirect:/";

	}

}
