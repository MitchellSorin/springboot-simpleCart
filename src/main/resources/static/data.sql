INSERT INTO user_ (id, name, password, salt)
VALUES ('hfN5pW5GXcXZecPJNvYTDdri7d1RneqN', 'seller', 'a249b828e0c0a5a0aa67e942d2bd1b31', 'e2cWtqEpRO6H1C48')
     , ('HOVQLXMiXTgeddezi7hE8HtUNFRMe4sc', 'superadmin', 'fc049bd9299c16f47e8a9c5941198c7a', 'nsn1xcZIygqx6z4K')
;

INSERT INTO role_ (id, description, name)
VALUES ('J2V0sCo4Qw3rXjI32UhKCvKwsnVnV5VF', '系统管理', '管理员')
     , ('V9VcEfOesNJt7AVPr9BJwvenEl3AGwTA', '维护商品', '商家')
;

INSERT INTO permission_ (id, name, description, url)
VALUES ('0lmwQA7fhqEGStwdNnpc5lktpG5dtr9o', '角色权限管理', NULL, '/manage/rolePermission')
     , ('2noRxpl4f8CWq4Ct1RrP2eTPAogqpVRL', '商品删除', NULL, '/cart/item==DELETE')
     , ('3ykacmaEcneOHNkUIXiQgcFD0zNMVRlH', '角色权限查询', NULL, '/manage/rolePermission/selectByPermissionId')
     , ('9Z7lgxaJgyy1YDX4qEJaT8lz6BBCqhWb', '权限查询', NULL, '/manage/permission/selectByName')
     , ('9Z8S0T4mzENe8vx9uyhoCgfEXlyeLCqZ', '角色管理', NULL, '/manage/role')
     , ('ftIwOHWXYQVg2F4QJYCwSt2mhGmOWBVw', '用户角色管理', NULL, '/manage/userRole')
     , ('GoNSdtolrwqkOPaA8FykDjgjnl0wymeU', '权限管理', NULL, '/manage/permission')
     , ('hIYomA00PH40V7d4OTnku63q2SUSCPhq', '用户删除', NULL, '/cart/user==DELETE')
     , ('IE8FK70KnQ9iPtJu1lg0dct5lraVnVm9', '角色查询', NULL, '/manage/role/selectByName')
     , ('kkYuAN9X5YUgg6ZyJLU8f2lUjFvnvblW', '用户角色查询', NULL, '/manage/userRole/selectByUserId')
;
INSERT INTO permission_ (id, name, description, url)
VALUES ('m3OIq1IPAR22ymhCz4CXcczn3KDeQKDZ', '商品修改', NULL, '/cart/item==POST')
     , ('OZsNCGxt6Nd5qMXF3vxrHiO0URUPknYT', '用户角色查询', NULL, '/manage/userRole/selectByRoleId')
     , ('s2LKd1fuSAKWJoUF5m7uAyujmj1sGoJ6', '用户查询', NULL, '/cart/user==GET')
     , ('V7w3Dxjxnr4yO63sT5zYZE4APAHxEJju', '角色权限查询', NULL, '/manage/rolePermission/selectByRoleId')
;


INSERT INTO user_role (role_id, user_id)
VALUES ('J2V0sCo4Qw3rXjI32UhKCvKwsnVnV5VF', 'HOVQLXMiXTgeddezi7hE8HtUNFRMe4sc')
     , ('V9VcEfOesNJt7AVPr9BJwvenEl3AGwTA', 'hfN5pW5GXcXZecPJNvYTDdri7d1RneqN')
;

INSERT INTO role_permission (permission_id, role_id)
VALUES ('0lmwQA7fhqEGStwdNnpc5lktpG5dtr9o', 'J2V0sCo4Qw3rXjI32UhKCvKwsnVnV5VF')
     , ('2noRxpl4f8CWq4Ct1RrP2eTPAogqpVRL', 'V9VcEfOesNJt7AVPr9BJwvenEl3AGwTA')
     , ('3ykacmaEcneOHNkUIXiQgcFD0zNMVRlH', 'J2V0sCo4Qw3rXjI32UhKCvKwsnVnV5VF')
     , ('9Z7lgxaJgyy1YDX4qEJaT8lz6BBCqhWb', 'J2V0sCo4Qw3rXjI32UhKCvKwsnVnV5VF')
     , ('9Z8S0T4mzENe8vx9uyhoCgfEXlyeLCqZ', 'J2V0sCo4Qw3rXjI32UhKCvKwsnVnV5VF')
     , ('ftIwOHWXYQVg2F4QJYCwSt2mhGmOWBVw', 'J2V0sCo4Qw3rXjI32UhKCvKwsnVnV5VF')
     , ('GoNSdtolrwqkOPaA8FykDjgjnl0wymeU', 'J2V0sCo4Qw3rXjI32UhKCvKwsnVnV5VF')
     , ('hIYomA00PH40V7d4OTnku63q2SUSCPhq', 'J2V0sCo4Qw3rXjI32UhKCvKwsnVnV5VF')
     , ('IE8FK70KnQ9iPtJu1lg0dct5lraVnVm9', 'J2V0sCo4Qw3rXjI32UhKCvKwsnVnV5VF')
     , ('kkYuAN9X5YUgg6ZyJLU8f2lUjFvnvblW', 'J2V0sCo4Qw3rXjI32UhKCvKwsnVnV5VF')
;
INSERT INTO role_permission (permission_id, role_id)
VALUES ('m3OIq1IPAR22ymhCz4CXcczn3KDeQKDZ', 'V9VcEfOesNJt7AVPr9BJwvenEl3AGwTA')
     , ('OZsNCGxt6Nd5qMXF3vxrHiO0URUPknYT', 'J2V0sCo4Qw3rXjI32UhKCvKwsnVnV5VF')
     , ('s2LKd1fuSAKWJoUF5m7uAyujmj1sGoJ6', 'J2V0sCo4Qw3rXjI32UhKCvKwsnVnV5VF')
     , ('V7w3Dxjxnr4yO63sT5zYZE4APAHxEJju', 'J2V0sCo4Qw3rXjI32UhKCvKwsnVnV5VF')
;

