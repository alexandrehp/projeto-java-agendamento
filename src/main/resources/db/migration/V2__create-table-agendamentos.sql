create table agendamentos(

    id bigint not null auto_increment,
    data_hora date not null,
    nome varchar(100) not null,
    cpf varchar(20) not null,
    medico_id bigint not null,

    primary key(id)

);