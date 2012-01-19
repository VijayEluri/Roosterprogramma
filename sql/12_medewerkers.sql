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

/*Data for the table `medewerkers` */

insert  into `medewerkers`(`personeelsnummer`,`voornaam`,`tussenvoegsel`,`achternaam`,`wachtwoord`,`fulltime`,`parttime`,`oproepkracht`,`baliemedewerker`,`museumdocent`,`contracturen`,`admin`) values (12,'Margo',NULL,'Aarsen',NULL,0,0,0,0,1,3,0),(11,'Brechje',NULL,'Manden',NULL,0,1,0,0,0,32,0),(20,'Annie','de','Boer',NULL,0,0,0,1,0,32,0),(21,'Miriam',NULL,'Blokker',NULL,0,0,0,1,0,20,0),(24,'Toyah',NULL,'Boot',NULL,0,0,0,1,0,2,0),(25,'Paulien',NULL,'Bosman',NULL,0,0,0,1,0,0,0),(26,'Annemijn',NULL,'Bouwman',NULL,0,0,0,1,0,0,0),(30,'Inge',NULL,'Commandeur',NULL,0,1,0,0,0,32,0),(40,'Wiepke',NULL,'Draisma',NULL,0,0,0,1,0,22.5,0),(71,'Farida',NULL,'Guseynova',NULL,0,1,0,0,0,0,0),(80,'Gerard',NULL,'Horneman',NULL,1,0,0,0,0,0,0),(85,'Rita',NULL,'Hooijschuur',NULL,0,1,0,0,0,0,0),(87,'Florence','van','Heuvelen',NULL,0,0,0,1,0,8,0),(111,'Lia',NULL,'Kijzer',NULL,0,0,0,1,0,8,0),(121,'Anneke',NULL,'Leeuw',NULL,0,0,0,1,0,16,0),(122,'Antonio',NULL,'Lopes',NULL,0,1,0,0,0,32,0),(123,'Nathalie',NULL,'Louter',NULL,0,0,0,1,0,16,0),(124,'Meta',NULL,'Lepelaar',NULL,0,0,0,0,1,3,0),(125,'Maaike',NULL,'Teunissen',NULL,0,1,0,0,0,24,0),(130,'Noortje',NULL,'Molenaar',NULL,0,0,0,1,0,8,0),(131,'Ananda',NULL,'Marchildon',NULL,0,0,0,1,0,24,0),(132,'Ilse',NULL,'Moester',NULL,0,0,1,0,0,0,0),(140,'Els',NULL,'Neeft',NULL,0,1,0,0,0,16,0),(161,'Mandy',NULL,'Ploeger',NULL,0,0,0,1,0,0,0),(180,'Fokelien',NULL,'Renckens',NULL,1,0,0,0,0,0,0),(182,'Jacqueline',NULL,'Jonker',NULL,0,0,0,1,0,8,0),(183,'Eva',NULL,'Rijs',NULL,0,1,0,0,0,32,0),(184,'Kiona',NULL,'van Rooijen',NULL,0,0,0,0,1,3,0),(190,'Femke',NULL,'Stevens',NULL,0,0,0,1,0,0,0),(200,'Joke',NULL,'Terol',NULL,0,1,0,0,0,32,0),(201,'Marieke',NULL,'Tervoort',NULL,0,0,0,0,1,16,0),(202,'José',NULL,'Tijhuis',NULL,0,1,0,0,0,0,0),(222,'Ellen',NULL,'Timmer',NULL,0,1,0,0,0,24,0),(223,'Jolanda',NULL,'Vries',NULL,0,0,0,1,0,20,0),(226,'Remco',NULL,'de Vries',NULL,0,0,0,0,1,3,0),(228,'Petra',NULL,'Dol',NULL,0,0,0,1,0,0,0),(229,'Els',NULL,'Vermeijden',NULL,0,0,0,1,0,0,0),(232,'Hester',NULL,'Wandel',NULL,0,1,0,0,0,32,0),(233,'Daantje','van de','Weijer',NULL,0,1,0,0,0,32,0),(234,'Marieke',NULL,'Roggeveen',NULL,0,0,0,1,0,40,0),(260,'Yvonne',NULL,'Zwart',NULL,0,1,0,0,0,20,0),(261,'Rena',NULL,'Zendedel',NULL,0,1,0,0,0,0,0),(262,'Chengiz',NULL,'Zendedel',NULL,0,1,0,0,0,0,0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
