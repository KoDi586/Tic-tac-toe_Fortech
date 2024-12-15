
CREATE TABLE IF NOT EXISTS public."user" (
    id bigint PRIMARY KEY,
    username VARCHAR(100) ,
    "password" VARCHAR(100),
    rating integer,
    status varchar(255)
);

CREATE TABLE IF NOT EXISTS public.game (
    game_id bigint PRIMARY KEY,
    first_user_id bigint,
    second_user_id bigint,
    user_id_who_stepped bigint,
    status varchar(255)
);


CREATE TABLE IF NOT EXISTS public.game_field (
    field_id bigint PRIMARY KEY,
    game_id bigint,
    field1 varchar(2),
    field2 varchar(2),
    field3 varchar(2),
    field4 varchar(2),
    field5 varchar(2),
    field6 varchar(2),
    field7 varchar(2),
    field8 varchar(2),
    field9 varchar(2)
);