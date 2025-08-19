DROP TABLE IF EXISTS classes;

CREATE TABLE character (
    id INT PRIMARY KEY,
    parent_id INT,
    name VARCHAR(100) NOT NULL,
    color VARCHAR(50) NOT NULL
);

INSERT INTO character (id, parent_id, name, color) VALUES
(1, 0, 'Warrior', 'red'),
(2, 0, 'Wizard', 'green'),
(3, 0, 'Priest', 'white'),
(4, 0, 'Rogue', 'yellow');

INSERT INTO character (id, parent_id, name, color) VALUES
(5, 1, 'Fighter', 'blue'),
(6, 1, 'Paladin', 'lightblue'),
(7, 1, 'Ranger', 'lightgreen');

INSERT INTO character (id, parent_id, name, color) VALUES
(8, 2, 'Mage', 'grey'),
(9, 2, 'Specialist wizard', 'lightgrey');

INSERT INTO character (id, parent_id, name, color) VALUES
(10, 3, 'Cleric', 'red'),
(11, 3, 'Druid', 'green'),
(12, 3, 'Priest of specific mythos', 'white');

INSERT INTO character (id, parent_id, name, color) VALUES
(13, 4, 'Thief', 'yellow'),
(14, 4, 'Bard', 'blue');

INSERT INTO character (id, parent_id, name, color) VALUES
(15, 13, 'Assassin', 'lightblue');
