package com.mysite.sbb.answer;

import org.springframework.data.jpa.repository.JpaRepository;

//jsp 할대는 dao crud 메소드를 직접 만들었으나 jpa 는 crud 자동완성됨
//인터페이스 imple 클래스 즉 구현클래스를 자동으로 만들어짐
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}
