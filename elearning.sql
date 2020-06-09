-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  mar. 02 juin 2020 à 00:15
-- Version du serveur :  10.1.38-MariaDB
-- Version de PHP :  7.2.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `elearning`
--

-- --------------------------------------------------------

--
-- Structure de la table `cours`
--

CREATE TABLE `cours` (
  `id` bigint(20) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `titre` varchar(255) NOT NULL,
  `filiere` bigint(20) DEFAULT NULL,
  `id_user` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `cours`
--

INSERT INTO `cours` (`id`, `created_date`, `description`, `titre`, `filiere`, `id_user`) VALUES
(1, '2020-06-01 20:54:23', 'il y a 5 fichiers :\r\n- le premier contient : Les instructions d\'entrées-sorties et   Les structures Conditionnelles ;\r\n- le deuxième contient : Les boucles;\r\n-le troisième contient : Les Tableaux, Fonctions et Procédures;\r\n- le quatrième contient : La Récursivité et la Complexité;\r\n- et le dernière fichier contient Les Tableaux:\r\n\r\n', 'Algorithmique', 1, 2),
(2, '2020-06-01 21:02:24', 'Ce cours Contient :\r\n-Instructions, Expressions et Opérateurs;\r\n-Les structures de contrôle;\r\n-La récursivité;\r\n-Les conversions de types;\r\n-Principales fonctions d’entrées-sorties standard;\r\n', 'Langage C', 1, 2),
(3, '2020-06-01 21:07:39', '-Le tri à bulle; \r\n-Le tri par sélection; \r\n-Le tri par insertion; \r\n-Le tri par fusion; \r\n-Le tri rapide;\r\n', 'Les tris', 2, 5),
(6, '2020-06-01 21:28:30', '-cours diagramme de cas d\'utilisation;\r\n- et cours diagramme de classe;', 'Uml', 2, 5),
(7, '2020-06-01 21:31:38', 'POO :\r\n– objets, classes, Encapsulation, Héritage,\r\npolymorphisme.\r\n• Programmation C++ :\r\n– spécificités du C++, classes et objets, fonctions\r\nmembres, constructeur de recopie,\r\n– fonctions amies, surcharge des opérateurs, héritage\r\nsimple,\r\n– héritage multiple, classe abstraite, les flots, la gestion\r\ndes exceptions.', 'c++', 2, 5),
(8, '2020-06-01 21:42:00', '-Partie 1:Généralité sur les réseaux;\r\n\r\n-Partie 2: Administration des systèmes;\r\n\r\n-Partie 3: Introduction aux outils et métodes de sécurisation', 'Réseaux', 13, 3),
(9, '2020-06-01 21:44:25', '? Généralités\r\n? Représentation de l’information\r\n? Architecture type VON NEUMANN\r\n? Composants de l’unité centrale\r\n? Unité centrale de traitement (Processeur)\r\n? Architecture d’un processeur\r\n? Unité arithmétique et logique\r\n? Automates synchrones et asynchrones\r\n? Les mémoires,\r\n? Les types de mémoire\r\n? Périphériques d’ entrées / sorties\r\n? Mécanisme de fonctionnement\r\n? Les interruptions\r\n? Les différents types d’interruptions\r\n? Programmation en assembleur\r\n? Travaux pratiques (Assemblage, BIOS, Configuration…)', 'Architecture des Ordinateurs', 11, 3),
(10, '2020-06-01 21:47:32', '- ReactJs ;\r\n- NodeJs;', 'Web ', 13, 10),
(11, '2020-06-01 21:48:50', 'Java : \r\n-Collections;\r\n-Les Threads', 'Java', 13, 10),
(12, '2020-06-01 21:55:28', '- les Exceptions (cours + tp);\r\n- JDBC;\r\n- Sockets ', 'Java', 13, 2),
(14, '2020-06-01 21:56:14', '- JSP;\r\n- MVC;\r\n- JPA', 'JEE', 13, 2),
(15, '2020-06-01 21:59:00', 'Architecture du SGBD d’ORACLE:\r\n-1. Introduction;\r\n-2. Outils et tâches d’administration ORACLE;\r\n-3. Architecture du SGBD ORACLE;\r\n Administration des bases de données ORACLE:\r\n-1. Gestion d’instance et de base de données ORACLE;\r\n-2. Gestion des utilisateurs;\r\n-3. Gestion des privilèges et rôles;\r\n-4. Confidentialité des données;\r\n-5. Techniques de sauvegarde et restauration;', 'Base de données avancées - chapitre 1', 13, 4),
(16, '2020-06-01 22:00:15', '-Conception de data warehouse;\r\n-Intégration et analyse des données;\r\n', 'Base de données avancées - chapitre 2 et 3', 13, 4),
(17, '2020-06-01 22:09:36', 'cette vidéo vous aidera à vous préparer aux examens\r\nBonne chance.', 'Sujet x ', 17, 12);

-- --------------------------------------------------------

--
-- Structure de la table `cours_files`
--

CREATE TABLE `cours_files` (
  `id` bigint(20) NOT NULL,
  `file_extension` varchar(255) DEFAULT NULL,
  `modified_file_name` varchar(255) DEFAULT NULL,
  `nom_file` varchar(255) DEFAULT NULL,
  `cours_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `cours_files`
--

INSERT INTO `cours_files` (`id`, `file_extension`, `modified_file_name`, `nom_file`, `cours_id`) VALUES
(1, 'ppt', 'Cours_S2_Algo_1591044563916.ppt', 'Cours_S2_Algo.ppt', 1),
(2, 'ppt', 'Cours_S3_Algo_1591044565757.ppt', 'Cours_S3_Algo.ppt', 1),
(3, 'ppt', 'Cours_S4_algo_1591044566145.ppt', 'Cours_S4_algo.ppt', 1),
(4, 'ppt', 'Cours_S5_Algo_1591044566645.ppt', 'Cours_S5_Algo.ppt', 1),
(5, 'ppt', 'Cours_S6_Algo_1591044566764.ppt', 'Cours_S6_Algo.ppt', 1),
(6, 'ppt', 'langageC_S1_1591045219286.ppt', 'langageC_S1.ppt', 2),
(7, 'pptx', 'Les tris_1591045659766.pptx', 'Les tris.pptx', 3),
(10, 'pdf', 'Diagramme de cas d\'utilisation_1591046861730.pdf', 'Diagramme de cas d\'utilisation.pdf', 6),
(11, 'pdf', 'Diagramme de Classes_1591046862558.pdf', 'Diagramme de Classes.pdf', 6),
(12, 'pdf', 'POO avec C++_partie1_1591047098379.pdf', 'POO avec C++_partie1.pdf', 7),
(13, 'pdf', 'POO avec C++_partie2_1591047099092.pdf', 'POO avec C++_partie2.pdf', 7),
(14, 'pdf', 'AdministrationRéseauxISILV4_1591047720186.pdf', 'AdministrationRéseauxISILV4.pdf', 8),
(15, 'pdf', 'ArchitectureComplette_1591047865354.pdf', 'ArchitectureComplette.pdf', 9),
(16, 'pdf', 'NodeJs_1591048052133.pdf', 'NodeJs.pdf', 10),
(17, 'pdf', 'ReactJS_1591048052917.pdf', 'ReactJS.pdf', 10),
(18, 'pdf', 'JAVA-Collections_1591048131065.pdf', 'JAVA-Collections.pdf', 11),
(19, 'pdf', 'JAVA-Threads_1591048131248.pdf', 'JAVA-Threads.pdf', 11),
(20, 'pdf', 'Chapitre0_Exception_1591048405395.pdf', 'Chapitre0_Exception.pdf', 12),
(21, 'pdf', 'Chapitre0_JDBC_1591048405487.pdf', 'Chapitre0_JDBC.pdf', 12),
(22, 'pdf', 'Chapitre0_Socket_1591048409453.pdf', 'Chapitre0_Socket.pdf', 12),
(24, 'pdf', 'TP_Exceptions_1591048528520.pdf', 'TP_Exceptions.pdf', 12),
(25, 'pdf', 'JPA_1591048574801.pdf', 'JPA.pdf', 14),
(26, 'pdf', 'JSP_1591048575231.pdf', 'JSP.pdf', 14),
(27, 'pdf', 'MVC_1591048576239.pdf', 'MVC.pdf', 14),
(28, 'pdf', 'BDA_Chapitre 1_1591048740793.pdf', 'BDA_Chapitre 1.pdf', 15),
(29, 'pdf', 'Chap 2_ISIL_1591048815807.pdf', 'Chap 2_ISIL.pdf', 16),
(30, 'pdf', 'Chap 3_ISIL_1591048816130.pdf', 'Chap 3_ISIL.pdf', 16);

-- --------------------------------------------------------

--
-- Structure de la table `filiere`
--

CREATE TABLE `filiere` (
  `id` bigint(20) NOT NULL,
  `abr` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `filiere`
--

INSERT INTO `filiere` (`id`, `abr`, `nom`) VALUES
(1, 'GI1', 'Génie Informatique 1'),
(2, 'GI2', 'Génie Informatique 2'),
(3, 'ER1', 'Energies Renouvelables 1'),
(4, 'ER2', 'Energies Renouvelables 2'),
(5, 'TM1', 'Techniques de Management 1'),
(6, 'TM2', 'Techniques de Management 2'),
(7, 'GODT1', 'Gestion des Organisations et des Destinations Touristiques 1'),
(8, 'GODT2', 'Gestion des Organisations et des Destinations Touristiques 2'),
(9, 'GE1', 'Génie de l\'Environnement 1'),
(10, 'GE2', 'Génie de l\'Environnement 2'),
(11, 'IDSE1', 'Informatique Décisionnelle et Science de Données 1'),
(12, 'IDSE2', 'Informatique Décisionnelle et Science de Données 2'),
(13, 'LP-ISIL', 'LP Ingénieriedes Systemes Informatiques Et Logiciels'),
(14, 'LP-MT', 'LP Management Bancaire et Financier'),
(15, 'LP-ERDD', 'LP Energies Renouvelables et Développement Durable (ERDD)'),
(16, 'LP-MBF', 'LP  Management Bancaire et Financier'),
(17, 'LP-MGE', 'LP Modélisation et Gestion de l\'Environnement');

-- --------------------------------------------------------

--
-- Structure de la table `remarque`
--

CREATE TABLE `remarque` (
  `id` bigint(20) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `msg` varchar(1000) DEFAULT NULL,
  `cours` bigint(20) DEFAULT NULL,
  `user` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `remarque`
--

INSERT INTO `remarque` (`id`, `created_date`, `msg`, `cours`, `user`) VALUES
(1, '2020-06-01 22:11:14', 'Merci ', 8, 13),
(2, '2020-06-01 22:11:49', 'Merci Monsieur', 10, 13),
(3, '2020-06-01 22:13:48', 'Daccord, Mrc beaucoup ', 17, 18);

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`id`, `description`, `role`) VALUES
(1, 'administrateur', 'ADMIN'),
(2, 'professeur', 'PROF'),
(3, 'etudiant', 'ETUDIANT');

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `actived` bit(1) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `genre` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `role` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `actived`, `created_date`, `email`, `genre`, `nom`, `password`, `prenom`, `role`) VALUES
(1, b'1', NULL, 'admin@gmail.com', 'F', 'Abou-Bichara', '{bcrypt}$2a$10$4C9EcnQkT0Iy9T4kGT3g5OO6PdNEQSs5QY..g.TxmEPfNTMeqYPli', 'khaoula', 1),
(2, b'0', '2020-06-01 20:03:04', 'prof1@gmail.com', 'F', 'Prof1', '{bcrypt}$2a$10$k2nuBDz4e2txGMD7.ALS9uB6307LQlentrPj2FOHNGu9lfSu5rooy', 'Prof1', 2),
(3, b'1', '2020-06-01 20:11:09', 'prof2@gmail.com', 'M', 'Prof2', '{bcrypt}$2a$10$JLAs8oHCSYsfx9zCvB3HB.anrfvarLkXtKDRNMJFH8fLPRzORNohW', 'Prof2', 2),
(4, b'1', '2020-06-01 20:12:08', 'prof3@gmail.com', 'M', 'Prof3', '{bcrypt}$2a$10$eeNM3FVj9h2T5FxLXr62teObv0U2udQRs5fHvDgyOySplz/9p3HBe', 'Prof3', 2),
(5, b'1', '2020-06-01 20:12:44', 'prof4@gmail.com', 'F', 'Prof4', '{bcrypt}$2a$10$KM2ucA/8dY2.rZCtRO8kzesLJx39LgCbzXO2rgIWZT.CqMkYETwQG', 'Prof4', 2),
(6, b'1', '2020-06-01 20:13:58', 'prof5@gmail.com', 'M', 'Prof5', '{bcrypt}$2a$10$Bpx5ZMiqZpROCkQjFPliw.HxqRPU5GZyBGNCpeO3WmSsGc/h3bK32', 'Prof5', 2),
(7, b'1', '2020-06-01 20:14:38', 'prof6@gmail.com', 'M', 'Prof6', '{bcrypt}$2a$10$ej4ApZyrS8Emq7a21STZh.kT.NCXh5m20cb92Ot9./pSVNvJ/Ghhy', 'Prof6', 2),
(9, b'1', '2020-06-01 20:15:49', 'prof7@gmail.com', 'M', 'Prof7', '{bcrypt}$2a$10$V1.LZzySszseB.g7N0sGAOeRXCrEGqVGD3mqrmQsO3Qx1ly5dzNze', 'Prof7', 2),
(10, b'1', '2020-06-01 20:16:35', 'prof8@gmail.com', 'F', 'Prof8', '{bcrypt}$2a$10$Y4DMfdgmdzamZOUyNtxKledrLcrmG7xztrMu14p0rU9m/QUVLeZGu', 'Prof8', 2),
(11, b'1', '2020-06-01 20:17:13', 'prof9@gmail.com', 'F', 'Prof9', '{bcrypt}$2a$10$EGdW9qglaOp3Qu9ay67Y6eYQ5S/fVfbR65bbYXwT.ixT9VUTkEE5q', 'Prof9', 2),
(12, b'1', '2020-06-01 20:21:01', 'prof10@gmail.com', 'M', 'Prof10', '{bcrypt}$2a$10$W.nT4b2DQP.hYiALRfS0Quycysxi6sS8Sr7YnGDMO3XJfpwYHr6Wm', 'Prof10', 2),
(13, b'1', '2020-06-01 20:21:35', 'et1@gmail.com', 'M', 'etu1', '{bcrypt}$2a$10$cx5B2gao9GsHyQx8I1F4j.no/nrQ8Z0GFlJQ98GEUnD8NhbRP8IBW', 'etu1', 3),
(15, b'1', '2020-06-01 20:22:22', 'et2@gmail.com', 'M', 'etu2', '{bcrypt}$2a$10$KVA5Ee/PmzMsqnv57xvlZeLeqQjYhzLQEjPkrOinqgokt9oy773oO', 'etu2', 3),
(16, b'1', '2020-06-01 20:22:56', 'et3@gmail.com', 'M', 'etu3', '{bcrypt}$2a$10$a0Nv.mPi9SY0rGcWysgQmefOE7T31b9duFf5h0bynkAFcDzXAe0/a', 'etu3', 3),
(17, b'1', '2020-06-01 20:23:18', 'et4@gmail.com', 'M', 'etu4', '{bcrypt}$2a$10$XOkPEYtn3ZDNgbrvUv/FDupHQOIBWkDuveSMKAoF3Bo1JP4S3MfKa', 'etu4', 3),
(18, b'1', '2020-06-01 20:23:42', 'et5@gmail.com', 'F', 'etu5', '{bcrypt}$2a$10$RRbXcAhMMqkxocHQ0EweCeI6YDV.0rRTPbMAjLi0dxIguJ8pvQhNO', 'etu5', 3),
(19, b'1', '2020-06-01 20:24:03', 'et6@gmail.com', 'F', 'etu6', '{bcrypt}$2a$10$HiGDzkeGmiUWS09S0dSzHOoso6Cgiei4G0Th.gKfdzxsE9Thebf8S', 'etu6', 3),
(20, b'1', '2020-06-01 20:24:19', 'et7@gmail.com', 'F', 'etu7', '{bcrypt}$2a$10$ivAIh/BtQcBM.YL1OjqSvuGikqtI4iW87mQctKT.xmsdGKTiLWiPO', 'etu7', 3),
(21, b'1', '2020-06-01 20:24:45', 'et8@gmail.com', 'F', 'etu8', '{bcrypt}$2a$10$cFC2OrHixJhhia3RJilFkeOuqBOUcgzFHCSw0Y8vIa1mScoQZ4.C2', 'etu8', 3),
(22, b'1', '2020-06-01 20:25:12', 'et9@gmail.com', 'F', 'etu9', '{bcrypt}$2a$10$95hH.kKq7HdG0TO450GpuuYetPivG6XLdEcDFJOmj9ZkMUBfmBuzu', 'etu9', 3),
(23, b'1', '2020-06-01 20:25:34', 'et10@gmail.com', 'F', 'etu10', '{bcrypt}$2a$10$EnMbfoWtNJEKHspfnMUVp.P653CjcUYXNf9kLkobftlrCh6dO7ZN.', 'etu10', 3),
(24, b'1', '2020-06-01 20:27:07', 'et11@gmail.com', 'M', 'etu11', '{bcrypt}$2a$10$6Y0F.5mWkUv03pWBtUg6H.Hiah2BAZrnT5sNHw0NcQs4LVwnvhqY6', 'etu11', 3);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `cours`
--
ALTER TABLE `cours`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKdxn3rmcyfqtnnts6dybiudso` (`filiere`),
  ADD KEY `FK5nnm4g11ogubumgyu2a0qdgis` (`id_user`);

--
-- Index pour la table `cours_files`
--
ALTER TABLE `cours_files`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK89q04ivgm3cw3i2hu8fgu0qkt` (`cours_id`);

--
-- Index pour la table `filiere`
--
ALTER TABLE `filiere`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_6wlpktn4l6ww1bqci0bhv7bho` (`abr`),
  ADD UNIQUE KEY `UK_fnacxk67cqd2s65y6tcr0jmr3` (`nom`);

--
-- Index pour la table `remarque`
--
ALTER TABLE `remarque`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKtd5uj2etooubfg365wgmal2bg` (`cours`),
  ADD KEY `FK14v62ipqte2xg20ppv4j78296` (`user`);

--
-- Index pour la table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_bjxn5ii7v7ygwx39et0wawu0q` (`role`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`),
  ADD KEY `FK20wcxq3dnh6io9qug4vc90p0p` (`role`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `cours`
--
ALTER TABLE `cours`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT pour la table `cours_files`
--
ALTER TABLE `cours_files`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT pour la table `filiere`
--
ALTER TABLE `filiere`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT pour la table `remarque`
--
ALTER TABLE `remarque`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `role`
--
ALTER TABLE `role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `cours`
--
ALTER TABLE `cours`
  ADD CONSTRAINT `FK5nnm4g11ogubumgyu2a0qdgis` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKdxn3rmcyfqtnnts6dybiudso` FOREIGN KEY (`filiere`) REFERENCES `filiere` (`id`);

--
-- Contraintes pour la table `cours_files`
--
ALTER TABLE `cours_files`
  ADD CONSTRAINT `FK89q04ivgm3cw3i2hu8fgu0qkt` FOREIGN KEY (`cours_id`) REFERENCES `cours` (`id`);

--
-- Contraintes pour la table `remarque`
--
ALTER TABLE `remarque`
  ADD CONSTRAINT `FK14v62ipqte2xg20ppv4j78296` FOREIGN KEY (`user`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKtd5uj2etooubfg365wgmal2bg` FOREIGN KEY (`cours`) REFERENCES `cours` (`id`);

--
-- Contraintes pour la table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `FK20wcxq3dnh6io9qug4vc90p0p` FOREIGN KEY (`role`) REFERENCES `role` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
