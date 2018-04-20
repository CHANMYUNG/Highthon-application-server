-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: highthon
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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `admin_id` char(36) NOT NULL,
  `name` varchar(50) NOT NULL,
  `email` varchar(45) NOT NULL,
  `belong` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `created_date` datetime(3) NOT NULL,
  `updated_date` datetime(3) NOT NULL,
  `last_password_reset_date` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`admin_id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `phone_UNIQUE` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('402881e462e1edc40162e1f4e8020000','테스트슈퍼어드민','admin1@test.com','test School!','$2a$10$j5d3gm5CkZ84L/pPw8pXjuAapq8nqrJV3HUXQhOf/DwCDJKkHuMa6','010-1000-0001','2018-04-20 16:29:06.572','2018-04-20 16:29:06.572',NULL),('402881e462e1edc40162e1f527730001','테스트베이직어드민','admin2@test.com','test School!','$2a$10$6iet4mi5Wuhy6Km53xEp1e0M4JGg9foYwZxaoL/M4WX1Z/m8FfoOW','010-1000-0002','2018-04-20 16:29:22.804','2018-04-20 16:29:22.804',NULL);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_role`
--

DROP TABLE IF EXISTS `admin_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_role` (
  `admin_id` char(36) NOT NULL,
  `role` enum('SUPER','BASIC') NOT NULL,
  PRIMARY KEY (`admin_id`,`role`),
  CONSTRAINT `FK_ADMIN_ID` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`admin_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_role`
--

LOCK TABLES `admin_role` WRITE;
/*!40000 ALTER TABLE `admin_role` DISABLE KEYS */;
INSERT INTO `admin_role` VALUES ('402881e462e1edc40162e1f4e8020000','SUPER'),('402881e462e1edc40162e1f4e8020000','BASIC'),('402881e462e1edc40162e1f527730001','BASIC');
/*!40000 ALTER TABLE `admin_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application`
--

DROP TABLE IF EXISTS `application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application` (
  `application_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `sex` enum('MAN','WOMAN') NOT NULL,
  `phone` varchar(15) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(100) NOT NULL,
  `area` enum('LIFE','GAME') NOT NULL,
  `position` enum('DEVELOP','DESIGN') NOT NULL,
  `is_accepted` tinyint(4) NOT NULL DEFAULT '0',
  `created_date` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `belong` varchar(100) NOT NULL,
  PRIMARY KEY (`application_id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `phone_UNIQUE` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=2074 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application`
--

LOCK TABLES `application` WRITE;
/*!40000 ALTER TABLE `application` DISABLE KEYS */;
INSERT INTO `application` VALUES (1970,'LIFE-DEVELOP1','MAN','010-0000-0001','test1@test.com','$2a$10$/Uihu/omt1nsdDTrtN7RkOpJFdEytiPMeTe1CXdcZZ4UwQp.afgoy','LIFE','DEVELOP',1,'2018-04-20 07:12:23.445','테스트고등학교1'),(1971,'LIFE-DEVELOP2','MAN','010-0000-0002','test2@test.com','$2a$10$gds3XhrDXr.dCrSU65aoHegxEzgIAE7dvJzYUnr0rDLqzJt1pe4eq','LIFE','DEVELOP',1,'2018-04-20 07:12:23.448','테스트고등학교2'),(1972,'LIFE-DEVELOP3','MAN','010-0000-0003','test3@test.com','$2a$10$w7hC1PiKyCXBaWsbu4BibuCp4wvLAN5hy5ujd/ZHtcjUSz/grqnSi','LIFE','DEVELOP',1,'2018-04-20 07:12:23.450','테스트고등학교3'),(1973,'LIFE-DEVELOP4','MAN','010-0000-0004','test4@test.com','$2a$10$Gy3L1moXnz1qvr8iHWAveObW9aXHGONec/J1CnYtzaYsMU2E2gwJu','LIFE','DEVELOP',1,'2018-04-20 07:12:23.453','테스트고등학교4'),(1974,'LIFE-DEVELOP5','MAN','010-0000-0005','test5@test.com','$2a$10$6utBD68GTc8wlsrlUHei.uXW.Pee73HzK.sFTU8eVvQthweUkHrrq','LIFE','DEVELOP',1,'2018-04-20 07:12:23.455','테스트고등학교5'),(1975,'LIFE-DEVELOP6','MAN','010-0000-0006','test6@test.com','$2a$10$F.TFVgBd0gws8wcIlAWRP.uPzJbaFZ0cDrBFL.FJOhXtOPosyXF4m','LIFE','DEVELOP',1,'2018-04-20 07:12:23.458','테스트고등학교6'),(1976,'LIFE-DEVELOP7','MAN','010-0000-0007','test7@test.com','$2a$10$.W6HvycRA0n4miac.c/8cOggxXbiweV77rLk1Sb5xRy8cDG6grhUW','LIFE','DEVELOP',1,'2018-04-20 07:12:23.460','테스트고등학교7'),(1977,'LIFE-DEVELOP8','MAN','010-0000-0008','test8@test.com','$2a$10$543xFfWkeJSZOi7qEi3.SOi9u5VNW/7CSzsoeX8AHL7.H5MJh1Er.','LIFE','DEVELOP',1,'2018-04-20 07:12:23.462','테스트고등학교8'),(1978,'LIFE-DEVELOP9','MAN','010-0000-0009','test9@test.com','$2a$10$.UJukyUaw4qMFAqiyAfoOudZhVYPv7HD.MOFcoWien69YRfzrwiH6','LIFE','DEVELOP',1,'2018-04-20 07:12:23.464','테스트고등학교9'),(1979,'LIFE-DEVELOP10','MAN','010-0000-0010','test10@test.com','$2a$10$f7ta54odtvXY8wjmRevdMOOs.45fUZ4hCKHE3cntZAcGGOHoKhxry','LIFE','DEVELOP',1,'2018-04-20 07:12:23.467','테스트고등학교10'),(1980,'LIFE-DEVELOP11','MAN','010-0000-0011','test11@test.com','$2a$10$2jzAAWfUxLYGtXXIObJjaelf/l.B2Wj/BLrOB7JVNNu9LMM0QCsiG','LIFE','DEVELOP',1,'2018-04-20 07:12:23.469','테스트고등학교11'),(1981,'LIFE-DEVELOP12','MAN','010-0000-0012','test12@test.com','$2a$10$3tNb53NHonfkMv5Z7NgPnuMNVcADqFZzVAty6Ps/B4h7sxj3gp4IC','LIFE','DEVELOP',1,'2018-04-20 07:12:23.471','테스트고등학교12'),(1982,'LIFE-DEVELOP13','MAN','010-0000-0013','test13@test.com','$2a$10$Am2GUndMBjz75Tx1D/bwuOquDWubwo.KmSnOyk9thpC44Xv9n0pT2','LIFE','DEVELOP',1,'2018-04-20 07:12:23.473','테스트고등학교13'),(1983,'LIFE-DEVELOP14','MAN','010-0000-0014','test14@test.com','$2a$10$yoG1.Nh6PENSd.ugxpb20etD1pqMZq1aPgrbWo40u25t1un9g12Cy','LIFE','DEVELOP',1,'2018-04-20 07:12:23.475','테스트고등학교14'),(1984,'LIFE-DEVELOP15','MAN','010-0000-0015','test15@test.com','$2a$10$jm0iyzp6tm1Mwd15rJ27cOdJUbbO4lTYER2A1XAFo/qIWoGSKs0IK','LIFE','DEVELOP',1,'2018-04-20 07:12:23.477','테스트고등학교15'),(1985,'LIFE-DEVELOP16','MAN','010-0000-0016','test16@test.com','$2a$10$u6FQ274iv9ErstrjZTMNW.gumI06M4StsA/w7V8KingEAuNUu1Y2y','LIFE','DEVELOP',1,'2018-04-20 07:12:23.479','테스트고등학교16'),(1986,'LIFE-DEVELOP17','MAN','010-0000-0017','test17@test.com','$2a$10$7KtH6tRoFCmO1j9.9QnkHuZHMURijHz5sX9g0p9ynr3NaM6Tm566S','LIFE','DEVELOP',1,'2018-04-20 07:12:23.481','테스트고등학교17'),(1987,'LIFE-DEVELOP18','MAN','010-0000-0018','test18@test.com','$2a$10$duA5QanGSNY8HLjPGnv4rusk.IJTSvssZHDp11GT.5RXTVhyA3fru','LIFE','DEVELOP',1,'2018-04-20 07:12:23.483','테스트고등학교18'),(1988,'LIFE-DEVELOP19','MAN','010-0000-0019','test19@test.com','$2a$10$2cQhEs9xOOyMCykwFMlla..FpXB.fbO25Rsel0F8OwtICToB7yuAK','LIFE','DEVELOP',1,'2018-04-20 07:12:23.485','테스트고등학교19'),(1989,'LIFE-DEVELOP20','MAN','010-0000-0020','test20@test.com','$2a$10$KvMHF.Yg1/amPGQ1XitI8e.WeMJt4a2uEIFPxMsD2VgQkq9Zrhgsu','LIFE','DEVELOP',1,'2018-04-20 07:12:23.486','테스트고등학교20'),(1990,'LIFE-DEVELOP21','MAN','010-0000-0021','test21@test.com','$2a$10$n4mbwWimwohpdRwdYiJL5eOc13Hk675OA7wSTNcSfQ6VkIqrPiZoK','LIFE','DEVELOP',1,'2018-04-20 07:12:23.488','테스트고등학교21'),(1991,'LIFE-DEVELOP22','MAN','010-0000-0022','test22@test.com','$2a$10$fazt5ncFl7vZbu.tGj.IAOhftSeE5u6N4RaTMUq2Sn2Gkhb3fmQnS','LIFE','DEVELOP',1,'2018-04-20 07:12:23.490','테스트고등학교22'),(1992,'LIFE-DEVELOP23','MAN','010-0000-0023','test23@test.com','$2a$10$vMmnYswwQFx94AR4qze5xeCnx8A0fV3xSe4yWoQ.AIVgSa6bUGJa.','LIFE','DEVELOP',1,'2018-04-20 07:12:23.492','테스트고등학교23'),(1993,'LIFE-DEVELOP24','MAN','010-0000-0024','test24@test.com','$2a$10$SeURSumU4tK7v7Sxqd14MuH/4L.ZW2gq3DLSDdiVH.zTQxQ82Fu86','LIFE','DEVELOP',1,'2018-04-20 07:12:23.494','테스트고등학교24'),(1994,'LIFE-DEVELOP25','MAN','010-0000-0025','test25@test.com','$2a$10$37uyHcBHAJp.KK/79H46BOSS2S87BpAnTB5kV0BrQusQJ/l2Eu8pO','LIFE','DEVELOP',1,'2018-04-20 07:12:23.495','테스트고등학교25'),(1995,'LIFE-DEVELOP26','MAN','010-0000-0026','test26@test.com','$2a$10$7CT7mj9UqYcslfUvdaBIeOaKfAGKsNeRbdisTVUn1JQl2Mm9HVexe','LIFE','DEVELOP',1,'2018-04-20 07:12:23.497','테스트고등학교26'),(1996,'LIFE-DEVELOP27','MAN','010-0000-0027','test27@test.com','$2a$10$Uqe2fpfJ8wIRRhlPd5ZPu.u60q9De5LbsS2pZDuRRh3shG5Hjxwz2','LIFE','DEVELOP',1,'2018-04-20 07:12:23.499','테스트고등학교27'),(1997,'LIFE-DEVELOP28','MAN','010-0000-0028','test28@test.com','$2a$10$NtfnpMnymm3BwkuBvdph/eURSoY8XNtzpTKsbL2co/XPjgslnILi6','LIFE','DEVELOP',1,'2018-04-20 07:12:23.501','테스트고등학교28'),(1998,'LIFE-DEVELOP29','MAN','010-0000-0029','test29@test.com','$2a$10$O9ihJuuvDNILJtIj5T0jMerIye3QLiRzlXkhhF1ihwd4Of0SisqUe','LIFE','DEVELOP',1,'2018-04-20 07:12:23.503','테스트고등학교29'),(1999,'LIFE-DEVELOP30','MAN','010-0000-0030','test30@test.com','$2a$10$OrhmVL5Q/PfmLvAuJ./yZOvflkuNSlpphiIjK/BaGIrS/e7qe1cNm','LIFE','DEVELOP',1,'2018-04-20 07:12:23.504','테스트고등학교30'),(2000,'LIFE-DEVELOP31','MAN','010-0000-0031','test31@test.com','$2a$10$O1usAZEBpsxGBFsnCp6XOOi9S.dvoOlIDMbTObIPgvwkh2bbIiLmm','LIFE','DEVELOP',1,'2018-04-20 07:12:23.506','테스트고등학교31'),(2001,'LIFE-DEVELOP32','MAN','010-0000-0032','test32@test.com','$2a$10$kE4sOHmmV2hOZ.EqB2q1bOA6pvuMoMaIwZ6OLrwQLVXTLjJzU6FZe','LIFE','DEVELOP',1,'2018-04-20 07:12:23.508','테스트고등학교32'),(2002,'LIFE-DEVELOP33','MAN','010-0000-0033','test33@test.com','$2a$10$yhkbnrKRVjDC2tZt./PKhulzgiAIkhU5z7UzcPupX25UyF9qgyHba','LIFE','DEVELOP',1,'2018-04-20 07:12:23.510','테스트고등학교33'),(2003,'LIFE-DEVELOP34','MAN','010-0000-0034','test34@test.com','$2a$10$G5p33W2e25NL6zVzZXRsh.bkbUhOyhHTLz.tJ3S8Yp3tFiVYo.lEC','LIFE','DEVELOP',1,'2018-04-20 07:12:23.512','테스트고등학교34'),(2004,'LIFE-DEVELOP35','MAN','010-0000-0035','test35@test.com','$2a$10$GakT7uB/CFfsxA5nAY4PlO9CN79icOWGUNDrsgjDeezN5G53xhUsW','LIFE','DEVELOP',1,'2018-04-20 07:12:23.514','테스트고등학교35'),(2005,'LIFE-DEVELOP36','MAN','010-0000-0036','test36@test.com','$2a$10$YGSEmbDzfySi0.hISbNUT.dUbovoxIa4aLgA8XR0S3E8dCLDxeyuG','LIFE','DEVELOP',1,'2018-04-20 07:12:23.516','테스트고등학교36'),(2006,'LIFE-DEVELOP37','MAN','010-0000-0037','test37@test.com','$2a$10$RS.nkvlaHWkMmEca21MlT.ipAPB/DKgQpe35t0h8zGY8n8LWHQT12','LIFE','DEVELOP',1,'2018-04-20 07:12:23.517','테스트고등학교37'),(2007,'LIFE-DEVELOP38','MAN','010-0000-0038','test38@test.com','$2a$10$AyL/hty1uRbV5gdM6uNkquQliOZzs3yDuiRFe/JNBRczTP7gO267q','LIFE','DEVELOP',1,'2018-04-20 07:12:23.519','테스트고등학교38'),(2008,'LIFE-DEVELOP39','MAN','010-0000-0039','test39@test.com','$2a$10$gjam8JCI3YGiGPIpry.QXedwvZ7I6LQCVI5.kWSFC2i1qQfMM1Gtm','LIFE','DEVELOP',1,'2018-04-20 07:12:23.521','테스트고등학교39'),(2009,'LIFE-DEVELOP40','MAN','010-0000-0040','test40@test.com','$2a$10$xmPoDMqPOxBkoA4bN46KZe7tn3iT5KtIXIQQrVlTEvBzE8iNVpiaC','LIFE','DEVELOP',1,'2018-04-20 07:12:23.523','테스트고등학교40'),(2010,'LIFE-DEVELOP41','MAN','010-0000-0041','test41@test.com','$2a$10$amdJRhd9/Mi49gKyOyCFjeRTx8FM9H0MGHcebXZzEWA8x/PxjswQK','LIFE','DEVELOP',1,'2018-04-20 07:12:23.525','테스트고등학교41'),(2011,'LIFE-DEVELOP42','MAN','010-0000-0042','test42@test.com','$2a$10$J8o7ySuXXpKNPyVvQPE7eua1bs.coqRY90JX24ncBGLa6lFWv9sbq','LIFE','DEVELOP',1,'2018-04-20 07:12:23.526','테스트고등학교42'),(2012,'LIFE-DEVELOP43','MAN','010-0000-0043','test43@test.com','$2a$10$Ql8AvYU1yVhB2ven/l1iJue0xapioTxJTw/SsUw3V6T0MYMhcqRrK','LIFE','DEVELOP',1,'2018-04-20 07:12:23.528','테스트고등학교43'),(2013,'LIFE-DEVELOP44','MAN','010-0000-0044','test44@test.com','$2a$10$NgmHxAtKvK.8UQ94ZaTkGeJl91iSHo1yJJkYUn9wmtI9/6B9kYfcC','LIFE','DEVELOP',1,'2018-04-20 07:12:23.530','테스트고등학교44'),(2014,'LIFE-DEVELOP45','MAN','010-0000-0045','test45@test.com','$2a$10$AgW1sBJV0L1pzcNLtbWpYOLxLALtQTjwVuk6PLb7i.vU07ErKJLAu','LIFE','DEVELOP',1,'2018-04-20 07:12:23.533','테스트고등학교45'),(2015,'LIFE-DEVELOP46','MAN','010-0000-0046','test46@test.com','$2a$10$JOTc17j9Gowr.8xDZ3Hw1uZ4fqbcHphOJi7jwDB/plBOoMbpy7FpW','LIFE','DEVELOP',0,'2018-04-20 07:12:23.535','테스트고등학교46'),(2016,'LIFE-DESIGN1','MAN','010-0000-0047','test47@test.com','$2a$10$0vY0b8.JQQ6P9kHwTN6.H.OdUQIlIrndtBj.YDK7oc4gBZSDGlzqa','LIFE','DESIGN',1,'2018-04-20 07:17:06.500','테스트고등학교47'),(2017,'LIFE-DESIGN2','MAN','010-0000-0048','test48@test.com','$2a$10$NP0yg8agW8EnoGlZbAZe9.VL6X21S68tXopDAtmjHQ1PfMpjFJqZO','LIFE','DESIGN',1,'2018-04-20 07:17:06.518','테스트고등학교48'),(2018,'LIFE-DESIGN3','MAN','010-0000-0049','test49@test.com','$2a$10$3V5iN9r2PQ4FcykxX59zDOCgLkSWkOxWBtO66JmbGP.gXYWsozw/a','LIFE','DESIGN',1,'2018-04-20 07:17:06.520','테스트고등학교49'),(2019,'LIFE-DESIGN4','MAN','010-0000-0050','test50@test.com','$2a$10$lP1A08MoFAlSsKK4HuTV0OV/kbk3m1EyLbvycRgj5HotM4GSXSyne','LIFE','DESIGN',1,'2018-04-20 07:17:06.523','테스트고등학교50'),(2020,'LIFE-DESIGN5','MAN','010-0000-0051','test51@test.com','$2a$10$1UuysC3gfrXDpLW5Pwdh3.c9EwVsKj8vE7sX5.gzQyPTIJDN7Bfoq','LIFE','DESIGN',1,'2018-04-20 07:17:06.525','테스트고등학교51'),(2021,'LIFE-DESIGN6','MAN','010-0000-0052','test52@test.com','$2a$10$cTvqDew.oTi9tGnmjwrdHe1AaYETWTQVgGINmbUuiix7RK6ETSim6','LIFE','DESIGN',1,'2018-04-20 07:17:06.527','테스트고등학교52'),(2022,'LIFE-DESIGN7','MAN','010-0000-0053','test53@test.com','$2a$10$tWHQcrqg02oaQV9A1PL/ceUbQbPz.tSwKWV0lJjxA/WNPYR9kjlJ.','LIFE','DESIGN',1,'2018-04-20 07:17:06.530','테스트고등학교53'),(2023,'LIFE-DESIGN8','MAN','010-0000-0054','test54@test.com','$2a$10$9v00ZEFWho1TajMKi6a/E.xcFOEErj90A3O0ZwWHqLssrgRTyyfvW','LIFE','DESIGN',1,'2018-04-20 07:17:06.532','테스트고등학교54'),(2024,'LIFE-DESIGN9','MAN','010-0000-0055','test55@test.com','$2a$10$x.mxzs4TiFP4UyiNgvjwLOA1cXnH2iQG1vT/jJeUt/zN06AC9TLWO','LIFE','DESIGN',1,'2018-04-20 07:17:06.537','테스트고등학교55'),(2025,'LIFE-DESIGN10','MAN','010-0000-0056','test56@test.com','$2a$10$Eqll8bJqg4Odt7jin4nhl.cVzbVSnbeVsvpHr/oU4hk4JWzsQPEOi','LIFE','DESIGN',1,'2018-04-20 07:17:06.539','테스트고등학교56'),(2026,'LIFE-DESIGN11','MAN','010-0000-0057','test57@test.com','$2a$10$KTuSMwbgRPKAWWRzVgQcDuudTXbGIsE6.GFyuSswg1f0MDXJxlmKa','LIFE','DESIGN',1,'2018-04-20 07:17:06.541','테스트고등학교57'),(2027,'LIFE-DESIGN12','MAN','010-0000-0058','test58@test.com','$2a$10$K963tLcmJHCt9rfV1QiGfu/9ooFzDo1fl9dHAv500au28Zs83bYw2','LIFE','DESIGN',1,'2018-04-20 07:17:06.544','테스트고등학교58'),(2028,'LIFE-DESIGN13','MAN','010-0000-0059','test59@test.com','$2a$10$3f2X1mBuQ0G0U8pLt9Y6p.Zogx3MnJ6lGV0mr/G4gHpebX54IXajG','LIFE','DESIGN',1,'2018-04-20 07:17:06.547','테스트고등학교59'),(2029,'LIFE-DESIGN14','MAN','010-0000-0060','test60@test.com','$2a$10$nkSN5GZseOnAVZrn/3OdiuT9AkeFX5YmWghoqdzoePHKv5qmNDiA2','LIFE','DESIGN',1,'2018-04-20 07:17:06.549','테스트고등학교60'),(2030,'LIFE-DESIGN15','MAN','010-0000-0061','test61@test.com','$2a$10$CoVzJenRTRdizxnb1Tx8SO48ZnVHkh/cTz21Am7iz97b2hOwZBchm','LIFE','DESIGN',1,'2018-04-20 07:17:06.551','테스트고등학교61'),(2031,'LIFE-DESIGN16','MAN','010-0000-0062','test62@test.com','$2a$10$dnnYO6lcX2rVlTW.2RD0feRevrv/0.GJ5XbrBN5IOSBtxdkqIC.HO','LIFE','DESIGN',0,'2018-04-20 07:17:06.555','테스트고등학교62'),(2032,'GAME-DEVELOP1','MAN','010-0000-0063','test63@test.com','$2a$10$x/yYfj5sSSqG.nhJeLJKbupXJ0dRGXJh0dg0hN9yFQhBAt97DAmt.','GAME','DEVELOP',1,'2018-04-20 07:19:12.707','테스트고등학교63'),(2033,'GAME-DEVELOP2','MAN','010-0000-0064','test64@test.com','$2a$10$GNtetKYYcje8HVsIF7GlU.P98gRcvPBs66jS9ZGGghYTYAmcWA/XW','GAME','DEVELOP',1,'2018-04-20 07:19:12.723','테스트고등학교64'),(2034,'GAME-DEVELOP3','MAN','010-0000-0065','test65@test.com','$2a$10$uuhZUbPYBcT2uG0iXT98Tu7gutHb6VLtbVP1NSUWqO89QxR0jGEaO','GAME','DEVELOP',1,'2018-04-20 07:19:12.725','테스트고등학교65'),(2035,'GAME-DEVELOP4','MAN','010-0000-0066','test66@test.com','$2a$10$Mco.4jMMjcU1cQBEqUHg/uLyt1GKIdZ3xED7IvmlPnKIDbarJv5rO','GAME','DEVELOP',1,'2018-04-20 07:19:12.729','테스트고등학교66'),(2036,'GAME-DEVELOP5','MAN','010-0000-0067','test67@test.com','$2a$10$yim55F4se0qJk9gYtEhH/uAGASB7JdxshKW9OhHU/CYoBn.gNgh1q','GAME','DEVELOP',1,'2018-04-20 07:19:12.731','테스트고등학교67'),(2037,'GAME-DEVELOP6','MAN','010-0000-0068','test68@test.com','$2a$10$CCVvNcNago46OvlMa9UvteRB6l8pQz5oIjpA2uYRTuhtu7mM5aMUm','GAME','DEVELOP',1,'2018-04-20 07:19:12.733','테스트고등학교68'),(2038,'GAME-DEVELOP7','MAN','010-0000-0069','test69@test.com','$2a$10$cV6K8o1bOGRTt2AvYvcBnudKoxXiKZbGpsOSMrDWpXao7dWVYQX2W','GAME','DEVELOP',1,'2018-04-20 07:19:12.735','테스트고등학교69'),(2039,'GAME-DEVELOP8','MAN','010-0000-0070','test70@test.com','$2a$10$vqzMgtXKqQg1KUjTkngZoO7iJWdds6jZBZy42Zgrj1S6fTXw3atCu','GAME','DEVELOP',1,'2018-04-20 07:19:12.737','테스트고등학교70'),(2040,'GAME-DEVELOP9','MAN','010-0000-0071','test71@test.com','$2a$10$4eW6WeeCev0kYm26oz1qEelGSXS.p7cYSoW7xOHbpFiVmUE6IUZp2','GAME','DEVELOP',1,'2018-04-20 07:19:12.739','테스트고등학교71'),(2041,'GAME-DEVELOP10','MAN','010-0000-0072','test72@test.com','$2a$10$MK1OSL46wSZI/6rnF2JTyeQzS5ZOJL6lkblIn0.FoxLVIJMQa39Ua','GAME','DEVELOP',1,'2018-04-20 07:19:12.741','테스트고등학교72'),(2042,'GAME-DEVELOP11','MAN','010-0000-0073','test73@test.com','$2a$10$gg7TU4ksnGO4.rnTz84GYOhMHmHw9aaAp2I1Nf66yN1sUk9TGRyMe','GAME','DEVELOP',1,'2018-04-20 07:19:12.743','테스트고등학교73'),(2043,'GAME-DEVELOP12','MAN','010-0000-0074','test74@test.com','$2a$10$PPHEu5PDxNZRHAGmJshIH.iziMWMHmLxFIzLlLGBGHhdAZTRNWM4K','GAME','DEVELOP',1,'2018-04-20 07:19:12.746','테스트고등학교74'),(2044,'GAME-DEVELOP13','MAN','010-0000-0075','test75@test.com','$2a$10$bmVCmOITWVS/UO6LUjjKh.6GRmiaXt1fqXnvTFlf4ihmRY4Yol6oW','GAME','DEVELOP',1,'2018-04-20 07:19:12.748','테스트고등학교75'),(2045,'GAME-DEVELOP14','MAN','010-0000-0076','test76@test.com','$2a$10$O70PabL/gFVi/Cy0wT5/xe0mR3cMJOgZHcxjuVpLHnxkdhu3JscgS','GAME','DEVELOP',1,'2018-04-20 07:19:12.750','테스트고등학교76'),(2046,'GAME-DEVELOP15','MAN','010-0000-0077','test77@test.com','$2a$10$e7rhollvwXueewjSpJynYetDz9BC3EqHyytWsidScNZ/k8u8mefxW','GAME','DEVELOP',1,'2018-04-20 07:19:12.752','테스트고등학교77'),(2047,'GAME-DEVELOP16','MAN','010-0000-0078','test78@test.com','$2a$10$cE5Huxswbljo/NwneH1qLO5/TYY/5EtkWcLWll.mvko4YsjQp5hlC','GAME','DEVELOP',1,'2018-04-20 07:19:12.756','테스트고등학교78'),(2048,'GAME-DEVELOP17','MAN','010-0000-0079','test79@test.com','$2a$10$WNrUUl.qCiGGMatuTlnQheXmM.LusW/7PQKuri1FqFcUtDBQ34i3y','GAME','DEVELOP',1,'2018-04-20 07:19:12.758','테스트고등학교79'),(2049,'GAME-DEVELOP18','MAN','010-0000-0080','test80@test.com','$2a$10$4WTCfP7vkLNQFEcZLKQgW..A/Zqyv2wo6d28ZC4BlsQgg3d1VdaWC','GAME','DEVELOP',1,'2018-04-20 07:19:12.760','테스트고등학교80'),(2050,'GAME-DEVELOP19','MAN','010-0000-0081','test81@test.com','$2a$10$H7sfZ6CWLsdAnaal1ueNou134b2E1cOvSSgRuGa2n39I7Zo6CwOn6','GAME','DEVELOP',1,'2018-04-20 07:19:12.762','테스트고등학교81'),(2051,'GAME-DEVELOP20','MAN','010-0000-0082','test82@test.com','$2a$10$1sAwq8Kp6KuVHVOHsO23VulLnmW2rCT3p3k5KnHgzAFqQPmfFjWJa','GAME','DEVELOP',1,'2018-04-20 07:19:12.764','테스트고등학교82'),(2052,'GAME-DEVELOP21','MAN','010-0000-0083','test83@test.com','$2a$10$rQ1BmLqDave0nTKXRdXLp.X6uuE9f6J5Qe1sv6gQ43IzuGbiZn2.i','GAME','DEVELOP',1,'2018-04-20 07:19:12.766','테스트고등학교83'),(2053,'GAME-DEVELOP22','MAN','010-0000-0084','test84@test.com','$2a$10$6NTzuvUQGhOo12vqGL/3RewKm8i7CtMhA1wXHgSqwKHrnRUqyFMQi','GAME','DEVELOP',1,'2018-04-20 07:19:12.768','테스트고등학교84'),(2054,'GAME-DEVELOP23','MAN','010-0000-0085','test85@test.com','$2a$10$UN1FsN3.YQ7BoNgTVbOVH.jmsZ1/N0FqfIzWsEjaC1yl8n9KvmGpC','GAME','DEVELOP',1,'2018-04-20 07:19:12.770','테스트고등학교85'),(2055,'GAME-DEVELOP24','MAN','010-0000-0086','test86@test.com','$2a$10$6e7p/DkQO9gu0ACQJtD27.TJpzaMzhuQVyy9KQgHmrnh2uB8qcBOq','GAME','DEVELOP',1,'2018-04-20 07:19:12.772','테스트고등학교86'),(2056,'GAME-DEVELOP25','MAN','010-0000-0087','test87@test.com','$2a$10$v8OhZ9W0tUMoyQB96dcgIuqT8Qxrw4840ELDQ9nLQNJabpfP8MxBC','GAME','DEVELOP',1,'2018-04-20 07:19:12.774','테스트고등학교87'),(2057,'GAME-DEVELOP26','MAN','010-0000-0088','test88@test.com','$2a$10$6jJAYjBJ2pS2rv6B3G/edOsZIdko/7tqvLIhjp2gdhZxzyu1kothu','GAME','DEVELOP',1,'2018-04-20 07:19:12.776','테스트고등학교88'),(2058,'GAME-DEVELOP27','MAN','010-0000-0089','test89@test.com','$2a$10$8Hc62p.MHfPHnhHB1BPg4O99kkaNCDzku5505nhFLg0g5VrDAa0Vu','GAME','DEVELOP',1,'2018-04-20 07:19:12.778','테스트고등학교89'),(2059,'GAME-DEVELOP28','MAN','010-0000-0090','test90@test.com','$2a$10$6af3VuBZsHDn2eVrZFz2X.Ffm/VkCAi8DRcjtUvm1qt8HaqykjdF6','GAME','DEVELOP',1,'2018-04-20 07:19:12.780','테스트고등학교90'),(2060,'GAME-DEVELOP29','MAN','010-0000-0091','test91@test.com','$2a$10$v3j4e.HXeP2hUXSd3pGhSet6G..9CJvhpuqMSDq.74ksuRHU/CVs2','GAME','DEVELOP',1,'2018-04-20 07:19:12.782','테스트고등학교91'),(2061,'GAME-DEVELOP30','MAN','010-0000-0092','test92@test.com','$2a$10$MUFPrb9diLZwLxBM9OsN2.rYs2vtuGM1d2bqXmcRiub4KSY5H/VTC','GAME','DEVELOP',1,'2018-04-20 07:19:12.785','테스트고등학교92'),(2062,'GAME-DEVELOP31','MAN','010-0000-0093','test93@test.com','$2a$10$8S.l/Uij4QAGo4nCNqmzluE/gfzmm9VnocRIzPhL51jLQMOCqCKzW','GAME','DEVELOP',0,'2018-04-20 07:19:12.787','테스트고등학교93'),(2063,'GAME-DESIGN1','MAN','010-0000-0094','test94@test.com','$2a$10$/OTtiLkdXcQYnFX2AWVyUeCev/9WPvuQ4FMYknX4MSzFE0xKKZVxG','GAME','DESIGN',1,'2018-04-20 07:20:34.368','테스트고등학교94'),(2064,'GAME-DESIGN2','MAN','010-0000-0095','test95@test.com','$2a$10$Jb65EAYaaF07hEYWNa8Lou4MUhESagcy5ETzDCx/e10vM.i/NXIeG','GAME','DESIGN',1,'2018-04-20 07:20:34.383','테스트고등학교95'),(2065,'GAME-DESIGN3','MAN','010-0000-0096','test96@test.com','$2a$10$12nJgfYMbDI7yFMqFzE/V.Opr.e4zBbiv1Z8H0whIpwX4duNQyfHK','GAME','DESIGN',1,'2018-04-20 07:20:34.386','테스트고등학교96'),(2066,'GAME-DESIGN4','MAN','010-0000-0097','test97@test.com','$2a$10$3iKVmWkyw7Jk6mMRUhf1wunTxUsPHE.3j0bt90Ctkfvf8NfSXpPNa','GAME','DESIGN',1,'2018-04-20 07:20:34.388','테스트고등학교97'),(2067,'GAME-DESIGN5','MAN','010-0000-0098','test98@test.com','$2a$10$U/TpQvg98WX1TcYpClufwOB/8cUkSB96kxt8f4U49kylK.sjEMdka','GAME','DESIGN',1,'2018-04-20 07:20:34.390','테스트고등학교98'),(2068,'GAME-DESIGN6','MAN','010-0000-0099','test99@test.com','$2a$10$L1GjqMQhV9RqmUsRslvlPO405zvjmIbKiJ8hNChTwkaiTWhe2jsCG','GAME','DESIGN',1,'2018-04-20 07:20:34.392','테스트고등학교99'),(2069,'GAME-DESIGN7','MAN','010-0000-0100','test100@test.com','$2a$10$k3ZNHdVabGZ6Rcho.oSQluWijdhuDm6BFWXH5QTRaH/cKNpBt6avi','GAME','DESIGN',1,'2018-04-20 07:20:34.394','테스트고등학교100'),(2070,'GAME-DESIGN8','MAN','010-0000-0101','test101@test.com','$2a$10$NS1oKC4EFIn7KynNq9liW.Ez4Xc6Ntt5ZR9snwwhgPdjVSd8LQPh2','GAME','DESIGN',1,'2018-04-20 07:20:34.396','테스트고등학교101'),(2071,'GAME-DESIGN9','MAN','010-0000-0102','test102@test.com','$2a$10$fWJ.UJoOgeEutgF9YgLcqu6mFib/BmHk0Jr8KM/0cE5amCo6NmWMS','GAME','DESIGN',1,'2018-04-20 07:20:34.398','테스트고등학교102'),(2072,'GAME-DESIGN10','MAN','010-0000-0103','test103@test.com','$2a$10$LCqaoXB0whaqrLuG7r4aCObgEyYRJ4BiyG/rxi4Jlpx2cqhnz1VA.','GAME','DESIGN',1,'2018-04-20 07:20:34.400','테스트고등학교103'),(2073,'GAME-DESIGN11','MAN','010-0000-0104','test104@test.com','$2a$10$Rju0rY1Sx76gfZV1CCHVHO34hoXPzt1ziBl1HynA9EdbkndLi8WLu','GAME','DESIGN',0,'2018-04-20 07:20:34.402','테스트고등학교104');
/*!40000 ALTER TABLE `application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invitation`
--

DROP TABLE IF EXISTS `invitation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invitation` (
  `invitation_code` char(36) NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`email`),
  UNIQUE KEY `invitation_code_UNIQUE` (`invitation_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invitation`
--

LOCK TABLES `invitation` WRITE;
/*!40000 ALTER TABLE `invitation` DISABLE KEYS */;
/*!40000 ALTER TABLE `invitation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-20 16:32:43
