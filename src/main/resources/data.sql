INSERT INTO gcm.roles (id, name)
VALUES (1, 'admin');
INSERT INTO gcm.roles (id, name)
VALUES (2, 'contestmanager');
INSERT INTO gcm.roles (id, name)
VALUES (3, 'player');

INSERT INTO gcm.permissions (id, perm)
VALUES (1, '*:*');
INSERT INTO gcm.permissions (id, perm)
VALUES (2, 'contest:create');
INSERT INTO gcm.permissions (id, perm)
VALUES (3, 'contest:view');

INSERT INTO gcm.users
VALUES (1, 'admin', null, null, 'secret');
INSERT INTO gcm.users
VALUES (2, 'orga1', null, null, 'secret');
INSERT INTO gcm.users
VALUES (3, 'guest', null, null, 'guest');


INSERT INTO gcm.role_permissions (role_id, permission_id)
VALUES (1, 1);
INSERT INTO gcm.role_permissions (role_id, permission_id)
VALUES (2, 2);
INSERT INTO gcm.role_permissions (role_id, permission_id)
VALUES (2, 3);
INSERT INTO gcm.role_permissions (role_id, permission_id)
VALUES (3, 3);

INSERT INTO gcm.user_roles (role_id, user_id)
VALUES (1, 1);
INSERT INTO gcm.user_roles (role_id, user_id)
VALUES (2, 2);
INSERT INTO gcm.user_roles (role_id, user_id)
VALUES (3, 3);