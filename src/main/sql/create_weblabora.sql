--
-- Table structure for table `weblabora_config`
--

DROP TABLE IF EXISTS `weblabora_config`;
CREATE TABLE `weblabora_config` (
  `config_id` int(11) NOT NULL PRIMARY KEY,
  `name` varchar(255) NOT NULL UNIQUE KEY,
  `value` varchar(255) DEFAULT NULL
);

--
-- Dumping data for table `weblabora_config`
--

LOCK TABLES `weblabora_config` WRITE;
/*!40000 ALTER TABLE `weblabora_config` DISABLE KEYS */;
INSERT INTO `weblabora_config` VALUES (-1,'client_id','135113846608059'),(-2,'client_secret','06600eab41a3a5ceb9f9f6f2b86bc774'),(-3,'redirect_uri','http://localhost:8080/weblabora/authenticate.do');
/*!40000 ALTER TABLE `weblabora_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `weblabora_game`
--

DROP TABLE IF EXISTS `weblabora_game`;
CREATE TABLE `weblabora_game` (
  `game_id` int(11) NOT NULL PRIMARY KEY,
  `date_created` datetime DEFAULT NULL,
  `date_updated` datetime DEFAULT NULL,
  `player1_color` varchar(255) DEFAULT NULL,
  `player1_user_id` int(11) DEFAULT NULL,
  `player2_color` varchar(255) DEFAULT NULL,
  `player2_user_id` int(11) DEFAULT NULL,
  `player3_color` varchar(255) DEFAULT NULL,
  `player3_user_id` int(11) DEFAULT NULL,
  `player4_color` varchar(255) DEFAULT NULL,
  `player4_user_id` int(11) DEFAULT NULL,
  `state_id` int(11) DEFAULT NULL,
  `player1_move` varchar(255) DEFAULT NULL,
  `player2_move` varchar(255) DEFAULT NULL,
  `player3_move` varchar(255) DEFAULT NULL,
  `player4_move` varchar(255) DEFAULT NULL,
  `length` varchar(255) DEFAULT NULL,
  `players` int(11) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `current_player` int(11) DEFAULT NULL,
  `player1_score` int(11) DEFAULT NULL,
  `player2_score` int(11) DEFAULT NULL,
  `player3_score` int(11) DEFAULT NULL,
  `player4_score` int(11) DEFAULT NULL,
  `game_over` tinyint(1) DEFAULT NULL,
  KEY `FK_weblabora_game_state_id` (`state_id`),
  KEY `FK_weblabora_game_player3_user_id` (`player3_user_id`),
  KEY `FK_weblabora_game_player4_user_id` (`player4_user_id`),
  KEY `FK_weblabora_game_player1_user_id` (`player1_user_id`),
  KEY `FK_weblabora_game_player2_user_id` (`player2_user_id`)
);


--
-- Table structure for table `weblabora_state`
--

DROP TABLE IF EXISTS `weblabora_state`;
CREATE TABLE `weblabora_state` (
  `state_id` int(11) NOT NULL PRIMARY KEY,
  `date_created` datetime DEFAULT NULL,
  `date_updated` datetime DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `src_state_id` int(11) DEFAULT NULL,
  `explorer_id` int(11) DEFAULT NULL,
  KEY `FK_weblabora_state_src_state_id` (`src_state_id`)
);

--
-- Table structure for table `weblabora_user`
--

DROP TABLE IF EXISTS `weblabora_user`;
CREATE TABLE `weblabora_user` (
  `user_id` int(11) NOT NULL PRIMARY KEY,
  `date_created` datetime DEFAULT NULL,
  `date_updated` datetime DEFAULT NULL,
  `facebook_id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `active_game_id` int(11) DEFAULT NULL,
  KEY `FK_weblabora_user_active_game_id` (`active_game_id`)
);