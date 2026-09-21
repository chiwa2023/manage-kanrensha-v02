DELETE FROM `task_plan_2025`;
ALTER TABLE `task_plan_2025` auto_increment = 0;

DELETE FROM `save_file_storage_2025`;
ALTER TABLE `save_file_storage_2025` auto_increment = 0;

DELETE FROM `task_info`;
ALTER TABLE `task_info` auto_increment = 0;

INSERT INTO `task_info` (`task_info_id`,`task_info_code`,`task_info_name`,`is_latest`,`role_list`,`message_start`,`transfer_pass`,`param_query`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES (423,390,'関連者コード移動申請',1,'manager,partner_api','【transferPass】で作業を行ってください','/accept-combine-riyousha','',198,190,'ユーザ','2022-12-05 12:34:56',0,0,'ユーザ','1948-07-28 00:00:00');

