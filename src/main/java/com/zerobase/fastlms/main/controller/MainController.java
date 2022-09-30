package com.zerobase.fastlms.main.controller;

// MainPage 클래스를 만든 목적
// 매핑하기 위해서
// 주소와(논리적인 주소, 인터넷 주소) 물리적인 파일(프로그래밍) 매핑

//메소드를 이용해 매핑

import com.zerobase.fastlms.components.MailComponents;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final MailComponents mailComponents;

    //thymeleaf 때문에 문자열을 반환하면 자동으로 파일이름으로 연결해준다
    @RequestMapping("/")
    public String index() {

//        String email = "fksdudchlrh@naver.com";
//        String subject = "안녕하세요. 메일 보내기 테스트";
//        String text = "<p> 안녕 </p> <p>집중하는 하루 되쇼.</p>";
//
//        mailComponents.sendMail(email, subject, text);

        return "index";
    }

    // 스프링 -> MVC (View -> 템플릿엔진 화면에 내용을 출력(html))
    // V -> HTML ==> 웹페이지
    // V -> json ==> API

    // request -> WEB -> SERVER
    // response -> SERVER -> WEB

    @RequestMapping("/hello")
    public void hello(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();

        String msg = "<html>" +
                "<head>" +
                "<meta charset='UTF-8'" +
                "</head>" +
                "<body>" +
                "<p> hello </p> <p> fastlms website!! </p>" +
                "<p> 안녕하세요!! </p>" +
                "</body>" +
                "</html>";

        printWriter.write(msg);
        printWriter.close();
    }

    @RequestMapping("/error/denied")
    public String errorDenied() {
        return "error/denied";
    }
}
