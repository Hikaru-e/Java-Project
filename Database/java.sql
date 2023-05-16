-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 15, 2023 at 07:15 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `java`
--

-- --------------------------------------------------------

--
-- Table structure for table `departement`
--

CREATE TABLE `departement` (
  `id_dprt` varchar(20) NOT NULL,
  `nom_dprt` varchar(20) NOT NULL,
  `email_dprt` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `departement`
--

INSERT INTO `departement` (`id_dprt`, `nom_dprt`, `email_dprt`) VALUES
('1', 'Genie Electrique', 'genie.electrique@gmail.com'),
('2', 'Genie Procedees', 'genie.procedees@gmail.com'),
('3', 'Maths Info', 'maths.info@gmail.com'),
('4', 'Reseaux et cyber sec', 'iric@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `entreprise`
--

CREATE TABLE `entreprise` (
  `id_ent` varchar(20) NOT NULL,
  `raisone_sociale` varchar(20) NOT NULL,
  `adresse_ent` varchar(40) NOT NULL,
  `tele_ent` int(11) NOT NULL,
  `email_ent` varchar(40) NOT NULL,
  `responsable_ent` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `etape`
--

CREATE TABLE `etape` (
  `id_etape` varchar(20) NOT NULL,
  `duree_etape` varchar(20) NOT NULL,
  `etape_debut` date NOT NULL,
  `livraison` varchar(255) NOT NULL,
  `documentation` longtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `etudiant`
--

CREATE TABLE `etudiant` (
  `cne_etd` varchar(20) NOT NULL,
  `nom_etd` varchar(20) NOT NULL,
  `prenom_etd` varchar(20) NOT NULL,
  `email_etd` varchar(40) NOT NULL,
  `niveau_etd` varchar(20) NOT NULL,
  `password_etd` varchar(20) NOT NULL,
  `fk_nom_filiere` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `etudiant`
--

INSERT INTO `etudiant` (`cne_etd`, `nom_etd`, `prenom_etd`, `email_etd`, `niveau_etd`, `password_etd`, `fk_nom_filiere`) VALUES
('1', '1', '1', '1', '1', '1', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `filiere`
--

CREATE TABLE `filiere` (
  `nom_filiere` varchar(20) NOT NULL,
  `fk_id_dprt` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `filiere`
--

INSERT INTO `filiere` (`nom_filiere`, `fk_id_dprt`) VALUES
('Genie Electrique', '1'),
('Genie Procedees', '2'),
('Genie Informatique', '3'),
('Ingenierie de donnes', '3'),
('Reseaux et cyber sec', '4');

-- --------------------------------------------------------

--
-- Table structure for table `laboratoire`
--

CREATE TABLE `laboratoire` (
  `id_lab` varchar(20) NOT NULL,
  `nom_lab` varchar(20) NOT NULL,
  `email_lab` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `professeur`
--

CREATE TABLE `professeur` (
  `id_prf` varchar(20) NOT NULL,
  `nom_prf` varchar(20) NOT NULL,
  `prenom_prf` varchar(20) NOT NULL,
  `email_prf` varchar(20) NOT NULL,
  `grade` varchar(20) NOT NULL,
  `role_prf` varchar(20) NOT NULL,
  `password_prf` varchar(20) NOT NULL,
  `fk_id_lab` varchar(20) DEFAULT NULL,
  `fk_id_dprt` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `professeur`
--

INSERT INTO `professeur` (`id_prf`, `nom_prf`, `prenom_prf`, `email_prf`, `grade`, `role_prf`, `password_prf`, `fk_id_lab`, `fk_id_dprt`) VALUES
('1', '1', '1', 'admin', '1', 'admin', 'admin', NULL, NULL),
('2', '2', '2', '2', '2', 'professeur', '2', NULL, NULL),
('3', '3', '3', '3', '3', 'chef depart', '3', NULL, NULL),
('4', '4', '4', '4', '4', 'coord filiere', '4', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `prof_filiere`
--

CREATE TABLE `prof_filiere` (
  `fk_id_prof` varchar(20) NOT NULL,
  `fk_id_filiere` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `projet`
--

CREATE TABLE `projet` (
  `id_prj` varchar(20) NOT NULL,
  `type_prj` varchar(20) NOT NULL,
  `lieu_prj` varchar(20) NOT NULL,
  `titre_prj` varchar(20) NOT NULL,
  `date_depart` date NOT NULL,
  `duree_prj` float NOT NULL,
  `fk_cne_etd` varchar(20) DEFAULT NULL,
  `fk_id_prf` varchar(20) DEFAULT NULL,
  `fk_id_lab` varchar(20) DEFAULT NULL,
  `fk_id_ent` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `projet_etape`
--

CREATE TABLE `projet_etape` (
  `fk_id_prj` varchar(20) NOT NULL,
  `fk_id_etape` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `departement`
--
ALTER TABLE `departement`
  ADD PRIMARY KEY (`id_dprt`);

--
-- Indexes for table `entreprise`
--
ALTER TABLE `entreprise`
  ADD PRIMARY KEY (`id_ent`);

--
-- Indexes for table `etape`
--
ALTER TABLE `etape`
  ADD PRIMARY KEY (`id_etape`);

--
-- Indexes for table `etudiant`
--
ALTER TABLE `etudiant`
  ADD PRIMARY KEY (`cne_etd`),
  ADD KEY `fk_id_fil` (`fk_nom_filiere`);

--
-- Indexes for table `filiere`
--
ALTER TABLE `filiere`
  ADD PRIMARY KEY (`nom_filiere`),
  ADD KEY `fk_id_dprt` (`fk_id_dprt`);

--
-- Indexes for table `laboratoire`
--
ALTER TABLE `laboratoire`
  ADD PRIMARY KEY (`id_lab`);

--
-- Indexes for table `professeur`
--
ALTER TABLE `professeur`
  ADD PRIMARY KEY (`id_prf`),
  ADD KEY `fk_id_lab` (`fk_id_lab`),
  ADD KEY `fk_id_dprt` (`fk_id_dprt`);

--
-- Indexes for table `prof_filiere`
--
ALTER TABLE `prof_filiere`
  ADD PRIMARY KEY (`fk_id_prof`,`fk_id_filiere`),
  ADD KEY `fk_id_filiere` (`fk_id_filiere`);

--
-- Indexes for table `projet`
--
ALTER TABLE `projet`
  ADD PRIMARY KEY (`id_prj`),
  ADD KEY `fk_id_prf` (`fk_id_prf`),
  ADD KEY `fk_id_lab` (`fk_id_lab`),
  ADD KEY `fk_id_ent` (`fk_id_ent`),
  ADD KEY `projet_ibfk_4` (`fk_cne_etd`);

--
-- Indexes for table `projet_etape`
--
ALTER TABLE `projet_etape`
  ADD PRIMARY KEY (`fk_id_prj`,`fk_id_etape`),
  ADD KEY `fk_id_etape` (`fk_id_etape`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `etudiant`
--
ALTER TABLE `etudiant`
  ADD CONSTRAINT `etudiant_ibfk_1` FOREIGN KEY (`fk_nom_filiere`) REFERENCES `filiere` (`nom_filiere`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `filiere`
--
ALTER TABLE `filiere`
  ADD CONSTRAINT `filiere_ibfk_1` FOREIGN KEY (`fk_id_dprt`) REFERENCES `departement` (`id_dprt`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `professeur`
--
ALTER TABLE `professeur`
  ADD CONSTRAINT `professeur_ibfk_1` FOREIGN KEY (`fk_id_lab`) REFERENCES `laboratoire` (`id_lab`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `professeur_ibfk_2` FOREIGN KEY (`fk_id_dprt`) REFERENCES `departement` (`id_dprt`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `prof_filiere`
--
ALTER TABLE `prof_filiere`
  ADD CONSTRAINT `prof_filiere_ibfk_1` FOREIGN KEY (`fk_id_prof`) REFERENCES `professeur` (`id_prf`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `prof_filiere_ibfk_2` FOREIGN KEY (`fk_id_filiere`) REFERENCES `filiere` (`nom_filiere`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `projet`
--
ALTER TABLE `projet`
  ADD CONSTRAINT `projet_ibfk_1` FOREIGN KEY (`fk_id_prf`) REFERENCES `professeur` (`id_prf`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `projet_ibfk_2` FOREIGN KEY (`fk_id_lab`) REFERENCES `laboratoire` (`id_lab`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `projet_ibfk_3` FOREIGN KEY (`fk_id_ent`) REFERENCES `entreprise` (`id_ent`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `projet_ibfk_4` FOREIGN KEY (`fk_cne_etd`) REFERENCES `etudiant` (`cne_etd`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `projet_etape`
--
ALTER TABLE `projet_etape`
  ADD CONSTRAINT `projet_etape_ibfk_1` FOREIGN KEY (`fk_id_prj`) REFERENCES `projet` (`id_prj`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `projet_etape_ibfk_2` FOREIGN KEY (`fk_id_etape`) REFERENCES `etape` (`id_etape`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
