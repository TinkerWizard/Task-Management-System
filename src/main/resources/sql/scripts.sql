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
    assigned_date VARCHAR(50) NOT NULL,
    due_date VARCHAR(50) NOT NULL,
    task_status VARCHAR(50) NOT NULL
);



-- Insert statements for the users table

INSERT INTO users (name, user_id, password, email) VALUES
('Alice Smith', 'ADMIN_1', 'pass123', 'alice.smith@example.com'),
('Bob Johnson', 'ADMIN_2', 'adminPass!56', 'bob.johnson@example.com'),
('Charlie Davis', 'ADMIN_3', 'charlieD@v1s', 'charlie.davis@example.com'),
('Dana Lee', 'ADMIN_4', 'DanaPass!789', 'dana.lee@example.com'),
('Evan Moore', 'ADMIN_5', 'evanM00re', 'evan.moore@example.com'),

('Frank Harris', 'NOR_1', 'pass123', 'frank.harris@example.com'),
('Grace Kim', 'NOR_2', 'graceK!m987', 'grace.kim@example.com'),
('Hank Wright', 'NOR_3', 'hankWright!', 'hank.wright@example.com'),
('Ivy Scott', 'NOR_4', 'ivySc0ttPass', 'ivy.scott@example.com'),
('Jake Green', 'NOR_5', 'JakeGreeN123', 'jake.green@example.com'),

('Karen Thompson', 'NEE_1', 'pass123', 'karen.thompson@example.com'),
('Leo Martin', 'NEE_2', 'leoM@rt!n', 'leo.martin@example.com'),
('Mia Clark', 'NEE_3', 'MiaClark!', 'mia.clark@example.com'),
('Noah Lewis', 'NEE_4', 'noahL3w!s', 'noah.lewis@example.com'),
('Olivia Young', 'NEE_5', 'OliviaY0ung', 'olivia.young@example.com'),
('Paul Walker', 'NEE_6', 'paulW@lk3r', 'paul.walker@example.com'),
('Quincy Brown', 'NEE_7', 'QuincyBr0wn', 'quincy.brown@example.com'),
('Rachel Wilson', 'NEE_8', 'RachelW!ls0n', 'rachel.wilson@example.com'),
('Sam Baker', 'NEE_9', 'samB@k3r', 'sam.baker@example.com'),
('Tina Hall', 'NEE_10', 'tinaH@ll', 'tina.hall@example.com');

-- INSERTING INTO ROLES TABLE
INSERT INTO roles (user_id, role) VALUES
('ADMIN_1', 'ADMIN'),
('ADMIN_2', 'ADMIN'),
('ADMIN_3', 'ADMIN'),
('ADMIN_4', 'ADMIN'),
('ADMIN_5', 'ADMIN'),

('NOR_1', 'ASSIGNOR'),
('NOR_2', 'ASSIGNOR'),
('NOR_3', 'ASSIGNOR'),
('NOR_4', 'ASSIGNOR'),
('NOR_5', 'ASSIGNOR'),

