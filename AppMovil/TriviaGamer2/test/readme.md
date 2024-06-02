# Automatización de Pruebas en Android con WebdriverIO y Appium

Este proyecto contiene pruebas automatizadas para una aplicación Android utilizando WebdriverIO y Appium. Estas pruebas permiten verificar el comportamiento de la aplicación en diferentes escenarios y garantizar su calidad a lo largo del tiempo.

## Instalación

1. **Clonar el Repositorio:** 

Clona este repositorio en tu máquina local utilizando el siguiente comando:

   ```bash
   git clone https://github.com/DesarrollAR2024/proyecto-ispc-2024
````

2. **Instalar Dependencias:** 

Una vez clonado el repositorio, navega hasta el directorio del proyecto e instala las dependencias utilizando npm:

   ```bash
cd  AppMovil/TriviaGamer2/test

npm install
````

3. **Configuración:**

Appium: Asegúrate de tener Appium instalado y configurado en tu máquina. Puedes descargar Appium desde su sitio web oficial.

4. **Configuración de WebdriverIO**

Ajusta la configuración de WebdriverIO según sea necesario en el archivo wdio.conf.js. Aquí puedes definir la configuración del dispositivo, el navegador, los scripts de prueba, entre otros aspectos.

5. **Ejecución de Pruebas**

Para ejecutar las pruebas, simplemente utiliza el siguiente comando:

   ```bash
npm test
````
Este comando ejecutará todas las pruebas definidas en el proyecto y mostrará los resultados en la consola.

6. **Estructura del Proyecto**

/test: Contiene los scripts de prueba escritos utilizando WebdriverIO y Mocha. Aquí es donde se definen y organizan las pruebas para verificar diferentes aspectos de la aplicación.

/screenObjects: Contiene los objetos de pantalla que encapsulan la lógica para interactuar con la interfaz de usuario de la aplicación. Esto ayuda a mantener el código limpio y modularizado.


## ¡Gracias por tu interés en este proyecto de automatización de pruebas en Android!
