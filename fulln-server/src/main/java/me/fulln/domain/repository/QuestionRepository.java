package me.fulln.domain.repository;

import me.fulln.domain.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author fulln
 * @description question repo
 * @date Created in  22:20  2022/3/31.
 **/
public interface QuestionRepository extends JpaRepository<Question, Long> {


}
