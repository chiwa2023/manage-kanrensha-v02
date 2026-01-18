DELETE FROM `user_role`;
ALTER TABLE `user_role` auto_increment = 0;

DELETE FROM `user_person`;
ALTER TABLE `user_person` auto_increment = 0;


INSERT INTO `user_person` (`user_person_id`,`user_person_code`,`user_person_name`,`is_latest`,`email`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
  VALUES 
  (80,70,'aaa',0,'aaa@politician.balanse.report.net',1,1,'aaa','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59') -- 最新でない
  ,(81,71,'bbb',1,'bbb@politician.balanse.report.net',1,1,'bbb','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ,(82,72,'ddd-bbb',1,'ddd-bbb@politician.balanse.report.net',1,1,'bbb','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ,(83,73,'ddd',1,'ddd@politician.balanse.report.net',1,1,'bbb','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59') -- 名前が合致しない
  ;

INSERT INTO `user_role` (`user_role_id`,`email`,`is_latest`,`role`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES 
  (1,'aaa@politician.balanse.report.net',1,'manager',1,1,'aaa','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ,(2,'bbb@politician.balanse.report.net',1,'manager',1,1,'bbb','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ,(3,'ccc@politician.balanse.report.net',0,'manager',1,1,'bbb','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59') -- 最新でない
  ,(4,'ddd-bbb@politician.balanse.report.net',1,'kanrensha_person',1,1,'bbb','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ,(5,'eee@politician.balanse.report.net',1,'partner_api',1,1,'bbb','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59') -- roleが抽出対象でない
  ,(6,'ddd@politician.balanse.report.net',1,'kanrensha_person',1,1,'bbb','2026-01-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  
  
  
  ;

  