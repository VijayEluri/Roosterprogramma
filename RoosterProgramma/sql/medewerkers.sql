/*
SQLyog Community v8.7 
MySQL - 5.1.40-community : Database - roosterprogramma
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`roosterprogramma` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `roosterprogramma`;

/*Table structure for table `medewerkers` */

DROP TABLE IF EXISTS `medewerkers`;

CREATE TABLE `medewerkers` (
  `id` int(2) NOT NULL AUTO_INCREMENT,
  `personeelsnummer` int(4) NOT NULL,
  `voornaam` varchar(50) NOT NULL,
  `achternaam` varchar(50) NOT NULL,
  `wachtwoord` varchar(50) DEFAULT NULL,
  `fulltime` tinyint(1) NOT NULL DEFAULT '1',
  `parttime` tinyint(1) NOT NULL DEFAULT '0',
  `oproepkracht` tinyint(1) NOT NULL DEFAULT '0',
  `admin` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `medewerkers` */

insert  into `medewerkers`(`id`,`personeelsnummer`,`voornaam`,`achternaam`,`wachtwoord`,`fulltime`,`parttime`,`oproepkracht`,`admin`) values (1,8085,'Joke','Terol',NULL,1,0,0,1),(2,2154,'Eveline','Kroon',NULL,1,0,0,0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
