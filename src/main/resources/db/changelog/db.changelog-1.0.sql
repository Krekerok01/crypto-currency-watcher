-- liquibase formatted sql

-- changeset varvaramamatsiuk:1
create table if not exists cryptocurrencies (
  cryptocurrency_id bigserial not null,
  symbol varchar(60) not null,
  price_usd float not null,

  constraint cryptocurrency_pkey primary key(cryptocurrency_id)
 );


-- changeset varvaramamatsiuk:2
create table if not exists users (
  user_id bigserial not null,
  username varchar(60) not null,
  cryptocurrency_id bigint not null,
  coin_price_usd float not null,

  constraint users_pkey primary key(user_id),
  constraint users_fk_cryptocurrency foreign key (cryptocurrency_id) references cryptocurrencies(cryptocurrency_id)
 );






