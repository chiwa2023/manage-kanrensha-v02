DELETE FROM `wk_tbl_kanrensha_kigyou_dt_add_min_result`;
ALTER TABLE `wk_tbl_kanrensha_kigyou_dt_add_min_result` auto_increment = 0;

INSERT INTO `wk_tbl_kanrensha_kigyou_dt_add_min_result` (`wk_tbl_kanrensha_kigyou_dt_add_min_result_id`, `wk_tbl_kanrensha_kigyou_dt_add_min_id`, `is_latest`, `insert_user_id`, `insert_user_code`, `insert_user_name`, `insert_timestamp`) VALUES
(1, 1, 1, 198, 190, 'ユーザーA', '2023-01-01 11:00:00'),
(2, 2, 1, 198, 190, 'ユーザーA', '2023-01-01 11:00:00'),
(3, 3, 1, 198, 190, 'ユーザーA', '2023-01-01 11:00:00'),
(4, 4, 0, 198, 190, 'ユーザーA', '2023-01-01 11:00:00'),
(5, 5, 1, 199, 191, 'ユーザーB', '2023-01-01 11:00:00');
