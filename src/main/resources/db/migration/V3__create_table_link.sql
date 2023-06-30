create table link(
	id serial primary key not null,
	url text not null,
	descricao text,
	id_usuario int not null,
	foreign key(id_usuario) references usuario(id) on delete cascade
);