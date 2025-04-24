-- 코드를 입력하세요
SELECT p.product_code as PRODUCT_CODE, p.price * sum(o.sales_amount) as SALES
from product p
    left join offline_sale o on p.product_id = o.product_id
group by p.product_id
ORDER BY SALES DESC, PRODUCT_CODE ASC;