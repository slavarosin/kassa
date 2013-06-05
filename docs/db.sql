DROP TABLE IF EXISTS `gb_x_goodk97b`.`comments`;
CREATE TABLE  `gb_x_goodk97b`.`comments` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `text` varchar(256) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `gb_x_goodk97b`.`companies`;
CREATE TABLE  `gb_x_goodk97b`.`companies` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `gb_x_goodk97b`.`currencies`;
CREATE TABLE  `gb_x_goodk97b`.`currencies` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `sign` char(3) DEFAULT NULL,
  `kassa` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `gb_x_goodk97b`.`kassa`;
CREATE TABLE  `gb_x_goodk97b`.`kassa` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `amount` decimal(10,2) NOT NULL,
  `rate` decimal(10,5) NOT NULL,
  `currency` int(10) unsigned NOT NULL,
  `invoice_type` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `createdBy` int(10) unsigned NOT NULL,
  `comments` text,
  `creditor` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `createdBy` (`createdBy`),
  KEY `currency` (`currency`),
  CONSTRAINT `kassa_ibfk_1` FOREIGN KEY (`createdBy`) REFERENCES `users` (`id`),
  CONSTRAINT `kassa_ibfk_2` FOREIGN KEY (`currency`) REFERENCES `currencies` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `gb_x_goodk97b`.`rates`;
CREATE TABLE  `gb_x_goodk97b`.`rates` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `currency1` int(10) unsigned DEFAULT NULL,
  `currency2` int(10) unsigned DEFAULT NULL,
  `rate` decimal(10,5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `gb_x_goodk97b`.`trade`;
CREATE TABLE  `gb_x_goodk97b`.`trade` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `amount` int(10) unsigned NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `createdBy` int(10) unsigned NOT NULL,
  `milestone` int(10) unsigned NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `createdBy` (`createdBy`),
  KEY `trade_ibfk_2` (`milestone`),
  CONSTRAINT `trade_ibfk_2` FOREIGN KEY (`milestone`) REFERENCES `trade_milestones` (`id`),
  CONSTRAINT `trade_ibfk_1` FOREIGN KEY (`createdBy`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `gb_x_goodk97b`.`trade_milestones`;
CREATE TABLE  `gb_x_goodk97b`.`trade_milestones` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `gb_x_goodk97b`.`users`;
CREATE TABLE  `gb_x_goodk97b`.`users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `login` varchar(20) DEFAULT NULL,
  `passwd` varchar(32) DEFAULT NULL,
  `role` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;