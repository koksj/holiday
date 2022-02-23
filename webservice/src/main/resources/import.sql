-- This file allow to write SQL commands that will be emitted in test and dev.

INSERT INTO public.country (countrypk, country_code, country_name) VALUES (1, 'IL', 'Israel');

INSERT INTO public.holiday (id, date, name) VALUES (1, '2022-04-15', 'First day of Passover');
INSERT INTO public.holiday (id, date, name) VALUES (2, '2022-04-16', 'First day of Passover');
INSERT INTO public.holiday (id, date, name) VALUES (3, '2022-04-21', 'Seventh day of Passover');
INSERT INTO public.holiday (id, date, name) VALUES (4, '2022-04-22', 'Seventh day of Passover');
INSERT INTO public.holiday (id, date, name) VALUES (5, '2022-05-04', 'Yom Ha''atzmaut');
INSERT INTO public.holiday (id, date, name) VALUES (6, '2022-05-05', 'Yom Ha''atzmaut');
INSERT INTO public.holiday (id, date, name) VALUES (7, '2022-06-04', 'Shavuot');
INSERT INTO public.holiday (id, date, name) VALUES (8, '2022-06-05', 'Shavuot');
INSERT INTO public.holiday (id, date, name) VALUES (9, '2022-09-25', 'Rosh Hashanah');
INSERT INTO public.holiday (id, date, name) VALUES (10, '2022-09-26', 'Rosh Hashanah');
INSERT INTO public.holiday (id, date, name) VALUES (11, '2022-09-27', 'Rosh Hashanah');
INSERT INTO public.holiday (id, date, name) VALUES (12, '2022-10-04', 'Yom Kippur');

INSERT INTO public.country_holiday (countryentity_countrypk, holidayentities_id) VALUES (1, 1);
INSERT INTO public.country_holiday (countryentity_countrypk, holidayentities_id) VALUES (1, 2);
INSERT INTO public.country_holiday (countryentity_countrypk, holidayentities_id) VALUES (1, 3);
INSERT INTO public.country_holiday (countryentity_countrypk, holidayentities_id) VALUES (1, 4);
INSERT INTO public.country_holiday (countryentity_countrypk, holidayentities_id) VALUES (1, 5);
INSERT INTO public.country_holiday (countryentity_countrypk, holidayentities_id) VALUES (1, 6);
INSERT INTO public.country_holiday (countryentity_countrypk, holidayentities_id) VALUES (1, 7);
INSERT INTO public.country_holiday (countryentity_countrypk, holidayentities_id) VALUES (1, 8);
INSERT INTO public.country_holiday (countryentity_countrypk, holidayentities_id) VALUES (1, 9);
INSERT INTO public.country_holiday (countryentity_countrypk, holidayentities_id) VALUES (1, 10);
INSERT INTO public.country_holiday (countryentity_countrypk, holidayentities_id) VALUES (1, 11);
INSERT INTO public.country_holiday (countryentity_countrypk, holidayentities_id) VALUES (1, 12);