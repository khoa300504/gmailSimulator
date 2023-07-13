-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.27-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for email_system
CREATE DATABASE IF NOT EXISTS `email_system` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `email_system`;

-- Dumping structure for table email_system.ta_kda_email
CREATE TABLE IF NOT EXISTS `ta_kda_email` (
  `I_ID` int(11) NOT NULL AUTO_INCREMENT,
  `T_Subject` varchar(100) DEFAULT NULL,
  `T_Body` varchar(255) NOT NULL,
  `I_IdSend` int(11) NOT NULL,
  `I_IdReceive` int(11) NOT NULL,
  `T_FilePath` varchar(255) DEFAULT NULL,
  `T_Attach` varchar(10) NOT NULL,
  PRIMARY KEY (`I_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

-- Dumping structure for table email_system.ta_kda_trash
CREATE TABLE IF NOT EXISTS `ta_kda_trash` (
  `I_ID` int(11) NOT NULL AUTO_INCREMENT,
  `T_Subject` varchar(100) DEFAULT NULL,
  `T_Body` varchar(255) NOT NULL,
  `I_IdSend` int(11) NOT NULL,
  `I_IdReceive` int(11) NOT NULL,
  `T_FilePath` varchar(255) DEFAULT NULL,
  `T_Attach` varchar(10) NOT NULL,
  PRIMARY KEY (`I_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

-- Dumping structure for table email_system.ta_kda_user
CREATE TABLE IF NOT EXISTS `ta_kda_user` (
  `I_ID` int(11) NOT NULL AUTO_INCREMENT,
  `T_EmailAddress` varchar(255) NOT NULL,
  `T_PassWord` varchar(30) NOT NULL,
  `T_FullName` varchar(255) NOT NULL,
  `T_BirthDay` varchar(30) NOT NULL,
  `T_PhoneNumber` varchar(30) NOT NULL,
  PRIMARY KEY (`I_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
