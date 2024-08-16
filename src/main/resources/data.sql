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


INSERT INTO contrato (description, date, labor, value_products, name_client, person_id)
VALUES
    ('Contrato de instalação de faróis', '2024-07-24', 150.00, 32.00, 'João Silva', 1),
    ('Contrato de substituição de lâmpadas', '2024-07-24', 75.00, 8.00, 'Maria Oliveira', 1),
    ('Contrato de troca de pneus', '2024-07-24', 200.00, 75.00, 'Carlos Souza', 1),
    ('Contrato de revisão completa', '2024-07-24', 300.00, 70.00, 'Ana Paula', 1),
    ('Contrato de troca de óleo', '2024-07-24', 50.00, 15.00, 'Pedro Almeida', 1),
    ('Contrato de revisão de freios', '2024-07-24', 100.00, 25.00, 'Lucas Fernandes', 1),
    ('Contrato de troca de amortecedores', '2024-07-24', 250.00, 50.00, 'Paula Lima', 1),
    ('Contrato de revisão elétrica', '2024-07-24', 175.00, 30.00, 'Bruno Rocha', 1),
    ('Contrato de instalação de retrovisores', '2024-07-24', 90.00, 20.00, 'Fernanda Souza', 1),
    ('Contrato de revisão geral', '2024-07-24', 350.00, 40.00, 'Rafael Alves', 1),
    ('Contrato de troca de correias', '2024-07-24', 150.00, 30.00, 'Carla Pereira', 1),
    ('Contrato de substituição de baterias', '2024-07-24', 120.00, 70.00, 'Renato Castro', 1),
    ('Contrato de ajuste de suspensão', '2024-07-24', 220.00, 50.00, 'Gabriela Costa', 1),
    ('Contrato de alinhamento e balanceamento', '2024-07-24', 180.00, 25.00, 'Marcelo Martins', 1),
    ('Contrato de troca de filtros', '2024-07-24', 110.00, 18.00, 'Sofia Lopes', 1),
    ('Contrato de revisão de motor', '2024-07-24', 280.00, 55.00, 'Ricardo Nunes', 1),
    ('Contrato de troca de pastilhas de freio', '2024-07-24', 140.00, 25.00, 'Juliana Melo', 1),
    ('Contrato de revisão de ar-condicionado', '2024-07-24', 130.00, 30.00, 'Leandro Vieira', 1),
    ('Contrato de troca de palhetas', '2024-07-24', 60.00, 15.00, 'Patrícia Dias', 1),
    ('Contrato de instalação de bombas de combustível', '2024-07-24', 190.00, 45.00, 'Thiago Barros', 1),
    ('Contrato de revisão de embreagem', '2024-07-24', 210.00, 50.00, 'Isabela Braga', 1),
    ('Contrato de ajuste de volante', '2024-07-24', 95.00, 30.00, 'Fábio Ribeiro', 1),
    ('Contrato de troca de alternador', '2024-07-24', 160.00, 55.00, 'André Gomes', 1),
    ('Contrato de instalação de faróis de neblina', '2024-07-24', 180.00, 32.00, 'Bruna Santana', 1),
    ('Contrato de revisão de direção', '2024-07-24', 230.00, 40.00, 'Aline Ramos', 1),
    ('Contrato de substituição de lâmpadas de freio', '2024-07-24', 85.00, 15.00, 'Diego Moreira', 1),
    ('Contrato de troca de filtros de cabine', '2024-07-24', 100.00, 25.00, 'Michele Borges', 1),
    ('Contrato de revisão de injeção eletrônica', '2024-07-24', 200.00, 45.00, 'Roberto Lima', 1),
    ('Contrato de troca de óleos e fluidos', '2024-07-24', 70.00, 15.00, 'Vitor Mendes', 1),
    ('Contrato de revisão completa premium', '2024-07-24', 400.00, 75.00, 'Beatriz Carvalho', 1);

INSERT INTO contrato_itens (total_value, contrato_id, product_id)
VALUES
    (32.00, 1, 1),
    (8.00, 2, 2),
    (75.00, 3, 3),
    (70.00, 4, 4),
    (15.00, 5, 5),
    (25.00, 6, 9),
    (50.00, 7, 13),
    (30.00, 8, 17),
    (20.00, 9, 14),
    (40.00, 10, 15),
    (30.00, 11, 18),
    (70.00, 12, 4),
    (50.00, 13, 13),
    (25.00, 14, 12),
    (18.00, 15, 8),
    (55.00, 16, 17),
    (25.00, 17, 12),
    (30.00, 18, 19),
    (15.00, 19, 10),
    (45.00, 20, 11),
    (50.00, 21, 13),
    (30.00, 22, 14),
    (55.00, 23, 17),
    (32.00, 24, 1),
    (40.00, 25, 15),
    (15.00, 26, 10),
    (25.00, 27, 19),
    (45.00, 28, 11),
    (15.00, 29, 5),
    (75.00, 30, 3);



