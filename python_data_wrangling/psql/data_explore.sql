-- Question 0: Show table schema 
\d+ retail;

-- Question 1: Show first 10 rows
SELECT * FROM retail limit 10;

-- Question 2: Check # of records
SELECT count(*) FROM retail;

-- Question 3: number of clients (e.g. unique client ID)
SELECT count(distinct(customer_id)) FROM retail;

-- Question 4: invoice date range (e.g. max/min dates)
SELECT max(invoice_date) AS max, min(invoice_date) AS min FROM retail;

-- Question 5: number of SKU/merchants (e.g. unique stock code)
SELECT count(distinct(stock_code)) FROM retail;

-- Question 6: Calculate average invoice amount excluding invoices with a negative amount (e.g. canceled orders have negative amount)
CREATE VIEW invoice_amount AS
SELECT invoice_no, sum(unit_price * quantity) AS amount
FROM retail
GROUP BY invoice_no
HAVING sum(unit_price * quantity) > 0;

SELECT avg(amount) FROM invoice_amount;

-- Question 7: Calculate total revenue (e.g. sum of unit_price * quantity)
SELECT sum(unit_price * quantity)
FROM retail;

-- Question 8: Calculate total revenue by YYYYMM 
CREATE VIEW monthly_rev AS
SELECT 
	cast(extract(year FROM invoice_date) AS integer) yy, 
	cast(extract(month FROM invoice_date) AS integer) mm, quantity * unit_price AS amount
FROM retail;

SELECT yy * 100 + mm AS yymm, sum(amount)
FROM monthly_rev
GROUP BY yy, mm
ORDER BY yymm ASC;