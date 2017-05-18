/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 5.7.17-log : Database - testdb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`testdb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `testdb`;

/*Table structure for table `score` */

DROP TABLE IF EXISTS `score`;

CREATE TABLE `score` (
  `sid` int(11) DEFAULT NULL,
  `course` varchar(20) DEFAULT NULL,
  `score` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `score` */

insert  into `score`(`sid`,`course`,`score`) values (1,'数学',72),(1,'语文',62),(2,'语文',50);

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` int(11) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `addr` varchar(40) DEFAULT NULL,
  `feature` varchar(40) DEFAULT NULL,
  `timestamp` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `student` */

insert  into `student`(`id`,`name`,`age`,`addr`,`feature`,`timestamp`) values (1,'lisi',11,'深圳,深圳，福田','矮,瘦','2017-05-18 18:28:44'),(2,'liuqs',11,'上海,浦东','高,胖','2017-05-18 18:28:44'),(3,'liubm',11,'深圳，深圳，深圳，','矮,瘦','2017-05-18 18:28:44'),(4,'tom',11,'深圳，深圳，深圳，深圳','矮,瘦','2017-05-18 18:28:44'),(5,'KKK',12,'深圳','矮，瘦','2017-05-18 18:28:44'),(6,'liubk',11,'上海，黄浦','高，瘦','2017-05-18 18:28:44'),(7,'liubk',11,'深圳，罗湖','矮，胖','2017-05-18 18:28:44'),(8,'liuqs',11,'深圳','矮','2017-05-18 18:28:44'),(9,'liuqsd',11,'深圳，罗湖','矮，矮，胖','2017-05-18 18:28:44'),(10,'liuqsd',11,'深圳，罗湖','矮,矮,矮,胖','2017-05-18 18:28:44'),(11,'dd',12,'深圳','矮，胖','2017-05-18 18:28:44'),(12,'ddc',12,'深圳,南山','矮，胖','2017-05-18 18:28:44'),(13,'dd',13,'深圳,南山','矮，胖','2017-05-18 18:28:44'),(14,'cc',11,'深圳,南山','矮，胖','2017-05-18 18:28:44');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
