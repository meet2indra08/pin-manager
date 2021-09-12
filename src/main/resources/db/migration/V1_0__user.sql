CREATE TABLE IF NOT EXISTS "d_user"
(
uid   UUID NOT NULL,
name varchar(50) NOT NULL,
msisdn varchar(50) NOT NULL,
created     timestamp NOT NULL,
status varchar(50),
CONSTRAINT PK_user PRIMARY KEY ( msisdn )
);

INSERT INTO "d_user"(
	uid, name, msisdn, created, status)
	VALUES ('c2ae5608-4b28-4c4d-9e4f-51edf31309c2','John','8527733090','2021-04-06 19:29:07.981',NULL);


CREATE TABLE IF NOT EXISTS "pin"
(
uid         UUID NOT NULL,
msisdn varchar(50) NOT NULL,
pin varchar(100) NOT NULL,
created     timestamp NOT NULL,
retryCount INTEGER,
status Boolean,
CONSTRAINT PK_pin PRIMARY KEY ( uid ),
CONSTRAINT FK_pin FOREIGN KEY ( msisdn ) REFERENCES "d_user" ( msisdn )
);