-- phpMyAdmin SQL Dump
-- version 4.0.5
-- http://www.phpmyadmin.net
--
-- Host: 127.2.71.130:3306
-- Generation Time: Jun 11, 2014 at 11:32 PM
-- Server version: 5.5.37
-- PHP Version: 5.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `inventory42`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE IF NOT EXISTS `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `email`) VALUES
(1, 'lolo8304@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `inventory`
--

CREATE TABLE IF NOT EXISTS `inventory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `location_address` varchar(200) DEFAULT NULL,
  `location_city` varchar(200) DEFAULT NULL,
  `location_country` varchar(200) DEFAULT NULL,
  `location_latitude` double DEFAULT NULL,
  `location_longitude` double DEFAULT NULL,
  `location_postalcode` varchar(10) DEFAULT NULL,
  `customer` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cdur1xk9cfpc96t9sdjuodtud` (`customer`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `inventory`
--

INSERT INTO `inventory` (`id`, `location_address`, `location_city`, `location_country`, `location_latitude`, `location_longitude`, `location_postalcode`, `customer`) VALUES
(1, 'Gablerackerstrasse 4', 'Wermatswil', 'Switzerland', 4.0113141515, 8.723423422, '8615', 1);

-- --------------------------------------------------------

--
-- Table structure for table `PERSONS`
--

CREATE TABLE IF NOT EXISTS `PERSONS` (
  `personid` int(11) NOT NULL AUTO_INCREMENT,
  `birthday` date DEFAULT NULL,
  `emailaddress` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`personid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `PERSONS`
--

INSERT INTO `PERSONS` (`personid`, `birthday`, `emailaddress`, `name`) VALUES
(1, '1969-04-13', 'lorenz.haenggi@bluewin.ch', 'Lorenz HÃ¤nggi');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `inventory`
--
ALTER TABLE `inventory`
  ADD CONSTRAINT `FK_cdur1xk9cfpc96t9sdjuodtud` FOREIGN KEY (`customer`) REFERENCES `customer` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
