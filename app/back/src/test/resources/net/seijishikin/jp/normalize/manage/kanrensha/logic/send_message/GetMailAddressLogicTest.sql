DELETE FROM `user_person`;
ALTER TABLE `user_person` auto_increment = 0;

INSERT INTO `user_person` (`user_person_id`,`user_person_code`,`user_person_name`,`is_latest`,`email`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
  VALUES 
    (196,190,'aaa',1,'aaa@politician.balanse.report.net',1,1,'aaa','2026-03-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  -- 最新ではない
  , (197,190,'aaa',0,'bbb@politician.balanse.report.net',1,1,'aaa','2026-04-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  -- 最新だが更新時間が後(PG実装でで複数最新は避ける)
  , (198,190,'aaa',1,'ccc@politician.balanse.report.net',1,1,'aaa','2026-02-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  -- ユーザコードが異なる
  , (199,196,'aaa',0,'ddd@politician.balanse.report.net',1,1,'aaa','2026-07-05 20:15:48',0,0,'','1948-07-28 22:59:59')
  ;
