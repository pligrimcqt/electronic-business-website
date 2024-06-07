package org.example.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;


@Controller
@RequestMapping("/admin")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    private DefaultKaptcha defaultKaptcha;

    //跳转登录界面
    @GetMapping(value = "/tologin")
    public String tologin() {
        return "login";
    }

    // 用户登录
    //因为上面/login的get请求中没有相应的验证码请求和判断验证码后进入商品界面，所以对此进行了修改
    // 这个方法处理用户登录请求，首先验证验证码是否正确，然后检查用户名和密码是否匹配。
    // 如果所有验证通过，将用户信息存储到会话中，并重定向到商品列表页面。如果验证失败，则重定向回登录页面并显示相应的错误信息，进一步提高安全性能。
    // 若不加判断，则验证码输入错误也会进入商品界面，跟不加验证码是一样的，那这个验证码加了也就没有意义了。
    @GetMapping(value = "/login")
    public String login(String uname,
                        String upassword,
                        String captcha,
                        HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String sessionCaptcha = (String) session.getAttribute("captcha");

        if (sessionCaptcha == null || !sessionCaptcha.equalsIgnoreCase(captcha)) {
            // 验证码错误
            return "login";
        }

        if (userService.login(uname) != null) {
            if (userService.login(uname).getUpassword().equals(upassword)){
                session.setAttribute("user", userService.login(uname));  // 将登录者信息存入 session
                System.out.print("登录成功");
                return "redirect:http://localhost:8896/goods/getAll?uaccount=" + userService.login(uname).getUaccount()
                        + "&upassword=" + userService.login(uname).getUpassword();
            }
        }

        // 用户名或密码错误
        request.setAttribute("mag","账号密码错误");
        System.out.print("登录失败");
        return "login";
    }

     // 跳转注册界面
    @GetMapping(value = "/toregister")
    public String toregister() {
        return "register";
    }

    //用户注册
    @GetMapping(value = "/register")
    public String register(HttpServletRequest request,String upassword,String uname,String usex) {
       if(userService.register(upassword, uname, usex)>0){
           System.out.print("注册成功");
           return "login";
       }
        System.out.print("注册失败");
        return "register";
    }


}

//    @GetMapping(value = "/login")
//    public String login(String uname, String upassword, HttpServletRequest request) {
//        if (userService.login(uname) != null) {
//            if (userService.login(uname).getUpassword().equals(upassword)){
//                HttpSession session = request.getSession(true);
//                session.setAttribute("user", userService.login(uname));  //将登陆者信息存入session
//                  System.out.print("登录失败");
//                return "redirect:http://localhost:8896/goods/getAll?uaccount="+userService.login(uname).getUaccount()
//                        +"&upassword="+userService.login(uname).getUpassword();
//            }
//        }
//        request.setAttribute("mag","账号密码错误");
//        System.out.print("登录失败");
//        return "login";
//    }


