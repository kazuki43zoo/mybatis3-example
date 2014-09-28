INSERt INTO t_account (account_uuid,account_name,birth_date) VALUES ('4d3c8bdd-5379-4aeb-bc56-fcb01eb7cc01', 'Bob1', '2014-01-01 00:00:00.000');
INSERt INTO t_account (account_uuid,account_name,birth_date) VALUES ('4d3c8bdd-5379-4aeb-bc56-fcb01eb7cc02', 'Bob2', '2014-01-02 00:00:00.000');
INSERt INTO t_account (account_uuid,account_name,birth_date) VALUES ('4d3c8bdd-5379-4aeb-bc56-fcb01eb7cc03', 'Bob3', '2014-01-03 00:00:00.000');
INSERt INTO t_account (account_uuid,account_name,birth_date) VALUES ('4d3c8bdd-5379-4aeb-bc56-fcb01eb7cc04', 'Bob4', '2014-01-04 00:00:00.000');
INSERt INTO t_account (account_uuid,account_name,birth_date) VALUES ('4d3c8bdd-5379-4aeb-bc56-fcb01eb7cc05', 'Bob5', '2014-01-05 00:00:00.000');
INSERt INTO t_account (account_uuid,account_name,birth_date) VALUES ('4d3c8bdd-5379-4aeb-bc56-fcb01eb7cc11', 'Mark1', '2014-02-01 00:00:00.000');

INSERt INTO t_account_address (account_uuid,zip_code,address) VALUES ('4d3c8bdd-5379-4aeb-bc56-fcb01eb7cc02', '1710051', '東京都豊島区長崎');

COMMIT;
