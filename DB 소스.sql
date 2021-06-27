-- member 테이블
create table member (
memcode integer constraint mem_PK primary key,
name varchar2(50) not null,
id varchar2(50) unique not null,
pw varchar2(50) not null,
address varchar2(255),
phone varchar2(30) not null,
point number(10) default '0' not null
);

-- member 시퀀스
create sequence member_sq
start with 1;

-- sale 테이블
create table sale (
salecode integer constraint sale_PK primary key,
sname varchar2(50) not null,
price integer not null,
saledate date default sysdate,
id varchar2(50) not null,
count number(10) not null
);

-- sale 시퀀스
create sequence sale_sq
start with 1;

-- 메뉴 DB
create table menu (
menucode integer constraint menu_PK primary key,
mname varchar2(50) not null,
price number(10) not null
);

-- 메뉴 시퀀스
create sequence menu_sq
start with 1;


-- member 값
-- id는 a,b,c순
-- 비밀번호는 1234 통일
insert into member (memcode, name, id, pw, address, phone, point) values (member_sq.nextval, '김은선', 'a', '1234', '서울', '01012345678', 1000);
insert into member (memcode, name, id, pw, address, phone, point) values (member_sq.nextval, '위세라', 'b', '1234', '서울', '01012345678', 1500);
insert into member (memcode, name, id, pw, address, phone, point) values (member_sq.nextval, '이윤성', 'c', '1234', '서울', '01012345678', 2000);
insert into member (memcode, name, id, pw, address, phone, point) values (member_sq.nextval, '이현우', 'd', '1234', '서울', '01012345678', 5000);
insert into member (memcode, name, id, pw, address, phone, point) values (member_sq.nextval, '박지성', 'e', '1234', '서울', '01012345678', 9000);
insert into member (memcode, name, id, pw, address, phone, point) values (member_sq.nextval, '이강인', 'f', '1234', '서울', '01012345678', 4000);

-- sale 값
insert into sale(salecode, sname, price,saledate, id, count) values (sale_sq.nextval, 'latte', 4600, '21/06/21', 'a', 2);
insert into sale(salecode, sname, price,saledate, id, count) values (sale_sq.nextval, 'salad', 5000, '21/06/21', 'a', 1);
insert into sale(salecode, sname, price,saledate, id, count) values (sale_sq.nextval, 'cake', 5500, '21/06/22', 'b', 3);
insert into sale(salecode, sname, price,saledate, id, count) values (sale_sq.nextval, 'Amerciano', 4100, '21/06/22', 'a', 2);
insert into sale(salecode, sname, price, id, count) values (sale_sq.nextval, 'americano', 4100, 'a', 5);
insert into sale(salecode, sname, price, id, count) values (sale_sq.nextval, 'latte', 4600, 'c', 4);
insert into sale(salecode, sname, price, id, count) values (sale_sq.nextval, 'sandwich', 6200, 'a', 7);
insert into sale(salecode, sname, price, id, count) values (sale_sq.nextval, 'sandwich', 6200, 'a', 1);


insert into menu(menucode, mname, price) values (menu_sq.nextval, 'americano', 4200);
insert into menu(menucode, mname, price) values (menu_sq.nextval, 'latte', 4700);
insert into menu(menucode, mname, price) values (menu_sq.nextval, 'sandwich', 4800);
insert into menu(menucode, mname, price) values (menu_sq.nextval, 'bagel', 2300);
insert into menu(menucode, mname, price) values (menu_sq.nextval, 'cake', 4800);
