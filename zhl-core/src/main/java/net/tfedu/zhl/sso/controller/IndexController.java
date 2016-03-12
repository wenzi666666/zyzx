package net.tfedu.zhl.sso.controller;

import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;  
  
@Controller 
public class IndexController {
	
	@RequestMapping("home")  
    public String index() {  
        System.out.println("登录成功");  
        return "home";  
    }  
}
