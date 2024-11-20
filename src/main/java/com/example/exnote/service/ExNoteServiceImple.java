package com.example.exnote.service;

import com.example.exnote.dto.ExNoteDTO;
import com.example.exnote.dto.PageRequestDTO;
import com.example.exnote.dto.PageResultDTO;
import com.example.exnote.entity.ExNoteEntity;
import com.example.exnote.entity.QExNoteEntity;
import com.example.exnote.repository.ExNoteRepository;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class ExNoteServiceImple implements ExNoteService{
    private final ExNoteRepository exNoteRepository;
    private final EntityManager entityManager;

    @Override
    public Long register(ExNoteDTO exNoteDTO) {
        ExNoteEntity exNoteEntity = dtoToEntity(exNoteDTO);
        exNoteRepository.save(exNoteEntity);
        log.info(exNoteDTO);
        return null;
    }
    @Override
    public Boolean delete(Long num) {
        try {
            // 해당 num의 ExNoteEntity를 찾음
            Optional<ExNoteEntity> exNoteEntityOptional = exNoteRepository.findById(num);

            // 일정이 존재하면 삭제
            if (exNoteEntityOptional.isPresent()) {
                ExNoteEntity exNoteEntity = exNoteEntityOptional.get();
                exNoteRepository.delete(exNoteEntity);  // 삭제
                log.info("삭제된 일정: " + exNoteEntity);
                return true;
            }

            // 일정이 존재하지 않으면 로그 남기기
            log.warn("삭제할 일정이 존재하지 않음: " + num);
            return false;

        } catch (Exception e) {
            // 예외 발생 시 로그 남기기
            log.error("삭제 중 오류 발생: ", e);
            return false;
        }
    }

    @Override
    public PageResultDTO<ExNoteDTO, ExNoteEntity> getList(PageRequestDTO requestDTO,String sortBy) {
        QExNoteEntity qExNoteEntity = QExNoteEntity.exNoteEntity;//Querydsl Q객체
        //JPQ QEURY생성
        JPAQuery<ExNoteEntity> query = new JPAQuery<>(entityManager);
        query.from(qExNoteEntity);
        if ("regDate".equals(requestDTO.getSortBy())) {
            if (requestDTO.isAscending()) {
                query.orderBy(qExNoteEntity.regDate.asc()); // 오름차순
            } else {
                query.orderBy(qExNoteEntity.regDate.desc()); // 내림차순
            }
        } else if ("lastDate".equals(requestDTO.getSortBy())) {
            if (requestDTO.isAscending()) {
                query.orderBy(qExNoteEntity.lastDate.asc()); // 오름차순
            } else {
                query.orderBy(qExNoteEntity.lastDate.desc()); // 내림차순
            }
        } else {
            // 기본적으로 num을 기준으로 정렬
            query.orderBy(qExNoteEntity.num.asc());
        }

        // Spring Data JPA의 PageRequest와 Pageable을 이용하여 페이징 처리
        long totalCount = query.fetchCount(); // 전체 데이터 수
        log.info("Total Count: {}", totalCount);
        List<ExNoteEntity> resultList = query
                .offset(requestDTO.getPage() * requestDTO.getSize()) // 페이지 오프셋
                .limit(requestDTO.getSize()) // 한 페이지에 가져올 데이터 수
                .fetch(); // 결과 조회
        log.info("Result List Size: {}", resultList.size());
        log.info("Page: {}, Size: {}", requestDTO.getPage(), requestDTO.getSize());
        // 페이지 정보와 결과를 담아 Page 객체로 반환
        Page<ExNoteEntity> pageResult = new PageImpl<>(resultList, requestDTO.getPageable(), totalCount);

        // DTO로 변환
        Function<ExNoteEntity, ExNoteDTO> fn = (entity -> entityToDto(entity));
        PageResultDTO<ExNoteDTO, ExNoteEntity> resultDTO = new PageResultDTO<>(pageResult, fn);
        log.info("resultList: {}", resultList);
        log.info("dtoList: {}", resultDTO.getDtoList());
        return new PageResultDTO<>(pageResult, fn);
    }
        //Page<ExNoteEntity> result = exNoteRepository.findAll(requestDTO.getPageable(Sort.by("num").ascending()));

        //Function<ExNoteEntity, ExNoteDTO> fn = (entity -> entityToDto(entity));
       // return new PageResultDTO<>(result, fn);
    }



