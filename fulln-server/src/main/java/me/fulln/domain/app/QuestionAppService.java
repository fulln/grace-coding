package me.fulln.domain.app;

import lombok.extern.slf4j.Slf4j;
import me.fulln.domain.command.CreateQuestionCommand;
import me.fulln.domain.model.Question;
import me.fulln.domain.repository.QuestionRepository;
import me.fulln.domain.resp.CreateQuestionResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional(rollbackOn = Exception.class)
public class QuestionAppService {

    @Autowired
    private QuestionRepository questionRepository;

    public CreateQuestionResp create(CreateQuestionCommand command) {
        Question question = new Question(command.questionId(), command.description(), command.title());
        questionRepository.save(question);
        return new CreateQuestionResp(question.getId());
    }

    public void delete(Long id) {
        log.info("delete question id:{}", id);
    }

}

