CREATE TABLE heroes_comics
(
    id        bigint auto_increment,
    heroes_id bigint,
    comic_id  bigint
);

ALTER TABLE heroes_comics
    ADD PRIMARY KEY (id);

ALTER TABLE heroes_comics
    ADD FOREIGN KEY (heroes_id) REFERENCES heroes (id);

ALTER TABLE heroes_comics
    ADD FOREIGN KEY (comic_id) REFERENCES comics (id);