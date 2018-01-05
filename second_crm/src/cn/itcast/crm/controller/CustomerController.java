package cn.itcast.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("customer")
public class CustomerController {
	
	//http://localhost:8080/second_crm/customer/list.action
	@RequestMapping("list")
	public String list(){
		
		return "customer";
	}
}
