# javautils
java综合学习工具类
项目结构层次概述：
javautils-web 主要提供http请求，以及和前段交互
javautils-service对外提供dubbo服务-接口
javautils-remote引用第三方服务
javautils-model 所有对象存放的地方
javautils-dao mybatis
javautils-common 公共类
javautils-biz  
里面的export javautils-service对外提供dubbo服务接口实现类，
 里面的manager
a)对第三方平台封装的层，预处理返回结果及转化异常信息；
b)对 Service 层通用能力的下沉，如缓存方案、 中间件通用处理；
c)与 DAO 层交互，对 DAO 的业务通用能力的封装。

# 如何启动
找到这个类直接运行就可以了com.ju.test.QuickStartServer   

