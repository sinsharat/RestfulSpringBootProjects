create table course (
id varchar(255) not null, 
description varchar(255), 
name varchar(255), 
topic_id varchar(255), 
primary key (id)
);

create table topic (
id varchar(255) not null, 
description varchar(255), 
name varchar(255), 
primary key (id)
);

alter table course add constraint FKokaxyfpv8p583w8yspapfb2ar foreign key (topic_id) references topic (id);

create table userinfo
(
id bigint(18),
user_name varchar(250) not null,
password varchar(250) not null,
role varchar(100) not null,
islogin int1 not null default 0,
primary key (id)
);