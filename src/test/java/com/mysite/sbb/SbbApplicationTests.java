package com.mysite.sbb;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class SbbApplicationTests {

    @Autowired//객체 자동주입
    private QuestionRepository qRepo;

    @Test
    void testJpa() {
        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sbb에 대해 알고 싶습니다.");
        q1.setCreateDate(LocalDateTime.now());
        this.qRepo.save(q1);// 첫번째 질문 저장

        Question q2 = new Question();
        q2.setSubject("스프링부트 모델 질문입니다.");
        q2.setContent("id는 자동으로 생성되나요?");
        q2.setCreateDate(LocalDateTime.now());
        this.qRepo.save(q2);  // 두번째 질문 저장


    }

    @Test
    void testFind() {
        List<Question> qList = this.qRepo.findAll();
        for (Question q : qList) {
            System.out.println(q.getSubject());
        }
        //id로 질문을 찾기 이때 메소드는 Optional<타입>으로 리턴됨(못찾을 경우도 포함)
        Optional<Question> q1 = this.qRepo.findById(1);
        if (q1.isPresent()) {//q1객체가 있을경우(찾았을 경우)
            Question q = q1.get();//get()메소드로 가져옴
            System.out.println(q.getContent());

        }

    }

    @Test
    void testFindBy() {
        Question q = this.qRepo.findBySubject("sbb가 무엇인가요?");
        System.out.println(q.getContent());
    }

    @Test
    void testFindByContaining() {
        List<Question> qList = this.qRepo.findBySubjectContaining("sbb");
        for (Question q : qList) {
            System.out.println(q.getSubject());
        }
    }

    @Test
    void testInput() {
        Question q1 = new Question();
        q1.setSubject("새로운 sbb 제목");
        q1.setContent("새로운 내용입니다.");
        q1.setCreateDate(LocalDateTime.now());
        this.qRepo.save(q1);//id가 없으므로 입력이다.

    }
    //수정
    @Test
    void testUpdate() {
        Optional<Question> oq = this.qRepo.findById(1);
        Question q = oq.get();
        q.setSubject("수정된 제목");
        this.qRepo.save(q);//입력과 수정은 같은 save 메소드 사용 이때 id가 있으면 수정이다(중요)
    }

   /* @Test
    void testDelete() {
        this.qRepo.deleteById(3);

    }*/

    @Test
    void testDelete() {
        this.qRepo.deleteById(9);

    }
}