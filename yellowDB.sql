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
) ENGINE=InnoDB AUTO_INCREMENT=50009 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- 테이블 데이터 yellow.buying:~4 rows (대략적) 내보내기
/*!40000 ALTER TABLE `buying` DISABLE KEYS */;
INSERT INTO `buying` (`b_id`, `p_id`, `p_name`, `p_img`, `p_price`, `p_quantity`, `b_time`, `buycode`) VALUES
	(50001, 6, 'MAKA 에펠의자', '../img/bg-img/6.jpg', '20000', 14, '2019-03-16 10:00:00', 'A'),
	(50002, 21, 'RUSTA 장스탠드', '.../img/bg-img/25.jpg', '18000', 6, '2019-04-16 10:00:00', 'E'),
	(50003, 22, 'SAMONY 캔들워머', '.../img/bg-img/24.jpg', '30000', 12, '2019-04-16 10:00:00', 'E'),
	(50004, 1, 'DANCI 1인소파', '../img/bg-img/1.jpg', '300000', 12, '2019-05-16 10:00:00', 'A'),
	(50005, 2, 'LILLBERG 1인소파', '../img/bg-img/2.jpg', '180000', 8, '2019-05-16 10:00:00', 'A'),
	(50006, 3, 'DUHO 의자', '../img/bg-img/3.jpg', '120000', 10, '2019-05-16 10:00:00', 'A'),
	(50007, 5, 'BUGOK 3인소파', '../img/bg-img/5.jpg', '300000', 6, '2019-05-16 10:00:00', 'A'),
	(50008, 6, 'MAKA 에펠의자', '../img/bg-img/6.jpg', '20000', 7, '2019-05-16 10:00:00', 'A');
/*!40000 ALTER TABLE `buying` ENABLE KEYS */;

-- 테이블 yellow.commodity 구조 내보내기
CREATE TABLE IF NOT EXISTS `commodity` (
  `c_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `p_id` int(20) NOT NULL,
  `c_basic` int(20) DEFAULT NULL,
  `c_in` int(20) DEFAULT NULL,
  `c_out` int(20) DEFAULT NULL,
  `c_close` int(20) DEFAULT NULL,
  `c_time` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;

-- 테이블 데이터 yellow.commodity:~30 rows (대략적) 내보내기
/*!40000 ALTER TABLE `commodity` DISABLE KEYS */;
/*!40000 ALTER TABLE `commodity` ENABLE KEYS */;

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

-- 테이블 yellow.no_waybill 구조 내보내기
CREATE TABLE IF NOT EXISTS `no_waybill` (
  `o_id` int(10) unsigned NOT NULL,
  `o_name` varchar(10) NOT NULL,
  `o_tel` varchar(20) NOT NULL,
  `o_address` varchar(80) NOT NULL,
  `o_time` datetime NOT NULL,
  PRIMARY KEY (`o_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 yellow.no_waybill:~2 rows (대략적) 내보내기
/*!40000 ALTER TABLE `no_waybill` DISABLE KEYS */;
INSERT INTO `no_waybill` (`o_id`, `o_name`, `o_tel`, `o_address`, `o_time`) VALUES
	(10022, '김현정', '010-7458-1315', '서울특별시 구로구 디지털로 288', '2019-05-15 14:54:00'),
	(10024, '한예슬', '010-4789-1313', '서울특별시 강북구 솔매로 132', '2019-05-15 14:54:00'),
	(10027, '지창욱', '010-1313-3489', '충청북도 단양군 가곡면 두산길', '2019-05-15 14:54:01');
/*!40000 ALTER TABLE `no_waybill` ENABLE KEYS */;

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
) ENGINE=InnoDB AUTO_INCREMENT=10032 DEFAULT CHARSET=utf8;

