#Docker image for springboot file run
#基础镜像使用java
FROM java:8
#作者
MAINTAINER wangtao <wwangtaoc11@163.com>
#指定临时文件目录为/tmp
VOLUME /tmp
#将jar包添加到容器中并更名为itour.jar
ADD itour-online-web.jar itour.app
#运行jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/itour.jar"]