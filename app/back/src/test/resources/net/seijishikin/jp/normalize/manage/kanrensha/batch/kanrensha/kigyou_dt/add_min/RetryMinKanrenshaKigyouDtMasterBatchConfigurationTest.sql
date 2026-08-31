DELETE FROM `wk_tbl_kanrensha_kigyou_dt_add_min`;
ALTER TABLE `wk_tbl_kanrensha_kigyou_dt_add_min` auto_increment = 0;

INSERT INTO `wk_tbl_kanrensha_kigyou_dt_add_min` (`wk_tbl_kanrensha_kigyou_dt_add_min_id`, `wk_tbl_kanrensha_kigyou_dt_add_min_code`, `is_latest`, `is_finish`, `kanrensha_name`, `all_address`, `kigyou_dt_delegate`, `houjin_no`, `is_affected`, `judge_reason`, `insert_user_id`, `insert_user_code`, `insert_user_name`, `insert_timestamp`) VALUES
(1, 101, 1, 0, '団体名1', '住所1', '代表者1', '1234567890123', 1, '判定理由1', 198, 190, 'ユーザーA', '2023-01-01 10:00:00'),
(2, 102, 1, 0, '団体名2', '住所2', '代表者2', '1234567890124', 1, '判定理由2', 198, 190, 'ユーザーA', '2023-01-01 10:00:00'),
(3, 103, 1, 0, '団体名3', '住所3', '代表者3', '1234567890125', 0, '判定理由3', 198, 190, 'ユーザーA', '2023-01-01 10:00:00'),
(4, 104, 0, 0, '団体名4', '住所4', '代表者4', '1234567890126', 1, '判定理由4', 198, 190, 'ユーザーA', '2023-01-01 10:00:00'),
(5, 105, 1, 0, '団体名5', '住所5', '代表者5', '1234567890127', 1, '判定理由5', 199, 191, 'ユーザーB', '2023-01-01 10:00:00');


DELETE FROM `task_plan_2026`;
ALTER TABLE `task_plan_2026` auto_increment = 0;

INSERT INTO `task_plan_2026` (`task_plan_id`,`task_plan_code`,`task_info_code`,`task_plan_name`,`table_year`,`is_latest`,`is_start`,`is_finished`,`is_suspended`,`start_datetime`,`end_dateimte`,`role_list`,`transfer_pass`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES 
    (453,187,24,'タスク名称1',2026,1,1,0,0,'1990-07-24 23:34:56','1947-07-29 00:00:00','manager','pass',213,190,'ユーザ','2026-4-05 12:34:56',231,190,'ユーザ','1947-07-28 23:59:59')
   ,(459,187,24,'タスク名称1',2025,1,1,0,0,'1990-07-24 23:34:56','1947-07-29 00:00:00','manager','pass',213,190,'ユーザ','2026-4-05 12:34:56',231,190,'ユーザ','1947-07-28 23:59:59')
