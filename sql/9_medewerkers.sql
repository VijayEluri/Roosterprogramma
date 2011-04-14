alter table `roosterprogramma`.`medewerkers` 
   add column `baliemedewerker` boolean DEFAULT '0' NOT NULL after `oproepkracht`,
   change `fulltime` `fulltime` boolean default '1' NOT NULL, 
   change `parttime` `parttime` boolean default '0' NOT NULL, 
   change `oproepkracht` `oproepkracht` boolean default '0' NOT NULL, 
   change `admin` `admin` boolean default '0' NOT NULL;