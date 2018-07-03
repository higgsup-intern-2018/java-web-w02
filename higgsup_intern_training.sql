-- phpMyAdmin SQL Dump
-- version 4.8.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jul 02, 2018 at 03:48 AM
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
-- Database: `higgsup_intern_training`
--

-- --------------------------------------------------------

--
-- Table structure for table `classroom`
--

CREATE TABLE `classroom` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `instructor_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `classroom`
--

INSERT INTO `classroom` (`id`, `name`, `description`, `instructor_id`) VALUES
(1001, 'Front-end', 'Tranning about HTML, CSS, jQuery, bootstrap', 2001),
(1002, 'Back-end', 'Focus on Java, web knowledge, technical.', 0),
(1003, 'QA', 'Tranning test skills, How to become a good Ssoftware quality assurance engineer', 2001),
(1004, 'Design', 'Effective communication skills. Plan for design. Be a Web designer, Not a Graphic designer. update new technology.', 2003);

-- --------------------------------------------------------

--
-- Table structure for table `enrollment`
--

CREATE TABLE `enrollment` (
  `id` bigint(20) NOT NULL,
  `student_id` bigint(20) NOT NULL,
  `classroom_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `enrollment`
--

INSERT INTO `enrollment` (`id`, `student_id`, `classroom_id`) VALUES
(4001, 3001, 1001),
(4002, 3002, 1001),
(4003, 3001, 1002),
(4004, 3003, 1003),
(4005, 3005, 1004),
(4006, 3007, 1004),
(4007, 3007, 1001),
(4008, 3004, 1002),
(4009, 3006, 1001),
(4010, 3006, 1003);

-- --------------------------------------------------------

--
-- Table structure for table `instructor`
--

CREATE TABLE `instructor` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `year_of_birth` int(11) NOT NULL,
  `address` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `instructor`
--

INSERT INTO `instructor` (`id`, `name`, `year_of_birth`, `address`) VALUES
(2001, 'Pham Xuan Dung', 1986, 'Ha Noi'),
(2002, 'Nguyen Huy Hung', 1992, 'Hai Duong'),
(2003, 'Nguuyen xuan Lam', 1984, 'Ha Nam');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `year_of_birth` int(11) NOT NULL,
  `address` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`id`, `name`, `year_of_birth`, `address`) VALUES
(3001, 'Pham Thi Hai Anh', 1996, 'Ha Noi'),
(3002, 'Ngo Thi Thuy', 1998, 'Ha Noi'),
(3003, 'Nguyen Thi Kim Oanh', 1996, 'Bac Ninh'),
(3004, 'Nguyen Thanh Luan', 1996, 'Hai Duong'),
(3005, 'Phan Thanh Hang', 1996, 'Ha Noi'),
(3006, 'Nguyen Van Tuan', 1997, 'Nam Dinh'),
(3007, 'Le Van Manh', 1994, 'Ha Nam');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `classroom`
--
ALTER TABLE `classroom`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `enrollment`
--
ALTER TABLE `enrollment`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `instructor`
--
ALTER TABLE `instructor`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `classroom`
--
ALTER TABLE `classroom`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1005;

--
-- AUTO_INCREMENT for table `enrollment`
--
ALTER TABLE `enrollment`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4012;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3010;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
