INSERT INTO ACCOUNTS (email,username,passwordhash, salt) VALUES ('asdf@asdf.ch', 'Hans', 'c7c0ab669652af727ff2f99e6fd4bc99', 'blubbersal');
INSERT INTO ACCOUNTS (email,username,passwordhash, salt) VALUES ('abc@asdf.ch', 'Heinz', 'c7c0ab669652af727ff2f99e6fd4bc99', 'blubbersal');
INSERT INTO projects (projectid, title, description, owner_email) VALUES (1, 'TestProject1', 'Beschreibung', 'asdf@asdf.ch');
INSERT INTO projects (projectid, title, description, owner_email) VALUES (2, 'TestProject2', 'Beschreibung', 'abc@asdf.ch');
INSERT INTO projects_members (projects_projectid, members_email) VALUES (1, 'abc@asdf.ch');
INSERT INTO projects_members (projects_projectid, members_email) VALUES (1, 'asdf@asdf.ch');
INSERT INTO projects_members (projects_projectid, members_email) VALUES (2, 'asdf@asdf.ch');
INSERT INTO TASKS (taskid, title, email, description, projectid, responsible_user_email) VALUES (1, 'TestTask', 'asdf@asdf.ch', 'Descitpion', 2, 'asdf@asdf.ch' );
INSERT INTO TASKS (taskid, title, email, description, projectid, responsible_user_email) VALUES (2, 'TestTask2', 'abc@asdf.ch', 'Descitpion2', 1, 'abc@asdf.ch');
