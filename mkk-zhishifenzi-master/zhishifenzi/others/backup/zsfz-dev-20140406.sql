-- MySQL dump 10.13  Distrib 5.5.15, for Win32 (x86)
--
-- Host: localhost    Database: zsfz
-- ------------------------------------------------------
-- Server version	5.5.15

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
-- Table structure for table `album`
--

DROP TABLE IF EXISTS `album`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `album` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `archived` tinyint(1) DEFAULT '0',
  `name_` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `match_id` int(11) DEFAULT NULL,
  `creator_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `guid` (`guid`),
  KEY `guid_index` (`guid`),
  KEY `match_id_index` (`match_id`),
  KEY `creator_id_index` (`creator_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `album`
--

LOCK TABLES `album` WRITE;
/*!40000 ALTER TABLE `album` DISABLE KEYS */;
INSERT INTO `album` VALUES (1,'f73231acfe33479490501fd026e425431395497814283','2014-03-22 22:16:54',0,'2013-04-09 球队和环保局比赛照片e','3:1 .2013-04-09 球队和环保局比赛照片xxxx',NULL,NULL),(3,'d7675b0ca53d4b5aadbe048557cd53fe1395502616128','2014-03-22 23:36:56',0,'雅安地震时的比赛照片','雅安地震时的比赛照片, 大家静静地.',NULL,NULL),(4,'2a177f6107164e999bcb043836c57f891395502668189','2014-03-22 23:37:48',0,'afsdfddsfdfsde','asdfsdsfsddsfsd',NULL,NULL),(5,'986d8ea94f4f49fa9433cd56a7ea63241395502934549','2014-03-22 23:42:14',0,'test11','test112',NULL,NULL),(6,'c68b90ca58fe48239084f9b32651b93b1395505102466','2014-03-23 00:18:22',0,'assfeddd','sadfadfs',NULL,NULL),(7,'7a031efe2a4b432dad610769692f4a511395505612046','2014-03-23 00:26:52',1,'qqqq','qqqqqq',NULL,NULL),(8,'91cb61fe1d8948889f48fddfb2b2d7b81395583056113','2014-03-23 21:57:36',0,'2013-04-09 球队和环保局比赛照片','kolkljjjkl',NULL,NULL),(9,'6120c97a128645059b42bf0d657102951395934897293','2014-03-27 23:41:37',0,'test232','33332',NULL,NULL);
/*!40000 ALTER TABLE `album` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `club`
--

DROP TABLE IF EXISTS `club`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `club` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `archived` tinyint(1) DEFAULT '0',
  `name_` varchar(255) DEFAULT NULL,
  `contact` varchar(255) DEFAULT NULL,
  `creator_id` int(11) DEFAULT NULL,
  `remark` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `guid` (`guid`),
  KEY `guid_index` (`guid`),
  KEY `creator_id_index` (`creator_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `club`
--

LOCK TABLES `club` WRITE;
/*!40000 ALTER TABLE `club` DISABLE KEYS */;
INSERT INTO `club` VALUES (1,'7999187fec614ede8223ab7f2f5e5a881394980899511','2014-03-16 22:41:39',0,'清江足球队','李四 13300231117(手机) 87658767(QQ号)',NULL,'这球队来自巴中.'),(2,'5c9e5dc742c74a8f930b50d1173041ab1394981250388','2014-03-16 22:47:30',0,'申龙足球队','李明 19876578932',NULL,'老代之前的足球队'),(3,'498440ea528a4aa681f97cbf9ff3cb451394981857205','2014-03-16 22:57:37',1,'asfsdfa','asdfasdf',NULL,''),(4,'3ddeb1322ff748b6a00740c1e4178d6a1394982024894','2014-03-16 23:00:24',0,'环保局足球队','marico 13344334(手机)',NULL,'老对手了, 踢了好几年了.'),(5,'a8174069eef945798d1bed7c58b8137a1395417993882','2014-03-22 00:06:33',0,'国腾电子足球队','李d 13300231117(手机) 87658767(QQ号)',NULL,'国腾电子足球队');
/*!40000 ALTER TABLE `club` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gecko_file`
--

DROP TABLE IF EXISTS `gecko_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gecko_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `archived` tinyint(1) DEFAULT '0',
  `name_` varchar(255) DEFAULT NULL,
  `path_` varchar(255) DEFAULT NULL,
  `suffix` varchar(255) DEFAULT NULL,
  `temp_` tinyint(1) DEFAULT '0',
  `size_` int(20) DEFAULT '0',
  `type_` varchar(255) DEFAULT 'GeckoFile',
  `url` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `head_photo` tinyint(1) DEFAULT '0',
  `album_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `guid` (`guid`),
  KEY `guid_index` (`guid`),
  KEY `album_id_index` (`album_id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gecko_file`
--

LOCK TABLES `gecko_file` WRITE;
/*!40000 ALTER TABLE `gecko_file` DISABLE KEYS */;
INSERT INTO `gecko_file` VALUES (1,'e66ab810c04f48bd895606ae5b0b79701395497814778','2014-03-22 22:16:54',0,'3.jpg','e1569709554144ccacd76edef5a1c7d11395497814780','jpg',0,108715,'Photo',NULL,NULL,0,1),(3,'2a0ff06afb994c39be658b10c495dc711395502618102','2014-03-22 23:36:58',0,'2.jpg','543fba51639544aa91302bebb6c59e981395502618102','jpg',0,55178,'Photo',NULL,NULL,0,3),(4,'6bd4c4a2b6094489bf49dc85ba4692a41395502669023','2014-03-22 23:37:49',0,'2.jpg','e610c31a40bd4b4e8c29a5dd8d3098eb1395502669024','jpg',0,55178,'Photo',NULL,NULL,0,4),(5,'f1414e0b056e4c08865e1f879fac17ac1395502669168','2014-03-22 23:37:49',0,'exaple-image.jpg','8b3333e30c3a447eb26aa849a5c8eb491395502669168','jpg',0,180970,'Photo',NULL,NULL,0,4),(6,'99fca75193304d4eb5d0cf4668daa7111395503027153','2014-03-22 23:43:47',0,'3.jpg','844263b1c7184e6682410a5e577807af1395503028817','jpg',0,127446,'Photo',NULL,NULL,0,5),(7,'a4ee17fe4c2d4c398a0d91bfc6e2455c1395503061514','2014-03-22 23:44:21',0,'4.jpg','0cef08ca8f244b45906416ed20ebe3141395503064626','jpg',0,110171,'Photo',NULL,NULL,0,5),(8,'a6328a0310d24609a3324dd6ccf2f9b91395505102906','2014-03-23 00:18:22',0,'exaple-image.jpg','f2457a67592a4ec4934a57589ae7ab511395505102906','jpg',0,180970,'Photo',NULL,NULL,0,6),(9,'e6fea3c2f64049dc93ee13786c81b0f61395505103059','2014-03-23 00:18:23',0,'4.jpg','06867d6296594c9b8a474a35922406fc1395505103059','jpg',0,110171,'Photo',NULL,NULL,0,6),(12,'e96f26db74b245a3b6f13025c4dbdec91395583057636','2014-03-23 21:57:37',0,'3.jpg','27a8ba78542944b8a0b0624877327e8c1395583057636','jpg',0,127446,'Photo',NULL,NULL,0,8),(13,'b8eb538164b64948a599993c3d7ce6b31395583058359','2014-03-23 21:57:38',0,'2.jpg','bab4957f9f7d4c32a81295e15d4c57721395583058359','jpg',0,55178,'Photo',NULL,NULL,0,8),(14,'ae04a66b7ea6427a911e0aca40c73e821395583058507','2014-03-23 21:57:38',0,'4.jpg','365baff4fcf14b33987328fc146917941395583058508','jpg',0,110171,'Photo',NULL,NULL,0,8),(16,'1d34d9fc96d644b7ad48838eda9f114e1395929847239','2014-03-27 22:17:27',0,'1.jpg','d61b009191ec474f9110b9c795112e1a1395929847240','jpg',0,152160,'Photo',NULL,NULL,0,6),(17,'cfbe14d1af9948ab9c56570b6ff4b4f11395929994898','2014-03-27 22:19:54',0,'DSC00759.JPG','e8161f0b2aa84fb5ac9d627ab058583d1395929994898','JPG',0,110842,'Photo',NULL,NULL,0,6),(18,'a9540ccbd64a4bc09447f2949d00fa5c1395930014546','2014-03-27 22:20:14',0,'DSC00758.JPG','e99155dcc16f43f9b401357147e4e24a1395930014546','JPG',0,75487,'Photo',NULL,NULL,0,6),(20,'f2a8167306044e208668b3995b6f46081395930130201','2014-03-27 22:22:10',0,'DSC00866.JPG','6d5e906b230741869457aa53ab5e2f141395930130201','JPG',0,229608,'Photo',NULL,NULL,0,6),(21,'236e782681584f24bc57b17363f96a5c1395930130857','2014-03-27 22:22:10',0,'DSC00782.JPG','d912c4c5604a4c81be709a2b1d2e8bc61395930130857','JPG',0,123304,'Photo',NULL,NULL,0,6),(22,'601af3750c7e4384941fbc2f2b5998241395930624082','2014-03-27 22:30:24',0,'DSC00749.JPG','5d21620d361a446791c9c982e224c4b81395930624082','JPG',0,214003,'Photo',NULL,NULL,0,8),(23,'1868ff9b943b4c90bed8618b07b6de841395930697054','2014-03-27 22:31:37',0,'DSC00748.JPG','2dde15d33e78404db3742233cdd757dc1395930697054','JPG',0,192743,'Photo',NULL,NULL,0,8),(24,'d82069a478e0443b931b4a09c048b84f1395931075307','2014-03-27 22:37:55',0,'DSC00748.JPG','d5f895b0d5364fa3961fa35d1ae6146b1395931075307','JPG',0,192743,'Photo',NULL,NULL,0,8),(26,'168a54ed5968441bb44d7795466578eb1395931471635','2014-03-27 22:44:31',0,'DSC00748.JPG','589b0c0dc2714354addeae6fa232f39a1395931471635','JPG',0,192743,'Photo',NULL,NULL,0,8),(27,'66bb2dd3ca834a3bb3290612cee6638f1395931472756','2014-03-27 22:44:32',0,'DSC00759.JPG','6c924a3adfbb40038bc8b67866a761401395931472756','JPG',0,110842,'Photo',NULL,NULL,0,8),(28,'a62b3a5020874191b7fb0617adf7efe11395931473376','2014-03-27 22:44:33',0,'DSC00778.JPG','963563dd3a2a4619a5d3dd9adddb3a081395931473376','JPG',0,142362,'Photo',NULL,NULL,0,8),(29,'a1fa5e4b36a64c719b93d25adb2966f61395931933588','2014-03-27 22:52:13',0,'DSC00891.JPG','025d54753df34ca5a8b8ed8ae342f4161395931933588','JPG',0,244809,'Photo',NULL,NULL,0,8),(30,'2a937b00bfed40ea98a80e3b587bbd5b1395934233403','2014-03-27 23:30:33',0,'DSC00749.JPG','e249322d051a428db390de4cf8b85f291395934233403','JPG',0,214003,'Photo',NULL,NULL,0,4),(31,'a677d94edad64069a8755fb04c96e2911395934233996','2014-03-27 23:30:33',0,'DSC00763.JPG','dfcdeba24ea740759d14824ff2d675191395934233996','JPG',0,180350,'Photo',NULL,NULL,0,4),(32,'4efea7bbf9134f7da927a8f321c596fa1395934235157','2014-03-27 23:30:35',0,'DSC00768.JPG','25eb1686b5d140ab8f133f1987d397481395934235157','JPG',0,205393,'Photo',NULL,NULL,0,4),(33,'4953f667115d4f46a4c2a1af9209bfbb1395934420169','2014-03-27 23:33:40',0,'DSCN0407.JPG','87ce330a1d6c497290d6876f314bb70c1395934420169','JPG',0,127807,'Photo',NULL,NULL,0,6),(34,'b0a52b8c65be4adaaf995fd599b8ed151395934421534','2014-03-27 23:33:41',0,'DSCN0412.JPG','00463edfe018467aa5b6483ee4c489251395934421535','JPG',0,125641,'Photo',NULL,NULL,0,6),(35,'720061231aa44d459ef55222de581e081395934528422','2014-03-27 23:35:28',0,'DSC00748.JPG','c2b6c2e77d1f45338fcb7b921d7c220b1395934528422','JPG',0,192743,'Photo',NULL,NULL,0,6),(36,'1318070a5c2644d6bb2dd46f4d7da8dc1395934529536','2014-03-27 23:35:29',0,'DSC00749.JPG','abdd8d779d2443e3b4e81a4be97c5bd71395934529536','JPG',0,214003,'Photo',NULL,NULL,0,6),(37,'11fa79dd41374824b1587ff8e297098f1395934530535','2014-03-27 23:35:30',0,'DSC00750.JPG','e596ce7c73ad4896a1ea08d01c1c2e851395934530535','JPG',0,135323,'Photo',NULL,NULL,0,6),(38,'b61273cff79b4945be234859b98061471395934532223','2014-03-27 23:35:32',0,'DSC00753.JPG','913138757b7c4d9ea1046f1978c6873d1395934532224','JPG',0,167603,'Photo',NULL,NULL,0,6),(39,'e7cbac8aa5ef4fb8a5875d972a6d4fcd1395934533487','2014-03-27 23:35:33',0,'DSC00758.JPG','6b6a37581182401b9bc267dc7c97c36d1395934533487','JPG',0,75487,'Photo',NULL,NULL,0,6),(40,'0b0e524170684839b253a99993600c741395934534566','2014-03-27 23:35:34',0,'DSC00759.JPG','3603ce094fb44287b9ceaed0901d00961395934534566','JPG',0,110842,'Photo',NULL,NULL,0,6),(41,'bded1e5f3e7b40a388ebdd75453d305c1395934535550','2014-03-27 23:35:35',0,'DSC00763.JPG','e89dd948121a4bd0a85b5bdf5fde7cd71395934535550','JPG',0,180350,'Photo',NULL,NULL,0,6),(42,'ee92e3ac8f7d41c795c5dcb751adb5501395934536572','2014-03-27 23:35:36',0,'DSC00768.JPG','969fd7e09e6b4b6b94171a831ba80cfc1395934536572','JPG',0,205393,'Photo',NULL,NULL,0,6),(43,'5aa25692cd2b4440b3e9f1a210d86e921395934538145','2014-03-27 23:35:38',0,'DSC00772.JPG','688f9a446da04e48a7e231b248d00d691395934538145','JPG',0,184168,'Photo',NULL,NULL,0,6),(44,'1d0c7ca954dc4fa4bedaf89b9e0ded841395934539736','2014-03-27 23:35:39',0,'DSC00775.JPG','b5e32660d83d481da0cfde3e3f449c471395934539736','JPG',0,144868,'Photo',NULL,NULL,0,6),(45,'e9e48215c2f64753825314f38bc9ce9d1395934897584','2014-03-27 23:41:37',0,'DSC00759.JPG','20292b73567a47cb960a55889f1b28161395934897584','JPG',0,110842,'Photo',NULL,NULL,0,9),(46,'25c09470910c471380e1492ba24b90cd1395934953315','2014-03-27 23:42:33',0,'DSC00775.JPG','4b4ba5e4b3a24de393b1b35ccb9aacab1395934953315','JPG',0,144868,'Photo',NULL,NULL,0,9),(47,'5b278eb34b9d4e7ebe18befdbfea382c1395935016108','2014-03-27 23:43:36',0,'DSC00748.JPG','34129a49378a4b3782a9d1ccb847f9fe1395935016108','JPG',0,203035,'Photo',NULL,NULL,0,9),(48,'67dfa22faa764636b2ea80b7c90163391395935041757','2014-03-27 23:44:01',0,'DSC00759.JPG','16f5e208c53448029dcbecda19f977721395935041757','JPG',0,95032,'Photo',NULL,NULL,0,9),(49,'762e1f87f91548aa8e22957b65fca2171395935042043','2014-03-27 23:44:02',0,'DSC00775.JPG','612edbaab2cf43929d0147d5aad13bd01395935042043','JPG',0,116924,'Photo',NULL,NULL,0,9),(50,'4e3ba570550f4012b1155b9ee5631bfd1395935125206','2014-03-27 23:45:25',0,'1.jpg','480faff60d394fa0a4056c7c7224665c1395935125206','jpg',0,724424,'Photo',NULL,NULL,0,9);
/*!40000 ALTER TABLE `gecko_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goal`
--

DROP TABLE IF EXISTS `goal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `goal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `archived` tinyint(1) DEFAULT '0',
  `goal_player_id` int(11) DEFAULT NULL,
  `assistant_player_id` int(11) DEFAULT NULL,
  `own_goal` tinyint(1) DEFAULT '0',
  `goal_time` int(11) DEFAULT NULL,
  `match_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `guid` (`guid`),
  KEY `guid_index` (`guid`),
  KEY `goal_player_id_index` (`goal_player_id`),
  KEY `match_id_index` (`match_id`),
  KEY `assistant_player_id_index` (`assistant_player_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goal`
--

LOCK TABLES `goal` WRITE;
/*!40000 ALTER TABLE `goal` DISABLE KEYS */;
INSERT INTO `goal` VALUES (1,'a9f1e06a5dcd436899e6fdce638801c51396770434920','2014-04-06 15:47:14',0,4,11,0,17,4),(2,'f662efcfacee4afb9cc8672245bcfa261396770434921','2014-04-06 15:47:14',0,14,NULL,0,67,4),(3,'1cdb5f374de14b2d9e0af5d9c59b8d2f1396772499279','2014-04-06 16:21:39',0,8,19,0,6,5),(4,'e183435fde50477793e9258dbcc79b7b1396772499279','2014-04-06 16:21:39',0,4,18,0,17,5);
/*!40000 ALTER TABLE `goal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `archived` tinyint(1) DEFAULT '0',
  `time_` datetime DEFAULT NULL,
  `content` text,
  `ip_address` varchar(255) DEFAULT NULL,
  `type_` varchar(255) DEFAULT NULL,
  `class_` varchar(255) DEFAULT 'Log',
  `who_id` int(11) DEFAULT NULL,
  `creator_id` int(11) DEFAULT NULL,
  `important` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `guid` (`guid`),
  KEY `guid_index` (`guid`),
  KEY `creator_id_index` (`creator_id`),
  KEY `who_id_index` (`who_id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
INSERT INTO `log` VALUES (1,'088993f3d53245278b43c409bd1b35f91395929847306','2014-03-27 22:17:27',0,'2014-03-27 22:17:27','Upload [1] photos','0:0:0:0:0:0:0:1','ALBUM','Log',NULL,NULL,0),(2,'3b6b03de20dd49809804daafdae646981395929994900','2014-03-27 22:19:54',0,'2014-03-27 22:19:54','Upload [1] photos','0:0:0:0:0:0:0:1','ALBUM','Log',NULL,NULL,0),(3,'b1f292e957fc46338ce3061e79118e541395930014548','2014-03-27 22:20:14',0,'2014-03-27 22:20:14','Upload [1] photos','0:0:0:0:0:0:0:1','ALBUM','Log',NULL,NULL,0),(4,'6c66dc1c56e4469cbb4aab1324140dd81395930086493','2014-03-27 22:21:26',0,'2014-03-27 22:21:26','Upload [1] photos','0:0:0:0:0:0:0:1','ALBUM','Log',NULL,NULL,0),(5,'e18b032f999647c1b6ca060580f153ef1395930130204','2014-03-27 22:22:10',0,'2014-03-27 22:22:10','Upload [1] photos','0:0:0:0:0:0:0:1','ALBUM','Log',NULL,NULL,0),(6,'54f26615a613416bbbe8013be15fe08a1395930130859','2014-03-27 22:22:10',0,'2014-03-27 22:22:10','Upload [1] photos','0:0:0:0:0:0:0:1','ALBUM','Log',NULL,NULL,0),(7,'d400cbed7efc4a1fbbb0910dcd02f2701395930624085','2014-03-27 22:30:24',0,'2014-03-27 22:30:24','Upload [1] photos','0:0:0:0:0:0:0:1','ALBUM','Log',NULL,NULL,0),(8,'881ad35d430e48848cf689725ad636291395930697057','2014-03-27 22:31:37',0,'2014-03-27 22:31:37','Upload [1] photos','0:0:0:0:0:0:0:1','ALBUM','Log',NULL,NULL,0),(9,'13125640918d470a94f5391ad4037b141395931075310','2014-03-27 22:37:55',0,'2014-03-27 22:37:55','Upload [1] photos','0:0:0:0:0:0:0:1','ALBUM','Log',NULL,NULL,0),(10,'b2e0a912ce554c838811cda7096ffcc91395931256737','2014-03-27 22:40:56',0,'2014-03-27 22:40:56','Upload [1] photos','0:0:0:0:0:0:0:1','ALBUM','Log',NULL,NULL,0),(11,'c351916f7ded40c796d4d27cbe4ee8e91395931471638','2014-03-27 22:44:31',0,'2014-03-27 22:44:31','Upload [1] photos','0:0:0:0:0:0:0:1','ALBUM','Log',NULL,NULL,0),(12,'2385a776283f49a9b0eb540d73b0e0661395931472758','2014-03-27 22:44:32',0,'2014-03-27 22:44:32','Upload [1] photos','0:0:0:0:0:0:0:1','ALBUM','Log',NULL,NULL,0),(13,'5ad3e35f00b74002a667c17d46fecbce1395931473379','2014-03-27 22:44:33',0,'2014-03-27 22:44:33','Upload [1] photos','0:0:0:0:0:0:0:1','ALBUM','Log',NULL,NULL,0),(14,'1581adfc140d497aa67c9258f3055b2e1395931933592','2014-03-27 22:52:13',0,'2014-03-27 22:52:13','Upload [1] photos','0:0:0:0:0:0:0:1','ALBUM','Log',NULL,NULL,0),(15,'8538f44a14c443959cb12fd6fd5bb9ab1395934233443','2014-03-27 23:30:33',0,'2014-03-27 23:30:33','Upload [1] photos','0:0:0:0:0:0:0:1','ALBUM','Log',NULL,NULL,0),(16,'c91e47a433b54e808aa0f10ac2124df21395934233999','2014-03-27 23:30:33',0,'2014-03-27 23:30:33','Upload [1] photos','0:0:0:0:0:0:0:1','ALBUM','Log',NULL,NULL,0),(17,'d45ef038aba740419c5eb163564798a61395934235163','2014-03-27 23:30:35',0,'2014-03-27 23:30:35','Upload [1] photos','0:0:0:0:0:0:0:1','ALBUM','Log',NULL,NULL,0),(18,'86316567140c48299ddc885f51d35d831395934420172','2014-03-27 23:33:40',0,'2014-03-27 23:33:40','Upload [1] photos','0:0:0:0:0:0:0:1','ALBUM','Log',NULL,NULL,0),(19,'2d0803e2e2e0489594b63d29c7d353e61395934421537','2014-03-27 23:33:41',0,'2014-03-27 23:33:41','Upload [1] photos','0:0:0:0:0:0:0:1','ALBUM','Log',NULL,NULL,0),(20,'dbe35482306d45438b8009049229cd341395934528425','2014-03-27 23:35:28',0,'2014-03-27 23:35:28','Upload [1] photos','0:0:0:0:0:0:0:1','ALBUM','Log',NULL,NULL,0),(21,'50002afb713545d9a8901972affe747f1395934529540','2014-03-27 23:35:29',0,'2014-03-27 23:35:29','Upload [1] photos','0:0:0:0:0:0:0:1','ALBUM','Log',NULL,NULL,0),(22,'6bfa5a29838e4823b8adb69f52aa102d1395934530537','2014-03-27 23:35:30',0,'2014-03-27 23:35:30','Upload [1] photos','0:0:0:0:0:0:0:1','ALBUM','Log',NULL,NULL,0),(23,'01609a55dfd74b3f82cb658ed27529741395934532226','2014-03-27 23:35:32',0,'2014-03-27 23:35:32','Upload [1] photos','0:0:0:0:0:0:0:1','ALBUM','Log',NULL,NULL,0),(24,'d2d475924fba491f868fe5734ce43daa1395934533489','2014-03-27 23:35:33',0,'2014-03-27 23:35:33','Upload [1] photos','0:0:0:0:0:0:0:1','ALBUM','Log',NULL,NULL,0),(25,'ecf1e9b4f027439b8768a08d1ac4287d1395934534568','2014-03-27 23:35:34',0,'2014-03-27 23:35:34','Upload [1] photos','0:0:0:0:0:0:0:1','ALBUM','Log',NULL,NULL,0),(26,'520f439bfadf414bbb3fee787a80d5fb1395934535552','2014-03-27 23:35:35',0,'2014-03-27 23:35:35','Upload [1] photos','0:0:0:0:0:0:0:1','ALBUM','Log',NULL,NULL,0),(27,'9943826d308f47adad060ef1899769c11395934536574','2014-03-27 23:35:36',0,'2014-03-27 23:35:36','Upload [1] photos','0:0:0:0:0:0:0:1','ALBUM','Log',NULL,NULL,0),(28,'0da33d06a10b45e2b7f10ed6005e93a31395934538147','2014-03-27 23:35:38',0,'2014-03-27 23:35:38','Upload [1] photos','0:0:0:0:0:0:0:1','ALBUM','Log',NULL,NULL,0),(29,'424be955f2984d11ae34b12b065a11901395934539739','2014-03-27 23:35:39',0,'2014-03-27 23:35:39','Upload [1] photos','0:0:0:0:0:0:0:1','ALBUM','Log',NULL,NULL,0),(30,'5846a86edc6f47bf992532841f1c2c431395934897588','2014-03-27 23:41:37',0,'2014-03-27 23:41:37','Create album [{id=9, archived=false, guid=\'6120c97a128645059b42bf0d657102951395934897293\', createTime=Thu Mar 27 23:41:37 CST 2014}]','0:0:0:0:0:0:0:1','ALBUM','Log',NULL,NULL,0),(31,'5cb69e8d98414ee9af841b924e71b8831395934953318','2014-03-27 23:42:33',0,'2014-03-27 23:42:33','Upload [1] photos','0:0:0:0:0:0:0:1','ALBUM','Log',NULL,NULL,0),(32,'f91befc2016043fb9edb355c2f6389b31395935016112','2014-03-27 23:43:36',0,'2014-03-27 23:43:36','Upload [1] photos','0:0:0:0:0:0:0:1','ALBUM','Log',NULL,NULL,0),(33,'c6a475a84b6043909c962c10177e78201395935041759','2014-03-27 23:44:01',0,'2014-03-27 23:44:01','Upload [1] photos','0:0:0:0:0:0:0:1','ALBUM','Log',NULL,NULL,0),(34,'8d6054e1942b4f53969107294380c7991395935042045','2014-03-27 23:44:02',0,'2014-03-27 23:44:02','Upload [1] photos','0:0:0:0:0:0:0:1','ALBUM','Log',NULL,NULL,0),(35,'03c52e6937d7419083c9038c9d18eee41395935125212','2014-03-27 23:45:25',0,'2014-03-27 23:45:25','Upload [1] photos','0:0:0:0:0:0:0:1','ALBUM','Log',NULL,NULL,0),(36,'45d4b42a312a4d59918b00a9458518111396541192601','2014-04-04 00:06:32',0,'2014-04-04 00:06:32','Cancel publish MatchNotice [{id=1, archived=false, guid=\'49e8f8e16c6d43b6b497e1719820875d1395417824898\', createTime=Sat Mar 22 00:03:44 CST 2014}]','0:0:0:0:0:0:0:1','MATCH_NOTICE','Log',NULL,NULL,0),(37,'6809030eab7647c68f8c1af5b1da2c8e1396759789210','2014-04-06 12:49:49',0,'2014-04-06 12:49:49','Create MatchNotice [{id=0, archived=false, guid=\'7cb4796ad1d34422a1499f58a9da61221396759789125\', createTime=Sun Apr 06 12:49:49 CST 2014}]','0:0:0:0:0:0:0:1','MATCH_NOTICE','Log',NULL,NULL,0),(38,'b0f27fc9f0024b2da3d5e0bfff81afee1396759797969','2014-04-06 12:49:57',0,'2014-04-06 12:49:57','Publish MatchNotice [{id=4, archived=false, guid=\'7cb4796ad1d34422a1499f58a9da61221396759789125\', createTime=Sun Apr 06 12:49:49 CST 2014}]','0:0:0:0:0:0:0:1','MATCH_NOTICE','Log',NULL,NULL,0),(39,'cd4d1a9a10da422d8cff774b396c2c4e1396761702711','2014-04-06 13:21:42',0,'2014-04-06 13:21:42','Create MatchNotice [{id=0, archived=false, guid=\'9f7b54533e264030a64a75bc07aeeca81396761702703\', createTime=Sun Apr 06 13:21:42 CST 2014}]','0:0:0:0:0:0:0:1','MATCH_NOTICE','Log',NULL,NULL,0),(40,'9ac8f64fd8c441deb4340373797d5cef1396761720325','2014-04-06 13:22:00',0,'2014-04-06 13:22:00','Publish MatchNotice [{id=5, archived=false, guid=\'9f7b54533e264030a64a75bc07aeeca81396761702703\', createTime=Sun Apr 06 13:21:42 CST 2014}]','0:0:0:0:0:0:0:1','MATCH_NOTICE','Log',NULL,NULL,0),(41,'dafdfad6429d4e46b34294011dbff9b91396770434926','2014-04-06 15:47:14',0,'2014-04-06 15:47:14','Add Match [{id=4, archived=false, guid=\'4899aa341e614bd6ade3bf29f5b190d51396770434650\', createTime=Sun Apr 06 15:47:14 CST 2014}]','0:0:0:0:0:0:0:1','MATCH','Log',NULL,NULL,0),(42,'273de66871674afbafe4294aa85c7c4d1396772499313','2014-04-06 16:21:39',0,'2014-04-06 16:21:39','Add Match [{id=5, archived=false, guid=\'a1eac55dc054420a8575320177a1b5c41396772499109\', createTime=Sun Apr 06 16:21:39 CST 2014}]','0:0:0:0:0:0:0:1','MATCH','Log',NULL,NULL,0);
/*!40000 ALTER TABLE `log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `match_`
--

DROP TABLE IF EXISTS `match_`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `match_` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `archived` tinyint(1) DEFAULT '0',
  `match_time` datetime DEFAULT NULL,
  `home_scores` int(11) DEFAULT NULL,
  `away_scores` int(11) DEFAULT NULL,
  `notice_id` int(11) DEFAULT NULL,
  `season_id` int(11) DEFAULT NULL,
  `opponent_id` int(11) DEFAULT NULL,
  `stadium_id` int(11) DEFAULT NULL,
  `creator_id` int(11) DEFAULT NULL,
  `remark` text,
  `futsal` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `guid` (`guid`),
  KEY `guid_index` (`guid`),
  KEY `notice_id_index` (`notice_id`),
  KEY `stadium_id_index` (`stadium_id`),
  KEY `season_id_index` (`season_id`),
  KEY `creator_id_index` (`creator_id`),
  KEY `opponent_id_index` (`opponent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `match_`
--

LOCK TABLES `match_` WRITE;
/*!40000 ALTER TABLE `match_` DISABLE KEYS */;
INSERT INTO `match_` VALUES (4,'4899aa341e614bd6ade3bf29f5b190d51396770434650','2014-04-06 15:47:14',0,'2014-04-06 15:30:00',2,1,4,3,1,1,NULL,'test',0),(5,'a1eac55dc054420a8575320177a1b5c41396772499109','2014-04-06 16:21:39',0,'2014-04-04 10:30:00',2,4,NULL,3,5,2,NULL,'test2',0);
/*!40000 ALTER TABLE `match_` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `match_notice`
--

DROP TABLE IF EXISTS `match_notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `match_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `archived` tinyint(1) DEFAULT '0',
  `time_` datetime DEFAULT NULL,
  `stadium_id` int(11) DEFAULT NULL,
  `opponent_id` int(11) DEFAULT NULL,
  `creator_id` int(11) DEFAULT NULL,
  `remark` text,
  `pending` tinyint(1) DEFAULT '0',
  `match_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `guid` (`guid`),
  KEY `guid_index` (`guid`),
  KEY `stadium_id_index` (`stadium_id`),
  KEY `creator_id_index` (`creator_id`),
  KEY `opponent_id_index` (`opponent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `match_notice`
--

LOCK TABLES `match_notice` WRITE;
/*!40000 ALTER TABLE `match_notice` DISABLE KEYS */;
INSERT INTO `match_notice` VALUES (1,'49e8f8e16c6d43b6b497e1719820875d1395417824898','2014-03-22 00:03:44',0,'2014-03-23 10:00:00',1,5,NULL,'穿新球衣. 多传球，少大脚。要争先恐后接球！\r\n',0,NULL),(2,'2728cba7cfab4091b60decd27072b1991395418565360','2014-03-22 00:16:05',0,'2014-03-22 00:10:00',2,2,NULL,'test',0,NULL),(3,'dcfd90c8320b4984877513ba4bca3e1d1395420446599','2014-03-22 00:47:26',1,'2014-03-28 10:30:00',3,2,NULL,'okokk',0,NULL),(4,'7cb4796ad1d34422a1499f58a9da61221396759789125','2014-04-06 12:49:49',0,'2014-04-06 15:30:00',1,1,NULL,'新球衣, ',0,0),(5,'9f7b54533e264030a64a75bc07aeeca81396761702703','2014-04-06 13:21:42',0,'2014-04-10 14:20:00',3,4,NULL,'test',1,NULL);
/*!40000 ALTER TABLE `match_notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `match_player`
--

DROP TABLE IF EXISTS `match_player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `match_player` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `archived` tinyint(1) DEFAULT '0',
  `match_id` int(11) DEFAULT NULL,
  `player_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `guid` (`guid`),
  KEY `guid_index` (`guid`),
  KEY `match_id_index` (`match_id`),
  KEY `player_id_index` (`player_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `match_player`
--

LOCK TABLES `match_player` WRITE;
/*!40000 ALTER TABLE `match_player` DISABLE KEYS */;
INSERT INTO `match_player` VALUES (1,'a59ef7dec6f740beb271d25a949236511396770434849','2014-04-06 15:47:14',0,4,1),(2,'9281ec105b3c468fa84b06ac337f3df01396770434849','2014-04-06 15:47:14',0,4,2),(3,'8eb1208d79a14dc6ad1bbf87e5d245cd1396770434849','2014-04-06 15:47:14',0,4,3),(4,'3508aaf4d61f445b893e64ba8bdc0c0e1396770434849','2014-04-06 15:47:14',0,4,4),(5,'c363b55438794e3aa2fbd250c1331c691396770434849','2014-04-06 15:47:14',0,4,5),(6,'0e86647a3efa48a79d2e72556dd3150a1396770434849','2014-04-06 15:47:14',0,4,7),(7,'b61a9381804343fabd914b6c8473713d1396770434849','2014-04-06 15:47:14',0,4,8),(8,'5c8a000b128542a6ab0a768b922fb7a11396770434849','2014-04-06 15:47:14',0,4,10),(9,'f42a30c4fdc8454fa202f1249453d8641396770434849','2014-04-06 15:47:14',0,4,11),(10,'c026d946e688454ba34e3ba107d870d01396770434849','2014-04-06 15:47:14',0,4,13),(11,'be61647cb0184dba99386fcd5508adcf1396770434849','2014-04-06 15:47:14',0,4,15),(12,'2982bf51c2ed47dcbe1f4f38d56ccced1396770434849','2014-04-06 15:47:14',0,4,23),(13,'88f70f9c7cac41dbaa871af5b4f427291396770434849','2014-04-06 15:47:14',0,4,25),(14,'775495cbcacb4808ae207bad870970961396772499268','2014-04-06 16:21:39',0,5,1),(15,'f130691478684ab59ff3b9e3f3dd5abf1396772499268','2014-04-06 16:21:39',0,5,3),(16,'5449f9dce6b24e028574ca54e0f49f681396772499268','2014-04-06 16:21:39',0,5,4),(17,'bbe3875b398f44599d215461882eaa1e1396772499268','2014-04-06 16:21:39',0,5,5),(18,'8fff153f5d9148149a8d7fcca712ccef1396772499268','2014-04-06 16:21:39',0,5,21),(19,'c32e325497dc4f65b96ea9fa5c5990bd1396772499268','2014-04-06 16:21:39',0,5,22);
/*!40000 ALTER TABLE `match_player` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `player`
--

DROP TABLE IF EXISTS `player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `player` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `archived` tinyint(1) DEFAULT '0',
  `captain` tinyint(1) DEFAULT '0',
  `full_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `entry_date` date DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `position_` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `guid` (`guid`),
  KEY `guid_index` (`guid`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player`
--

LOCK TABLES `player` WRITE;
/*!40000 ALTER TABLE `player` DISABLE KEYS */;
INSERT INTO `player` VALUES (1,'784c88b8abe94a7ab9b0da5b14ced8e31395241303205','2014-03-19 23:01:43',0,0,'李胜钊','shengzhao@shengzhaoli.com','13308231107','2011-06-19','1987-01-18','SW'),(2,'2449ec5f2654460b89587e618f32c9211395241482990','2014-03-19 23:04:42',0,0,'刘杨','kuangyawen@wdcy.cc','028-6553686723','2009-01-16','2014-03-13','LB'),(3,'3d5eea84753547cbba2c92d8bc1696a11395242309493','2014-03-19 23:18:29',0,1,'郑醒','','',NULL,'2014-02-05','AMF'),(4,'26ea0ef92a0943ed8fd5b40ad03667041395242583285','2014-03-19 23:23:03',0,0,'吴东','','',NULL,NULL,'CF'),(5,'222aa5d4dae24120a58d5b84ea3f99de1395242616871','2014-03-19 23:23:36',0,0,'汤坤','','',NULL,NULL,'LW'),(6,'70865276720a40ca870c16bcfc4227901395242644098','2014-03-19 23:24:04',0,0,'曾智敏','','','2010-02-10',NULL,'GK'),(7,'df998c21d6d8490cbfc1c2e138f187801395242718779','2014-03-19 23:25:18',0,0,'代力韬','','','2011-07-15',NULL,'CB'),(8,'8a57ab953c6c4ed7b551cc617a123ce41395242764204','2014-03-19 23:26:04',0,0,'夏竹','','',NULL,NULL,'RB'),(9,'602bf166b9e1426eb4f8244b6d0370741395243084494','2014-03-19 23:31:24',0,0,'小蒋','','',NULL,NULL,'LW'),(10,'54c014ca2f424de6bb2c6e54eb68681a1395243105748','2014-03-19 23:31:45',0,0,'梁晓飞','','',NULL,NULL,'DMF'),(11,'abf7326d9aff430b9c1f1842c012e80a1395243123433','2014-03-19 23:32:03',0,0,'王国强','','',NULL,NULL,'DMF'),(12,'ad95539b5f3443bbb90680bcc53c88ff1395243269742','2014-03-19 23:34:29',1,0,'谭锐','','',NULL,NULL,'DMF'),(13,'3ca95a4b52cf44b192d385bce9c68b961395243287997','2014-03-19 23:34:47',0,0,'刘鑫','','13076060555',NULL,NULL,'CB'),(14,'52408c8dfbb24ecea69fadf3c5ba2b0e1395243324086','2014-03-19 23:35:24',0,0,'查尔斯','','',NULL,NULL,'RW'),(15,'471461dead954cd0971e075f91d9f9f41395243925569','2014-03-19 23:45:25',0,0,'贺翔','dengrui@wdcy.cc','',NULL,NULL,'RB'),(16,'c8cc8c560b5047e8a98ecdd183cb91651395407496386','2014-03-21 21:11:36',1,0,'Li','wli@hy.com','13308231107','2014-03-07','2014-02-26','CB'),(17,'facb550aa0d3425c8ef9f8ebe4e3eb7e1395468959072','2014-03-22 14:15:59',0,0,'付安','asdfsdf@sd.com','12323444','2014-02-25','2014-02-25','DMF'),(18,'8929116fe83c4329a89b93afaaab71f51395468993060','2014-03-22 14:16:33',0,0,'高博','','',NULL,NULL,'RB'),(19,'0af932498376420697743f64a91547471395469013127','2014-03-22 14:16:53',0,0,'Jason','','',NULL,NULL,'DMF'),(20,'f3af5464332c4b289410c4e513e5fd321395469034757','2014-03-22 14:17:14',0,0,'刘唱','','',NULL,NULL,'RB'),(21,'f581ebadcbaf40dc90b191153029649f1395469054155','2014-03-22 14:17:34',0,0,'李少山','','',NULL,NULL,'CF'),(22,'720076adb140494d92000c449e364d431395469072583','2014-03-22 14:17:52',0,0,'李涛','','',NULL,NULL,'AMF'),(23,'449e9722bc3c4fa59e939c651aa29d711395469124168','2014-03-22 14:18:44',0,0,'覃锐','','',NULL,NULL,'DMF'),(24,'2eb689735e96441e989acbe02b9bc4bc1395469157769','2014-03-22 14:19:17',0,0,'王敏','','',NULL,NULL,'LW'),(25,'2a817e1ed23a4e8cbf4c2133573391401395469180419','2014-03-22 14:19:40',0,0,'谢佳','','',NULL,NULL,'CB');
/*!40000 ALTER TABLE `player` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `season`
--

DROP TABLE IF EXISTS `season`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `season` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `archived` tinyint(1) DEFAULT '0',
  `name_` varchar(255) DEFAULT NULL,
  `start` date DEFAULT NULL,
  `end_` date DEFAULT NULL,
  `remark` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `guid` (`guid`),
  KEY `guid_index` (`guid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `season`
--

LOCK TABLES `season` WRITE;
/*!40000 ALTER TABLE `season` DISABLE KEYS */;
INSERT INTO `season` VALUES (1,'d24421e6c20c4416b0e0e88606e7892a1394940385751','2014-03-16 11:26:25',0,'2010 赛季','2010-01-01','2010-12-31','vb'),(2,'4976c10292074f5f90f247ba48d997081394940446493','2014-03-16 11:27:26',0,'2011 赛季','2011-01-01','2011-12-31','2011年的.'),(3,'6cbed058e1f342209c4a04e9010e88cf1394941233877','2014-03-16 11:40:33',0,'2014 赛季','2014-01-01','2014-12-31','多传球,少大脚.'),(4,'bb0e4e3affb6481ebe2a97f87ee9e98d1394941286848','2014-03-16 11:41:26',0,'2015 赛季','2015-01-01','2015-12-31','cheer up我们的进步之年, 必须雄起.也是.'),(5,'3de4c86c4e1e4a27ab5094aeacc0a91c1394941656567','2014-03-16 11:47:36',1,'afssdf','2014-03-13','2014-03-26','asfs结束日期建议为每年的最后一天, 格式: yyyy-MM-dd.写点对这赛季的展望或总结等.开始日期建议为每年的第一天, 格式: yyyy-MM-dd.\r\n\r\n结束日期'),(6,'cb145b3e88804f64b7a3a9290e0b39b11394973283935','2014-03-16 20:34:43',1,'asfeweeee','2014-02-26','2014-03-22','asfsfd');
/*!40000 ALTER TABLE `season` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stadium`
--

DROP TABLE IF EXISTS `stadium`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stadium` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `archived` tinyint(1) DEFAULT '0',
  `name_` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `contact` varchar(255) DEFAULT NULL,
  `home_stadium` tinyint(1) DEFAULT '0',
  `remark` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `guid` (`guid`),
  KEY `guid_index` (`guid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stadium`
--

LOCK TABLES `stadium` WRITE;
/*!40000 ALTER TABLE `stadium` DISABLE KEYS */;
INSERT INTO `stadium` VALUES (1,'0ff5cc3703514b27ac9f992b1868d84e1395152530457','2014-03-18 22:22:10',0,'太平寺2号场地','大件路三环路旁边','老唐',1,'Home stadium098'),(2,'4f256dde97cb47e39c1adb1d4499974a1395152658862','2014-03-18 22:24:18',0,'太平寺3号场地','大件路三环路旁边','老唐',0,'xxx'),(3,'1bc8c6f8ba3d40c1922282db5315fc8e1395152770070','2014-03-18 22:26:10',0,'川大磨子桥球场1','一环路, 红瓦寺1','xxxx1',0,'xxxx1'),(4,'ab598e49e65440e0bf13b7567aa9440b1395153283338','2014-03-18 22:34:43',1,'asdf','ssfdsdffdssdf','asdfds',0,'sfsdfasddsfdsf'),(5,'3d238aea004b4a13bc58617e14b06ba81395468802320','2014-03-22 14:13:22',0,'太平寺4号场地','大件路三环路旁边','老唐',0,'xxxx'),(6,'9efad1098e5349edae682922e40c2c241395468839073','2014-03-22 14:13:59',0,'民院球场','一环路南三段','李四 13300231117(手机) 87658767(QQ号)',0,'xxx');
/*!40000 ALTER TABLE `stadium` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_`
--

DROP TABLE IF EXISTS `user_`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `archived` tinyint(1) DEFAULT '0',
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `user_role` varchar(255) NOT NULL,
  `type_` varchar(255) NOT NULL DEFAULT 'User',
  `status` varchar(255) NOT NULL DEFAULT 'ENABLED',
  `default_user` tinyint(1) DEFAULT '0',
  `last_login_time` datetime DEFAULT NULL,
  `others` text,
  `player_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `guid` (`guid`),
  UNIQUE KEY `username` (`username`),
  KEY `guid_index` (`guid`),
  KEY `player_id_index` (`player_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_`
--

LOCK TABLES `user_` WRITE;
/*!40000 ALTER TABLE `user_` DISABLE KEYS */;
INSERT INTO `user_` VALUES (21,'29f6004fb1b0466f9572b02bf2ac1be8','2014-03-18 23:08:52',0,'ed30a0f893f2fc70733490d9b6ec202f','admin','ADMIN','User','ENABLED',1,NULL,NULL,NULL);
/*!40000 ALTER TABLE `user_` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-04-06 22:24:34
