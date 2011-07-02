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
  `vakantiepercentage` double NOT NULL DEFAULT '0',
  `admin` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`personeelsnummer`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert  into `medewerkers`(`personeelsnummer`,`voornaam`,`tussenvoegsel`,`achternaam`,`wachtwoord`,`fulltime`,`parttime`,`oproepkracht`,`baliemedewerker`,`museumdocent`,`contracturen`,`vakantiepercentage`,`admin`) values (11,'Brechje',NULL,'Manden','TEST',0,1,0,0,0,32,0,0),(12,'Margo',NULL,'Aarsen','TEST',0,0,1,0,1,3,9.615,0),(20,'Annie','de','Boer','TEST',0,1,0,1,0,32,0,0),(21,'Miriam',NULL,'Blokker','TEST',0,1,0,1,0,20,0,0),(24,'Toyah',NULL,'Boot','TEST',0,0,1,1,0,2,9.615,0),(25,'Paulien',NULL,'Bosman','TEST',0,0,1,1,0,0,9.615,0),(26,'Annemijn',NULL,'Bouwman','TEST',0,0,1,1,0,0,9.615,0),(27,'Karin',NULL,'Borst','TEST',1,0,0,0,0,40,0,0),(30,'Inge',NULL,'Commandeur','TEST',0,1,0,0,0,32,0,0),(40,'Wiepke',NULL,'Draisma','TEST',0,1,0,1,0,22.5,0,0),(71,'Farida',NULL,'Guseynova','TEST',0,0,1,0,0,0,9.615,0),(80,'Gerard',NULL,'Horneman','TEST',1,0,0,0,0,0,0,0),(85,'Rita',NULL,'Hooijschuur','TEST',0,0,1,0,0,0,9.615,0),(87,'Florence','van','Heuvelen','TEST',0,0,1,1,0,8,9.615,0),(111,'Lia',NULL,'Kijzer','TEST',0,0,1,1,0,8,9.615,0),(121,'Anneke',NULL,'Leeuw','TEST',0,0,1,1,0,16,9.615,0),(122,'Antonio',NULL,'Lopes','TEST',0,1,0,0,0,32,0,0),(123,'Nathalie',NULL,'Louter','TEST',0,1,0,1,0,16,0,0),(124,'Meta',NULL,'Lepelaar','TEST',0,0,1,0,1,3,9.615,0),(125,'Maaike',NULL,'Teunissen','TEST',0,1,0,0,0,24,0,0),(130,'Noortje',NULL,'Molenaar','TEST',0,0,1,1,0,8,9.615,0),(131,'Ananda',NULL,'Marchildon','TEST',0,1,0,1,0,24,0,0),(140,'Els',NULL,'Neeft','TEST',0,1,0,0,0,16,0,0),(161,'Mandy',NULL,'Ploeger','TEST',0,0,1,1,0,0,9.615,0),(180,'Fokelien',NULL,'Renckens','TEST',1,0,0,0,0,0,0,0),(182,'Jacqueline',NULL,'Jonker','TEST',0,1,0,1,0,8,0,0),(183,'Eva',NULL,'Rijs','TEST',0,1,0,0,0,32,0,0),(184,'Kiona','van','Rooijen','TEST',0,0,1,0,1,3,9.615,0),(190,'Femke',NULL,'Stevens','TEST',0,0,1,1,0,0,9.615,0),(200,'Joke',NULL,'Terol','2792837ec3ec78ee7ce20f44db9e881d494641d1',0,1,0,0,0,32,0,1),(201,'Marieke',NULL,'Tervoort','TEST',0,1,0,0,1,16,0,0),(202,'José',NULL,'Tijhuis','TEST',0,1,0,0,0,0,0,0),(222,'Ellen',NULL,'Timmer','TEST',0,1,0,0,0,24,0,0),(223,'Jolanda',NULL,'Vries','TEST',0,1,0,1,0,20,0,0),(226,'Remco',NULL,'de Vries','TEST',0,0,1,0,1,3,9.615,0),(228,'Petra',NULL,'Dol','TEST',0,0,1,1,0,0,9.615,0),(229,'Els',NULL,'Vermeijden','TEST',0,1,0,1,0,0,0,0),(232,'Hester',NULL,'Wandel','TEST',0,1,0,0,0,32,0,0),(233,'Daantje','van de','Weijer','TEST',0,1,0,0,0,32,0,0),(260,'Yvonne',NULL,'Zwart','TEST',0,1,0,0,0,20,0,0),(261,'Rena',NULL,'Zendedel','TEST',0,0,1,0,0,0,9.615,0),(262,'Chengiz',NULL,'Zendedel','TEST',0,0,1,0,0,0,9.615,0);

DROP TABLE IF EXISTS `werktijden`;

CREATE TABLE `werktijden` (
  `personeelsnummer` int(5) NOT NULL,
  `datum` date NOT NULL,
  `ingeroosterd` varchar(5) NOT NULL,
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

insert  into `werktijden`(`personeelsnummer`,`datum`,`ingeroosterd`,`gewerkt`,`compensatie150`,`compensatie200`,`vakantie`,`adv`,`ziekte`,`verlof`,`project`) values (25,'2011-07-02','V',0,0,0,0,0,0,0,0);