### BookInfo Application Testing Guide

#### 测试地址

https://www.katacoda.com/courses/istio/deploy-istio-on-kubernetes

#### BookInfo Application 部署文件地址:

https://github.com/istio/istio/tree/1.5.4/samples/bookinfo

```sh


// 下载istio 1.5.4
$ curl -L https://istio.io/downloadIstio | ISTIO_VERSION=1.5.4 TARGET_ARCH=x86_64 sh -

// 打开istio目录
$ cd istio-1.5.4

// 添加环境变量
$ export PATH=$PWD/bin:$PATH

// 安装 isito
$ istioctl manifest apply --set profile=demo

// 查看istio-system 下 isito的组件
$ kubectl get svc -n istio-system -o name

// 设置 default 命名空间为 istio 自动注入 sidecar
$ kubectl label namespace default istio-injection=enabled

// 部署bookinfo服务 在线文件地址 : https://github.com/istio/istio/blob/1.5.4/samples/bookinfo/platform/kube/bookinfo.yaml
$ kubectl apply -f samples/bookinfo/platform/kube/bookinfo.yaml

// 查看service, po 是否正常安装 
$ kubectl get svc,po

// 检测 productpage服务是否正常启动
$ kubectl exec "$(kubectl get pod -l app=ratings -o jsonpath='{.items[0].metadata.name}')" -c ratings -- curl -sS productpage:9080/productpage | grep -o "<title>.*</title>"

// 部署gateway , 使得 mesh 外部可以访问
$ kubectl apply -f samples/bookinfo/networking/bookinfo-gateway.yaml

// 查看gateway
$ kubectl get gateway

// 点击dashboard Tab 页, 在地址后追加 /productpage 访问, 发现reviews服务的三个版本依次被调用

// 查看Pod的Label
$ kubectl get po --show-labels | grep reviews

// 查看reviews服务会转发到哪几个review实例中去.
$ kubectl get svc reviews -o jsonpath='{.spec.selector}'

// 初始化DestinationRule
$ kubectl apply -f samples/bookinfo/networking/destination-rule-all.yaml

// 初始化VirtualService
$ kubectl apply -f samples/bookinfo/networking/virtual-service-all-v1.yaml

// 刷新页面, 流量全部转发到了v1版本, 看不到一个星星.

// 修改review的vs, 将v1改为v2. 
$ kubectl edit vs reviews -o yaml

//保存后刷新页面发现流量全部被转发到了v2版本.

// 修改 jason 用户登录后访问 v2 版本的 reviews 服务, 未登录则访问v3版本的 reviews 服务.
$ kubectl apply -f samples/bookinfo/networking/virtual-service-reviews-jason-v2-v3.yaml

// 点击页面中的 Sign in , 使用 jason 用户登录(密码为空), 发现调用了 v3 版本的 reviews 服务. 

// 点击 Sign out 退出登录, 发现调用的是 v2 版本的 reviews 服务.
```

