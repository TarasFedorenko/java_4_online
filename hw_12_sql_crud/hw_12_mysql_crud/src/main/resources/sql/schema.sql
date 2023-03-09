create table actors
(
    id      bigint auto_increment primary key,
    created datetime(6) null,
    name    varchar(255) null,
    surname varchar(255) null,
    age     int
);
create table movies
(
    id           bigint auto_increment primary key,
    created      datetime(6) null,
    title        varchar(255) null,
    movie_genre  varchar(255) not null,
    release_year int
);
create table act_mov
(
    act_id bigint not null,
    mov_id bigint not null,
    primary key (act_id, mov_id),
    foreign key (act_id) references movies (id),
    foreign key (mov_id) references actors (id)
);