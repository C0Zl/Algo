SELECT FLAVOR
FROM (
    SELECT
        F.FLAVOR,
        F.TOTAL_ORDER + NVL(SUM(J.TOTAL_ORDER), 0) AS TOTAL
    FROM
        FIRST_HALF F
    LEFT JOIN
        JULY J
    ON F.FLAVOR = J.FLAVOR
    GROUP BY
        F.FLAVOR, F.TOTAL_ORDER
    ORDER BY
        TOTAL DESC
)
WHERE ROWNUM <= 3;
