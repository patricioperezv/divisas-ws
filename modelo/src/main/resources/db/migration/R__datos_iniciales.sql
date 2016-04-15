TRUNCATE divisas CASCADE;
INSERT INTO divisas (iso, nombre) VALUES ('CLP', 'Peso Chileno');
INSERT INTO campus (iso, nombre) VALUES ('USD', 'Dolar Estadounidense');

TRUNCATE cambios CASCADE;
INSERT INTO cambios (divisa1_id, divisa2_id, monto, fecha) VALUES ((SELECT id from divisas WHERE iso = 'USD'), (SELECT id from divisas WHERE iso = 'CLP'), 682.45, '2016-04-11');
INSERT INTO cambios (divisa1_id, divisa2_id, monto, fecha) VALUES ((SELECT id from divisas WHERE iso = 'USD'), (SELECT id from divisas WHERE iso = 'CLP'), 682.16, '2016-04-12');
INSERT INTO cambios (divisa1_id, divisa2_id, monto, fecha) VALUES ((SELECT id from divisas WHERE iso = 'USD'), (SELECT id from divisas WHERE iso = 'CLP'), 674.58, '2016-04-13');
INSERT INTO cambios (divisa1_id, divisa2_id, monto, fecha) VALUES ((SELECT id from divisas WHERE iso = 'USD'), (SELECT id from divisas WHERE iso = 'CLP'), 670.80, '2016-04-14');

