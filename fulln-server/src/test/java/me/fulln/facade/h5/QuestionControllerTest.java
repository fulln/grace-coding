package me.fulln.facade.h5;

import me.fulln.domain.app.QuestionAppService;
import me.fulln.domain.resp.CreateQuestionResp;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest
class QuestionControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionAppService questionAppService;

    @Test
    void testCreate() throws Exception {
        BDDMockito.given(questionAppService.create(any()))
                .willReturn(new CreateQuestionResp(1L));
        byte[] bytes = new ClassPathResource("requestBody/question/200.json").getInputStream().readAllBytes();
        mockMvc.perform(MockMvcRequestBuilders.post("/question/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bytes))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value("1"));
    }
}