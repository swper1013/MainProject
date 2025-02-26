package com.example.exnote.dto;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Console;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
@Log4j2
public class PageResultDTO<DTO, EN> {
    private List<DTO>dtoList;

    //총 페이지 번호
    private int totalPage;
    //현재 페이지
    private int page;
    //목록 총 사이즈
    private int size;
    //시작번호 끝번호
    private int start,end;
    //이전 다음
    private boolean prev,next;
    //페이지 번호 목록
    private List<Integer> pageList;
    public PageResultDTO(Page<EN> result, Function<EN,DTO> fn){

        dtoList= result.stream().map(fn).collect(Collectors.toList());
        if (dtoList == null){
            log.info("dtoList-===========null");
        }
        totalPage = result.getTotalPages();
        makePageList(result.getPageable());
    }

    private void makePageList(Pageable pageable) {
        this.page=pageable.getPageNumber()+1;//1부터 시작
        this.size=pageable.getPageSize();
        //temp end page
        int tempEnd =(int)(Math.ceil(page/10.0))*10;
        start = tempEnd - 9;
        prev =start>1;
        end=totalPage>tempEnd?tempEnd:totalPage;
        next=totalPage>tempEnd;

        pageList = IntStream.rangeClosed(start,end).boxed().collect(Collectors.toList());


    }
    //페이지 리스트 만드는 거

}
