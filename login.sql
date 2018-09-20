-- phpMyAdmin SQL Dump
-- version 4.8.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 18, 2018 at 12:09 PM
-- Server version: 10.1.31-MariaDB
-- PHP Version: 7.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `login`
--

-- --------------------------------------------------------

--
-- Table structure for table `function`
--

CREATE TABLE `function` (
  `functionid` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `url` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `function`
--

INSERT INTO `function` (`functionid`, `name`, `url`) VALUES
(1, 'Update Transfer', 'updateTransfer'),
(2, 'Delete Transfer', 'deleteTransfer'),
(3, 'Make Deposit', 'createDeposit'),
(4, 'Make Withdraw', 'createWithdraw');

-- --------------------------------------------------------

--
-- Table structure for table `func_interface`
--

CREATE TABLE `func_interface` (
  `functionid` int(11) NOT NULL,
  `interfaceid` int(11) NOT NULL,
  `roleid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `func_interface`
--

INSERT INTO `func_interface` (`functionid`, `interfaceid`, `roleid`) VALUES
(1, 1, 1),
(2, 1, 1),
(3, 3, 1),
(3, 3, 2),
(4, 2, 1),
(4, 2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `interface`
--

CREATE TABLE `interface` (
  `interfaceid` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `url` varchar(50) NOT NULL,
  `description` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `interface`
--

INSERT INTO `interface` (`interfaceid`, `name`, `url`, `description`) VALUES
(1, 'Transfer', 'transfer', 'money transferring interface'),
(2, 'Withdraw', 'withdraw', 'money withdrawal interface'),
(3, 'Deposit', 'deposit', 'money deposit interface'),
(4, 'Statistics', 'statistic', 'viewing only to the admin');

-- --------------------------------------------------------

--
-- Table structure for table `privilage`
--

CREATE TABLE `privilage` (
  `roleid` int(11) NOT NULL,
  `interfaceid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `privilage`
--

INSERT INTO `privilage` (`roleid`, `interfaceid`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(2, 1),
(2, 2),
(2, 3);

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `roleid` int(11) NOT NULL,
  `rolename` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`roleid`, `rolename`) VALUES
(1, 'admin'),
(2, 'user');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userid` int(11) NOT NULL,
  `roleid` int(11) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userid`, `roleid`, `username`, `password`) VALUES
(1, 1, 'suren', 'suren@123'),
(2, 2, 'shalini', 'shalini@123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `function`
--
ALTER TABLE `function`
  ADD PRIMARY KEY (`functionid`),
  ADD KEY `functionid` (`functionid`,`name`,`url`);

--
-- Indexes for table `func_interface`
--
ALTER TABLE `func_interface`
  ADD KEY `functionid` (`functionid`,`interfaceid`,`roleid`),
  ADD KEY `interfaceid` (`interfaceid`),
  ADD KEY `roleid` (`roleid`);

--
-- Indexes for table `interface`
--
ALTER TABLE `interface`
  ADD PRIMARY KEY (`interfaceid`),
  ADD KEY `interfaceid` (`interfaceid`,`name`,`url`,`description`);

--
-- Indexes for table `privilage`
--
ALTER TABLE `privilage`
  ADD KEY `roleid` (`roleid`,`interfaceid`),
  ADD KEY `interfaceid` (`interfaceid`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`roleid`),
  ADD KEY `roleid` (`roleid`,`rolename`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userid`),
  ADD KEY `userid` (`userid`,`roleid`,`username`,`password`),
  ADD KEY `roleid` (`roleid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `function`
--
ALTER TABLE `function`
  MODIFY `functionid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `interface`
--
ALTER TABLE `interface`
  MODIFY `interfaceid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `roleid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `userid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `func_interface`
--
ALTER TABLE `func_interface`
  ADD CONSTRAINT `func_interface_ibfk_1` FOREIGN KEY (`interfaceid`) REFERENCES `interface` (`interfaceid`),
  ADD CONSTRAINT `func_interface_ibfk_2` FOREIGN KEY (`functionid`) REFERENCES `function` (`functionid`),
  ADD CONSTRAINT `func_interface_ibfk_3` FOREIGN KEY (`roleid`) REFERENCES `role` (`roleid`);

--
-- Constraints for table `privilage`
--
ALTER TABLE `privilage`
  ADD CONSTRAINT `privilage_ibfk_1` FOREIGN KEY (`interfaceid`) REFERENCES `interface` (`interfaceid`),
  ADD CONSTRAINT `privilage_ibfk_2` FOREIGN KEY (`roleid`) REFERENCES `role` (`roleid`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`roleid`) REFERENCES `role` (`roleid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
