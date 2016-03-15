package net.tfedu.zhl.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.tfedu.zhl.sso.entity.User;
import net.tfedu.zhl.sso.service.AccountService;

/**** 
 * 用户登录Controller 
 *  
 * @author bruce 
 *  
 */  
@Controller 
@RequestMapping("/security*")
public class LoginController {
    // 处理用户业务类  
    @Autowired  
    private AccountService accountService;  

    @RequestMapping(value = "/tologin")  
    public String tologin() {  
    	String result = "login";
    	return result;
    }
    /*** 
     * 实现用户登录 
     *  
     * @param username 
     * @param password 
     * @return 
     */  
    @RequestMapping(value = "/dologin")  
    public String Login(HttpServletRequest request, HttpServletResponse response) { 
    	String username = request.getParameter("username");
    	String password = request.getParameter("password");
    	String result = "login";
        User user = accountService.getUserByUserName(username);  
        if (user == null) {  
            return result;  
        }  
        if (!user.getPassword().equals(password)) {  
            return result;  
        }  
        SecurityUtils.getSecurityManager().logout(SecurityUtils.getSubject());  
        // 登录后存放进shiro token  
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());  
        Subject subject = SecurityUtils.getSubject();  
        subject.login(token);  
        result = "index";//验证成功
        return result;  
    }  
  
}
