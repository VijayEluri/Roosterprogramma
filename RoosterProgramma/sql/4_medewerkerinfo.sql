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

/*Table structure for table `medewerkerinfo` */

DROP TABLE IF EXISTS `medewerkerinfo`;

CREATE TABLE `medewerkerinfo` (
  `id` int(2) NOT NULL AUTO_INCREMENT,
  `weeknr` int(2) NOT NULL,
  `dag` int(2) NOT NULL,
  `contracturen` int(2) NOT NULL,
  `tewerken` int(2) NOT NULL,
  `werkelijkgewerkt` int(2) NOT NULL,
  `gewerkttebetalen` int(2) NOT NULL,
  `gewerkttecompenseren` int(2) NOT NULL,
  `mindergewerkt` int(2) NOT NULL,
  `atv` int(2) NOT NULL,
  `vakantie` int(3) NOT NULL,
  `compensatie33` int(11) NOT NULL,
  `compensatie50` int(11) NOT NULL,
  `compensatie100` int(11) NOT NULL,
  `dokter` int(2) NOT NULL,
  `ziekte` int(3) NOT NULL,
  `eigenrekening` int(2) NOT NULL,
  `feestdagen` int(2) NOT NULL,
  `compensatieopname` int(2) NOT NULL,
  `caoverlof` int(2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `medewerkerinfo` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
