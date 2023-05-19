INSERT INTO ci_modules (name, price, type)
VALUES ('Quantis CI+ 1.3', 70, 'Quantis'),
       ('Smit CI+ 1.3', 61, 'SMiT');

INSERT INTO remote_controllers(compatible_with, battery_type, name, brand, price, original_stock)
VALUES ('Sony', 'AAA', 'URC4912', 'One For Al', 23.99, 5),
       ('Philips', 'AAA', 'URC4913', 'One For Al', 36.99, 5),
       ('Samsung', 'AAA', 'URC4910', 'One For Al', 26.99, 5);

INSERT INTO wall_brackets(size, adjustable, name, price)
VALUES (48, true, 'BlueBuilt Muurbeugel vanaf 48"', 112),
       (42, true, 'Vogels Muurbeugel vanaf 32"', 120),
       (52, true, 'Vogels comfort Muurbeugel vanaf 48"', 179);

INSERT INTO televisions(type, brand, name, price, available_size, refresh_rate, screen_type, screen_quality, smart_tv, wifi, voice_control, hdr, bluetooth, ambilight, original_stock, sold)
VALUES ('903226', 'Samsung', 'Samsung QLED 65Q74B', 1499, 65, 100, 'LED-LCD', 'Goed', true, true, true, true, true, false, 25, 5),
       ('925022', 'Philips', 'Philips The One 43PUS8837', 699, 43, 100, 'LED-LCD', 'Goed', true, true, true, true, true, true, 20, 5),
       ('906264', 'Sony', 'Sony Bravia KD-43X85KP', 648, 43, 100, 'LED-LCD', 'Goed', true, true, true, true, true, false, 15, 3),
       ('903314', 'LG', 'LG OLED42C24LA (2022)', 1099, 42, 100, 'OLED', 'Uitstekend', true, true, true, true, true, false, 12, 6);