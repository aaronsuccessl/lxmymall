
1. 项目介绍
监控示例：日志系统 + APM系统 + GPE系统

基于Spring Boot 2.0.X、Spring Cloud Finchley和Spring Cloud Alibaba

前后端分离的企业级微服务架构
基于Spring Boot 2.0.X、Spring Cloud Finchley和Spring Cloud Alibaba
深度定制Spring Security真正实现了基于RBAC、jwt和oauth2的无状态统一权限认证的解决方案
提供应用管理，方便第三方系统接入
引入组件化的思想实现高内聚低耦合，项目代码简洁注释丰富上手容易
注重代码规范，严格控制包依赖，每个工程基本都是最小依赖
非常适合学习和企业中使用

二流程图


3. 功能介绍
• 统一认证功能
o 支持oauth2的四种模式登录
o 支持用户名、密码加图形验证码登录
o 支持手机号加密码登录
o 支持openId登录
o 支持第三方系统单点登录
• 分布式系统基础支撑
o 服务注册发现、路由与负载均衡
o 服务降级与熔断
o 服务限流(url/方法级别)
o 统一配置中心
o 统一日志中心
o 统一分布式缓存操作类、cacheManager配置扩展
o 分布式锁
o 分布式任务调度器
o 支持CI/CD持续集成(包括前端和后端)
o 分布式高性能Id生成器
o 分布式事务
• 系统监控功能
o 服务调用链监控
o 应用拓扑图
o 慢服务检测
o 服务Metric监控
o 应用监控(应用健康、JVM、内存、线程)
o 错误日志查询
o 慢查询SQL监控
o 应用吞吐量监控(qps、rt)
o 服务降级、熔断监控
o 服务限流监控
o 分库分表、读写分离
• 业务基础功能支撑
o 高性能方法级幂等性支持
o RBAC权限管理，实现细粒度控制(方法、url级别)
o 快速实现导入、导出功能
o 数据库访问层自动实现crud操作
o 代码生成器
o 基于Hutool的各种便利开发工具
o 网关聚合所有服务的Swagger接口文档
o 统一跨域处理
o 统一异常处理

4. 模块说明
lxmymall-- 父项目，公共依赖
│  ├─lxmy-business -- 业务模块一级工程
│  │  ├─user-center -- 用户中心[7000]
│  │  ├─file-center -- 文件中心[5000]
│  │  ├─member-center -- 会员中心[7001]
│  │  ├─goods-center -- 商品中心[7002]
│  │  ├─order-center -- 订单中心[7003]
│  │  ├─marking-center -- 营销中心[7004]
│  │─lxmy-commons -- 通用工具一级工程
│  │  ├─lxmy-auth-client-spring-boot-starter -- 封装spring security client端的通用操作逻辑
│  │  ├─lxmy-common- starter -- 封装通用操作逻辑
│  │  ├─lxmy-db-starter      -- 封装数据库通用操作逻辑
│  │  ├─lxmy-log-starter     -- 封装log通用操作逻辑
│  │  ├─lxmy-redis-starter   -- 封装Redis通用操作逻辑
│  │  ├─lxmy-ribbon-starter  -- 封装Ribbon和Feign的通用操作逻辑
│  │  ├─lxmy-sentinel-starter -- 封装Sentinel的通用操作逻辑
│  │  ├─lxmy-swagger2-starter -- 封装Swagger通用操作逻辑
│  ├─lxmy-config -- 配置中心
│  ├─lxmy-doc -- 项目文档
│  ├─lxmy-gateway -- api网关一级工程
│  │  ├─zuul-gateway -- netflix-zuul[8080]
│  ├─lxmy-job -- 分布式任务调度一级工程
│  │  ├─job-admin -- 任务管理器[8081]
│  │  ├─job-core -- 任务调度核心代码
│  │  ├─job-executor-samples -- 任务执行者executor样例[8082]
│  ├─lxmy-monitor -- 监控一级工程
│  │  ├─sc-admin -- 应用监控[6500]
│  │  ├─log-center -- 日志中心[6200]
│  ├─lxmy-uaa -- spring-security认证中心[8000]
│  ├─lxmy-register -- 注册中心Nacos[8848]
│  ├─l
