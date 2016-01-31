-- phpMyAdmin SQL Dump
-- version 4.5.4.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 31, 2016 at 11:46 PM
-- Server version: 10.1.11-MariaDB-log
-- PHP Version: 7.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `vvv_db`
--
CREATE DATABASE IF NOT EXISTS `vvv_db` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `vvv_db`;

-- --------------------------------------------------------

--
-- Table structure for table `cidades`
--

DROP TABLE IF EXISTS `cidades`;
CREATE TABLE `cidades` (
  `id_cidade` int(11) NOT NULL,
  `nome` varchar(63) COLLATE utf8_unicode_ci NOT NULL,
  `identificador` char(3) COLLATE utf8_unicode_ci NOT NULL,
  `codigo` varchar(15) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `modais`
--

DROP TABLE IF EXISTS `modais`;
CREATE TABLE `modais` (
  `id_modal` int(11) NOT NULL,
  `tipo` varchar(31) COLLATE utf8_unicode_ci NOT NULL,
  `codigo` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `companhia` varchar(63) COLLATE utf8_unicode_ci NOT NULL,
  `capacidade` int(11) UNSIGNED NOT NULL,
  `modelo` varchar(63) COLLATE utf8_unicode_ci NOT NULL,
  `ano_fabricacao` int(11) NOT NULL,
  `em_manutencao` tinyint(1) NOT NULL DEFAULT '0',
  `em_uso` tinyint(1) NOT NULL DEFAULT '0',
  `data_manutencao` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `passageiros`
--

DROP TABLE IF EXISTS `passageiros`;
CREATE TABLE `passageiros` (
  `id_passageiro` int(11) NOT NULL,
  `id_pessoa` int(11) NOT NULL,
  `id_passageiro_responsavel` int(11) DEFAULT NULL,
  `cpf` char(11) COLLATE utf8_unicode_ci NOT NULL,
  `telefone` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `profissao` varchar(63) COLLATE utf8_unicode_ci DEFAULT NULL,
  `data_de_nascimento` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `percursos`
--

DROP TABLE IF EXISTS `percursos`;
CREATE TABLE `percursos` (
  `id_percurso` int(11) NOT NULL,
  `id_modal` int(11) DEFAULT NULL,
  `id_cidade_partida` int(11) NOT NULL,
  `id_cidade_chegada` int(11) NOT NULL,
  `hora_partida` datetime NOT NULL,
  `horas_duracao` decimal(8,1) NOT NULL,
  `codigo_aeroporto` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `codigo` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `pessoas`
--

DROP TABLE IF EXISTS `pessoas`;
CREATE TABLE `pessoas` (
  `id_pessoa` int(11) NOT NULL,
  `nome` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `endereco` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `codigo` varchar(15) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `pontos_de_venda`
--

DROP TABLE IF EXISTS `pontos_de_venda`;
CREATE TABLE `pontos_de_venda` (
  `id_ponto_de_venda` int(11) NOT NULL,
  `nome` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `endereco` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `telefone` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `codigo` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `cnpj` char(14) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `reservas`
--

DROP TABLE IF EXISTS `reservas`;
CREATE TABLE `reservas` (
  `id_reserva` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_viagem` int(11) NOT NULL,
  `codigo` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `data_da_reserva` datetime NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `valor` decimal(8,2) UNSIGNED NOT NULL,
  `tipo_pagamento` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `qtd_parcelas` int(11) UNSIGNED NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `reservas_passageiros`
--

DROP TABLE IF EXISTS `reservas_passageiros`;
CREATE TABLE `reservas_passageiros` (
  `id_reserva_passageiro` int(11) NOT NULL,
  `id_reserva` int(11) NOT NULL,
  `id_passageiro` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tickets`
--

DROP TABLE IF EXISTS `tickets`;
CREATE TABLE `tickets` (
  `id_ticket` int(11) NOT NULL,
  `id_reserva` int(11) NOT NULL,
  `id_percurso` int(11) NOT NULL,
  `numero` int(11) UNSIGNED NOT NULL,
  `id_localizador` varchar(15) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL,
  `id_pessoa` int(11) NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `senha` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `nivel_permissao` tinyint(3) UNSIGNED NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `viagens`
--

DROP TABLE IF EXISTS `viagens`;
CREATE TABLE `viagens` (
  `id_viagem` int(11) NOT NULL,
  `nome_do_pacote` varchar(63) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lotacao` int(10) UNSIGNED NOT NULL,
  `data_partida` datetime NOT NULL,
  `data_chegada` datetime NOT NULL,
  `codigo` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `viagens_percursos`
--

DROP TABLE IF EXISTS `viagens_percursos`;
CREATE TABLE `viagens_percursos` (
  `id_viagem_percurso` int(11) NOT NULL,
  `id_viagem` int(11) NOT NULL,
  `id_percurso` int(11) NOT NULL,
  `ordem` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cidades`
--
ALTER TABLE `cidades`
  ADD PRIMARY KEY (`id_cidade`),
  ADD UNIQUE KEY `codigo` (`codigo`);

--
-- Indexes for table `modais`
--
ALTER TABLE `modais`
  ADD PRIMARY KEY (`id_modal`),
  ADD UNIQUE KEY `codigo` (`codigo`);

--
-- Indexes for table `passageiros`
--
ALTER TABLE `passageiros`
  ADD PRIMARY KEY (`id_passageiro`),
  ADD UNIQUE KEY `cpf` (`cpf`),
  ADD KEY `id_pessoa` (`id_pessoa`),
  ADD KEY `responsavel` (`id_passageiro_responsavel`);

--
-- Indexes for table `percursos`
--
ALTER TABLE `percursos`
  ADD PRIMARY KEY (`id_percurso`),
  ADD UNIQUE KEY `codigo` (`codigo`),
  ADD KEY `id_cidade_partida` (`id_cidade_partida`),
  ADD KEY `id_cidade_chegada` (`id_cidade_chegada`),
  ADD KEY `id_modal` (`id_modal`);

--
-- Indexes for table `pessoas`
--
ALTER TABLE `pessoas`
  ADD PRIMARY KEY (`id_pessoa`),
  ADD UNIQUE KEY `codigo` (`codigo`);

--
-- Indexes for table `pontos_de_venda`
--
ALTER TABLE `pontos_de_venda`
  ADD PRIMARY KEY (`id_ponto_de_venda`),
  ADD UNIQUE KEY `codigo` (`codigo`);

--
-- Indexes for table `reservas`
--
ALTER TABLE `reservas`
  ADD PRIMARY KEY (`id_reserva`),
  ADD UNIQUE KEY `codigo` (`codigo`),
  ADD KEY `id_viagem` (`id_viagem`),
  ADD KEY `id_usuario` (`id_usuario`) USING BTREE;

--
-- Indexes for table `reservas_passageiros`
--
ALTER TABLE `reservas_passageiros`
  ADD PRIMARY KEY (`id_reserva_passageiro`),
  ADD KEY `id_reserva` (`id_reserva`),
  ADD KEY `id_passageiro` (`id_passageiro`);

--
-- Indexes for table `tickets`
--
ALTER TABLE `tickets`
  ADD PRIMARY KEY (`id_ticket`),
  ADD UNIQUE KEY `id_localizador` (`id_localizador`),
  ADD KEY `id_reserva` (`id_reserva`),
  ADD KEY `id_percurso` (`id_percurso`);

--
-- Indexes for table `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `id_pessoa` (`id_pessoa`);

--
-- Indexes for table `viagens`
--
ALTER TABLE `viagens`
  ADD PRIMARY KEY (`id_viagem`),
  ADD UNIQUE KEY `codigo` (`codigo`);

--
-- Indexes for table `viagens_percursos`
--
ALTER TABLE `viagens_percursos`
  ADD PRIMARY KEY (`id_viagem_percurso`),
  ADD KEY `id_viagem` (`id_viagem`),
  ADD KEY `id_percurso` (`id_percurso`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cidades`
--
ALTER TABLE `cidades`
  MODIFY `id_cidade` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `modais`
--
ALTER TABLE `modais`
  MODIFY `id_modal` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `passageiros`
--
ALTER TABLE `passageiros`
  MODIFY `id_passageiro` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `percursos`
--
ALTER TABLE `percursos`
  MODIFY `id_percurso` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `pessoas`
--
ALTER TABLE `pessoas`
  MODIFY `id_pessoa` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `pontos_de_venda`
--
ALTER TABLE `pontos_de_venda`
  MODIFY `id_ponto_de_venda` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `reservas`
--
ALTER TABLE `reservas`
  MODIFY `id_reserva` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `reservas_passageiros`
--
ALTER TABLE `reservas_passageiros`
  MODIFY `id_reserva_passageiro` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `tickets`
--
ALTER TABLE `tickets`
  MODIFY `id_ticket` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `viagens`
--
ALTER TABLE `viagens`
  MODIFY `id_viagem` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `viagens_percursos`
--
ALTER TABLE `viagens_percursos`
  MODIFY `id_viagem_percurso` int(11) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `passageiros`
--
ALTER TABLE `passageiros`
  ADD CONSTRAINT `passageiros_ibfk_1` FOREIGN KEY (`id_pessoa`) REFERENCES `pessoas` (`id_pessoa`) ON DELETE CASCADE,
  ADD CONSTRAINT `passageiros_ibfk_2` FOREIGN KEY (`id_passageiro_responsavel`) REFERENCES `passageiros` (`id_passageiro`) ON DELETE CASCADE;

--
-- Constraints for table `percursos`
--
ALTER TABLE `percursos`
  ADD CONSTRAINT `percursos_ibfk_1` FOREIGN KEY (`id_cidade_partida`) REFERENCES `cidades` (`id_cidade`) ON DELETE CASCADE,
  ADD CONSTRAINT `percursos_ibfk_2` FOREIGN KEY (`id_cidade_chegada`) REFERENCES `cidades` (`id_cidade`) ON DELETE CASCADE,
  ADD CONSTRAINT `percursos_ibfk_3` FOREIGN KEY (`id_modal`) REFERENCES `modais` (`id_modal`) ON DELETE SET NULL;

--
-- Constraints for table `reservas`
--
ALTER TABLE `reservas`
  ADD CONSTRAINT `reservas_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE,
  ADD CONSTRAINT `reservas_ibfk_2` FOREIGN KEY (`id_viagem`) REFERENCES `viagens` (`id_viagem`) ON DELETE CASCADE;

--
-- Constraints for table `reservas_passageiros`
--
ALTER TABLE `reservas_passageiros`
  ADD CONSTRAINT `reservas_passageiros_ibfk_1` FOREIGN KEY (`id_reserva`) REFERENCES `reservas` (`id_reserva`) ON DELETE CASCADE,
  ADD CONSTRAINT `reservas_passageiros_ibfk_2` FOREIGN KEY (`id_passageiro`) REFERENCES `passageiros` (`id_passageiro`) ON DELETE CASCADE;

--
-- Constraints for table `tickets`
--
ALTER TABLE `tickets`
  ADD CONSTRAINT `tickets_ibfk_1` FOREIGN KEY (`id_reserva`) REFERENCES `reservas` (`id_reserva`) ON DELETE CASCADE,
  ADD CONSTRAINT `tickets_ibfk_2` FOREIGN KEY (`id_percurso`) REFERENCES `percursos` (`id_percurso`) ON DELETE CASCADE;

--
-- Constraints for table `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`id_pessoa`) REFERENCES `pessoas` (`id_pessoa`) ON DELETE CASCADE;

--
-- Constraints for table `viagens_percursos`
--
ALTER TABLE `viagens_percursos`
  ADD CONSTRAINT `viagens_percursos_ibfk_1` FOREIGN KEY (`id_viagem`) REFERENCES `viagens` (`id_viagem`) ON DELETE CASCADE,
  ADD CONSTRAINT `viagens_percursos_ibfk_2` FOREIGN KEY (`id_percurso`) REFERENCES `percursos` (`id_percurso`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
