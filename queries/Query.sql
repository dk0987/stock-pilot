INSERT INTO authority (name, description, is_active, created_at, updated_at, created_by, updated_by)
VALUES
    ('system', 'Full system access', true, NOW(), NOW(), NULL, NULL),
    ('inventory_manager', 'Manages inventory', true, NOW(), NOW(), NULL, NULL),
    ('sales_manager', 'Manages sales operations', true, NOW(), NOW(), NULL, NULL),
    ('purchase_manager', 'Manages purchase operations', true, NOW(), NOW(), NULL, NULL),
    ('employee', 'Regular employee access', true, NOW(), NOW(), NULL, NULL);

INSERT INTO users (id, user_name, email, password, first_name, last_name, phone_number, last_login,
                   is_active, created_at, updated_at, created_by, updated_by)
VALUES
    (1, 'system_user', 'system@example.com', 'password1', 'System', 'User', '0000000001', NOW(),
     true, NOW(), NOW(), NULL, NULL);

INSERT INTO users_authority (users_id, authority_id)
VALUES (1, 1);

INSERT INTO users (id, user_name, email, password, first_name, last_name, phone_number, last_login,
                   is_active, created_at, updated_at, created_by, updated_by)
VALUES
    (2, 'inventory_user', 'inventory@example.com', 'password2', 'Inventory', 'Manager', '0000000002', NOW(),
     true, NOW(), NOW(), 1, NULL),

    (3, 'sales_user', 'sales@example.com', 'sales@example.com', 'Sales', 'Manager', '0000000003', NOW(),
     true, NOW(), NOW(), 1, NULL),

    (4, 'purchase_user', 'purchase@example.com', 'password4', 'Purchase', 'Manager', '0000000004', NOW(),
     true, NOW(), NOW(), 1, NULL),

    (5, 'employee_user', 'employee@example.com', 'password5', 'Employee', 'User', '0000000005', NOW(),
     true, NOW(), NOW(), 1, NULL);


INSERT INTO users_authority (users_id, authority_id)
VALUES
    (2, 2),  -- inventory_user → inventory_manager
    (3, 3),  -- sales_user → sales_manager
    (4, 4),  -- purchase_user → purchase_manager
    (5, 5);  -- employee_user → employee
