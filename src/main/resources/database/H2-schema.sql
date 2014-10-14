DROP TABLE IF EXISTS t_image;
DROP TABLE IF EXISTS t_text;
DROP TABLE IF EXISTS t_account;
DROP TABLE IF EXISTS t_account_address;
DROP TABLE IF EXISTS t_order_item;
DROP TABLE IF EXISTS t_order_coupon;
DROP TABLE IF EXISTS t_order;
DROP TABLE IF EXISTS c_order_status;
DROP TABLE IF EXISTS m_item_category;
DROP TABLE IF EXISTS m_item;
DROP TABLE IF EXISTS m_category;
DROP TABLE IF EXISTS m_coupon;
DROP TABLE IF EXISTS t_todo;
DROP TABLE IF EXISTS m_stock;

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
CREATE TABLE t_account_address(
   account_uuid CHAR(36)
  ,zip_code CHAR(7)
  ,address  NVARCHAR2(256)
  ,CONSTRAINT t_account_address_pk PRIMARY KEY (account_uuid)
);


CREATE TABLE m_item (
    code CHAR(10),
    name NVARCHAR(256),
    price INTEGER,
    CONSTRAINT m_item_pk PRIMARY KEY(code)
);
CREATE TABLE m_category (
    code CHAR(10),
    name NVARCHAR(256),
    CONSTRAINT m_category_pk PRIMARY KEY(code)
);
CREATE TABLE m_item_category (
    item_code CHAR(10),
    category_code CHAR(10),
    CONSTRAINT m_item_category_pk
        PRIMARY KEY(item_code, category_code),
    CONSTRAINT m_item_category_fk_item_code
        FOREIGN KEY(item_code) REFERENCES m_item(code),
    CONSTRAINT m_item_category_fk_category_code
        FOREIGN KEY(category_code) REFERENCES m_category(code)
);
CREATE TABLE m_coupon (
    code CHAR(10),
    name NVARCHAR(256),
    price INTEGER,
    CONSTRAINT m_coupon_pk PRIMARY KEY(code)
);
CREATE TABLE c_order_status (
    code VARCHAR(10),
    name NVARCHAR(256),
    CONSTRAINT c_order_status_pk PRIMARY KEY(code)
);


CREATE TABLE t_order (
    id INTEGER,
    status_code VARCHAR(10),
    CONSTRAINT t_order_pk PRIMARY KEY(id),
    CONSTRAINT t_order_fk_status_code
        FOREIGN KEY(status_code) REFERENCES c_order_status(code)
);
CREATE TABLE t_order_item (
    order_id INTEGER,
    item_code CHAR(10),
    quantity INTEGER,
    CONSTRAINT t_order_item_pk PRIMARY KEY(order_id, item_code),
    CONSTRAINT t_order_item_fk_order_id
        FOREIGN KEY(order_id) REFERENCES t_order(id),
    CONSTRAINT t_order_item_fk_item_code
        FOREIGN KEY(item_code) REFERENCES m_item(code),
);
CREATE TABLE t_order_coupon (
    order_id INTEGER,
    coupon_code CHAR(10),
    CONSTRAINT t_order_coupon_pk PRIMARY KEY(order_id, coupon_code),
    CONSTRAINT t_order_coupon_fk_order_id
        FOREIGN KEY(order_id) REFERENCES t_order(id),
    CONSTRAINT t_order_coupon_fk_item_code
        FOREIGN KEY(coupon_code) REFERENCES m_coupon(code),
);

CREATE TABLE t_todo(
   todo_id CHAR(36)
  ,title NVARCHAR(256)
  ,finished BOOLEAN
  ,created_at TIMESTAMP
  ,version BIGINT
  ,CONSTRAINT t_todo_pk PRIMARY KEY (todo_id)
);

CREATE TABLE m_stock (
  item_code CHAR(10),
  quantity INTEGER,
  version BIGINT,
  CONSTRAINT m_stock_pk PRIMARY KEY(item_code)
);


COMMIT;
