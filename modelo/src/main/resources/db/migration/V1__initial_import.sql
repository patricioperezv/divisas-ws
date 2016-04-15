--
-- Nombre: divisas
--

DROP TABLE IF EXISTS divisas CASCADE;
CREATE TABLE divisas (
  id        BIGSERIAL       NOT NULL,
  iso    VARCHAR(255) NOT NULL UNIQUE,
  nombre VARCHAR(255) NOT NULL UNIQUE,
  PRIMARY KEY (id)
);


--
-- Nombre: cambios
--
DROP TABLE IF EXISTS cambios CASCADE;
CREATE TABLE cambios (
  id          BIGSERIAL       NOT NULL,
  divisa1_id   BIGINT          NOT NULL REFERENCES divisas (id) ON UPDATE CASCADE ON DELETE CASCADE,
  divisa2_id   BIGINT          NOT NULL REFERENCES divisas (id) ON UPDATE CASCADE ON DELETE CASCADE,
  monto DOUBLE PRECISION NOT NULL,
  fecha DATE NOT NULL DEFAULT NOW(),
  UNIQUE (divisa1_id, divisa2_id, fecha),
  PRIMARY KEY (id)
);
