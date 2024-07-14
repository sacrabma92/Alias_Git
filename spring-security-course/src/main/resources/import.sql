INSERT INTO "user" (username, name, password, role) VALUES ('ljuan', 'luis juan', '$2a$10$qpZjDEb7yrq1CkdXt45oJuT3lmOPncG0V.4tvGpOiYS1VQqY8kkcK', 'ROLE_CUSTOMER');
INSERT INTO "user" (username, name, password, role) VALUES ('jmarcos', 'juan marcos', '$2a$10$QU4mZyX7YEgzVmU1C8rvSu27paHe47XPcCLhuOsZst5i5fO9RKQ.G', 'ROLE_ASSISTANT_ADMINISTRATOR');
INSERT INTO "user" (username, name, password, role) VALUES ('cramirez', 'Carlos Ramirez', '$2a$10$Z2WQQfMoOBvqtQta677HPe65tTSBAGSkbqunlVe0cIp/Y22LTFN8i', 'ROLE_ADMINISTRATOR');

INSERT INTO category (name, status) VALUES ('Electrónica', 'ENABLED');
INSERT INTO category (name, status) VALUES ('Ropa', 'ENABLED');
INSERT INTO category (name, status) VALUES ('Deportes', 'ENABLED');
INSERT INTO category (name, status) VALUES ('Hogar', 'ENABLED');

INSERT INTO product (name, price, status, category_id) VALUES ('Smartphone', 500.00, 'ENABLED', 1);
INSERT INTO product (name, price, status, category_id) VALUES ('Auriculares Bluetooth', 50.00, 'DISABLED', 1);
INSERT INTO product (name, price, status, category_id) VALUES ('Tablet', 300.00, 'ENABLED', 1);

INSERT INTO product (name, price, status, category_id) VALUES ('Camiseta', 25.00, 'ENABLED', 2);
INSERT INTO product (name, price, status, category_id) VALUES ('Pantalones', 35.00, 'ENABLED', 2);
INSERT INTO product (name, price, status, category_id) VALUES ('Zapatos', 45.00, 'ENABLED', 2);

INSERT INTO product (name, price, status, category_id) VALUES ('Balón de Fútbol', 20.00, 'ENABLED', 3);
INSERT INTO product (name, price, status, category_id) VALUES ('Raqueta de Tenis', 80.00, 'DISABLED', 3);

INSERT INTO product (name, price, status, category_id) VALUES ('Aspiradora', 120.00, 'ENABLED', 4);
INSERT INTO product (name, price, status, category_id) VALUES ('Licuadora', 50.00, 'ENABLED', 4);
