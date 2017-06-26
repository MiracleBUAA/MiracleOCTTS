-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: octts
-- ------------------------------------------------------
-- Server version	5.7.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `auth_student`
--

DROP TABLE IF EXISTS `auth_student`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_student` (
  `id`           BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `gmt_create`   DATETIME            NOT NULL,
  `gmt_modified` DATETIME            NOT NULL,
  `student_id`   CHAR(8)             NOT NULL,
  `group_id`     TINYINT                      DEFAULT NULL,
  `password`     VARCHAR(30)         NOT NULL,
  `name`         VARCHAR(20)         NOT NULL,
  `gender`       BOOL                NOT NULL,
  `class`        CHAR(6)             NOT NULL,
  `email`        VARCHAR(50)                  DEFAULT NULL,
  `telephone`    CHAR(14)                     DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_student_id` (`student_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_student`
--

LOCK TABLES `auth_student` WRITE;
/*!40000 ALTER TABLE `auth_student`
  DISABLE KEYS */;
/*!40000 ALTER TABLE `auth_student`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_teacher`
--

DROP TABLE IF EXISTS `auth_teacher`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_teacher` (
  `id`           BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `gmt_create`   DATETIME            NOT NULL,
  `gmt_modified` DATETIME            NOT NULL,
  `teacher_id`   VARCHAR(20)         NOT NULL,
  `password`     VARCHAR(30)         NOT NULL,
  `name`         VARCHAR(20)         NOT NULL,
  `email`        VARCHAR(50)                  DEFAULT NULL,
  `telephone`    CHAR(14)                     DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_teacher_id` (`teacher_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_teacher`
--

LOCK TABLES `auth_teacher` WRITE;
/*!40000 ALTER TABLE `auth_teacher`
  DISABLE KEYS */;
/*!40000 ALTER TABLE `auth_teacher`
  ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2017-06-25 22:35:00
