package com.personal.utility.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This controller class handles the refresh request 
 * from browser and redirect it to angular root.
 * UI refresh should not be handled by rest controller. 
 * @author renjith
 *
 */

@Controller
public class WebController {

	@RequestMapping("/**/{[path:[^\\.]*}")
	public String forward() {
		return "forward:/";
	}
}
