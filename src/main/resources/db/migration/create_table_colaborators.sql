CREATE TABLE colaborators
(
    id      bigint                  auto_increment,
    name    VARCHAR (255),
    role    VARCHAR (100)
);

ALTER TABLE colaborators
    ADD PRIMARY KEY (id);