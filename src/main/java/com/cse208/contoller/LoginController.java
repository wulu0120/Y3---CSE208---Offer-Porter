package com.cse208.contoller;

import com.cse208.Entity.SecurityQ;
import com.cse208.Entity.User;
import com.cse208.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class LoginController {
    @Autowired
    UserService userService;

    //用/toLogin先进入login页面，才能执行/login， 获取这些值
    @RequestMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String pwd, @RequestParam(name="remember-me", required=false, defaultValue= "false") Boolean remember, Model model, HttpSession session) {
        Subject subject= SecurityUtils.getSubject();
        //用户登录数据
        UsernamePasswordToken token=new UsernamePasswordToken(username,pwd);
        token.setRememberMe(remember);
        try {
            subject.login(token);
            session.setAttribute("loginUser", username);
            return "redirect:/";
        }
        catch (UnknownAccountException e){
            model.addAttribute("msg","Wrong username");
            return "publicWeb/login";
        }
        catch (IncorrectCredentialsException e){
            model.addAttribute("msg","Wrong password");
            return "publicWeb/login";
        }
        catch (Exception e){
            model.addAttribute("msg","Wrong username or password");
            return "publicWeb/login";
        }
    }

    @RequestMapping({"/forbidden"})
    public String forbidLogin(Model model){
        model.addAttribute("msg","No permission, please login first");
        return "publicWeb/login";
    }

    @RequestMapping("/forgetPwd")
    public String forgetPwd() {
        return "publicWeb/fp-username";  //跳转到该html
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "publicWeb/login";
    }

    /* 注册界面跳转 */
    @RequestMapping("/register")
    public String getRegister(){
        return "publicWeb/register";
    }

    /* 注册功能实现: 获取前端传过来的数据, 传输并调用UserService类里的方法 */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPost(Model model, @ModelAttribute(value = "user") //这里和模板中的th:object="${user}"对应起来
            User user, HttpServletResponse response) {

        String result = register(user);

        //将结果放到model里, 在模板中可以获取到model的值
        model.addAttribute("msg", result);

        if (result.equals("Register successfully")){
            return response.encodeRedirectURL("publicWeb/login");
        }
        return response.encodeRedirectURL("publicWeb/register"); //重定向到login
    }

    private String register(User user){
        User u = userService.getUserByName(user.getName());

        if (u != null){
            return "This username is already in use";
        }

        if (validPassword(user.getPwd()) == false) {
            return "Incorrect password format: at least 1 digit, 1 lowercase character, 1 uppercase character, 1 special symbol #?!()@$%^&*-.";
        }

        if (! user.getRe_pwd().equals(user.getPwd())) {
            return "Password and Re-enter password should be same";
        }

        if (user.getQusId() == 3){
            user.setQusId(null);
        }

        user.setRegisterDate(new Date());
        //注册成功
        userService.setOneUser(user);
        return "Register successfully";
    }

    private boolean validPassword(String password){
        String REG_NUMBER = ".*\\d+.*";  //数字
        String REG_UPPERCASE = ".*[A-Z]+.*"; //大写字母
        String REG_LOWERCASE = ".*[a-z]+.*"; //小写字母
        String REG_SYMBOL = ".*[#?!()@$%^&*-.]+.*"; //特殊符号(#?!()@$%^&*-.)
        
        //密码为空或者长度小于6
        if (password == null || password.length() < 6) return false;

        int count = 0;
        if (password.matches(REG_NUMBER)) count++;
        if (password.matches(REG_LOWERCASE)) count++;
        if (password.matches(REG_UPPERCASE)) count++;
        if (password.matches(REG_SYMBOL)) count++;
        if (count != 4) return false;
        return true;
    }

    // 前端点击链接跳转
    @RequestMapping(value = "/toPage",method = RequestMethod.GET)
    public String toPage(HttpServletRequest request){
        String url = request.getParameter("url");
        return url;
    }
}
