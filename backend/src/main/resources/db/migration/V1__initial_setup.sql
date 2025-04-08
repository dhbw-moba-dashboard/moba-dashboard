create table loc (
                     locid integer not null,
                     name varchar(255),
                     primary key (locid)
);
create table type (
                      typeid integer not null,
                      type_name varchar(255),
                      primary key (typeid)
);
create table "Values" (
                       ValueId integer not null,
                       loc integer not null,
                       time_stamp varchar(255),
                       typeid integer not null,
                       value integer not null,
                       primary key (ValueId)
);