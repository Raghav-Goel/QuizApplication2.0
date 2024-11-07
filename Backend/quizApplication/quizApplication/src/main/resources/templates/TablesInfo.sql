-- create database quiz_app;
-- use quiz_app;
/*create table options(
	option_id bigint not null auto_increment,
    option_text varchar(250) not null,
    primary key (option_id)
);*/
/*create table question(
	question_id bigint not null auto_increment,
    question_text varchar(500) not null,
    correct_ans varchar(250) not null,
    primary key (question_id)
);*/

/*create table question_options(
	question_option_id bigint not null,
	question_id bigint not null,
    option_id bigint not null,
    primary key(question_option_id),
    foreign key(question_id) references question(question_id),
    foreign key(option_id) references options(option_id)
);*/
/*create table quiz(
quiz_id bigint not null auto_increment,
title varchar(2000),
primary key(quiz_id)
);*/
/*create table quiz_question(
	quiz_question_id bigint not null,
	question_id bigint not null,
    quiz_id bigint not null,
    primary key(quiz_question_id),
    foreign key(question_id) references question(question_id),
    foreign key(quiz_id) references quiz(quiz_id)
);*/
/*create Table user(
	user_id bigint not null auto_increment,
	name varchar(60),
    email_id varchar(60),
    password varchar(20),
    phone_number varchar(13),
	constraint ps_user_id_pk primary key (user_id)
);*/

/*create table user_quiz(
	user_quiz_id bigint not null auto_increment,
	user_id bigint not null,
    quiz_id bigint not null,
    marks int default 0,
    primary key(user_quiz_id),
    foreign key(user_id) references user(user_id),
    foreign key(quiz_id) references quiz(quiz_id)
);*/
-- alter table user_quiz add column attempted boolean;