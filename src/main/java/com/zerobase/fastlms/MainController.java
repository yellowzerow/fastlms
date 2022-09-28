package com.zerobase.fastlms;

// MainPage 클래스를 만든 목적
// 매핑하기 위해서
// 주소와(논리적인 주소, 인터넷 주소) 물리적인 파일(프로그래밍) 매핑

//메소드를 이용해 매핑

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class MainController {

    //thymeleaf 때문에 문자열을 반환하면 자동으로 파일이름으로 연결해준다
    @RequestMapping("/")
    public String index() {
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
}
