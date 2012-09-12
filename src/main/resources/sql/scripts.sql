
-- user  
insert into scrum.user values (1, 'adm', 'Rafael Jesus', 'password', 'MASTER');
insert into scrum.user values (2, 'adm', 'Barbara Veloso', 'password', 'MASTER');

-- project
insert into scrum.project values (1, 'Vitae Featurekids', 'a management system for hospitals', '2012-08-05', 'Salute');
insert into scrum.project values (2, 'RSB', 'a management system for managing agile projects', '2012-06-01', 'Agile2Go');
insert into scrum.project values (3, 'SIMOVA', 'management system of civil works', '2012-09-01', 'Construmobil');
insert into scrum.project values (4, 'SIMOVA', 'management system of civil works', '2012-09-01', 'QMSSQA');
insert into scrum.project values (5, 'OCPSoft', 'a management system for managing social agile projects', '2012-12-01', 'SocialPM');


-- sprint
insert into scrum.sprint values (1, '09:00', '2012-12-01', 'login implementation', 'Iteration 01', '2012-12-15' , 1);
insert into scrum.sprint values (2, '09:00', '2012-12-05', 'selenium tests implementation', 'Iteration 02', '2012-12-20', 1);
insert into scrum.sprint values (3, '09:00', '2013-01-10', 'user view implementation', 'Iteration 03', '2013-01-25', 1);
insert into scrum.sprint values (4, '09:00', '2013-01-15', 'user crud implementation', 'Iteration 04', '2013-02-01', 1);
insert into scrum.sprint values (5, '09:00', '2013-02-01', 'arquillian crud tests implementation', 'Iteration 05', '2013-02-27', 1);

-- task
insert into scrum.sprint values (1, '00:45', 1, 1, 'any user story', 1);
insert into scrum.sprint values (2, '00:30', 2, 1, 'any user story', 1);
insert into scrum.sprint values (3, '01:00', 2, 2, 'any user story', 1);
insert into scrum.sprint values (4, '01:45', 3, 2, 'any user story', 1);
insert into scrum.sprint values (5, '06:00', 5, 1, 'any user story', 1);







