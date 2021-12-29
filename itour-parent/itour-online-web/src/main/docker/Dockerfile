#Docker image for springboot file run
#1.使用FROM指令指明所基于的镜像名称;
FROM java:8
#2.使用MAINTAINER指令说明维护者信息
MAINTAINER wangtao <wwangtaoc11@163.com>
#3.创建一个数据卷挂载点
VOLUME /tmp
#4.添加内容到镜像
ADD itour-online-web.jar itour.app
#5.指定镜像的默认入口命令
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/itour.jar"]