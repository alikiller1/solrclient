

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

create table `student` (
	`id` int (11),
	`name` varchar (60),
	`age` int (11),
	`addr` varchar (120),
	`feature` varchar (120),
	`timestamp` timestamp ,
	`content` varchar (1200)
); 
insert into `student` (`id`, `name`, `age`, `addr`, `feature`, `timestamp`, `content`) values('1','lisi','11','深圳,深圳，福田','矮,瘦','2017-05-23 17:02:56','若按照机构名称查询');
insert into `student` (`id`, `name`, `age`, `addr`, `feature`, `timestamp`, `content`) values('2','liuqs','11','上海,浦东','高,胖','2017-05-23 17:03:18','请输入机构的全称');
insert into `student` (`id`, `name`, `age`, `addr`, `feature`, `timestamp`, `content`) values('3','liubm','11','深圳，深圳，深圳，','矮,瘦','2017-05-23 17:03:25','公司代码怎么查询');
insert into `student` (`id`, `name`, `age`, `addr`, `feature`, `timestamp`, `content`) values('4','cll','11','深圳，深圳，深圳，深圳','矮,瘦','2017-05-23 17:03:27','组织机构代码是什么');
insert into `student` (`id`, `name`, `age`, `addr`, `feature`, `timestamp`, `content`) values('5','abcliuqh','12','深圳','矮，瘦','2017-05-23 17:03:38','组织机构代码是什么 how are you');
insert into `student` (`id`, `name`, `age`, `addr`, `feature`, `timestamp`, `content`) values('6','liubk','11','上海，黄浦,世纪大道','高，瘦','2017-05-23 17:03:53','where you from');
insert into `student` (`id`, `name`, `age`, `addr`, `feature`, `timestamp`, `content`) values('7','aliuqh','11','上海，浦东，唐镇','矮，胖','2017-05-23 17:04:26','yes,you are right');
insert into `student` (`id`, `name`, `age`, `addr`, `feature`, `timestamp`, `content`) values('8','aaliuqs','11','湖南省衡阳市唐镇世纪大道','矮','2017-05-23 17:04:44','事实上，对短语进行精确匹配的查询语句 “chief  development  officer” 很容易改写成  “chief ');
insert into `student` (`id`, `name`, `age`, `addr`, `feature`, `timestamp`, `content`) values('9','liuqccc','11','深圳，罗湖','矮，矮，胖','2017-05-23 17:05:01','查询语句： “chief officer”~1 ');
