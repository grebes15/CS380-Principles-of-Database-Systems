
CREATE TABLE Tournaments (
id INTEGER NOT NULL PRIMARY KEY, --ranking of player
id_tournament VARCHAR(15) NOT NULL, --this is grand slam tournament that the tennis player has played in
RESULT VARCHAR(4) --how well did the tennis player do in the tournament
);

CREATE TABLE Players (
id     INTEGER NOT NULL PRIMARY KEY, --this will be the ranking of thye tennis player
first_name VARCHAR(15) NOT NULL, --first name of tennis player
last_name VARCHAR(15) NOT NULL, --last name of tennis player
country_of_origin VARCHAR(15) NOT NULL, --where the tennis player was born
FOREIGN KEY (id) REFERENCES Tournaments (id)
);


