SELECT 
    TO_CHAR(SALES_DATE, 'YYYY-MM-DD'),
    PRODUCT_ID,
    USER_ID,
    SALES_AMOUNT
FROM (
    -- 오프라인 판매 (USER_ID는 NULL)
    SELECT 
        SALES_DATE,
        PRODUCT_ID,
        NULL AS USER_ID,
        SALES_AMOUNT
    FROM OFFLINE_SALE
    WHERE TO_CHAR(SALES_DATE, 'YYYY-MM') = '2022-03'

    UNION ALL

    -- 온라인 판매
    SELECT 
        SALES_DATE,
        PRODUCT_ID,
        USER_ID,
        SALES_AMOUNT
    FROM ONLINE_SALE
    WHERE TO_CHAR(SALES_DATE, 'YYYY-MM') = '2022-03'
)
ORDER BY 
    SALES_DATE ASC,
    PRODUCT_ID ASC,
    USER_ID ASC;
