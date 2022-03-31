package me.fulln.domain.repository;

import me.fulln.core.TestJpa;
import me.fulln.domain.model.Question;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;

@TestJpa
class QuestionRepositoryTest {

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    void saveQuestion() {
        var question = new Question("1", "test title", "test detail");
        var save = questionRepository.save(question);
        assertThat(save.getId(), Matchers.is(Matchers.notNullValue()));
        assertThat(save.getQuestionId(), Matchers.equalTo(question.getQuestionId()));
        assertThat(save.getDescription(), Matchers.equalTo(question.getDescription()));
        assertThat(save.getTitle(), Matchers.equalTo(question.getTitle()));
    }

}