('NEE_1', 'ASSIGNEE'),
('NEE_2', 'ASSIGNEE'),
('NEE_3', 'ASSIGNEE'),
('NEE_4', 'ASSIGNEE'),
('NEE_5', 'ASSIGNEE'),
('NEE_6', 'ASSIGNEE'),
('NEE_7', 'ASSIGNEE'),
('NEE_8', 'ASSIGNEE'),
('NEE_9', 'ASSIGNEE'),
('NEE_10', 'ASSIGNEE');
-- Insert tasks
INSERT INTO tasks (task_title, task_note, assignor_id, assignee_id, assigned_date, due_date, task_status) VALUES
('Complete Report', 'Finish the quarterly report', 'NOR_1', 'NEE_1', '2024-05-01', '2024-05-07', 'In Progress'),
('Update Website', 'Refresh homepage content', 'NOR_2', 'NEE_2', '2024-05-02', '2024-05-10', 'Pending'),
('Team Meeting', 'Prepare slides for meeting', 'NOR_3', 'NEE_3', '2024-05-03', '2024-05-05', 'Completed'),
('Client Feedback', 'Review and compile client feedback', 'NOR_4', 'NEE_4', '2024-05-04', '2024-05-06', 'Pending'),
('Code Review', 'Review the latest code commits', 'NOR_5', 'NEE_5', '2024-05-05', '2024-05-09', 'In Progress'),
('Market Research', 'Analyze market trends', 'NOR_1', 'NEE_6', '2024-05-06', '2024-05-11', 'Pending'),
('Product Launch', 'Plan product launch event', 'NOR_2', 'NEE_7', '2024-05-07', '2024-05-12', 'Completed'),
('Design Update', 'Update the design mockups', 'NOR_3', 'NEE_8', '2024-05-08', '2024-05-13', 'In Progress'),
('Server Maintenance', 'Schedule server downtime', 'NOR_4', 'NEE_9', '2024-05-09', '2024-05-14', 'Pending'),
('Financial Audit', 'Conduct internal financial audit', 'NOR_5', 'NEE_10', '2024-05-10', '2024-05-15', 'In Progress'),
('Bug Fixes', 'Fix bugs reported by QA', 'NOR_1', 'NEE_1', '2024-05-11', '2024-05-16', 'Pending'),
('User Testing', 'Conduct user testing sessions', 'NOR_2', 'NEE_2', '2024-05-12', '2024-05-17', 'Completed'),
('Content Creation', 'Create new blog posts', 'NOR_3', 'NEE_3', '2024-05-13', '2024-05-18', 'In Progress'),
('Performance Review', 'Prepare employee performance reviews', 'NOR_4', 'NEE_4', '2024-05-14', '2024-05-19', 'Pending'),
('Security Audit', 'Perform security audit', 'NOR_5', 'NEE_5', '2024-05-15', '2024-05-20', 'Completed'),
('Customer Survey', 'Design and send customer survey', 'NOR_1', 'NEE_6', '2024-05-16', '2024-05-21', 'In Progress'),
('Database Migration', 'Plan database migration', 'NOR_2', 'NEE_7', '2024-05-17', '2024-05-22', 'Pending'),
('Training Session', 'Organize staff training session', 'NOR_3', 'NEE_8', '2024-05-18', '2024-05-23', 'Completed'),
('Software Deployment', 'Deploy new software update', 'NOR_4', 'NEE_9', '2024-05-19', '2024-05-24', 'In Progress'),
('Marketing Campaign', 'Launch new marketing campaign', 'NOR_5', 'NEE_10', '2024-05-20', '2024-05-25', 'Pending'),
('Data Analysis', 'Analyze sales data', 'NOR_1', 'NEE_1', '2024-05-21', '2024-05-26', 'Completed'),
('Budget Planning', 'Prepare budget for next quarter', 'NOR_2', 'NEE_2', '2024-05-22', '2024-05-27', 'In Progress'),
('Event Coordination', 'Coordinate company event', 'NOR_3', 'NEE_3', '2024-05-23', '2024-05-28', 'Pending'),
('SEO Optimization', 'Optimize website for SEO', 'NOR_4', 'NEE_4', '2024-05-24', '2024-05-29', 'Completed'),
('Graphic Design', 'Create graphics for social media', 'NOR_5', 'NEE_5', '2024-05-25', '2024-05-30', 'In Progress'),
('User Onboarding', 'Develop onboarding materials', 'NOR_1', 'NEE_6', '2024-05-26', '2024-05-31', 'Pending'),
('Compliance Check', 'Check compliance with regulations', 'NOR_2', 'NEE_7', '2024-05-27', '2024-06-01', 'Completed'),
('System Upgrade', 'Upgrade internal systems', 'NOR_3', 'NEE_8', '2024-05-28', '2024-06-02', 'In Progress'),
('Legal Review', 'Review legal documents', 'NOR_4', 'NEE_9', '2024-05-29', '2024-06-03', 'Pending'),
('Network Setup', 'Set up new network', 'NOR_5', 'NEE_10', '2024-05-30', '2024-06-04', 'Completed');
