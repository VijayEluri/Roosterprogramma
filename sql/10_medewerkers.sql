alter table `roosterprogramma`.`medewerkers` 
   add column `museumdocent` tinyint(1) DEFAULT '0' NOT NULL after `baliemedewerker`,
   change `wachtwoord` `wachtwoord` varchar(50) character set latin1 collate latin1_swedish_ci NULL , 
   change `fulltime` `fulltime` tinyint(1) default '0' NOT NULL, 
   change `parttime` `parttime` tinyint(1) default '1' NOT NULL;