package com.monstersoftwarellc.springneo4jtemplate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.monstersoftwarellc.springneo4jtemplate.dao.AccountRepository;
import com.monstersoftwarellc.springneo4jtemplate.model.Account;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private AccountRepository accountDAO;
	
	@RequestMapping(value="/addUser")
	public String home(Model model) {
		Account account = new Account();
		model.addAttribute("account", account );		
		return "createUser";
	}
	
	/**
	 * NOTE: when performing {@Valid} you need to ensure the BindingResult is next in the parameter list.
	 * ALSO: @Valid can take the place of @ModelAttribute as well, so no need to specify that 
	 * @param person
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/saveUser", method=RequestMethod.POST)
	public String createUser(@Valid Account account, BindingResult result, Model model){
		// if errors stay on createUser page. 
		if(result.hasErrors()) {
			 return "createUser";
		}
		accountDAO.save(account);
		// easy forwarding! 
		return listUsers(model);
	}
	
	@RequestMapping("/listUsers")
	public String listUsers(Model model) {
		Page<Account> accounts = accountDAO.findAll(new PageRequest(0, 10));
		model.addAttribute("accounts", accounts.getContent());
		return "listUsers"; 
	}
	
}
