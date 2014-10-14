DROP TABLE IF EXISTS t_image;
DROP TABLE IF EXISTS t_text;
DROP TABLE IF EXISTS t_account;
DROP TABLE IF EXISTS t_todo;
DROP TABLE IF EXISTS t_sales;
CREATE TABLE t_image (
    id         CHAR(36)
  , image_data OID       NOT NULL
--    ,image_data BYTEA NOT NULL
  , created_at TIMESTAMP NOT NULL
  , CONSTRAINT t_image_pk PRIMARY KEY (id)
);
CREATE TABLE t_text (
    id         CHAR(36)
  , text_data  OID       NOT NULL
  , created_at TIMESTAMP NOT NULL
  , CONSTRAINT t_text_pk PRIMARY KEY (id)
);
CREATE TABLE t_account (
    account_uuid CHAR(36)
  , account_name VARCHAR(256)
  , birth_date   TIMESTAMP
  , CONSTRAINT t_account_pk PRIMARY KEY (account_uuid)
);
CREATE TABLE t_todo (
    todo_id    CHAR(36)
  , title      VARCHAR(256)
  , finished   BOOLEAN
  , created_at TIMESTAMP
  , version    BIGINT
  , CONSTRAINT t_todo_pk PRIMARY KEY (todo_id)
);

CREATE TABLE t_sales (
  item_no  INT4 PRIMARY KEY,
  quantity INT4 NOT NULL,
  price    INT4 NOT NULL
);

COMMIT;
