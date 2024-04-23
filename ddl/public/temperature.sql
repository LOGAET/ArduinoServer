create table temperature
(
    datatime_id timestamp with time zone not null
        constraint time_pk
            primary key,
    temperature numeric(5, 2)            not null
        constraint check_name
            check ((temperature >= ('-55'::integer)::numeric) AND (temperature <= (125)::numeric))
);

alter table temperature
    owner to postgres;

create index temperature_datatime_id_index
    on temperature (datatime_id desc);

