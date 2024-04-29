# Proyecto-FullStack
Proyecto realizado por estudiantes de la Tecnicatura superior en Desarrollo Web y Aplicaciones Digitales del Instituto Superior Politécnico Córdoba (ISPC).

El proyecto se denomina "Game Mate" y consiste en una página web/aplicación para buscar dúos y amistades en videojuegos en línea.
Contará con filtros por consola/PC, zona geográfica, sexo, edad y calificación o rango de juego. Los usuarios registrados podrán crear un perfil y subir sus mejores partidas, además podrán comentarlas. También podrán formar comunidades por juego. Contará con un E-commerce donde se comercializará merchandansing "gamming"(productos y articulos para los amantesde Videojuegos).
Dentro de la plataforma tambien encontrarán  una sección exclusiva para resolver dudas, denunciar un mal uso y dejar asentados inconvenientes o sugerencias.

# Instalación
Para correr el Proyecto debemos de pararnos en la rama Frontend:

cd Frontend 

cd GameMate

Instalar las dependencias:

npm install

// ó

npm i

Y correr el servidor:

ng serve

// ó

ng s

# Backend
Para correr el proyecto instalar un entorno virtual:

(Se sugiere como buena practica)

python -m virtualenv env


Activar el entrno virtual:

./env/Scripts/activate

Instalar las dependencias

pip install django-cors-headers

pip install djangorestframework

pip install django-rest-passwordreset

pip install djangorestframework_simplejwt

pip install django-paypal

npm install ngx-paypal

Correr el servidor:

python manage.py makemigrations

python manage.py migrate 

python manage.py runserver
