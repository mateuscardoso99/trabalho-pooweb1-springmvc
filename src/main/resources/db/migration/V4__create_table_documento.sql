create table documento(
	id serial primary key not null,
	arquivo varchar(255) not null,
	id_usuario int not null,
	foreign key(id_usuario) references usuario(id) on delete cascade
);