DELETE FROM `task_info`;
ALTER TABLE `task_info` auto_increment = 0;

INSERT INTO `task_info` (`task_info_id`,`task_info_code`,`task_info_name`,`is_latest`,`role_list`,`message_start`,`transfer_pass`,`param_query`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES 
   (423,311,'タスク名称1',1,'admin,manager','メッセージ','pageUrl','',198,190,'ユーザ','2022-12-05 12:34:56',0,0,'ユーザ','1948-07-28 00:00:00')
  ,(424,312,'タスク名称2',1,'admin,manager','メッセージ','pageUrl','',198,190,'ユーザ','2022-12-05 12:34:56',0,0,'ユーザ','1948-07-28 00:00:00')
	-- 最新でない
  ,(425,312,'タスク名称2',0,'admin,manager','メッセージ','pageUrl','',198,190,'ユーザ','2022-12-05 12:34:56',0,0,'ユーザ','1948-07-28 00:00:00')
	-- 名称が合致しない
  ,(426,312,'処理内容',1,'admin,manager','メッセージ','pageUrl','',198,190,'ユーザ','2022-12-05 12:34:56',0,0,'ユーザ','1948-07-28 00:00:00')
	-- コードのタイプが異なる
  ,(427,901,'タスク名称3',1,'admin,manager','メッセージ','pageUrl','',198,190,'ユーザ','2022-12-05 12:34:56',0,0,'ユーザ','1948-07-28 00:00:00')
  ;
