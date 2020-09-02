\c host_agent

CREATE TABLE IF NOT EXISTS public.host_info (

    "id" SERIAL PRIMARY KEY NOT NULL,
    "hostname" VARCHAR UNIQUE NOT NULL,
    "cpu_number" INT NOT NULL,
    "cpu_arch" VARCHAR NOT NULL,
    "cpu_model" VARCHAR NOT NULL,
    "cpu_mhz" INT NOT NULL,
    "l2_cache" INT NOT NULL,
    "total_mem" INT NOT NULL,
    "record_time" TIMESTAMP NOT NULL

);

CREATE TABLE IF NOT EXISTS public.host_usage (

    "host_id" INT NOT NULL,
    "memory_free" INT NOT NULL,
    "cpu_idle" INT NOT NULL,
    "cpu_kernel" INT NOT NULL,
    "disk_io" INT NOT NULL,
    "disk_available" INT NOT NULL,
    "record_time" TIMESTAMP NOT NULL,
    FOREIGN KEY ("host_id") REFERENCES host_info

);