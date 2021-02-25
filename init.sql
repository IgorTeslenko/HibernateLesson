BEGIN;

SET search_path TO hibernate_lessons;

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial PRIMARY KEY, title VARCHAR(255), price NUMERIC);
INSERT INTO products (title, price) VALUES
('Яблоки', 10),
('Помидоры', 15),
('Огурцы', 16),
('Кукуруза', 20),
('Бананы', 12),
('Киви', 17),
('Баклажаны', 14),
('Авокадо', 22),
('Апельсины', 21),
('Мандарины', 30),
('Кабачки', 15),
('Чеснок', 20),
('Перец', 13),
('Картофель', 17),
('Сметана', 25),
('Йогурт', 30),
('Молоко', 20),
('Говядина', 70),
('Баранина', 80),
('Свинина', 60);

COMMIT;