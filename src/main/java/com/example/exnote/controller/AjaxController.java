package com.example.exnote.controller;

import com.example.exnote.dto.AjaxDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AjaxController {
    @GetMapping("/ex01")
    public String ex01() {
        System.out.println("ex01성공!!!!!!!!!!!!!!!!!!!");
        return "exnote/main";
    }
    @PostMapping("/ex02") // HTTP POST 요청을 처리하는 메서드
    // @ResponseBody: 메서드가 반환하는 데이터를 HTTP 응답 본문(response body)으로 전송함을 나타냄
    public @ResponseBody String ex02() {
        System.out.println("ex02성공!!!!!!!!!!!!!!!!!!!");
        return "안녕하세요"; // 리턴값이 그대로 출력됨
    }
    @GetMapping("/ex03")
    public @ResponseBody String ex03(@RequestParam("param1") String param1, @RequestParam("param2") String param2) {
        System.out.println("");
        System.out.println("param1 = " + param1 + ", param2 = " + param2);
        return "ex03메서드 호출 완료"; // 리턴값이 그대로 출력됨
    }
    @PostMapping("/ex04")
    public @ResponseBody String ex04(@RequestParam("param1")String param1, @RequestParam("param2")String param2){
        System.out.println("");
        System.out.println("param1 = "+param1+"param2 = "+param2);
        return "ex04라는 메서드 호출 완료!";
    }
    @GetMapping("/ex05")
    public @ResponseBody AjaxDTO ex05(@ModelAttribute AjaxDTO ajaxDTO) {
        // @modelAttribute: Model 객체에 추가하여 뷰에서 사용할 수 있도록 합니다.
        System.out.println("ajaxDTO = " + ajaxDTO);
        return ajaxDTO;
    }
    @PostMapping("/ex06")
    public @ResponseBody AjaxDTO ex06(@ModelAttribute AjaxDTO ajaxDTO) {
        // @modelAttribute: Model 객체에 추가하여 뷰에서 사용할 수 있도록 합니다.
        System.out.println("ajaxDTO = " + ajaxDTO);
        return ajaxDTO;
    }
    @PostMapping("/ex07")
    public @ResponseBody AjaxDTO ex07(@RequestBody AjaxDTO ajaxDTO) {
        System.out.println("ajaxDTO = " + ajaxDTO);
        return ajaxDTO;
    }
}
