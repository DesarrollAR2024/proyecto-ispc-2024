CREATE TABLE `usuarios` (
  `id_usuario` INT PRIMARY KEY,
  `nombre_completo` VARCHAR(50) NOT NULL,
  `nickname` VARCHAR(20) NOT NULL,
  `tipo_usuario` ENUM ('cliente', 'administrador') NOT NULL,
  `CONSTRAINT` chk_tipo_usuario
);

CREATE TABLE `juegos` (
  `id_juego` INT PRIMARY KEY,
  `nombre_juego` VARCHAR(50) NOT NULL,
  `imagen` VARCHAR(100) NOT NULL
);

CREATE TABLE `puntajes` (
  `id_puntaje` INT PRIMARY KEY,
  `id_usuario` INT NOT NULL,
  `id_juego` INT NOT NULL,
  `puntaje` INT NOT NULL,
  `fecha` DATE NOT NULL
);

CREATE TABLE `preguntas` (
  `id_pregunta` INT PRIMARY KEY,
  `id_juego` INT NOT NULL,
  `texto_pregunta` TEXT NOT NULL
);

CREATE TABLE `respuestas` (
  `id_respuesta` INT PRIMARY KEY,
  `id_pregunta` INT NOT NULL,
  `texto_respuesta` TEXT NOT NULL,
  `es_correcta` BOOLEAN NOT NULL
);

ALTER TABLE `puntajes` ADD FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`);

ALTER TABLE `puntajes` ADD FOREIGN KEY (`id_juego`) REFERENCES `juegos` (`id_juego`);

ALTER TABLE `preguntas` ADD FOREIGN KEY (`id_juego`) REFERENCES `juegos` (`id_juego`);

ALTER TABLE `respuestas` ADD FOREIGN KEY (`id_pregunta`) REFERENCES `preguntas` (`id_pregunta`);
