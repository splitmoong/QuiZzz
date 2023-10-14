CREATE TABLE players(
    id int AUTO_INCREMENT PRIMARY KEY,
    player_username varchar(50),
    player_password varchar(50),
    score int
    );
    
select * from players;

ALTER TABLE players 
RENAME COLUMN score_topic01 TO score;

TRUNCATE TABLE players;

ALTER TABLE players   
    RENAME COLUMN score TO high_score
    
DROP TABLE players;

