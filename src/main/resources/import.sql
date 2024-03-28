INSERT INTO users (username, email, password) VALUES ('admin', 'admin@email.com', '$2a$10$M8HYtV57BybNBUXE/HJkOeSE0LuaJaTtVgIWnJD8W.laBKiiIpf.2');
INSERT INTO users (username, email, password) VALUES ('César', 'cesar@email.com', '$2a$10$M8HYtV57BybNBUXE/HJkOeSE0LuaJaTtVgIWnJD8W.laBKiiIpf.2');
INSERT INTO users (username, email, password) VALUES ('Manuel', 'manuel@email.com', '$2a$10$M8HYtV57BybNBUXE/HJkOeSE0LuaJaTtVgIWnJD8W.laBKiiIpf.2');
INSERT INTO users (username, email, password) VALUES ('Joe', 'joe@email.com', '$2a$10$M8HYtV57BybNBUXE/HJkOeSE0LuaJaTtVgIWnJD8W.laBKiiIpf.2');
INSERT INTO users (username, email, password) VALUES ('Jane', 'jane@email.com', '$2a$10$M8HYtV57BybNBUXE/HJkOeSE0LuaJaTtVgIWnJD8W.laBKiiIpf.2');

INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_USER');

INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (1, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (3, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (4, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (5, 2);