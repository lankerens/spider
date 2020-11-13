-- DROP TABLE IF EXISTS `sys_anmie`;
Create Table If Not Exists `sys_anmie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `animeName` varchar(255) DEFAULT NULL,
  `blues` varchar(255) DEFAULT NULL,
  `desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;

