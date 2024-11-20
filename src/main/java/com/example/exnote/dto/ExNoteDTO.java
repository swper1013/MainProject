package com.example.exnote.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExNoteDTO {
    private long num;

    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastDate;

    // 필요하다면 regDate와 modDate는 LocalDateTime 유지
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate regDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime modDate;

    public ExNoteDTO(long num, String content, LocalDate lastDate) {
        this.num = num;
        this.content = content;
        this.lastDate = lastDate;
    }
}
