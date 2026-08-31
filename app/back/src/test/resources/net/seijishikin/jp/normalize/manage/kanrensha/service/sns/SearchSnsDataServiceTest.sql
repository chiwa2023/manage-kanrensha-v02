DELETE FROM `sns_service`;
ALTER TABLE `sns_service` auto_increment = 0;

INSERT INTO `sns_service` (`sns_service_id`,`sns_service_code`,`sns_service_name`,`is_latest`,`sns_portal_url`,`search_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
  VALUES 
     (671,245,'弱小SNS',0,'https://jakushou-sns.com/','弱小sns',196,190,'管理人　太郎','2026-03-20 20:25:18',0,0,'','1948-07-28 23:59:59') -- 最新でない
   , (672,246,'弱小SNS',1,'https://jakushou-sns.com/','弱小sns',196,190,'管理人　太郎','2026-03-20 20:25:18',0,0,'','1948-07-28 23:59:59') -- 抽出
   , (673,246,'弱小SNS',1,'https://jakushou-sns.com/','弱小sns',196,190,'管理人　太郎','2026-03-20 20:25:18',0,0,'','1948-07-28 23:59:59') -- 抽出
   , (674,246,'弱小SNS',1,'https://jakushou-sns.com/','弱小sns',196,190,'管理人　太郎','2026-03-20 20:25:18',0,0,'','1948-07-28 23:59:59') -- 抽出
   , (675,246,'消滅メッセージ',1,'https://jakushou-sns.com/','消滅メッセージ',196,190,'管理人　太郎','2026-03-20 20:25:18',0,0,'','1948-07-28 23:59:59') -- 検索条件に合わない
  ;
