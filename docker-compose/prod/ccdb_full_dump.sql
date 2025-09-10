-- MySQL dump 10.13  Distrib 9.3.0, for Linux (x86_64)
--
-- Host: localhost    Database: ccdb
-- ------------------------------------------------------
-- Server version	9.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `badge`
--

DROP TABLE IF EXISTS `badge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `badge` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `badge`
--

LOCK TABLES `badge` WRITE;
/*!40000 ALTER TABLE `badge` DISABLE KEYS */;
/*!40000 ALTER TABLE `badge` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Voirie'),(2,'??clairage public'),(3,'Trottoirs'),(4,'Conteneurs ?? d??chets'),(5,'Aire de jeux'),(6,'Fontaines'),(7,'Propret??'),(8,'Gestion des d??chets'),(9,'Espaces verts'),(10,'Installations sportives'),(11,'B??timents publics'),(12,'Nuisibles'),(13,'??gouts'),(14,'Circulation'),(15,'Autres'),(16,'Accessibilit??');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `city` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'Aix-en-Provence'),(2,'Marseille');
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `incident`
--

DROP TABLE IF EXISTS `incident`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `incident` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` tinytext NOT NULL,
  `closed_at` datetime(6) DEFAULT NULL,
  `created_at` datetime(6) NOT NULL,
  `description` tinytext,
  `image` varchar(255) DEFAULT NULL,
  `latitude` decimal(10,6) DEFAULT NULL,
  `longitude` decimal(10,6) DEFAULT NULL,
  `neighborhood` varchar(100) DEFAULT NULL,
  `postal_code` varchar(10) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `city_id` int NOT NULL,
  `status_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK61yh08o87m6bxg3iisql9ci8y` (`city_id`),
  KEY `FKnnvqhgllxqbqdrpobr7v3qmo` (`status_id`),
  KEY `FK8bqewpr8w6nc8leoue11rmuew` (`user_id`),
  CONSTRAINT `FK61yh08o87m6bxg3iisql9ci8y` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK8bqewpr8w6nc8leoue11rmuew` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FKnnvqhgllxqbqdrpobr7v3qmo` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `incident`
--

