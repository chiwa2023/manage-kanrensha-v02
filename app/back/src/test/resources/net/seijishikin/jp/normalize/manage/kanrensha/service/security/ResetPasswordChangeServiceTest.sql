DELETE FROM `login_status`;
DELETE FROM `user_password_reset`;

INSERT INTO `user_password_reset` (`email`,`regist_code`,`limit_datetime`)    VALUES 
    ('ddd@seijishikin.net','12345','2099-12-05 00:00:00')
   ,('eee@seijishikin.net','23456','2099-12-05 00:00:00')
;

   
INSERT INTO `login_status` (`email`,`password`,`is_success`,`fail_reason`,`disabled`,`disabled_reason`,`login_time`,`pass_change_time`)
  VALUES 
   ('aaa@politician.balanse.report.net','$2a$10$KLZCF1ao2ZOZ1NRTDhfYSuwcY0jHFgUoIOJE9S44z/mxA0WeR3yg6',1,'',0,'','2026-01-05 20:15:48','2026-01-05 20:15:48')
  ,('eee@seijishikin.net','$2a$10$KLZCF1ao2ZOZ1NRTDhfYSuwcY0jHFgUoIOJE9S44z/mxA0WeR3yg6',1,'',0,'','2026-01-05 20:15:48','2026-01-05 20:15:48')
  ;
   