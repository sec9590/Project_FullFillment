-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        5.7.25-log - MySQL Community Server (GPL)
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- yellow 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `yellow` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `yellow`;

-- 테이블 yellow.buying 구조 내보내기
CREATE TABLE IF NOT EXISTS `buying` (
  `b_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `p_id` int(10) unsigned NOT NULL,
  `p_name` varchar(40) DEFAULT NULL,
  `p_img` varchar(80) NOT NULL,
  `p_price` varchar(16) NOT NULL,
  `p_quantity` int(11) NOT NULL,
  `b_time` datetime DEFAULT NULL,
  `buycode` varchar(10) NOT NULL,
  PRIMARY KEY (`b_id`)
) ENGINE=InnoDB AUTO_INCREMENT=50037 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- 내보낼 데이터가 선택되어 있지 않습니다.
-- 테이블 yellow.commodity 구조 내보내기
CREATE TABLE IF NOT EXISTS `commodity` (
  `c_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `p_id` int(20) NOT NULL,
  `p_name` varchar(20) DEFAULT NULL,
  `c_basic` int(20) DEFAULT NULL,
  `c_in` int(20) DEFAULT NULL,
  `c_out` int(20) DEFAULT NULL,
  `c_close` int(20) DEFAULT NULL,
  `c_time` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB AUTO_INCREMENT=221 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.
-- 테이블 yellow.member 구조 내보내기
CREATE TABLE IF NOT EXISTS `member` (
  `m_id` varchar(10) NOT NULL,
  `m_password` varchar(10) NOT NULL,
  `m_name` varchar(10) NOT NULL,
  `m_tel` varchar(20) NOT NULL,
  `m_job` varchar(10) DEFAULT NULL,
  `m_field` varchar(16) NOT NULL,
  `hashed` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`m_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.
-- 테이블 yellow.no_waybill 구조 내보내기
CREATE TABLE IF NOT EXISTS `no_waybill` (
  `o_id` int(10) unsigned NOT NULL,
  `o_name` varchar(10) NOT NULL,
  `o_tel` varchar(20) NOT NULL,
  `o_address` varchar(80) NOT NULL,
  `o_time` datetime NOT NULL,
  PRIMARY KEY (`o_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.
-- 테이블 yellow.orders 구조 내보내기
CREATE TABLE IF NOT EXISTS `orders` (
  `o_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `o_name` varchar(10) NOT NULL,
  `o_tel` varchar(20) NOT NULL,
  `o_address` varchar(80) NOT NULL,
  `o_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `shopcode` varchar(10) NOT NULL,
  `total` int(20) DEFAULT NULL,
  `shippay` int(20) DEFAULT NULL,
  PRIMARY KEY (`o_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10116 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.
-- 테이블 yellow.orders_detail 구조 내보내기
CREATE TABLE IF NOT EXISTS `orders_detail` (
  `d_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `o_id` int(10) NOT NULL,
  `p_id` int(11) NOT NULL,
  `p_name` varchar(40) NOT NULL,
  `o_quantity` int(11) NOT NULL,
  PRIMARY KEY (`d_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1346 DEFAULT CHARSET=utf8;

-- 테이블 데이터 yellow.member:~11 rows (대략적) 내보내기
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` (`m_id`, `m_password`, `m_name`, `m_tel`, `m_job`, `m_field`, `hashed`) VALUES
	('admin', '*', '관리자', '010-1234-5678', '0', '관리자', '$2a$10$njHk8srHzFBi..sFPn/6wuU3WXMs6/BbEweBYNYmXKk9ofwgoS//u'),
	('bed', '*', '에이스침대', '010-1245-5677', '1', 'B', '$2a$10$naryfhsqcfNwm9N9qiLRvOfQHODb/FP1RNT6G6WRswirp69sDHoOe'),
	('cabinet', '*', '한샘캐비넷', '010-1145-8945', '1', 'D', '$2a$10$p8s8AsrdsgV6dizxBghs5eF.oEy9ylLOyTl43WwCSuVOAWJYMMIgm'),
	('daehan', '*', '대한통운', '010-3489-1318', '2', 'a', '$2a$10$tHtEMUPOFnwQUL2X0Y.xTeCKmYrzXzkFIGqDc0ELQ8b7umMGYjkDa'),
	('hanjin', '*', '한진택배', '010-8523-4512', '2', 'c', '$2a$10$/alMvFLlYFRRtbkP9l/.f.AuD.XDN8ZSF0VJtUfjQiegI56YxosCG'),
	('led', '*', '조명나라', '010-5612-8546', '1', 'E', '$2a$10$ntTVF29bm3G9UqK4LhvwCOdwdRJJckhzU/aHx9UnI47bpLeMxNedO'),
	('logen', '*', '로젠택배', '010-3265-4212', '2', 'd', '$2a$10$ArJfrbLJ4l6SvTKq82qK.eQQDZTOjtu0xF.El7TIrigPjAL2nZ2Da'),
	('lotte', '*', '롯데택배', '010-8451-1233', '2', 'e', '$2a$10$jb6YUTNnIDDBMmkyhmRjoO9T0aaFfXjjg.fDBo2zJfeAePv./Xoaq'),
	('post', '*', '우체국', '010-6548-7822', '2', 'b', '$2a$10$pPpRyim8m.CzL.GbX/vrm.sLLpDBGqA0imY6J1nhM9U9y9LN7noZG'),
	('table', '*', 'IKEA', '010-7874-5610', '1', 'C', '$2a$10$tMjtnHqScYSgIvZGuC5n/.B6fGMwbDO2DNZ3dYgm5AHpSZcsfHcN.'),
	('tree', '*', '나무향기', '010-6489-1358', '1', 'A', '$2a$10$iJ2O2n/uFJ1wEjg1CxfBGeSg2kwkf5EOVdG6rWAI1XJJWhd2CyiU.');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;

-- 테이블 yellow.product 구조 내보내기
CREATE TABLE IF NOT EXISTS `product` (
  `p_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `p_name` varchar(40) DEFAULT NULL,
  `p_img` varchar(80) NOT NULL,
  `p_price` varchar(16) NOT NULL,
  `p_quantity` int(11) NOT NULL,
  `buycode` varchar(10) NOT NULL,
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- 테이블 데이터 yellow.product:~30 rows (대략적) 내보내기
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` (`p_id`, `p_name`, `p_img`, `p_price`, `p_quantity`, `buycode`) VALUES
	(1, 'DANCI 1인소파', 'img/bg-img/1.jpg', '300000', 3, 'A'),
	(2, 'LILLBERG 1인소파', 'img/bg-img/2.jpg', '180000', 11, 'A'),
	(3, 'DUHO 의자', 'img/bg-img/3.jpg', '120000', 3, 'A'),
	(4, 'BONESEN 2인소파베드', 'img/bg-img/4.jpg', '300000', 6, 'A'),
	(5, 'BUGOK 3인소파', 'img/bg-img/5.jpg', '300000', 3, 'A'),
	(6, 'MAKA 에펠의자', 'img/bg-img/6.jpg', '20000', 3, 'A'),
	(7, 'MARINO 분리형 이층침대 트윈싱글', 'img/bg-img/7.jpg', '170000', 9, 'B'),
	(8, 'FIERA 원목침대', 'img/bg-img/8.jpg', '170000', 12, 'B'),
	(9, 'FRUGA 원목침대 더블', 'img/bg-img/9.jpg', '120000', 10, 'B'),
	(10, 'BERGEN 데이베드 침대', 'img/bg-img/10.jpg', '140000', 10, 'B'),
	(11, 'DROI 원목이층침대', 'img/bg-img/11.jpg', '270000', 6, 'B'),
	(12, 'NINO 원목유아침대', 'img/bg-img/12.jpg', '63000', 10, 'B'),
	(13, 'TETOS 책상', 'img/bg-img/13.jpg', '40000', 5, 'C'),
	(14, 'KASY 커피테이블', 'img/bg-img/14.jpg', '24000', 15, 'C'),
	(15, 'SIDON 원목테이블', 'img/bg-img/15.jpg', '720000', 9, 'C'),
	(16, 'ANTHOR 담요', 'img/bg-img/30.jpg', '90000', 3, 'E'),
	(17, 'TIANO 전신거울', 'img/bg-img/29.jpg', '300000', 9, 'E'),
	(18, 'KULJAM 펫베드', 'img/bg-img/28.jpg', '26000', 14, 'E'),
	(19, 'PLUID 펜던트등', 'img/bg-img/27.jpg', '30000', 15, 'E'),
	(20, 'RETRO 펜던트등', 'img/bg-img/26.jpg', '50000', 11, 'E'),
	(21, 'RUSTA 장스탠드', 'img/bg-img/25.jpg', '18000', 3, 'E'),
	(22, 'SAMONY 캔들워머', 'img/bg-img/24.jpg', '30000', 4, 'E'),
	(23, 'JIYA 원목TV스탠드', 'img/bg-img/23.jpg', '500000', 13, 'D'),
	(24, 'KRUSS 선반', 'img/bg-img/22.jpg', '200000', 12, 'D'),
	(25, 'ESENI 선반/행거', 'img/bg-img/21.jpg', '280000', 3, 'D'),
	(26, 'MKB 캐비넷', 'img/bg-img/20.jpg', '60000', 3, 'D'),
	(27, 'LEITER 선반', 'img/bg-img/19.jpg', '20000', 15, 'D'),
	(28, 'PECRE 빈티지테이블', 'img/bg-img/18.jpg', '500000', 12, 'C'),
	(29, 'DUGED 사이드테이블', 'img/bg-img/17.jpg', '16000', 15, 'C'),
	(30, 'MKB 서랍협탁', 'img/bg-img/16.jpg', '40000', 3, 'C');

-- 내보낼 데이터가 선택되어 있지 않습니다.
-- 테이블 yellow.waybill 구조 내보내기
CREATE TABLE IF NOT EXISTS `waybill` (
  `w_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `o_id` int(20) NOT NULL,
  `o_name` varchar(10) NOT NULL,
  `o_tel` varchar(20) NOT NULL,
  `o_address` varchar(80) NOT NULL,
  `w_waycode` varchar(20) DEFAULT NULL,
  `o_time` datetime DEFAULT NULL,
  `w_time` datetime DEFAULT NULL,
  PRIMARY KEY (`w_id`)
) ENGINE=InnoDB AUTO_INCREMENT=90075 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
