DELETE FROM `publish_code`;
ALTER TABLE `publish_code` auto_increment = 0;

-- 重複確認用
INSERT INTO `publish_code` (`kanrensha_code`) VALUES ('1-2345-67-890abc-defghij');
