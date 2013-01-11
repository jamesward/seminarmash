# --- First database schema

# --- !Ups

create sequence s_person_id;

create table person (
  id    bigint DEFAULT nextval('s_person_id'),
  name  varchar(128)
);


# --- !Downs

drop table person;
drop sequence s_person_id;