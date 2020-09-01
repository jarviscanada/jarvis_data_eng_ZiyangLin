#! /bin/bash

# setting up arguments
host=$1
port=$2
username=$3
password=$4
db_name=$5

# check if correct number of arguments is provided
if [ "$#" -ne 5 ]; then
    echo "error: illegal number of parameters, please input psql host, port, username, password, and database name"
    exit 1
fi

# save hostname to a variable
hostname=$(hostname -f)

# extract specification and usage data from commands and store as variables
usage_out=$(vmstat -t --unit M)
disk_out=$(vmstat -d)
disk_space_out=$(df -BM /)

# collecting hardware usage data
memory_free=$(echo "$usage_out" | egrep '[0-9]' | awk '{print $4}'| xargs)
cpu_idle=$(echo "$usage_out" | egrep '[0-9]' | awk '{print $15}'| xargs)
cpu_kernel=$(echo "$usage_out" | egrep '[0-9]' | awk '{print $16}'| xargs)
disk_io=$(echo "$disk_out" | grep 'sda' | awk '{print $10}'| xargs)
disk_available=$(echo "$disk_space_out" | grep '/dev/sda2' | awk '{print $4}' | egrep -o '[0-9]+'| xargs)
timestamp=$(echo "$usage_out" | egrep -o '[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}'| xargs)

# constructing SQL insert statement