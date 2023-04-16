package me.fulln.study;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.ComponentScan;

@EnableDubbo
@ComponentScan(basePackages = "me.fulln.study")
public class StudyApplication {

}
