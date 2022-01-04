cd c:/
cd Users/ddv
calc
docker ps

docker exec -it efb04cfe5c93 date

docker exec -i -t efb04cfe5c93 sh
date

sudo date --set 2022-01-10

sudo hwclock -r
sudo hwclock -w

timedatectl
sudo timedatectl set-ntp no
sudo timedatectl set-ntp yes
sudo date --set='+5 days'

sudo docker top 5ca4c6a8bb0c
sudo docker inspect 5ca4c6a8bb0c



echo "DOCKER INFO"
echo "Images"
sudo docker images
echo "Running Containers"
sudo docker ps
echo "Run RabbitMQ"
sudo docker run 6c3c2a225947
echo "Running Containers"
sudo docker ps
echo "CURRENT date"
timedatectl
echo "Date change +5 days"
sudo timedatectl set-ntp no
sudo date --set="+5 days"
timedatectl
echo "Setting the CURRENT date"
sudo timedatectl set-ntp yes
timedatectl