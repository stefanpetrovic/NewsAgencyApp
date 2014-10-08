-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 09, 2014 at 12:52 AM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `novinskaagencija`
--

-- --------------------------------------------------------

--
-- Table structure for table `autor`
--

CREATE TABLE IF NOT EXISTS `autor` (
  `AutorID` int(11) NOT NULL,
  `Ime` varchar(20) NOT NULL,
  `Prezime` varchar(20) NOT NULL,
  PRIMARY KEY (`AutorID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `autor`
--

INSERT INTO `autor` (`AutorID`, `Ime`, `Prezime`) VALUES
(1, 'Marko', 'Markovic'),
(2, 'Petar', 'Petrovic');

-- --------------------------------------------------------

--
-- Table structure for table `broj`
--

CREATE TABLE IF NOT EXISTS `broj` (
  `BrojID` bigint(20) NOT NULL,
  `DatumIzdavanja` date DEFAULT NULL,
  `Tiraz` int(11) DEFAULT NULL,
  `Cena` double DEFAULT NULL,
  `Novine` int(11) DEFAULT NULL,
  `Urednik` int(11) DEFAULT NULL,
  PRIMARY KEY (`BrojID`),
  KEY `Urednik` (`Urednik`),
  KEY `Novine` (`Novine`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `broj`
--

INSERT INTO `broj` (`BrojID`, `DatumIzdavanja`, `Tiraz`, `Cena`, `Novine`, `Urednik`) VALUES
(7, '2014-09-01', 125, 256, 2, 2),
(10, '2014-09-01', 456, 456, 1, 2),
(11, '2014-09-05', 150, 100, 1, 2),
(12, NULL, 0, 0, NULL, NULL),
(13, '2014-10-06', 456, 456, 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `clanak`
--

CREATE TABLE IF NOT EXISTS `clanak` (
  `BrojID` bigint(20) NOT NULL,
  `ClanakID` bigint(20) NOT NULL,
  `Naslov` varchar(100) NOT NULL,
  `Sadrzaj` text NOT NULL,
  `Kategorija` int(11) NOT NULL,
  `Autor` int(11) NOT NULL,
  PRIMARY KEY (`BrojID`,`ClanakID`),
  KEY `fk_kategorija` (`Kategorija`),
  KEY `autorIndex` (`Autor`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `clanak`
--

INSERT INTO `clanak` (`BrojID`, `ClanakID`, `Naslov`, `Sadrzaj`, `Kategorija`, `Autor`) VALUES
(7, 2, 'asdfasdf', 'asdfasdfdsfggsdf', 1, 2),
(10, 1, 'dfgh', 'gfhdasdfasdfasdf<br>asdfasdfasdf<br>', 2, 1),
(11, 1, 'Prvi clanak', 'Sadrzaj prvog clanka izmenjen<br>asdf<br>asdf<br>', 1, 1),
(13, 1, 'Prvi clanak', 'asdfasdfasdf', 1, 1),
(13, 2, 'Drugi clanak', 'Sadrzaj drugog clanka <br><br><br>asdfasdfasdf<br>asdfasdf<br>', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `kategorija`
--

CREATE TABLE IF NOT EXISTS `kategorija` (
  `KategorijaID` int(11) NOT NULL,
  `Naziv` varchar(40) NOT NULL,
  PRIMARY KEY (`KategorijaID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kategorija`
--

INSERT INTO `kategorija` (`KategorijaID`, `Naziv`) VALUES
(1, 'Politika'),
(2, 'Ekonomija');

-- --------------------------------------------------------

--
-- Table structure for table `novine`
--

CREATE TABLE IF NOT EXISTS `novine` (
  `NovineID` int(11) NOT NULL,
  `Naziv` varchar(50) NOT NULL,
  PRIMARY KEY (`NovineID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `novine`
--

INSERT INTO `novine` (`NovineID`, `Naziv`) VALUES
(1, 'Politika'),
(2, 'Vecernje novosti');

-- --------------------------------------------------------

--
-- Table structure for table `urednik`
--

CREATE TABLE IF NOT EXISTS `urednik` (
  `UrednikID` int(11) NOT NULL,
  `Ime` varchar(20) NOT NULL,
  `Prezime` varchar(20) NOT NULL,
  `KorisnickoIme` varchar(20) NOT NULL,
  `KorisnickaSifra` varchar(20) NOT NULL,
  PRIMARY KEY (`UrednikID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `urednik`
--

INSERT INTO `urednik` (`UrednikID`, `Ime`, `Prezime`, `KorisnickoIme`, `KorisnickaSifra`) VALUES
(1, 'Ivan', 'Ivanovic', 'Ivan', 'Ivan'),
(2, 'Stefan', 'Petrovic', 'Stefan', 'Stefan');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `broj`
--
ALTER TABLE `broj`
  ADD CONSTRAINT `fk_novine` FOREIGN KEY (`Novine`) REFERENCES `novine` (`NovineID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_urednik` FOREIGN KEY (`Urednik`) REFERENCES `urednik` (`UrednikID`) ON UPDATE CASCADE;

--
-- Constraints for table `clanak`
--
ALTER TABLE `clanak`
  ADD CONSTRAINT `fk_autor` FOREIGN KEY (`Autor`) REFERENCES `autor` (`AutorID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_broj` FOREIGN KEY (`BrojID`) REFERENCES `broj` (`BrojID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_kategorija` FOREIGN KEY (`Kategorija`) REFERENCES `kategorija` (`KategorijaID`) ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
