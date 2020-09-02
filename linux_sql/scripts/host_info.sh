#! /bin/bash

# setting up arguments
host=$1
port=$2
db_name=$3
username=$4
export PGPASSWORD=$5

# check if correct number of arguments is provided
if [ "$#" -ne 5 ]; then
    echo "error: illegal number of parameters, please input psql host, port, username, password, and database name"
    exit 1
fi

# save hostname to a variable
hostname=$(hostname -f)

# extract specification and usage data from commands and store as variables
lscpu_out=`lscpu`
mem_out=$(cat /proc/meminfo)
usage_out=$(vmstat -t --unit M)

# collecting hardware specification data
cpu_number=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
cpu_architecture=$(echo "$lscpu_out"  | egrep "^Architecture:" | awk '{print $2}' | xargs)
cpu_model=$(echo "$lscpu_out"  | egrep "^Model name:" | awk '{print $3,$4,$5,$6,$7}' | xargs)
cpu_mhz=$(echo "$lscpu_out"  | egrep "^CPU MHz:" | awk '{print $3}' | xargs)
l2_cache=$(echo "$lscpu_out"  | egrep "^L2 cache:" | awk '{print $3}' | egrep -o "[0-9]+" | xargs)
total_mem=$(echo "$mem_out"  | egrep "^MemTotal:" | awk '{print $2}' | xargs)
record_time=$(echo "$usage_out" | egrep -o '[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}'| xargs)

# constructing SQL insert statement
sql_statement=$(cat <<-END
INSERT INTO host_info (hostname, cpu_number, cpu_arch, cpu_model, cpu_mhz, l2_cache, total_mem, record_time) VALUES('${hostname}', ${cpu_number}, '${cpu_architecture}', '${cpu_model}', ${cpu_mhz}, ${l2_cache}, ${total_mem}, '${record_time}');
END
)

# insert data using constructed statement into the psql database
psql -h "$host" -p "$port" -U "$username" -d "$db_name" -c "$sql_statement"
exit 0