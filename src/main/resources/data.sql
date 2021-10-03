DELETE from TBL_USER;
DELETE from TBL_USER_TYPE;

INSERT INTO TBL_USER_TYPE(user_type_id, user_type) values
('UTID_1', 'ADMIN'),
('UTID_2', 'USER');

INSERT INTO TBL_USER(user_id, user_name, password, active, user_type_id, updated_by, updated_time) VALUES
('UID_1', 'admin', 'YWRtaW4=', 'Y', 'UTID_1', 'UID_1', null),
('UID_2', 'user', 'dXNlcg==', 'Y', 'UTID_2', 'UID_1', null);
