--Players
INSERT INTO  player(first_name, last_name, height,shirt_number, position,nationality,category,dob,photo)
    VALUES ('Andrei','Sasu',186,6,'setter','romana', 'senior','1986-07-06',FILE_READ('src/main/resources/images/players/1-Andrei.jpeg'));
INSERT INTO  player(first_name, last_name, height,shirt_number, position,nationality,category,dob,photo)
    VALUES ('Alexandru','Dragomir',195,12,'setter','romana', 'senior','1999-07-02',FILE_READ('src/main/resources/images/players/2-Alexandru.jpeg'));
INSERT INTO  player(first_name, last_name, height,shirt_number, position,nationality,category,dob,photo)
    VALUES ('Darius ','Pop',200,2,'opposite','romana', 'senior','2003-03-10',FILE_READ('src/main/resources/images/players/3-Darius.jpeg'));
INSERT INTO  player(first_name, last_name, height,shirt_number, position,nationality,category,dob,photo)
    VALUES ('Ioan','Verciuc',195,4,'outside hitter','romana', 'senior','2008-01-23',FILE_READ('src/main/resources/images/players/4-Ioan.jpeg'));
INSERT INTO  player(first_name, last_name, height,shirt_number, position,nationality,category,dob,photo)
    VALUES ('Alexandru','Dragomir',185,12,'outside hitter','romana', 'senior','2002-12-05',FILE_READ('src/main/resources/images/players/5-Alexandru.jpeg'));
INSERT INTO  player(first_name, last_name, height,shirt_number, position,nationality,category,dob,photo)
VALUES ('Marius','Gontariu',194,9,'outside hitter','romana', 'senior','1992-08-05',FILE_READ('src/main/resources/images/players/6-Marius.jpeg'));
    INSERT INTO  player(first_name, last_name, height,shirt_number, position,nationality,category,photo)
VALUES ('Sabin','Hartopeanu',195,7,'middle-blocker','romana', 'senior',FILE_READ('src/main/resources/images/players/7-Sabin.jpeg'));
    INSERT INTO  player(first_name, last_name, height,shirt_number, position,nationality,category,dob,photo)
    VALUES ('Cosmin','Boghean',195,11,'middle-blocker','romana', 'senior','2002-07-17',FILE_READ('src/main/resources/images/players/8-Cosmin.jpeg'));
INSERT INTO  player(first_name, last_name, height,shirt_number, position,nationality,category,dob,photo)
VALUES ('Cezar','Ciubotariu',195,13,'middle-blocker','romana', 'senior','2004-07-28',FILE_READ('src/main/resources/images/players/9-Cezar.jpeg'));
INSERT INTO  player(first_name, last_name, height,shirt_number, position,nationality,category,photo)
    VALUES ('Cosmin Robert','Ciubotaru',205,14,'middle-blocker','romana', 'senior',FILE_READ('src/main/resources/images/players/10-Cosmin.jpeg'));
INSERT INTO  player(first_name, last_name, height,shirt_number, position,nationality,category,dob,photo)
    VALUES ('Matei','Platon',185,12,'libero','romana', 'senior','2003-07-27',FILE_READ('src/main/resources/images/players/11-Matei.jpeg'));
INSERT INTO  player(first_name, last_name, height,shirt_number, position,nationality,category,dob,photo)
    VALUES ('Andrei','Curic',173,15,'libero','romana', 'senior','2004-05-02',FILE_READ('src/main/resources/images/players/12-Andrei.jpeg'));

INSERT INTO coach(first_name, last_name, photo) VALUES ('Tudor','Orasanu',FILE_READ('src/main/resources/images/coach/1-Tudor.jpg'));

INSERT INTO sponsor(name,site_link,logo) VALUES ('Assist Software', 'https://assist-software.net/', FILE_READ('src/main/resources/images/sponsors/1-Assist.jpeg'));
INSERT INTO sponsor(name,site_link,logo) VALUES ('Tipografia Celestin', 'https://www.tipografiacelestin.ro/', FILE_READ('src/main/resources/images/sponsors/2-Celestin.jpeg'));
INSERT INTO sponsor(name,site_link,logo) VALUES ('Primaria Suceava', 'http://evp.primariasv.ro/dm_suceava/site.nsf', FILE_READ('src/main/resources/images/sponsors/3-Suceava.png'));

INSERT INTO subscriber(email) values ('alin.orobet@student.usv.ro');
INSERT INTO subscriber(email) values ('alin.chiperi@student.usv.ro');
INSERT INTO subscriber(email) values ('stefan.bejinariu@student.usv.ro');
INSERT INTO subscriber(email) values ('stefan.taranu@student.usv.ro');

INSERT INTO tag ( name) values ('CSM');
INSERT INTO tag ( name) values ('Volei');
INSERT INTO tag ( name) values ('Suceava');
INSERT INTO post(title, under_title, created_by ,content, created_on) values ('Csm Campioana','Echipa de volei a Sucevei a castigat campionatul','Alin Chiperi','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Eget est lorem ipsum dolor sit. Tortor id aliquet lectus proin.', '2022-12-21');

INSERT INTO post_tag(post_id, tag_id) values (1,1);

INSERT INTO team(name, logo) values ('CSM Suceva', FILE_READ('src/main/resources/images/teams/logo_CSM.png'));
INSERT INTO team(name, logo) values ('CSM Falticeni ', FILE_READ('src/main/resources/images/teams/logo_CSM.png'));






