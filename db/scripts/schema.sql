CREATE DATABASE bcp;
use bcp;
create table tipocambio
(
    id             int auto_increment,
    moneda_origen  varchar(50) not null,
    moneda_destino varchar(50) not null,
    tipo_cambio    varchar(50) not null,
    constraint table_name_pk
        primary key (id)
);