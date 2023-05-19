insert into public.week_day_schedule (id, time_begin, time_end, day_of_week, created, changed, is_actual) values (1, '09:00:00', '18:00:00', null, '2023-05-16 10:46:21.000000', '2023-05-16 10:46:23.000000', true);

insert into public.user (id, name, surname, login, password, phone_number, email, created, changed, deleted) values (1, 'Васян', 'Пупкинович', 'VasyaLog', 'VasyaPass', '123', 'asdsa@mail.ru', '2023-05-16 01:04:13.000000', '2023-05-16 01:04:15.000000', null);
insert into public.user (id, name, surname, login, password, phone_number, email, created, changed, deleted) values (2, 'Петя', 'Петрович', 'PetyaLog', 'PetyaPass', '321', 'zxczc@mail.ru', '2023-05-16 01:04:13.000000', '2023-05-16 01:04:15.000000', null);

insert into public.role (id, name, created, changed, is_actual) values (1, 'ADMIN', '2023-05-16 01:02:33.000000', '2023-05-16 01:02:35.000000', true);
insert into public.role (id, name, created, changed, is_actual) values (2, 'USER', '2023-05-16 01:02:56.000000', '2023-05-16 01:02:58.000000', true);

insert into public.l_breaks (id, break_id, week_day_schedule_id) values (1, 1, 1);
insert into public.l_breaks (id, break_id, week_day_schedule_id) values (2, 2, 1);

insert into public.break (id, from_time, to_time, created, changed, is_actual) values (1, '10:00:00', '11:00:00', '2023-05-15 20:57:23.000000', '2023-05-15 20:57:25.000000', true);
insert into public.break (id, from_time, to_time, created, changed, is_actual) values (2, '13:00:00', '14:00:00', '2023-05-16 10:45:32.000000', '2023-05-16 10:45:34.000000', true);

insert into public.l_user_role (id, user_id, role_id, created, changed, is_actual) values (1, 1, 1, '2023-05-16 01:06:12.000000', '2023-05-16 01:06:13.000000', true);
insert into public.l_user_role (id, user_id, role_id, created, changed, is_actual) values (2, 1, 2, '2023-05-16 01:06:42.000000', '2023-05-16 01:06:43.000000', true);
insert into public.l_user_role (id, user_id, role_id, created, changed, is_actual) values (3, 2, 1, '2023-05-16 01:06:58.000000', '2023-05-16 01:06:59.000000', true);
