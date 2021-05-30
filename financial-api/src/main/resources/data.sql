DROP TABLE IF EXISTS USER_ENTITY;
CREATE TABLE IF NOT EXISTS USER_ENTITY(
 CLIENT_ID BIGINT NOT NULL,
 EMAIL VARCHAR(100),
 NAME VARCHAR(100),
 PRIMARY KEY (CLIENT_ID)
);

INSERT INTO USER_ENTITY VALUES(1234,'test@test.com','test');

DROP TABLE IF EXISTS REVENUE_EXPENSE_ENTITY;
CREATE TABLE IF NOT EXISTS REVENUE_EXPENSE_ENTITY(
 ID BIGINT NOT NULL AUTO_INCREMENT,
 NAME VARCHAR(100),
 TYPE VARCHAR(100),
 TYPE_REVENUE_EXPENSE VARCHAR(100),
 AMOUNT BIGINT,
 DATE TIMESTAMP,
 CLIENT_ID BIGINT,
 PRIMARY KEY (ID)
);

INSERT INTO REVENUE_EXPENSE_ENTITY VALUES(1,'Invitar a los amigos hamburguesa','EXPENSE','Comida',1000000,'2021-06-24 00:00:00',1234);
INSERT INTO REVENUE_EXPENSE_ENTITY VALUES(2,'Salitre Magico','EXPENSE','Diversion',2000000,'2021-06-24 00:00:00',1234);
INSERT INTO REVENUE_EXPENSE_ENTITY VALUES(3,'Semestre','EXPENSE','Educacion',52000000,'2021-08-22 00:00:00',1234);
INSERT INTO REVENUE_EXPENSE_ENTITY VALUES(4,'Salario mensual','REVENUE','Salario',60000000,'2021-07-24 00:00:00',1234);
INSERT INTO REVENUE_EXPENSE_ENTITY VALUES(5,'Venta computador','REVENUE','Trabajo extra',20000000,'2021-05-24 00:00:00',1234);
INSERT INTO REVENUE_EXPENSE_ENTITY VALUES(6,'Bolos','EXPENSE','Diversion',900000,'2021-04-24 00:00:00',1234);
INSERT INTO REVENUE_EXPENSE_ENTITY VALUES(7,'Perro Caliente','EXPENSE','Comida',100000,'2021-03-24 00:00:00',1234);
INSERT INTO REVENUE_EXPENSE_ENTITY VALUES(8,'Pizza','EXPENSE','Comida',150000,'2021-02-24 00:00:00',1234);
INSERT INTO REVENUE_EXPENSE_ENTITY VALUES(9,'Paseo Medellin','EXPENSE','Diversion',1000000,'2021-01-24 00:00:00',1234);