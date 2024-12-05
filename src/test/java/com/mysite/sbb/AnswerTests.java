package com.mysite.sbb;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.answer.AnswerRepository;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class AnswerTests {

    @Autowired//객체 자동주입
    private QuestionRepository qRepo;

    @Autowired
    private AnswerRepository aRepo;

    @Test
    public void input(){
   // 먼저 질문을 가져와서 그 질문에 해당 답변 저장
    Optional<Question> oq = this.qRepo.findById(2);
    Question q = oq.get();//2번 질문객체
    Answer a = new Answer();
    a.setQuestion(q);
    a.setContent("잘 모르겠습니다.");
    a.setCreateDate(LocalDateTime.now());
    aRepo.save(a);

    }

    @Test
    public void findAnswer(){
        Optional<Answer> oa = this.aRepo.findById(1);
        Answer a = oa.get();
        System.out.println(a.getContent());
    }

    //한 질문에 해당한 답변들!
    @Transactional
    @Test
    public void findAllByQuestion(){
        Optional<Question> oq = this.qRepo.findById(2);
        Question q = oq.get();//2번 질문객체

        List<Answer> aList = q.getAnswerList();
        for (Answer a : aList) {
            System.out.println(a.getContent());
        }
    }
}
