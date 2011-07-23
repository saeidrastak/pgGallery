package chotchki.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import chotchki.db.pojo.User;
import chotchki.db.service.UserService;
import chotchki.form.pojo.RegistrationForm;

@Controller
@RequestMapping("/register")
public class RegisterController {
	protected Logger log = LoggerFactory.getLogger(this.getClass());
	private UserService userService = null;
	
	@ModelAttribute("registrationForm")
	public RegistrationForm getRegistrationObject(){
		return new RegistrationForm();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showRegistration(Model mod){
		return "register/register";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String signUp(@ModelAttribute("registrationForm") @Valid RegistrationForm form, BindingResult result,Model mod){
		if(result.hasErrors()){
			mod.addAttribute("error", result.getFieldError().getDefaultMessage());
			return "register/register";
		}
		
		if(!form.getRetypePassword().equals(form.getPassword())){
			mod.addAttribute("error", "You must retype the password correctly.");
			return "register/register";
		}
		
		try {
			//userService.createUser(form.getUser());
		} catch (Exception e){
			log.error("Had a sign up error", e);
			mod.addAttribute("error", "Had an issue during registration.");
			return "register/register";
		}
		
		return "redirect:/";
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
