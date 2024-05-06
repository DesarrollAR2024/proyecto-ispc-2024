-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 02-05-2023 a las 00:41:56
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 8.1.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `gamemate`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `id_categoria` int(11) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `informacion` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id_cliente` int(11) NOT NULL,
  `nombre_completo` varchar(20) NOT NULL,
  `apellido` varchar(20) NOT NULL,
  `dni` int(12) NOT NULL,
  `telefono` int(20) NOT NULL,
  `nickname` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compatibilidad`
--

CREATE TABLE `compatibilidad` (
  `juego` int(11) DEFAULT NULL,
  `dispositivo` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `direccion`
--

CREATE TABLE `direccion` (
  `id_direccion` varchar(50) NOT NULL,
  `direccion` varchar(30) NOT NULL,
  `cod_post` int(4) NOT NULL,
  `provincia` varchar(30) NOT NULL,
  `localidad` varchar(50) NOT NULL,
  `proveedor` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dispositivos`
--

CREATE TABLE `dispositivos` (
  `id_dispositivos` int(11) NOT NULL,
  `pc` varchar(15) DEFAULT NULL,
  `version_celular` varchar(15) DEFAULT NULL,
  `nintendo_switch` varchar(10) DEFAULT NULL,
  `version_playstation` varchar(10) DEFAULT NULL,
  `version_xbox` varchar(10) DEFAULT NULL,
  `usuario` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `envio`
--

CREATE TABLE `envio` (
  `id_envio` int(11) NOT NULL,
  `fecha_envio` date NOT NULL,
  `localidad` varchar(20) NOT NULL,
  `provincia` varchar(20) NOT NULL,
  `cod_post` int(4) NOT NULL,
  `estado_envio` varchar(20) NOT NULL,
  `direccion_envio` varchar(30) NOT NULL,
  `metodo_envio` varchar(30) NOT NULL,
  `cliente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `facturacion`
--

CREATE TABLE `facturacion` (
  `numero_factura` int(11) NOT NULL,
  `metodo_pago` varchar(20) NOT NULL,
  `costo_envio` decimal(5,2) DEFAULT NULL,
  `fecha_factura` date NOT NULL,
  `fecha_pago` date NOT NULL,
  `monto_total` decimal(6,2) NOT NULL,
  `estado_pago` varchar(20) DEFAULT NULL,
  `orden` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `galeria`
--

CREATE TABLE `galeria` (
  `id_galeria` int(11) NOT NULL,
  `nombre_foto` varchar(25) DEFAULT NULL,
  `foto` varchar(50) DEFAULT NULL,
  `jugador` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `juegos`
--

CREATE TABLE `juegos` (
  `id_juego` int(11) NOT NULL,
  `juego` varchar(30) NOT NULL,
  `imagen` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `jugar`
--

CREATE TABLE `jugar` (
  `nivel_jugador` varchar(15) NOT NULL,
  `usuario` varchar(15) DEFAULT NULL,
  `juego` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `orden`
--

CREATE TABLE `orden` (
  `id_orden` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `monto_total` decimal(6,2) NOT NULL,
  `precio_unitario` decimal(5,2) NOT NULL,
  `cantidad_pedido` int(11) NOT NULL,
  `cliente` int(11) NOT NULL,
  `producto_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE `pedido` (
  `id_pedido` int(11) NOT NULL,
  `fecha_pedido` date NOT NULL,
  `estado_pedido` varchar(20) NOT NULL,
  `envio` int(11) NOT NULL,
  `cliente` int(11) NOT NULL,
  `facturacion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id_producto` int(11) NOT NULL,
  `precio` decimal(5,2) NOT NULL,
  `stock` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `proveedor` int(11) NOT NULL,
  `categoria` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

CREATE TABLE `proveedor` (
  `id_proveedor` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `cuil` varchar(15) NOT NULL,
  `email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `redes`
--

CREATE TABLE `redes` (
  `id_redes` int(11) NOT NULL,
  `instagram` varchar(100) DEFAULT NULL,
  `youtube` varchar(100) DEFAULT NULL,
  `tik_tok` varchar(100) DEFAULT NULL,
  `twitter` varchar(100) DEFAULT NULL,
  `twitch` varchar(100) DEFAULT NULL,
  `discord` varchar(100) DEFAULT NULL,
  `usuario` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `nickname` varchar(15) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `apellido` varchar(20) NOT NULL,
  `fecha_nac` date NOT NULL,
  `provincia` varchar(15) NOT NULL,
  `disp_horaria` varchar(6) NOT NULL,
  `email` varchar(25) NOT NULL,
  `contraseña` varchar(10) NOT NULL,
  `imagen_perfil` varchar(50) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`nickname`, `nombre`, `apellido`, `fecha_nac`, `provincia`, `disp_horaria`, `email`, `contraseña`, `imagen_perfil`, `descripcion`) VALUES
('Licia', 'Dora', 'Coronel', '1989-07-22', 'Misiones', 'mañana', 'par@hotmail.com', 'explorador', 'asdasd.jpg', 'hhaihsdabdsasddas'),
('Lila90', 'Clara', 'Lopez', '2010-04-17', 'Jujuy', 'Tarde', 'cl@hotmail.com', 'nosequepon', 'PACK Wallpapers 【 NUEVOS VIII 】 (50).jpg', 'wow'),
('Mar2007', 'Mariela', 'Suer', '2007-02-06', 'Neuquen', 'Tarde', 'marr_07@gmail.com', '123456sdf', 'thumb-1920-1210173.jpg', 'super wow'),
('Ten', 'Lucas', 'Diaz', '2007-11-24', 'Buenos Aires', 'Noche', 'WayV@hotmail.com', 'djhenyangw', '1118470.jpg', 'fdgdsfgsg');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id_categoria`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id_cliente`),
  ADD KEY `nickname` (`nickname`);

--
-- Indices de la tabla `compatibilidad`
--
ALTER TABLE `compatibilidad`
  ADD KEY `juego` (`juego`),
  ADD KEY `dispositivo` (`dispositivo`);

--
-- Indices de la tabla `direccion`
--
ALTER TABLE `direccion`
  ADD PRIMARY KEY (`id_direccion`),
  ADD KEY `proveedor` (`proveedor`);

--
-- Indices de la tabla `dispositivos`
--
ALTER TABLE `dispositivos`
  ADD PRIMARY KEY (`id_dispositivos`),
  ADD KEY `usuario` (`usuario`);

--
-- Indices de la tabla `envio`
--
ALTER TABLE `envio`
  ADD PRIMARY KEY (`id_envio`),
  ADD KEY `cliente` (`cliente`);

--
-- Indices de la tabla `facturacion`
--
ALTER TABLE `facturacion`
  ADD PRIMARY KEY (`numero_factura`),
  ADD KEY `orden` (`orden`);

--
-- Indices de la tabla `galeria`
--
ALTER TABLE `galeria`
  ADD PRIMARY KEY (`id_galeria`),
  ADD KEY `jugador` (`jugador`);

--
-- Indices de la tabla `juegos`
--
ALTER TABLE `juegos`
  ADD PRIMARY KEY (`id_juego`);

--
-- Indices de la tabla `jugar`
--
ALTER TABLE `jugar`
  ADD KEY `usuario` (`usuario`),
  ADD KEY `juego` (`juego`);

--
-- Indices de la tabla `orden`
--
ALTER TABLE `orden`
  ADD PRIMARY KEY (`id_orden`),
  ADD KEY `cliente` (`cliente`),
  ADD KEY `producto_id` (`producto_id`);

--
-- Indices de la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`id_pedido`),
  ADD KEY `envio` (`envio`),
  ADD KEY `cliente` (`cliente`),
  ADD KEY `facturacion` (`facturacion`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id_producto`),
  ADD KEY `proveedor` (`proveedor`),
  ADD KEY `categoria` (`categoria`);

--
-- Indices de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  ADD PRIMARY KEY (`id_proveedor`);

--
-- Indices de la tabla `redes`
--
ALTER TABLE `redes`
  ADD PRIMARY KEY (`id_redes`),
  ADD KEY `usuario` (`usuario`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`nickname`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id_categoria` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id_cliente` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `dispositivos`
--
ALTER TABLE `dispositivos`
  MODIFY `id_dispositivos` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `envio`
--
ALTER TABLE `envio`
  MODIFY `id_envio` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `facturacion`
--
ALTER TABLE `facturacion`
  MODIFY `numero_factura` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `galeria`
--
ALTER TABLE `galeria`
  MODIFY `id_galeria` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `juegos`
--
ALTER TABLE `juegos`
  MODIFY `id_juego` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `orden`
--
ALTER TABLE `orden`
  MODIFY `id_orden` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pedido`
--
ALTER TABLE `pedido`
  MODIFY `id_pedido` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id_producto` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  MODIFY `id_proveedor` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `redes`
--
ALTER TABLE `redes`
  MODIFY `id_redes` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`nickname`) REFERENCES `usuarios` (`nickname`);

--
-- Filtros para la tabla `compatibilidad`
--
ALTER TABLE `compatibilidad`
  ADD CONSTRAINT `compatibilidad_ibfk_1` FOREIGN KEY (`juego`) REFERENCES `juegos` (`id_juego`),
  ADD CONSTRAINT `compatibilidad_ibfk_2` FOREIGN KEY (`dispositivo`) REFERENCES `dispositivos` (`id_dispositivos`);

--
-- Filtros para la tabla `direccion`
--
ALTER TABLE `direccion`
  ADD CONSTRAINT `direccion_ibfk_1` FOREIGN KEY (`proveedor`) REFERENCES `proveedor` (`id_proveedor`);

--
-- Filtros para la tabla `dispositivos`
--
ALTER TABLE `dispositivos`
  ADD CONSTRAINT `dispositivos_ibfk_1` FOREIGN KEY (`usuario`) REFERENCES `usuarios` (`nickname`);

--
-- Filtros para la tabla `envio`
--
ALTER TABLE `envio`
  ADD CONSTRAINT `envio_ibfk_1` FOREIGN KEY (`cliente`) REFERENCES `cliente` (`id_cliente`);

--
-- Filtros para la tabla `facturacion`
--
ALTER TABLE `facturacion`
  ADD CONSTRAINT `facturacion_ibfk_1` FOREIGN KEY (`orden`) REFERENCES `orden` (`id_orden`);

--
-- Filtros para la tabla `galeria`
--
ALTER TABLE `galeria`
  ADD CONSTRAINT `galeria_ibfk_1` FOREIGN KEY (`jugador`) REFERENCES `usuarios` (`nickname`);

--
-- Filtros para la tabla `jugar`
--
ALTER TABLE `jugar`
  ADD CONSTRAINT `jugar_ibfk_1` FOREIGN KEY (`usuario`) REFERENCES `usuarios` (`nickname`),
  ADD CONSTRAINT `jugar_ibfk_2` FOREIGN KEY (`juego`) REFERENCES `juegos` (`id_juego`);

--
-- Filtros para la tabla `orden`
--
ALTER TABLE `orden`
  ADD CONSTRAINT `orden_ibfk_1` FOREIGN KEY (`cliente`) REFERENCES `cliente` (`id_cliente`),
  ADD CONSTRAINT `orden_ibfk_2` FOREIGN KEY (`producto_id`) REFERENCES `producto` (`id_producto`);

--
-- Filtros para la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `pedido_ibfk_1` FOREIGN KEY (`envio`) REFERENCES `envio` (`id_envio`),
  ADD CONSTRAINT `pedido_ibfk_2` FOREIGN KEY (`cliente`) REFERENCES `cliente` (`id_cliente`),
  ADD CONSTRAINT `pedido_ibfk_3` FOREIGN KEY (`facturacion`) REFERENCES `facturacion` (`numero_factura`);

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `producto_ibfk_1` FOREIGN KEY (`proveedor`) REFERENCES `proveedor` (`id_proveedor`),
  ADD CONSTRAINT `producto_ibfk_2` FOREIGN KEY (`categoria`) REFERENCES `categoria` (`id_categoria`);

--
-- Filtros para la tabla `redes`
--
ALTER TABLE `redes`
  ADD CONSTRAINT `redes_ibfk_1` FOREIGN KEY (`usuario`) REFERENCES `usuarios` (`nickname`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
