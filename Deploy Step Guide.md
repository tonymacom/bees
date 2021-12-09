Deploy Step Guide
测试地址
https://www.katacoda.com/courses/container-runtimes/building-container-images-with-buildah
### 第一步: 创建一个可运行的服务
```
 1. 拉取代码并切换到 deploy_step_demo 分支
    git clone https://github.com/tonymacom/bees.git
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
1. 编写 bees-deploy.yaml
    1.1 deployment
    1.2 service
    1.3 virtualservice
    1.4 destinationrule
```

### 第三步: 配置 Jenkins
```
1. 编写Jenkinsfile 文件
2. 配置Jenkins
    2.1 新建任务bees , 选择"流水线" 确认.
    2.2 流水线定义下拉框选择 "pipeline script from SCM".
    2.3 SCM 选择 "Git"
    2.4 输入 Repository URL : https://github.com/tonymacom/bees.git
    2.5 选择一个 Credentials
    2.6 修改代码分支为: */deploy_step_demo
    2.7 修改脚本路径为 Jenkinsfile-TEST 
    2.8 保存引用
3. 点击立即构建.    
```

### 第四步: 访问测试
```
https://api.tb1.sayweee.net/bees/name
```