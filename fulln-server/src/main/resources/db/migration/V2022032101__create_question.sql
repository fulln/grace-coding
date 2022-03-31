create table question
(
    id bigint comment 'id' auto_increment not null primary key ,
    question_id VARCHAR(64) NOT NULL comment '业务id',
    description TEXT NULL,
    title VARCHAR(64) NOT NULL comment '标题'
) comment '问题表';