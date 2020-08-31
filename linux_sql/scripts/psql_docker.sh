#! /bin/bash
# this script will create, start, or stop a postgresql container for hardware usage monitoring uses

# setting up arguments
action=$1
username=$2
password=$3

# check if number of arguments is provided correctly
if [ "$#" -ne 3 ]; then
  echo "error: illegal number of parameters"
  exit
fi

# check if the docker is started already, and start it if not
sudo systemctl status docker || systemctl start docker

case $action in
'start')
  # if the container does not exist, print error message and exit with code 1

  # start the container
  ;;

'stop')
  # if the container does not exist, print error message and exit with code 1

  # stop the container
  ;;

'create')
  # if the container already exist, print error message and exit with code 1

  # create the container with the provided user name and password
  ;;
*)
  # invalid input argument, print error message and exit with code 1

  ;;
esac
