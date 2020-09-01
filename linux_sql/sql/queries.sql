/* 1st query: group hosts by CPU number and sort by memory size in descending order */
SELECT cpu_number, id AS host_id, total_mem
FROM host_info
GROUP BY cpu_number
ORDER BY total_mem DESC;

/* helper function: generate 5-minute intervals from timestamp */
CREATE FUNCTION round5(ts timestamp) RETURNS timestamp AS
$$
BEGIN
    RETURN date_trunc('hour', ts) + date_part('minute', ts):: int / 5 * interval '5 min';
END;
$$
LANGUAGE PLPGSQL;

/* 2nd query: average used memory in percentage over 5 mins interval for each host */
SELECT
    use.host_id AS "host_id",
    use.hostname AS "host_name",
    round5(use.record_time) AS "interval",
    AVG(1 - info.memory_free / use.total_mem) * 100::INT AS "percentage"
FROM host_info info
    INNER JOIN host_usage use ON info.id = use.host_id
GROUP BY use.host_id, round5(use.record_time)
ORDER BY use.host_id, round5(use.record_time)

/* 3rd query: group hosts by CPU number and sort by memory size in descending order */
SELECT host_id, round5(record_time), count(*) as records_per_interval
FROM host_usage
GROUP BY host_id, round5(record_time)
HAVING count(*) < 3

