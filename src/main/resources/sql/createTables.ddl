
    create table scrum.PROJECT (
        PROJECT_ID  serial not null,
        COMPANY varchar(60) not null,
        DESCRIPTION varchar(255) not null,
        LAST_DATE date not null,
        NAME varchar(60) not null,
        primary key (PROJECT_ID),
        unique (NAME)
    );

    create table scrum.SPRINT (
        SPRINT_ID  serial not null,
        DAILY_SCRUM varchar(5) not null,
        END_DATE date not null,
        GOAL varchar(60) not null,
        NAME varchar(60) not null,
        START_DATE date not null,
        PROJECT_ID int4,
        primary key (SPRINT_ID),
        unique (PROJECT_ID)
    );

    create table scrum.TASK (
        TASK_ID  serial not null,
        HOURS varchar(5) not null,
        PRIORITY int4 not null,
        STATUS varchar(15),
        STORY varchar(60) not null,
        SPRINT_ID int4,
        primary key (TASK_ID),
        unique (SPRINT_ID)
    );

    create table scrum.USER (
        USER_ID  serial not null,
        CITY varchar(60),
        STREET varchar(60),
        UF varchar(2),
        LOGIN varchar(20) not null unique,
        NAME varchar(100) not null,
        PASSWORD varchar(15) not null,
        role varchar(10),
        primary key (USER_ID),
        unique (NAME)
    );

    alter table scrum.SPRINT 
        add constraint FK922FF61A6C43551A 
        foreign key (PROJECT_ID) 
        references scrum.PROJECT;

    alter table scrum.TASK 
        add constraint FK272D859B52DD7A 
        foreign key (SPRINT_ID) 
        references scrum.SPRINT;
