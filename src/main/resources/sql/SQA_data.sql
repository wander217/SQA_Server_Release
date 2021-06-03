-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: sqa_test
-- ------------------------------------------------------
-- Server version	8.0.23

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

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKd4vb66o896tay3yy52oqxr9w0` (`role_id`),
  CONSTRAINT `FKd4vb66o896tay3yy52oqxr9w0` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'$2y$10$a4SC37lbme0NTjMKtONHWOgmM6cFgxw/QLfhVCKcs3AOJW2VJ/a8S','Admin',1),(2,'$2y$10$a4SC37lbme0NTjMKtONHWOgmM6cFgxw/QLfhVCKcs3AOJW2VJ/a8S','Teacher1',3),(3,'$2y$10$a4SC37lbme0NTjMKtONHWOgmM6cFgxw/QLfhVCKcs3AOJW2VJ/a8S','Teacher2',3),(4,'$2y$10$a4SC37lbme0NTjMKtONHWOgmM6cFgxw/QLfhVCKcs3AOJW2VJ/a8S','Teacher3',3),(5,'$2y$10$a4SC37lbme0NTjMKtONHWOgmM6cFgxw/QLfhVCKcs3AOJW2VJ/a8S','Employee1',2),(6,'$2y$10$a4SC37lbme0NTjMKtONHWOgmM6cFgxw/QLfhVCKcs3AOJW2VJ/a8S','Employee2',2);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `city` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `district` varchar(255) NOT NULL,
  `street` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'A','A','A','A'),(2,'B','B','B','B'),(3,'C','C','C','C'),(4,'D','D','D','D'),(5,'E','E','E','E'),(6,'F','F','F','F');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `member_id` bigint NOT NULL,
  PRIMARY KEY (`member_id`),
  CONSTRAINT `FKqe770yjl5gjfgesmdn6xj44sn` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assigned_subject`
--

DROP TABLE IF EXISTS `assigned_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assigned_subject` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `number_of_group` int NOT NULL,
  `teacher_member_id` bigint NOT NULL,
  `term_subject_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6s68g9md5xcqcxin45uiy1d2m` (`teacher_member_id`),
  KEY `FK51uwcsxoqqji3usdni0nbh1td` (`term_subject_id`),
  CONSTRAINT `FK51uwcsxoqqji3usdni0nbh1td` FOREIGN KEY (`term_subject_id`) REFERENCES `term_subject` (`id`),
  CONSTRAINT `FK6s68g9md5xcqcxin45uiy1d2m` FOREIGN KEY (`teacher_member_id`) REFERENCES `teacher` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assigned_subject`
--

LOCK TABLES `assigned_subject` WRITE;
/*!40000 ALTER TABLE `assigned_subject` DISABLE KEYS */;
INSERT INTO `assigned_subject` VALUES (1,1,2,1),(2,1,2,2),(3,1,2,3),(4,1,3,4),(5,1,3,5),(6,1,3,1),(7,1,4,2),(8,1,4,3),(9,1,4,4);
/*!40000 ALTER TABLE `assigned_subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `building`
--

DROP TABLE IF EXISTS `building`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `building` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `building`
--

