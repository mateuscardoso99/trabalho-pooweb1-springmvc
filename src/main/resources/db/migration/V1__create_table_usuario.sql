create table usuario(
	id serial primary key not null,
	nome varchar(255) not null,
	email varchar(255) unique not null,
	senha varchar(255) not null
);