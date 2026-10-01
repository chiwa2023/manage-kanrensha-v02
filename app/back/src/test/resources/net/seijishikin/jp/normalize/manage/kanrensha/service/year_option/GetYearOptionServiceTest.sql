DELETE FROM `year_option`;
ALTER TABLE `year_option` auto_increment = 0;

INSERT INTO `year_option` (`selected_year`,`is_selected`) VALUES 
    (2020,0)
  , (2022,0)
  , (2024,0)
;
