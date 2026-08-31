DELETE FROM `wk_tbl_kanrensha_seijidantai_add_min`;
ALTER TABLE `wk_tbl_kanrensha_seijidantai_add_min` auto_increment = 0;

DELETE FROM `wk_tbl_kanrensha_seijidantai_add_min_result`;
ALTER TABLE `wk_tbl_kanrensha_seijidantai_add_min_result` auto_increment = 0;

INSERT INTO `wk_tbl_kanrensha_seijidantai_add_min` (`wk_tbl_kanrensha_seijidantai_add_min_id`, `wk_tbl_kanrensha_seijidantai_add_min_code`, `is_latest`, `is_finish`, `kanrensha_name`, `all_address`, `seijidantai_delegate`, `dantai_kbn`, `poli_org_no`, `is_affected`, `judge_reason`, `insert_user_id`, `insert_user_code`, `insert_user_name`, `insert_timestamp`) VALUES
(1, 301, 1, 0, '政治団体名1', '住所1', '代表者1', 'AA', '11111', 0, '判定理由1', 198, 190, 'ユーザーA', '2023-01-01 10:00:00'),
(2, 302, 1, 0, '政治団体名2', '住所2', '代表者2', 'BB', '22222', 0, '判定理由2', 198, 190, 'ユーザーA', '2023-01-01 10:00:00'),
(3, 303, 1, 0, '政治団体名3', '住所3', '代表者3', 'CC', '33333', 0, '判定理由3', 198, 190, 'ユーザーA', '2023-01-01 10:00:00'),
(4, 304, 1, 0, '政治団体名4', '住所4', '代表者4', 'DD', '44444', 0, '判定理由4', 198, 190, 'ユーザーA', '2023-01-01 10:00:00'),
(5, 305, 1, 0, '政治団体名5', '住所5', '代表者5', 'EE', '55555', 0, '判定理由5', 199, 191, 'ユーザーB', '2023-01-01 10:00:00');

INSERT INTO `wk_tbl_kanrensha_seijidantai_add_min_result` (`wk_tbl_kanrensha_seijidantai_add_min_result_id`, `wk_tbl_kanrensha_seijidantai_add_min_id`, `is_latest`, `insert_user_id`, `insert_user_code`, `insert_user_name`, `insert_timestamp`) VALUES
(1, 1, 1, 198, 190, 'ユーザーA', '2023-01-01 11:00:00'),
(2, 2, 1, 198, 190, 'ユーザーA', '2023-01-01 11:00:00'),
(3, 3, 1, 198, 190, 'ユーザーA', '2023-01-01 11:00:00'),
(4, 4, 1, 198, 190, 'ユーザーA', '2023-01-01 11:00:00'),
(5, 5, 1, 199, 191, 'ユーザーB', '2023-01-01 11:00:00');