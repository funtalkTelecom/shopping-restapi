FROM  java:8

#声明一个挂载点，容器内此路径会对应宿主机的某个文件夹
VOLUME /tmp

#应用构建成功后的jar文件被复制到镜像内，名字也改成了taibei.jar
#ADD  ../target/taibei-1.0.jar  taibei.jar
ADD  ./taibei-1.0.jar  taibei.jar

#启动容器时的进程
ENTRYPOINT ["java","-jar","taibei.jar"]

#暴露80端口
EXPOSE  80