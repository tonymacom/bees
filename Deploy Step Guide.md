Deploy Step Guide
测试地址
https://www.katacoda.com/courses/container-runtimes/building-container-images-with-buildah
### 第一步: 创建一个可运行的服务
```
 1. 拉取代码并切换到 deploy_step_demo 分支
 $ git clone https://github.com/tonymacom/bees.git
 2. 编写一个Dockerfile
```

### 第二步: 制作镜像
```
1. 打开测试地址, 拉起一个 Docker 坏境.
2. 使用 docker build 打镜像.
    docker build -t itmabo/bees:latest .    
3. 登录 docker.io
    docker login docker.io -u itmabo -p xxxx
4. 使用 docker push 推送镜像到远程仓库.
    docker push itmabo/bees:latest
```

### 第二步: 编写部署编排文件

```
1. 编写 bees-deploy.yml
```