
INSERt INTO t_account (account_uuid,account_name,birth_date) VALUES ('4d3c8bdd-5379-4aeb-bc56-fcb01eb7cc01', 'Bob1', '2014-01-01 00:00:00.000');
INSERt INTO t_account (account_uuid,account_name,birth_date) VALUES ('4d3c8bdd-5379-4aeb-bc56-fcb01eb7cc02', 'Bob2', '2014-01-02 00:00:00.000');
INSERt INTO t_account (account_uuid,account_name,birth_date) VALUES ('4d3c8bdd-5379-4aeb-bc56-fcb01eb7cc03', 'Bob3', '2014-01-03 00:00:00.000');
INSERt INTO t_account (account_uuid,account_name,birth_date) VALUES ('4d3c8bdd-5379-4aeb-bc56-fcb01eb7cc04', 'Bob4', '2014-01-04 00:00:00.000');
INSERt INTO t_account (account_uuid,account_name,birth_date) VALUES ('4d3c8bdd-5379-4aeb-bc56-fcb01eb7cc05', 'Bob5', '2014-01-05 00:00:00.000');
INSERt INTO t_account (account_uuid,account_name,birth_date) VALUES ('4d3c8bdd-5379-4aeb-bc56-fcb01eb7cc11', 'Mark1', '2014-02-01 00:00:00.000');

INSERt INTO t_account_address (account_uuid,zip_code,address) VALUES ('4d3c8bdd-5379-4aeb-bc56-fcb01eb7cc02', '1710051', 'Tokyo Toshima-ku Nagasaki');


-- Setup master tables
INSERT INTO m_item VALUES ('ITM0000001','Orange juice',100);
INSERT INTO m_item VALUES ('ITM0000002','NotePC',100000);

INSERT INTO m_category VALUES ('CTG0000001','Drink');
INSERT INTO m_category VALUES ('CTG0000002','PC');
INSERT INTO m_category VALUES ('CTG0000003','Hot selling');

INSERT INTO m_item_category VALUES ('ITM0000001','CTG0000001');
INSERT INTO m_item_category VALUES ('ITM0000002','CTG0000002');
INSERT INTO m_item_category VALUES ('ITM0000002','CTG0000003');

INSERT INTO m_coupon VALUES ('CPN0000001','Join coupon',3000);
INSERT INTO m_coupon VALUES ('CPN0000002','PC coupon',30000);

-- Setup code tables
INSERT  INTO  c_order_status VALUES ('accepted','Order accepted');
INSERT  INTO  c_order_status VALUES ('checking','Stock checking');
INSERT  INTO  c_order_status VALUES ('shipped','Item Shipped');

-- Setup transaction tables
INSERT INTO t_order VALUES (1,'accepted');
INSERT INTO t_order VALUES (2,'checking');

INSERT INTO t_order_item VALUES (1,'ITM0000001',1);
INSERT INTO t_order_item VALUES (1,'ITM0000002',2);
INSERT INTO t_order_item VALUES (2,'ITM0000001',3);
INSERT INTO t_order_item VALUES (2,'ITM0000002',4);

INSERT INTO t_order_coupon VALUES (1,'CPN0000001');
INSERT INTO t_order_coupon VALUES (1,'CPN0000002');

COMMIT;