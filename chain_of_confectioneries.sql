-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: chain_of_confectioneries
-- ------------------------------------------------------
-- Server version	8.0.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cofetarie`
--

DROP TABLE IF EXISTS `cofetarie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cofetarie` (
  `id_cofetarie` int NOT NULL AUTO_INCREMENT,
  `adresa_cofetarie` varchar(255) NOT NULL,
  PRIMARY KEY (`id_cofetarie`)
) ENGINE=InnoDB AUTO_INCREMENT=448 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cofetarie`
--

LOCK TABLES `cofetarie` WRITE;
/*!40000 ALTER TABLE `cofetarie` DISABLE KEYS */;
INSERT INTO `cofetarie` VALUES (4,'strada Clujului, Baita23'),(10,'strada Cojocnei 23, Cluj'),(222,'strada clujului, gherla'),(444,'strada Floirolo 234, Giurgiu'),(445,'strada Brasov, Tragiu Jiu'),(446,'strada Bacau, Giurgiu'),(447,'srrada lfeknhgro');
/*!40000 ALTER TABLE `cofetarie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prajitura`
--

DROP TABLE IF EXISTS `prajitura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prajitura` (
  `prajitura_id` int NOT NULL AUTO_INCREMENT,
  `nume_prajitura` varchar(100) NOT NULL,
  `descriere` text,
  `cofetarie_id` int NOT NULL,
  `pret` decimal(10,2) NOT NULL,
  `data_productie` date NOT NULL,
  `data_expirare` date NOT NULL,
  `imagine` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`prajitura_id`),
  KEY `fk_cofetarie` (`cofetarie_id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prajitura`
--

LOCK TABLES `prajitura` WRITE;
/*!40000 ALTER TABLE `prajitura` DISABLE KEYS */;
INSERT INTO `prajitura` VALUES (1,'mamam','egrgre',4,23.00,'2025-12-10','2025-03-10','C:\\Users\\barna\\OneDrive\\Desktop\\an3-sem2\\T1PS\\PT2024_30225_Barna_Melisa_Assignment_3\\amandina_1634223388_0.png'),(21,'broscuta','salam de biscuiti',4,12.00,'2022-10-25','2023-10-25','C:\\Users\\barna\\OneDrive\\Desktop\\an3-sem2\\ltf\\pt2024_30225_barna_melisa_assignment_3\\PT2024_30225_Barna_Melisa_Assignment_3\\tort-ciocolata_1635257504_0.png'),(22,'cioco','cioco',10,11.00,'2022-03-10','2023-03-10','C:\\Users\\barna\\OneDrive\\Desktop\\an3-sem2\\ltf\\pt2024_30225_barna_melisa_assignment_3\\PT2024_30225_Barna_Melisa_Assignment_3\\tort-ciocolata_1635257504_0.png'),(25,'dobos','ciocolata ',4,25.00,'2025-03-14','2025-03-10','C:\\Users\\barna\\OneDrive\\Desktop\\an3-sem2\\ltf\\pt2024_30225_barna_melisa_assignment_3\\PT2024_30225_Barna_Melisa_Assignment_3\\tort-ciocolata_1635257504_0.png'),(41,'lamaita','cu lamaie galbena',10,17.00,'2025-03-25','2025-03-15','C:\\Users\\barna\\OneDrive\\Desktop\\an3-sem2\\ltf\\pt2024_30225_barna_melisa_assignment_3\\PT2024_30225_Barna_Melisa_Assignment_3\\tort-ciocolata_1635257504_0.png'),(42,'sdfef','frgrg',4,324.00,'2025-10-20','2025-03-20','C:\\Users\\barna\\OneDrive\\Desktop\\an3-sem2\\T1PS\\PT2024_30225_Barna_Melisa_Assignment_3'),(43,'mini ciocolata','ciocolata',10,26.00,'2025-04-15','2025-04-02','C:\\Users\\barna\\OneDrive\\Desktop\\an3-sem2\\T1PS\\PT2024_30225_Barna_Melisa_Assignment_3\\amandina_1634223388_0.png'),(44,'rererere','rererereerwrew',4,222.00,'2025-10-03','2025-03-03','C:\\Users\\barna\\OneDrive\\Desktop\\an3-sem2\\T1PS\\PT2024_30225_Barna_Melisa_Assignment_3'),(46,'floricica','vanilie si ciocolata',4,266.00,'2025-10-08','2025-02-08','C:\\Users\\barna\\OneDrive\\Desktop\\an3-sem2\\T1PS\\PT2024_30225_Barna_Melisa_Assignment_3'),(48,'ananas cu portocale','vanilie',10,55.00,'2025-10-09','2025-02-09','C:\\Users\\barna\\OneDrive\\Desktop\\an3-sem2\\T1PS\\PT2024_30225_Barna_Melisa_Assignment_3'),(49,'capsuni','capsuini',10,26.00,'2025-04-15','2025-04-02','C:\\Users\\barna\\OneDrive\\Desktop\\an3-sem2\\T1PS\\PT2024_30225_Barna_Melisa_Assignment_3\\amandina_1634223388_0.png');
/*!40000 ALTER TABLE `prajitura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prajitura_cofetarie`
--

DROP TABLE IF EXISTS `prajitura_cofetarie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prajitura_cofetarie` (
  `prajitura_id` int NOT NULL,
  `id_cofetarie` int NOT NULL,
  `stoc` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`prajitura_id`,`id_cofetarie`),
  KEY `prajitura_cofetarie_ibfk_2` (`id_cofetarie`),
  CONSTRAINT `prajitura_cofetarie_ibfk_2` FOREIGN KEY (`id_cofetarie`) REFERENCES `cofetarie` (`id_cofetarie`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prajitura_cofetarie`
--

LOCK TABLES `prajitura_cofetarie` WRITE;
/*!40000 ALTER TABLE `prajitura_cofetarie` DISABLE KEYS */;
INSERT INTO `prajitura_cofetarie` VALUES (21,4,87),(22,10,0),(25,4,20),(41,10,67),(42,4,0),(43,10,0),(44,4,0),(45,4,0),(46,4,0),(48,10,0),(49,10,0);
/*!40000 ALTER TABLE `prajitura_cofetarie` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-10 12:53:25
