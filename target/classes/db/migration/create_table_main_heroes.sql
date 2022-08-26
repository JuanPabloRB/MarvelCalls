CREATE TABLE main_heroes
(
    id            bigint not null,
    name          varchar(255),
    full_name     varchar(255),
    syncronizable boolean,
    last_sync     TIMESTAMP WITH TIME ZONE
);
ALTER TABLE main_heroes
    ADD PRIMARY KEY (id);