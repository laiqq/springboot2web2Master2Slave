# springboot2web2Master2Slave
spingboot实现api接口，分别对应2个web系统，web分别对应2个主2个从，主写从读，集成rabbitmq、resttemplate、http调用
 
本地启动rabbitmq或修改连接配置账号密码，或注释掉mq的代码
修改 数据库配置

创建 数据库test1、test2，创建表t_order、t_user
CREATE TABLE `t_order` (
  `order_id` VARCHAR(32) DEFAULT NULL,
  `goods_id` VARCHAR(32) DEFAULT NULL,
  `goods_name` VARCHAR(32) DEFAULT NULL
)

 CREATE TABLE `t_user` (
  `user_name` VARCHAR(64) DEFAULT NULL,
  `age` INT(11) DEFAULT NULL,
  `sex` INT(11) DEFAULT NULL,
  `uid` VARCHAR(32) DEFAULT NULL
)
 
webSerice调试地址例子：http://127.0.0.1:8099/Services/userService?wsdl
 
http 使用postMan调试 
测试查询  http://localhost:8099/test/user/select    post请求，请求报文如下 application/json格式
{
"uid" : "10000",
"userName" : "pony1",
"sex" : 27,
"age" : 0
}
 
测试写操作  http://localhost:8099/test/mmtorder   post请求 ，请求报文如下 ，application/json格式
{
"orderId" : "10000",
"goodsId" : "11111",
"goodsName" : "11111"
}
