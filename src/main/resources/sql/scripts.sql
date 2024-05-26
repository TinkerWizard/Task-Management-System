#CREATING THE DATABASE
DROP DATABASE IF EXISTS tms;
CREATE DATABASE tms;
USE tms;

-- CREATEING TABLES
CREATE TABLE users (
    name VARCHAR(50) NOT NULL,
    user_id VARCHAR(50) PRIMARY KEY,
    password VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL
);

CREATE TABLE roles (
    user_id VARCHAR(50),
    role VARCHAR(50) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE tasks (
    task_id INT AUTO_INCREMENT PRIMARY KEY,
    task_title VARCHAR(50) NOT NULL,
    task_note VARCHAR(50) NOT NULL,
    assignor_id VARCHAR(50) NOT NULL,
    assignee_id VARCHAR(50) NOT NULL,
    assigned_on VARCHAR(50) NOT NULL,
    due_date VARCHAR(50) NOT NULL,
    task_status VARCHAR(50) NOT NULL
);



-- Insert statements for the users table

-- INSERT INTO users (name, user_id, password, email) VALUES
-- ('Alice Smith', 'ADMIN_1', 'password123', 'alice.smith@example.com'),
-- ('Bob Johnson', 'ADMIN_2', 'adminPass!56', 'bob.johnson@example.com'),
-- ('Charlie Davis', 'ADMIN_3', 'charlieD@v1s', 'charlie.davis@example.com'),
-- ('Dana Lee', 'ADMIN_4', 'DanaPass!789', 'dana.lee@example.com'),
-- ('Evan Moore', 'ADMIN_5', 'evanM00re', 'evan.moore@example.com'),

-- ('Frank Harris', 'NOR_1', 'frankH@rr1s', 'frank.harris@example.com'),
-- ('Grace Kim', 'NOR_2', 'graceK!m987', 'grace.kim@example.com'),
-- ('Hank Wright', 'NOR_3', 'hankWright!', 'hank.wright@example.com'),
-- ('Ivy Scott', 'NOR_4', 'ivySc0ttPass', 'ivy.scott@example.com'),
-- ('Jake Green', 'NOR_5', 'JakeGreeN123', 'jake.green@example.com'),

-- ('Karen Thompson', 'NEE_1', 'KarenT2022', 'karen.thompson@example.com'),
-- ('Leo Martin', 'NEE_2', 'leoM@rt!n', 'leo.martin@example.com'),
-- ('Mia Clark', 'NEE_3', 'MiaClark!', 'mia.clark@example.com'),
-- ('Noah Lewis', 'NEE_4', 'noahL3w!s', 'noah.lewis@example.com'),
-- ('Olivia Young', 'NEE_5', 'OliviaY0ung', 'olivia.young@example.com'),
-- ('Paul Walker', 'NEE_6', 'paulW@lk3r', 'paul.walker@example.com'),
-- ('Quincy Brown', 'NEE_7', 'QuincyBr0wn', 'quincy.brown@example.com'),
-- ('Rachel Wilson', 'NEE_8', 'RachelW!ls0n', 'rachel.wilson@example.com'),
-- ('Sam Baker', 'NEE_9', 'samB@k3r', 'sam.baker@example.com'),
-- ('Tina Hall', 'NEE_10', 'tinaH@ll', 'tina.hall@example.com');
