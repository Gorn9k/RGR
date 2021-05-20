drop database if exists `talon_by`;
CREATE DATABASE  `talon_by` ;
USE `talon_by`;

CREATE TABLE `institutions` (
  `id` int not null auto_increment,
  `Name` varchar(35) NOT NULL unique,
  `Address` varchar(35) NOT NULL unique,
  `Tel` varchar(20) default NULL unique,
  `Mail` varchar(30) default NULL unique,
  
  PRIMARY KEY (`id`) 
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE utf8mb4_general_ci;
  
CREATE TABLE `doctors` (
  `id` int not null auto_increment,
  `id_institution` int not null,
  `Surname` varchar(20) NOT NULL,
  `Name` varchar(20) NOT NULL,
  `Otchestvo` varchar(20) default NULL,
  `Dolznost` varchar(15) NOT NULL,
  `Tel` varchar(20) default NULL unique,
  `Nomer_kab` int NOT NULL unique,

  PRIMARY KEY (`id`), 
  KEY `ForeignKey_id_institution` (`id_institution`),
  CONSTRAINT `ForeignKey_id_institution` FOREIGN KEY (`id_institution`) REFERENCES `institutions` (`id`) ON DELETE CASCADE
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE utf8mb4_general_ci;

CREATE TABLE `users` (
  `id` int not null auto_increment,
  `Login` varchar(15) NOT NULL unique,
  `Password` varchar(64) NOT NULL unique,
  `Tel` varchar(20) default NULL unique,
  `Mail` varchar(30) default NULL unique, 
  `Nomer_kartyi` varchar(7) NOT NULL unique,
  `Authority` varchar(10) default "ROLE_USER",
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  COLLATE utf8mb4_general_ci;

CREATE TABLE `talons` (
  `id` int not null auto_increment,
  `id_doctor` int NOT NULL,
  `id_user` int default NULL,
  `id_institution` int NOT NULL,
  `Date_time` datetime NOT NULL,
  `Nomer_kab` int NOT NULL unique,

  PRIMARY KEY (`id`),
  KEY `FK_id_doctor` (`id_doctor`),
  CONSTRAINT `FK_id_doctor` FOREIGN KEY (`id_doctor`) REFERENCES `doctors` (`id`) ON DELETE CASCADE,
  KEY `FK_id_user` (`id_user`),
  CONSTRAINT `FK_id_user` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`) ON DELETE SET NULL,
  KEY `FK_id_institution` (`id_institution`),
  CONSTRAINT `FK_id_institution` FOREIGN KEY (`id_institution`) REFERENCES `institutions` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  COLLATE utf8mb4_general_ci;
  
LOCK TABLES `institutions` WRITE;

INSERT INTO `institutions` VALUES 
(1, 'Polik 1', 'г. Витебск, Московский пр-т 43А', '+375(29)745-14-11', 'polik1@vitebsk.by'),
(2, 'Polik 2', 'г. Могилев, Московский пр-т 43А', '+375(29)741-14-11', 'polik2@vitebsk.by'),
(3, 'Polik 3', 'г. Минск, Московский пр-т 43А', '+375(29)742-14-11', 'polik3@vitebsk.by'),
(4, 'Polik 4', 'г. Гродно, Московский пр-т 43А', '+375(29)743-14-11', 'polik4@vitebsk.by'),
(5, 'Polik 5', 'г. Брест, Московский пр-т 43А', '+375(29)744-14-11', 'polik5@vitebsk.by');
UNLOCK TABLES;

LOCK TABLES `doctors` WRITE;

INSERT INTO `doctors` VALUES 
(1, 1, 'Ivanov','Ivan','Ivanich', 'doctor1', '+375(29)745-14-21', 4),
(2, 2, 'Ivanov1','Ivan1','Ivanich1', 'doctor2', '+375(29)745-14-22', 5),
(3, 3, 'Ivanov2','Ivan2','Ivanich2', 'doctor3', '+375(29)745-14-23', 6),
(4, 4, 'Ivanov3','Ivan3','Ivanich3', 'doctor1', '+375(29)745-14-24', 7),
(5, 5, 'Ivanov4','Ivan4','Ivanich4', 'doctor2', '+375(29)745-14-25', 8),
(6, 1, 'Ivanov5','Ivan5','Ivanich5', 'doctor3', '+375(29)745-14-27', 9),
(7, 1, 'Ivanov6','Ivan6','Ivanich6', 'doctor1', '+375(29)745-14-28', 10),
(8, 2, 'Ivanov7','Ivan7','Ivanich7', 'doctor2', '+375(29)745-14-29', 11),
(9, 2, 'Ivanov8','Ivan8','Ivanich8', 'doctor3', '+375(29)745-14-39', 12),
(10, 3, 'Ivanov9','Ivan9','Ivanich9', 'doctor1', '+375(29)745-14-31', 13);

UNLOCK TABLES;

LOCK TABLES `users` WRITE;

INSERT INTO `users` (Login, Password, Nomer_kartyi, Authority) values 
('admin', '$2a$10$jrryFNptnoGWwyWhxc47eeeHpin/LPOut7J221Xv4DB3qTswVcvJS', '', 'ROLE_ADMIN');
UNLOCK TABLES;

LOCK TABLES `talons` WRITE;

INSERT INTO `talons` (id_doctor, id_institution, Date_time, Nomer_kab) values
(1, 1, '2021-09-01 14:30:00', 4),
(2, 2, '2021-09-02 14:30:00', 5),
(3, 3, '2021-09-01 18:30:00', 6),
(4, 4, '2021-09-01 22:30:00', 7),
(5, 5, '2021-09-03 14:30:00', 8),
(6, 1, '2021-09-02 18:30:00', 9),
(1, 1, '2021-09-01 22:30:00', 10),
(2, 2, '2021-09-02 18:30:00', 11),
(3, 3, '2021-09-02 18:30:00', 12),
(4, 4, '2021-09-02 18:30:00', 13),
(5, 5, '2021-09-01 22:30:00', 14),
(6, 1, '2021-09-02 18:30:00', 15),
(7, 1, '2021-09-02 18:30:00', 16),
(8, 2, '2021-09-01 22:30:00', 17),
(9, 2, '2021-09-02 18:30:00', 18),
(10, 3, '2021-09-01 22:30:00', 19);

UNLOCK TABLES;


-- Dump completed on 2014-08-28 15:51:23