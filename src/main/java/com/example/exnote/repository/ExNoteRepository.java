package com.example.exnote.repository;

import com.example.exnote.entity.ExNoteEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository

public interface ExNoteRepository extends JpaRepository<ExNoteEntity,Long>, QuerydslPredicateExecutor<ExNoteEntity> {


}
