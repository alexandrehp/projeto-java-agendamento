create table agendamentos(

    id bigint not null auto_increment,
    medico_id bigint not null,
    paciente_id bigint not null,
    data datetime not null,
    motivo_cancelamento varchar(100),

    primary key(id),
    constraint fk_agendamentos_medico_id foreign key(medico_id) references medicos(id),
    constraint fk_agendamentos_paciente_id foreign key(paciente_id) references pacientes(id)

);