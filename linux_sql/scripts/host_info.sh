# save hostname to a variable
hostname=$(hostname -f)

# extract specification and usage data from commands and store as variables
lscpu_out=`lscpu`
mem_out=$(cat /proc/meminfo)
usage_out=$(vmstat -t --unit M)
disk_out=$(vmstat -d)
disk_space_out=$(df -BM /)

# collecting hardware specification data
cpu_number=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
cpu_architecture=$(echo "$lscpu_out"  | egrep "^Architecture:" | awk '{print $2}' | xargs)
cpu_model=$(echo "$lscpu_out"  | egrep "^Model name:" | awk '{print $3,$4,$5,$6,$7}' | xargs)
cpu_mhz=$(echo "$lscpu_out"  | egrep "^CPU MHz:" | awk '{print $3}' | xargs)
l2_cache=$(echo "$lscpu_out"  | egrep "^L2 cache:" | awk '{print $3}' | xargs)
total_mem=$(echo "$mem_out"  | egrep "^MemTotal:" | awk '{print $2}' | xargs)
timestamp=$(echo "$usage_out" | egrep -o '[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}'| xargs)

# collecting hardware usage data
memory_free=$(echo "$usage_out" | egrep '[0-9]' | awk '{print $4}'| xargs)
cpu_idle=$(echo "$usage_out" | egrep '[0-9]' | awk '{print $15}'| xargs)
cpu_kernel=$(echo "$usage_out" | egrep '[0-9]' | awk '{print $16}'| xargs)
disk_io=$(echo "$disk_out" | grep 'sda' | awk '{print $10}'| xargs)
disk_available=$(echo "$disk_space_out" | grep '/dev/sda2' | awk '{print $4}' | egrep -o '[0-9]+'| xargs)