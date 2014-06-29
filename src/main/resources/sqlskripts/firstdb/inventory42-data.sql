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

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `email`) VALUES
(1, 'lolo8304@gmail.com');

INSERT INTO `inventory` (`id`, `location_address`, `location_city`, `location_country`, `location_latitude`, `location_longitude`, `location_postalcode`, `customer`) VALUES
(1, 'Gablerackerstrasse 4', 'Wermatswil', 'Switzerland', 4.0113141515, 8.723423422, '8615', 1);

INSERT INTO `PERSONS` (`personid`, `birthday`, `emailaddress`, `name`) VALUES
(1, '1969-04-13', 'lorenz.haenggi@bluewin.ch', 'Lorenz HÃ¤nggi');
