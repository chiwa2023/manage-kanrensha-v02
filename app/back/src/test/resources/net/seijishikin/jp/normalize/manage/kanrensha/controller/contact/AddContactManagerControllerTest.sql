
DELETE FROM `contact_manager`;
ALTER TABLE `contact_manager` auto_increment = 0;

INSERT INTO `contact_manager` (`contact_manager_id`,`contact_manager_code`,`is_latest`,`first_timestamp`,`is_closed`,`close_timestamp`,`inquire_user_id`,`inquire_user_code`,`inquire_user_name`,`inquire_title`,`inquire_content`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
   VALUES 
       --最新でない
      (324,26,0,'2025-12-05 12:34:56',0,'1948-07-28 23:59:59',196,190,'管理人　太郎','問い合わせタイトル','問い合わせ内容',196,190,'管理人　太郎','2026-09-23 16:12:34',0,0,'','1948-07-28 23:59:59')
       --最新履歴
    , (325,26,1,'2025-12-05 12:34:56',0,'1948-07-28 23:59:59',196,190,'管理人　太郎','問い合わせタイトルc','問い合わせ内容',196,190,'管理人　太郎','2026-09-23 16:12:34',0,0,'','1948-07-28 23:59:59')
       -- 該当履歴でない
    , (326,29,1,'2025-12-05 12:34:56',0,'1948-07-28 23:59:59',197,191,'aaa','問い合わせタイトルa','問い合わせ内容a',196,190,'管理人　太郎','2026-09-23 16:12:34',0,0,'','1948-07-28 23:59:59')
      ;
      
DELETE FROM `task_info`;
ALTER TABLE `task_info` auto_increment = 0;

INSERT INTO `task_info` (`task_info_id`,`task_info_code`,`task_info_name`,`is_latest`,`role_list`,`message_start`,`transfer_pass`,`param_query`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES (423,412,'問い合わせに回答追加がありました',1,'manager,partner_api','問い合わせに回答がありました','/accept-combine-riyousha','',198,190,'ユーザ','2022-12-05 12:34:56',0,0,'ユーザ','1948-07-28 00:00:00');

DELETE FROM `user_person`;
ALTER TABLE `user_person` auto_increment = 0;

INSERT INTO `user_person` (`user_person_id`,`user_person_code`,`user_person_name`,`is_latest`,`email`  ,`is_alert_task_start` ,`is_alert_task_end`      ,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
  VALUES 
    (196,190,'aaa',1,'aaa@politician.balanse.report.net',1,1,1,1,'aaa','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  , (197,190,'aaa',1,'aaa@politician.balanse.report.net',0,0,1,1,'aaa','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ;
