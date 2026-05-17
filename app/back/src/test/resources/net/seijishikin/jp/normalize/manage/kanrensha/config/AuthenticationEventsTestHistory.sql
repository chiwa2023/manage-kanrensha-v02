-- 実施時期ごとに対象となるテーブルが異なる  
DELETE FROM `login_history_2026`;
ALTER TABLE `login_history_2026` auto_increment = 0;
  