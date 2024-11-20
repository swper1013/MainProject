package com.example.exnote.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {//두 파라미터 수집(화면에 전달되는)
    //JPA 에서 사용하는 pageable타입 객체 생성 단, JPA 이용시 번호 0번부터 시작
    private int page;
    private int size;
    //검색처리
    private String type;
    private String keyword;

    private String sortBy = "num";  // 기본 정렬 기준 (예: "num", "content", "lastDate")
    private boolean ascending = true;  // 기본 정렬 방향 (오름차순)
    public PageRequestDTO(){
        this.page=0;//파라미터
        this.size=10;//파라미터
    }

    public Pageable getPageable(){

        return PageRequest.of(page, size, ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
    }
}
