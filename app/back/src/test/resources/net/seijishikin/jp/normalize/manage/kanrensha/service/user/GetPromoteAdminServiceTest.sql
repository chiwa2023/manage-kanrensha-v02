DELETE FROM `promote_admin`;
ALTER TABLE `promote_admin` auto_increment = 0;

INSERT INTO `promote_admin` (`promote_admin_id`,`promote_admin_code`,`is_latest`,`promote_user_id`,`promote_user_code`,`promote_user_name`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES 
   (137,129,1,319,310,'def',1,1,'aaa','2026-01-24 20:20:40',0,0,'','1948-07-28 22:59:59') -- 他人
  ,(138,130,0,196,190,'aaa',1,1,'aaa','2026-01-24 20:20:40',0,0,'','1948-07-28 22:59:59') -- 承諾判定済
  ,(148,140,1,196,190,'aaa',1,1,'aaa','2026-01-21 20:20:40',0,0,'','1948-07-28 22:59:59') --最新でない
  ,(158,150,1,196,190,'aaa',1,1,'aaa','2026-01-22 20:20:40',0,0,'','1948-07-28 22:59:59')
;
