package me.fulln.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * @author fulln
 * @description 问题实体
 * @date  Created in  15:12  2022/5/2.
 **/
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String questionId;
    private String description;
    private String title;

    public Question(String questionId, String desc, String title) {
        this.questionId = questionId;
        this.description = desc;
        this.title = title;
    }


}

