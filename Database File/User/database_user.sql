-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Giu 22, 2021 alle 13:41
-- Versione del server: 10.4.11-MariaDB
-- Versione PHP: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `userstage`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `users`
--

CREATE TABLE `users` (
  `ID` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `date_of_birth` date NOT NULL,
  `password` varchar(255) NOT NULL,
  `wallet` varchar(256) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `users`
--

INSERT INTO `users` (`ID`, `name`, `surname`, `email`, `date_of_birth`, `password`, `wallet`) VALUES
(0, 'Luca', 'Tappo', 'lucaTappo@gmail.com', '1998-06-01', '182196649', '0x143f589648A4f0e2A16a2c4dA9b460668bE2F8C0'),
(10, 'Andrea', 'Tappo', 'andreaTappo@gmail.com', '1998-06-01', '182196649', '0x143f589648A4f0e2A16a2c4dA9b460668bE2F8C0'),
(11, 'Filippo', 'Tappo', 'filippoTappo@gmail.com', '1998-06-01', '182196649', '0x5517e1498EDf8bB4B76005fD8a81697043A7E928'),
(12, 'Mattia', 'Tappo', 'mattiaTappo@gmail.com', '1998-06-01', '182196649', '0x143f589648A4f0e2A16a2c4dA9b460668bE2F8C0'),
(14, 'James', 'Tappo', 'jamesTappo@gmail.com', '1998-06-01', '182196649', '0x143f589648A4f0e2A16a2c4dA9b460668bE2F8C0'),
(16, 'Franco', 'Tappo', 'francoTappo@gmail.com', '1998-06-01', '182196649', '0x390c7a1EA64e521B725b1869Ea054DDBF05F9abe'),
(17, 'Giacomo', 'Tappo', 'giacomoTappo@gmail.com', '1998-06-01', '182196649', '0x143f589648A4f0e2A16a2c4dA9b460668bE2F8C0'),
(18, 'Aldo', 'Tappo', 'aldoTappo@gmail.com', '1998-06-01', '182196649', '0x9042B2650bBfF24F1e3c6947AC903f291AC73B5c'),
(21, 'Anna', 'Tappo', 'annaTappo@gmail.com', '1998-06-01', '182196649', '0x143f589648A4f0e2A16a2c4dA9b460668bE2F8C0'),
(22, 'Margherita', 'Mitillo', 'marghe@gmail.com', '1991-02-06', '182196649', '0xE4Aa92574e0748a43E0f5baF915C2C95554486e0'),
(23, 'Michele', 'Baldisseri', 'michele@gmail.com', '1996-06-05', '182196649', '0xE4Aa92574e0748a43E0f5baF915C2C95554486e0'),
(24, 'Andrea', 'Tommasin', 'cecchin@gmail.com', '1994-02-02', '182196649', '0xfF05b87C6D1118F10d4f5532Da6135BD31FE4505'),
(25, 'Marco', 'Colino', 'marcolino@gmail.com', '1970-02-04', '182196649', '0x9C018459FecFD87A23ad5172a02C9AEF93D30787');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `users`
--
ALTER TABLE `users`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
