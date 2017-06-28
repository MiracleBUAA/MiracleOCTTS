-- MySQL dump 10.13  Distrib 5.7.18, for Win64 (x86_64)
--
-- Host: localhost    Database: octts
-- ------------------------------------------------------
-- Server version	5.7.18

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
-- Table structure for table `announcement`
--

DROP TABLE IF EXISTS `announcement`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `announcement` (
  `id`              BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `gmt_create`      DATETIME            NOT NULL,
  `gmt_modified`    DATETIME            NOT NULL,
  `announcement_id` INT(11)             NOT NULL,
  `course_id`       INT(11)             NOT NULL,
  `teacher_id`      VARCHAR(20)         NOT NULL,
  `title`           VARCHAR(255)                 DEFAULT NULL,
  `message`         VARCHAR(255)                 DEFAULT NULL,
  `start_time`      DATETIME            NOT NULL,
  `end_time`        DATETIME            NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_announcement_id` (`announcement_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `id`                     BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `gmt_create`             DATETIME            NOT NULL,
  `gmt_modified`           DATETIME            NOT NULL,
  `course_id`              INT(11)             NOT NULL,
  `course_start_time`      DATE                NOT NULL,
  `course_end_time`        DATE                NOT NULL,
  `course_name`            VARCHAR(20)         NOT NULL,
  `course_hours`           TINYINT(4)          NOT NULL,
  `credit`                 DECIMAL(10, 0)      NOT NULL,
  `course_location`        VARCHAR(50)         NOT NULL,
  `team_limit_information` VARCHAR(255)        NOT NULL,
  `teacher_information`    VARCHAR(255)        NOT NULL,
  `course_information`     VARCHAR(255)        NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_course_id` (`course_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `group`
--

DROP TABLE IF EXISTS `group`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group` (
  `id`             BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `gmt_create`     DATETIME            NOT NULL,
  `gmt_modified`   DATETIME            NOT NULL,
  `group_id`       TINYINT(4)          NOT NULL,
  `group_name`     VARCHAR(50)         NOT NULL,
  `group_owner_id` TINYINT(4)          NOT NULL,
  `group_score`    TINYINT(4)                   DEFAULT NULL,
  `group_status`   TINYINT(4)                   DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_group_id` (`group_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `group_member`
--

DROP TABLE IF EXISTS `group_member`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_member` (
  `id`           BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `gmt_create`   DATETIME            NOT NULL,
  `gmt_modified` DATETIME            NOT NULL,
  `group_id`     TINYINT(4)          NOT NULL,
  `student_id`   CHAR(8)             NOT NULL,
  `group_role`   TINYINT(4)          NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_student_id` (`student_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `homework`
--

DROP TABLE IF EXISTS `homework`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `homework` (
  `id`                  BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `gmt_create`          DATETIME            NOT NULL,
  `gmt_modified`        DATETIME            NOT NULL,
  `homework_id`         INT(11)             NOT NULL,
  `course_id`           INT(11)             NOT NULL,
  `teacher_id`          VARCHAR(20)         NOT NULL,
  `status`              VARCHAR(255)                 DEFAULT '0',
  `title`               VARCHAR(255)                 DEFAULT NULL,
  `message`             VARCHAR(255)                 DEFAULT NULL,
  `start_time`          DATETIME            NOT NULL,
  `end_time`            DATETIME            NOT NULL,
  `resubmit_limit`      TINYINT(4)          NOT NULL,
  `resubmit_limit_time` DATETIME            NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_homework_id` (`homework_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `homework_upload`
--

DROP TABLE IF EXISTS `homework_upload`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `homework_upload` (
  `id`                   BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `gmt_create`           DATETIME            NOT NULL,
  `gmt_modified`         DATETIME            NOT NULL,
  `homework_upload_id`   INT(11)             NOT NULL,
  `group_id`             TINYINT(4)          NOT NULL,
  `homework_url`         VARCHAR(255)        NOT NULL,
  `message`              VARCHAR(255)                 DEFAULT NULL,
  `homework_upload_time` DATETIME            NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_homework_upload_id` (`homework_upload_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `resource`
--

DROP TABLE IF EXISTS `resource`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource` (
  `id`               BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `gmt_create`       DATETIME            NOT NULL,
  `gmt_modified`     DATETIME            NOT NULL,
  `resource_id`      INT(11)             NOT NULL,
  `course_id`        INT(11)             NOT NULL,
  `teacher_id`       VARCHAR(20)         NOT NULL,
  `resource_type`    TINYINT(4)          NOT NULL,
  `title`            VARCHAR(255)                 DEFAULT NULL,
  `resource_url`     VARCHAR(255)                 DEFAULT NULL,
  `resource_size`    INT(11)                      DEFAULT '0',
  `last_update_time` DATETIME            NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_resource_id` (`resource_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `score`
--

DROP TABLE IF EXISTS `score`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `score` (
  `id`           BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `gmt_create`   DATETIME            NOT NULL,
  `gmt_modified` DATETIME            NOT NULL,
  `score_id`     INT(11)             NOT NULL,
  `group_id`     TINYINT(4)          NOT NULL,
  `homework_id`  TINYINT(4)          NOT NULL,
  `teacher_id`   VARCHAR(20)         NOT NULL,
  `score`        DECIMAL(10, 0)               DEFAULT '0',
  `message`      VARCHAR(255)                 DEFAULT NULL,
  `score_time`   DATETIME            NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_score_id` (`score_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `id`           BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `gmt_create`   DATETIME            NOT NULL,
  `gmt_modified` DATETIME            NOT NULL,
  `student_id`   CHAR(8)             NOT NULL,
  `group_id`     TINYINT(4)                   DEFAULT NULL,
  `password`     VARCHAR(30)         NOT NULL,
  `name`         VARCHAR(20)         NOT NULL,
  `gender`       CHAR(1)             NOT NULL,
  `class`        CHAR(6)             NOT NULL,
  `email`        VARCHAR(50)                  DEFAULT NULL,
  `telephone`    CHAR(14)                     DEFAULT NULL,
  `absent`       INT(11)                      DEFAULT '0',
  `rate`         DECIMAL(10, 0)               DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_student_id` (`student_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher` (
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
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2017-06-27 23:36:40
