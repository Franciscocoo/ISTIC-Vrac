-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  jeu. 10 oct. 2019 à 23:24
-- Version du serveur :  5.7.24
-- Version de PHP :  7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `dsb`
--

-- --------------------------------------------------------

--
-- Structure de la table `modele`
--

DROP TABLE IF EXISTS `modele`;
CREATE TABLE IF NOT EXISTS `modele` (
  `ID` int(11) NOT NULL,
  `Longueur` double DEFAULT NULL,
  `Largeur` double DEFAULT NULL,
  `Technique` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `modele`
--

INSERT INTO `modele` (`ID`, `Longueur`, `Largeur`, `Technique`) VALUES
(1, 203, 314, 'Tempera sur panneau de bois'),
(2, 24, 21, 'Huile sur Toile'),
(4, 131, 175, 'Huile sur toile'),
(11, 56, 46, 'Huile sur toile'),
(12, 100, 65, 'Huile sur toile'),
(10, 64, 81, 'Huile sur toile'),
(5, 102, 145.5, 'Huile sur toile'),
(7, 72, 92, 'Huile sur toile'),
(6, 65, 80, 'Huile sur toile'),
(3, 80, 59, 'Huile sur toile'),
(9, 84, 42, 'Huile sur toile'),
(14, 40.5, 30.5, 'Huile sur carton'),
(15, 80, 80, 'Acrylique sur panneau'),
(13, 21.1, 33, 'Huile sur toile');

-- --------------------------------------------------------

--
-- Structure de la table `mouvement`
--

DROP TABLE IF EXISTS `mouvement`;
CREATE TABLE IF NOT EXISTS `mouvement` (
  `Nom` varchar(40) NOT NULL,
  `Description` varchar(100) DEFAULT NULL,
  `Siecle` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`Nom`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `mouvement`
--

INSERT INTO `mouvement` (`Nom`, `Description`, `Siecle`) VALUES
('Première renaissance Italienne', 'Evolutions techniques lumineuses', 'XV siecle'),
('Siecle d\'or Néerlandais', 'Peintures d\'histoire', 'XVI siecle'),
('Néo Classicisme', 'Retour au source de l\'art', 'XVIII siecle'),
('Impressionisme', 'Mouvement d\'impressions fugitives', 'XIX siecle'),
('Art Figuratif', 'Representation de forme/figure', 'XX siecle'),
('Cubisme', 'objets analysés, décomposés et réassemblés', 'XX siecle'),
('Art nouveau Autrichien', 'Rattaché à l\'art nouveau', 'XIX siecle'),
('Expressionisme', 'Projection subjective de la réalité', 'XX siecle'),
('Art Cinétique', 'Très relié à la sculpture', 'XX siecle'),
('Surréalisme', 'Utilise force psychique : inconscient, reve ...', 'XIX siecle');

-- --------------------------------------------------------

--
-- Structure de la table `peintre`
--

DROP TABLE IF EXISTS `peintre`;
CREATE TABLE IF NOT EXISTS `peintre` (
  `ID` int(11) NOT NULL,
  `nom` varchar(100) DEFAULT NULL,
  `ybirth` int(11) DEFAULT NULL,
  `ydeath` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `peintre`
--

INSERT INTO `peintre` (`ID`, `nom`, `ybirth`, `ydeath`) VALUES
(1, 'Sandro Botticelli', 1445, 1510),
(2, 'Johannes Vermeer', 1632, 1675),
(4, 'Pierre-Auguste Renoir', 1841, 1919),
(5, 'Berthe Morizot', 1841, 1895),
(6, 'Amedeo Modigliani', 1884, 1920),
(7, 'Claude Monet', 1840, 1926),
(8, 'Gustave Caillebotte', 1848, 1894),
(9, 'Vincent Van Gogh', 1853, 1894),
(10, 'Paul Cézanne', 1839, 1906),
(11, 'Georges Braque', 1882, 1963),
(12, 'Gustave Kilmt', 1862, 1918),
(13, 'Paul Klee', 1879, 1940),
(14, 'Victor Vasarély', 1908, 1997),
(15, 'Salvador Dali', 1904, 1989);

-- --------------------------------------------------------

--
-- Structure de la table `suit`
--

DROP TABLE IF EXISTS `suit`;
CREATE TABLE IF NOT EXISTS `suit` (
  `ID_Peintre` int(11) NOT NULL,
  `Nom_Mouvement` varchar(40) NOT NULL,
  PRIMARY KEY (`ID_Peintre`,`Nom_Mouvement`),
  KEY `Nom_Mouvement` (`Nom_Mouvement`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `suit`
--

INSERT INTO `suit` (`ID_Peintre`, `Nom_Mouvement`) VALUES
(1, 'Première renaissance Italienne'),
(2, 'Siecle d\'or Néerlandais'),
(4, 'Impressionisme'),
(5, 'Impressionisme'),
(6, 'Art Figuratif'),
(7, 'Impressionisme'),
(8, 'Impressionisme'),
(9, 'Impressionisme'),
(10, 'Impressionisme'),
(11, 'Cubisme'),
(12, 'Art nouveau Autrichien'),
(13, 'Expressionisme'),
(14, 'Art Cinétique'),
(15, 'Surréalisme');

-- --------------------------------------------------------

--
-- Structure de la table `tableau`
--

DROP TABLE IF EXISTS `tableau`;
CREATE TABLE IF NOT EXISTS `tableau` (
  `ID` int(11) NOT NULL,
  `Titre` varchar(100) DEFAULT NULL,
  `Date_` int(11) DEFAULT NULL,
  `Modele_ID` int(11) DEFAULT NULL,
  `Peintre_ID` int(11) DEFAULT NULL,
  `Nom_Mouvement` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `Modele_ID` (`Modele_ID`),
  KEY `Peintre_ID` (`Peintre_ID`),
  KEY `Nom_Mouvement` (`Nom_Mouvement`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `tableau`
--

INSERT INTO `tableau` (`ID`, `Titre`, `Date_`, `Modele_ID`, `Peintre_ID`, `Nom_Mouvement`) VALUES
(1, 'Persistance de la mémoire', 1931, 13, 15, 'Surréalisme'),
(2, 'Ter-A Vega Gyongly', 1968, 15, 14, 'Art Cinétique'),
(3, 'Lever de lune et coucher de soleil', 1919, 14, 13, 'Expressionisme'),
(4, 'Judith et Holopherne', 1901, 9, 12, 'Art nouveau Autrichien'),
(5, 'Composition avec as de trefle', 1913, 3, 11, 'Cubisme'),
(6, 'Nature morte aux pommes', 1890, 6, 10, 'Impressionisme'),
(7, 'Le café de nuit', 1888, 7, 9, 'Impressionisme'),
(8, 'Les raboteurs de parquets', 1875, 5, 8, 'Impressionisme'),
(9, 'Le pont de l\'Europe', 1877, 10, 7, 'Impressionisme'),
(10, 'Femme à l\'enventail', 1919, 12, 6, 'Art Figuratif'),
(11, 'Le berceau', 1872, 11, 5, 'Impressionisme'),
(12, 'Le moulin de la galette', 1876, 4, 4, 'Impressionisme'),
(14, 'La dentelière', 1669, 2, 2, 'Siecle d\'or Néerlandais'),
(15, 'Le printemps', 1482, 1, 1, 'Première renaissance Italienne');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
