-- 创建数据库 sphzb
CREATE DATABASE sphzb;

-- 选择数据库 sphzb
USE sphzb;

-- 创建案件表
create table `cases` (
<<<<<<< HEAD
  case_id BIGINT NOT NULL AUTO_INCREMENT COMMENT '案件ID',
=======
  `case_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '案件ID',
>>>>>>> 2991ad825f826d5057bab562fe48c2ce836f9b5e
  case_code VARCHAR(64) NOT NULL COMMENT '案件编号',
  case_description VARCHAR(4000) NOT NULL COMMENT '案件案情',
  create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '案件创建时间',
  PRIMARY KEY (case_id),
  unique key idx_case_code(case_code)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='案件表';

-- 创建题目表
create table question (
  question_id BIGINT NOT NULL AUTO_INCREMENT COMMENT '题目ID',
  case_id BIGINT NOT NULL COMMENT '案件ID',
  question_content VARCHAR(4000) COMMENT '题目内容',
  answer_a VARCHAR(64) NOT NULL COMMENT '选项A',
  answer_b VARCHAR(64) NOT NULL COMMENT '选项B',
  answer_c VARCHAR(64) NOT NULL COMMENT '选项C',
  answer_d VARCHAR(64) NOT NULL COMMENT '选项D',
  status SMALLINT NOT NULL COMMENT '题目状态，0：失效，1：有效，2：已存在正确答案',
  create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '题目创建时间',
  PRIMARY KEY (question_id),
  key idx_case_id(case_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='题目表';

-- 创建案别类型表
create table case_type (
  type_id SMALLINT NOT NULL AUTO_INCREMENT COMMENT '类型ID',
  type_name VARCHAR(64) NOT NULL COMMENT '案别类型',
  PRIMARY KEY (type_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='案别类型表';

-- 创建关键词权重表
create table key_weight (
  key_id BIGINT NOT NULL AUTO_INCREMENT COMMENT '关键词ID',
  key_content varchar(32) NOT NULL COMMENT '关键词',
  type_id SMALLINT NOT NULL COMMENT '类型ID',
  weight SMALLINT NOT NULL COMMENT '权重值',
  create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  status SMALLINT NOT NULL COMMENT '状态，1：有效，0：无效',
  PRIMARY KEY (key_id),
  KEY idx_type_id(type_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '关键词权重表';

-- 创建答题记录表
CREATE TABLE answer_record (
  answer_id VARCHAR(32) NOT NULL COMMENT '答题记录主键,格式为：YYYYMMDDHH24MISS-IP-QUESTION_ID',
  ip VARCHAR(16) NOT NULL COMMENT '答题IP地址',
  user_code VARCHAR(16) COMMENT '答题人用户编号',
  question_id  BIGINT NOT NULL COMMENT '题目ID',
  answer char(1) COMMENT '答案编号',
  PRIMARY KEY (answer_id),
  KEY idx_question_id(question_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '答题记录表';

-- 创建答题结果统计表
CREATE TABLE answer_stat (
  stat_id BIGINT NOT NULL  AUTO_INCREMENT COMMENT '统计主键',
  question_id BIGINT NOT NULL COMMENT '题目ID',
  total_answer INT NOT NULL DEFAULT 0 COMMENT '总答题次数',
  choice_a INT NOT NULL DEFAULT 0 COMMENT '选择A的次数',
  choice_b INT NOT NULL DEFAULT 0 COMMENT '选择B的次数',
  choice_c INT NOT NULL DEFAULT 0 COMMENT '选择C的次数',
  choice_d INT NOT NULL DEFAULT 0 COMMENT '选择D的次数',
  remark VARCHAR(64) COMMENT '备注',
  PRIMARY KEY (stat_id),
  key idx_question_id (question_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '答题结果统计表';