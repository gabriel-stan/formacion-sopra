package es.rf.tienda.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = {"/home", "/"} , method = RequestMethod.GET)
	public String index() {
//		logger.info("Welcome home! The client locale is {}.", locale);
//		model.addAttribute("serverTime", formattedDate );		
		
		return "index";
	}
}
