-- 企業団体マスタ
DELETE FROM `kanrensha_kigyou_dt_master`;
ALTER TABLE `kanrensha_kigyou_dt_master` auto_increment = 0;

INSERT INTO `kanrensha_kigyou_dt_master` (`kanrensha_kigyou_dt_master_id`,`kigyou_dt_kanrensha_code`,`houjin_no`,`is_latest`,`kanrensha_name`,`all_address`,`kigyou_dt_delegate`,`compare_name_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES 
(421,'430','1234567890',1,'ふんだくり企業','和歌山県架空市山麓町','代表者　太郎','ふんだくり企業',216,190,'管理者太郎','2022-12-05 12:34:56',0,0,'ユーザ','1948-07-28 23:59:59');

-- 企業団体履歴
DELETE FROM `kanrensha_kigyou_dt_history_30`;
ALTER TABLE `kanrensha_kigyou_dt_history_30` auto_increment = 0;
/** 300004,和歌山県 */
INSERT INTO `kanrensha_kigyou_dt_history_30` (`kanrensha_kigyou_dt_history_id`,`kigyou_dt_kanrensha_code`,`is_latest`,`all_name`,`all_address`,`org_delegate_name`,`org_delegate_code`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES
(102,'1-2345-67-890123-4567890',1,'ぼったくり企業','和歌山県架空市山麓町','代表者　太郎','1-2345-67-890123-9999999',200,190,'ユーザ','2022-12-05 12:34:56',1,1,'1','1948-07-28 00:00:00');
