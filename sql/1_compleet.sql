/*
SQLyog without ads - By Darkrulerz v9.02 
MySQL - 5.5.8 : Database - roosterprogramma
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
  `personeelsnummer` int(4) NOT NULL,
  `voornaam` varchar(50) NOT NULL,
  `tussenvoegsel` varchar(25) DEFAULT NULL,
  `achternaam` varchar(50) NOT NULL,
  `wachtwoord` varchar(50) DEFAULT NULL,
  `fulltime` tinyint(1) NOT NULL DEFAULT '0',
  `parttime` tinyint(1) NOT NULL DEFAULT '1',
  `oproepkracht` tinyint(1) NOT NULL DEFAULT '0',
  `baliemedewerker` tinyint(1) NOT NULL DEFAULT '0',
  `museumdocent` tinyint(1) NOT NULL DEFAULT '0',
  `contracturen` double NOT NULL DEFAULT '0',
  `admin` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`personeelsnummer`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `medewerkers` */

insert  into `medewerkers`(`personeelsnummer`,`voornaam`,`tussenvoegsel`,`achternaam`,`wachtwoord`,`fulltime`,`parttime`,`oproepkracht`,`baliemedewerker`,`museumdocent`,`contracturen`,`admin`) values (11,'Brechje',NULL,'Manden','TEST',0,1,0,0,0,32,0),(12,'Margo',NULL,'Aarsen','TEST',0,0,0,0,1,3,0),(20,'Annie','de','Boer','TEST',0,0,0,1,0,32,0),(21,'Miriam',NULL,'Blokker','TEST',0,0,0,1,0,20,0),(24,'Toyah',NULL,'Boot','TEST',0,0,0,1,0,2,0),(25,'Paulien',NULL,'Bosman','TEST',0,0,0,1,0,0,0),(26,'Annemijn',NULL,'Bouwman','TEST',0,0,0,1,0,0,0),(27,'Karin',NULL,'Borst','TEST',1,0,0,0,0,40,0),(30,'Inge',NULL,'Commandeur','TEST',0,1,0,0,0,32,0),(40,'Wiepke',NULL,'Draisma','TEST',0,0,0,1,0,22.5,0),(71,'Farida',NULL,'Guseynova','TEST',0,1,0,0,0,0,0),(80,'Gerard',NULL,'Horneman','TEST',1,0,0,0,0,0,0),(85,'Rita',NULL,'Hooijschuur','TEST',0,1,0,0,0,0,0),(87,'Florence','van','Heuvelen','TEST',0,0,0,1,0,8,0),(111,'Lia',NULL,'Kijzer','TEST',0,0,0,1,0,8,0),(121,'Anneke',NULL,'Leeuw','TEST',0,0,0,1,0,16,0),(122,'Antonio',NULL,'Lopes','TEST',0,1,0,0,0,32,0),(123,'Nathalie',NULL,'Louter','TEST',0,0,0,1,0,16,0),(124,'Meta',NULL,'Lepelaar','TEST',0,0,0,0,1,3,0),(125,'Maaike',NULL,'Teunissen','TEST',0,1,0,0,0,24,0),(130,'Noortje',NULL,'Molenaar','TEST',0,0,0,1,0,8,0),(131,'Ananda',NULL,'Marchildon','TEST',0,0,0,1,0,24,0),(140,'Els',NULL,'Neeft','TEST',0,1,0,0,0,16,0),(161,'Mandy',NULL,'Ploeger','TEST',0,0,0,1,0,0,0),(180,'Fokelien',NULL,'Renckens','TEST',1,0,0,0,0,0,0),(182,'Jacqueline',NULL,'Jonker','TEST',0,0,0,1,0,8,0),(183,'Eva',NULL,'Rijs','TEST',0,1,0,0,0,32,0),(184,'Kiona','0','van Rooijen','TEST',0,0,0,0,1,3,0),(190,'Femke',NULL,'Stevens','TEST',0,0,0,1,0,0,0),(200,'Joke',NULL,'Terol','TEST',0,1,0,0,0,32,0),(201,'Marieke',NULL,'Tervoort','TEST',0,0,0,0,1,16,0),(202,'José',NULL,'Tijhuis','TEST',0,1,0,0,0,0,0),(222,'Ellen',NULL,'Timmer','TEST',0,1,0,0,0,24,0),(223,'Jolanda',NULL,'Vries','TEST',0,0,0,1,0,20,0),(226,'Remco','0','de Vries','TEST',0,0,0,0,1,3,0),(228,'Petra',NULL,'Dol','TEST',0,0,0,1,0,0,0),(229,'Els',NULL,'Vermeijden','TEST',0,0,0,1,0,0,0),(232,'Hester',NULL,'Wandel','TEST',0,1,0,0,0,32,0),(233,'Daantje','van de','Weijer','TEST',0,1,0,0,0,32,0),(260,'Yvonne',NULL,'Zwart','TEST',0,1,0,0,0,20,0),(261,'Rena',NULL,'Zendedel','TEST',0,1,0,0,0,0,0),(262,'Chengiz',NULL,'Zendedel','TEST',0,1,0,0,0,0,0);

/*Table structure for table `werktijden` */

DROP TABLE IF EXISTS `werktijden`;

CREATE TABLE `werktijden` (
  `personeelsnummer` int(5) NOT NULL,
  `datum` date NOT NULL,
  `ingeroosterd` tinyint(2) NOT NULL,
  `gewerkt` tinyint(2) NOT NULL,
  `compensatie150` tinyint(2) NOT NULL,
  `compensatie200` tinyint(2) NOT NULL,
  `vakantie` tinyint(2) NOT NULL,
  `adv` tinyint(2) NOT NULL,
  `ziekte` tinyint(2) NOT NULL,
  `verlof` tinyint(2) NOT NULL,
  `project` tinyint(2) NOT NULL,
  PRIMARY KEY (`personeelsnummer`,`datum`),
  CONSTRAINT `FK_personeel` FOREIGN KEY (`personeelsnummer`) REFERENCES `medewerkers` (`personeelsnummer`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `werktijden` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
