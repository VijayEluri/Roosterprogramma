/*
SQLyog Community v8.71 
MySQL - 5.1.41 : Database - roosterprogramma
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

/*Table structure for table `werktijden` */

DROP TABLE IF EXISTS `werktijden`;

CREATE TABLE `werktijden` (
  `datum` date NOT NULL,
  `Joke Terol` varchar(255) NOT NULL,
  `Eveline Kroon` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `werktijden` */

insert  into `werktijden`(`datum`,`Joke Terol`,`Eveline Kroon`) values ('2011-01-22','8;0;0;0;0;0;0;0','');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
