# bees
simple web application

测试滚动发布。
- 使用Jenkins的发布Number发布最新分支代码。（Jenkinsfile-TEST，发布时Version不填写）
- 按照TAG发布。（Jenkinsfile-PRD）

/name

/request/info

/request/times

/request/times/reset

/request/sleep/{time} # 线程睡眠 (单位: 秒)

/request/running/{time} # 满线程运行 (单位: 秒)