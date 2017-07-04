-- MySQL dump 10.13  Distrib 5.7.18, for Win64 (x86_64)
--
-- Host: localhost    Database: octts
-- ------------------------------------------------------
-- Server version	5.7.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `announcement`
--

DROP TABLE IF EXISTS `announcement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `announcement` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  `uid` varchar(20) NOT NULL,
  `announcement_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  `teacher_id` varchar(20) NOT NULL,
  `announcement_title` varchar(255) DEFAULT NULL,
  `announcement_message` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_announcement_id` (`announcement_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `announcement`
--

LOCK TABLES `announcement` WRITE;
/*!40000 ALTER TABLE `announcement` DISABLE KEYS */;
/*!40000 ALTER TABLE `announcement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  `uid` varchar(20) NOT NULL,
  `course_id` int(11) NOT NULL,
  `course_year` int(11) NOT NULL,
  `course_start_time` date NOT NULL,
  `course_status` tinyint(4) NOT NULL,
  `course_name` varchar(20) NOT NULL,
  `course_hour` int(11) NOT NULL,
  `course_credit` decimal(10,0) NOT NULL,
  `course_location` varchar(50) NOT NULL,
  `team_limit_information` varchar(255) DEFAULT NULL,
  `teacher_information` varchar(255) DEFAULT NULL,
  `course_information` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_course_id` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_apply`
--

DROP TABLE IF EXISTS `group_apply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_apply` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  `uid` varchar(20) NOT NULL,
  `group_apply_id` tinyint(4) NOT NULL,
  `course_id` int(11) NOT NULL,
  `group_apply_name` varchar(50) NOT NULL,
  `group_apply_owner_id` char(8) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_group_apply_id` (`group_apply_id`),
  UNIQUE KEY `uk_group_apply_owner_id` (`group_apply_owner_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_apply`
--

LOCK TABLES `group_apply` WRITE;
/*!40000 ALTER TABLE `group_apply` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_apply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_apply_member`
--

DROP TABLE IF EXISTS `group_apply_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_apply_member` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  `uid` varchar(20) NOT NULL,
  `group_apply_id` tinyint(4) NOT NULL,
  `course_id` int(11) NOT NULL,
  `student_id` char(8) NOT NULL,
  `group_role` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_student_id` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_apply_member`
--

LOCK TABLES `group_apply_member` WRITE;
/*!40000 ALTER TABLE `group_apply_member` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_apply_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_confirm`
--

DROP TABLE IF EXISTS `group_confirm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_confirm` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  `uid` varchar(20) NOT NULL,
  `group_id` tinyint(4) NOT NULL,
  `course_id` int(11) NOT NULL,
  `group_name` varchar(50) NOT NULL,
  `group_owner_id` char(8) NOT NULL,
  `group_score` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_group_id` (`group_id`),
  UNIQUE KEY `uk_group_owner_id` (`group_owner_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_confirm`
--

LOCK TABLES `group_confirm` WRITE;
/*!40000 ALTER TABLE `group_confirm` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_confirm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_confirm_member`
--

DROP TABLE IF EXISTS `group_confirm_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_confirm_member` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  `uid` varchar(20) NOT NULL,
  `group_id` tinyint(4) NOT NULL,
  `course_id` int(11) NOT NULL,
  `student_id` char(8) NOT NULL,
  `group_role` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_student_id` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_confirm_member`
--

LOCK TABLES `group_confirm_member` WRITE;
/*!40000 ALTER TABLE `group_confirm_member` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_confirm_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `homework`
--

DROP TABLE IF EXISTS `homework`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `homework` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  `uid` varchar(20) NOT NULL,
  `homework_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  `teacher_id` varchar(20) NOT NULL,
  `homework_score` int(11) NOT NULL,
  `homework_status` varchar(255) DEFAULT '0',
  `homework_title` varchar(255) DEFAULT NULL,
  `homework_message` varchar(255) DEFAULT NULL,
  `homework_start_time` datetime NOT NULL,
  `homework_end_time` datetime NOT NULL,
  `resubmit_limit` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_homework_id` (`homework_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `homework`
--

LOCK TABLES `homework` WRITE;
/*!40000 ALTER TABLE `homework` DISABLE KEYS */;
/*!40000 ALTER TABLE `homework` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `homework_upload`
--

DROP TABLE IF EXISTS `homework_upload`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `homework_upload` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  `uid` varchar(20) NOT NULL,
  `homework_upload_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  `homework_id` int(11) NOT NULL,
  `group_id` tinyint(4) NOT NULL,
  `file_name` varchar(255) NOT NULL,
  `homework_url` varchar(255) NOT NULL,
  `resubmit` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_homework_upload_id` (`homework_upload_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `homework_upload`
--

LOCK TABLES `homework_upload` WRITE;
/*!40000 ALTER TABLE `homework_upload` DISABLE KEYS */;
/*!40000 ALTER TABLE `homework_upload` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invitation`
--

DROP TABLE IF EXISTS `invitation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invitation` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  `uid` varchar(20) NOT NULL,
  `sender_id` char(8) NOT NULL,
  `receiver_id` char(8) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invitation`
--

LOCK TABLES `invitation` WRITE;
/*!40000 ALTER TABLE `invitation` DISABLE KEYS */;
/*!40000 ALTER TABLE `invitation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resource`
--

DROP TABLE IF EXISTS `resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  `uid` varchar(20) NOT NULL,
  `resource_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  `teacher_id` varchar(20) NOT NULL,
  `resource_title` varchar(255) DEFAULT NULL,
  `resource_url` varchar(255) DEFAULT NULL,
  `resource_type` varchar(255) DEFAULT '默认',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_resource_id` (`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource`
--

LOCK TABLES `resource` WRITE;
/*!40000 ALTER TABLE `resource` DISABLE KEYS */;
/*!40000 ALTER TABLE `resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `score`
--

DROP TABLE IF EXISTS `score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `score` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  `uid` varchar(20) NOT NULL,
  `score_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  `homework_id` tinyint(4) NOT NULL,
  `group_id` tinyint(4) NOT NULL,
  `grader_id` varchar(20) NOT NULL,
  `score` decimal(10,0) DEFAULT '0',
  `score_message` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_score_id` (`score_id`),
  UNIQUE KEY `uk_homework_id_group_id` (`homework_id`,`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `score`
--

LOCK TABLES `score` WRITE;
/*!40000 ALTER TABLE `score` DISABLE KEYS */;
/*!40000 ALTER TABLE `score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  `uid` varchar(20) NOT NULL,
  `student_id` char(8) NOT NULL,
  `group_id` tinyint(4) DEFAULT NULL,
  `password` varchar(30) NOT NULL,
  `student_name` varchar(20) NOT NULL,
  `student_gender` char(1) NOT NULL,
  `student_class` char(6) NOT NULL,
  `student_absent` int(11) DEFAULT '0',
  `student_rate` decimal(10,0) DEFAULT '0',
  `personal_score` decimal(10,0) DEFAULT '0',
  `group_score` decimal(10,0) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_student_id` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  `uid` varchar(20) NOT NULL,
  `teacher_id` varchar(20) NOT NULL,
  `password` varchar(30) NOT NULL,
  `teacher_name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_teacher_id` (`teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES (1,NOW(),NOW(),'T001','T001','T001','林广艳'),(2,NOW(),NOW(),'T001','T002','T002','贾经冬'),(3,NOW(),NOW(),'T001','T003','T003','谭火彬');
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-04 19:04:58
