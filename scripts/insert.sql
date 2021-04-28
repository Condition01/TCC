INSERT INTO PRODUTO VALUES('FRUT1', 'Abacate Mexicano', 'Uma delícia', 'Fruta', '');
INSERT INTO PRODUTO VALUES('FRUT2', 'Abacaxi Vitória', 'Uma delícia', 'Fruta', '');
INSERT INTO PRODUTO VALUES('FRUT3', 'Mamão Papaya', 'Uma delícia', 'Fruta', '');
INSERT INTO PRODUTO VALUES('FRUT4', 'Laranja Pera', 'Uma delícia', 'Fruta', '');
INSERT INTO PRODUTO VALUES('FRUT5', 'Maçã Argentina', 'Uma delícia', 'Fruta', '');
INSERT INTO PRODUTO VALUES('FRUT6', 'Laranja Lima', 'Uma delícia', 'Fruta', '');
INSERT INTO PRODUTO VALUES('FRUT7', 'Carambola', 'Uma delícia', 'Fruta', '');
INSERT INTO PRODUTO VALUES('FRUT8', 'Melancia Forrageira', 'Uma delícia', 'Fruta', '');
INSERT INTO PRODUTO VALUES('VER1', 'Alface Crespo', 'Uma delícia', 'Verdura', '');
INSERT INTO PRODUTO VALUES('VER2', 'Alface Lisa', 'Uma delícia', 'Verdura', '');
INSERT INTO PRODUTO VALUES('VER3', 'Couve Galega', 'Uma delícia', 'Verdura', '');
INSERT INTO PRODUTO VALUES('VER4', 'Agrião', 'Uma delícia', 'Verdura', '');
INSERT INTO PRODUTO VALUES('VER5', 'Repolho', 'Uma delícia', 'Verdura', '');
INSERT INTO PRODUTO VALUES('VER6', 'Repolho Roxo', 'Uma delícia', 'Verdura', '');
INSERT INTO PRODUTO VALUES('VER7', 'Coentro', 'Uma delícia', 'Verdura', '');
INSERT INTO PRODUTO VALUES('VER8', 'Rúcula', 'Uma delícia', 'Verdura', '');
INSERT INTO PRODUTO VALUES('LEG1', 'Abóbora', 'Uma delícia', 'Legumes', '');
INSERT INTO PRODUTO VALUES('LEG2', 'Abóbora Japonesa', 'Uma delícia', 'Legumes', '');
INSERT INTO PRODUTO VALUES('LEG3', 'Abobrinha', 'Uma delícia', 'Legumes', '');
INSERT INTO PRODUTO VALUES('LEG4', 'Berinjela', 'Uma delícia', 'Legumes', '');
INSERT INTO PRODUTO VALUES('LEG5', 'Pimentão Vermelho', 'Uma delícia', 'Legumes', '');
INSERT INTO PRODUTO VALUES('LEG6', 'Quiabo', 'Uma delícia', 'Legumes', '');
INSERT INTO PRODUTO VALUES('LEG7', 'Tomate Caqui', 'Uma delícia', 'Legumes', '');
INSERT INTO PRODUTO VALUES('LEG8', 'Mandioquinha', 'Uma delícia', 'Legumes', '');

INSERT INTO ENDERECO(NUMERO, CEP, COMPLEMENTO, BAIRRO, CIDADE, UF) VALUES('442', '44002080', 'DO LADO DA CASA BRANCA', 'Rua 13 de Novembro', 'Feira de Santana', 'BA');

INSERT INTO FEIRA(ID_ENDERECO, NOME, TELEFONE_CONTATO, CONTEXTO, LATITUDE, LONGITUDE) VALUES(1, 'Feira da Feira de Santana', '73999988888', 'ffsantana', '-12.2626582', '-38.9577475');

INSERT INTO PRODUTO_FEIRA VALUES('FRUT1', 1, 12.90, true, 'Kilo');