create table if not exists public.role
(
    id bigserial,
    name varchar(50),
    created timestamp not null,
    changed timestamp not null,
    is_actual boolean not null,
    constraint roles_pkey
    primary key (id),
    constraint roles_name_key
    unique (name)
    );

alter table public.role owner to postgres;

create table if not exists public."user"
(
    id bigserial,
    name varchar(100),
    surname varchar(100),
    login varchar(100),
    password varchar(100),
    phone_number varchar(50),
    email varchar(100),
    created timestamp,
    changed timestamp,
    deleted timestamp,
    constraint users_pkey
    primary key (id),
    constraint user_login_key
    unique (login),
    constraint user_email_key
    unique (email)
    );

alter table public."user" owner to postgres;

create table if not exists public.terminal
(
    id bigserial,
    name varchar(100),
    ip_address varchar(15),
    physical_address varchar(200),
    port smallint,
    created timestamp(6),
    changed timestamp(6),
    deleted timestamp(6),
    constraint terminals_pkey
    primary key (id)
    );

alter table public.terminal owner to postgres;

create table if not exists public.service
(
    id bigserial,
    name varchar(100),
    duration integer,
    created timestamp(6),
    changed timestamp(6),
    deleted timestamp(6),
    constraint services_pkey
    primary key (id)
    );

alter table public.service owner to postgres;

create unique index if not exists service_name_duration_uindex
    on public.service (name, duration);

create table if not exists public.advanced_appointment
(
    id bigserial,
    user_id bigint,
    terminal_id bigint,
    service_id bigint,
    date date,
    time_from time,
    time_to time,
    created timestamp(6) not null,
    changed timestamp(6) not null,
    is_actual boolean,
    constraint advanced_appointment_pkey
    primary key (id)
    );

alter table public.advanced_appointment owner to postgres;

create table if not exists public.l_breaks
(
    id bigserial,
    break_id bigint not null,
    week_day_schedule_id bigint not null,
    created timestamp default CURRENT_TIMESTAMP,
    constraint c_breaks_pkey
    primary key (id)
    );

alter table public.l_breaks owner to postgres;

create unique index if not exists l_breaks_break_id_week_day_schedule_id_uindex
    on public.l_breaks (break_id, week_day_schedule_id);

create unique index if not exists l_breaks_break_id_week_day_schedule_id_uindex_2
    on public.l_breaks (break_id, week_day_schedule_id);

create table if not exists public.break
(
    id bigserial,
    from_time time,
    to_time time,
    created timestamp(6) not null,
    changed timestamp(6) not null,
    is_actual boolean,
    constraint l_break_pkey
    primary key (id)
    );

alter table public.break owner to postgres;

create unique index if not exists break_from_time_to_time_uindex
    on public.break (from_time, to_time);

create table if not exists public.terminal_services
(
    id bigserial,
    service_id bigint,
    terminal_id bigint,
    is_actual boolean,
    created timestamp(6) not null,
    changed timestamp(6) not null,
    constraint l_terminal_services_pkey
    primary key (id),
    constraint terminal_services__fk
    foreign key (terminal_id) references public.terminal,
    constraint terminal_services_service_id_fk
    foreign key (service_id) references public.service
    );

alter table public.terminal_services owner to postgres;

create unique index if not exists terminal_services_service_id_terminal_id_uindex
    on public.terminal_services (service_id, terminal_id);

create table if not exists public.week_day_schedule
(
    id bigserial,
    time_begin time not null,
    time_end time not null,
    day_of_week varchar(20),
    created timestamp(6) not null,
    changed timestamp(6) not null,
    is_actual boolean,
    constraint week_day_schedule_pk
    primary key (id)
    );

alter table public.week_day_schedule owner to postgres;

create unique index if not exists week_day_schedule_id_uindex
    on public.week_day_schedule (id);

create table if not exists public.calendar_out_days
(
    id bigserial,
    terminal_services_id bigint,
    date date not null,
    created timestamp(6) not null,
    changed timestamp(6) not null,
    is_actual boolean,
    constraint calendar_out_days_pk
    primary key (id)
    );

alter table public.calendar_out_days owner to postgres;

create unique index if not exists calendar_out_days_id_uindex
    on public.calendar_out_days (id);

create unique index if not exists calendar_out_days_terminal_services_id_date_uindex
    on public.calendar_out_days (terminal_services_id, date);

create table if not exists public.l_week_days_schedule
(
    id bigserial,
    terminal_services_id bigint not null,
    week_day_schedule_id bigint not null,
    created timestamp default CURRENT_TIMESTAMP,
    constraint l_week_day_schedule_pk
    primary key (id)
    );

alter table public.l_week_days_schedule owner to postgres;

create unique index if not exists l_week_days_schedule_id_uindex
    on public.l_week_days_schedule (id);

create unique index if not exists l_week_days_schedule_terminal_services_id_week_day_schedule_id_
    on public.l_week_days_schedule (terminal_services_id, week_day_schedule_id);

create table if not exists public.l_user_role
(
    id bigserial,
    user_id bigint not null,
    role_id integer not null,
    created timestamp(6) default CURRENT_TIMESTAMP not null,
    changed timestamp(6) default CURRENT_TIMESTAMP,
    is_actual boolean default true not null,
    constraint l_user_role_pk
    primary key (id)
    );

alter table public.l_user_role owner to postgres;

create unique index if not exists l_user_role_id_uindex
    on public.l_user_role (id);

create unique index if not exists l_user_role_user_id_role_id_uindex
    on public.l_user_role (user_id, role_id);

