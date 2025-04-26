-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 26, 2025 at 09:22 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cc103_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `issued_tools_tbl`
--

CREATE TABLE `issued_tools_tbl` (
  `IssueID` int(11) NOT NULL,
  `ToolID` int(11) NOT NULL,
  `StudentID` int(11) NOT NULL,
  `IssueDate` date NOT NULL,
  `DueDate` date NOT NULL,
  `ReturnDate` date DEFAULT NULL,
  `Status` enum('Issued','Returned','Overdue') DEFAULT 'Issued'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `maintenance_reports_tbl`
--

CREATE TABLE `maintenance_reports_tbl` (
  `ReportID` int(11) NOT NULL,
  `ToolID` int(11) NOT NULL,
  `IssueDescription` text NOT NULL,
  `ReportDate` date DEFAULT curdate(),
  `Status` enum('Pending','Resolved') DEFAULT 'Pending'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `students_tbl`
--

CREATE TABLE `students_tbl` (
  `UserID` int(11) NOT NULL,
  `YearAndCourse` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tools_tbl`
--

CREATE TABLE `tools_tbl` (
  `ToolID` int(11) NOT NULL,
  `ToolName` varchar(255) NOT NULL,
  `Condition` varchar(255) DEFAULT NULL,
  `Quantity` int(11) DEFAULT 0,
  `Location` varchar(255) DEFAULT NULL,
  `Status` enum('Available','Issued','Under Maintenance') DEFAULT 'Available'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tools_tbl`
--

INSERT INTO `tools_tbl` (`ToolID`, `ToolName`, `Condition`, `Quantity`, `Location`, `Status`) VALUES
(2, 'Goofy', 'Damaged', 3, 'Local Storage', 'Available'),
(3, 'goofy ahh', 'Used - Good', 2, '3', 'Available');

-- --------------------------------------------------------

--
-- Table structure for table `users_tbl`
--

CREATE TABLE `users_tbl` (
  `ID` int(11) NOT NULL,
  `Username` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `Full Name` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `Phone Number` varchar(20) DEFAULT NULL,
  `Gender` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users_tbl`
--

INSERT INTO `users_tbl` (`ID`, `Username`, `Password`, `Full Name`, `Email`, `Phone Number`, `Gender`) VALUES
(1, 'test', 'testtest', 'test test', 'test', '1', 'Male');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `issued_tools_tbl`
--
ALTER TABLE `issued_tools_tbl`
  ADD PRIMARY KEY (`IssueID`),
  ADD KEY `ToolID` (`ToolID`),
  ADD KEY `StudentID` (`StudentID`),
  ADD KEY `idx_issue_status` (`Status`);

--
-- Indexes for table `maintenance_reports_tbl`
--
ALTER TABLE `maintenance_reports_tbl`
  ADD PRIMARY KEY (`ReportID`),
  ADD KEY `ToolID` (`ToolID`);

--
-- Indexes for table `students_tbl`
--
ALTER TABLE `students_tbl`
  ADD PRIMARY KEY (`UserID`),
  ADD KEY `idx_student_user` (`UserID`);

--
-- Indexes for table `tools_tbl`
--
ALTER TABLE `tools_tbl`
  ADD PRIMARY KEY (`ToolID`),
  ADD KEY `idx_tool_status` (`Status`);

--
-- Indexes for table `users_tbl`
--
ALTER TABLE `users_tbl`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Username` (`Username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `issued_tools_tbl`
--
ALTER TABLE `issued_tools_tbl`
  MODIFY `IssueID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `maintenance_reports_tbl`
--
ALTER TABLE `maintenance_reports_tbl`
  MODIFY `ReportID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tools_tbl`
--
ALTER TABLE `tools_tbl`
  MODIFY `ToolID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `users_tbl`
--
ALTER TABLE `users_tbl`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `issued_tools_tbl`
--
ALTER TABLE `issued_tools_tbl`
  ADD CONSTRAINT `issued_tools_tbl_ibfk_1` FOREIGN KEY (`ToolID`) REFERENCES `tools_tbl` (`ToolID`),
  ADD CONSTRAINT `issued_tools_tbl_ibfk_2` FOREIGN KEY (`StudentID`) REFERENCES `students_tbl` (`UserID`);

--
-- Constraints for table `maintenance_reports_tbl`
--
ALTER TABLE `maintenance_reports_tbl`
  ADD CONSTRAINT `maintenance_reports_tbl_ibfk_1` FOREIGN KEY (`ToolID`) REFERENCES `tools_tbl` (`ToolID`);

--
-- Constraints for table `students_tbl`
--
ALTER TABLE `students_tbl`
  ADD CONSTRAINT `students_tbl_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `users_tbl` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
