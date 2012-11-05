
-- user  
insert into scrum.user (NAME, LOGIN, PASSWORD, ROLE)
values ('Rafael Jesus', 'adm', '123', 'MASTER');

insert into scrum.user(NAME, LOGIN, PASSWORD, ROLE)
values ('Barbara Veloso', 'admin', '123', 'MASTER');

-- project
insert into scrum.project (NAME, DESCRIPTION, LAST_DATE, COMPANY)
values ('Vitae Featurekids', 'a management system for hospitals', '2012-08-05', 'Salute');

insert into scrum.project (NAME, DESCRIPTION, LAST_DATE, COMPANY)
values ('RSB', 'a management system for managing agile projects', '2012-06-01', 'Agile2Go');

insert into scrum.project (NAME, DESCRIPTION, LAST_DATE, COMPANY)
values ('SIMOVA', 'management system of civil works', '2012-09-01', 'Construmobil');

insert into scrum.project (NAME, DESCRIPTION, LAST_DATE, COMPANY)
values ('QMSSQA', 'management system of civil works', '2012-09-01', 'SIMOVA');

insert into scrum.project (NAME, DESCRIPTION, LAST_DATE, COMPANY)
values ('SocialPM', 'a management system for managing social agile projects', '2012-12-01', 'OCPSoft');

insert into scrum.project (NAME, DESCRIPTION, LAST_DATE, COMPANY)
values ('Preatty Faces', 'get your url beaultiful', '2012-12-01', 'OCPSoft');

insert into scrum.project (NAME, DESCRIPTION, LAST_DATE, COMPANY)
values ('Readmine', 'a bugtracking project made in ruby on rails for agile projects', '2012-12-01', 'Readmine Tools');

-- sprint
insert into scrum.sprint (NAME, START_DATE, END_DATE, DAILY_SCRUM, GOAL, PROJECT_ID)
values ('S-1', '2012-12-01', '2012-12-15', '10:00', 'Delivery for PO a potencial increment', 1);

-- insert into scrum.sprint values (2, '09:00', '2012-12-05', 'selenium tests implementation', 'Iteration 02', '2012-12-20', 1);
-- insert into scrum.sprint values (3, '09:00', '2013-01-10', 'user view implementation', 'Iteration 03', '2013-01-25', 1);
-- insert into scrum.sprint values (4, '09:00', '2013-01-15', 'user crud implementation', 'Iteration 04', '2013-02-01', 1);
-- insert into scrum.sprint values (5, '09:00', '2013-02-01', 'arquillian crud tests implementation', 'Iteration 05', '2013-02-27', 1);

-- task
-- insert into scrum.sprint values (1, '00:45', 1, 1, 'any user story', 1);
-- insert into scrum.sprint values (2, '00:30', 2, 1, 'any user story', 1);
-- insert into scrum.sprint values (3, '01:00', 2, 2, 'any user story', 1);
-- insert into scrum.sprint values (4, '01:45', 3, 2, 'any user story', 1);
-- insert into scrum.sprint values (5, '06:00', 5, 1, 'any user story', 1);







