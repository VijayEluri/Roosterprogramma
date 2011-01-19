alter table `roosterprogramma`.`medewerkers` drop column `id`,
   change `personeelsnummer` `personeelsnummer` int(4) NOT NULL,
   drop primary key, 
   add primary key(`personeelsnummer`)