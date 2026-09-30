CREATE TABLE `year_option` (
  `selected_year` int NOT NULL COMMENT '選択年',
  `is_selected` tinyint DEFAULT NULL COMMENT '選択該非',
  PRIMARY KEY (`selected_year`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
