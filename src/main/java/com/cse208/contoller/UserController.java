package com.cse208.contoller;

import com.cse208.Entity.User;
import com.cse208.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

//@RestController中 return语句不能返回jsp,html页面，视图解析器无法解析jsp,html页面
@Controller
public class UserController {

    @Autowired
    UserService userService;

    //获得全部用户
    @RequestMapping("/getAllUsers")
    public List<User> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return users;
    }

    //获取用户 by ID
    @RequestMapping("/getUserById/{id}")
    public User getUserById(@PathVariable("id") int id){
        User user=userService.getUserById(id);
        return user;
    }

    //修改对应ID的用户信息
    @RequestMapping("/updateUser")
    public String updateUser(@RequestParam("password1") String password1,@RequestParam("password2") String password2, Model model, HttpServletRequest request){
            String username= (String) request.getSession().getAttribute("pwdUser");
        User user=userService.getUserByName(username);
        if (!password1.equals(password2)){
            model.addAttribute("warn","The second password is not the same as the first one, please re-enter");
            return "publicWeb/fp-reset";
        }
        else {
            user.setPwd(password1);  //更改该对应ID的名字
            userService.updateUser(user);
            return "publicWeb/login";
        }
    }

    //确认用户名
    @RequestMapping("/getUsername")
    public String getUsername(@RequestParam("username") String username, Model model, HttpSession session) {
        session.setAttribute("pwdUser", username); //step1。 赋值

        User user=userService.getUserByName(username);
        if (user==null) {
            model.addAttribute("msg","Username not exist");
            return "publicWeb/fp-username";
        }
        model.addAttribute("msg",username);  //设置 当前html页面的 “msg”变量
        String question;
        if (user.getQusId() == null){
            question = user.getQuest();
        }
        else {
            question=userService.getQues(user);
        }
        model.addAttribute("sq",question);

        return "publicWeb/fp-verify";  //进入该页面后，才能执行后面的 /getAns 动作
    }

    //找回密码-确认安密保问题
    @RequestMapping("/checkAns")
    public String getAns(@RequestParam("answer") String answer, Model model, HttpServletRequest request, HttpSession session) {
        String username= (String) request.getSession().getAttribute("pwdUser");  //step2.获取
        User user=userService.getUserByName(username);
        String ans=userService.getAns(user);

        if (ans.equals(answer)){
            model.addAttribute("user",username);  //尽管当前页面没有“user”这个attribute，是为下一个页面服务的，提前设置，这样下一页就能直接显示
            return "publicWeb/fp-reset";
        }
        else {
            model.addAttribute("msg",username);  //设置 当前html页面的 “msg”变量
            String question;
            if (user.getQusId() == null){
                question = user.getQuest();
            }
            else {
                question=userService.getQues(user);
            }
            model.addAttribute("sq",question);
            model.addAttribute("warn","Incorrect secret answer, please input again");  //设置 当前html页面的 “msg”变量
            return "publicWeb/fp-verify";
        }
    }

}
