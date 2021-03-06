# Backend

> *** IMPORTANTE TENER LA VERSIÓN 11 DE JAVA***

Para poder ejecutar correctamente el backend del proyecto, debemos tener la versión 11 de java instalada en nuestra máquina. 

Además, podemos ejecutar el proyecto de dos maneras, la primera es desde la linea de comandos, y la otra, usando SpringToolSuite. Explicaremos las dos maneras.

### Clonar el proyecto

Antes de ejecutar el proyecto de alguna manera, debemos clonar el proyecto o descargar como archivo zip desde github, para este ejemplo, optaré por la segunda opción.

[![](https://i.imgur.com/m61n7fk.png)](https://i.imgur.com/m61n7fk.png)

Luego, debemos descomprimirlo en alguna parte, yo lo haré en el escritorio.

[![](https://i.imgur.com/QFKZrC1.png)](https://i.imgur.com/QFKZrC1.png)

## Ejecutar el proyecto desde la terminal

- Sabiendo en donde tenemos ubicado la carpeta del proyecto, debemos movernos hacia la ubicación de la carpeta, y ejecutar el siguiente comando: mvnw spring-boot:run

[![](https://i.imgur.com/3szl8C9.png)](https://i.imgur.com/3szl8C9.png)

- Si todo ha salido bien nos debería salir una respuesta como la siguiente: 

[![](https://i.imgur.com/JrZR9I7.png)](https://i.imgur.com/JrZR9I7.png)

- Esto nos indicará que el proyecto se ha ejecutado correctamente, y es accesible desde el puerto 8080. Para poder ver el proyecto en ejecución, podemos abrir la siguiente ruta en nuestro navegador de preferencia **http://localhost:8080**

# Ejecutar el proyecto con SpringToolSuite4

- Teniendo instalado SprintToolSuite4 debemos ejecutarlo, posteriormente, debemos ir a la pestaña Archivo y dar click en la opción Abrir proyecto desde archivos del sistema

[![](https://i.imgur.com/Tlj7vmn.png)](https://i.imgur.com/Tlj7vmn.png)

- Luego, debemos seleccionar la opción directorio, y buscar la carpeta donde tenemos el backend del proyecto, y luego darle click a Finalizar

[![](https://i.imgur.com/ndRnvLh.png)](https://i.imgur.com/ndRnvLh.png)

- Esto nos abrirá el proyecto y poder visualizarlo desde el entorno

[![](https://i.imgur.com/rOSWbmW.png)](https://i.imgur.com/rOSWbmW.png)

- Luego, en la pestaña Boot Dashboard podemos ver el nombre del proyecto y si le damos click derecho, se desplegará un menú donde tenemos varias opciones y le debemos dar click a la primera para poder ejecutar el servidor local

[![](https://i.imgur.com/epkzM4H.png)](https://i.imgur.com/epkzM4H.png)

- Si todo ha salido bien, se nos abrirá la consola dentro del entorno, y nos mostrará un mensaje similar a este

[![](https://i.imgur.com/fT7kTJK.png)](https://i.imgur.com/fT7kTJK.png)

En caso de que todo haya salido bien, podemos dirigirnos a nuestro navegador favorito y abrir la siguiente ruta: http://localhost:8080 y nos saldrá un mensaje como este: 

[![](https://i.imgur.com/HeMpBfV.png)](https://i.imgur.com/HeMpBfV.png)

Aunque nos salga un mensaje de error, es algo normal, pues en esta ruta no hay respuesta programada, así que todo estará bien.

# Base de datos

Para poder entrar a la base de datos en memoria (H2), podemos acceder a la ruta **http://localhost:8080/database** y se nos mostrará un formulario donde debemos diligenciarlo con la siguiente información:

[![](https://i.imgur.com/MXxSHzE.png)](https://i.imgur.com/MXxSHzE.png)

Luego, podríamos realizar consultas a las tablas que se encuentran en la base de datos y ver los registros que existen 

[![](https://i.imgur.com/HYVyUAc.png)](https://i.imgur.com/HYVyUAc.png)

Pero, **¿de donde ha salido todos estos registros?**, en la ruta \src\main\resources existe un archivo llamado data.sql que contiene las consultas que insertan estos registros que acabamos de ver, y la inserción de los mismos se realiza al iniciar el servidor, lo que quiere decir, que si eliminamos manualmente la información, ya sea desde la aplicación o desde la interfaz de la base de datos, al momento de reiniciar el servidor, los datos serán nuevamente ingresados. Estos nos sirven como datos de prueba en la aplicación

# Suposiciones

En una parte del examen, se especifica que : 

"La batalla dará como ganador el equipo que más peleas haya ganado. Cuando un superhéroe
pierde la pelea será eliminado de la aplicación, sin importar si su equipo ganó la batalla."

Sin embargo, yo hubiese preguntado a mi superior sobre esta petición, pues en caso de que se eliminen de la aplicación **es poco informativo para el usuario** saber que sucedió con los perdedores cuando vuelva a verlos en la lista, pues, claramente ya no estará, lo que **generaría incertidumbre** y quizá una mala experiencia para el usuario final, por lo tanto, he decidido no realizar este punto en concreto.
