DELETE FROM `wk_tbl_kanrensha_kigyou_dt_add_min`;
ALTER TABLE `wk_tbl_kanrensha_kigyou_dt_add_min` auto_increment = 0;

DELETE FROM `wk_tbl_kanrensha_kigyou_dt_add_min_result`;
ALTER TABLE `wk_tbl_kanrensha_kigyou_dt_add_min_result` auto_increment = 0;

INSERT INTO `wk_tbl_kanrensha_kigyou_dt_add_min` (`wk_tbl_kanrensha_kigyou_dt_add_min_id`, `wk_tbl_kanrensha_kigyou_dt_add_min_code`, `is_latest`, `is_finish`, `kanrensha_name`, `all_address`, `kigyou_dt_delegate`, `houjin_no`, `is_affected`, `judge_reason`, `insert_user_id`, `insert_user_code`, `insert_user_name`, `insert_timestamp`) VALUES
(1, 101, 1, 0, '団体名1', '住所1', '代表者1', '1234567890123', 0, '判定理由1', 198, 190, 'ユーザーA', '2023-01-01 10:00:00'),
(2, 102, 1, 0, '団体名2', '住所2', '代表者2', '1234567890124', 0, '判定理由2', 198, 190, 'ユーザーA', '2023-01-01 10:00:00'),
(3, 103, 1, 0, '団体名3', '住所3', '代表者3', '1234567890125', 0, '判定理由3', 198, 190, 'ユーザーA', '2023-01-01 10:00:00'),
(4, 104, 1, 0, '団体名4', '住所4', '代表者4', '1234567890126', 0, '判定理由4', 198, 190, 'ユーザーA', '2023-01-01 10:00:00'),
(5, 105, 1, 0, '団体名5', '住所5', '代表者5', '1234567890127', 0, '判定理由5', 199, 191, 'ユーザーB', '2023-01-01 10:00:00');

DELETE FROM `kanrensha_kigyou_dt_master`;
ALTER TABLE `kanrensha_kigyou_dt_master` auto_increment = 0;

DELETE FROM `kanrensha_kigyou_dt_history_01`;
DELETE FROM `kanrensha_kigyou_dt_history_02`;
DELETE FROM `kanrensha_kigyou_dt_history_03`;
DELETE FROM `kanrensha_kigyou_dt_history_04`;
DELETE FROM `kanrensha_kigyou_dt_history_05`;
DELETE FROM `kanrensha_kigyou_dt_history_06`;
DELETE FROM `kanrensha_kigyou_dt_history_07`;
DELETE FROM `kanrensha_kigyou_dt_history_08`;
DELETE FROM `kanrensha_kigyou_dt_history_09`;
DELETE FROM `kanrensha_kigyou_dt_history_10`;
DELETE FROM `kanrensha_kigyou_dt_history_11`;
DELETE FROM `kanrensha_kigyou_dt_history_12`;
DELETE FROM `kanrensha_kigyou_dt_history_13`;
DELETE FROM `kanrensha_kigyou_dt_history_14`;
DELETE FROM `kanrensha_kigyou_dt_history_15`;
DELETE FROM `kanrensha_kigyou_dt_history_16`;
DELETE FROM `kanrensha_kigyou_dt_history_17`;
DELETE FROM `kanrensha_kigyou_dt_history_18`;
DELETE FROM `kanrensha_kigyou_dt_history_19`;
DELETE FROM `kanrensha_kigyou_dt_history_20`;
DELETE FROM `kanrensha_kigyou_dt_history_21`;
DELETE FROM `kanrensha_kigyou_dt_history_22`;
DELETE FROM `kanrensha_kigyou_dt_history_23`;
DELETE FROM `kanrensha_kigyou_dt_history_24`;
DELETE FROM `kanrensha_kigyou_dt_history_25`;
DELETE FROM `kanrensha_kigyou_dt_history_26`;
DELETE FROM `kanrensha_kigyou_dt_history_27`;
DELETE FROM `kanrensha_kigyou_dt_history_28`;
DELETE FROM `kanrensha_kigyou_dt_history_29`;
DELETE FROM `kanrensha_kigyou_dt_history_30`;
DELETE FROM `kanrensha_kigyou_dt_history_31`;
DELETE FROM `kanrensha_kigyou_dt_history_32`;
DELETE FROM `kanrensha_kigyou_dt_history_33`;
DELETE FROM `kanrensha_kigyou_dt_history_34`;
DELETE FROM `kanrensha_kigyou_dt_history_35`;
DELETE FROM `kanrensha_kigyou_dt_history_36`;
DELETE FROM `kanrensha_kigyou_dt_history_37`;
DELETE FROM `kanrensha_kigyou_dt_history_38`;
DELETE FROM `kanrensha_kigyou_dt_history_39`;
DELETE FROM `kanrensha_kigyou_dt_history_40`;
DELETE FROM `kanrensha_kigyou_dt_history_41`;
DELETE FROM `kanrensha_kigyou_dt_history_42`;
DELETE FROM `kanrensha_kigyou_dt_history_43`;
DELETE FROM `kanrensha_kigyou_dt_history_44`;
DELETE FROM `kanrensha_kigyou_dt_history_45`;
DELETE FROM `kanrensha_kigyou_dt_history_46`;
DELETE FROM `kanrensha_kigyou_dt_history_47`;
DELETE FROM `kanrensha_kigyou_dt_history_99`;

