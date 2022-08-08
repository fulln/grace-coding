package me.fulln.facade.h5;

import me.fulln.domain.app.QuestionAppService;
import me.fulln.domain.command.CreateQuestionCommand;
import me.fulln.domain.resp.CreateQuestionResp;
import me.fulln.facade.req.QuestionReq;
import me.fulln.facade.resp.QuestionListResp;
import me.fulln.facade.resp.QuestionResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
/**
 * @author fulln
 * @description 问题列表查询
 * @date  Created in  15:16  2022/5/2.
 **/
@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionAppService questionAppService;

    @PostMapping("/add")
    public QuestionResp create(@Validated @RequestBody QuestionReq req) {
        CreateQuestionCommand createQuestionCommand = new CreateQuestionCommand(req.questionId(), req.title(), req.description());
        CreateQuestionResp createQuestionResp = questionAppService.create(createQuestionCommand);
        return new QuestionResp(createQuestionResp.questionId());
    }

    @GetMapping("/list")
    public List<QuestionListResp> list(@RequestBody QuestionReq req){
        return Collections.emptyList();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        questionAppService.delete(id);
        return "SUCCESS";
    }
}
