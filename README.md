# Prueba - Nexos Software

## Julian Enrique Rodriguez
### Ingeniero de siostemas
#### Correo: julianand2009@hotmail.com
#### Telefono: 3228980094

## BACKEND

La API (Backend) se ejecutaen el puerto: 8080
esta desarrollda con Java 24, Spring Boot 3.5.3
es compatible con versiones de Java 17, 21 y 23

### Rutas de API
Ruta de acceso a la API: http://localhost:8080/inventory/
##### Cargo
Ruta de acceso a la API de Cargos: http://localhost:8080/inventory/position/**
##### Usuario
Ruta de acceso a la API de Usuarios: http://localhost:8080/inventory/user/**
##### Mercancia
Ruta de acceso a la API de Mercancias: http://localhost:8080/inventory/product/**

##### Metodos HTTP
Cada metodo tiene su path pero es el mismo para todas las rutas anteriores seria reemplazar "**" por el path correspondiente </br> 
Para crear se utilza POST con la URL/create </br>
Para obtener todos se utilza GET con la URL/get-all </br>
Para obtener por id se utilza GET con la URL/get-id/? </br>
Para actualizar se utilza PUT con la URL/update </br>
Para eliminar se utilza DELETE con la URL/delete/? </br>

### Base de datos
esta vinculado con cualquier version de PostgreSQL solo hay que cambiar los parametros de la base de datos en el archivo "application.properties" del proyecto 
y tambien hay que crear la base de datos antes de ejecutar por defecto se llama "inventoryDB"


## FRONTEND

En las pantallas web (Rrontend) se ejecuta en el puerto: 4200
estan desarrolladas con Angular version 20.0.5 con el complemento Angular Material

### Rutas de app web
Ruta de acceso a la aplicacion web: http://localhost:4200/
