echo "CURRENT date"
timedatectl
echo "Date change +5 days"
sudo timedatectl set-ntp no
sudo date --set="+5 days"
timedatectl