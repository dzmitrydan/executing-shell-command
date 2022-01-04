#operations with docker and container
echo "DOCKER INFO"
echo "Images"
sudo docker images
echo "Running Containers"
sudo docker ps
echo "Running Containers"
sudo docker ps
#if container running
#change date
echo "CURRENT date"
timedatectl
echo "Date change +5 days"
sudo timedatectl set-ntp no
sudo date --set="+5 days"
timedatectl
echo "Setting the CURRENT date"
sudo timedatectl set-ntp yes
timedatectl