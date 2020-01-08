create table user_info
(
userid bigint(18),
username varchar(100) not null,
password varchar(100) not null,
name varchar(100) not null,
gender int1 not null, 
primary key (userid)
);

create table city
(
cityid bigint(18) auto_increment,
cityname varchar(100) not null,
statename varchar(100) not null,
primary key (cityid)
);

insert into city (statename, cityname) values ("Delhi", "new Delhi"), ("Bihar", "Patna"), ("Karnataka", "Bangalore");