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

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `addr` varchar(40) DEFAULT NULL,
  `feature` varchar(40) DEFAULT NULL,
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `content` varchar(400) DEFAULT NULL,
  `grade` decimal(4,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `student` */

insert  into `student`(`id`,`name`,`age`,`addr`,`feature`,`updatetime`,`content`,`grade`) values (1,'lisi',1,'深圳 衡阳 福田','矮 瘦','2017-07-07 11:43:44','拍拍','10.20'),(2,'liuqs',2,'武汉 衡阳 福田','高 胖','2017-07-07 11:25:00','深圳拍拍球','5.20'),(3,'liubm',3,'武汉 衡阳 长沙 沈阳','高 瘦','2017-07-07 11:25:46','中国深圳拍拍球','13.20'),(4,'cll',4,'深圳 衡阳 福田','高 瘦','2017-07-07 11:24:33','中国深圳拍拍深圳拍拍深圳拍拍深圳拍拍','10.20'),(5,'abcliuqh',5,'衡阳 上海 福田 广州','高 瘦','2017-07-07 11:24:27','中国深圳拍球','11.20'),(6,'liubk',6,'深圳 济南 福田 沈阳','高 瘦','2017-07-07 11:43:56','中国球拍拍','10.20'),(7,'aliuqh',7,'深圳 北京 福田','矮 胖','2017-07-07 11:25:26','深圳球拍拍','10.20'),(8,'aaliuqs',8,'武汉 上海 福田 广州','矮','2017-07-07 11:26:56','中国郑州球拍拍','12.00'),(9,'liuqccc',9,'深圳 长沙 福田 郑州','矮 矮 胖','2017-07-07 11:43:35','拍拍 上海3','10.20'),(10,'liuqccc',11,'深圳 上海 福田 广州','矮 矮 胖','2017-07-07 11:24:18','深a12,*圳b, 12 &拍12拍14',NULL),(11,'name1',NULL,NULL,NULL,'2017-07-03 09:24:32','我是中国人，你来自哪里的？你现在好吗？1',NULL),(12,'name2',NULL,NULL,NULL,'2017-07-03 09:24:32','我是中国人，你来自哪里的？你现在好吗？2',NULL),(13,'name3',NULL,NULL,NULL,'2017-07-03 09:24:32','我是中国人，你来自哪里的？你现在好吗？3',NULL),(14,'name4',NULL,NULL,NULL,'2017-07-03 09:24:32','我是中国人，你来自哪里的？你现在好吗？4',NULL),(15,'name5',NULL,NULL,NULL,'2017-07-03 09:24:32','我是中国人，你来自哪里的？你现在好吗？5',NULL),(16,'name6',NULL,NULL,NULL,'2017-07-03 09:24:32','我是中国人，你来自哪里的？你现在好吗？6',NULL),(17,'name7',NULL,NULL,NULL,'2017-07-03 09:24:32','我是中国人，你来自哪里的？你现在好吗？7',NULL),(18,'name8',NULL,NULL,NULL,'2017-07-03 09:24:32','我是中国人，你来自哪里的？你现在好吗？8',NULL),(19,'name9',NULL,NULL,NULL,'2017-07-03 09:24:32','我是中国人，你来自哪里的？你现在好吗？9',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

/*123*/