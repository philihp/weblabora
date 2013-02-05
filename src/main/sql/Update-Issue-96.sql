DROP TABLE IF EXISTS `philihp`.`weblabora_chat`;



CREATE  TABLE `philihp`.`weblabora_chat` (

  `chat_id` INT NOT NULL COMMENT 'Artificial primary key.' ,

  `date_created` DATETIME NOT NULL COMMENT 'Date on which the row was created.' ,

  `date_updated` DATETIME NULL COMMENT 'Date on which the row was last updated or NULL if it wasn\\\'t updated since it was created.' ,

  `ref_game_id` INT NOT NULL COMMENT 'Foreign key to `weblabora_game` table row corresponding to the game in which the chat action took place.' ,

  `ref_user_id` INT NULL DEFAULT NULL COMMENT 'Foreign key to `weblabora_user` table row corresponding to the user who made the chat action. Or NULL for "system" actions.' ,

  `player_seat` INT NULL COMMENT 'If `ref_user_id` is one of the players in the game this denotes which one (a 1-based index). Otherwise this is NULL. Tracing leaving and joining players requires to either keep this information here (simple) or add proper entries to `weblabora_state` (better but complex). Note that this silently assumes that a user may take only one player seat in a game even thou it does not have to be so.' ,

  `action` ENUM('create','edit','delete') NOT NULL COMMENT 'Type of chat action. This column also determines meaning and validity of other columns (see those columns for details).' ,

  `text` LONGTEXT NULL DEFAULT NULL COMMENT 'If `action` is `create` then this is the text of the chat message that is created. If `action` is `edit` then this is the new text of the chat message that is edited. Otherwise this is unspecified (use NULL on write but do not depend on NULL on read).' ,

  `ref_chat_id` INT NULL DEFAULT NULL COMMENT 'If `action` is `edit` then this is the `chat_id` of the row that previously manipulated (possibly created) the chat message that is edited. If `action` is `delete` then this is the `chat_id` of the row that previously manipulated (possibly created) the chat message that is deleted. Otherwise this is unspecified (use NULL on write but do not depend on NULL on read).' ,

  PRIMARY KEY (`chat_id`) ,

  UNIQUE INDEX `chat_id_UNIQUE` (`chat_id` ASC))

ENGINE = MyISAM

DEFAULT CHARACTER SET = utf8

COLLATE = utf8_unicode_ci 

COMMENT = 'Stores chat messages.';



ALTER TABLE `philihp`.`weblabora_chat` 

  ADD CONSTRAINT `FK_weblabora_chat_ref_game_id`

  FOREIGN KEY (`ref_game_id` )

  REFERENCES `philihp`.`weblabora_game` (`game_id` )

  ON DELETE NO ACTION

  ON UPDATE NO ACTION

, ADD INDEX `FK_weblabora_chat_ref_game_id_idx` (`ref_game_id` ASC) ;



ALTER TABLE `philihp`.`weblabora_chat` 

  ADD CONSTRAINT `FK_weblabora_chat_ref_user_id`

  FOREIGN KEY (`ref_user_id` )

  REFERENCES `philihp`.`weblabora_user` (`user_id` )

  ON DELETE NO ACTION

  ON UPDATE NO ACTION

, ADD INDEX `FK_weblabora_chat_ref_user_id_idx` (`ref_user_id` ASC) ;



ALTER TABLE `philihp`.`weblabora_chat` 

  ADD CONSTRAINT `FK_weblabora_chat_ref_chat_id`

  FOREIGN KEY (`ref_chat_id` )

  REFERENCES `philihp`.`weblabora_chat` (`chat_id` )

  ON DELETE NO ACTION

  ON UPDATE NO ACTION

, ADD INDEX `FK_weblabora_chat_ref_chat_id_idx` (`ref_chat_id` ASC) ;
