package com.mysite.sbb.question;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//JpaRespository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    //JPA는 CRUD 구현 클래스가 자동으로 완성되어 객체가 생성됨
    Question findBySubject(String subject);
    Question findBySubjectAndContent(String subject, String content);
    List<Question> findBySubjectContaining(String subject);
    //Pageable 객체를 입력받아 Page<Question> 객체를 리턴
    Page<Question> findAll(Pageable pageable);
}
