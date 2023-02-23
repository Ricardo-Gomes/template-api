create schema seguranca

create table seguranca.usuario (
	id bigserial not null primary key,
	login varchar(50) not null,
	password varchar (255) not null
)