DELETE FROM `login_status`;

DELETE FROM `user_role`;
ALTER TABLE `user_role` auto_increment = 0;

DELETE FROM `user_person`;
ALTER TABLE `user_person` auto_increment = 0;
