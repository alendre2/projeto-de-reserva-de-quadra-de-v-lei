-- Inserir cozinhas (Exemplo adaptado para tipos de quadras ou categorias)
INSERT INTO cozinha (nome) VALUES ('Vôlei Indoor');
INSERT INTO cozinha (nome) VALUES ('Vôlei de Praia');
INSERT INTO cozinha (nome) VALUES ('Vôlei de Areia');
INSERT INTO cozinha (nome) VALUES ('Vôlei Sub-21');

-- Inserir restaurantes (Adaptado para quadras de vôlei e suas categorias)
INSERT INTO restaurante (nome, taxa_frete, cozinha_id) VALUES ('Centro Esportivo A', 15, 1);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id) VALUES ('Centro Esportivo B', 20, 2);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id) VALUES ('Academia Vôlei Pro', 10, 3);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id) VALUES ('Arena Vôlei Vibe', 12, 4);

-- Inserir estados (Exemplo para cidades ou estados que tenham quadras)
INSERT INTO estado (id, nome) VALUES (1, 'Pernambuco');
INSERT INTO estado (id, nome) VALUES (2, 'São Paulo');
INSERT INTO estado (id, nome) VALUES (3, 'Rio de Janeiro');
INSERT INTO estado (id, nome) VALUES (4, 'Minas Gerais');

-- Inserir cidades (Exemplo de localização das quadras)
INSERT INTO cidade (nome, estado_id) VALUES ('Recife', 1);
INSERT INTO cidade (nome, estado_id) VALUES ('São Paulo', 2);
INSERT INTO cidade (nome, estado_id) VALUES ('Rio de Janeiro', 3);
INSERT INTO cidade (nome, estado_id) VALUES ('Belo Horizonte', 4);

-- Inserir administradores (Com e-mails fictícios para o gerenciamento das quadras)
INSERT INTO administrador (email, nome) VALUES ('admin@centro.com', 'Carlos Silva');
INSERT INTO administrador (email, nome) VALUES ('admin@academia.com', 'Ana Souza');

-- Inserir jogadores (Jogadores que vão participar das reservas de quadra)
INSERT INTO jogador (contato, nome) VALUES ('999999999', 'João Pereira');
INSERT INTO jogador (contato, nome) VALUES ('988888888', 'Lucas Almeida');
INSERT INTO jogador (contato, nome) VALUES ('977777777', 'Maria Oliveira');
INSERT INTO jogador (contato, nome) VALUES ('966666666', 'Fernanda Costa');

-- Inserir quadras de vôlei (Com referência ao administrador)
INSERT INTO quadra (administrador_id, localizacao, nome, tipo) VALUES (1, 'Centro Esportivo A', 'Quadra 1', 'Vôlei Indoor');
INSERT INTO quadra (administrador_id, localizacao, nome, tipo) VALUES (2, 'Centro Esportivo B', 'Quadra 2', 'Vôlei de Praia');

-- Inserir reservas de quadra (Com referência à quadra)
INSERT INTO reserva (data, horario, quadra_id, status) VALUES ('2024-11-12', '10:00:00', 1, 'Confirmada');
INSERT INTO reserva (data, horario, quadra_id, status) VALUES ('2024-11-13', '14:00:00', 2, 'Pendente');

-- Inserir jogadores nas reservas de quadra (Com referências a jogador e reserva)
INSERT INTO reserva_jogadores (jogadores_id, reservas_id) VALUES (1, 1);
INSERT INTO reserva_jogadores (jogadores_id, reservas_id) VALUES (2, 1);
INSERT INTO reserva_jogadores (jogadores_id, reservas_id) VALUES (3, 2);
INSERT INTO reserva_jogadores (jogadores_id, reservas_id) VALUES (4, 2);

-- Inserir jogadores com suas posições nas reservas (Com referências a jogador_reserva)
INSERT INTO jogador_reserva (jogador_id, reserva_id, posicao) VALUES (1, 1, 'Atacante');
INSERT INTO jogador_reserva (jogador_id, reserva_id, posicao) VALUES (2, 1, 'Bloqueador');
INSERT INTO jogador_reserva (jogador_id, reserva_id, posicao) VALUES (3, 2, 'Libero');
INSERT INTO jogador_reserva (jogador_id, reserva_id, posicao) VALUES (4, 2, 'Ponteiro');
