# springboot
初始化语句
create table test.user
(
  id       int auto_increment
  comment '用户ID'
    primary key,
  email    varchar(255) not null
  comment '用户邮箱',
  password varchar(255) not null
  comment '用户密码',
  username varchar(255) not null
  comment '用户昵称',
  role     varchar(255) not null
  comment '用户身份',
  status   int(1)       not null
  comment '用户状态',
  regTime  datetime     not null
  comment '注册时间',
  regIp    varchar(255) not null
  comment '注册IP',
  constraint email
  unique (email)
)
  engine = InnoDB
  charset = utf8;