-- 테이블 데이터 yellow.orders:~21 rows (대략적) 내보내기
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` (`o_id`, `o_name`, `o_tel`, `o_address`, `o_time`, `shopcode`, `total`, `shippay`) VALUES
	(10001, '홍민지', '010-7777-7777', '대전시 동구 가양2동 153-9번지', '2019-03-15 13:53:45', 'shop1', NULL, NULL),
	(10002, '노연아', '010-8584-8454', '대전시 서구 만년동 강변A', '2019-03-15 13:53:45', 'shop1', NULL, NULL),
	(10003, '천세은', '010-1654-5823', '서울특별시 도봉구 도봉로 476', '2019-03-15 13:53:45', 'shop1', NULL, NULL),
	(10004, '정정화', '010-8465-7188', '대전시 동구 비례동 ', '2019-03-15 13:53:45', 'shop1', NULL, NULL),
	(10005, '강진희', '010-5692-8123', '세종특별자치시 시청대로 219', '2019-03-15 13:53:45', 'shop1', NULL, NULL),
	(10006, '정승아', '010-4897-1369', '대전시 유성구 궁동', '2019-03-15 13:53:45', 'shop1', NULL, NULL),
	(10007, '김자바', '010-2165-5118', '경상남도 양산시 평산5길 19', '2019-03-15 13:53:45', 'shop1', NULL, NULL),
	(10008, '박이젠', '010-6794-1358', '대구광역시 중구 동성로1길 26', '2019-03-15 13:53:45', 'shop1', NULL, NULL),
	(10009, '박지영', '010-7846-3154', '경기도 포천시 중앙로 58', '2019-03-15 13:53:45', 'shop1', NULL, NULL),
	(10010, '라이언', '010-4786-9318', '경상북도 구미시 산호대로 142-46', '2019-03-15 13:53:45', 'shop1', NULL, NULL),
	(10011, '어피치', '010-4584-6489', '광주광역시 광산구 도산로 33', '2019-04-15 13:56:57', 'shop2', NULL, NULL),
	(10012, '브이콘', '010-7345-3156', '부산광역시 서구 충무대로 56 송도 탑스빌 상가 3층', '2019-04-15 13:56:57', 'shop2', NULL, NULL),
	(10013, '프로도', '010-8643-5648', '경기도 가평군 가평읍 경춘로 1745', '2019-04-15 13:56:57', 'shop2', NULL, NULL),
	(10014, '김무지', '010-3489-8711', '경기도 이천시 마장면 서이천로634번길 104-82', '2019-04-15 13:56:57', 'shop2', NULL, NULL),
	(10015, '이네오', '010-9135-4812', '경상남도 김해시 능동로 197', '2019-04-15 13:56:57', 'shop2', NULL, NULL),
	(10016, '제이지', '010-4861-4886', '인천광역시 계양구 길마로 33 미추홀 프라자', '2019-04-15 13:56:57', 'shop2', NULL, NULL),
	(10017, '정튜브', '010-4878-5499', '대전광역시 대덕구 신탄진로 17', '2019-04-15 13:56:57', 'shop2', NULL, NULL),
	(10018, '타노스', '010-7849-0134', '전라북도 전주시 덕진구 기린대로 101', '2019-04-15 13:56:57', 'shop2', NULL, NULL),
	(10019, '손주연', '010-7088-4978', '강원도 강릉시 임영로 10', '2019-04-15 13:56:57', 'shop2', NULL, NULL),
	(10020, '추소정', '010-0653-7842', '강원도 춘천시 벌말길 49', '2019-04-15 13:56:57', 'shop2', NULL, NULL),
	(10021, '김지연', '010-4413-1385', '경기도 파주시 문산읍 돈유2로 91-1 ', '2019-05-15 14:54:00', 'shop3', NULL, NULL),
	(10022, '김현정', '010-7458-1315', '서울특별시 구로구 디지털로 288', '2019-05-15 14:54:00', 'shop3', NULL, NULL),
	(10023, '정소정', '010-9416-3156', '부산광역시 동래구 명서로110번길 13', '2019-05-15 14:54:00', 'shop3', NULL, NULL),
	(10024, '한예슬', '010-4789-1313', '서울특별시 강북구 솔매로 132', '2019-05-15 14:54:00', 'shop3', NULL, NULL),
	(10025, '김태희', '010-1134-7894', '경기도 의정부시 시민로 39', '2019-05-15 14:54:00', 'shop3', NULL, NULL),
	(10026, '이동욱', '010-1398-1231', '경상남도 창원시 의창구 용호로', '2019-05-15 14:54:01', 'shop3', NULL, NULL),
	(10027, '지창욱', '010-1313-3489', '충청북도 단양군 가곡면 두산길', '2019-05-15 14:54:01', 'shop3', NULL, NULL),
	(10028, '이민호', '010-3456-8797', '충청북도 제천시 하소천길 176', '2019-05-15 14:54:01', 'shop3', NULL, NULL),
	(10029, '김우빈', '010-6468-1356', '충청북도 청주시 흥덕구', '2019-05-15 14:54:01', 'shop3', NULL, NULL),
	(10030, '구혜선', '010-6465-7894', '강원도 속초시 영금정로 33', '2019-05-15 14:54:01', 'shop3', NULL, NULL),
	(10031, '안재현', '010-3417-1315', '강원도 강릉시 경강로2255번길', '2019-05-15 14:54:01', 'shop3', NULL, NULL);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;

-- 테이블 yellow.orders_detail 구조 내보내기
CREATE TABLE IF NOT EXISTS `orders_detail` (
  `d_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `o_id` int(10) NOT NULL,
  `p_id` int(11) NOT NULL,
  `p_name` varchar(40) NOT NULL,
  `o_quantity` int(11) NOT NULL,
  PRIMARY KEY (`d_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1094 DEFAULT CHARSET=utf8;

-- 테이블 데이터 yellow.orders_detail:~63 rows (대략적) 내보내기
/*!40000 ALTER TABLE `orders_detail` DISABLE KEYS */;
INSERT INTO `orders_detail` (`d_id`, `o_id`, `p_id`, `p_name`, `o_quantity`) VALUES
	(1001, 10001, 15, 'SIDON 원목테이블', 1),
	(1002, 10001, 17, 'DUGED 사이드테이블', 1),
	(1003, 10001, 13, 'TETOS 책상', 2),
	(1004, 10002, 24, 'SAMONY 캔들워머', 1),
	(1005, 10002, 23, 'JIYA 원목TV스탠드', 1),
	(1006, 10002, 26, 'RETRO 펜던트등', 2),
	(1007, 10003, 8, 'FIERA 원목침대', 1),
	(1008, 10003, 19, 'LEITER 선반', 5),
	(1009, 10003, 22, 'KRUSS 선반', 4),
	(1010, 10004, 20, 'MKB 캐비넷', 2),
	(1011, 10004, 6, 'MAKA 에펠의자', 6),
	(1012, 10004, 2, 'LILLBERG 1인소파', 3),
	(1013, 10005, 30, 'ANTHOR 담요', 5),
	(1014, 10005, 13, 'TETOS 책상', 1),
	(1015, 10005, 10, 'BERGEN 데이베드 침대', 1),
	(1016, 10006, 25, 'RUSTA 장스탠드', 6),
	(1017, 10006, 15, 'SIDON 원목테이블/벤치', 2),
	(1018, 10006, 11, '원목이층침대 트윈싱글', 1),
	(1019, 10007, 26, 'RETRO 펜던트등', 8),
	(1020, 10007, 28, 'KULJAM 펫베드', 1),
	(1021, 10007, 30, 'ANTHOR 담요', 2),
	(1022, 10008, 17, 'DUGED 사이드테이블', 1),
	(1023, 10008, 1, 'DANCI 1인소파', 2),
	(1024, 10008, 4, 'BONESEN 2인 소파베드', 1),
	(1025, 10009, 13, 'TETOS 책상', 2),
	(1026, 10009, 3, 'DUHO 의자', 4),
	(1027, 10009, 7, 'MARINO 분리형 이층침대 트윈싱글', 5),
	(1028, 10010, 6, 'MAKA 에펠의자', 8),
	(1029, 10010, 8, 'PLEVEN/FIERA 원목침대', 1),
	(1030, 10010, 5, 'BUGOK 3인소파', 2),
	(1031, 10011, 14, 'KASY 커피테이블', 8),
	(1032, 10011, 1, 'DANCI 1인소파', 1),
	(1033, 10011, 5, 'BUGOK 3인소파', 1),
	(1034, 10012, 29, 'TIANO 전신거울', 2),
	(1035, 10012, 8, 'PLEVEN/FIERA 원목침대', 1),
	(1036, 10012, 3, 'DUHO 의자', 4),
	(1037, 10013, 12, 'NINO 원목유아침대', 5),
	(1038, 10013, 5, 'BUGOK 3인소파', 1),
	(1039, 10013, 16, 'MKB 서랍협탁', 2),
	(1040, 10014, 4, 'BONESEN 2인소파베드', 2),
	(1041, 10014, 11, '원목이층침대 트윈싱글', 1),
	(1042, 10014, 13, 'TETOS 책상', 1),
	(1043, 10015, 22, 'KRUSS 선반', 5),
	(1044, 10015, 20, 'MKB 캐비넷 가로양문 TV스탠드', 1),
	(1045, 10015, 18, 'PECRE 빈티지테이블', 1),
	(1046, 10016, 9, 'FRUGA 원목침대 더블', 1),
	(1047, 10016, 1, 'DANCI 1인소파', 3),
	(1048, 10016, 30, 'ANTHOR 담요', 2),
	(1049, 10017, 2, 'LILLBERG 1인소파', 2),
	(1050, 10017, 5, 'BUGOK 3인소파', 1),
	(1051, 10017, 21, 'ESENI 선반/행거', 3),
	(1052, 10018, 5, 'BUGOK 3인소파', 1),
	(1053, 10018, 15, 'SIDON 원목테이블/벤치', 1),
	(1054, 10018, 23, 'JIYA 원목TV스탠드', 1),
	(1055, 10019, 21, 'ESENI 선반/행거', 3),
	(1056, 10019, 28, 'KULJAM 펫베드', 2),
	(1057, 10019, 22, 'KRUSS 선반', 3),
	(1058, 10020, 11, 'DROI 원목이층침대', 1),
	(1059, 10020, 6, 'MAKA 에펠의자', 2),
	(1060, 10020, 27, 'PLUID 펜던트등', 4),
	(1061, 10021, 28, 'KULJAM 펫베드', 5),
	(1062, 10021, 6, 'MAKA 에펠의자', 4),
	(1063, 10021, 2, 'LILLBERG 1인소파', 1),
	(1064, 10022, 16, 'MKB 서랍협탁', 2),
	(1065, 10022, 5, 'BUGOK 3인소파', 1),
	(1066, 10022, 1, 'DANCI 1인소파', 4),
	(1067, 10023, 1, 'DANCI 1인소파', 6),
	(1068, 10023, 7, 'MARINO 분리형 이층침대 트윈싱글', 1),
	(1069, 10023, 9, 'FRUGA 원목침대 더블', 1),
	(1070, 10024, 27, 'PLUID 펜던트등', 5),
	(1071, 10024, 22, 'KRUSS 선반', 4),
	(1072, 10024, 26, 'RETRO 펜던트등', 4),
	(1073, 10025, 7, 'MARINO 분리형 이층침대 트윈싱글', 1),
	(1074, 10025, 8, 'PLEVEN/FIERA 원목침대', 2),
	(1075, 10025, 6, 'MAKA 에펠의자', 1),
	(1076, 10026, 3, 'DUHO 의자', 2),
	(1077, 10026, 18, 'PECRE 빈티지테이블', 3),
	(1078, 10026, 27, 'PLUID 펜던트등 꽃병모양', 4),
	(1079, 10027, 16, 'MKB 서랍협탁', 2),
	(1080, 10027, 26, 'RETRO 펜던트등', 3),
	(1081, 10027, 30, 'ANTHOR 담요', 3),
	(1082, 10028, 10, 'BERGEN 데이베드 침대', 1),
	(1083, 10028, 26, 'RETRO 펜던트등', 2),
	(1084, 10028, 22, 'KRUSS 선반', 4),
	(1085, 10029, 23, 'JIYA 원목TV스탠드', 1),
	(1086, 10029, 2, 'LILLBERG 1인소파', 2),
	(1087, 10029, 19, 'LEITER 선반', 6),
	(1088, 10030, 18, 'PECRE 빈티지테이블', 8),
	(1089, 10030, 28, 'KULJAM 펫베드 타원', 4),
	(1090, 10030, 22, 'KRUSS 선반', 2),
	(1091, 10031, 19, 'LEITER 선반', 3),
	(1092, 10031, 14, 'KASY 커피테이블', 2),
	(1093, 10031, 16, 'MKB 서랍협탁', 2);
/*!40000 ALTER TABLE `orders_detail` ENABLE KEYS */;

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
	(1, 'DANCI 1인소파', '../img/bg-img/1.jpg', '300000', 15, 'A'),
	(2, 'LILLBERG 1인소파', '../img/bg-img/2.jpg', '180000', 15, 'A'),
	(3, 'DUHO 의자', '../img/bg-img/3.jpg', '120000', 15, 'A'),
	(4, 'BONESEN 2인소파베드', '../img/bg-img/4.jpg', '300000', 12, 'A'),
	(5, 'BUGOK 3인소파', '../img/bg-img/5.jpg', '300000', 15, 'A'),
	(6, 'MAKA 에펠의자', '../img/bg-img/6.jpg', '20000', 15, 'A'),
	(7, 'MARINO 분리형 이층침대 트윈싱글', '../img/bg-img/7.jpg', '170000', 8, 'B'),
	(8, 'FIERA 원목침대', '../img/bg-img/8.jpg', '170000', 10, 'B'),
	(9, 'FRUGA 원목침대 더블', '../img/bg-img/9.jpg', '120000', 13, 'B'),
	(10, 'BERGEN 데이베드 침대', '../img/bg-img/10.jpg', '140000', 13, 'B'),
	(11, 'DROI 원목이층침대', '../img/bg-img/11.jpg', '270000', 12, 'B'),
	(12, 'NINO 원목유아침대', '../img/bg-img/12.jpg', '63000', 10, 'B'),
	(13, 'TETOS 책상', '../img/bg-img/13.jpg', '40000', 9, 'C'),
	(14, 'KASY 커피테이블', '../img/bg-img/14.jpg', '24000', 5, 'C'),
	(15, 'SIDON 원목테이블', '../img/bg-img/15.jpg', '720000', 11, 'C'),
	(16, 'ANTHOR 담요', '.../img/bg-img/30.jpg', '90000', 11, 'E'),
	(17, 'TIANO 전신거울', '.../img/bg-img/29.jpg', '300000', 13, 'E'),
	(18, 'KULJAM 펫베드', '.../img/bg-img/28.jpg', '26000', 3, 'E'),
	(19, 'PLUID 펜던트등', '.../img/bg-img/27.jpg', '30000', 1, 'E'),
	(20, 'RETRO 펜던트등', '.../img/bg-img/26.jpg', '50000', 12, 'E'),
	(21, 'RUSTA 장스탠드', '.../img/bg-img/25.jpg', '18000', 15, 'E'),
	(22, 'SAMONY 캔들워머', '.../img/bg-img/24.jpg', '30000', 9, 'E'),
	(23, 'JIYA 원목TV스탠드', '.../img/bg-img/23.jpg', '500000', 12, 'D'),
	(24, 'KRUSS 선반', '.../img/bg-img/22.jpg', '200000', 14, 'D'),
	(25, 'ESENI 선반/행거', '.../img/bg-img/21.jpg', '280000', 9, 'D'),
	(26, 'MKB 캐비넷', '.../img/bg-img/20.jpg', '60000', 3, 'D'),
	(27, 'LEITER 선반', '.../img/bg-img/19.jpg', '20000', 7, 'D'),
	(28, 'PECRE 빈티지테이블', '.../img/bg-img/18.jpg', '500000', 3, 'C'),
	(29, 'DUGED 사이드테이블', '.../img/bg-img/17.jpg', '16000', 13, 'C'),
	(30, 'MKB 서랍협탁', '.../img/bg-img/16.jpg', '40000', 6, 'C');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;

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
) ENGINE=InnoDB AUTO_INCREMENT=90029 DEFAULT CHARSET=utf8;

-- 테이블 데이터 yellow.waybill:~19 rows (대략적) 내보내기
/*!40000 ALTER TABLE `waybill` DISABLE KEYS */;
INSERT INTO `waybill` (`w_id`, `o_id`, `o_name`, `o_tel`, `o_address`, `w_waycode`, `o_time`, `w_time`) VALUES
	(90001, 10010, '라이언', '010-4786-9318', '경상북도 구미시 산호대로 142-46', 'd', '2019-03-15 13:53:45', '2019-03-15 18:00:00'),
	(90002, 10009, '박지영', '010-7846-3154', '경기도 포천시 중앙로 58', 'a', '2019-03-15 13:53:45', '2019-03-15 18:00:00'),
	(90003, 10008, '박이젠', '010-6794-1358', '대구광역시 중구 동성로1길 26', 'd', '2019-03-15 13:53:45', '2019-03-15 18:00:00'),
	(90004, 10007, '김자바', '010-2165-5118', '경상남도 양산시 평산5길 19', 'd', '2019-03-15 13:53:45', '2019-03-15 18:00:00'),
	(90005, 10006, '정승아', '010-4897-1369', '대전시 유성구 궁동', 'b', '2019-03-15 13:53:45', '2019-03-15 18:00:00'),
	(90006, 10005, '강진희', '010-5692-8123', '세종특별자치시 시청대로 219', 'b', '2019-03-15 13:53:45', '2019-03-15 18:00:00'),
	(90007, 10004, '정정화', '010-8465-7188', '대전시 동구 비례동 ', 'b', '2019-03-15 13:53:45', '2019-03-15 18:00:00'),
	(90008, 10003, '천세은', '010-1654-5823', '서울특별시 도봉구 도봉로 476', 'a', '2019-03-15 13:53:45', '2019-03-15 18:00:00'),
	(90009, 10002, '노연아', '010-8584-8454', '대전시 서구 만년동 강변A', 'b', '2019-03-15 13:53:45', '2019-03-15 18:00:00'),
	(90010, 10001, '홍민지', '010-7777-7777', '대전시 동구 가양2동 153-9번지', 'b', '2019-03-15 13:53:45', '2019-03-15 18:00:00'),
	(90011, 10020, '추소정', '010-0653-7842', '강원도 춘천시 벌말길 49', 'e', '2019-04-15 13:56:57', '2019-04-15 18:00:00'),
	(90012, 10019, '손주연', '010-7088-4978', '강원도 강릉시 임영로 10', 'e', '2019-04-15 13:56:57', '2019-04-15 18:00:00'),
	(90013, 10018, '타노스', '010-7849-0134', '전라북도 전주시 덕진구 기린대로 101', 'c', '2019-04-15 13:56:57', '2019-04-15 18:00:00'),
	(90014, 10017, '정튜브', '010-4878-5499', '대전광역시 대덕구 신탄진로 17', 'b', '2019-04-15 13:56:57', '2019-04-15 18:00:00'),
	(90015, 10016, '제이지', '010-4861-4886', '인천광역시 계양구 길마로 33 미추홀 프라자', 'e', '2019-04-15 13:56:57', '2019-04-15 18:00:00'),
	(90016, 10015, '이네오', '010-9135-4812', '경상남도 김해시 능동로 197', 'd', '2019-04-15 13:56:57', '2019-04-15 18:00:00'),
	(90017, 10014, '김무지', '010-3489-8711', '경기도 이천시 마장면 서이천로634번길 104-82', 'a', '2019-04-15 13:56:57', '2019-04-15 18:00:00'),
	(90018, 10013, '프로도', '010-8643-5648', '경기도 가평군 가평읍 경춘로 1745', 'a', '2019-04-15 13:56:57', '2019-04-15 18:00:00'),
	(90019, 10012, '브이콘', '010-7345-3156', '부산광역시 서구 충무대로 56 송도 탑스빌 상가 3층', 'd', '2019-04-15 13:56:57', '2019-04-15 18:00:00'),
	(90020, 10011, '어피치', '010-4584-6489', '광주광역시 광산구 도산로 33', 'c', '2019-04-15 13:56:57', '2019-04-15 18:00:00'),
	(90021, 10031, '안재현', '010-3417-1315', '강원도 강릉시 경강로2255번길', 'e', '2019-05-15 14:54:01', '2019-05-15 18:00:00'),
	(90022, 10030, '구혜선', '010-6465-7894', '강원도 속초시 영금정로 33', 'e', '2019-05-15 14:54:01', '2019-05-15 18:00:00'),
	(90023, 10029, '김우빈', '010-6468-1356', '충청북도 청주시 흥덕구', 'b', '2019-05-15 14:54:01', '2019-05-15 18:00:00'),
	(90024, 10028, '이민호', '010-3456-8797', '충청북도 제천시 하소천길 176', 'b', '2019-05-15 14:54:01', '2019-05-15 18:00:00'),
	(90025, 10026, '이동욱', '010-1398-1231', '경상남도 창원시 의창구 용호로', 'd', '2019-05-15 14:54:01', '2019-05-15 18:00:00'),
	(90026, 10025, '김태희', '010-1134-7894', '경기도 의정부시 시민로 39', 'a', '2019-05-15 14:54:00', '2019-05-15 18:00:00'),
	(90027, 10023, '정소정', '010-9416-3156', '부산광역시 동래구 명서로110번길 13', 'd', '2019-05-15 14:54:00', '2019-05-15 18:00:00'),
	(90028, 10021, '김지연', '010-4413-1385', '경기도 파주시 문산읍 돈유2로 91-1 ', 'a', '2019-05-15 14:54:00', '2019-05-15 18:00:00');
/*!40000 ALTER TABLE `waybill` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
