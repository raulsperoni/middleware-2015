# Instrucciones Ejecucion

###Para generar los war de cada módulo ejecutar el comando: 
mvn clean package

###Se debe iniciar ActiveMQ mediante el comando: 
./activemq start

###Deploy
Luego hacer el deploy de los war en Tomcat.

###Para verificar el correcto funcionamiento del flujo se pueden ejecutar los tests
mvn test





# middl2015
Repo tareas Middleware

Modulo ws-facturas: Servidor WS para una de las empresas.

http://localhost:8080/ws-1/hello

Modulo ws-entradas: Servidor con autenticacion.

http://localhost:8080/ws-2/hello

Modulo central: Spring Integration + jms

Modulo lealtad: jms


# NOTAS:

## SOBRE TOMCAT:

En la configuracion del tomcat, agregar los dos artefactos war. En cada uno configurar el context con el nombre del war.

central -> /

lealtad -> /lealtad

ws-entradas -> /ws-1

ws-facturas -> /ws-2

TOMCAT 8: http://tomcat.apache.org/download-80.cgi

## SOBRE WS:

JAX-WS libs en Tomacat/lib: https://jax-ws.java.net/2.2.10/

sun-jaxws.xml is a proprietary deployment descriptor needed when web services are deployed as a standard WAR archive on a non-Java EE5 servlet container using the SUN's reference implementation.

## SOBRE ACTIVEMQ

Usamos http://activemq.apache.org/activemq-5120-release.html

hay que hacer en bin ./activemq start

la consola se ve en: http://localhost:8161/admin/queues.jsp

