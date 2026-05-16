CREATE OR  REPLACE VIEW `view_riyousha_alive`(
        `email`,
        `user_role`,
        `riyousha_id`,
        `riyousha_code`,
        `all_name`,
        `search_text`
) AS (
WITH 
  `role_partner` as(
SELECT 
    `user_role`.`email` AS `email`,
    `user_role`.`role` AS `role`,
    `user_role`.`riyousha_code` AS `riyousha_code`
FROM
    `user_role`
WHERE
    `user_role`.`is_latest` = 1
        AND `user_role`.`riyousha_code` <> 0
        AND `user_role`.`role` = 'partner_api'
)                
, `role_manager` as(
        SELECT 
    `user_role`.`email` AS `email`,
    `user_role`.`role` AS `role`,
    `user_role`.`riyousha_code` AS `riyousha_code`
FROM
    `user_role`
WHERE
    `user_role`.`is_latest` = 1
        AND `user_role`.`riyousha_code` <> 0
        AND `user_role`.`role` = 'manager'
    )
SELECT 
    `role_partner`.`email` AS `email`,
    `role_partner`.`role` AS `user_role`,
    `partner`.`riyousha_partner_api_master_id` AS `ruiyousha_id`,
    `role_partner`.`riyousha_code` AS `riyousha_code`,
    `partner`.`all_name` AS `all_name`,
    `partner`.`search_text` AS `search_text`
FROM
    `riyousha_partner_api_master` `partner`
        INNER JOIN
    `role_partner` ON `role_partner`.`riyousha_code` = `partner`.`riyousha_partner_api_master_code`
WHERE
    is_latest = 1
union
SELECT 
    `role_manager`.`email` AS `email`,
    `role_manager`.`role` AS `user_role`,
    `manager`.`riyousha_manager_master_id` AS `ruiyousha_id`,
    `role_manager`.`riyousha_code` AS `riyousha_code`,
    `manager`.`all_name` AS `all_name`,
    `manager`.`search_text` AS `search_text`
FROM
    `riyousha_manager_master` `manager`
        INNER JOIN
    `role_manager` ON `role_manager`.`riyousha_code` = `manager`.`riyousha_manager_master_code`
WHERE
    is_latest = 1
)    
