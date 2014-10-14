DROP TABLE t_image;
DROP TABLE t_text;
DROP TABLE t_account_address;
DROP TABLE t_account;
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
  ,account_name NVARCHAR2(256)
  ,birth_date TIMESTAMP
  ,CONSTRAINT t_account_pk PRIMARY KEY (account_uuid)
);
CREATE TABLE t_account_address(
   account_uuid CHAR(36)
  ,zip_code CHAR(7)
  ,address  NVARCHAR2(256)
  ,CONSTRAINT t_account_address_pk PRIMARY KEY (account_uuid)
);
CREATE TABLE t_todo(
   todo_id CHAR(36)
  ,title VARCHAR(256)
  ,finished CHAR (1)
  ,created_at TIMESTAMP
  ,version NUMBER
  ,CONSTRAINT t_todo_pk PRIMARY KEY (todo_id)
);

COMMIT;
