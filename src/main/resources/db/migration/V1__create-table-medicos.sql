create table medicos(

    id bigint not null auto_increment,
    email varchar(100) not null unique,
    senha varchar(100) not null,
    cpf varchar(20) not null unique,
    data_nascimento date default null,
    telefone varchar(20) not null,
    especialidade varchar(100) not null,

    primary key(id)

);