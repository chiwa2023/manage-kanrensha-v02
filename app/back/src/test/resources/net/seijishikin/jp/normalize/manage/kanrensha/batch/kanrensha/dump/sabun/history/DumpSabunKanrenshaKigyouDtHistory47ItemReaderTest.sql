-- 企業団体履歴
DELETE FROM `kanrensha_kigyou_dt_history_47`;
ALTER TABLE `kanrensha_kigyou_dt_history_47` auto_increment = 0;

-- 2022
INSERT INTO `kanrensha_kigyou_dt_history_47` (`kanrensha_kigyou_dt_history_id`,`kigyou_dt_kanrensha_code`,`is_latest`,`all_name`,`all_address`,`org_delegate_name`,`org_delegate_code`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES
(414,'1-2345-67-890123-4567890',1,'ぼったくり企業','和歌山県架空市山麓町','代表者　太郎','1-2345-67-890123-4747479',200,190,'ユーザ','2022-12-05 12:34:56',1,1,'1','1948-07-28 00:00:00');

-- 2024～2025
INSERT INTO `kanrensha_kigyou_dt_history_47` (`kanrensha_kigyou_dt_history_id`,`kigyou_dt_kanrensha_code`,`is_latest`,`all_name`,`all_address`,`org_delegate_name`,`org_delegate_code`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES
(415,'1-2345-67-890123-4567890',1,'ぼったくり企業','和歌山県架空市山麓町','代表者　太郎','1-2345-67-890123-4747479',200,190,'ユーザ','2024-09-18 12:34:56',1,1,'1','1948-07-28 00:00:00');

-- 最新でない
INSERT INTO `kanrensha_kigyou_dt_history_47` (`kanrensha_kigyou_dt_history_id`,`kigyou_dt_kanrensha_code`,`is_latest`,`all_name`,`all_address`,`org_delegate_name`,`org_delegate_code`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES
(416,'1-2345-67-890123-4567890',0,'ぼったくり企業','和歌山県架空市山麓町','代表者　太郎','1-2345-67-890123-4747479',200,190,'ユーザ','2024-03-12 12:34:56',1,1,'1','1948-07-28 00:00:00');
