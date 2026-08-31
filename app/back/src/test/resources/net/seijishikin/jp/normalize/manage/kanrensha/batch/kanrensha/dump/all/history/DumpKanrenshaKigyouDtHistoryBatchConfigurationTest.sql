-- 企業団体履歴
DELETE FROM `kanrensha_kigyou_dt_history_01`;
ALTER TABLE `kanrensha_kigyou_dt_history_01` auto_increment = 0;

-- 2022
INSERT INTO `kanrensha_kigyou_dt_history_01` (`kanrensha_kigyou_dt_history_id`,`kigyou_dt_kanrensha_code`,`is_latest`,`all_name`,`all_address`,`org_delegate_name`,`org_delegate_code`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES
(414,'1-2345-67-890123-4567890',1,'ぼったくり企業','和歌山県架空市山麓町','代表者　太郎','1-2345-67-890123-0101019',200,190,'ユーザ','2022-12-05 12:34:56',1,1,'1','1948-07-28 00:00:00');

-- 2024～2025
INSERT INTO `kanrensha_kigyou_dt_history_01` (`kanrensha_kigyou_dt_history_id`,`kigyou_dt_kanrensha_code`,`is_latest`,`all_name`,`all_address`,`org_delegate_name`,`org_delegate_code`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES
(415,'1-2345-67-890123-4567890',1,'ぼったくり企業','和歌山県架空市山麓町','代表者　太郎','1-2345-67-890123-0101019',200,190,'ユーザ','2024-09-18 12:34:56',1,1,'1','1948-07-28 00:00:00');

-- 最新でない
INSERT INTO `kanrensha_kigyou_dt_history_01` (`kanrensha_kigyou_dt_history_id`,`kigyou_dt_kanrensha_code`,`is_latest`,`all_name`,`all_address`,`org_delegate_name`,`org_delegate_code`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES
(416,'1-2345-67-890123-4567890',0,'ぼったくり企業','和歌山県架空市山麓町','代表者　太郎','1-2345-67-890123-0101019',200,190,'ユーザ','2024-03-12 12:34:56',1,1,'1','1948-07-28 00:00:00');



DELETE FROM `task_plan_2026`;
ALTER TABLE `task_plan_2026` auto_increment = 0;

INSERT INTO `task_plan_2026` (`task_plan_id`,`task_plan_code`,`task_info_code`,`task_plan_name`,`table_year`,`is_latest`,`is_start`,`is_finished`,`is_suspended`,`start_datetime`,`end_dateimte`,`role_list`,`transfer_pass`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES 
    (453,187,24,'タスク名称1',2026,1,1,0,0,'1990-07-24 23:34:56','1947-07-29 00:00:00','manager','pass',213,190,'ユーザ','2026-4-05 12:34:56',231,190,'ユーザ','1947-07-28 23:59:59')
   ,(459,187,24,'タスク名称1',2025,1,1,0,0,'1990-07-24 23:34:56','1947-07-29 00:00:00','manager','pass',213,190,'ユーザ','2026-4-05 12:34:56',231,190,'ユーザ','1947-07-28 23:59:59')
