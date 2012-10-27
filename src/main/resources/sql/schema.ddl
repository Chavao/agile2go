
    create table identity_attributes (
        id int8 not null,
        name varchar(255),
        value varchar(255),
        IDENTITY_OBJECT_ID int8,
        primary key (id)
    );

    create table identity_credential_types (
        id int8 not null,
        name varchar(255),
        primary key (id)
    );

    create table identity_credentials (
        id int8 not null,
        value varchar(255),
        CREDENTIAL_IDENTITY_OBJECT_ID int8,
        CREDENTIAL_TYPE_ID int8,
        primary key (id)
    );

    create table identity_object_relationship_types (
        id int8 not null,
        name varchar(255),
        primary key (id)
    );

    create table identity_object_relationships (
        id int8 not null,
        name varchar(255),
        FROM_IDENTITY_ID int8,
        RELATIONSHIP_TYPE_ID int8,
        TO_IDENTITY_ID int8,
        primary key (id)
    );

    create table identity_object_types (
        id int8 not null,
        name varchar(255),
        primary key (id)
    );

    create table identity_objects (
        id int8 not null,
        name varchar(255),
        IDENTITY_OBJECT_TYPE_ID int8,
        primary key (id)
    );

    create table identity_roles (
        id int8 not null,
        name varchar(255),
        primary key (id)
    );

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
        primary key (SPRINT_ID)
    );

    create table scrum.TASK (
        TASK_ID  serial not null,
        HOURS varchar(5) not null,
        PRIORITY int4 not null,
        STATUS varchar(15),
        STORIE varchar(255) not null,
        SPRINT_ID int4,
        primary key (TASK_ID)
    );

    create table scrum.USER (
        USER_ID  serial not null,
        LOGIN varchar(20) not null,
        NAME varchar(60) not null,
        PASSWORD varchar(20) not null,
        role varchar(10),
        primary key (USER_ID),
        unique (LOGIN)
    );

    alter table identity_attributes 
        add constraint FK4E564B784F773B59 
        foreign key (IDENTITY_OBJECT_ID) 
        references identity_objects;

    alter table identity_credentials 
        add constraint FKA024C4FBE7D47441 
        foreign key (CREDENTIAL_IDENTITY_OBJECT_ID) 
        references identity_objects;

    alter table identity_credentials 
        add constraint FKA024C4FB49FE5E8 
        foreign key (CREDENTIAL_TYPE_ID) 
        references identity_credential_types;

    alter table identity_object_relationships 
        add constraint FKD0DFD91CC9343AA6 
        foreign key (FROM_IDENTITY_ID) 
        references identity_objects;

    alter table identity_object_relationships 
        add constraint FKD0DFD91C2BBB7FF7 
        foreign key (TO_IDENTITY_ID) 
        references identity_objects;

    alter table identity_object_relationships 
        add constraint FKD0DFD91CCC5DDC6A 
        foreign key (RELATIONSHIP_TYPE_ID) 
        references identity_object_relationship_types;

    alter table identity_objects 
        add constraint FKB4E63E9351DEE43A 
        foreign key (IDENTITY_OBJECT_TYPE_ID) 
        references identity_object_types;

    alter table scrum.SPRINT 
        add constraint FK922FF61A4F4B444E 
        foreign key (PROJECT_ID) 
        references scrum.PROJECT;

    alter table scrum.TASK 
        add constraint FK272D85E4B637C6 
        foreign key (SPRINT_ID) 
        references scrum.SPRINT;

    create sequence hibernate_sequence;
