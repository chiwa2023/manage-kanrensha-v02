DELETE FROM `wk_tbl_kanrensha_kigyou_dt_add_min`;
ALTER TABLE `wk_tbl_kanrensha_kigyou_dt_add_min` auto_increment = 0;

INSERT INTO `wk_tbl_kanrensha_kigyou_dt_add_min` (`wk_tbl_kanrensha_kigyou_dt_add_min_id`, `wk_tbl_kanrensha_kigyou_dt_add_min_code`, `is_latest`, `is_finish`, `kanrensha_name`, `all_address`, `kigyou_dt_delegate`, `houjin_no`, `is_affected`, `judge_reason`, `insert_user_id`, `insert_user_code`, `insert_user_name`, `insert_timestamp`) VALUES
(1, 101, 1, 0, '団体名1', '住所1', '代表者1', '1234567890123', 1, '判定理由1', 198, 190, 'ユーザーA', '2023-01-01 10:00:00'),
(2, 102, 1, 0, '団体名2', '住所2', '代表者2', '1234567890124', 1, '判定理由2', 198, 190, 'ユーザーA', '2023-01-01 10:00:00'),
(3, 103, 1, 0, '団体名3', '住所3', '代表者3', '1234567890125', 0, '判定理由3', 198, 190, 'ユーザーA', '2023-01-01 10:00:00'),
(4, 104, 0, 0, '団体名4', '住所4', '代表者4', '1234567890126', 1, '判定理由4', 198, 190, 'ユーザーA', '2023-01-01 10:00:00'),
(5, 105, 1, 0, '団体名5', '住所5', '代表者5', '1234567890127', 1, '判定理由5', 199, 191, 'ユーザーB', '2023-01-01 10:00:00');
