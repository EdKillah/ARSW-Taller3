
# Introducción a redes, clientes y servicios con Java

El siguiente repositorio contiene varios ejercicios en los cuales se vera una introducción a temas relacionados con manejo de URL's, Sockets por parte de un servidor y un cliente.

## Instrucciones de uso

Para poder usar el proyecto lo primero que debe realizar es clonar este repositorio utilizando el siguiente comando desde la terminal del S.O que este utilizando:

```
git clone https://github.com/EdKillah/ARSW-Taller3
```
Otra alternativa es descargarlo directamente por formato ZIP en el botón que indica **"Clone or Download".**
Luego debe redirigirse por medio de la terminal al directorio raíz la cual contiene el achivo pom.xml.

Una vez se encuentre en este directorio se debe compilar el programa para esto se debe ejecutar el siguiente comando:

```
mvn package
```

Una vez compliado el repositorio completo se encuentra en posibilidad de probar los siguientes ejercicios:

### EJERCICIO 1

 Escriba un programa en el cual usted cree un objeto URL e imprima en pantalla cada uno de los datos que retornan los siguientes métodos: 
"getProtocol", "getAuthority", "getHost", "getPort", "getPath", "getQuery", "getFile", "getRef"

#### Comando para ejecutar:
```
java -cp target/miprimera-app-1.0-SNAPSHOT.jar edu.escuelaing.arsw.intro.app.tallernetworking.URLManager
```

### EJERCICIO 2

Escriba una aplicación browser que pregunte una dirección URL al usuario y que lea datos de esa dirección y que los almacene en un archivo con el nombre resultado.html. Luego intente ver este archivo en el navegador.

#### Comando para ejecutar:
```
java -cp target/miprimera-app-1.0-SNAPSHOT.jar edu.escuelaing.arsw.intro.app.tallernetworking.BrowserPrototype
```

### EJERCICIO 4.3.1

Escriba un servidor que reciba un número y responda el cuadrado de este número.

Para ejecutar este ejercicio se requiere abrir dos consolas/terminales en las cuales se ejecutará el Socket del servidor  y el Socket del cliente. ***Primero se debe ejecutar el Socket del servidor para que no genere errores.***

#### Comando para ejecutar ServerSocket:
```
java -cp target/miprimera-app-1.0-SNAPSHOT.jar edu.escuelaing.arsw.intro.app.tallernetworking.EchoServer
```

#### Comando para ejecutar ClientSocket:
```
java -cp target/miprimera-app-1.0-SNAPSHOT.jar edu.escuelaing.arsw.intro.app.tallernetworking.EchoClient
```

### EJERCICIO 4.3.2

Escriba un servidor que pueda recibir un número y responda con un operación sobre este número. Este servidor puede recibir un mensaje que empiece por “fun:”, si recibe este mensaje cambia la operación a las especificada. El servidor debe responder las funciones seno, coseno y tangente. Por defecto debe empezar calculando el coseno. Por ejemplo, si el primer número que recibe es 0, debe responder 1, si después recibe π/2 debe responder 0, si luego recibe “fun:sin” debe cambiar la operación actual a seno, es decir a a partir de ese momento debe calcular senos. Si enseguida recibe 0 debe responder 0.

En este ejercicio también se requieren de dos terminales/consolas para ejecutar correctamente el programa y como en el caso anterior se debe ejecutar inicilamente el SocketServer (FunctionServer) seguido del SocketClient.

#### Comando para ejecutar FunctionServer:
```
java -cp target/miprimera-app-1.0-SNAPSHOT.jar edu.escuelaing.arsw.intro.app.tallernetworking.FunctionServer
```
#### Comando para ejecutar EchoClient:
```
java -cp target/miprimera-app-1.0-SNAPSHOT.jar edu.escuelaing.arsw.intro.app.tallernetworking.EchoClient
```

### EJERCICIO 4.5.1

Escriba un servidor web que soporte múltiples solicitudes seguidas (no concurrentes). El servidor debe retornar todos los archivos solicitados, incluyendo páginas html e imágenes.

#### Comando para ejecutar Http:
```
java -cp target/miprimera-app-1.0-SNAPSHOT.jar edu.escuelaing.arsw.intro.app.tallernetworking.Http.HttpServer
```

### Prerequisitos

Para ejecutar con éxito este programa se requiere de los siguientes programas instalados en su máquina:

```
java version: "1.8.0_171"
git version: 2.19.1.windows.1
Apache Maven version: 3.6.3
```

## Diagrama de clases

![](resources/diagrama.PNG)




## Pruebas del programa

Al ejecutar el comando mvn package  se compila el programa y ejecuta las pruebas al mismo tiempo.
Estas pruebas pruebas la funcionalidad correcta del programa, validando que cargué correctamente los archivos y que el conteo de lineas sea el correcto con un archivo base.

A continuación se presenta una imagen detallando el éxito de las mismas:

![](resources/pruebas.PNG)



## Construido con


* [Maven](https://maven.apache.org/) - Dependency Management
* [Java](https://www.java.com/es/download/) - Lenguaje de programación
* [Git](https://github.com/) - Versionamiento



## Authors

* **Eduard Jimenez** - *LOCS/Hora  360/12 = 30 LOCS/Hora* - Fecha: 10/06/2020



## Licencia

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details


### Enlace al sistema de integración continua

_A continuación se presenta el link:_
