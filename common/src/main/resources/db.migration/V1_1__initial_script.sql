create table public.role
(
    id        serial
        constraint roles_pkey
            primary key,
    name      varchar(50)
        constraint roles_name_key
            unique,
    created   timestamp not null,
    changed   timestamp not null,
    is_actual boolean   not null
);

alter table public.role
    owner to postgres;

create table public.user
(
    id           bigserial
        constraint users_pkey
            primary key,
    name         varchar(50),
    surname      varchar(50),
    login        varchar(50),
    password     varchar(100),
    phone_number varchar(50),
    email        varchar(50),
    created      timestamp,
    changed      timestamp,
    deleted      timestamp,
    role_id      integer
        constraint users_roles_id_fk
            references public.role
);

alter table public.user
    owner to postgres;

create table public.terminal
(
    id               bigserial
        constraint terminals_pkey
            primary key,
    name             varchar(100),
    ip_address       varchar(15),
    physical_address varchar(100),
    port             smallint,
    created          timestamp(6),
    changed          timestamp(6),
    deleted          timestamp(6)
);

alter table public.terminal
    owner to postgres;

create table public.service
(
    id       bigserial
        constraint services_pkey
            primary key,
    name     varchar(100),
    duration integer,
    created  timestamp(6),
    changed  timestamp(6),
    deleted  timestamp(6)
);

alter table public.service
    owner to postgres;

create table public.advanced_appointment
(
    id          bigserial
        constraint advanced_appointment_pkey
            primary key,
    user_id     bigint,
    terminal_id bigint,
    service_id  bigint,
    date        date,
    time_from   time,
    time_to     time,
    created     timestamp(6) not null,
    changed     timestamp(6) not null,
    is_actual   boolean
);

alter table public.advanced_appointment
    owner to postgres;

create table public.l_breaks
(
    id                   bigserial
        constraint c_breaks_pkey
            primary key,
    break_id             bigint not null,
    week_day_schedule_id bigint not null
);

alter table public.l_breaks
    owner to postgres;

create table public.break
(
    id        bigserial
        constraint l_break_pkey
            primary key,
    from_time time,
    to_time   time,
    created   timestamp(6) not null,
    changed   timestamp(6) not null,
    is_actual boolean
);

alter table public.break
    owner to postgres;

create table public.terminal_services
(
    id          bigserial
        constraint l_terminal_services_pkey
            primary key,
    service_id  bigint,
    terminal_id bigint,
    is_actual   boolean,
    created     timestamp(6) not null,
    changed     timestamp(6) not null
);

alter table public.terminal_services
    owner to postgres;

create table public.week_day_schedule
(
    id          bigserial
        constraint week_day_schedule_pk
            primary key,
    time_begin  time         not null,
    time_end    time         not null,
    day_of_week integer,
    created     timestamp(6) not null,
    changed     timestamp(6) not null,
    is_actual   boolean
);

alter table public.week_day_schedule
    owner to postgres;

create unique index week_day_schedule_id_uindex
    on public.week_day_schedule (id);

create table public.calendar_out_days
(
    id                   bigserial
        constraint calendar_out_days_pk
            primary key,
    terminal_services_id bigint       not null,
    date                 date         not null,
    created              timestamp(6) not null,
    changed              timestamp(6) not null,
    is_actual            boolean
);

alter table public.calendar_out_days
    owner to postgres;

create unique index calendar_out_days_id_uindex
    on public.calendar_out_days (id);

create table public.l_week_days_schedule
(
    id                   bigserial
        constraint l_week_day_schedule_pk
            primary key,
    terminal_services_id bigint not null,
    week_day_schedule_id bigint not null
);

alter table public.l_week_days_schedule
    owner to postgres;

create unique index l_week_day_schedule_id_uindex
    on public.l_week_days_schedule (id);

