INSERT INTO public."user" (id, "username", "password")
VALUES
    (1, 'username', 'password'),
    (2, 'admin', 'admin')

ON CONFLICT (id) DO NOTHING;

--select * from public."user";