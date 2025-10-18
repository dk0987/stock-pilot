-- Define variables for your specific role UUIDs (Confirmed from your input)
CREATE TABLE IF NOT EXISTS roles (
    role_id UUID PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS CustomUser (
    -- User ID (Primary Key)
    id UUID PRIMARY KEY,

    -- User Details (Required & Unique)
    username VARCHAR(100) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(100) NOT NULL,

    -- User Details (Optional)
    last_name VARCHAR(100),
    phone_number VARCHAR(20),

    -- Status and Dates
    is_active BOOLEAN NOT NULL,
    created_at DATE,
    updated_at DATE,

    -- Foreign Key to Role Table
    role_id UUID,

    CONSTRAINT fk_user_role
    FOREIGN KEY (role_id)
    REFERENCES roles (role_id)
    ON DELETE RESTRICT -- Prevents deleting a role if users are assigned to it
);

-- INSERT INTO roles (role_id, name, description) VALUES
-- ('a0e9b8c7-d6f5-e4d3-c2b1-100000000001', 'ADMIN', 'System Administrator'),
-- ('a0e9b8c7-d6f5-e4d3-c2b1-100000000002', 'INVENTORY_MANAGER', 'Manages Stock'),
-- ('a0e9b8c7-d6f5-e4d3-c2b1-100000000003', 'SALES_MANAGER', 'Manages Sales'),
-- ('a0e9b8c7-d6f5-e4d3-c2b1-100000000004', 'VIEWER', 'View-only access'),
-- ('a0e9b8c7-d6f5-e4d3-c2b1-100000000005', 'APPROVER', 'Approves requests');

-- Insert 100 users with varied test data
INSERT INTO CustomUser (
    id, username, email, password, first_name, last_name, phone_number, is_active, created_at, updated_at, role_id
) VALUES
-- Admins (5 Users) - All Active, Full Data
-- ('a0e9b8c7-d6f5-e4d3-c2b1-100000000008', 'sys_alice', 'alice.adams@corp.com', 'password', 'Alice', 'Adams', '555-0101', TRUE, '2022-01-10', '2024-09-01', 'a0e9b8c7-d6f5-e4d3-c2b1-100000000001');
-- (UUID(), 'bbanner', 'bruce.b@corp.com', @hashed_password, 'Bruce', 'Banner', '555-0102', TRUE, '2023-05-15', '2023-05-15', @admin_id),
-- (UUID(), 'cclark', 'clara.c@corp.com', @hashed_password, 'Clara', 'Clark', '555-0103', TRUE, '2023-08-20', '2024-10-10', @admin_id),
-- (UUID(), 'davis', 'david.d@corp.com', @hashed_password, 'David', 'Davis', '555-0104', TRUE, '2021-11-01', '2021-11-01', @admin_id),
-- (UUID(), 'e_admin', 'eva.e@corp.com', @hashed_password, 'Eva', 'Edwards', '555-0105', TRUE, '2024-07-07', '2024-07-07', @admin_id),
--
-- -- Inventory Managers (10 Users) - Mix of Active/Inactive, Missing Phone
-- (UUID(), 'frank_inv', 'frank.f@inv.com', @hashed_password, 'Frank', 'Farmer', '555-0201', TRUE, '2023-11-25', CURDATE(), @inventory_manager_id),
-- (UUID(), 'grace_g', 'grace.g@inv.com', @hashed_password, 'Grace', 'Green', NULL, TRUE, '2024-01-12', '2024-01-12', @inventory_manager_id), -- Missing Phone
-- (UUID(), 'h_hill', 'henry.h@inv.com', @hashed_password, 'Henry', 'Hill', '555-0203', TRUE, '2022-10-01', '2023-03-01', @inventory_manager_id),
-- (UUID(), 'ivy_irwin', 'ivy.i@inv.com', @hashed_password, 'Ivy', 'Irwin', '555-0204', FALSE, '2024-02-14', '2024-02-14', @inventory_manager_id), -- Inactive
-- (UUID(), 'j_jones', 'jack.j@inv.com', @hashed_password, 'Jack', 'Jones', '555-0205', TRUE, '2021-08-01', '2024-06-01', @inventory_manager_id),
-- (UUID(), 'kking', 'kara.k@inv.com', @hashed_password, 'Kara', 'King', '555-0206', TRUE, '2023-09-01', CURDATE(), @inventory_manager_id),
-- (UUID(), 'leo_lane', 'leo.l@inv.com', @hashed_password, 'Leo', 'Lane', '555-0207', TRUE, '2023-12-05', '2023-12-05', @inventory_manager_id),
-- (UUID(), 'mmiller', 'mia.m@inv.com', @hashed_password, 'Mia', 'Miller', '555-0208', TRUE, '2024-04-18', '2024-04-18', @inventory_manager_id),
-- (UUID(), 'nelson', 'noah.n@inv.com', @hashed_password, 'Noah', 'Nelson', '555-0209', TRUE, '2023-06-20', '2024-01-20', @inventory_manager_id),
-- (UUID(), 'olivia_o', 'olivia.o@inv.com', @hashed_password, 'Olivia', 'Owen', '555-0210', FALSE, '2022-03-03', '2023-03-03', @inventory_manager_id), -- Inactive
--
-- -- Sales Managers (10 Users) - Mix of Missing Last Name, Old Accounts
-- (UUID(), 'p_sales', 'peter.p@sales.com', @hashed_password, 'Peter', 'Parker', '555-0301', TRUE, '2023-07-01', CURDATE(), @sales_manager_id),
-- (UUID(), 'q_mgr', 'quinn.q@sales.com', @hashed_password, 'Quinn', 'Queen', '555-0302', TRUE, '2024-05-10', '2024-05-10', @sales_manager_id),
-- (UUID(), 'rross', 'rachel.r@sales.com', @hashed_password, 'Rachel', 'Ross', NULL, TRUE, '2022-04-04', '2024-08-04', @sales_manager_id), -- Missing Phone
-- (UUID(), 'sam_s', 'sam.s@sales.com', @hashed_password, 'Sam', NULL, '555-0304', TRUE, '2023-01-01', '2023-01-01', @sales_manager_id), -- Missing Last Name
-- (UUID(), 'tt_mgr', 'tina.t@sales.com', @hashed_password, 'Tina', 'Turner', '555-0305', TRUE, '2024-08-01', CURDATE(), @sales_manager_id),
-- (UUID(), 'uma', 'uma.u@sales.com', @hashed_password, 'Uma', 'Underwood', '555-0306', FALSE, '2023-03-15', '2023-03-15', @sales_manager_id), -- Inactive
-- (UUID(), 'v_vance', 'victor.v@sales.com', @hashed_password, 'Victor', 'Vance', '555-0307', TRUE, '2022-07-22', '2024-01-22', @sales_manager_id),
-- (UUID(), 'wwest', 'wendy.w@sales.com', @hashed_password, 'Wendy', 'West', '555-0308', TRUE, '2024-06-15', '2024-06-15', @sales_manager_id),
-- (UUID(), 'xavier', 'xavier.x@sales.com', @hashed_password, 'Xavier', NULL, '555-0309', TRUE, '2023-04-01', '2024-05-01', @sales_manager_id), -- Missing Last Name
-- (UUID(), 'yara', 'yara.y@sales.com', @hashed_password, 'Yara', 'Young', '555-0310', TRUE, '2022-12-12', '2022-12-12', @sales_manager_id),
--
-- -- Approvers (15 Users) - Mixed Dates, Some Inactive, Some Missing Data
-- (UUID(), 'zane_app', 'zane.z@appr.com', @hashed_password, 'Zane', 'Zimmerman', '555-0401', TRUE, '2024-01-01', CURDATE(), @approver_id),
-- (UUID(), 'aapple', 'aaron.a@appr.com', @hashed_password, 'Aaron', 'Apple', '555-0402', TRUE, '2023-11-11', '2023-11-11', @approver_id),
-- (UUID(), 'bball', 'bella.b@appr.com', @hashed_password, 'Bella', NULL, '555-0403', TRUE, '2024-03-01', '2024-03-01', @approver_id), -- Missing Last Name
-- (UUID(), 'c_castle', 'carl.c@appr.com', @hashed_password, 'Carl', 'Castle', '555-0404', TRUE, '2022-09-01', '2024-01-01', @approver_id),
-- (UUID(), 'd_duke', 'diana.d@appr.com', @hashed_password, 'Diana', 'Duke', '555-0405', FALSE, '2024-04-04', '2024-04-04', @approver_id), -- Inactive
-- (UUID(), 'ethan', 'ethan.e@appr.com', @hashed_password, 'Ethan', 'Evans', '555-0406', TRUE, '2023-02-01', '2023-02-01', @approver_id),
-- (UUID(), 'fiona', 'fiona.f@appr.com', @hashed_password, 'Fiona', 'Fisher', '555-0407', TRUE, '2024-08-10', CURDATE(), @approver_id),
-- (UUID(), 'gglass', 'george.g@appr.com', @hashed_password, 'George', 'Glass', '555-0408', TRUE, '2022-06-06', '2023-06-06', @approver_id),
-- (UUID(), 'h_hale', 'holly.h@appr.com', @hashed_password, 'Holly', 'Hale', NULL, TRUE, '2024-09-01', '2024-09-01', @approver_id), -- Missing Phone
-- (UUID(), 'ian_i', 'ian.i@appr.com', @hashed_password, 'Ian', 'Ingram', '555-0410', TRUE, '2023-10-01', '2024-03-01', @approver_id),
-- (UUID(), 'jane_j', 'jane.j@appr.com', @hashed_password, 'Jane', 'Jetson', '555-0411', FALSE, '2023-01-01', '2023-01-01', @approver_id), -- Inactive
-- (UUID(), 'kevin', 'kevin.k@appr.com', @hashed_password, 'Kevin', 'Kline', '555-0412', TRUE, '2024-07-01', '2024-07-01', @approver_id),
-- (UUID(), 'llombard', 'laura.l@appr.com', @hashed_password, 'Laura', 'Lombard', '555-0413', TRUE, '2022-11-20', '2024-05-20', @approver_id),
-- (UUID(), 'mark_m', 'mark.m@appr.com', @hashed_password, 'Mark', NULL, '555-0414', TRUE, '2023-03-30', '2023-03-30', @approver_id), -- Missing Last Name
-- (UUID(), 'n_norman', 'nora.n@appr.com', @hashed_password, 'Nora', 'Norman', '555-0415', TRUE, '2024-02-29', CURDATE(), @approver_id),
--
-- -- Viewers (60 Users) - Mostly Active, Varied Last Names and Dates
-- (UUID(), 'viewer_o', 'oscar.o@view.com', @hashed_password, 'Oscar', 'Olsen', '555-0501', TRUE, '2023-05-01', '2024-06-01', @viewer_id),
-- (UUID(), 'p_prince', 'pam.p@view.com', @hashed_password, 'Pam', 'Prince', '555-0502', TRUE, '2024-01-01', '2024-01-01', @viewer_id),
-- (UUID(), 'r_rhodes', 'randy.r@view.com', @hashed_password, 'Randy', 'Rhodes', '555-0503', FALSE, '2022-09-09', '2022-09-09', @viewer_id), -- Inactive
-- (UUID(), 's_stone', 'sara.s@view.com', @hashed_password, 'Sara', 'Stone', '555-0504', TRUE, '2023-10-10', CURDATE(), @viewer_id),
-- (UUID(), 't_thompson', 'tom.t@view.com', @hashed_password, 'Tom', 'Thompson', '555-0505', TRUE, '2024-02-01', '2024-02-01', @viewer_id),
-- (UUID(), 'user_06', 'user.06@view.com', @hashed_password, 'William', 'White', '555-0506', TRUE, '2023-04-10', '2023-04-10', @viewer_id),
-- (UUID(), 'user_07', 'user.07@view.com', @hashed_password, 'Andrea', 'Anderson', '555-0507', TRUE, '2024-05-01', '2024-05-01', @viewer_id),
-- (UUID(), 'user_08', 'user.08@view.com', @hashed_password, 'Brad', 'Black', '555-0508', TRUE, '2022-12-01', '2024-08-01', @viewer_id),
-- (UUID(), 'user_09', 'user.09@view.com', @hashed_password, 'Cathy', 'Cole', '555-0509', TRUE, '2023-07-07', '2023-07-07', @viewer_id),
-- (UUID(), 'user_10', 'user.10@view.com', @hashed_password, 'Derek', 'Day', '555-0510', TRUE, '2024-03-20', CURDATE(), @viewer_id),
-- (UUID(), 'user_11', 'user.11@view.com', @hashed_password, 'Erica', 'East', '555-0511', TRUE, '2022-11-11', '2022-11-11', @viewer_id),
-- (UUID(), 'user_12', 'user.12@view.com', @hashed_password, 'Fred', 'Finch', NULL, TRUE, '2024-01-20', '2024-01-20', @viewer_id), -- Missing Phone
-- (UUID(), 'user_13', 'user.13@view.com', @hashed_password, 'Gail', 'Gray', '555-0513', TRUE, '2023-08-05', '2024-02-05', @viewer_id),
-- (UUID(), 'user_14', 'user.14@view.com', @hashed_password, 'Hugo', 'Hart', '555-0514', TRUE, '2024-06-10', '2024-06-10', @viewer_id),
-- (UUID(), 'user_15', 'user.15@view.com', @hashed_password, 'Irene', 'Ivy', '555-0515', TRUE, '2023-09-09', CURDATE(), @viewer_id),
-- (UUID(), 'user_16', 'user.16@view.com', @hashed_password, 'Jesse', 'Judd', '555-0516', TRUE, '2022-10-20', '2023-10-20', @viewer_id),
-- (UUID(), 'user_17', 'user.17@view.com', @hashed_password, 'Kate', 'Kyle', '555-0517', TRUE, '2024-04-15', '2024-04-15', @viewer_id),
-- (UUID(), 'user_18', 'user.18@view.com', @hashed_password, 'Larry', 'Lee', '555-0518', TRUE, '2023-02-28', '2023-02-28', @viewer_id),
-- (UUID(), 'user_19', 'user.19@view.com', @hashed_password, 'Mary', 'May', '555-0519', TRUE, '2024-07-25', CURDATE(), @viewer_id),
-- (UUID(), 'user_20', 'user.20@view.com', @hashed_password, 'Nick', 'Neal', '555-0520', TRUE, '2022-03-01', '2024-01-01', @viewer_id),
-- (UUID(), 'user_21', 'user.21@view.com', @hashed_password, 'Owen', NULL, '555-0521', TRUE, '2023-06-15', '2023-06-15', @viewer_id), -- Missing Last Name
-- (UUID(), 'user_22', 'user.22@view.com', @hashed_password, 'Pat', 'Pike', '555-0522', TRUE, '2024-08-05', '2024-08-05', @viewer_id),
-- (UUID(), 'user_23', 'user.23@view.com', @hashed_password, 'Quentin', 'Quick', '555-0523', TRUE, '2023-03-03', '2024-04-03', @viewer_id),
-- (UUID(), 'user_24', 'user.24@view.com', @hashed_password, 'Rita', 'Rich', '555-0524', TRUE, '2024-01-15', '2024-01-15', @viewer_id),
-- (UUID(), 'user_25', 'user.25@view.com', @hashed_password, 'Steve', 'Star', '555-0525', TRUE, '2022-12-25', CURDATE(), @viewer_id),
-- (UUID(), 'user_26', 'user.26@view.com', @hashed_password, 'Tracy', 'Tree', '555-0526', TRUE, '2023-09-01', '2023-09-01', @viewer_id),
-- (UUID(), 'user_27', 'user.27@view.com', @hashed_password, 'Ulric', 'Up', '555-0527', TRUE, '2024-05-15', '2024-05-15', @viewer_id),
-- (UUID(), 'user_28', 'user.28@view.com', @hashed_password, 'Vivian', 'Volt', '555-0528', TRUE, '2023-04-20', '2024-09-20', @viewer_id),
-- (UUID(), 'user_29', 'user.29@view.com', @hashed_password, 'Walter', 'Wing', '555-0529', TRUE, '2022-05-01', '2023-05-01', @viewer_id),
-- (UUID(), 'user_30', 'user.30@view.com', @hashed_password, 'Xena', 'Xer', '555-0530', TRUE, '2024-07-01', '2024-07-01', @viewer_id),
-- (UUID(), 'user_31', 'user.31@view.com', @hashed_password, 'Yancy', 'Yates', '555-0531', TRUE, '2023-11-05', CURDATE(), @viewer_id),
-- (UUID(), 'user_32', 'user.32@view.com', @hashed_password, 'Zoe', 'Zell', '555-0532', TRUE, '2024-02-10', '2024-02-10', @viewer_id),
-- (UUID(), 'user_33', 'user.33@view.com', @hashed_password, 'Alex', 'Able', '555-0533', FALSE, '2023-01-01', '2023-01-01', @viewer_id), -- Inactive
-- (UUID(), 'user_34', 'user.34@view.com', @hashed_password, 'Beth', 'Best', '555-0534', TRUE, '2024-04-01', '2024-04-01', @viewer_id),
-- (UUID(), 'user_35', 'user.35@view.com', @hashed_password, 'Cody', 'Call', '555-0535', TRUE, '2022-08-08', '2024-03-08', @viewer_id),
-- (UUID(), 'user_36', 'user.36@view.com', @hashed_password, 'Dawn', 'Drew', '555-0536', TRUE, '2023-12-10', '2023-12-10', @viewer_id),
-- (UUID(), 'user_37', 'user.37@view.com', @hashed_password, 'Erin', 'Eric', '555-0537', TRUE, '2024-06-25', CURDATE(), @viewer_id),
-- (UUID(), 'user_38', 'user.38@view.com', @hashed_password, 'Finn', 'Ford', '555-0538', TRUE, '2023-03-17', '2023-03-17', @viewer_id),
-- (UUID(), 'user_39', 'user.39@view.com', @hashed_password, 'Gina', 'Gale', '555-0539', TRUE, '2022-10-10', '2024-02-10', @viewer_id),
-- (UUID(), 'user_40', 'user.40@view.com', @hashed_password, 'Hank', 'Hume', '555-0540', TRUE, '2024-09-05', '2024-09-05', @viewer_id),
-- (UUID(), 'user_41', 'user.41@view.com', @hashed_password, 'Iris', 'Isle', '555-0541', TRUE, '2023-07-28', CURDATE(), @viewer_id),
-- (UUID(), 'user_42', 'user.42@view.com', @hashed_password, 'Jeff', 'Joss', '555-0542', TRUE, '2022-01-01', '2024-01-01', @viewer_id),
-- (UUID(), 'user_43', 'user.43@view.com', @hashed_password, 'Kim', 'Kite', '555-0543', TRUE, '2024-03-10', '2024-03-10', @viewer_id),
-- (UUID(), 'user_44', 'user.44@view.com', @hashed_password, 'Lou', 'Link', '555-0544', TRUE, '2023-05-10', '2024-07-10', @viewer_id),
-- (UUID(), 'user_45', 'user.45@view.com', @hashed_password, 'Max', 'Moss', '555-0545', FALSE, '2024-06-01', '2024-06-01', @viewer_id), -- Inactive
-- (UUID(), 'user_46', 'user.46@view.com', @hashed_password, 'Nina', 'Noy', '555-0546', TRUE, '2022-11-01', CURDATE(), @viewer_id),
-- (UUID(), 'user_47', 'user.47@view.com', @hashed_password, 'Opal', 'Oak', '555-0547', TRUE, '2023-08-22', '2023-08-22', @viewer_id),
-- (UUID(), 'user_48', 'user.48@view.com', @hashed_password, 'Paul', 'Parr', '555-0548', TRUE, '2024-01-05', '2024-01-05', @viewer_id),
-- (UUID(), 'user_49', 'user.49@view.com', @hashed_password, 'Quincy', 'Quay', '555-0549', TRUE, '2023-04-01', '2024-05-01', @viewer_id),
-- (UUID(), 'user_50', 'user.50@view.com', @hashed_password, 'Rose', 'Reed', '555-0550', TRUE, '2024-09-20', '2024-09-20', @viewer_id),
-- (UUID(), 'user_51', 'user.51@view.com', @hashed_password, 'Sammy', 'Shine', '555-0551', TRUE, '2023-01-10', CURDATE(), @viewer_id),
-- (UUID(), 'user_52', 'user.52@view.com', @hashed_password, 'Troy', 'Tate', '555-0552', TRUE, '2024-03-25', '2024-03-25', @viewer_id),
-- (UUID(), 'user_53', 'user.53@view.com', @hashed_password, 'Ursula', 'Unit', '555-0553', TRUE, '2022-07-07', '2024-06-07', @viewer_id);