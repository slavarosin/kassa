-- MySQL dump 10.11
--
-- Host: mysql34.1gb.ru    Database: gb_x_goodk97b
-- ------------------------------------------------------
-- Server version	5.0.60-log

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
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `text` varchar(256) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (7,'покупка товара Sanitex'),(8,'транспорт DHL'),(10,'транспорт HRX'),(11,'зарплата Сане'),(12,'премия Сане'),(13,'упаковочные материалы'),(14,'зарплата Игорю'),(15,'премия Евгению'),(16,'бензин '),(17,'расход на передачу денег'),(18,'продажа товара'),(19,'покупка тары'),(20,'возврат тары'),(21,'аренда почтового ящика');
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `companies`
--

DROP TABLE IF EXISTS `companies`;
CREATE TABLE `companies` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `name` varchar(32) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `companies`
--

LOCK TABLES `companies` WRITE;
/*!40000 ALTER TABLE `companies` DISABLE KEYS */;
/*!40000 ALTER TABLE `companies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `currencies`
--

DROP TABLE IF EXISTS `currencies`;
CREATE TABLE `currencies` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `name` varchar(20) NOT NULL,
  `sign` char(3) default NULL,
  `kassa` tinyint(1) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `currencies`
--

LOCK TABLES `currencies` WRITE;
/*!40000 ALTER TABLE `currencies` DISABLE KEYS */;
INSERT INTO `currencies` VALUES (20,'лит','LTL',1),(21,'фунт','GBP',0),(22,'крона','EEK',0);
/*!40000 ALTER TABLE `currencies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kassa`
--

DROP TABLE IF EXISTS `kassa`;
CREATE TABLE `kassa` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `amount` decimal(10,2) NOT NULL,
  `rate` decimal(10,5) NOT NULL,
  `currency_from` int(10) unsigned NOT NULL,
  `invoice_type` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `createdBy` int(10) unsigned NOT NULL,
  `comments` text,
  `creditor` varchar(20) default NULL,
  `user_comments` text,
  `currency_to` int(10) unsigned NOT NULL,
  `company` varchar(32) default NULL,
  PRIMARY KEY  (`id`),
  KEY `createdBy` (`createdBy`),
  KEY `kassa_currency_from` (`currency_from`),
  KEY `kassa_currency_to` (`currency_to`),
  CONSTRAINT `kassa_currency_from` FOREIGN KEY (`currency_from`) REFERENCES `currencies` (`id`),
  CONSTRAINT `kassa_currency_to` FOREIGN KEY (`currency_to`) REFERENCES `currencies` (`id`),
  CONSTRAINT `kassa_user` FOREIGN KEY (`createdBy`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `kassa`
--

LOCK TABLES `kassa` WRITE;
/*!40000 ALTER TABLE `kassa` DISABLE KEYS */;
INSERT INTO `kassa` VALUES (61,'50000.00','1.00000',20,3,'2008-10-16 00:00:00',18,NULL,'alex',NULL,20,NULL),(62,'8820.00','4.26000',21,1,'2008-10-28 00:00:00',17,'продажа товара',NULL,NULL,20,NULL),(63,'17913.00','4.25000',21,1,'2008-11-04 00:00:00',17,'продажа товара',NULL,NULL,20,NULL),(64,'670.00','1.00000',20,1,'2008-11-04 00:00:00',17,'продажа товара',NULL,NULL,20,NULL),(65,'17640.00','4.22000',21,1,'2008-11-20 00:00:00',17,NULL,NULL,NULL,20,NULL),(66,'17640.00','4.06000',21,1,'2008-11-20 00:00:00',17,'продажа товара',NULL,NULL,20,NULL),(67,'3640.00','4.20000',21,1,'2008-11-09 00:00:00',17,'продажа товара',NULL,NULL,20,NULL),(68,'14000.00','4.06000',21,1,'2008-11-20 00:00:00',17,'продажа товара',NULL,NULL,20,NULL),(69,'7000.00','4.06400',21,1,'2008-11-20 00:00:00',17,'продажа товара',NULL,NULL,20,NULL),(70,'10640.00','4.10000',21,1,'2008-11-20 00:00:00',17,'продажа товара',NULL,NULL,20,NULL),(71,'17640.00','4.06400',21,1,'2008-11-20 00:00:00',17,'продажа товара',NULL,NULL,20,NULL),(72,'10846.00','1.00000',20,3,'2008-10-31 00:00:00',17,NULL,'alex',NULL,20,NULL),(73,'10846.00','1.00000',20,3,'2008-10-31 00:00:00',17,NULL,'Sarsas',NULL,20,NULL),(74,'10846.00','1.00000',20,3,'2008-11-20 00:00:00',17,NULL,'Danila',NULL,20,NULL),(75,'24520.00','1.00000',20,2,'2008-11-20 00:00:00',18,'raz 4 ',NULL,NULL,20,NULL),(76,'51070.00','1.00000',20,2,'2008-11-21 00:00:00',18,'raz 5',NULL,NULL,20,NULL),(77,'46870.00','1.00000',20,2,'2008-11-21 00:00:00',18,'raz 6',NULL,NULL,20,NULL),(78,'49300.00','1.00000',20,2,'2008-11-21 00:00:00',18,NULL,NULL,NULL,20,NULL),(79,'57020.00','1.00000',20,2,'2008-11-21 00:00:00',18,NULL,NULL,NULL,20,NULL),(80,'47000.00','1.00000',20,2,'2008-11-21 00:00:00',18,'raz 9',NULL,NULL,20,NULL),(81,'48050.00','1.00000',20,2,'2008-11-21 00:00:00',18,'raz 10',NULL,NULL,20,NULL),(82,'8190.00','4.10000',21,3,'2008-11-21 00:00:00',16,NULL,'Gedrius',NULL,20,NULL),(83,'1280.00','0.22124',22,2,'2008-11-24 00:00:00',17,'зарплата Игорю',NULL,'c 25.10 по 24.11',20,NULL),(84,'750.00','0.22124',22,2,'2008-11-24 00:00:00',17,'зарплата Игорю',NULL,NULL,20,NULL),(85,'2714.00','0.22124',22,2,'2008-10-31 00:00:00',17,'транспорт HRX',NULL,NULL,20,NULL),(86,'2714.00','0.22124',22,2,'2008-10-31 00:00:00',17,'транспорт HRX',NULL,NULL,20,NULL),(87,'11253.00','0.22124',22,2,'2008-10-31 00:00:00',17,'транспорт DHL',NULL,NULL,20,NULL),(88,'13960.00','0.22124',22,2,'2008-10-31 00:00:00',17,'транспорт DHL',NULL,NULL,20,NULL),(89,'13960.00','0.22124',22,2,'2008-10-31 00:00:00',17,'транспорт DHL',NULL,NULL,20,NULL),(90,'2714.00','0.22124',22,2,'2008-11-05 00:00:00',17,'транспорт HRX',NULL,NULL,20,NULL),(91,'2714.00','0.22124',22,2,'2008-11-06 00:00:00',17,'транспорт HRX',NULL,NULL,20,NULL),(92,'13616.00','0.22124',22,2,'2008-11-08 00:00:00',17,'транспорт HRX',NULL,NULL,20,NULL),(93,'13616.00','0.22124',22,2,'2008-11-08 00:00:00',17,'транспорт HRX',NULL,NULL,20,NULL),(94,'13444.00','0.22124',22,2,'2008-11-03 00:00:00',17,'транспорт DHL',NULL,NULL,20,NULL),(95,'13782.00','0.22124',22,2,'2008-11-06 00:00:00',17,'транспорт DHL',NULL,NULL,20,NULL),(96,'2714.00','0.22124',22,2,'2008-11-11 00:00:00',17,'транспорт HRX',NULL,NULL,20,NULL),(97,'12.73','0.22124',22,2,'2008-11-11 00:00:00',17,'транспорт DHL',NULL,NULL,20,NULL),(98,'13782.00','0.22124',22,2,'2008-11-14 00:00:00',17,'транспорт DHL',NULL,NULL,20,NULL),(99,'1357.00','0.22124',22,2,'2008-11-14 00:00:00',17,'транспорт HRX',NULL,NULL,20,NULL),(100,'2714.00','0.22124',22,2,'2008-11-19 00:00:00',17,'транспорт DHL',NULL,NULL,20,NULL),(101,'500.00','0.22124',22,2,'2008-11-24 00:00:00',17,'аренда почтового ящика',NULL,NULL,20,NULL),(102,'13444.00','0.22124',22,2,'2008-11-21 00:00:00',17,'транспорт DHL',NULL,NULL,20,NULL),(103,'13444.00','0.22124',22,2,'2008-11-19 00:00:00',17,'транспорт DHL',NULL,NULL,20,NULL),(104,'5200.00','0.22124',22,2,'2008-11-24 00:00:00',17,NULL,NULL,'создание программы \"Касса\"',20,NULL),(105,'3000.00','0.22124',22,2,'2008-11-24 00:00:00',17,NULL,NULL,'прочие административные расходы',20,NULL),(106,'215.00','1.00000',20,2,'2008-11-24 00:00:00',17,NULL,NULL,'оплата хостинга для кассы',20,NULL);
/*!40000 ALTER TABLE `kassa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rates`
--

DROP TABLE IF EXISTS `rates`;
CREATE TABLE `rates` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `currency1` int(10) unsigned default NULL,
  `currency2` int(10) unsigned default NULL,
  `rate` decimal(10,5) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `rates`
--

LOCK TABLES `rates` WRITE;
/*!40000 ALTER TABLE `rates` DISABLE KEYS */;
INSERT INTO `rates` VALUES (21,20,21,'0.24606'),(22,21,20,'4.06400'),(23,20,22,'4.52000'),(24,21,22,'18.56020'),(25,22,20,'0.22124'),(26,22,21,'0.05388');
/*!40000 ALTER TABLE `rates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trade`
--

DROP TABLE IF EXISTS `trade`;
CREATE TABLE `trade` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `amount` int(10) unsigned NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `createdBy` int(10) unsigned NOT NULL,
  `milestone` int(10) unsigned NOT NULL,
  `date` datetime NOT NULL,
  `received` tinyint(1) NOT NULL,
  `trackingID` varchar(24) default NULL,
  `company` varchar(32) default NULL,
  PRIMARY KEY  (`id`),
  KEY `createdBy` (`createdBy`),
  KEY `trade_ibfk_2` (`milestone`),
  CONSTRAINT `trade_ibfk_1` FOREIGN KEY (`createdBy`) REFERENCES `users` (`id`),
  CONSTRAINT `trade_ibfk_2` FOREIGN KEY (`milestone`) REFERENCES `trade_milestones` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `trade`
--

LOCK TABLES `trade` WRITE;
/*!40000 ALTER TABLE `trade` DISABLE KEYS */;
/*!40000 ALTER TABLE `trade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trade_milestones`
--

DROP TABLE IF EXISTS `trade_milestones`;
CREATE TABLE `trade_milestones` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `date` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `trade_milestones`
--

LOCK TABLES `trade_milestones` WRITE;
/*!40000 ALTER TABLE `trade_milestones` DISABLE KEYS */;
INSERT INTO `trade_milestones` VALUES (5,NULL);
/*!40000 ALTER TABLE `trade_milestones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `login` varchar(20) default NULL,
  `passwd` varchar(32) default NULL,
  `role` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (16,'admin','99b3b060154898840f0ebdfb46ec78f',1),(17,'alex','fc84baaf21e7ee1f0bad13d427d98b83',0),(18,'saras','bc0a8c2b34f3e33c0af1c1b298d253ee',0),(19,'danila.uk','4867f055c9882f3a68c6dcb81d676e2e',0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2008-12-05  6:07:10
