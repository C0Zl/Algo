SELECT
    car_id,
    CASE 
        WHEN SUM(CASE 
                    WHEN DATE '2022-10-16' BETWEEN START_DATE AND END_DATE 
                    THEN 1 ELSE 0 
                 END) >= 1
        THEN '대여중'
        ELSE '대여 가능'
    END AS AVAILABILITY
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
group by car_id
ORDER BY car_id desc;