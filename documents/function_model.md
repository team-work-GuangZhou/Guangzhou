## 系统顺序图
- 用例主场景一：用户登录注册</br>
![用户登录注册](../assets/UML/登陆注册系统顺序图.png)

- 用例主场景二：发布&展示信息</br>
![发布展示信息](../assets/UML/发布信息及展示系统顺序图.png)

- 用例主场景三：下单</br>
![下单](../assets/UML/下单系统顺序图.png)

- 用例主场景四：退款</br>
![退款](../assets/UML/退款系统顺序图.png)

## 操作契约
- 契约一：createNewAccount</br>
	操作：createNewAccount(AccountID：int, Password:string, Telphone:int)</br>
	前置条件：用户正在注册</br>
	后置条件：创建一个新账号，将该用户的注册信息存入数据库</br>
  
- 契约二：EnterUserInformation</br>
	操作：EnterUserInformation(UserID:int, Password:string)</br>
	前置条件：用户正在登录</br>
	后置条件：核对数据库里已注册的用户信息</br>
  
- 契约三：createMessage</br>
	操作：createMessage(Tittle:string, Image:bitmap, Description:string, Place:string, Data:string, Fare:float)</br>
	前置条件：用户正在发布信息</br>
	后置条件：系统为该用户创建一条信息数据，将该信息写入数据库中，并将处理后的信息返回给用户</br>
  
- 契约四：Display</br>
	操作：Display(title:string, image:bitmap, data:string)</br>
	前置条件：用户确认发布信息</br>
	后置条件：按发布时间和分类展示在系统的不同位置</br>
  
- 契约五：confirmOrderInfo</br>
	操作：confirmOrderInfo(userID:int, orderID:long)</br>
	前置条件：用户正在确认订单</br>
	后置条件：系统确认订单并更改订单状态</br>
  
- 契约六：requireRefund</br>
	操纵：requireRefund(userID:int, orderID:long)</br>
	前置条件：用户正在发起退款</br>
	后置条件：经过系统审核后，返回请求结果，并更改订单状态</br>