ALTER TABLE `kanrensha_kigyou_dt_history_01` auto_increment = 0;
ALTER TABLE `kanrensha_kigyou_dt_history_02` auto_increment = 0;
ALTER TABLE `kanrensha_kigyou_dt_history_03` auto_increment = 0;
ALTER TABLE `kanrensha_kigyou_dt_history_04` auto_increment = 0;
ALTER TABLE `kanrensha_kigyou_dt_history_05` auto_increment = 0;
ALTER TABLE `kanrensha_kigyou_dt_history_06` auto_increment = 0;
ALTER TABLE `kanrensha_kigyou_dt_history_07` auto_increment = 0;
ALTER TABLE `kanrensha_kigyou_dt_history_08` auto_increment = 0;
ALTER TABLE `kanrensha_kigyou_dt_history_09` auto_increment = 0;
ALTER TABLE `kanrensha_kigyou_dt_history_10` auto_increment = 0;
ALTER TABLE `kanrensha_kigyou_dt_history_11` auto_increment = 0;
ALTER TABLE `kanrensha_kigyou_dt_history_12` auto_increment = 0;
ALTER TABLE `kanrensha_kigyou_dt_history_13` auto_increment = 0;
ALTER TABLE `kanrensha_kigyou_dt_history_14` auto_increment = 0;
ALTER TABLE `kanrensha_kigyou_dt_history_15` auto_increment = 0;
ALTER TABLE `kanrensha_kigyou_dt_history_16` auto_increment = 0;
ALTER TABLE `kanrensha_kigyou_dt_history_17` auto_increment = 0;
ALTER TABLE `kanrensha_kigyou_dt_history_18` auto_increment = 0;
ALTER TABLE `kanrensha_kigyou_dt_history_19` auto_increment = 0;
ALTER TABLE `kanrensha_kigyou_dt_history_20` auto_increment = 0;
ALTER TABLE `kanrensha_kigyou_dt_history_21` auto_increment = 0;
ALTER TABLE `kanrensha_kigyou_dt_history_22` auto_increment = 0;
ALTER TABLE `kanrensha_kigyou_dt_history_23` auto_increment = 0;
ALTER TABLE `kanrensha_kigyou_dt_history_24` auto_increment = 0;
ALTER TABLE `kanrensha_kigyou_dt_history_25` auto_increment = 0;
ALTER TABLE `kanrensha_kigyou_dt_history_26` auto_increment = 0;
ALTER TABLE `kanrensha_kigyou_dt_history_27` auto_increment = 0;
ALTER TABLE `kanrensha_kigyou_dt_history_28` auto_increment = 0;
ALTER TABLE `kanrensha_kigyou_dt_history_29` auto_increment = 0;
ALTER TABLE `kanrensha_kigyou_dt_history_30` auto_increment = 0;
ALTER TABLE `kanrensha_kigyou_dt_history_31` auto_increment = 0;
ALTER TABLE `kanrensha_kigyou_dt_history_32` auto_increment = 0;
ALTER TABLE `kanrensha_kigyou_dt_history_33` auto_increment = 0;
ALTER TABLE `kanrensha_kigyou_dt_history_34` auto_increment = 0;
ALTER TABLE `kanrensha_kigyou_dt_history_35` auto_increment = 0;
ALTER TABLE `kanrensha_kigyou_dt_history_36` auto_increment = 0;
ALTER TABLE `kanrensha_kigyou_dt_history_37` auto_increment = 0;
ALTER TABLE `kanrensha_kigyou_dt_history_38` auto_increment = 0;
ALTER TABLE `kanrensha_kigyou_dt_history_39` auto_increment = 0;
ALTER TABLE `kanrensha_kigyou_dt_history_40` auto_increment = 0;
ALTER TABLE `kanrensha_kigyou_dt_history_41` auto_increment = 0;
ALTER TABLE `kanrensha_kigyou_dt_history_42` auto_increment = 0;
ALTER TABLE `kanrensha_kigyou_dt_history_43` auto_increment = 0;
ALTER TABLE `kanrensha_kigyou_dt_history_44` auto_increment = 0;
ALTER TABLE `kanrensha_kigyou_dt_history_45` auto_increment = 0;
ALTER TABLE `kanrensha_kigyou_dt_history_46` auto_increment = 0;
ALTER TABLE `kanrensha_kigyou_dt_history_47` auto_increment = 0;
ALTER TABLE `kanrensha_kigyou_dt_history_99` auto_increment = 0;

