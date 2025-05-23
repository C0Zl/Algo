SELECT
    c.CAR_ID,
    c.CAR_TYPE,
    FLOOR(c.DAILY_FEE * 30 * (1 - dp.DISCOUNT_RATE / 100)) AS FEE
FROM
    CAR_RENTAL_COMPANY_CAR c
JOIN
    CAR_RENTAL_COMPANY_DISCOUNT_PLAN dp
    ON c.CAR_TYPE = dp.CAR_TYPE
    AND dp.DURATION_TYPE = '30일 이상'
WHERE
    c.CAR_TYPE IN ('세단', 'SUV')
    AND NOT EXISTS (
        SELECT 1
        FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY h
        WHERE h.CAR_ID = c.CAR_ID
          AND h.START_DATE <= DATE '2022-11-30'
          AND h.END_DATE >= DATE '2022-11-01'
    )
    AND FLOOR(c.DAILY_FEE * 30 * (1 - dp.DISCOUNT_RATE / 100)) BETWEEN 500000 AND 1999999
ORDER BY
    FEE DESC,
    c.CAR_TYPE ASC,
    c.CAR_ID DESC;
