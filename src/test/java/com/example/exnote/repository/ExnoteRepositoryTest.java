package com.example.exnote.repository;

import com.example.exnote.entity.ExNoteEntity;
import com.example.exnote.repository.ExNoteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.stream.IntStream;

@SpringBootTest
public class ExnoteRepositoryTest {
    @Autowired
    private ExNoteRepository exNoteRepository;
    @Test
    public void insertnote(){
        IntStream.rangeClosed(1,100).forEach(i->{
            ExNoteEntity exNoteEntity = ExNoteEntity.builder()
                    .content("content"+i)
                    .lastDate(LocalDate.now())
                    .build();
            exNoteRepository.save(exNoteEntity);

        });
    }
}