LOCK TABLES `building` WRITE;
/*!40000 ALTER TABLE `building` DISABLE KEYS */;
INSERT INTO `building` VALUES (1,'A1'),(2,'A2');
/*!40000 ALTER TABLE `building` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'A'),(2,'B'),(3,'C');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `emp_code` varchar(255) NOT NULL,
  `member_id` bigint NOT NULL,
  PRIMARY KEY (`member_id`),
  CONSTRAINT `FKqugy4vbtss1105s6mvjcqea4l` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES ('EMP1',5),('EMP2',6);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fullname`
--

DROP TABLE IF EXISTS `fullname`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fullname` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `middlename` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fullname`
--

LOCK TABLES `fullname` WRITE;
/*!40000 ALTER TABLE `fullname` DISABLE KEYS */;
INSERT INTO `fullname` VALUES (1,'Nguyễn','A','Văn'),(2,'Nguyễn','B','Thị'),(3,'Lê','C',''),(4,'Nguyễn','D','Văn'),(5,'Trần','E','Duy'),(6,'Trần','F','Văn');
/*!40000 ALTER TABLE `fullname` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_info`
--

DROP TABLE IF EXISTS `group_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_info` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `room_id` bigint NOT NULL,
  `shift_id` bigint NOT NULL,
  `subject_group_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpkip39rf6kcdyh3u2vl0q1phw` (`room_id`),
  KEY `FK8e7gp17j1n2kafsgmu9i9w85g` (`shift_id`),
  KEY `FKk7psw29at3eidhuv9s48t1u0v` (`subject_group_id`),
  CONSTRAINT `FK8e7gp17j1n2kafsgmu9i9w85g` FOREIGN KEY (`shift_id`) REFERENCES `shift` (`id`),
  CONSTRAINT `FKk7psw29at3eidhuv9s48t1u0v` FOREIGN KEY (`subject_group_id`) REFERENCES `subject_group` (`id`),
  CONSTRAINT `FKpkip39rf6kcdyh3u2vl0q1phw` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_info`
--

LOCK TABLES `group_info` WRITE;
/*!40000 ALTER TABLE `group_info` DISABLE KEYS */;
INSERT INTO `group_info` VALUES (19,4,1,1),(20,4,2,1),(21,5,1,2),(22,5,2,2),(23,1,1,3),(24,1,2,3),(25,3,1,4),(26,3,2,4),(27,6,3,5),(28,6,4,5),(29,2,3,6),(30,2,4,6),(31,3,4,7),(32,3,5,7),(33,7,4,8),(34,7,5,8),(35,8,4,9),(36,8,5,9);
/*!40000 ALTER TABLE `group_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `learning_week`
--

DROP TABLE IF EXISTS `learning_week`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `learning_week` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `is_desist` bit(1) NOT NULL,
  `group_info_id` bigint NOT NULL,
  `term_week_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKf6uqg6eyqneb9exg7miwjvhg5` (`group_info_id`),
  KEY `FKqnx5w0dbdnc341n4rpb0e2vnx` (`term_week_id`),
  CONSTRAINT `FKf6uqg6eyqneb9exg7miwjvhg5` FOREIGN KEY (`group_info_id`) REFERENCES `group_info` (`id`),
  CONSTRAINT `FKqnx5w0dbdnc341n4rpb0e2vnx` FOREIGN KEY (`term_week_id`) REFERENCES `term_week` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=321 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `learning_week`
--

LOCK TABLES `learning_week` WRITE;
/*!40000 ALTER TABLE `learning_week` DISABLE KEYS */;
INSERT INTO `learning_week` VALUES (1,_binary '\0',19,1),(2,_binary '\0',19,2),(3,_binary '\0',19,3),(4,_binary '\0',19,4),(5,_binary '\0',19,5),(6,_binary '\0',19,6),(7,_binary '\0',19,7),(8,_binary '\0',19,8),(9,_binary '\0',19,9),(10,_binary '\0',19,10),(11,_binary '\0',19,11),(12,_binary '\0',19,12),(13,_binary '\0',19,13),(14,_binary '\0',19,14),(15,_binary '\0',19,15),(16,_binary '\0',19,16),(17,_binary '\0',19,17),(18,_binary '\0',19,18),(19,_binary '\0',19,19),(20,_binary '\0',19,20),(21,_binary '\0',19,21),(22,_binary '\0',19,22),(23,_binary '\0',19,23),(24,_binary '\0',20,15),(25,_binary '\0',20,16),(26,_binary '\0',20,17),(27,_binary '\0',20,18),(28,_binary '\0',20,19),(29,_binary '\0',20,20),(30,_binary '\0',20,21),(31,_binary '\0',20,22),(32,_binary '\0',20,23),(33,_binary '\0',21,1),(34,_binary '\0',21,2),(35,_binary '\0',21,3),(36,_binary '\0',21,4),(37,_binary '\0',21,5),(38,_binary '\0',21,6),(39,_binary '\0',21,7),(40,_binary '\0',21,8),(41,_binary '\0',21,9),(42,_binary '\0',21,10),(43,_binary '\0',21,11),(44,_binary '\0',21,12),(45,_binary '\0',21,13),(46,_binary '\0',21,14),(47,_binary '\0',21,15),(48,_binary '\0',21,16),(49,_binary '\0',21,17),(50,_binary '\0',21,18),(51,_binary '\0',21,19),(52,_binary '\0',21,21),(53,_binary '\0',21,22),(54,_binary '\0',21,23),(56,_binary '\0',22,15),(57,_binary '\0',22,16),(58,_binary '\0',22,17),(59,_binary '\0',22,18),(60,_binary '\0',22,19),(61,_binary '\0',22,20),(62,_binary '\0',22,21),(63,_binary '\0',22,22),(64,_binary '\0',22,23),(65,_binary '\0',23,1),(66,_binary '\0',23,2),(67,_binary '\0',23,3),(68,_binary '\0',23,4),(69,_binary '\0',23,5),(70,_binary '\0',23,6),(71,_binary '\0',23,7),(72,_binary '\0',23,8),(73,_binary '\0',23,9),(74,_binary '\0',23,10),(75,_binary '\0',23,11),(76,_binary '\0',23,12),(77,_binary '\0',23,13),(78,_binary '\0',23,14),(79,_binary '\0',23,15),(80,_binary '\0',23,16),(81,_binary '\0',23,17),(82,_binary '\0',23,18),(83,_binary '\0',23,19),(84,_binary '\0',23,20),(85,_binary '\0',23,21),(86,_binary '\0',23,22),(87,_binary '\0',23,23),(97,_binary '\0',24,15),(98,_binary '\0',24,16),(99,_binary '\0',24,17),(100,_binary '\0',24,18),(101,_binary '\0',24,19),(102,_binary '\0',24,20),(103,_binary '\0',24,21),(104,_binary '\0',24,22),(105,_binary '\0',24,23),(106,_binary '\0',25,1),(107,_binary '\0',25,2),(108,_binary '\0',25,3),(109,_binary '\0',25,4),(110,_binary '\0',25,5),(111,_binary '\0',25,6),(112,_binary '\0',25,7),(113,_binary '\0',25,8),(114,_binary '\0',25,9),(115,_binary '\0',25,10),(116,_binary '\0',25,11),(117,_binary '\0',25,12),(118,_binary '\0',25,13),(119,_binary '\0',25,14),(120,_binary '\0',25,15),(121,_binary '\0',25,16),(122,_binary '\0',25,17),(123,_binary '\0',25,18),(124,_binary '\0',25,19),(125,_binary '\0',25,20),(126,_binary '\0',25,21),(127,_binary '\0',25,22),(128,_binary '\0',25,23),(129,_binary '\0',27,1),(130,_binary '\0',27,2),(131,_binary '\0',27,3),(132,_binary '\0',27,4),(133,_binary '\0',27,5),(134,_binary '\0',27,6),(135,_binary '\0',27,7),(136,_binary '\0',27,8),(137,_binary '\0',27,9),(138,_binary '\0',27,10),(139,_binary '\0',27,11),(140,_binary '\0',27,12),(141,_binary '\0',27,13),(142,_binary '\0',27,14),(143,_binary '\0',27,15),(144,_binary '\0',27,16),(145,_binary '\0',27,17),(146,_binary '\0',27,18),(147,_binary '\0',27,19),(148,_binary '\0',27,20),(149,_binary '\0',27,21),(150,_binary '\0',27,22),(151,_binary '\0',27,23),(152,_binary '\0',29,1),(153,_binary '\0',29,2),(154,_binary '\0',29,3),(155,_binary '\0',29,4),(156,_binary '\0',29,5),(157,_binary '\0',29,6),(158,_binary '\0',29,7),(159,_binary '\0',29,8),(160,_binary '\0',29,9),(161,_binary '\0',29,10),(162,_binary '\0',29,11),(163,_binary '\0',29,12),(164,_binary '\0',29,13),(165,_binary '\0',29,14),(166,_binary '\0',29,15),(167,_binary '\0',29,16),(168,_binary '\0',29,17),(169,_binary '\0',29,18),(170,_binary '\0',29,19),(171,_binary '\0',29,20),(172,_binary '\0',29,21),(173,_binary '\0',29,22),(174,_binary '\0',29,23),(175,_binary '\0',29,1),(176,_binary '\0',29,2),(177,_binary '\0',29,3),(178,_binary '\0',29,4),(179,_binary '\0',29,5),(180,_binary '\0',29,6),(181,_binary '\0',29,7),(182,_binary '\0',29,8),(183,_binary '\0',29,9),(184,_binary '\0',29,10),(185,_binary '\0',29,11),(186,_binary '\0',29,12),(187,_binary '\0',29,13),(188,_binary '\0',29,14),(189,_binary '\0',29,15),(190,_binary '\0',29,16),(191,_binary '\0',29,17),(192,_binary '\0',29,18),(193,_binary '\0',29,19),(194,_binary '\0',29,20),(195,_binary '\0',29,21),(196,_binary '\0',29,22),(197,_binary '\0',29,23),(198,_binary '\0',31,1),(199,_binary '\0',31,2),(200,_binary '\0',31,3),(201,_binary '\0',31,4),(202,_binary '\0',31,5),(203,_binary '\0',31,6),(204,_binary '\0',31,7),(205,_binary '\0',31,8),(206,_binary '\0',31,9),(207,_binary '\0',31,10),(208,_binary '\0',31,11),(209,_binary '\0',31,12),(210,_binary '\0',31,13),(211,_binary '\0',31,14),(212,_binary '\0',31,15),(213,_binary '\0',31,16),(214,_binary '\0',31,17),(215,_binary '\0',31,18),(216,_binary '\0',31,19),(217,_binary '\0',31,20),(218,_binary '\0',31,21),(219,_binary '\0',31,22),(220,_binary '\0',31,23),(221,_binary '\0',33,1),(222,_binary '\0',33,2),(223,_binary '\0',33,3),(224,_binary '\0',33,4),(225,_binary '\0',33,5),(226,_binary '\0',33,6),(227,_binary '\0',33,7),(228,_binary '\0',33,8),(229,_binary '\0',33,9),(230,_binary '\0',33,10),(231,_binary '\0',33,11),(232,_binary '\0',33,12),(233,_binary '\0',33,13),(234,_binary '\0',33,14),(235,_binary '\0',33,15),(236,_binary '\0',33,16),(237,_binary '\0',33,17),(238,_binary '\0',33,18),(239,_binary '\0',33,19),(240,_binary '\0',33,20),(241,_binary '\0',33,21),(242,_binary '\0',33,22),(243,_binary '\0',33,23),(244,_binary '\0',35,1),(245,_binary '\0',35,2),(246,_binary '\0',35,3),(247,_binary '\0',35,4),(248,_binary '\0',35,5),(249,_binary '\0',35,6),(250,_binary '\0',35,7),(251,_binary '\0',35,8),(252,_binary '\0',35,9),(253,_binary '\0',35,10),(254,_binary '\0',35,11),(255,_binary '\0',35,12),(256,_binary '\0',35,13),(257,_binary '\0',35,14),(258,_binary '\0',35,15),(259,_binary '\0',35,16),(260,_binary '\0',35,17),(261,_binary '\0',35,18),(262,_binary '\0',35,19),(263,_binary '\0',35,20),(264,_binary '\0',35,21),(265,_binary '\0',35,22),(266,_binary '\0',35,23),(267,_binary '\0',26,15),(268,_binary '\0',26,16),(269,_binary '\0',26,17),(270,_binary '\0',26,18),(271,_binary '\0',26,19),(272,_binary '\0',26,20),(273,_binary '\0',26,21),(274,_binary '\0',26,22),(275,_binary '\0',26,23),(276,_binary '\0',28,15),(277,_binary '\0',28,16),(278,_binary '\0',28,17),(279,_binary '\0',28,18),(280,_binary '\0',28,19),(281,_binary '\0',28,20),(282,_binary '\0',28,21),(283,_binary '\0',28,22),(284,_binary '\0',28,23),(285,_binary '\0',30,15),(286,_binary '\0',30,16),(287,_binary '\0',30,17),(288,_binary '\0',30,18),(289,_binary '\0',30,19),(290,_binary '\0',30,20),(291,_binary '\0',30,21),(292,_binary '\0',30,22),(293,_binary '\0',30,23),(294,_binary '\0',32,15),(295,_binary '\0',32,16),(296,_binary '\0',32,17),(297,_binary '\0',32,18),(298,_binary '\0',32,19),(299,_binary '\0',32,20),(300,_binary '\0',32,21),(301,_binary '\0',32,22),(302,_binary '\0',32,23),(303,_binary '\0',34,15),(304,_binary '\0',34,16),(305,_binary '\0',34,17),(306,_binary '\0',34,18),(307,_binary '\0',34,19),(308,_binary '\0',34,20),(309,_binary '\0',34,21),(310,_binary '\0',34,22),(311,_binary '\0',34,23),(312,_binary '\0',36,15),(313,_binary '\0',36,16),(314,_binary '\0',36,17),(315,_binary '\0',36,18),(316,_binary '\0',36,19),(317,_binary '\0',36,20),(318,_binary '\0',36,21),(319,_binary '\0',36,22),(320,_binary '\0',36,23);
/*!40000 ALTER TABLE `learning_week` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `account_id` bigint NOT NULL,
  `address_id` bigint NOT NULL,
  `fullname_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4jsivcqa7rxm6w59nggnpywh9` (`account_id`),
  KEY `FKe949jkrgjkwq2hxgj3ow03bpm` (`address_id`),
  KEY `FKhbsg3rvc1hibpf2dge9qn8h64` (`fullname_id`),
  CONSTRAINT `FK4jsivcqa7rxm6w59nggnpywh9` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `FKe949jkrgjkwq2hxgj3ow03bpm` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`),
  CONSTRAINT `FKhbsg3rvc1hibpf2dge9qn8h64` FOREIGN KEY (`fullname_id`) REFERENCES `fullname` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,'Trinhthinh2171999@gmail.com','0328296433',1,1,1),(2,'Trinhthinh2171999@gmail.com','0328296433',2,2,2),(3,'Trinhthinh2171999@gmail.com','0328296433',3,3,3),(4,'Trinhthinh2171999@gmail.com','0328296433',4,4,4),(5,'Trinhthinh2171999@gmail.com','0328296433',5,5,5),(6,'Trinhthinh2171999@gmail.com','0328296433',6,6,6);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registration`
--

DROP TABLE IF EXISTS `registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registration` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `is_enable` bit(1) NOT NULL,
  `reg_time` datetime(6) NOT NULL,
  `subject_group_id` bigint NOT NULL,
  `teacher_member_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKowxp1chwof3fnyi1oqavkbjff` (`subject_group_id`),
  KEY `FK7f9jm2lkkgikumbyl1kxbe4xp` (`teacher_member_id`),
  CONSTRAINT `FK7f9jm2lkkgikumbyl1kxbe4xp` FOREIGN KEY (`teacher_member_id`) REFERENCES `teacher` (`member_id`),
  CONSTRAINT `FKowxp1chwof3fnyi1oqavkbjff` FOREIGN KEY (`subject_group_id`) REFERENCES `subject_group` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registration`
--

LOCK TABLES `registration` WRITE;
/*!40000 ALTER TABLE `registration` DISABLE KEYS */;
INSERT INTO `registration` VALUES (11,_binary '\0','2021-05-24 04:24:42.744000',1,2),(12,_binary '\0','2021-05-24 10:29:19.256000',1,2),(13,_binary '\0','2021-05-24 10:35:20.938000',1,2),(14,_binary '\0','2021-05-24 10:36:37.190000',1,2),(15,_binary '\0','2021-05-24 10:39:08.247000',1,2),(16,_binary '\0','2021-05-24 10:39:17.301000',2,2),(17,_binary '\0','2021-05-24 10:41:01.481000',3,2),(18,_binary '\0','2021-05-24 10:41:09.906000',5,2),(19,_binary '\0','2021-05-24 10:41:14.115000',6,2),(20,_binary '\0','2021-05-24 10:41:24.325000',3,2),(21,_binary '\0','2021-05-24 22:27:55.259000',6,2),(22,_binary '\0','2021-05-24 22:29:36.466000',5,2),(23,_binary '\0','2021-05-24 23:03:22.971000',2,2),(24,_binary '\0','2021-05-25 01:25:15.223000',1,3),(27,_binary '\0','2021-05-25 02:34:31.430000',2,3),(28,_binary '\0','2021-05-25 02:34:48.160000',2,3),(29,_binary '\0','2021-05-25 02:37:35.535000',2,3),(30,_binary '\0','2021-05-25 02:37:40.074000',1,2),(31,_binary '\0','2021-05-25 02:52:18.963000',2,3),(32,_binary '\0','2021-05-25 03:24:34.053000',1,2),(33,_binary '\0','2021-05-25 03:30:55.653000',2,2),(34,_binary '\0','2021-05-25 03:45:39.407000',1,2),(35,_binary '\0','2021-05-25 03:46:43.614000',1,2),(36,_binary '\0','2021-05-25 03:47:31.505000',2,2),(37,_binary '\0','2021-05-25 06:29:48.182000',8,3),(38,_binary '\0','2021-05-25 06:30:37.388000',2,3),(39,_binary '\0','2021-05-25 14:52:01.785000',6,2),(40,_binary '\0','2021-05-25 14:57:13.094000',4,2),(41,_binary '\0','2021-05-25 14:59:27.722000',1,2),(42,_binary '\0','2021-05-25 15:00:14.303000',3,2),(43,_binary '\0','2021-05-25 15:00:23.103000',3,2),(44,_binary '\0','2021-05-25 21:20:54.896000',2,2),(45,_binary '\0','2021-05-25 22:32:03.235000',1,2),(46,_binary '\0','2021-05-25 22:33:54.050000',1,2),(47,_binary '\0','2021-05-25 22:36:10.230000',1,2),(48,_binary '\0','2021-05-25 22:48:48.540000',2,2),(49,_binary '\0','2021-05-27 13:52:54.904000',7,4),(50,_binary '\0','2021-05-27 13:55:51.431000',7,4),(51,_binary '\0','2021-05-27 13:58:43.880000',7,4),(52,_binary '\0','2021-05-27 14:02:05.708000',7,4),(53,_binary '\0','2021-05-27 14:02:58.658000',5,4),(54,_binary '\0','2021-05-27 14:03:05.612000',4,4),(55,_binary '\0','2021-05-27 14:04:32.972000',4,4),(56,_binary '\0','2021-05-27 14:04:40.937000',7,4),(57,_binary '\0','2021-05-27 14:08:42.201000',7,4),(58,_binary '\0','2021-05-27 14:25:23.135000',7,4),(59,_binary '\0','2021-05-27 14:25:29.480000',4,4),(60,_binary '\0','2021-05-27 14:38:59.991000',7,4),(61,_binary '\0','2021-05-27 14:39:26.678000',4,4),(62,_binary '\0','2021-05-27 14:42:22.406000',4,4),(63,_binary '\0','2021-05-27 14:43:07.640000',7,4),(64,_binary '\0','2021-05-27 14:43:52.438000',4,4),(65,_binary '\0','2021-05-27 14:47:32.080000',4,4),(66,_binary '\0','2021-05-27 14:47:59.706000',4,4),(67,_binary '\0','2021-05-27 14:49:42.662000',7,4),(68,_binary '\0','2021-05-27 14:51:42.795000',7,4),(69,_binary '\0','2021-05-27 14:54:07.468000',7,4),(70,_binary '\0','2021-05-27 14:55:38.465000',7,4),(71,_binary '\0','2021-05-27 14:58:11.589000',7,4),(72,_binary '\0','2021-05-27 15:01:06.124000',7,4),(73,_binary '\0','2021-05-27 15:03:03.806000',5,4),(74,_binary '\0','2021-05-27 15:03:14.516000',6,4),(75,_binary '\0','2021-05-27 16:46:52.069000',1,2),(76,_binary '\0','2021-05-27 17:00:47.282000',2,2),(77,_binary '\0','2021-05-27 17:01:19.895000',3,2),(78,_binary '\0','2021-05-27 17:47:24.000000',1,2),(79,_binary '\0','2021-05-27 18:19:15.132000',2,2),(80,_binary '\0','2021-05-27 22:38:43.745000',7,3),(81,_binary '\0','2021-05-27 22:38:52.010000',9,3),(82,_binary '\0','2021-05-27 22:38:59.177000',2,3),(83,_binary '\0','2021-05-27 22:39:05.253000',1,3),(84,_binary '\0','2021-05-27 22:41:17.853000',7,3),(85,_binary '\0','2021-05-27 22:41:36.972000',2,3),(86,_binary '\0','2021-05-27 22:42:17.152000',2,2),(87,_binary '\0','2021-05-27 22:42:27.721000',6,2),(88,_binary '\0','2021-05-27 22:49:45.466000',8,3),(89,_binary '\0','2021-05-27 22:51:07.087000',2,3),(90,_binary '\0','2021-05-27 22:51:41.633000',2,2),(91,_binary '\0','2021-05-27 22:55:50.674000',4,4),(92,_binary '\0','2021-05-27 22:57:10.699000',2,2),(93,_binary '\0','2021-05-27 23:00:50.461000',5,2),(94,_binary '\0','2021-05-27 23:26:43.846000',9,3),(95,_binary '\0','2021-05-27 23:26:44.056000',4,2),(96,_binary '\0','2021-05-27 23:26:44.735000',5,2),(97,_binary '\0','2021-05-29 22:41:34.716000',3,2),(98,_binary '\0','2021-05-29 22:43:28.795000',3,2),(99,_binary '\0','2021-05-29 22:43:40.264000',4,2),(100,_binary '\0','2021-05-29 23:50:09.707000',1,2),(101,_binary '\0','2021-05-30 02:18:01.063000',3,2),(102,_binary '\0','2021-05-30 02:31:20.384000',2,2),(103,_binary '\0','2021-05-30 02:35:57.891000',1,2),(104,_binary '','2021-05-30 02:37:04.901000',2,2),(105,_binary '\0','2021-05-30 02:37:27.389000',3,2),(106,_binary '','2021-05-30 17:36:58.372000',3,2),(107,_binary '\0','2021-06-02 15:50:03.161000',1,3),(108,_binary '\0','2021-06-02 16:35:55.031000',1,3),(109,_binary '\0','2021-06-02 16:40:25.741000',1,3),(110,_binary '\0','2021-06-02 16:43:52.175000',1,3),(111,_binary '\0','2021-06-02 16:56:38.935000',1,3),(112,_binary '\0','2021-06-02 16:57:21.035000',1,3),(113,_binary '\0','2021-06-02 18:08:28.181000',1,3),(114,_binary '\0','2021-06-02 18:10:18.106000',1,3),(115,_binary '\0','2021-06-02 18:25:47.643000',1,3),(116,_binary '\0','2021-06-02 20:07:34.610000',1,3),(117,_binary '\0','2021-06-02 20:32:55.976000',1,3),(118,_binary '\0','2021-06-02 20:34:16.429000',1,3),(119,_binary '\0','2021-06-02 20:43:17.503000',1,3),(120,_binary '\0','2021-06-02 21:07:26.728000',1,3),(121,_binary '\0','2021-06-03 02:29:27.820000',1,3),(122,_binary '\0','2021-06-03 02:32:07.390000',1,3),(123,_binary '\0','2021-06-03 02:36:00.601000',1,3),(124,_binary '\0','2021-06-03 02:37:10.261000',1,3),(125,_binary '\0','2021-06-03 03:07:17.855000',1,3),(126,_binary '\0','2021-06-03 04:24:31.860000',7,3),(127,_binary '','2021-06-03 04:24:48.520000',8,3),(128,_binary '\0','2021-06-03 04:30:38.974000',1,3),(129,_binary '','2021-06-03 04:32:58.472000',1,3);
/*!40000 ALTER TABLE `registration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ADMIN'),(2,'EMPLOYEE'),(3,'TEACHER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `building_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4kmfw73x2vpfymk0ml875rh2q` (`building_id`),
  CONSTRAINT `FK4kmfw73x2vpfymk0ml875rh2q` FOREIGN KEY (`building_id`) REFERENCES `building` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,'101',1),(2,'102',1),(3,'101',2),(4,'102',2),(5,'103',1),(6,'103',2),(7,'201',1),(8,'201',2);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shift`
--

DROP TABLE IF EXISTS `shift`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shift` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `end_time` time NOT NULL,
  `name` varchar(255) NOT NULL,
  `start_time` time NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shift`
--

LOCK TABLES `shift` WRITE;
/*!40000 ALTER TABLE `shift` DISABLE KEYS */;
INSERT INTO `shift` VALUES (1,'08:50:00','Kíp 1','07:00:00'),(2,'10:50:00','Kíp 2','09:00:00'),(3,'13:50:00','Kíp 3','12:00:00'),(4,'17:50:00','Kíp 4','14:00:00'),(5,'19:50:00','Kíp 5','18:00:00');
/*!40000 ALTER TABLE `shift` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (1,'Lập trình C'),(2,'Lập trình C++'),(3,'Lập trình Java'),(4,'Kiểm thử phần mềm'),(5,'Phân tích thiết kế hệ thống');
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject_group`
--

DROP TABLE IF EXISTS `subject_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject_group` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `learning_day` varchar(255) NOT NULL,
  `number_of_teacher` int NOT NULL,
  `term_subject_id` bigint NOT NULL,
  `code` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpkkp9dc28cv6rlyhwgnjkts2s` (`term_subject_id`),
  CONSTRAINT `FKpkkp9dc28cv6rlyhwgnjkts2s` FOREIGN KEY (`term_subject_id`) REFERENCES `term_subject` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject_group`
--

LOCK TABLES `subject_group` WRITE;
/*!40000 ALTER TABLE `subject_group` DISABLE KEYS */;
INSERT INTO `subject_group` VALUES (1,'Thứ hai',2,1,'N1'),(2,'Thứ hai',2,1,'N2'),(3,'Thứ ba',1,2,'N3'),(4,'Thứ hai',1,2,'N4'),(5,'Thứ tư',1,3,'N5'),(6,'Thứ ba',1,3,'N6'),(7,'Thứ ba',1,4,'N7'),(8,'Thứ tư',1,4,'N8'),(9,'Thứ tư',1,5,'N9');
/*!40000 ALTER TABLE `subject_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher` (
  `tch_code` varchar(255) NOT NULL,
  `member_id` bigint NOT NULL,
  `department_id` bigint NOT NULL,
  PRIMARY KEY (`member_id`),
  KEY `FKd419q6obhj46eoye136y7rjyq` (`department_id`),
  CONSTRAINT `FKd419q6obhj46eoye136y7rjyq` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`),
  CONSTRAINT `FKoe31o068r2af29qr9nf0290k` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES ('GV1',2,1),('GV2',3,2),('GV3',4,3);
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `term`
--

DROP TABLE IF EXISTS `term`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `term` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `end_date` date NOT NULL,
  `end_reg_time` datetime(6) NOT NULL,
  `start_date` date NOT NULL,
  `start_reg_time` datetime(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `term`
--

LOCK TABLES `term` WRITE;
/*!40000 ALTER TABLE `term` DISABLE KEYS */;
INSERT INTO `term` VALUES (1,'2022-01-09','2021-07-17 12:00:00.000000','2021-08-02','2021-05-19 01:00:00.000000');
/*!40000 ALTER TABLE `term` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `term_subject`
--

DROP TABLE IF EXISTS `term_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `term_subject` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `credit` int NOT NULL,
  `subject_id` bigint NOT NULL,
  `term_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKikx2ji352jnex38unm5rvuxks` (`subject_id`),
  KEY `FKduo944drbos5rmup3genjimc9` (`term_id`),
  CONSTRAINT `FKduo944drbos5rmup3genjimc9` FOREIGN KEY (`term_id`) REFERENCES `term` (`id`),
  CONSTRAINT `FKikx2ji352jnex38unm5rvuxks` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `term_subject`
--

LOCK TABLES `term_subject` WRITE;
/*!40000 ALTER TABLE `term_subject` DISABLE KEYS */;
INSERT INTO `term_subject` VALUES (1,3,1,1),(2,3,2,1),(3,3,3,1),(4,3,4,1),(5,3,5,1);
/*!40000 ALTER TABLE `term_subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `term_week`
--

DROP TABLE IF EXISTS `term_week`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `term_week` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `end_date` date NOT NULL,
  `start_date` date NOT NULL,
  `term_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK97t8159poj3wf2wmv73mvf4hl` (`term_id`),
  CONSTRAINT `FK97t8159poj3wf2wmv73mvf4hl` FOREIGN KEY (`term_id`) REFERENCES `term` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `term_week`
--

LOCK TABLES `term_week` WRITE;
/*!40000 ALTER TABLE `term_week` DISABLE KEYS */;
INSERT INTO `term_week` VALUES (1,'2021-08-08','2021-08-02',1),(2,'2021-08-15','2021-08-09',1),(3,'2021-08-22','2021-08-16',1),(4,'2021-08-29','2021-08-23',1),(5,'2021-09-05','2021-09-30',1),(6,'2021-09-12','2021-09-06',1),(7,'2021-09-19','2021-09-13',1),(8,'2021-09-26','2021-09-20',1),(9,'2021-10-03','2021-10-27',1),(10,'2021-10-10','2021-10-04',1),(11,'2021-10-17','2021-10-11',1),(12,'2021-10-24','2021-10-18',1),(13,'2021-10-31','2021-10-25',1),(14,'2021-11-07','2021-11-01',1),(15,'2021-11-14','2021-11-08',1),(16,'2021-11-21','2021-11-15',1),(17,'2021-11-28','2021-11-22',1),(18,'2021-12-05','2021-12-29',1),(19,'2021-12-12','2021-12-06',1),(20,'2021-12-19','2021-12-13',1),(21,'2022-12-26','2022-12-20',1),(22,'2022-01-02','2022-01-27',1),(23,'2022-01-09','2022-01-03',1);
/*!40000 ALTER TABLE `term_week` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-03  7:39:00
