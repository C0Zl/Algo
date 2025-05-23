SELECT
    P.PRODUCT_ID,
    PRODUCT_NAME,
    PRICE * SUM(AMOUNT) AS TOTAL_SALES
FROM
    FOOD_PRODUCT P
JOIN
    FOOD_ORDER O
ON
    P.PRODUCT_ID = O.PRODUCT_ID
WHERE
    PRODUCE_DATE >= TO_DATE('2022-05-01', 'YYYY-MM-DD')
    AND PRODUCE_DATE < TO_DATE('2022-06-01', 'YYYY-MM-DD')
GROUP BY
    P.PRODUCT_ID, PRODUCT_NAME, PRICE
ORDER BY
    TOTAL_SALES DESC, PRODUCT_ID ASC