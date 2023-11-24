-- Migration para popular a tabela supplier
INSERT INTO supplier (code, name, state) VALUES
  ('S12345', 'Suprimentos Express', true),
  ('S54321', 'Global Materials', false),
  ('S98765', 'Quality Merchants', true),
  ('S45678', 'Infinite Supplies', false),
  ('S87654', 'Trade Connect', true),
  ('S23456', 'Pinnacle Resources', false),
  ('S78901', 'Eco Ventures', true),
  ('S34567', 'Elite Procure', false),
  ('S21098', 'Harmony Suppliers', true),
  ('S87654', 'Vista Enterprises', false);


-- Migration para popular a tabela category
INSERT INTO category (name, state) VALUES
  ('Camisetas de eventos', 'true'),
  ('Calças', 'true'),
  ('Jalecos', 'true'),
  ('Camisa social', 'true'),
  ('Serviços gerais', 'true'),
  ('Óculos de Proteção', 'true'),
  ('Uniformes Esportivos', 'true'),
  ('Estoque', 'true'),
  ('TI', 'true');


-- Migration para popular a tabela batch
INSERT INTO batch (batch_code, description, quantity, acquisition_date, id_supplier, id_category) VALUES
  ('B12345', 'Lote de Camisetas de Eventos', 100, NOW(), 1, 1),
  ('B54321', 'Lote de Calças', 50, NOW(), 3, 2),
  ('B98765', 'Lote de Jalecos', 75, NOW(), 5, 3),
  ('B45678', 'Lote de Camisa Social', 30, NOW(), 7, 4),
  ('B87654', 'Lote de Serviços Gerais', 20, NOW(), 9, 5),
  ('B23456', 'Lote de Óculos de Proteção', 40, NOW(), 1, 6),
  ('B78901', 'Lote de Uniformes Esportivos', 60, NOW(), 3, 7),
  ('B34567', 'Lote de Estoque', 90, NOW(), 5, 8),
  ('B21098', 'Lote de TI', 25, NOW(), 7, 9),
  ('B87654', 'Lote de Uniformes Esportivos', 70, NOW(), 9, 7),
  ('B10293', 'Lote de Camisetas de Eventos', 45, NOW(), 1, 1),
  ('B78945', 'Lote de Serviços Gerais', 55, NOW(), 3, 5),
  ('B65432', 'Lote de Óculos de Proteção', 80, NOW(), 5, 6),
  ('B54321', 'Lote de Camisa Social', 35, NOW(), 7, 4),
  ('B78910', 'Lote de Calças', 15, NOW(), 9, 2),
  ('B34567', 'Lote de Jalecos', 10, NOW(), 1, 3),
  ('B89012', 'Lote de Uniformes Esportivos', 120, NOW(), 3, 7),
  ('B56789', 'Lote de Estoque', 95, NOW(), 5, 8),
  ('B45678', 'Lote de Uniformes Esportivos', 50, NOW(), 7, 7),
  ('B12345', 'Lote de Calças', 30, NOW(), 9, 2),
  ('B67890', 'Lote de Camisetas de Eventos', 25, NOW(), 1, 1),
  ('B23456', 'Lote de Jalecos', 40, NOW(), 3, 3),
  ('B89012', 'Lote de Óculos de Proteção', 70, NOW(), 5, 6),
  ('B56789', 'Lote de Serviços Gerais', 55, NOW(), 7, 5),
  ('B45678', 'Lote de Camisa Social', 85, NOW(), 9, 4),
  ('B34567', 'Lote de Uniformes Esportivos', 65, NOW(), 1, 7),
  ('B90123', 'Lote de Estoque', 20, NOW(), 3, 8),
  ('B78945', 'Lote de TI', 10, NOW(), 5, 9),
  ('B65432', 'Lote de Uniformes Esportivos', 30, NOW(), 7, 7),
  ('B23456', 'Lote de Jalecos', 100, NOW(), 9, 3);


-- Migration para popular a tabela uniform
INSERT INTO uniform (name, quantity, sex, size, id_batch) VALUES
  ('Camiseta Evento P', 20, 'M', 'P', 1),
  ('Calça Casual M', 15, 'F', 'M', 2),
  ('Jaleco Branco G', 25, 'M', 'G', 3),
  ('Camisa Social Azul M', 10, 'M', 'M', 4),
  ('Uniforme Serviços Gerais P', 30, 'F', 'P', 5),
  ('Óculos Proteção UV', 40, 'M', 'Único', 6),
  ('Uniforme Esportivo Azul P', 10, 'F', 'P', 7),
  ('Uniforme Estoque Cinza G', 20, 'M', 'G', 8),
  ('Uniforme TI Preto M', 5, 'M', 'M', 9),
  ('Uniforme Esportivo Preto M', 15, 'F', 'M', 10),
  ('Camiseta Evento M', 25, 'M', 'M', 1),
  ('Uniforme Serviços Gerais M', 8, 'F', 'M', 2),
  ('Óculos Proteção Incolor', 12, 'M', 'Único', 3),
  ('Camisa Social Branca P', 18, 'F', 'P', 4),
  ('Calça Casual G', 22, 'M', 'G', 5),
  ('Jaleco Azul M', 13, 'M', 'M', 6),
  ('Uniforme Esportivo Verde G', 10, 'F', 'G', 7),
  ('Uniforme Estoque Azul P', 7, 'M', 'P', 8),
  ('Uniforme Esportivo Amarelo G', 20, 'F', 'G', 9),
  ('Calça Casual P', 30, 'F', 'P', 10);


  -- Migration para popular a tabela user
  INSERT INTO users (name, email, password)
  VALUES
    ('João Silva', 'joao@example.com', 'senha123'),
    ('Maria Oliveira', 'maria@example.com', 'senha456'),
    ('Carlos Santos', 'carlos@example.com', 'senha789');
