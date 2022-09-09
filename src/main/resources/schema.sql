create table users
(
	id int not null,
	full_name varchar(255) not null,
	email varchar(255) not null,
	password varchar(255) not null,
	is_admin BOOLEAN
);

create unique index users_email_uindex
	on users (email);

create unique index users_full_name_uindex
	on users (full_name);

create unique index users_id_uindex
	on users (id);

create unique index users_password_uindex
	on users (password);

alter table users
	add constraint users_pk
		primary key (id);

