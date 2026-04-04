DELETE FROM `kanrensha_kigyou_dt_history_01`;
ALTER TABLE `kanrensha_kigyou_dt_history_01` auto_increment = 0;

DELETE FROM `kanrensha_person_history_01`;
ALTER TABLE `kanrensha_person_history_01` auto_increment = 0;

DELETE FROM `kanrensha_seijidantai_history_01`;
ALTER TABLE `kanrensha_seijidantai_history_01` auto_increment = 0;

DELETE FROM `kanrensha_kigyou_dt_master`;
ALTER TABLE `kanrensha_kigyou_dt_master` auto_increment = 0;

DELETE FROM `kanrensha_person_master`;
ALTER TABLE `kanrensha_person_master` auto_increment = 0;

DELETE FROM `kanrensha_seijidantai_master`;
ALTER TABLE `kanrensha_seijidantai_master` auto_increment = 0;



DELETE FROM `wk_tbl_kanrensha_seijidantai_add_min`;
ALTER TABLE `wk_tbl_kanrensha_seijidantai_add_min` auto_increment = 0;
INSERT INTO `wk_tbl_kanrensha_seijidantai_add_min` (`wk_tbl_kanrensha_seijidantai_add_min_id`,`wk_tbl_kanrensha_seijidantai_add_min_code`,`is_latest`,`is_finish`,`kanrensha_name`,`all_address`,`seijidantai_delegate`,`dantai_kbn`,`is_affected`,`judge_reason`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (298,290,1,1,'a','b','c','w',1,'理由',219,190,'ユーザ','2022-12-05 12:34:56',1,1,'ユーザ','1948-07-29 00:00:00');
INSERT INTO `wk_tbl_kanrensha_seijidantai_add_min` (`wk_tbl_kanrensha_seijidantai_add_min_id`,`wk_tbl_kanrensha_seijidantai_add_min_code`,`is_latest`,`is_finish`,`kanrensha_name`,`all_address`,`seijidantai_delegate`,`dantai_kbn`,`is_affected`,`judge_reason`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (299,290,1,1,'a','b','c','w',1,'理由',219,190,'ユーザ','2022-12-05 12:34:56',1,1,'ユーザ','1948-07-29 00:00:00');
INSERT INTO `wk_tbl_kanrensha_seijidantai_add_min` (`wk_tbl_kanrensha_seijidantai_add_min_id`,`wk_tbl_kanrensha_seijidantai_add_min_code`,`is_latest`,`is_finish`,`kanrensha_name`,`all_address`,`seijidantai_delegate`,`dantai_kbn`,`is_affected`,`judge_reason`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (300,290,1,1,'a','b','c','w',1,'理由',219,190,'ユーザ','2022-12-05 12:34:56',1,1,'ユーザ','1948-07-29 00:00:00');
INSERT INTO `wk_tbl_kanrensha_seijidantai_add_min` (`wk_tbl_kanrensha_seijidantai_add_min_id`,`wk_tbl_kanrensha_seijidantai_add_min_code`,`is_latest`,`is_finish`,`kanrensha_name`,`all_address`,`seijidantai_delegate`,`dantai_kbn`,`is_affected`,`judge_reason`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (301,290,1,0,'a','b','c','w',0,'理由',252,233,'ユーザ','2022-12-05 12:34:56',1,1,'ユーザ','1948-07-29 00:00:00');
INSERT INTO `wk_tbl_kanrensha_seijidantai_add_min` (`wk_tbl_kanrensha_seijidantai_add_min_id`,`wk_tbl_kanrensha_seijidantai_add_min_code`,`is_latest`,`is_finish`,`kanrensha_name`,`all_address`,`seijidantai_delegate`,`dantai_kbn`,`is_affected`,`judge_reason`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (302,290,0,1,'a','b','c','w',1,'理由',219,190,'ユーザ','2022-12-05 12:34:56',1,1,'ユーザ','1948-07-29 00:00:00');
INSERT INTO `wk_tbl_kanrensha_seijidantai_add_min` (`wk_tbl_kanrensha_seijidantai_add_min_id`,`wk_tbl_kanrensha_seijidantai_add_min_code`,`is_latest`,`is_finish`,`kanrensha_name`,`all_address`,`seijidantai_delegate`,`dantai_kbn`,`is_affected`,`judge_reason`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (303,290,1,1,'a','b','c','w',0,'理由',219,190,'ユーザ','2022-12-05 12:34:56',1,1,'ユーザ','1948-07-29 00:00:00');


DELETE FROM `wk_tbl_kanrensha_person_add_min`;
ALTER TABLE `wk_tbl_kanrensha_person_add_min` auto_increment = 0;
INSERT INTO `wk_tbl_kanrensha_person_add_min` (`wk_tbl_kanrensha_person_add_min_id`,`wk_tbl_kanrensha_person_add_min_code`,`is_latest`,`is_finish`,`kanrensha_name`,`all_address`,`person_shokugyou`,`is_affected`,`judge_reason`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (218,210,1,0,'a','b','w',1,'理由',210,190,'ユーザ','2022-12-05 12:34:56',1,1,'ユーザ','1948-07-29 00:00:00');
INSERT INTO `wk_tbl_kanrensha_person_add_min` (`wk_tbl_kanrensha_person_add_min_id`,`wk_tbl_kanrensha_person_add_min_code`,`is_latest`,`is_finish`,`kanrensha_name`,`all_address`,`person_shokugyou`,`is_affected`,`judge_reason`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (219,210,1,0,'a','b','x',1,'理由',210,190,'ユーザ','2022-12-05 12:34:56',1,1,'ユーザ','1948-07-29 00:00:00');
INSERT INTO `wk_tbl_kanrensha_person_add_min` (`wk_tbl_kanrensha_person_add_min_id`,`wk_tbl_kanrensha_person_add_min_code`,`is_latest`,`is_finish`,`kanrensha_name`,`all_address`,`person_shokugyou`,`is_affected`,`judge_reason`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (220,210,1,0,'a','b','y',1,'理由',210,190,'ユーザ','2022-12-05 12:34:56',1,1,'ユーザ','1948-07-29 00:00:00');
INSERT INTO `wk_tbl_kanrensha_person_add_min` (`wk_tbl_kanrensha_person_add_min_id`,`wk_tbl_kanrensha_person_add_min_code`,`is_latest`,`is_finish`,`kanrensha_name`,`all_address`,`person_shokugyou`,`is_affected`,`judge_reason`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (221,210,1,1,'a','b','z',0,'理由',252,224,'ユーザ','2022-12-05 12:34:56',1,1,'ユーザ','1948-07-29 00:00:00');


DELETE FROM `wk_tbl_kanrensha_kigyou_dt_add_min`;
ALTER TABLE `wk_tbl_kanrensha_kigyou_dt_add_min` auto_increment = 0;
INSERT INTO `wk_tbl_kanrensha_kigyou_dt_add_min` (`wk_tbl_kanrensha_kigyou_dt_add_min_id`,`wk_tbl_kanrensha_kigyou_dt_add_min_code`,`is_latest`,`is_finish`,`kanrensha_name`,`all_address`,`kigyou_dt_delegate`,`houjin_no`,`is_affected`,`judge_reason`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (102,102,1,0,'a','b','c','d',1,'理由',215,190,'ユーザ','2022-12-05 12:34:56',1,1,'ユーザ','1948-07-29 00:00:00');
INSERT INTO `wk_tbl_kanrensha_kigyou_dt_add_min` (`wk_tbl_kanrensha_kigyou_dt_add_min_id`,`wk_tbl_kanrensha_kigyou_dt_add_min_code`,`is_latest`,`is_finish`,`kanrensha_name`,`all_address`,`kigyou_dt_delegate`,`houjin_no`,`is_affected`,`judge_reason`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (103,102,1,0,'a','b','c','e',1,'理由',215,190,'ユーザ','2022-12-05 12:34:56',1,1,'ユーザ','1948-07-29 00:00:00');
INSERT INTO `wk_tbl_kanrensha_kigyou_dt_add_min` (`wk_tbl_kanrensha_kigyou_dt_add_min_id`,`wk_tbl_kanrensha_kigyou_dt_add_min_code`,`is_latest`,`is_finish`,`kanrensha_name`,`all_address`,`kigyou_dt_delegate`,`houjin_no`,`is_affected`,`judge_reason`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (104,102,1,0,'a','b','c','f',1,'理由',215,190,'ユーザ','2022-12-05 12:34:56',1,1,'ユーザ','1948-07-29 00:00:00');
INSERT INTO `wk_tbl_kanrensha_kigyou_dt_add_min` (`wk_tbl_kanrensha_kigyou_dt_add_min_id`,`wk_tbl_kanrensha_kigyou_dt_add_min_code`,`is_latest`,`is_finish`,`kanrensha_name`,`all_address`,`kigyou_dt_delegate`,`houjin_no`,`is_affected`,`judge_reason`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (105,102,1,1,'a','b','c','g',1,'理由',303,293,'ユーザ','2022-12-05 12:34:56',1,1,'ユーザ','1948-07-29 00:00:00');
INSERT INTO `wk_tbl_kanrensha_kigyou_dt_add_min` (`wk_tbl_kanrensha_kigyou_dt_add_min_id`,`wk_tbl_kanrensha_kigyou_dt_add_min_code`,`is_latest`,`is_finish`,`kanrensha_name`,`all_address`,`kigyou_dt_delegate`,`houjin_no`,`is_affected`,`judge_reason`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (106,102,0,1,'a','b','c','h',1,'理由',215,190,'ユーザ','2022-12-05 12:34:56',1,1,'ユーザ','1948-07-29 00:00:00');
INSERT INTO `wk_tbl_kanrensha_kigyou_dt_add_min` (`wk_tbl_kanrensha_kigyou_dt_add_min_id`,`wk_tbl_kanrensha_kigyou_dt_add_min_code`,`is_latest`,`is_finish`,`kanrensha_name`,`all_address`,`kigyou_dt_delegate`,`houjin_no`,`is_affected`,`judge_reason`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (107,102,0,0,'a','b','c','h',0,'理由',215,190,'ユーザ','2022-12-05 12:34:56',1,1,'ユーザ','1948-07-29 00:00:00');

