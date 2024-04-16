SELECT b.book_id, a.author_name, DATE_FORMAT(b.PUBLISHED_DATE, '%Y-%m-%d') published_date
FROM book b
    LEFT JOIN author a
    ON b.author_id = a.author_id
WHERE b.category = '경제'
ORDER BY b.published_date ASC;