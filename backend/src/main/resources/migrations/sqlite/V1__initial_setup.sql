create table loc
(
    loc_id integer not null,
    name  varchar(255),
    primary key (loc_id)
);

create table type
(
    type_id   integer not null,
    type_name varchar(255),
    primary key (type_id)
);

create table loc_values
(
    value_id   integer not null,
    loc        integer not null,
    time_stamp integer not null,
    type_id    integer not null,
    data       integer not null,
    primary key (value_id),
    foreign key (loc) references loc (loc_id),
    foreign key (type_id) references type (type_id)
);