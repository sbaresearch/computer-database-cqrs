# --- First database schema

# --- !Ups

create table companydetails (
  id                        uuid not null,
  name                      varchar(255),
  constraint pk_company primary key (id))
;

create table computerdetails (
  id                        uuid not null,
  name                      varchar(255),
  introduced                timestamp,
  discontinued              timestamp,
  companyid                 uuid,
  companyname               varchar(255),
  constraint pk_computer primary key (id))
;

alter table computerdetails add constraint fk_computer_company_1 foreign key (companyid) references companydetails (id) on delete restrict on update restrict;
create index ix_computer_company_1 on computerdetails (companyid);


# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists companydetails;

drop table if exists computerdetails;

SET REFERENTIAL_INTEGRITY TRUE;
