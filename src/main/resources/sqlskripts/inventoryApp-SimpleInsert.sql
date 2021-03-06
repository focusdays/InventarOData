CREATE DATABASE  IF NOT EXISTS `inventory2` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `inventory2`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost   Database: inventory2
-- ------------------------------------------------------
-- Server version	5.6.14

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
-- Table structure for table `Commodity`
--

DROP TABLE IF EXISTS `Commodity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Commodity` (
  `commodityID` int(11) NOT NULL AUTO_INCREMENT,
  `commodityTitle` varchar(255) DEFAULT NULL,
  `commodityType` int(2) DEFAULT NULL,
  `roomType` int(2) DEFAULT NULL,
  `commodityPrice` decimal(10,2) DEFAULT NULL,
  `commodityPicture` varchar(255) NOT NULL,
  `mutationTimestamp` datetime NOT NULL,
  `commodity_commodityID` int(11) DEFAULT NULL,
  PRIMARY KEY (`commodityID`),
  UNIQUE KEY `commodityID_UNIQUE` (`commodityID`)
  /*, KEY `fk_commodity_commodity1_idx` (`commodity_commodityID`),
  CONSTRAINT `fk_commodity_commodity1` FOREIGN KEY (`commodity_commodityID`) REFERENCES `Commodity` (`commodityID`) ON DELETE NO ACTION ON UPDATE NO ACTION*/
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Commodity`
--

LOCK TABLES `Commodity` WRITE;
/*!40000 ALTER TABLE `Commodity` DISABLE KEYS */;
INSERT INTO `Commodity` VALUES (1,'Chair Toro Rosso',1,NULL,1234.99,'chair.jpg','2014-06-22 00:01:00',3),(2,'Tisch Yellow Duck',1,NULL,2222.00,'duck.jpg','2014-06-22 00:01:00',3),(3,'Esszimmer Max',NULL,1,0.00,'esszimmer.jpg','2014-06-22 00:01:00',NULL);
/*!40000 ALTER TABLE `Commodity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Device`
--

DROP TABLE IF EXISTS `Device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Device` (
  `deviceID` int(11) NOT NULL,
  `deviceName` varchar(255) DEFAULT NULL,
  `deviceType` varchar(255) DEFAULT NULL,
  `person_personID` varchar(200) NOT NULL,
  PRIMARY KEY (`deviceID`,`person_personID`),
  UNIQUE KEY `deviceID_UNIQUE` (`deviceID`),
  KEY `fk_device_person_idx` (`person_personID`),
  CONSTRAINT `fk_device_person` FOREIGN KEY (`person_personID`) REFERENCES `Person` (`personID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Device`
--

LOCK TABLES `Device` WRITE;
/*!40000 ALTER TABLE `Device` DISABLE KEYS */;
INSERT INTO `Device` VALUES (1,'Samsung S5','Android','max@muster.ch'),(2,'IPhone 5','iOS','max@dachs.ch');
/*!40000 ALTER TABLE `Device` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Inventory`
--

DROP TABLE IF EXISTS `Inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Inventory` (
  `inventoryID` int(11) NOT NULL AUTO_INCREMENT,
  `inventoryTitle` varchar(255) NOT NULL,
  `inventoryTotalPrice` decimal(10,2) NOT NULL,
  `inventoryType` int(2) NOT NULL,
  `language` int(2) NOT NULL,
  `mutationTimestamp` datetime NOT NULL,
  `currency` int(2) NOT NULL,
  `person_personID` varchar(200) NOT NULL,
  `location_locationID` int(11) NOT NULL,
  PRIMARY KEY (`inventoryID`,`person_personID`,`location_locationID`),
  UNIQUE KEY `inventoryID_UNIQUE` (`inventoryID`),
  KEY `fk_inventory_person1_idx` (`person_personID`),
  KEY `fk_inventory_location1_idx` (`location_locationID`),
  CONSTRAINT `fk_inventory_person1` FOREIGN KEY (`person_personID`) REFERENCES `Person` (`personID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_inventory_location1` FOREIGN KEY (`location_locationID`) REFERENCES `Location` (`locationID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Inventory`
--

LOCK TABLES `Inventory` WRITE;
/*!40000 ALTER TABLE `Inventory` DISABLE KEYS */;
INSERT INTO `Inventory` VALUES (1,'Hausrat von Muster',0.00,1,1,'2014-06-22 00:01:00',1,'max@muster.ch',1),(2,'Uhren von Dachs',10000.00,2,1,'2014-06-22 00:01:00',1,'max@dachs.ch',2);
/*!40000 ALTER TABLE `Inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventory_has_commodity`
--

DROP TABLE IF EXISTS `inventory_has_commodity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inventory_has_commodity` (
  `inventory_inventoryID` int(11) NOT NULL,
  `commodity_commodityID` int(11) NOT NULL,
  KEY `fk_inventory_has_commodity_commodity1_idx` (`commodity_commodityID`),
  KEY `fk_inventory_has_commodity_inventory1_idx` (`inventory_inventoryID`),
  CONSTRAINT `fk_inventory_has_commodity_inventory1` FOREIGN KEY (`inventory_inventoryID`) REFERENCES `Inventory` (`inventoryID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_inventory_has_commodity_commodity1` FOREIGN KEY (`commodity_commodityID`) REFERENCES `Commodity` (`commodityID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory_has_commodity`
--

LOCK TABLES `inventory_has_commodity` WRITE;
/*!40000 ALTER TABLE `inventory_has_commodity` DISABLE KEYS */;
INSERT INTO `inventory_has_commodity` VALUES (1,1),(1,2),(1,3);
/*!40000 ALTER TABLE `inventory_has_commodity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Location`
--

DROP TABLE IF EXISTS `Location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Location` (
  `locationID` int(11) NOT NULL,
  `locationType` int(2) NOT NULL,
  `locationTitle` varchar(255) DEFAULT NULL,
  `geoLocation_latitude` decimal(10,6) NOT NULL,
  `geoLocation_longitude` decimal(10,6) NOT NULL,
  `geoLocation_accuracy` decimal(6,2) DEFAULT NULL,
  `locationAddress_land` varchar(255) DEFAULT NULL,
  `locationAddress_postalCodeCity` varchar(5) DEFAULT NULL,
  `locationAddress_streetHouseNr` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`locationID`),
  UNIQUE KEY `locationID_UNIQUE` (`locationID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Location`
--

LOCK TABLES `Location` WRITE;
/*!40000 ALTER TABLE `Location` DISABLE KEYS */;
INSERT INTO `Location` VALUES (1,1,'Muster Haus',1234.123456,9999.999999,NULL,NULL,NULL,NULL),(2,3,'Hölle von Dachs',7890.999999,9999.000000,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `Location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Person`
--

DROP TABLE IF EXISTS `Person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Person` (
  `personID` varchar(200) NOT NULL,
  `totalPriceInventories` decimal(10,2) NOT NULL,
  `personName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`personID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Person`
--

LOCK TABLES `Person` WRITE;
/*!40000 ALTER TABLE `Person` DISABLE KEYS */;
INSERT INTO `Person` VALUES ('max@dachs.ch',0.00,'Max der Dachs'),('max@muster.ch',0.00,'Max Muster');
/*!40000 ALTER TABLE `Person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person_has_location`
--

DROP TABLE IF EXISTS `person_has_location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person_has_location` (
  `person_personID` varchar(200) NOT NULL,
  `location_locationID` int(11) NOT NULL,
  KEY `fk_person_has_location_location1_idx` (`location_locationID`),
  KEY `fk_person_has_location_person1_idx` (`person_personID`),
  CONSTRAINT `fk_person_has_location_person1` FOREIGN KEY (`person_personID`) REFERENCES `person` (`personID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_person_has_location_location1` FOREIGN KEY (`location_locationID`) REFERENCES `location` (`locationID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person_has_location`
--

LOCK TABLES `person_has_location` WRITE;
/*!40000 ALTER TABLE `person_has_location` DISABLE KEYS */;
INSERT INTO `person_has_location` VALUES ('max@muster.ch',1),('max@dachs.ch',2);
/*!40000 ALTER TABLE `person_has_location` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-06-22 15:44:47
