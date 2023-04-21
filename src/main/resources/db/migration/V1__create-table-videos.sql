create table videos(
	id bigint not null auto_increment,
	titulo varchar(100) not null,
	descricao varchar(500) not null,
	url varchar(2083) not null,
	
	primary key(id) 
)