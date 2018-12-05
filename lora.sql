-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Dec 05, 2018 at 12:22 PM
-- Server version: 5.7.19
-- PHP Version: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `lora`
--

-- --------------------------------------------------------

--
-- Table structure for table `encrypted_data`
--

DROP TABLE IF EXISTS `encrypted_data`;
CREATE TABLE IF NOT EXISTS `encrypted_data` (
  `time` varchar(25) NOT NULL,
  `dest` int(10) NOT NULL,
  `src` int(10) NOT NULL,
  `packetno` int(10) NOT NULL,
  `packetlength` int(10) NOT NULL,
  `encrypted_data` varchar(250) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `encrypted_data`
--

INSERT INTO `encrypted_data` (`time`, `dest`, `src`, `packetno`, `packetlength`, `encrypted_data`) VALUES
('1/30/2018 10:29:18 AM', 8, 3, 9, 24, '8AEBB779B8584989F8F2244DC4A890D704134D\r\n'),
('1/30/2018 10:29:25 AM', 8, 3, 10, 24, '8AEBB779B8584B85F8F2244DC4A890D704134D\r\n'),
('1/30/2018 10:29:31 AM', 8, 3, 11, 24, '8AEBB779B8584B88F8F2244DC4A890D704134D\r\n'),
('1/30/2018 10:29:38 AM', 8, 3, 12, 24, '8AEBB779B8584A84F8F2244DC4A890D704134D\r\n'),
('1/30/2018 10:29:44 AM', 8, 3, 13, 24, '8AEBB779B8584A81F8F2244DC4A890D704134D\r\n'),
('1/30/2018 10:29:51 AM', 8, 3, 14, 24, '8AEBB779B8584B88F8F2244DC4A890D704134D\r\n'),
('1/30/2018 10:29:57 AM', 8, 3, 15, 24, '8AEBB779B8584B82F8F2244DC4A890D704134D\r\n'),
('1/30/2018 10:31:50 AM', 8, 2, 10, 24, '8AEBB779B8584B86F8F2244DC4A890D704134D\r\n'),
('1/30/2018 10:31:57 AM', 8, 2, 11, 24, '8AEBB779B8584A82F8F2244DC4A890D704134D\r\n'),
('1/30/2018 10:32:03 AM', 8, 2, 12, 24, '8AEBB779B8584B89F8F2244DC4A890D704134D\r\n'),
('1/30/2018 10:32:10 AM', 8, 2, 13, 24, '8AEBB779B8584A86F8F2244DC4A890D704134D\r\n'),
('1/30/2018 10:32:16 AM', 8, 2, 14, 24, '8AEBB779B8584A86F8F2244DC4A890D704134D\r\n'),
('1/30/2018 10:32:23 AM', 8, 2, 15, 24, '8AEBB779B8584B85F8F2244DC4A890D704134D\r\n'),
('1/30/2018 10:32:29 AM', 8, 2, 16, 24, '8AEBB779B8584A82F8F2244CC4AB99DD0E1F48\r\n');

-- --------------------------------------------------------

--
-- Stand-in structure for view `kar`
-- (See below for the actual view)
--
DROP VIEW IF EXISTS `kar`;
CREATE TABLE IF NOT EXISTS `kar` (
`time` varchar(500)
,`dest` int(10)
,`src` int(10)
,`packetno` int(10)
,`packetlength` int(10)
,`retry` int(10)
,`payload` varchar(500)
,`actual_msg` varchar(500)
);

-- --------------------------------------------------------

--
-- Table structure for table `log_lora`
--

DROP TABLE IF EXISTS `log_lora`;
CREATE TABLE IF NOT EXISTS `log_lora` (
  `time` varchar(500) NOT NULL,
  `dest` int(5) NOT NULL,
  `src` int(5) NOT NULL,
  `packetno` int(5) NOT NULL,
  `packetlength` int(5) NOT NULL,
  `retry` int(5) NOT NULL,
  `payload_hex` varchar(500) NOT NULL,
  `ldr` bigint(25) NOT NULL,
  `NTC` double(10,7) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `log_lora`
--

INSERT INTO `log_lora` (`time`, `dest`, `src`, `packetno`, `packetlength`, `retry`, `payload_hex`, `ldr`, `NTC`) VALUES
('1/23/2018 3:29:23 PM', 8, 2, 2, 25, 0, '6667680A203837300A2032332E3234323138380A\r\n', 870, 23.2421880),
('1/23/2018 3:29:30 PM', 8, 2, 3, 25, 0, '6667680A203837350A2032332E3234323138380A\r\n', 875, 23.2421880),
('1/23/2018 3:29:36 PM', 8, 2, 4, 25, 0, '6667680A203836310A2032332E3234323138380A\r\n', 861, 23.2421880),
('1/23/2018 3:29:43 PM', 8, 2, 5, 25, 0, '6667680A203837390A2032332E3234323138380A\r\n', 879, 23.2421880),
('1/23/2018 3:29:49 PM', 8, 2, 6, 25, 0, '6667680A203836320A2032332E3234323138380A\r\n', 862, 23.2421880);

-- --------------------------------------------------------

--
-- Table structure for table `log_node1`
--

DROP TABLE IF EXISTS `log_node1`;
CREATE TABLE IF NOT EXISTS `log_node1` (
  `time` varchar(25) NOT NULL,
  `dest` int(10) NOT NULL,
  `src` int(10) NOT NULL,
  `packetno` int(10) NOT NULL,
  `packetlength` int(10) NOT NULL,
  `retry` int(10) NOT NULL,
  `payload` varchar(250) NOT NULL,
  `actual_msg` varchar(500) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `log_node1`
--

INSERT INTO `log_node1` (`time`, `dest`, `src`, `packetno`, `packetlength`, `retry`, `payload`, `actual_msg`) VALUES
('1/17/2018 11:12:41 AM', 0, 2, 3, 18, 0, '5061636B6574323A5061726973\r\n', 'Packet2:Paris\r\n\r\n'),
('1/17/2018 11:12:46 AM', 8, 2, 4, 18, 0, '5061636B6574313A4E6F646532\r\n', 'Packet1:Node2\r\n\r\n'),
('1/17/2018 11:12:52 AM', 0, 2, 5, 18, 0, '5061636B6574323A5061726973\r\n', 'Packet2:Paris\r\n\r\n'),
('1/17/2018 11:12:57 AM', 8, 2, 6, 18, 0, '5061636B6574313A4E6F646532\r\n', 'Packet1:Node2\r\n\r\n'),
('1/17/2018 11:13:02 AM', 0, 2, 7, 18, 0, '5061636B6574323A5061726973\r\n', 'Packet2:Paris\r\n\r\n'),
('1/17/2018 11:13:08 AM', 8, 2, 8, 18, 0, '5061636B6574313A4E6F646532\r\n', 'Packet1:Node2\r\n\r\n'),
('1/17/2018 2:26:42 PM', 8, 3, 0, 18, 0, '5061636B6574313A4E6F646533\r\n', 'Packet1:Node3\r\n\r\n'),
('1/17/2018 2:26:47 PM', 0, 3, 1, 19, 0, '5061636B6574323A4C6F6E646F6E\r\n', 'Packet2:London\r\n\r\n'),
('1/17/2018 2:26:53 PM', 8, 3, 2, 18, 0, '5061636B6574313A4E6F646533\r\n', 'Packet1:Node3\r\n\r\n'),
('1/17/2018 2:26:58 PM', 0, 3, 3, 19, 0, '5061636B6574323A4C6F6E646F6E\r\n', 'Packet2:London\r\n\r\n'),
('1/17/2018 2:27:08 PM', 0, 0, 0, 0, 0, '', '');

-- --------------------------------------------------------

--
-- Table structure for table `log_node2`
--

DROP TABLE IF EXISTS `log_node2`;
CREATE TABLE IF NOT EXISTS `log_node2` (
  `time` varchar(500) NOT NULL,
  `dest` int(10) NOT NULL,
  `src` int(10) NOT NULL,
  `packetno` int(10) NOT NULL,
  `packetlength` int(10) NOT NULL,
  `retry` int(10) NOT NULL,
  `payload` varchar(500) NOT NULL,
  `actual_msg` varchar(500) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `log_node2`
--

INSERT INTO `log_node2` (`time`, `dest`, `src`, `packetno`, `packetlength`, `retry`, `payload`, `actual_msg`) VALUES
('1/17/2018 1:32:23 PM', 8, 3, 26, 18, 0, '5061636B6574313A4E6F646533\r\n', 'Packet1:Node3\r\n\r\n'),
('1/17/2018 1:32:28 PM', 0, 3, 27, 19, 0, '5061636B6574323A4C6F6E646F6E\r\n', 'Packet2:London\r\n\r\n'),
('1/17/2018 1:32:33 PM', 8, 3, 28, 18, 0, '5061636B6574313A4E6F646533\r\n', 'Packet1:Node3\r\n\r\n'),
('1/17/2018 1:32:38 PM', 0, 3, 29, 19, 0, '5061636B6574323A4C6F6E646F6E\r\n', 'Packet2:London\r\n\r\n'),
('1/17/2018 1:32:44 PM', 8, 3, 30, 18, 0, '5061636B6574313A4E6F646533\r\n', 'Packet1:Node3\r\n\r\n');

-- --------------------------------------------------------

--
-- Table structure for table `node2`
--

DROP TABLE IF EXISTS `node2`;
CREATE TABLE IF NOT EXISTS `node2` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time1` varchar(25) NOT NULL,
  `src` int(15) NOT NULL,
  `packetno` int(15) NOT NULL,
  `NTC1` double(8,6) NOT NULL,
  `LDR1` int(15) NOT NULL,
  UNIQUE KEY `unique` (`packetno`),
  KEY `id` (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `node2`
--

INSERT INTO `node2` (`id`, `time1`, `src`, `packetno`, `NTC1`, `LDR1`) VALUES
(1, '1/30/2018 10:29:18 AM', 3, 9, 28.613281, 859),
(2, '1/30/2018 10:29:25 AM', 3, 10, 28.613281, 875),
(3, '1/30/2018 10:29:31 AM', 3, 11, 28.613281, 878),
(4, '1/30/2018 10:29:38 AM', 3, 12, 28.613281, 864),
(5, '1/30/2018 10:29:44 AM', 3, 13, 28.613281, 861),
(6, '1/30/2018 10:29:51 AM', 3, 14, 28.613281, 878),
(7, '1/30/2018 10:29:57 AM', 3, 15, 28.613281, 872),
(8, '1/30/2018 10:32:29 AM', 2, 16, 29.589844, 862),
(9, '1/30/2018 6:29:23 PM', 2, 56, 28.698780, 789),
(10, '1/30/2018 6:30:23 PM', 2, 57, 28.698780, 850),
(11, '1/30/2018 6:29:23 PM', 2, 27, 28.698780, 789);

-- --------------------------------------------------------

--
-- Structure for view `kar`
--
DROP TABLE IF EXISTS `kar`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `kar`  AS  select `log_node2`.`time` AS `time`,`log_node2`.`dest` AS `dest`,`log_node2`.`src` AS `src`,`log_node2`.`packetno` AS `packetno`,`log_node2`.`packetlength` AS `packetlength`,`log_node2`.`retry` AS `retry`,`log_node2`.`payload` AS `payload`,`log_node2`.`actual_msg` AS `actual_msg` from `log_node2` order by `log_node2`.`actual_msg` desc ;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
