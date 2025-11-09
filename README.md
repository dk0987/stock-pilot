

🚀 Stock-Pilot Backend Service


<img width="2564" height="1325" alt="image" src="https://github.com/user-attachments/assets/501c6e3d-b6cd-46af-b68a-faa92a717b56" />


## 📘 Overview

This system manages the complete workflow from **user authentication** to **inventory updates** after **purchases** and **sales**.
It enables users to create and manage **products**, **warehouses**, **suppliers**, **purchase orders**, and **customers**, ensuring all approvals and stock movements are automated and traceable.

The architecture supports **role-based access**, **modular workflows**, and **real-time inventory management**.

---

## ⚙️ System Workflow

### 🔑 1. User Authentication

**Modules:** Authentication Service, User DB, Session Token Service
**Flow:**

1. User logs in using credentials.
2. The Authentication Service validates credentials and issues a session token.
3. The user is assigned a role (Admin, Purchase Admin, Sales Admin, or Viewer).

---

### 📦 2. Master Data Management

#### 🛍️ a. Product Management

* Add product details: name, SKU, price, description, etc.
* Store data in **Product DB**.
* Link each product to warehouse inventory for tracking.

#### 🏭 b. Warehouse Management

* Register new warehouses with location and capacity details.
* Data stored in **Warehouse DB**.
* Each warehouse connects to the inventory system.

#### 🧾 c. Supplier Management

* Create and validate supplier details (name, contact, address).
* Store data in **Supplier DB**.
* Used when creating purchase orders.

#### 👥 d. Customer Management

* Add customer records (name, contact, address).
* Data stored in **Customer DB** for future sales transactions.

---

### 🧾 3. Purchase Order Workflow

#### 🛒 a. Create Purchase Order

* User selects a supplier and adds products with quantities.
* System validates and saves the data in **Purchase Order DB** (status: *Pending Approval*).

#### 🧑‍💼 b. Purchase Approval

* Purchase Approval Process reviews and approves or rejects the order.
* Once approved:

  * PO status changes to *Approved*.
  * Inventory is updated (stock increased).
  * Action logged in **Audit Logging Service**.
  * Notification sent to user.

#### 📈 c. Inventory Update

* Approved POs trigger the **Inventory Service** to update product quantities.
* All actions are recorded for reporting and traceability.

---

### 💰 4. Sales Workflow

#### 🧾 a. Create Sales Request

* User creates a new sales request by selecting a customer and available products.
* Data saved in **Sales Request DB** (status: *Pending Approval*).

#### ✅ b. Sales Approval

* Sales Admin reviews and approves the request.
* Upon approval:

  * Sales status changes to *Approved*.
  * Stock is **deducted** from inventory.
  * Action logged and notifications sent.

---

### 📊 5. Reporting & Analytics

* The **Reporting Module** generates analytics for:

  * Stock shortages
  * Supplier performance
  * Purchase and sales summaries
* Helps management monitor operations and optimize resources.

---

## 🧩 Key Entities

| Entity             | Description                            |
| ------------------ | -------------------------------------- |
| **User**           | System users with defined roles        |
| **Product**        | Items available for purchase or sale   |
| **Warehouse**      | Physical storage locations             |
| **Supplier**       | Vendor providing products              |
| **Purchase Order** | Document for product acquisition       |
| **Customer**       | Buyer receiving products               |
| **Sales Request**  | Customer sales order awaiting approval |
| **Inventory**      | Real-time stock tracking system        |

---

## 🔐 Roles & Permissions

| Role               | Permissions                        |
| ------------------ | ---------------------------------- |
| **Admin**          | Full access to all modules         |
| **Purchase Admin** | Manage suppliers & purchase orders |
| **Sales Admin**    | Manage customers & sales requests  |
| **Viewer**         | Read-only access to reports        |

---

## 🧠 Core Services

| Service                    | Responsibility                        |
| -------------------------- | ------------------------------------- |
| **Authentication Service** | Manages user login and token issuance |
| **Approval Services**      | Handles purchase and sales approvals  |
| **Inventory Service**      | Updates and tracks stock quantities   |
| **Notification Service**   | Sends system alerts and updates       |
| **Audit Logging**          | Records all critical system actions   |

---

## 💡 Key Features

* Modular, scalable architecture
* Automated approval workflows
* Real-time inventory synchronization
* Complete audit and logging system
* Role-based access control
* Clear master data relationships (Product, Supplier, Customer, Warehouse)

