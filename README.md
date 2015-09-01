# middl2015
Repo tareas Middleware

Modulo ws-facturas: Servidor WS para una de las empresas.
Modulo ws-entradas: Servidor con autenticacion.

En la configuracion del tomcat, agregar los dos artefactos war. En cada uno configurar el context con el nombre del war.

TOMCAT 8: http://tomcat.apache.org/download-80.cgi
JAX-WS libs en Tomacat/lib: https://jax-ws.java.net/2.2.10/

# NOTAS:

## SOBRE WS:

sun-jaxws.xml is a proprietary deployment descriptor needed when web services are deployed as a standard WAR archive on a non-Java EE5 servlet container using the SUN's reference implementation.