LOCK TABLES `incident` WRITE;
/*!40000 ALTER TABLE `incident` DISABLE KEYS */;
INSERT INTO `incident` VALUES (7,'2 Rue Mignet, Aix-en-Provence',NULL,'2024-06-05 22:51:09.816457','Description modifi??e pour test','http://localhost:8883/api/images/by-id/687e269c5ecb63404a7e244d',43.529930,5.450910,'Mazarin','13100','Trou dangereux corrig??',2,1,1),(54,'20 Boulevard Longchamp, Aix-en-Provence',NULL,'2025-02-11 22:39:09.000000','Plusieurs lampadaires ne fonctionnent pas.','http://localhost:8883/api/images/by-id/687e269c5ecb63404a7e244d',43.528300,5.449700,'Le Panier','13001','??clairage public d??fectueux',1,1,2),(55,'10 Chemin Fleuri, Aix-en-Provence',NULL,'2025-02-11 22:39:09.000000','Ce feu marche pas','http://localhost:8883/api/images/by-id/687e269c5ecb63404a7e244d',43.525300,5.456400,'Jas','13090','Feu marche pas',1,2,3),(56,'5 Rue Mignet, Aix-en-Provence',NULL,'2025-01-15 00:00:00.000000','Embouteillage important au centre-ville.','http://localhost:8883/api/images/by-id/687e269c5ecb63404a7e244d',43.525300,5.456400,'Centre-ville','13100','Probl??me de circulation',1,3,1),(57,'12 Rue Espariat, Aix-en-Provence',NULL,'2025-02-10 00:00:00.000000','Les conteneurs ?? d??chets sont pleins depuis plusieurs jours.','http://localhost:8883/api/images/by-id/687e269c5ecb63404a7e244d',43.525300,5.456400,'Mazarin','13100','Poubelles d??bordantes',1,4,1),(58,' 9 Boulevard Carnot, Aix-en-Provence',NULL,'2025-03-08 00:00:00.000000','Un lampadaire ne s???allume plus depuis plusieurs nuits.','http://localhost:8883/api/images/by-id/68b5b8203d58e10d43e39042',43.525300,5.456400,'','13100','Lampadaire cass??',1,1,1),(59,'7 Avenue Victor Hugo, Aix-en-Provence',NULL,'2025-04-12 00:00:00.000000','Un ??gout d??gage une mauvaise odeur.','http://localhost:8883/api/images/by-id/687e269c5ecb63404a7e244d',43.525300,5.456400,'Centre-ville','13100','??gout bouch??',1,2,1),(60,'Parc Jourdan, Aix-en-Provence',NULL,'2025-05-06 00:00:00.000000','L???herbe est trop haute dans le parc public.','http://localhost:8883/api/images/by-id/687e269c5ecb63404a7e244d',43.521045,5.448439,'Sud','13100','Parc mal entretenu',1,3,1),(61,'Place de la Rotonde, Aix-en-Provence',NULL,'2025-06-18 00:00:00.000000','La fontaine pr??s de la Rotonde ne fonctionne plus.','http://localhost:8883/api/images/by-id/687e269c5ecb63404a7e244d',43.525300,5.456400,'Centre-ville','13100','Fontaine ?? sec',1,4,1),(62,'8 Rue d???Italie, Aix-en-Provence',NULL,'2025-07-04 00:00:00.000000','Des sacs-poubelle s???accumulent au coin de la rue.','http://localhost:8883/api/images/by-id/687e269c5ecb63404a7e244d',43.526870,5.452290,'Centre-ville','13100','Accumulation de d??chets',1,1,1),(111,'350 Chemin du Verladet',NULL,'2025-09-05 17:03:54.261069','asdf','http://localhost:8883/api/images/by-id/68bde4f48cc06626d42c8090',43.519181,5.360845,'','13290','test',1,1,1),(112,'9 Rue Mignet',NULL,'2025-09-05 17:04:01.856420',NULL,NULL,NULL,NULL,NULL,NULL,'',1,1,1),(113,'9 Rue Mignet',NULL,'2025-09-07 14:23:15.727603',NULL,NULL,NULL,NULL,NULL,NULL,'',1,1,1),(114,'9 Rue Mignet',NULL,'2025-09-07 14:23:33.181027',NULL,NULL,NULL,NULL,NULL,NULL,'',1,1,1),(115,'9 Rue Mignet',NULL,'2025-09-07 14:23:35.198379',NULL,NULL,NULL,NULL,NULL,NULL,'',1,1,1),(116,'9 Rue Mignet',NULL,'2025-09-07 17:42:40.614627',NULL,NULL,NULL,NULL,NULL,NULL,'Test incident Jeu d\'essai',1,1,1),(117,'9 Rue Mignet',NULL,'2025-09-07 17:43:09.468340',NULL,NULL,NULL,NULL,NULL,'ABC123','Test incident Jeu d\'essai',1,1,1),(118,'9 Rue Mignet',NULL,'2025-09-07 18:25:03.843511',NULL,NULL,NULL,NULL,NULL,NULL,'Test incident Jeu d\'essai',1,1,1),(119,'9 Rue Mignet',NULL,'2025-09-07 18:25:15.428176',NULL,NULL,NULL,NULL,NULL,NULL,'Test incident Jeu d\'essai',1,1,1),(120,'9 Rue Mignet',NULL,'2025-09-07 18:59:34.836720',NULL,NULL,NULL,NULL,NULL,NULL,'Essai',1,1,1),(121,'9 Rue Mignet',NULL,'2025-09-07 18:59:43.119453',NULL,NULL,NULL,NULL,NULL,NULL,' <script>alert(\'XSS\');</script>',1,1,1),(122,'350 Chemin du Verladet',NULL,'2025-09-07 21:58:11.068019','asdf','http://localhost:8883/api/images/by-id/68bde3d38cc06626d42c808f',43.519181,5.360845,'','13290','test',1,1,1);
/*!40000 ALTER TABLE `incident` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `incident_category`
--

DROP TABLE IF EXISTS `incident_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `incident_category` (
  `category_id` int NOT NULL,
  `incident_id` int NOT NULL,
  PRIMARY KEY (`category_id`,`incident_id`),
  KEY `FKkj36ha8nyncyv2w5lr5a4uc92` (`incident_id`),
  CONSTRAINT `FKbattrhukqq7nqrpfn9q0b4ep2` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FKkj36ha8nyncyv2w5lr5a4uc92` FOREIGN KEY (`incident_id`) REFERENCES `incident` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `incident_category`
--

LOCK TABLES `incident_category` WRITE;
/*!40000 ALTER TABLE `incident_category` DISABLE KEYS */;
INSERT INTO `incident_category` VALUES (1,7),(2,7),(3,7),(2,54),(14,55),(14,56),(7,57),(8,57),(2,58),(13,59),(9,60),(6,61),(8,62),(2,111),(2,122);
/*!40000 ALTER TABLE `incident_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `intervention`
--

DROP TABLE IF EXISTS `intervention`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `intervention` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date_intervention` datetime(6) DEFAULT NULL,
  `description` tinytext NOT NULL,
  `incident_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6ypokt9mr4ukap8oficol9rrh` (`incident_id`),
  CONSTRAINT `FK6ypokt9mr4ukap8oficol9rrh` FOREIGN KEY (`incident_id`) REFERENCES `incident` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `intervention`
--

LOCK TABLES `intervention` WRITE;
/*!40000 ALTER TABLE `intervention` DISABLE KEYS */;
/*!40000 ALTER TABLE `intervention` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'admin'),(2,'user');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'signal??'),(2,'valid??'),(3,'en cours'),(4,'r??solu');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `firstname` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `password` varchar(128) DEFAULT NULL,
  `phone` varchar(20) NOT NULL,
  `role_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn82ha3ccdebhokx3a8fgdqeyy` (`role_id`),
  CONSTRAINT `FKn82ha3ccdebhokx3a8fgdqeyy` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'loislane@gmail.com','Lois','Lane','$2a$10$srqLUKQf47bPA4CMpjZwEudqYStgCztdNvaDH.tXG6i0DDVH1usBe','05 55 55 55 55',2),(2,'mariecurie@gmail.com','Marie','Curie','$2a$10$mDPUiCdY45XW.cuwrVqxU.Q3ZaUI1HFDdRw5IH7BOvvUdSzx7JQIq','07 77 77 77 77',2),(3,'adalovelace@gmail.com','Ada','Lovelace','$2a$10$TgGBRrHyeV89KIGl7lbtEue91nSUgfB7nkw6vL6iqBDEirQbrg6Ku','01 11 11 11 11 ',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_badge`
--

DROP TABLE IF EXISTS `user_badge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_badge` (
  `badge_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`badge_id`,`user_id`),
  KEY `FK2jw9fpotmmbda07k27qc9t2ul` (`user_id`),
  CONSTRAINT `FK2jw9fpotmmbda07k27qc9t2ul` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FKjqx9n26pk9mqf1qo8f7xvvoq9` FOREIGN KEY (`badge_id`) REFERENCES `badge` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_badge`
--

LOCK TABLES `user_badge` WRITE;
/*!40000 ALTER TABLE `user_badge` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_badge` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'ccdb'
--

--
-- Dumping routines for database 'ccdb'
--
/*!50003 DROP PROCEDURE IF EXISTS `GetMonthlyIncidentCountsByCategory` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `GetMonthlyIncidentCountsByCategory`(
    IN p_year INT,
    IN p_city_id INT
)
BEGIN
    -- Tabla temporal de colores por categor??a
    CREATE TEMPORARY TABLE tmp_category_colors (
        category_name VARCHAR(255),
        color VARCHAR(7)
    );

    INSERT INTO tmp_category_colors (category_name, color) VALUES
        ('Voirie', '#1f77b4'),
        ('??clairage public', '#ff7f0e'),
        ('Trottoirs', '#2ca02c'),
        ('Conteneurs ?? d??chets', '#d62728'),
        ('Aire de jeux', '#9467bd'),
        ('Fontaines', '#8c564b'),
        ('Propret??', '#e377c2'),
        ('Gestion des d??chets', '#7f7f7f'),
        ('Espaces verts', '#bcbd22'),
        ('Installations sportives', '#17becf'),
        ('B??timents publics', '#aec7e8'),
        ('Nuisibles', '#ffbb78'),
        ('??gouts', '#98df8a'),
        ('Circulation', '#ff9896'),
        ('Autres', '#c5b0d5'),
        ('Accessibilit??', '#17a589');

    -- Consulta principal
    SELECT 
        LEFT(MONTHNAME(DATE(CONCAT(p_year, '-', LPAD(m.month,2,'0'), '-01'))), 3) AS month_name,
        cat.name AS category_name,
       GREATEST(COUNT(i.id), 0) AS incident_count,
        tmp.color AS category_color
    FROM 
        (SELECT 1 AS month UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION 
         SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION 
         SELECT 9 UNION SELECT 10 UNION SELECT 11 UNION SELECT 12) AS m
    CROSS JOIN ccdb.category AS cat
    LEFT JOIN ccdb.incident_category AS icat ON icat.category_id = cat.id
    LEFT JOIN ccdb.incident AS i 
        ON i.id = icat.incident_id 
        AND YEAR(i.created_at) = p_year
        AND MONTH(i.created_at) = m.month
        AND i.city_id = p_city_id
    LEFT JOIN tmp_category_colors tmp ON tmp.category_name = cat.name
    GROUP BY m.month, cat.id, cat.name, tmp.color
    ORDER BY m.month, cat.name;

    DROP TEMPORARY TABLE IF EXISTS tmp_category_colors;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `incidents_with_cat_by_userId` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `incidents_with_cat_by_userId`(userId int)
BEGIN
select i.id, i.address, i.closed_at, i.created_at, i.description, i.image, i.latitude, i.longitude, i.neighborhood, i.postal_code, i.title, i.city_id, i.status_id, i.user_id, cat.name category
from incident i
inner join incident_category ic on ic.incident_id = i.id 
inner join category cat on cat.id = ic.category_id 
where  user_id = userId
order by i.created_at desc;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `incidents_with_cat_by_userId_filtered_by_status` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `incidents_with_cat_by_userId_filtered_by_status`()
BEGIN
select i.id, i.address, i.closed_at, i.created_at, i.description, i.image, i.latitude, i.longitude, i.neighborhood, i.postal_code, i.title, i.city_id, i.status_id, i.user_id, cat.name category
from incident i
inner join incident_category ic on ic.incident_id = i.id 
inner join category cat on cat.id = ic.category_id 
inner join status st on st.id = i.status_id  AND st.id = 3
where  user_id = 1
limit 8; 

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `new_procedure` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `new_procedure`()
BEGIN
select i.id, i.address, i.closed_at, i.created_at, i.description, i.image, i.latitude, i.longitude, i.neighborhood, i.postal_code, i.title, i.city_id, i.status_id, i.user_id, cat.name category
from incident i
inner join incident_category ic on ic.incident_id = i.id 
inner join category cat on cat.id = ic.category_id 
inner join status st on st.id = i.status_id  AND st.id = 3
where  user_id = 1
limit 8; 

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-09-09 22:11:32
