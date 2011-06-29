alter table `roosterprogramma`.`medewerkers` 
   add column `admin` tinyint(1) DEFAULT '0' NOT NULL after `vakantiepercentage`,
   change `admin` `vakantiepercentage` double default '0' NOT NULL;