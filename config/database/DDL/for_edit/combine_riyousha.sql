CREATE OR REPLACE VIEW view_combine_alive_riyousha (
    email ,
    riyousha_id ,
    riyousha_code,
    role_base,
    role_has,
    all_name,
    search_text 
) AS (
WITH 
    t1  AS (
    SELECT * FROM user_role
         WHERE is_latest = 1 AND role IN ( 'manager' , 'partner_api', 'admin' ) AND riyousha_code <> 0
    )
  , riyousha_alive  AS (
    SELECT * FROM view_riyousha_alive where riyousha_code <> 0
    )
SELECT 
    t1.email AS email,
    riyousha_alive.riyousha_id AS riyousha_id ,
    t1.riyousha_code AS riyousha_code,
    t1.role AS role_base,
    t2.role AS role2_has,
    riyousha_alive.all_name AS all_name,
    riyousha_alive.search_text AS search_text 
    FROM user_role t2
    LEFT JOIN t1 ON t2.email = t1.email
    LEFT JOIN riyousha_alive ON riyousha_alive.user_role = t1.role 
                             AND riyousha_alive.riyousha_code = t1.riyousha_code 
                             AND riyousha_alive.email = t1.email 
    WHERE t2.is_latest = 1 AND t2.role IN ( 'manager' , 'partner_api', 'admin' ) AND t1.email is not null
)
