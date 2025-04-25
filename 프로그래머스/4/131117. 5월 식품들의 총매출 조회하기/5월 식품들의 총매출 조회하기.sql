SELECT
    p.product_id AS PRODUCT_ID,
    p.product_name AS PRODUCT_NAME,
    p.price * SUM(o.amount) AS TOTAL_SALES
FROM
    food_product p
LEFT JOIN
    food_order o
ON
    p.product_id = o.product_id
WHERE
    DATE(o.produce_date) BETWEEN '2022-05-01' AND '2022-05-31'
GROUP BY
    p.product_id
ORDER BY
    TOTAL_SALES DESC, PRODUCT_ID ASC