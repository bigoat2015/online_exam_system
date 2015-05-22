drop database if exists exam;

create database exam;

use exam;

set names gbk;

drop table if exists t_user;

create table t_user(
	id int(8) primary key auto_increment,
	uid varchar(255) unique not null,
	name varchar(255) not null,
	password varchar(255) not null,
	phone varchar(255) not null,
	email varchar(255) not null
)default charset=gbk ;

insert into t_user(uid,name,password,phone,email) values('1000','liu','1234','13810381038','ninglj@tarena.com.cn');
insert into t_user(uid,name,password,phone,email) values('1001','liuchangsong','1234','13810381038','liucs@tarena.com.cn');


drop table if exists t_option;
drop table if exists t_question;
drop table if exists t_paper;
 
create table t_paper(
	id int(8) primary key auto_increment,
	paper_desc text not null,
	exam_time int(3) not null,
	total_score int(3) not null
)default charset=gbk ;

insert into t_paper(paper_desc,exam_time,total_score) values('javaSE',1,100);

create table t_question(
	id int(8) primary key auto_increment,
	score int(3) not null,
	q_level int(3) not null,
	que_desc text not null,
	answer varchar(255) not null,
	paperId int(8) not null,
	foreign key(paperId) references t_paper(id)
)default charset=gbk ;

insert into t_question(score,q_level,que_desc,answer,paperId) values(5,1,'指出下面语句没有编译错误的是:1','2/3',1);
insert into t_question(score,q_level,que_desc,answer,paperId) values(5,1,'指出下面语句没有编译错误的是:11','2/3',1);
insert into t_question(score,q_level,que_desc,answer,paperId) values(5,2,'指出下面语句没有编译错误的是:2','2/3',1);
insert into t_question(score,q_level,que_desc,answer,paperId) values(5,2,'指出下面语句没有编译错误的是:22','2/3',1);
insert into t_question(score,q_level,que_desc,answer,paperId) values(5,3,'指出下面语句没有编译错误的是:3','2/3',1);
insert into t_question(score,q_level,que_desc,answer,paperId) values(5,3,'指出下面语句没有编译错误的是:33','2/3',1);

create table t_option(
	id int(8) primary key auto_increment,
	option_desc text not null,
	questionId int(8) not null,
	foreign key(questionId) references t_question(id)
)default charset=gbk ;

insert into t_option(option_desc,questionId) values('long n = 999999999999;',1);
insert into t_option(option_desc,questionId) values('int n = 999999999999L;',1);
insert into t_option(option_desc,questionId) values('long n = 999999999999L;',1);
insert into t_option(option_desc,questionId) values('double n = 999999999999;',1);


insert into t_option(option_desc,questionId) values('long n = 999999999999;2',2);
insert into t_option(option_desc,questionId) values('int n = 999999999999L;2',2);
insert into t_option(option_desc,questionId) values('long n = 999999999999L;2',2);
insert into t_option(option_desc,questionId) values('double n = 999999999999;2',2);


insert into t_option(option_desc,questionId) values('long n = 999999999999;3',3);
insert into t_option(option_desc,questionId) values('int n = 999999999999L;3',3);
insert into t_option(option_desc,questionId) values('long n = 999999999999L;3',3);
insert into t_option(option_desc,questionId) values('double n = 999999999999;3',3);


insert into t_option(option_desc,questionId) values('long n = 999999999999;4',4);
insert into t_option(option_desc,questionId) values('int n = 999999999999L;4',4);
insert into t_option(option_desc,questionId) values('long n = 999999999999L;4',4);
insert into t_option(option_desc,questionId) values('double n = 999999999999;4',4);


insert into t_option(option_desc,questionId) values('long n = 999999999999;5',5);
insert into t_option(option_desc,questionId) values('int n = 999999999999L;5',5);
insert into t_option(option_desc,questionId) values('long n = 999999999999L;5',5);
insert into t_option(option_desc,questionId) values('double n = 999999999999;5',5);


insert into t_option(option_desc,questionId) values('long n = 999999999999;6',6);
insert into t_option(option_desc,questionId) values('int n = 999999999999L;6',6);
insert into t_option(option_desc,questionId) values('long n = 999999999999L;6',6);
insert into t_option(option_desc,questionId) values('double n = 999999999999;6',6);

create table t_xxx(
	id int(8) primary key auto_increment,
	userId int(255) not null,
	paperId int(8) not null,
	score float not null,
	foreign key(userId) references t_user(id),
	foreign key(paperId) references t_paper(id)
)default charset=gbk ;
