-- 코드를 입력하세요
SELECT
    ao.animal_id AS ANIMAL_ID,
    ao.name AS NAME
FROM
    animal_outs ao
LEFT OUTER JOIN
    animal_ins ai
ON
    ao.animal_id = ai.animal_id
WHERE
    ai.animal_id IS NULL
ORDER BY
    ANIMAL_ID