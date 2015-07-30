sudo service tomcat7 stop

mvn clean package

sudo rm -rf /var/lib/tomcat7/webapps/CJAuth
sudo rm -rf /var/lib/tomcat7/webapps/CJAuth.war

sudo cp target/CJAuth.war /var/lib/tomcat7/webapps/

sudo service tomcat7 start
