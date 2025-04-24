SELECT
    ai.name AS NAME,
    ai.datetime AS DATETIME
FROM
    animal_ins ai
LEFT OUTER JOIN
    animal_outs ao
ON 
    ai.animal_id = ao.animal_id
WHERE
    ao.animal_id IS NULL
ORDER BY
    ai.datetime ASC
LIMIT 3
    