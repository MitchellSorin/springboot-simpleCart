# a simple cart
the cart includes user, item and order

## tools
* springboot
* jpa
* redis
* swagger
* websocket
* shiro

## introduction
* 业务功能：用户注册，商品维护，订单的crud

* 平台管理：用户，角色，权限。
权限基于url配置，支持restful接口的不同方法(形式/cart/item==GET)

* 角色：
    1. 商家：新增，修改，删除商品
    2. 管理员：维护用户，角色，权限，用户角色，角色权限
* 用户注册，商品查询，订单crud，登录可用
* websocket测试接口没加入权限管理，使用WEB-INF里的测试页面显示数据