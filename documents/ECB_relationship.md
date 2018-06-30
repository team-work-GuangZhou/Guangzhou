## 逻辑架构
  逻辑架构关注的是功能，包含用户直接可见的功能，还有系统中隐含的功能。我们将项目分为“表示层，业务逻辑层，数据访问层”这样的三层逻辑。
![逻辑架构图][1]

 - Application层主要由响应各种用户界面请求的动作类组成，它会调用Bussiness层中的函数进行业务逻辑处理，同时根据结果显示不同的界面给用户
 - Business层主要是完成业务逻辑层，同时包括与数据库的对接，以及访问存储数据
 - DataBase层主要完成数据存储，删除，持久化等操作，以及数据库安全访问，系统服务等

 [1]: https://github.com/team-work-GuangZhou/Guangzhou/blob/master/assets/img/ECB.PNG
