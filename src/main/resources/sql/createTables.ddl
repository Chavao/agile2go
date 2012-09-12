
    drop table if exists identity_attributes cascade;
    drop table if exists identity_credential_types cascade;
    drop table if exists identity_credentials cascade;
    drop table if exists identity_object_relationship_types cascade;
    drop table if exists identity_object_relationships cascade;
    drop table if exists identity_object_types cascade;
    drop table if exists identity_objects cascade;
    drop table if exists identity_roles cascade;
    drop table if exists scrum.PROJECT cascade;
    drop table if exists scrum.SPRINT cascade;
    drop table if exists scrum.TASK cascade;
    drop table if exists scrum.USER cascade;
    drop sequence if exists hibernate_sequence;
    
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
        unique (NAME)
    );
    alter table identity_attributes 
        add constraint FK4E564B7861AC3425 
        foreign key (IDENTITY_OBJECT_ID) 
        references identity_objects;
        
    alter table identity_credentials 
        add constraint FKA024C4FBFA096D0D 
        foreign key (CREDENTIAL_IDENTITY_OBJECT_ID) 
        references identity_objects;
        
    alter table identity_credentials 
        add constraint FKA024C4FB559B89B4 
        foreign key (CREDENTIAL_TYPE_ID) 
        references identity_credential_types;
 
    alter table identity_object_relationships 
        add constraint FKD0DFD91CDB693372 
        foreign key (FROM_IDENTITY_ID) 
        references identity_objects;
 
    alter table identity_object_relationships 
        add constraint FKD0DFD91C3DF078C3 
        foreign key (TO_IDENTITY_ID) 
        references identity_objects;
 
    alter table identity_object_relationships 
        add constraint FKD0DFD91CCCFFBD36 
        foreign key (RELATIONSHIP_TYPE_ID) 
        references identity_object_relationship_types;
 
    alter table identity_objects 
        add constraint FKB4E63E93DC129706 
        foreign key (IDENTITY_OBJECT_TYPE_ID) 
        references identity_object_types;
 
    alter table scrum.SPRINT 
        add constraint FK922FF61A6C43551A 
        foreign key (PROJECT_ID) 
        references scrum.PROJECT;
 
    alter table scrum.TASK 
        add constraint FK272D859B52DD7A 
        foreign key (SPRINT_ID) 
        references scrum.SPRINT;
 
    create sequence hibernate_sequence;