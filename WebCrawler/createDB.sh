#!/usr/bin/env bash

mysql -u sts -p < ./dao/src/main/resources/db/database.sql

mysql -u sts -p -D testCrawlerDB << FIRST_DATA
INSERT INTO userData(login, password, userName) VALUES('sts', '459CB1E41FECA155E33FB8D033CB4925FE2CCB7650587C8145310D223D26257A2033FDEAE6F900B638C3643F98F64C0DF23E6553D49CA7233B4750507BEB19EF', 'Sotnyk Tetiana');
INSERT INTO rolesGroup(groupName, description) VALUES ('admin', 'full access');
INSERT INTO rolesGroup(groupName, description) VALUES ('user', "guest's right plus node's manage");
INSERT INTO rolesGroup(groupName, description) VALUES ('guest', "view his profile, change his profile (he has no right to change the role group), view statistic about web crawler's and node's work");
INSERT INTO permission(groupId, userId) VALUES (1, 1);
INSERT INTO permission(groupId, userId) VALUES (2, 1);
INSERT INTO permission(groupId, userId) VALUES (3, 1);
INSERT INTO urlData(amountReadPage, status, amountTransition, statusChangeTime, url) VALUES (0, 0, 1, 1503527348147, 'http://j2w.blogspot.com');
INSERT INTO urlData(amountReadPage, status, amountTransition, statusChangeTime, url) VALUES (0, 0, 1, 1503527348147, 'https://en.wikipedia.org/wiki/Spring_Framework');
INSERT INTO urlData(amountReadPage, status, amountTransition, statusChangeTime, url) VALUES (0, 0, 1, 1503527348147, 'http://docs.oracle.com/javase/7/docs/technotes/tools/windows/classpath.html');
INSERT INTO urlData(amountReadPage, status, amountTransition, statusChangeTime, url) VALUES (0, 0, 1, 1503527348147, 'https://en.wikipedia.org/wiki/Universally_unique_identifier');
INSERT INTO urlData(amountReadPage, status, amountTransition, statusChangeTime, url) VALUES (0, 0, 1, 1503527348147, 'http://community.actian.com/wiki/SQL_BOOLEAN_type');
INSERT INTO urlData(amountReadPage, status, amountTransition, statusChangeTime, url) VALUES (0, 0, 1, 1503527348147, 'https://www.blueapron.com');
INSERT INTO urlData(amountReadPage, status, amountTransition, statusChangeTime, url) VALUES (0, 0, 1, 1503527348147, 'https://www.wellsfargo.com');
INSERT INTO urlData(amountReadPage, status, amountTransition, statusChangeTime, url) VALUES (0, 0, 1, 1503527348147, 'http://www.javaworld.com');
INSERT INTO urlData(amountReadPage, status, amountTransition, statusChangeTime, url) VALUES (0, 0, 1, 1503527348147, 'https://docs.oracle.com');
FIRST_DATA


