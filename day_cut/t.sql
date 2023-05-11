-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.29 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- 导出 day_cut 的数据库结构
CREATE DATABASE IF NOT EXISTS `day_cut` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;
USE `day_cut`;

-- 导出  表 day_cut.t_plan 结构
CREATE TABLE IF NOT EXISTS `t_plan` (
  `id` int(20) NOT NULL COMMENT '主键',
  `name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '计划名称',
  `desc` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '计划描述',
  `uid` int(20) NOT NULL COMMENT '创建者id',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='日常计划';

-- 正在导出表  day_cut.t_plan 的数据：~0 rows (大约)

-- 导出  表 day_cut.t_plan_record 结构
CREATE TABLE IF NOT EXISTS `t_plan_record` (
  `id` int(20) NOT NULL,
  `plan_id` int(20) DEFAULT NULL COMMENT '执行计划id',
  `user_id` int(20) DEFAULT NULL COMMENT '用户id',
  `addition_time` bigint(20) DEFAULT NULL COMMENT '执行增量时间',
  `is_completed` tinyint(1) DEFAULT NULL COMMENT '是否完成',
  PRIMARY KEY (`id`),
  KEY `addition_time` (`addition_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='计划执行记录';

-- 正在导出表  day_cut.t_plan_record 的数据：~0 rows (大约)

-- 导出  表 day_cut.t_plugin 结构
CREATE TABLE IF NOT EXISTS `t_plugin` (
  `id` int(20) NOT NULL,
  `subscribe` tinyint(1) DEFAULT NULL COMMENT '是否订阅',
  `style_type` int(5) DEFAULT NULL COMMENT '界面风格编号',
  `font_type` int(5) DEFAULT NULL COMMENT '字体编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='设置';

-- 正在导出表  day_cut.t_plugin 的数据：~0 rows (大约)

-- 导出  表 day_cut.t_task 结构
CREATE TABLE IF NOT EXISTS `t_task` (
  `id` int(20) NOT NULL COMMENT '主键',
  `time_start` time NOT NULL COMMENT '任务开始时间',
  `time_end` time NOT NULL COMMENT '任务结束时间',
  `detail` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '任务详情',
  `is_completed` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否完成',
  `order` int(5) NOT NULL DEFAULT '0' COMMENT '排序',
  `pid` int(20) NOT NULL DEFAULT '0' COMMENT '任务id',
  PRIMARY KEY (`id`),
  KEY `pid` (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='任务';

-- 正在导出表  day_cut.t_task 的数据：~0 rows (大约)

-- 导出  表 day_cut.t_user 结构
CREATE TABLE IF NOT EXISTS `t_user` (
  `id` int(20) NOT NULL,
  `nick_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '昵称',
  `user_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '用户名',
  `password` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '密码',
  `open_id` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '小程序用户id',
  `current_plan` int(20) DEFAULT NULL COMMENT '当前执行计划',
  `tel` varchar(11) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '电话号码',
  `email` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '电子邮箱',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `current_plan` (`current_plan`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户';

-- 正在导出表  day_cut.t_user 的数据：~0 rows (大约)

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
