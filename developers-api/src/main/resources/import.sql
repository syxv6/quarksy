INSERT INTO Developer (id, name, team, createdat, updatedat) VALUES ('982f5c0b-33c9-4d17-ba3c-d06c45f28ee1', 'Foo', 'Boo', '2020-09-20T14:34:23+10:00', '2020-09-20T14:34:23+10:00');
INSERT INTO Developer (id, name, team, createdat, updatedat) VALUES ('982f5c0b-33c9-4d17-ba3c-d06c45f28ee2', 'Foo2', 'Boo2', '2020-09-20T14:34:23+10:00', '2020-09-20T14:34:23+10:00');

INSERT INTO Skill (id, skill, developer_id) VALUES (101, 'Expert in Quarkus', '982f5c0b-33c9-4d17-ba3c-d06c45f28ee1');
INSERT INTO Skill (id, skill, developer_id) VALUES (102, 'Expert in Quarkus 2', '982f5c0b-33c9-4d17-ba3c-d06c45f28ee1');

INSERT INTO Skill (id, skill, developer_id) VALUES (103, 'Phd of Quarkus', '982f5c0b-33c9-4d17-ba3c-d06c45f28ee2');
INSERT INTO Skill (id, skill, developer_id) VALUES (104, 'Phd of Quarkus 2', '982f5c0b-33c9-4d17-ba3c-d06c45f28ee2');
