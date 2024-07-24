INSERT INTO person (login, password, name, email, phone, role)
VALUES
    ('lucas', '$2a$10$M9EXjmb1W4tfAm4UbH9hpeY8OR0jyUEA4M5FlLiYrUd9G/GchBZi6', 'Lucas Aparecido', 'lucas@gmail.com', '997574461', 'ADMIN');

INSERT INTO product (name, person_id, marca, code, quantity, status)
VALUES
    ('FAROL', 1, 'Power', '051000f018fghffffd14', 5, true),
    ('LÂMPADA', 1, 'LightCo', '062000a019ehhjjgghd15', 10, true),
    ('PNEU', 1, 'Speedy', '073000b020ijkklmffg16', 20, true),
    ('BATERIA', 1, 'ChargeUp', '084000c021lmmnnnoog17', 15, false),
    ('ÓLEO MOTOR', 1, 'LubriMax', '095000d022opqrrsstt18', 25, true),
    ('VELA DE IGNIÇÃO', 1, 'SparkPlug', '106000e023stuuuvvvw19', 30, true),
    ('FILTRO DE AR', 1, 'AirFlow', '117000f024vwxyyyzzz20', 8, true),
    ('FILTRO DE ÓLEO', 1, 'OilFilter', '128000g025zzzaaabbb21', 12, true),
    ('FREIO', 1, 'BrakePro', '139000h026bccdddeee22', 7, true),
    ('PALHETA', 1, 'ClearView', '140000i027effggghhh23', 18, true),
    ('BOMBA DE COMBUSTÍVEL', 1, 'FuelFlow', '151000j028gghhiiijj24', 5, false),
    ('PASTILHA DE FREIO', 1, 'BrakePad', '162000k029ijkkllmmo25', 12, true),
    ('AMORTECEDOR', 1, 'ShockAbs', '173000l030llmnnnoopp26', 10, true),
    ('RETROVISOR', 1, 'MirrorMax', '184000m031opqrrsstt27', 20, true),
    ('VOLANTE', 1, 'SteerPro', '195000n032stuuuvvvw28', 6, true),
    ('FILTRO DE COMBUSTÍVEL', 1, 'FuelFilter', '206000o033vwxyyyzzz29', 10, true),
    ('ALTERNADOR', 1, 'PowerGen', '217000p034zzzaaabbb30', 8, true),
    ('CORREIA DENTADA', 1, 'BeltDrive', '228000q035bccdddeee31', 15, true),
    ('FILTRO DE CABINE', 1, 'CabinAir', '239000r036effggghhh32', 7, true),
    ('LÂMPADA DE FAROL', 1, 'BrightLight', '240000s037gghhiiijj33', 25, true);



INSERT INTO price_product (date, lucro, price, price_sale, product_id)
VALUES
    ('2024-07-24', 87, 27, 32, 1),
    ('2024-07-24', 45, 5, 8, 2),
    ('2024-07-24', 150, 60, 75, 3),
    ('2024-07-24', 120, 50, 70, 4),
    ('2024-07-24', 30, 10, 15, 5),
    ('2024-07-24', 55, 15, 20, 6),
    ('2024-07-24', 25, 7, 10, 7),
    ('2024-07-24', 35, 12, 18, 8),
    ('2024-07-24', 70, 20, 25, 9),
    ('2024-07-24', 40, 10, 15, 10),
    ('2024-07-24', 95, 35, 45, 11),
    ('2024-07-24', 50, 20, 25, 12),
    ('2024-07-24', 80, 40, 50, 13),
    ('2024-07-24', 60, 25, 30, 14),
    ('2024-07-24', 75, 30, 40, 15),
    ('2024-07-24', 45, 15, 20, 16),
    ('2024-07-24', 110, 45, 55, 17),
    ('2024-07-24', 65, 25, 30, 18),
    ('2024-07-24', 55, 20, 25, 19),
    ('2024-07-24', 100, 35, 45, 20);

