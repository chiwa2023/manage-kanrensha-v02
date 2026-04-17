DELETE FROM `login_status`;
ALTER TABLE `login_status` auto_increment = 0;

INSERT INTO `login_status` (`email`,`password`,`is_success`,`fail_reason`,`disabled`,`disabled_reason`,`login_time`,`pass_change_time`) VALUES 
('aaa@politician.balanse.report.net','$2a$10$VAAGICAVk.O.5ct7Vm/nauN0jDqo6aanBcJsXoW6Zeug6i3APbvhu',1,'',0,'','2026-01-03 16:10:25','2026-01-03 16:10:25')
;
