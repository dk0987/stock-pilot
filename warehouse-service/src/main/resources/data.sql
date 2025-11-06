CREATE TABLE IF NOT EXISTS warehouse (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    city VARCHAR(100),
    state VARCHAR(100),
    zip VARCHAR(20) NOT NULL,
    country VARCHAR(100) NOT NULL,
    phone VARCHAR(50) NOT NULL,
    email VARCHAR(255),
    is_active BOOLEAN DEFAULT TRUE,
    user_id UUID,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by UUID
);

CREATE TABLE IF NOT EXISTS inventory_stock (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    product_id UUID NOT NULL,
    warehouse_id UUID NOT NULL,
    quantity INT NOT NULL CHECK (quantity >= 0),
    created_by UUID,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- INSERT INTO warehouse (
--     id, name, address, city, state, zip, country, phone, email,
--     is_active, user_id, created_at, created_by
-- ) VALUES
--       ('11111111-1111-1111-1111-111111111111', 'Central Warehouse', '123 Main St', 'Tokyo', 'Tokyo', '100001', 'Japan', '03-1111-2222', 'central@warehouse.com', TRUE, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '2024-10-01 10:00:00', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa'),
--       ('22222222-2222-2222-2222-222222222222', 'East Storage Depot', '55 East Rd', 'Osaka', 'Osaka', '530001', 'Japan', '06-3333-4444', 'east@warehouse.com', TRUE, 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', '2024-07-05 09:00:00', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa'),
--       ('33333333-3333-3333-3333-333333333333', 'West Logistics Hub', '88 West Lane', 'Nagoya', 'Aichi', '450001', 'Japan', '052-555-6666', 'west@warehouse.com', FALSE, 'cccccccc-cccc-cccc-cccc-cccccccccccc', '2023-12-15 14:30:00', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa'),
--       ('44444444-4444-4444-4444-444444444444', 'South Distribution', '44 South St', 'Fukuoka', 'Fukuoka', '810001', 'Japan', '092-777-8888', NULL, TRUE, 'dddddddd-dddd-dddd-dddd-dddddddddddd', '2024-02-20 16:00:00', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa'),
--       ('55555555-5555-5555-5555-555555555555', 'North Storage Facility', '99 North Ave', 'Sapporo', 'Hokkaido', '060001', 'Japan', '011-999-0000', 'north@warehouse.com', TRUE, 'eeeeeeee-eeee-eeee-eeee-eeeeeeeeeeee', '2024-11-01 11:00:00', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa');
--
--
-- INSERT INTO inventory_stock (
--     id, product_id, warehouse_id, quantity, created_by, created_at
-- ) VALUES
--       ('bbbb1111-1111-1111-1111-111111111111', '11111111-2222-3333-4444-555555555511', '11111111-1111-1111-1111-111111111111', 120, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '2024-11-01 09:00:00'),
--
--       ('bbbb2222-2222-2222-2222-222222222222', '11111111-2222-3333-4444-555555555522', '22222222-2222-2222-2222-222222222222', 60, 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', '2024-12-10 10:30:00'),
--
--       ('bbbb3333-3333-3333-3333-333333333333', '11111111-2222-3333-4444-555555555533', '44444444-4444-4444-4444-444444444444', 200, 'cccccccc-cccc-cccc-cccc-cccccccccccc', '2025-01-15 14:00:00');
