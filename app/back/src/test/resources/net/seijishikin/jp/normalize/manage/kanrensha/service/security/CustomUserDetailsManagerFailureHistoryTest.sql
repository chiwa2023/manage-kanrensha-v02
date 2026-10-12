
DELETE FROM `login_history_2026`;
ALTER TABLE `login_history_2026` auto_increment = 0;

INSERT INTO `login_history_2026` (`login_history_id`,`email`,`ip_address`,`user_agent`,`attempt_time`,`is_success`) 
  VALUES 
  -- 抽出
     (241,'aaa@politician.balanse.report.net','Unknown','','2026-10-12 11:41:05',0)
   , (242,'aaa@politician.balanse.report.net','Unknown','','2026-10-12 11:41:05',0)
   , (243,'aaa@politician.balanse.report.net','Unknown','','2026-10-12 11:41:05',0)
   , (244,'aaa@politician.balanse.report.net','Unknown','','2026-10-12 11:41:05',0)
   , (245,'aaa@politician.balanse.report.net','Unknown','','2026-10-12 11:41:05',0)
   , (246,'aaa@politician.balanse.report.net','Unknown','','2026-10-12 11:41:05',0)
   -- メールアドレスが異なる
   , (247,'wrong@user.com','Unknown','','2026-10-12 11:41:05',0)
   -- ログイン失敗でない
   , (248,'aaa@politician.balanse.report.net','Unknown','','2026-10-12 11:41:05',1)
   -- 指定期間外
   , (249,'aaa@politician.balanse.report.net','Unknown','','2014-03-06 11:41:05',0)
       ;
       