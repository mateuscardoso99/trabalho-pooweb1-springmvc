create table contato(
	id serial primary key not null,
	nome varchar(255) not null,
	telefone varchar(20) not null,
	foto varchar(255),
	id_usuario int not null,
	foreign key(id_usuario) references usuario(id) on delete cascade
);