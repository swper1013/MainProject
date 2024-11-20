package com.example.exnote.service;

import com.example.exnote.dto.ExNoteDTO;
import com.example.exnote.dto.PageRequestDTO;
import com.example.exnote.dto.PageResultDTO;
import com.example.exnote.entity.ExNoteEntity;
import com.example.exnote.repository.ExNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Member;

public interface ExNoteService {

    Long register(ExNoteDTO exNoteDTO);
    public PageResultDTO<ExNoteDTO, ExNoteEntity> getList(PageRequestDTO requestDTO, String sortBy);
    Boolean delete(Long num);



    default ExNoteEntity dtoToEntity(ExNoteDTO exNoteDTO){
       ExNoteEntity exNoteEntity = ExNoteEntity.builder()
               .num(exNoteDTO.getNum())
               .content(exNoteDTO.getContent())
               .lastDate(exNoteDTO.getLastDate())
               .build();
       return exNoteEntity;
    }
    default ExNoteDTO entityToDto(ExNoteEntity exNoteEntity){
        ExNoteDTO exNoteDTO = ExNoteDTO.builder()
                .num(exNoteEntity.getNum())
                .content(exNoteEntity.getContent())
                .regDate(exNoteEntity.getRegDate())
                .modDate(exNoteEntity.getModDate())
                .lastDate(exNoteEntity.getLastDate())
                .build();
        return exNoteDTO;
    }

}
