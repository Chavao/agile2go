
-- user  
insert into scrum.user values (1, 'São José dos Campos', 'Rua Francisca Maria de Jesus', 'SP', 'adm', 'Rafael Jesus', 'password', 'MASTER');
insert into scrum.user values (2, 'São José dos Campos', null, 'SP', 'adm', 'Barbara Veloso', 'password', 'MASTER');

-- project
insert into scrum.project values (1, 'Vitae Featurekids', 'a management system for hospitals', '2012-08-05', 'Salute');
insert into scrum.project values (2, 'RSB', 'a management system for managing agile projects', '2012-06-01', 'Agile2Go');
insert into scrum.project values (3, 'SIMOVA', 'management system of civil works', '2012-09-01', 'Construmobil');
insert into scrum.project values (4, 'SIMOVA', 'management system of civil works', '2012-09-01', 'QMSSQA');
insert into scrum.project values (5, 'OCPSoft', 'a management system for managing social agile projects', '2012-12-01', 'SocialPM');


-- sprint
insert into scrum.sprint values (1, '09:00', '2012-12-01', 'login implementation', 'Iteration 01', new Date(), 1);
insert into scrum.sprint values (2, '09:00', '2012-12-01', 'selenium tests implementation', 'Iteration 02', new Date(), 1);
insert into scrum.sprint values (3, '09:00', '2012-12-01', 'user view implementation', 'Iteration 03', new Date(), 1);
insert into scrum.sprint values (4, '09:00', '2012-12-01', 'user crud implementation', 'Iteration 04', new Date(), 1);
insert into scrum.sprint values (5, '09:00', '2012-12-01', 'arquillian crud tests implementation', 'Iteration 05', new Date(), 1);

-- task
insert into scrum.sprint values (1, '00:45', 1, 1, 'any user story', 1);
insert into scrum.sprint values (2, '00:30', 2, 1, 'any user story', 1);
insert into scrum.sprint values (3, '01:00', 2, 2, 'any user story', 1);
insert into scrum.sprint values (4, '01:45', 3, 2, 'any user story', 1);
insert into scrum.sprint values (5, '06:00', 5, 1, 'any user story', 1);







