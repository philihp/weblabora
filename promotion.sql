alter table weblabora_game add stage varchar(255);
alter table weblabora_user add email varchar(255);
alter table weblabora_user add email_validator varchar(255);
alter table weblabora_user add password varchar(255);
alter table weblabora_user add password_validator varchar(255);
alter table weblabora_user add unvalidated_email varchar(255);
alter table weblabora_user add username varchar(255);

create table weblabora_fingerprint (
  fingerprint_id int primary key not null,
  date_created datetime,
  date_updated datetime,
  uuid varchar(255),
  user_id int
);
alter table weblabora_user change name name varchar(255);
alter table weblabora_user change facebook_id facebook_id varchar(255);

update weblabora_game set stage='RECRUITING';
update weblabora_game set stage='IN_PROGRESS' where player1_user_id is not null and player2_user_id is not null and players = 2;
update weblabora_game set stage='IN_PROGRESS' where player1_user_id is not null and player2_user_id is not null and player3_user_id is not null and players = 3;
update weblabora_game set stage='IN_PROGRESS' where player1_user_id is not null and player2_user_id is not null and player3_user_id is not null and player4_user_id is not null and players = 4;

alter table weblabora_state add active int(0);
update weblabora_state set active = 0;
delete from weblabora_game where game_id=10767;
update weblabora_state set active=1 where state_id in (select state_id from weblabora_game);