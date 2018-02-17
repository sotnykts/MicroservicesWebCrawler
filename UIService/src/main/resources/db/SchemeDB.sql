CREATE TABLE userData      (
                                   userId INT(20) NOT NULL AUTO_INCREMENT,
                                   login TEXT NOT NULL,
                                   userName TEXT NOT NULL,
                                   password TEXT NOT NULL,
                                   PRIMARY KEY(userId)
                                    );

CREATE TABLE rolesGroup   (
                                   groupId INT(20) NOT NULL AUTO_INCREMENT,
                                   groupName TEXT NOT NULL,
                                   description TEXT,
                                   PRIMARY KEY (groupId)
                                   );


CREATE TABLE permission   (
                                  permissionId INT(20) NOT NULL AUTO_INCREMENT,
                                  userId INT(20) NOT NULL,
                                  groupId INT(20) NOT NULL,
                                  PRIMARY KEY (permissionId)
                                   );

CREATE TABLE nodeData       (
                                  nodeId INT(20) NOT NULL AUTO_INCREMENT,
                                  nodeName TEXT NOT NULL,
                                  startUnixTime BIGINT(20),
                                  stopUnixTime BIGINT(20),
                                  stoppedFlag BOOL DEFAULT FALSE ,
                                  statusWork BOOL DEFAULT TRUE ,
                                  PRIMARY KEY(nodeId)
                                  );


CREATE TABLE urlData      (
                                  urlId INT(20) NOT NULL AUTO_INCREMENT,
                                  url LONGTEXT,
                                  nodeId INT(20),
                                  amountTransition INT(11),
                                  status INT(11),
                                  amountReadPage INT(11),
                                  statusChangeTime BIGINT(20),
                                  PRIMARY KEY(urlId)
                                  );

CREATE TABLE pageInformation    (
                                  pageId INT(20) NOT NULL AUTO_INCREMENT,
                                  urlId INT(20)NOT NULL,
                                  dateInformation DATE,
                                  pageText LONGTEXT CHARACTER SET utf8,
                                  PRIMARY KEY(pageId, urlId)
                                  );

CREATE TABLE wordInformation (
                                  wordId INT(20) NOT NULL AUTO_INCREMENT,
                                  pageId INT(20) NOT NULL,
                                  urlId INT(20) NOT NULL,
                                  word VARCHAR(254) NOT NULL,
                                  amountOnPage SMALLINT(5) NOT NULL,
                                  PRIMARY KEY(wordId, pageId, urlId)
                                  );
