create table sys_user
(
    id bigint comment 'id' auto_increment not null primary key ,
    user_id VARCHAR(64) NOT NULL comment '业务id',
    name VARCHAR(64) NOT NULL comment '标题'
    pass VARCHAR(64) NOT NULL comment '密码'
    salt VARCHAR(16) NOT NULL comment '盐'
) comment '用户表';