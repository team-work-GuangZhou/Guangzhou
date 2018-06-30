# 逛周API

## 用户

### 登录

* POST /api/v1/login
* 参数:
    * `username` {string} 用户名
    * `password` {string} 密码

* 返回: 
    {
        code: 200,
        data: {
            token: "",
            message: "错误信息"
        }
    }

### 注册

* POST /api/v1/register
* 参数:
    * `username` {string} 用户名
    * `password` {string} 密码
    * `repeat_password` {string} 重复密码
    * `email` {string} 邮箱

* 返回: 
    {
        code: 200,
        data: {
            message: "错误信息"
        }
    }

## 动态

### 发布动态

* POST /api/v1/posts