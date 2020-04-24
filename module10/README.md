1. Install Tomcat 8.
2. Execute 'mvn clean install' for module10 
3. The resulting war file must be added to the 'apache-tomcat-8.\*.*/webapps'
4. Set up the port for deployment in 'apache-tomcat-8.\*.*/conf/server.xml' in the line '< Connector port="CHOSEN_PORT" protocol="AJP/1.3" redirectPort="8443" /> '
4. Run 'Tomcat' using command ./bin/catalina.sh run
5. Application will start.
6. Go to http://localhost:CHOSEN_PORT