# bees
simple web application



/name

/request/info

/error/500

/error/{status}

### 过滤器介绍

envoy_filter.yaml
只对bees的所有response添加 跨域请求头 `access-control-allow-origin`

envoy_filter_gateway.yaml
对经过网关的所有请求添加 跨域请求头 `access-control-allow-origin`

#### 解决的问题
istio 的 RequestAuthentication 资源对请求头中不合法的 Authorization 值校验之后直接会返回 `401` ,前端浏览器会先处理跨域，后返回401， 所以为了让前端能够拿到请求返回的401， 在请求头上需要加上`access-control-allow-origin : *` 样的相应头。