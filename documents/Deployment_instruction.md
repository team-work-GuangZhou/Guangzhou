## To run the application, please follow the instructions below:
1.	搭建nodejs环境，直接进入node官网下载相应版本的node即可
 ![nodejs环境][1]

2.	打开命令行，进入服务器所在文件夹(即，将**本项目codes文件夹下的server文件夹**下载到本地后所在的文件夹)，输入“npm start”，启动服务器，如果显示一下内容，则意味着运行成功。
 ![服务器][2]

3.	注意因为服务器搭建在本机，所以**Android必须与电脑是同IP段**，即在同一个局域网才可以访问。</br>
在电脑上用一个随身wifi共享网络（windows自带wifi或者360等其他wifi），让手机连接。</br>
要知道IP段的做法是，用cmd命令提示符 ipconfig查看本机的ip地址。</br>
![ipl][4]</br>
然后修改**App.java文件**中网络请求的URL.例如：192.168.137.1：5000（端口不变）</br>
 ![url][3]</br>
 **App.java文件的位置如下图所示**</br>
 ![改IP][5]</br>
4.下载codes文件夹下的android_code文件夹下的全部内容(即，安卓项目文件)到本地，使用AS打开安卓项目，生成apk安装在手机上即运行可。**本软件需要安装在android平台版本4.4及以上。**

 [1]: https://github.com/team-work-GuangZhou/Guangzhou/blob/master/assets/img/nodejs.png
 [2]: https://github.com/team-work-GuangZhou/Guangzhou/blob/master/assets/img/server.png
 [3]: https://github.com/team-work-GuangZhou/Guangzhou/blob/master/assets/img/url.png
 [4]: https://github.com/team-work-GuangZhou/Guangzhou/blob/master/assets/img/ip.png
 [5]: https://github.com/team-work-GuangZhou/Guangzhou/blob/master/assets/img/改IP.PNG
