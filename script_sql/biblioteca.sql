CREATE DATABASE  IF NOT EXISTS `biblioteca` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `biblioteca`;
-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: localhost    Database: biblioteca
-- ------------------------------------------------------
-- Server version	9.5.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '755dfe4a-bf60-11f0-ab07-ec916146af7c:1-179';

--
-- Table structure for table `acerca__de`
--

DROP TABLE IF EXISTS `acerca__de`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `acerca__de` (
  `id` int NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `carne` varchar(50) DEFAULT NULL,
  `proyecto` varchar(150) DEFAULT NULL,
  `version` varchar(20) DEFAULT NULL,
  `fecha` varchar(30) DEFAULT NULL,
  `ruta_foto` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acerca__de`
--

LOCK TABLES `acerca__de` WRITE;
/*!40000 ALTER TABLE `acerca__de` DISABLE KEYS */;
/*!40000 ALTER TABLE `acerca__de` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acerca_de`
--

DROP TABLE IF EXISTS `acerca_de`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `acerca_de` (
  `id` int NOT NULL AUTO_INCREMENT,
  `carne` varchar(50) DEFAULT NULL,
  `nombres` varchar(200) DEFAULT NULL,
  `numero_carne` varchar(50) DEFAULT NULL,
  `foto_path` varchar(500) DEFAULT NULL,
  `proyecto` varchar(200) DEFAULT NULL,
  `version` varchar(50) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acerca_de`
--

LOCK TABLES `acerca_de` WRITE;
/*!40000 ALTER TABLE `acerca_de` DISABLE KEYS */;
INSERT INTO `acerca_de` VALUES (1,'202312345','Tu Nombre','12345678',NULL,'Proyecto Biblioteca','1.0','2025-11-11'),(2,'202312345','Tu Nombre','12345678',NULL,'Proyecto Biblioteca','1.0','2025-11-11');
/*!40000 ALTER TABLE `acerca_de` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acercaa_de`
--

DROP TABLE IF EXISTS `acercaa_de`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `acercaa_de` (
  `id` int NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `carne` varchar(50) DEFAULT NULL,
  `proyecto` varchar(150) DEFAULT NULL,
  `version` varchar(20) DEFAULT NULL,
  `fecha` varchar(30) DEFAULT NULL,
  `ruta_foto` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acercaa_de`
--

LOCK TABLES `acercaa_de` WRITE;
/*!40000 ALTER TABLE `acercaa_de` DISABLE KEYS */;
INSERT INTO `acercaa_de` VALUES (1,'Rita Shantal de León Sanchez','7690-16-3770','Sistema de Biblioteca','1.0','15-11-2025','');
/*!40000 ALTER TABLE `acercaa_de` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acercade`
--

DROP TABLE IF EXISTS `acercade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `acercade` (
  `id` int NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `carne` varchar(50) DEFAULT NULL,
  `proyecto` varchar(150) DEFAULT NULL,
  `version` varchar(20) DEFAULT NULL,
  `fecha` varchar(30) DEFAULT NULL,
  `ruta_foto` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acercade`
--

LOCK TABLES `acercade` WRITE;
/*!40000 ALTER TABLE `acercade` DISABLE KEYS */;
/*!40000 ALTER TABLE `acercade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `autores`
--

DROP TABLE IF EXISTS `autores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `autores` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(150) NOT NULL,
  `nacionalidad` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autores`
--

LOCK TABLES `autores` WRITE;
/*!40000 ALTER TABLE `autores` DISABLE KEYS */;
INSERT INTO `autores` VALUES (2,'Catalina Santos Herrera','canadience'),(3,'Gabriel Garcia Marquez','Colombiana'),(4,'Alicia Escobedo','puertoriqueña'),(5,'Gabriela Garcia Marquez','venezolana'),(6,'Mario Vargas ','Peruano'),(7,'Isabel Allende','Chilena'),(8,'Julio Solola','Argentino'),(9,'Guadalupe Cordoba','mexicana'),(11,'Isabel Allende','Chilena'),(12,'Julio Solola','Argentino'),(16,'Rita de Leon','guatemalteca'),(19,'Hugo Estrada Cabrera','guatemalteco'),(20,'Gabriela Garcia Marquez','venezolana'),(23,'Rita Shantal','Guatemala'),(25,'Luis Aldana','peruano ');
/*!40000 ALTER TABLE `autores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorias` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias`
--

LOCK TABLES `categorias` WRITE;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` VALUES (1,'Romance'),(2,'Novela'),(5,'Accion'),(6,'Niños');
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `libros`
--

DROP TABLE IF EXISTS `libros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `libros` (
  `id` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(255) NOT NULL,
  `autor_id` int DEFAULT NULL,
  `categoria_id` int DEFAULT NULL,
  `anio` int DEFAULT NULL,
  `stock` int DEFAULT '0',
  `destacado` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_libro_autor` (`autor_id`),
  KEY `fk_libro_categoria` (`categoria_id`),
  CONSTRAINT `fk_libro_autor` FOREIGN KEY (`autor_id`) REFERENCES `autores` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_libro_categoria` FOREIGN KEY (`categoria_id`) REFERENCES `categorias` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libros`
--

LOCK TABLES `libros` WRITE;
/*!40000 ALTER TABLE `libros` DISABLE KEYS */;
INSERT INTO `libros` VALUES (1,'Cien Años de Soledad',NULL,1,1967,5,0),(3,'Caperucita Roja',16,6,2016,3,0),(7,'Los arboles',16,5,2025,3,0),(9,'Los caballeros',9,5,2021,9,0),(11,'Los caballos',2,1,2025,8,1),(12,'Los siete enanos',25,6,1995,3,1);
/*!40000 ALTER TABLE `libros` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password_hash` varchar(255) NOT NULL,
  `rol` varchar(50) DEFAULT NULL,
  `estado` tinyint DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'usuarioRita','admin123','ADMIN',1);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-11-15 11:36:10
