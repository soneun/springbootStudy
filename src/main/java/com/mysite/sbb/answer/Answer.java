package com.mysite.sbb.answer;


import com.mysite.sbb.question.Question;
import com.mysite.sbb.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    //수정 시간
    private LocalDateTime modifyDate;


    //질문 엔티티 참조(외래키)
    @ManyToOne
    private Question question;

    //질문과 추천인과의 관계가 다 대 다 many to many 관계임
    @ManyToMany
    Set<SiteUser> voter;

    //외래키 설정
    @ManyToOne
    private SiteUser author;



}
