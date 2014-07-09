CREATE SEQUENCE LOG_IMPORT_SEQ;
  
CREATE TABLE LOG_IMPORT
(
  LOG_IMPORT_ID    NUMBER                  NOT NULL DEFAULT (SELECT LOG_IMPORT_SEQ.NEXTVAL from DUAL),
  IMPORT_DATATYPE  VARCHAR2(10)            NOT NULL,
  FILE_NAME        VARCHAR2(100)           NOT NULL,
  MESSAGE_CODE     VARCHAR2(100)           NOT NULL,
  CREATE_USER      VARCHAR2(100)           NOT NULL,
  CREATE_DATE      DATE                         NOT NULL,
  PROGRAM_ID       VARCHAR2(20)            NOT NULL
);

ALTER TABLE LOG_IMPORT ADD PRIMARY KEY (LOG_IMPORT_ID);


create table IMPORT_WORKFLOW_REVENUE_AR
(
  COMPANY_CODE           VARCHAR2(5) not null,
  REVENUE_SOURCE         VARCHAR2(10) not null,
  DOCUMENT_TYPE          VARCHAR2(10) not null,
  INVOICE_DATE           DATE not null,
  INTERFACE_DATE         DATE,
  DOCUMENT_NO            VARCHAR2(20),
  DOCUMENT_HEADER_TEXT   VARCHAR2(20) not null,
  REFERENCE              VARCHAR2(100),
  BUSSINESS_PLACE        VARCHAR2(100),
  ACCOUNT_VENDORCUSTOMER VARCHAR2(20) not null,
  AMOUNT                 NUMBER,
  PROFIT_CENTER          VARCHAR2(10),
  INT_ORD                VARCHAR2(20),
  VALUE_DATE             DATE,
  PERIOD                 VARCHAR2(100),
  ASSIGN                 VARCHAR2(100),
  REF1                   VARCHAR2(100),
  REF2                   VARCHAR2(100),
  INC_TYPE               VARCHAR2(100),
  P_TERM                 VARCHAR2(20),
  BS_DATE                DATE,
  P_BLOCK                VARCHAR2(20),
  VAT_CODE               VARCHAR2(10),
  VAT_BASE               NUMBER,
  VAT_AMT                NUMBER,
  WH_CODE                VARCHAR2(5),
  WH_BASE                NUMBER,
  WH_AMT                 NUMBER,
  ALT_PAYEE              VARCHAR2(30),
  IND_PAYEE              VARCHAR2(30),
  IND_PAYLINE1           VARCHAR2(30),
  IND_PAYLINE2           VARCHAR2(30),
  IND_PAYCIT             VARCHAR2(1),
  CREATE_USER            VARCHAR2(1),
  CREATE_DATE            DATE,
  UPDATE_PROGRAM         DATE
);



DROP alias if exists TO_CHAR;
CREATE alias TO_CHAR as $$
import java.text.SimpleDateFormat;
import java.util.Date;
@CODE
String toChar(String date, String pattern) throws Exception {
	pattern = pattern.replaceAll("YY","yy");
	pattern = pattern.replaceAll("DD","dd");
	pattern = pattern.replaceAll("HH24|hh24","HH");
	pattern = pattern.replaceAll("HH?!24|hh?!24","KK");
	pattern = pattern.replaceAll("MON|mon","MMM");
	pattern = pattern.replaceAll("MI|mi","mm");
	pattern = pattern.replaceAll("SS|ss","ss");
	pattern = pattern.replaceAll("AM|PM","aa");
	SimpleDateFormat sm = new SimpleDateFormat(pattern);
	java.util.Date dt;
	  if(date.length() > 10)dt = java.sql.Timestamp.valueOf(date);
	else
	 dt = java.sql.Date.valueOf(date);
	return sm.format(dt); 
}
$$

