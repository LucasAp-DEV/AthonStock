INSERT INTO person (login, password, name, email, phone, role)
VALUES
    ('lucas', '$2a$10$M9EXjmb1W4tfAm4UbH9hpeY8OR0jyUEA4M5FlLiYrUd9G/GchBZi6', 'Lucas Aparecido', 'lucas@gmail.com', '997574461', 'ADMIN');

INSERT INTO store (name, cnpj, address, status, person_id)
VALUES
    ('FoodFlow', '60.490.445/0001-78', 'Rua Marfin 161B', 1, 1);
