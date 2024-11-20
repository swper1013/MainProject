package com.example.exnote.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QExNoteEntity is a Querydsl query type for ExNoteEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExNoteEntity extends EntityPathBase<ExNoteEntity> {

    private static final long serialVersionUID = 2097295899L;

    public static final QExNoteEntity exNoteEntity = new QExNoteEntity("exNoteEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath content = createString("content");

    public final DatePath<java.time.LocalDate> lastDate = createDate("lastDate", java.time.LocalDate.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    public final NumberPath<Long> num = createNumber("num", Long.class);

    //inherited
    public final DatePath<java.time.LocalDate> regDate = _super.regDate;

    public QExNoteEntity(String variable) {
        super(ExNoteEntity.class, forVariable(variable));
    }

    public QExNoteEntity(Path<? extends ExNoteEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExNoteEntity(PathMetadata metadata) {
        super(ExNoteEntity.class, metadata);
    }

}

