mvn clean install
sudo cp target/dylancomet.war /var/lib/tomcat7/webapps/dylancomet.war
sudo chmod 777 /var/lib/tomcat7/webapps/dylancomet.war
sudo rm -r /var/lib/tomcat7/webapps/dylancomet
sudo /etc/rc.d/tomcat7 restart
