# Lectura de datos

## Clases
- Proceso padre ProcesoPadre

- Proceso hijo ProcesoHijo

### ProcesoPadre
Se encarga de leer los 5 ficheros de datos, e inicia el proceso para enviar las 5 palabras como un string al proceso hijo.

crea un archivo "resumen.res"

Lee la cantidad de vocales de cada fichero creado por el hijo y las suma
Lee la la lista de palabras de cada fichero y las guarda

Por cada lectura vuelca los datosen el archivo creado

### ProcesoHijo
Lee las pabras recibidas, se encarga de contar las minusculas y vocales de las palabras recibidas, y crea los ficheros relacionados con la tarea, el de cantidad de vocales, la cantidad de minusculas y otro con la lista de palabras recibida

#### Problemas
No encontré otra manera de poder pasar los datos al proceso padre que no sea con un archivo de lista de palabras para que el proceso apdre los lea