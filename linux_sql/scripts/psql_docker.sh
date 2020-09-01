#! /bin/bash
# this script will create, start, or stop a postgresql container for hardware usage monitoring uses

# setting up arguments
action=$1
username=$2
password=$3

# check if the docker is started already, and start it if not
sudo systemctl status docker > /dev/null || systemctl start docker
echo "Docker is ready to use."

case $action in
'start')
  # if the container does not exist, print error message and exit with code 1
  if [[ ! $(docker container ls -a -f name=jrvs-psql | grep jrvs-psql) ]]; then
      echo "error: psql container does not exist, please create a container first."
      exit 1
  else
    # start the container
    docker container start jrvs-psql > /dev/null
    echo "container \"jrvs-psql\" started successfully."
    exit 0
  fi
  ;;

'stop')
  # if the container does not exist, print error message and exit with code 1
  if [[ ! $(docker container ls -a -f name=jrvs-psql | grep jrvs-psql) ]]; then
      echo "error: psql container does not exist, please create a container first."
      exit 1
  else
    # stop the container
    docker container stop jrvs-psql > /dev/null
    echo "container \"jrvs-psql\" stopped successfully."
    exit 0
  fi
  ;;

'create')
  # if the container already exist, print error message and exit with code 1
  if [[ $(docker container ls -a -f name=jrvs-psql | grep jrvs-psql) ]]; then
      echo "error: psql container already exists, please input start or stop."
      exit 1
  fi

  # check if number of arguments is provided correctly
  if [ "$#" -ne 3 ]; then
    echo "error: illegal number of parameters, please input action, username, and password"
    exit
  fi

  # create the container with the provided user name and password
  echo "creating a new psql container..."
  docker volume create pgdata
  docker run --name jrvs-psql -e POSTGRES_USER="$2" -e POSTGRES_PASSWORD="$3"  -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres
  echo "container created successfully."
  exit 0
  ;;

*)
  # invalid input argument, print error message and exit with code 1
  echo "error: invalid action, please input create, start, or stop."
  exit 1
  ;;
esac
