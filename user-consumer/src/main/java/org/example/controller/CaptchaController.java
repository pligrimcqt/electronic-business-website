package org.example.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class CaptchaController {
    // 创建验证码生成接口
    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @GetMapping("/captcha")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String captchaText = defaultKaptcha.createText();
        session.setAttribute("captcha", captchaText);
        BufferedImage captchaImage = defaultKaptcha.createImage(captchaText);
        response.setContentType("image/jpeg");
        ImageIO.write(captchaImage, "jpg", response.getOutputStream());
    }
}
