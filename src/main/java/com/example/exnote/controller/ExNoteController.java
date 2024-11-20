package com.example.exnote.controller;

import com.example.exnote.dto.ExNoteDTO;
import com.example.exnote.dto.PageRequestDTO;
import com.example.exnote.dto.PageResultDTO;
import com.example.exnote.entity.ExNoteEntity;
import com.example.exnote.service.ExNoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/exnote")
public class ExNoteController {
    private final ExNoteService exNoteService;
    @GetMapping("/main")
    public String main(){
        log.info("메인 페이지 접속 확인");
        return "exnote/main";
    }
    @GetMapping("/day")
    public String goDay(Model model, PageRequestDTO pageRequestDTO, @RequestParam(required = false, defaultValue = "daysLeftAsc") String sortBy){
        log.info("일정관리 접속확인");
        PageResultDTO<ExNoteDTO, ExNoteEntity> result = exNoteService.getList(pageRequestDTO,sortBy);
        model.addAttribute("result", result);
        return "exnote/day";
    }
    @GetMapping("/day/api")
    @ResponseBody
    public PageResultDTO<ExNoteDTO,ExNoteEntity> getdayApi(PageRequestDTO pageRequestDTO){
        return exNoteService.getList(pageRequestDTO,"num");
    }


    @GetMapping("/register")
    public void register(){
        log.info("등록 왔니?");
    }
    @PostMapping("/register")
    public String registerPost(ExNoteDTO exNoteDTO, RedirectAttributes redirectAttributes){
        log.info("DTO ======="+exNoteDTO);
        Long num = exNoteService.register(exNoteDTO);
        redirectAttributes.addFlashAttribute("msg",num);
        return "redirect:/exnote/day";
    }
    @DeleteMapping("/delete/{num}")
    @ResponseBody
    public ResponseEntity<String> deleteNote(@PathVariable Long num) {
        log.info("삭제 요청: " + num);

        boolean isDeleted = exNoteService.delete(num);

        if (isDeleted) {
            log.info("삭제 성공: " + num);
            return ResponseEntity.ok("삭제되었습니다."); // 200 OK 반환
        } else {
            log.warn("삭제 실패: " + num);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("삭제 실패"); // 400 BAD REQUEST 반환
        }
    }
}



