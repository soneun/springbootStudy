package com.mysite.sbb.answer;

import com.mysite.sbb.question.DataNotFoundException;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.user.SiteUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository aRepo;

    //해당 질문에 답변을 저장하는 메소드
    public void create(Question q, String content, SiteUser author){
        Answer a = new Answer();
        a.setQuestion(q);
        a.setContent(content);
        a.setCreateDate(LocalDateTime.now());
        a.setAuthor(author);

        aRepo.save(a);
    }
    //답변 조회하기
    public Answer getAnswer(Integer id) {
        Optional<Answer> answer = this.aRepo.findById(id);
        if (answer.isPresent()) {
            return answer.get();
        } else {
            throw new DataNotFoundException("답변을 찾을 수 없음" + id);
        }
    }

    //답변 수정하기
    public void modify(Answer a, String content){
        a.setContent(content);
        a.setModifyDate(LocalDateTime.now());
        this.aRepo.save(a);

    }
    //답변 삭제하기
    public void deleteAnswer(Answer a){
        this.aRepo.delete(a);
    }

    //답변 추천하기
    public void vote(Answer a, SiteUser siteUser){
        a.getVoter().add(siteUser);
        this.aRepo.save(a);
    }

}
