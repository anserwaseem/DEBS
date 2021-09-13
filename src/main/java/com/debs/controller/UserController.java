package com.debs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.debs.model.User;
import com.debs.service.UserService;

@Controller
public class UserController {
	@Autowired // Autowiring feature of spring framework enables you to inject the object
	// dependency implicitly. It internally uses setter or constructor injection.
	// Autowiring can't be used to inject primitive and string values.
	private UserService userService;

	@Autowired
	private UserDetails userDetails; // adding session scoped bean

	@GetMapping("/")
	public String viewHomePage(Model model) {
		/* model.addAttribute("listCustomers", customerService.getAllCustomers()); */
//		return "eAccounts";// Thymeleaf template name i.e., index

		// springboot auto configure view resolver for Thymeleaf.
		// By default, springboot picks up Thymeleaf templates from /resource/template
		// folder

		// temporary
		userDetails.setUserId(36);
//		return "dashboard";
		return "redirect:/createTransaction";
	}

	@GetMapping("/dashboard")
	public String getDashboardPage() {
		return "dashboard";
	}

	@GetMapping("/signup")
	public String getSignupPage(Model model) {
		User user = new User();
		// login page will access this empty user object for binding form data
		model.addAttribute("user", user);
		return "signup";
	}

	@PostMapping("/verifySignup")
	public String verifySignup(@ModelAttribute("user") User user, Model model) {// , HttpServletRequest request
		if (userService.verifySignup(user) == 1) {
			userDetails.setUserId(user.getId());// save user id in session scoped bean

//			@SuppressWarnings("unchecked")
//			List<Integer> user_ids = (List<Integer>) request.getSession().getAttribute("session_user_ids");
//			if (user_ids == null) {
//				user_ids = new ArrayList<>();
//				request.getSession().setAttribute("session_user_ids", user_ids);
//				user_ids.add(Math.toIntExact(user.getId()));
//			}
//			request.getSession().setAttribute("session_user_ids", user_ids);

			return "redirect:/dashboard";
		}

		return "signup";
		// springboot auto configure view resolver for Thymeleaf.
		// By default, springboot picks up Thymeleaf templates from /resource/template
		// folder
	}

	@GetMapping("/login")
	public String getLoginPage(Model model) {// @PathVariable(value = "msg") String msg,
		User user = new User();
		// login page will access this empty user object for binding form data
		model.addAttribute("user", user);
//		model.addAttribute("msg", msg);
		return "login";
	}

	@PostMapping("/verifyLogin")
	public String verifyLogin(@ModelAttribute("user") User user, Model model) {// , HttpServletRequest request
		if (userService.verifyLogin(user) == 1) {
			int userId = userService.getUserByUsername(user.getUsername()).getId(); // save user id for later use
			userDetails.setUserId(userId);// save user id in session scoped bean

//			@SuppressWarnings("unchecked")
//			List<Integer> user_ids = (List<Integer>) request.getSession().getAttribute("session_user_ids");
//			if (user_ids == null) {
//				user_ids = new ArrayList<>();
//				request.getSession().setAttribute("session_user_ids", user_ids);
//				user_ids.add(Math.toIntExact(user.getId()));
//			}
//			System.out.println("\n\n\n\n\n\n");
//			for (int i = 0; i < user_ids.size(); i++)
//				System.out.println(user_ids.get(i).intValue() + "\t");
//			System.out.println("\n\n\n\n\n\n");
//			request.getSession().setAttribute("session_user_ids", user_ids);

			return "redirect:/dashboard";
		}

		return "login";
		// springboot auto configure view resolver for Thymeleaf.
		// By default, springboot picks up Thymeleaf templates from /resource/template
		// folder
	}

	@GetMapping("/profile")
	public String getProfilePage(Model model) {
		model.addAttribute("user", userService.getUserById(userDetails.getUserId()));
		return "profile";
	}

	@PostMapping("/saveUser")
	public String saveUser(@Valid @ModelAttribute("user") User user) {// , BindingResult bindingResult
//		if (bindingResult.hasErrors())
//			System.out.println("\n\n E R R O R \n\n");

		// all the form data will be binded to user (given parameter) object.
		// now, we will save user to database
		User user1 = userService.getUserById(userDetails.getUserId());
		if (!user1.equals(user))
			userService.saveUser(user);

		return "profile";
	}

	@PostMapping("/changeUsername")
	public String changeUsername(@ModelAttribute("user") User user) {
		if (userService.changeUsername(user) == 1) {
			User u = userService.getUserById(userDetails.getUserId());
			u.setUsername(user.getNewUsername());
			user.setNewUsername(null);
			userService.saveUser(u);
		}
		return "redirect:/profile";
	}

	@PostMapping("/changePassword")
	public String changePassword(@ModelAttribute("user") User user) {
		if (userService.changePassword(user) == 1) {
			User u = userService.getUserById(userDetails.getUserId());
			u.setPassword(user.getNewPassword());
			user.setNewPassword(null);
			userService.saveUser(u);
		}
		return "redirect:/profile";
	}

//	@GetMapping("/fragments")
//	public String getFragmentsPage(@ModelAttribute("user") User user, Model model) {
//		System.out.println("\n\n" + userService.getUserById(userId));
//		model.addAttribute("user", userService.getUserById(userId));
//		return "fragments";
//	}

	@GetMapping("/reports")
	public String getReportsPage(Model model) {// , HttpSession session
//		@SuppressWarnings("unchecked")
//		List<Integer> user_ids = (List<Integer>) session.getAttribute("session_user_ids");
//		@SuppressWarnings("unused")
//		int uid;
//		for (int i = 0; i < user_ids.size(); i++)
//			if (user_ids.get(i).intValue() == userDetails.getUserId())
//				uid = user_ids.get(i).intValue();

		model.addAttribute("trialBalance", userService.getTrialBalance(Math.toIntExact(userDetails.getUserId())));
		return "reports";
	}

	@PostMapping("/destroy")
	public String destroySession(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/eAccounts";
	}
}
