-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Giu 24, 2021 alle 15:07
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
-- Database: `nft`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `category`
--

INSERT INTO `category` (`id`, `name`) VALUES
(2, 'sport'),
(3, 'food');

-- --------------------------------------------------------

--
-- Struttura della tabella `opecat`
--

CREATE TABLE `opecat` (
  `opera` varchar(255) NOT NULL,
  `cat` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `opecat`
--

INSERT INTO `opecat` (`opera`, `cat`) VALUES
('QmauN5VgBiXEcCHpJPHf1ACfKazYUwUPUs7jaF6pQXaaMS', 2),
('QmauN5VgBiXEcCHpJPHf1ACfKazYUwUPUs7jaF6pQXaaMS', 3),
('Qmb13ALEkqXtVXGxCSXJvAQNVwytCFcr5DT6jcrXuUGjat', 2),
('QmcWQUsCpnokDK1PHAhZrp48jmVGVnGX1gNdBpKoaJyrxA', 3),
('QmdE5MpTpX5L3b5nLs5Wvotfj1ShRdaASYpSoDumfbPhmv', 3),
('QmdUd5xNckDbJtx4jnwe39gXm1ga3CDv5XVDx9wS8VP7vX', 2),
('QmeMqcvLKU1insP5VS9KFym5gVw5XaEm3mbGsj1b6DhKUe', 2),
('QmPXsdgG3dwJ7w7NcKA2JkshXAvYKyZ3Rxmh8sTzavxhWo', 2),
('QmSefG3BGmTkAYKZtq45SFJQ7uoU5FEcQT4J83FpaKmxNX', 2),
('QmTqHoqaVWguo65Do25XydtVUos7SLVyRX8CjuzLakkB45', 3),
('test', 2);

-- --------------------------------------------------------

--
-- Struttura della tabella `opera`
--

CREATE TABLE `opera` (
  `id` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `description` text DEFAULT NULL,
  `author_id` int(11) NOT NULL,
  `price` double NOT NULL,
  `currency` varchar(255) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `path` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `user_id` int(11) NOT NULL,
  `token_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `opera`
--

INSERT INTO `opera` (`id`, `title`, `description`, `author_id`, `price`, `currency`, `status`, `path`, `type`, `user_id`, `token_id`) VALUES
('QmauN5VgBiXEcCHpJPHf1ACfKazYUwUPUs7jaF6pQXaaMS', 'letter y', 'letter y', 17, 12345, 'ETH', 0, 'gallery/img/QmauN5VgBiXEcCHpJPHf1ACfKazYUwUPUs7jaF6pQXaaMS.png', '3', 17, 17),
('Qmb13ALEkqXtVXGxCSXJvAQNVwytCFcr5DT6jcrXuUGjat', 'Space City', 'bella città spaziale', 18, 52, 'ETH', 0, 'gallery/imgQmb13ALEkqXtVXGxCSXJvAQNVwytCFcr5DT6jcrXuUGjat.jpg', '3', 17, 2),
('QmcWQUsCpnokDK1PHAhZrp48jmVGVnGX1gNdBpKoaJyrxA', 'letter V', 'letter V', 17, 123, 'ETH', 0, 'gallery/img/QmcWQUsCpnokDK1PHAhZrp48jmVGVnGX1gNdBpKoaJyrxA.png', '3', 17, 13),
('QmdE5MpTpX5L3b5nLs5Wvotfj1ShRdaASYpSoDumfbPhmv', 'Islandscape', 'Paesaggio con isola in qualche posto', 25, 12333, 'ETH', 0, 'QmdE5MpTpX5L3b5nLs5Wvotfj1ShRdaASYpSoDumfbPhmv.jpg', '3', 25, 20),
('QmdUd5xNckDbJtx4jnwe39gXm1ga3CDv5XVDx9wS8VP7vX', 'letter B', 'lettera B', 22, 52, 'ETH', 0, 'gallery/imgQmdUd5xNckDbJtx4jnwe39gXm1ga3CDv5XVDx9wS8VP7vX.png', '3', 22, 4),
('QmeMqcvLKU1insP5VS9KFym5gVw5XaEm3mbGsj1b6DhKUe', 'letter o', 'letter o', 22, 123, 'ETH', 0, 'gallery/img/QmeMqcvLKU1insP5VS9KFym5gVw5XaEm3mbGsj1b6DhKUe.jpg', '3', 22, 18),
('QmeZPWsUgH2MDmRMPpNYS4r6LRNh9kA8qmRqU9tPBxrbMh', 'Grafico calcolo numerico', 'Grafico', 18, 52, 'ETH', 0, 'gallery/imgQmeZPWsUgH2MDmRMPpNYS4r6LRNh9kA8qmRqU9tPBxrbMh.png', '3', 18, 1),
('QmPXsdgG3dwJ7w7NcKA2JkshXAvYKyZ3Rxmh8sTzavxhWo', 'letter k', 'letter k', 17, 12.55, 'ETH', 0, 'gallery/img/QmPXsdgG3dwJ7w7NcKA2JkshXAvYKyZ3Rxmh8sTzavxhWo.jpg', '3', 17, 16),
('QmSefG3BGmTkAYKZtq45SFJQ7uoU5FEcQT4J83FpaKmxNX', 'letter u', 'letter U', 17, 1234, 'ETH', 0, 'gallery/img/QmSefG3BGmTkAYKZtq45SFJQ7uoU5FEcQT4J83FpaKmxNX.png', '3', 17, 15),
('QmTdbXUqpQirMgztYazHxgodWvcLeNh6XUETi7NroDEoWo', 'letter F', 'letter F', 22, 52, 'ETH', 0, 'gallery/imgQmTdbXUqpQirMgztYazHxgodWvcLeNh6XUETi7NroDEoWo.jpg', '3', 22, 7),
('QmTqHoqaVWguo65Do25XydtVUos7SLVyRX8CjuzLakkB45', 'Il bel tramonto', 'Un paesaggio con un bel tramonto.', 24, 123.45, 'ETH', 0, 'QmTqHoqaVWguo65Do25XydtVUos7SLVyRX8CjuzLakkB45.jpg', '3', 24, 19),
('QmUnicCcPbxEdtY8mTXhHJAKhLy9525VH6qZPjKJ88jjs7', 'letter z', 'letter Z', 22, 1, 'ETH', 0, 'gallery/img/QmUnicCcPbxEdtY8mTXhHJAKhLy9525VH6qZPjKJ88jjs7.jpg', '3', 22, 12),
('QmURNbjxeGPUa1dCDL2EUDa91F7BBdC8VWLyDiKCpcMJnA', 'letter D', 'letter D', 22, 52, 'ETH', 0, 'gallery/imgQmURNbjxeGPUa1dCDL2EUDa91F7BBdC8VWLyDiKCpcMJnA.png', '3', 22, 5),
('QmUZKcyxFm82CpsuRxkentY3zw8dRxGxHni8ggDuxQQYDP', 'Space', 'Lo spazio', 18, 52, 'ETH', 0, 'gallery/imgQmUZKcyxFm82CpsuRxkentY3zw8dRxGxHni8ggDuxQQYDP.png', '3', 17, 3),
('QmVdHSRSrg2f4NcW3FTf33T8fBDiEb34hY7Xed56fzK1Aq', 'Grafico matematica', 'Matematica', 18, 44.3, 'ETH', 0, 'gallery/imgQmVdHSRSrg2f4NcW3FTf33T8fBDiEb34hY7Xed56fzK1Aq.png', '3', 10, 3),
('QmWL7s9U7csBmVAgSDEGUAWsxYSQqqNv51e1AheWH7e8Vc', 'letter J', 'letter J', 22, 52, 'ETH', 0, 'gallery/img/QmWL7s9U7csBmVAgSDEGUAWsxYSQqqNv51e1AheWH7e8Vc.png', '3', 22, 11),
('test', 'letter I', 'letter I', 22, 52, 'ETH', 0, 'gallery/img', '3', 22, 999);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `opecat`
--
ALTER TABLE `opecat`
  ADD PRIMARY KEY (`opera`,`cat`),
  ADD UNIQUE KEY `Id_Opera` (`opera`,`cat`),
  ADD KEY `Id_Cat` (`cat`);

--
-- Indici per le tabelle `opera`
--
ALTER TABLE `opera`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `opecat`
--
ALTER TABLE `opecat`
  ADD CONSTRAINT `opecat_ibfk_1` FOREIGN KEY (`opera`) REFERENCES `opera` (`id`),
  ADD CONSTRAINT `opecat_ibfk_2` FOREIGN KEY (`cat`) REFERENCES `category` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
