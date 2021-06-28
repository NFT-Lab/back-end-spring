-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Giu 22, 2021 alle 13:42
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
-- Database: `transaction`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `transaction`
--

CREATE TABLE `transaction` (
  `id_hash` varchar(255) NOT NULL,
  `buyer` int(11) DEFAULT NULL,
  `seller` int(11) DEFAULT NULL,
  `price` varchar(255) DEFAULT NULL,
  `timestamp` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `transaction`
--

INSERT INTO `transaction` (`id_hash`, `buyer`, `seller`, `price`, `timestamp`) VALUES
('Qmb13ALEkqXtVXGxCSXJvAQNVwytCFcr5DT6jcrXuUGjat', 17, 18, '52.0ETH', '2021-06-14 12:07:03.8'),
('Qmb13ALEkqXtVXGxCSXJvAQNVwytCFcr5DT6jcrXuUGjat', 17, 17, '52.0ETH', '2021-06-14 12:15:05.531'),
('Qmb13ALEkqXtVXGxCSXJvAQNVwytCFcr5DT6jcrXuUGjat', 17, 17, '52.0ETH', '2021-06-14 12:15:23.352'),
('Qmb13ALEkqXtVXGxCSXJvAQNVwytCFcr5DT6jcrXuUGjat', 18, 17, '52.0ETH', '2021-06-14 12:16:31.912'),
('Qmb13ALEkqXtVXGxCSXJvAQNVwytCFcr5DT6jcrXuUGjat', 17, 18, '52.0ETH', '2021-06-14 12:19:58.051'),
('QmeZPWsUgH2MDmRMPpNYS4r6LRNh9kA8qmRqU9tPBxrbMh', 17, 18, '52.0ETH', '2021-06-10 13:56:52.219'),
('QmeZPWsUgH2MDmRMPpNYS4r6LRNh9kA8qmRqU9tPBxrbMh', 17, 17, '52.0ETH', '2021-06-10 14:02:05.56'),
('QmeZPWsUgH2MDmRMPpNYS4r6LRNh9kA8qmRqU9tPBxrbMh', 17, 17, '52.0ETH', '2021-06-10 14:06:43.476'),
('QmeZPWsUgH2MDmRMPpNYS4r6LRNh9kA8qmRqU9tPBxrbMh', 18, 17, '52.0ETH', '2021-06-10 14:12:32.48'),
('QmeZPWsUgH2MDmRMPpNYS4r6LRNh9kA8qmRqU9tPBxrbMh', 18, 18, '52.0ETH', '2021-06-10 14:13:30.206'),
('QmeZPWsUgH2MDmRMPpNYS4r6LRNh9kA8qmRqU9tPBxrbMh', 10, 18, '52.0ETH', '2021-06-10 14:14:45.532'),
('QmeZPWsUgH2MDmRMPpNYS4r6LRNh9kA8qmRqU9tPBxrbMh', 16, 10, '52.0ETH', '2021-06-10 14:32:23.069'),
('QmeZPWsUgH2MDmRMPpNYS4r6LRNh9kA8qmRqU9tPBxrbMh', 18, 16, '52.0ETH', '2021-06-10 16:15:22.841'),
('QmeZPWsUgH2MDmRMPpNYS4r6LRNh9kA8qmRqU9tPBxrbMh', 17, 18, '52.0ETH', '2021-06-10 16:16:12.78'),
('QmeZPWsUgH2MDmRMPpNYS4r6LRNh9kA8qmRqU9tPBxrbMh', 18, 17, '52.0ETH', '2021-06-14 09:57:32.2'),
('QmUZKcyxFm82CpsuRxkentY3zw8dRxGxHni8ggDuxQQYDP', 17, 18, '52.0ETH', '2021-06-14 12:20:59.139'),
('QmVdHSRSrg2f4NcW3FTf33T8fBDiEb34hY7Xed56fzK1Aq', 10, 18, '33.3ETH', '2021-06-09 17:35:42.237');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`id_hash`,`timestamp`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
