-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 05, 2015 at 02:18 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gestao_de_quartos`
--

-- --------------------------------------------------------

--
-- Table structure for table `hospede`
--

CREATE TABLE `hospede` (
  `idHospede` int(11) NOT NULL,
  `nomeHospede` varchar(45) DEFAULT NULL,
  `nascimentoHospede` date DEFAULT NULL,
  `enderecoHospede` varchar(45) DEFAULT NULL,
  `emailHospede` varchar(45) DEFAULT NULL,
  `telefoneHospede` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocupacao`
--

CREATE TABLE `ocupacao` (
  `idOcupacao` int(11) NOT NULL,
  `idQuarto` int(11) DEFAULT NULL,
  `idHospede` int(11) DEFAULT NULL,
  `dataCheckIN` date DEFAULT NULL,
  `dataCheckOUT` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `quarto`
--

CREATE TABLE `quarto` (
  `idQuarto` int(11) NOT NULL,
  `tipoQuarto` varchar(45) DEFAULT NULL,
  `totalHospedes` int(11) DEFAULT NULL,
  `disponibilidadeQuarto` varchar(45) DEFAULT NULL,
  `recursosQuarto` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `reserva`
--

CREATE TABLE `reserva` (
  `idReserva` int(11) NOT NULL,
  `idHospede` int(11) DEFAULT NULL,
  `dataReserva` date DEFAULT NULL,
  `statusReserva` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `serviosadicionais`
--

CREATE TABLE `serviosadicionais` (
  `idServiosAdicionais` int(11) NOT NULL,
  `idTipoServico` int(11) DEFAULT NULL,
  `idHospede` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `statuspagamento`
--

CREATE TABLE `statuspagamento` (
  `idStatusPagamento` int(11) NOT NULL,
  `idQuarto` int(11) DEFAULT NULL,
  `Preco` decimal(5,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tiposervico`
--

CREATE TABLE `tiposervico` (
  `idTipoServico` int(11) NOT NULL,
  `nomeServico` varchar(45) DEFAULT NULL,
  `precoServico` decimal(5,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `hospede`
--
ALTER TABLE `hospede`
  ADD PRIMARY KEY (`idHospede`);

--
-- Indexes for table `ocupacao`
--
ALTER TABLE `ocupacao`
  ADD PRIMARY KEY (`idOcupacao`),
  ADD KEY `fk_Ocupacao_Quarto1_idx` (`idQuarto`),
  ADD KEY `fk_Ocupacao_Hospede1_idx` (`idHospede`);

--
-- Indexes for table `quarto`
--
ALTER TABLE `quarto`
  ADD PRIMARY KEY (`idQuarto`);

--
-- Indexes for table `reserva`
--
ALTER TABLE `reserva`
  ADD PRIMARY KEY (`idReserva`),
  ADD KEY `fk_Reserva_Hospede_idx` (`idHospede`);

--
-- Indexes for table `serviosadicionais`
--
ALTER TABLE `serviosadicionais`
  ADD PRIMARY KEY (`idServiosAdicionais`),
  ADD KEY `fk_ServiosAdicionais_TipoServico1_idx` (`idTipoServico`),
  ADD KEY `fk_ServiosAdicionais_Hospede1_idx` (`idHospede`);

--
-- Indexes for table `statuspagamento`
--
ALTER TABLE `statuspagamento`
  ADD PRIMARY KEY (`idStatusPagamento`),
  ADD KEY `fk_StatusPagamento_Quarto1_idx` (`idQuarto`);

--
-- Indexes for table `tiposervico`
--
ALTER TABLE `tiposervico`
  ADD PRIMARY KEY (`idTipoServico`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `hospede`
--
ALTER TABLE `hospede`
  MODIFY `idHospede` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ocupacao`
--
ALTER TABLE `ocupacao`
  MODIFY `idOcupacao` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `quarto`
--
ALTER TABLE `quarto`
  MODIFY `idQuarto` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `reserva`
--
ALTER TABLE `reserva`
  MODIFY `idReserva` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `serviosadicionais`
--
ALTER TABLE `serviosadicionais`
  MODIFY `idServiosAdicionais` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tiposervico`
--
ALTER TABLE `tiposervico`
  MODIFY `idTipoServico` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `ocupacao`
--
ALTER TABLE `ocupacao`
  ADD CONSTRAINT `fk_Ocupacao_Hospede1` FOREIGN KEY (`idHospede`) REFERENCES `hospede` (`idHospede`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Ocupacao_Quarto1` FOREIGN KEY (`idQuarto`) REFERENCES `quarto` (`idQuarto`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `reserva`
--
ALTER TABLE `reserva`
  ADD CONSTRAINT `fk_Reserva_Hospede` FOREIGN KEY (`idHospede`) REFERENCES `hospede` (`idHospede`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `serviosadicionais`
--
ALTER TABLE `serviosadicionais`
  ADD CONSTRAINT `fk_ServiosAdicionais_Hospede1` FOREIGN KEY (`idHospede`) REFERENCES `hospede` (`idHospede`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_ServiosAdicionais_TipoServico1` FOREIGN KEY (`idTipoServico`) REFERENCES `tiposervico` (`idTipoServico`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `statuspagamento`
--
ALTER TABLE `statuspagamento`
  ADD CONSTRAINT `fk_StatusPagamento_Quarto1` FOREIGN KEY (`idQuarto`) REFERENCES `quarto` (`idQuarto`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
