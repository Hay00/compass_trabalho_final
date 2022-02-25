-- Mesas já cadastradas
INSERT INTO
    MESA (NUMERO, CAPACIDADE, OCUPADA)
VALUES
    ('1A', 4, false),
    ('1B', 5, false),
    ('10', 7, false),
    ('20', 2, false),
    ('30', 4, false);

-- Contas vinculadas a mesas
INSERT INTO
    CONTA (STATUS, VALOR_TOTAL, MESA_ID)
VALUES
    (false, 20.63, 1),
    (true, 44.71, 3),
    (false, 87.22, 2),
    (false, 156.48, 1),
    (true, 18.87, 1);

INSERT INTO
    PEDIDO (STATUS_PEDIDO, VALOR_TOTAL, CONTA_ID)
VALUES
    ('Finalizado', 4, 1),
    ('Pendente', 20, 5),
    ('Pendente', 10, 5),
    ('Cancelado', 3, 2),
    ('Em Andamento', 14, 2);