CREATE TABLE IF NOT EXISTS category (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(100),
    created_by UUID,
    create_date DATE,
    update_date DATE
);


-- INSERT INTO category (id, name, description, created_by, create_date, update_date) VALUES
--  ('aaaaaaaa-1111-1111-1111-111111111111', 'Electronics', 'Electronic gadgets', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '2024-10-01', '2025-01-01'),
--  ('aaaaaaaa-2222-2222-2222-222222222222', 'Furniture', 'Household and office furniture', 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', '2024-10-05', '2025-01-05'),
--  ('aaaaaaaa-3333-3333-3333-333333333333', 'Groceries', 'Daily essential food items', 'cccccccc-cccc-cccc-cccc-cccccccccccc', '2024-11-01', '2025-01-10');


CREATE TABLE IF NOT EXISTS product (
                                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    stock INT NOT NULL,
    unit_price DOUBLE PRECISION NOT NULL,
    category_id UUID REFERENCES category(id),
    weight DOUBLE PRECISION,
    weight_unit VARCHAR(20),
    dimension VARCHAR(50),
    image_url VARCHAR(255),
    is_active BOOLEAN DEFAULT TRUE,
    created_by UUID,
    created_date DATE,
    updated_date DATE
);
-- INSERT INTO product (
--     id, name, description, stock, unit_price,
--     category_id, weight, weight_unit, dimension,
--     image_url, is_active, created_by, created_date, updated_date
-- ) VALUES
--       ('11111111-2222-3333-4444-555555555511', 'Smartphone X1', 'Latest 5G smartphone', 150, 799.99,
--        'aaaaaaaa-1111-1111-1111-111111111111', 180, 'grams', '150x70x7 mm',
--        'http://img.com/smartphone-x1.jpg', TRUE, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '2024-10-10', '2025-01-10'),
--
--       ('11111111-2222-3333-4444-555555555522', 'Office Chair Pro', 'Ergonomic office chair', 90, 199.49,
--        'aaaaaaaa-2222-2222-2222-222222222222', 12, 'kg', '120x60x50 cm',
--        'http://img.com/office-chair.jpg', TRUE, 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', '2024-12-01', '2025-01-08'),
--
--       ('11111111-2222-3333-4444-555555555533', 'Organic Rice 5kg', 'Premium Japanese rice', 300, 18.99,
--        'aaaaaaaa-3333-3333-3333-333333333333', 5, 'kg', NULL,
--        'http://img.com/rice-5kg.jpg', TRUE, 'cccccccc-cccc-cccc-cccc-cccccccccccc', '2025-01-01', '2025-01-20');
