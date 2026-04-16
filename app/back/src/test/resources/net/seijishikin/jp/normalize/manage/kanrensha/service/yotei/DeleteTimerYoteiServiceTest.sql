DELETE FROM `timer_yotei`;
ALTER TABLE `timer_yotei` auto_increment = 0;

INSERT INTO `timer_yotei` (`timer_yotei_id`,`timer_yotei_code`,`timer_yotei_name`,`is_latest`,`yoyaku_task_kbn`,`next_timestamp`,`previous_timestamp`,`is_repeat`,`is_pause`,`end_timestamp`,`sabun_timestamp`,`is_period`,`year_period`,`month_period`,`day_period`,`hour_period`,`year_pointed`,`month_pointed`,`day_pointed`,`hour_pointed`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
  VALUES 
    (123,116,'サンプルタスク',1,243,'2023-06-11 12:34:56','2022-12-05 12:34:56',1,1,'2023-01-01 00:00:00','2022-07-01 00:00:00',1,2022,7,4,9,2026,10,28,1,213,190,'ユーザ','2022-12-05 00:00:00',0,0,NULL,'1947-07-28 23:59:59')
;
