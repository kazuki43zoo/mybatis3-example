DROP TABLE IF EXISTS t_image;
DROP TABLE IF EXISTS t_text;
DrOP TABLE IF EXISTS t_account;
CREATE TABLE t_image(
  id CHAR(36)
  ,image_data BLOB NOT NULL
  ,created_at TIMESTAMP NOT NULL
  ,CONSTRAINT t_image_pk PRIMARY KEY (id)
);
CREATE TABLE t_text(
  id CHAR(36)
  ,text_data CLOB NOT NULL
  ,created_at TIMESTAMP NOT NULL
  ,CONSTRAINT t_text_pk PRIMARY KEY (id)
);
CREATE TABLE t_account(
   account_uuid CHAR(36)
  ,account_name NVARCHAR(256)
  ,birth_date TIMESTAMP
  ,CONSTRAINT t_account_pk PRIMARY KEY (account_uuid)
);
COMMIT;
