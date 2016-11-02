-- ###############
--    create database , if need create, cancel the comment
-- ###############
-- create database if not exists zsfz default character set utf8;
-- use zsfz set default character = utf8;

-- ###############
--    grant privileges  to build/build
-- ###############
-- GRANT ALL PRIVILEGES ON zsfz.* TO build@localhost IDENTIFIED BY "build";

-- ###############
-- Domain:  User
-- ###############
Drop table  if exists user_;
CREATE TABLE `user_` (
  `id` int(11) NOT NULL auto_increment,
  `guid` varchar(255) not null unique,
  `create_time` datetime ,
  `archived` tinyint(1) default '0',
  `password` varchar(255) not null,
  `username` varchar(255) not null unique,
  `user_role` varchar(255) not null,
  `type_` varchar(255) not null default 'User',
  `status` varchar(255) not null default 'ENABLED',
   `default_user` tinyint(1) default '0',
   `last_login_time` datetime ,
   `others` text,
   `player_id` int(11),
  PRIMARY KEY  (`id`),
  INDEX `guid_index` (`guid`),
  INDEX `player_id_index` (`player_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ###############
-- Domain:  GeckoFile
-- ###############
Drop table  if exists gecko_file;
CREATE TABLE `gecko_file` (
  `id` int(11) NOT NULL auto_increment,
  `guid` varchar(255) not null unique,
  `create_time` datetime ,
  `archived` tinyint(1) default '0',
  `name_` varchar(255),
  `path_` varchar(255),
  `suffix` varchar(255),
   `temp_` tinyint(1) default '0',
   `size_` int(20) default 0,
   `type_` varchar(255) default 'GeckoFile',
   `url` varchar(255) ,
   `description` varchar(255) ,
   `head_photo`  tinyint(1) default '0',
   `album_id`  int(11),
  PRIMARY KEY  (`id`),
  INDEX `guid_index` (`guid`),
  INDEX `album_id_index` (`album_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ###############
-- Domain:  Player
-- ###############
Drop table  if exists player;
CREATE TABLE `player` (
  `id` int(11) NOT NULL auto_increment,
  `guid` varchar(255) not null unique,
  `create_time` datetime ,
  `archived` tinyint(1) default '0',
  `captain` tinyint(1) default '0',
  `full_name` varchar(255),
  `email` varchar(255),
  `phone` varchar(255),
  `entry_date` date ,
  `birthday` date ,
  `position_` varchar(255),
  `number_` varchar(255),
  `description` text,
  `head_photo_id` int(11),
  PRIMARY KEY  (`id`),
  INDEX `guid_index` (`guid`),
  INDEX `head_photo_id_index` (`head_photo_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ###############
-- Domain:  Album
-- ###############
Drop table  if exists album;
CREATE TABLE `album` (
  `id` int(11) NOT NULL auto_increment,
  `guid` varchar(255) not null unique,
  `create_time` datetime ,
  `archived` tinyint(1) default '0',
  `name_` varchar(255),
  `description` varchar(255),
  `match_id` int(11),
  `creator_id` int(11),
  PRIMARY KEY  (`id`),
  INDEX `guid_index` (`guid`),
  INDEX `match_id_index` (`match_id`),
  INDEX `creator_id_index` (`creator_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ###############
-- Domain:  Club
-- ###############
Drop table  if exists club;
CREATE TABLE `club` (
  `id` int(11) NOT NULL auto_increment,
  `guid` varchar(255) not null unique,
  `create_time` datetime ,
  `archived` tinyint(1) default '0',
  `name_` varchar(255),
  `contact` varchar(255),
  `creator_id` int(11),
  `remark` text,
  PRIMARY KEY  (`id`),
  INDEX `guid_index` (`guid`),
  INDEX `creator_id_index` (`creator_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ###############
-- Domain:  Stadium
-- ###############
Drop table  if exists stadium;
CREATE TABLE `stadium` (
  `id` int(11) NOT NULL auto_increment,
  `guid` varchar(255) not null unique,
  `create_time` datetime ,
  `archived` tinyint(1) default '0',
  `name_` varchar(255),
  `address` varchar(255),
  `contact` varchar(255),
  `home_stadium` tinyint(1) default '0',
  `remark` text,
  PRIMARY KEY  (`id`),
  INDEX `guid_index` (`guid`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ###############
-- Domain:  Season
-- ###############
Drop table  if exists season;
CREATE TABLE `season` (
  `id` int(11) NOT NULL auto_increment,
  `guid` varchar(255) not null unique,
  `create_time` datetime ,
  `archived` tinyint(1) default '0',
  `name_` varchar(255),
  `start` date,
  `end_` date,
  `remark` text,
  PRIMARY KEY  (`id`),
  INDEX `guid_index` (`guid`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ###############
-- Domain:  Goal
-- ###############
Drop table  if exists goal;
CREATE TABLE `goal` (
  `id` int(11) NOT NULL auto_increment,
  `guid` varchar(255) not null unique,
  `create_time` datetime ,
  `archived` tinyint(1) default '0',
  `goal_player_id` int(11),
  `assistant_player_id` int(11),
  `own_goal` tinyint(1) default '0',
  `goal_time` int(11),
  `match_id` int(11),
  PRIMARY KEY  (`id`),
  INDEX `guid_index` (`guid`),
  INDEX `goal_player_id_index` (`goal_player_id`),
  INDEX `match_id_index` (`match_id`),
  INDEX `assistant_player_id_index` (`assistant_player_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ###############
-- Domain:  MatchNotice
-- ###############
Drop table  if exists match_notice;
CREATE TABLE `match_notice` (
  `id` int(11) NOT NULL auto_increment,
  `guid` varchar(255) not null unique,
  `create_time` datetime ,
  `archived` tinyint(1) default '0',
  `time_` datetime,
  `stadium_id` int(11),
  `opponent_id` int(11),
  `creator_id` int(11),
  `match_id` int(11),
  `remark` text,
  `pending` tinyint(1) default '0',
  PRIMARY KEY  (`id`),
  INDEX `guid_index` (`guid`),
  INDEX `stadium_id_index` (`stadium_id`),
  INDEX `creator_id_index` (`creator_id`),
  INDEX `match_id_index` (`match_id`),
  INDEX `opponent_id_index` (`opponent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ###############
-- Domain:  Match
-- ###############
Drop table  if exists match_;
CREATE TABLE `match_` (
  `id` int(11) NOT NULL auto_increment,
  `guid` varchar(255) not null unique,
  `create_time` datetime ,
  `archived` tinyint(1) default '0',
  `match_time` datetime,
  `home_scores` int(11),
  `away_scores` int(11),
  `notice_id` int(11),
  `season_id` int(11),
  `opponent_id` int(11),
  `stadium_id` int(11),
  `creator_id` int(11),
  `remark` text,
  `futsal` tinyint(1) default '0',
  PRIMARY KEY  (`id`),
  INDEX `guid_index` (`guid`),
  INDEX `notice_id_index` (`notice_id`),
  INDEX `stadium_id_index` (`stadium_id`),
  INDEX `season_id_index` (`season_id`),
  INDEX `creator_id_index` (`creator_id`),
  INDEX `opponent_id_index` (`opponent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ###############
-- Domain:  MatchPlayer
-- ###############
Drop table  if exists match_player;
CREATE TABLE `match_player` (
  `id` int(11) NOT NULL auto_increment,
  `guid` varchar(255) not null unique,
  `create_time` datetime ,
  `archived` tinyint(1) default '0',
  `match_id` int(11),
  `player_id` int(11),
  PRIMARY KEY  (`id`),
  INDEX `guid_index` (`guid`),
  INDEX `match_id_index` (`match_id`),
  INDEX `player_id_index` (`player_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;


-- ###############
-- Domain:  Log
-- ###############
Drop table  if exists log;
CREATE TABLE `log` (
  `id` int(11) NOT NULL auto_increment,
  `guid` varchar(255) not null unique,
  `create_time` datetime ,
  `archived` tinyint(1) default '0',
  `time_` datetime,
  `content` text,
  `ip_address` varchar(255),
  `type_` varchar(255),
  `class_` varchar(255) default 'Log',
  `who_id` int(11),
  `creator_id` int(11),
  `important` tinyint(1) default '0',
  PRIMARY KEY  (`id`),
  INDEX `guid_index` (`guid`),
  INDEX `creator_id_index` (`creator_id`),
  INDEX `who_id_index` (`who_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;


-- ###############
-- Domain:  GlobalSetting
-- ###############
Drop table  if exists global_setting;
CREATE TABLE `global_setting` (
  `id` int(11) NOT NULL auto_increment,
  `guid` varchar(255) not null unique,
  `create_time` datetime ,
  `archived` tinyint(1) default '0',
  `use_default_front_photos` tinyint(1) default '1',
  PRIMARY KEY  (`id`),
  INDEX `guid_index` (`guid`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;


