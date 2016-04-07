-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 07, 2016 at 06:45 PM
-- Server version: 5.5.47-0ubuntu0.14.04.1
-- PHP Version: 5.5.9-1ubuntu4.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `task_management`
--

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `taskId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `comment` varchar(1024) COLLATE utf8_bin NOT NULL,
  `createdDate` varchar(255) COLLATE utf8_bin NOT NULL,
  `modifiedDate` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=4 ;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`id`, `taskId`, `userId`, `comment`, `createdDate`, `modifiedDate`) VALUES
(1, 1, 1, 'My first Comment', '2016-04-06 19:25:45', '2016-04-06 19:25:45'),
(2, 1, 6, 'My first Comment', '2016-04-06 19:35:52', '2016-04-06 19:35:52'),
(3, 1, 1, 'My first Comment', '2016-04-07 17:11:04', '2016-04-07 17:11:04');

-- --------------------------------------------------------

--
-- Table structure for table `task`
--

CREATE TABLE IF NOT EXISTS `task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  `taskAssignerId` int(11) NOT NULL,
  `taskDoerId` int(11) NOT NULL,
  `status` varchar(25) COLLATE utf8_bin NOT NULL,
  `assignDate` varchar(255) COLLATE utf8_bin NOT NULL,
  `dueDate` varchar(255) COLLATE utf8_bin NOT NULL,
  `createdDate` varchar(255) COLLATE utf8_bin NOT NULL,
  `modifiedDate` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=5 ;

--
-- Dumping data for table `task`
--

INSERT INTO `task` (`id`, `name`, `taskAssignerId`, `taskDoerId`, `status`, `assignDate`, `dueDate`, `createdDate`, `modifiedDate`) VALUES
(1, 'name', 1, 7, 'assigned', '2016-04-07', '2016-04-09', '2016-04-06 15:17:48', '2016-04-07 17:44:30'),
(2, 'First task', 1, 5, 'assigned', '2016-04-06', '2016-04-06', '2016-04-06 15:22:13', '2016-04-06 15:22:13'),
(3, 'First task', 1, 6, 'assigned', '2016-04-06', '2016-04-06', '2016-04-06 15:26:22', '2016-04-06 15:26:22'),
(4, 'First task', 1, 6, 'assigned', '2016-04-07', '2016-04-06', '2016-04-07 15:18:22', '2016-04-07 15:18:22');

-- --------------------------------------------------------

--
-- Table structure for table `task_log`
--

CREATE TABLE IF NOT EXISTS `task_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `taskId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `description` varchar(255) COLLATE utf8_bin NOT NULL,
  `createdDate` datetime NOT NULL,
  `modifiedDate` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=11 ;

--
-- Dumping data for table `task_log`
--

INSERT INTO `task_log` (`id`, `taskId`, `userId`, `description`, `createdDate`, `modifiedDate`) VALUES
(1, 1, 1, 'Task updated with status = assignedassignDate = 2016-04-06taskDoerId = 6dueDate = 2016-04-06', '2016-04-06 18:28:26', '2016-04-06 18:28:26'),
(2, 1, 1, 'Comment on task : My first Comment', '2016-04-06 19:25:45', '2016-04-06 19:25:45'),
(3, 1, 6, 'Comment on task : My first Comment', '2016-04-06 19:35:52', '2016-04-06 19:35:52'),
(4, 4, 1, 'Task created by user 1', '2016-04-07 15:18:22', '2016-04-07 15:18:22'),
(5, 1, 1, 'Comment on task : My first Comment', '2016-04-07 17:11:04', '2016-04-07 17:11:04'),
(6, 1, 1, 'Task updated with status = assigned, assignDate = 2016-04-07, taskDoerId = 58, dueDate = 2016-04-06', '2016-04-07 17:42:01', '2016-04-07 17:42:01'),
(7, 1, 1, 'Task updated with status = assigned, assignDate = 2016-04-07, taskDoerId = 7, dueDate = 2016-04-06', '2016-04-07 17:43:36', '2016-04-07 17:43:36'),
(8, 1, 1, 'Task updated with status = assigned, assignDate = 2016-04-07, taskDoerId = 7, dueDate = 2016-04-06', '2016-04-07 17:44:13', '2016-04-07 17:44:13'),
(9, 1, 1, 'Task updated with status = draft, taskDoerId = 7, dueDate = 2016-04-06', '2016-04-07 17:44:23', '2016-04-07 17:44:23'),
(10, 1, 1, 'Task updated with status = draft, taskDoerId = 7, dueDate = 2016-04-09', '2016-04-07 17:44:30', '2016-04-07 17:44:30');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  `address` varchar(255) COLLATE utf8_bin NOT NULL,
  `phone` varchar(12) COLLATE utf8_bin NOT NULL,
  `email` varchar(255) COLLATE utf8_bin NOT NULL,
  `gender` varchar(255) COLLATE utf8_bin NOT NULL,
  `dateOfBirth` varchar(255) COLLATE utf8_bin NOT NULL,
  `createdDate` varchar(255) COLLATE utf8_bin NOT NULL,
  `modifiedDate` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=8 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `address`, `phone`, `email`, `gender`, `dateOfBirth`, `createdDate`, `modifiedDate`) VALUES
(1, 'Manish', 'address', 'phone', 'abc@gmail.com', 'female', '1988-05-25', '2016-04-05 19:34:11', '2016-04-05 19:34:11'),
(2, 'Manish', 'address', 'phone', 'abc@gmail.com', 'female', '1988-05-25', '2016-04-06 11:52:32', '2016-04-06 11:52:32'),
(3, 'Manish', 'address', 'phone', 'abc@gmail.com', 'female', '1988-05-25', '2016-04-06 12:25:50', '2016-04-06 12:25:50'),
(4, 'Manish', 'address', 'phone', 'abc@gmail.com', 'female', '1988-05-25', '2016-04-06 13:09:52', '2016-04-06 13:09:52'),
(5, 'Manish', 'address', 'phone', 'abc@gmail.com', 'female', '1988-05-25', '2016-04-06 13:38:52', '2016-04-06 13:38:52'),
(6, 'Manish', 'address', 'phone', 'abc@gmail.com', 'female', '1988-05-25', '2016-04-06 13:38:55', '2016-04-06 13:38:55'),
(7, 'Manish', 'address', 'phone', 'abc@gmail.com', 'male', '1988-05-25', '2016-04-07 15:07:17', '2016-04-07 15:07:17');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
