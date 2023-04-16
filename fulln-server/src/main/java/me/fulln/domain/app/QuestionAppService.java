package me.fulln.domain.app;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.slots.system.SystemRule;
import com.alibaba.csp.sentinel.slots.system.SystemRuleManager;
import lombok.extern.slf4j.Slf4j;
import me.fulln.domain.command.CreateQuestionCommand;
import me.fulln.domain.model.Question;
import me.fulln.domain.repository.QuestionRepository;
import me.fulln.domain.resp.CreateQuestionResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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

