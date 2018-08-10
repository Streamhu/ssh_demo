### 一、前期准备
#### 1.环境

##### &nbsp;&nbsp;&nbsp; 开发环境：idea + maven + jdk1.8 + tomcat8 + msyql5.6.14

#####  &nbsp;&nbsp;&nbsp;主要框架：Spring（4.2.5） + SpringMVC（4.2.5） + Mybatis（3.3.0）

#### 2. jdk配置好，tomcat配置好，maven配置，mysql安装好，且建好user表（id, name, address, city）

#### 3. 在idea中新建maven项目


<hr>

### 二、spring搭建
#### 1. 在pom.xml中已入若下配置（导入spring所需的jar包）
```
<!-- spring -->
<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-context</artifactId>
  <version>4.2.5.RELEASE</version >
</dependency>
<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-web</artifactId>
  <version>4.2.5.RELEASE</version>
</dependency>
```
#### 2. 在resources目录下新建spring.xml配置文件，如下
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd">

</beans>
```
#### 3. 在web.xml中加入如下配置
```
<!-- 容器全局变量加载spring.xml文件-->
<context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath:spring.xml</param-value>
</context-param>	

<!-- 监听器启动spring容器 -->
<listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>
```

<hr>

### 三、springMVC搭建
#### 1. 导入springMVC需要的jar包, 在pom.xml中追加如下配置
```
<!-- spring-mvc -->
<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-webmvc</artifactId>
  <version>4.2.5.RELEASE</version>
</dependency>
```
#### 2. 新建com.hh.test.controller包（供后面springMVC文件扫描包用）  

#### 3. 在resources目录下新建spring-mvc.xml配置文件
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns="http://www.springframework.org/schema/beans"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
       http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd
       http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc-3.2.xsd"
       default-autowire="byName">


    <!-- 自动扫描的包名， -->
    <context:component-scan base-package="com.hh.test.controller" ></context:component-scan>

    <!-- 默认的注解映射的支持 -->
    <mvc:annotation-driven />
    
</beans>    
```
#### 4. 在web.xml中加入以下配置
```
<servlet>
    <servlet-name>spring-mvc</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath*:spring-mvc.xml</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
</servlet>

<servlet-mapping>
    <servlet-name>spring-mvc</servlet-name>
    <url-pattern>/*</url-pattern>
</servlet-mapping>
```
#### 5. 在com.hh.test.controller新建测试类TestController  
```
@Controller
public class TestController {

    @RequestMapping(value="/test")
    public void test(){
        System.out.println("springMVC配置成功");
    }

} 
```
#### 6. 启动tomcat, 在浏览器中输入 http://localhost:8080/test ，可以看到控制台打印语句 "springMVC配置成功"

<hr>

### 四、mybatis配置
#### 1.导入mybatis以及集成所需的jar包，配置如
```
<!-- mybatis -->
<dependency>
  <groupId>org.mybatis</groupId>
  <artifactId>mybatis</artifactId>
  <version>3.3.0</version>
</dependency>
<dependency>
  <groupId>org.mybatis</groupId>
  <artifactId>mybatis-spring</artifactId>
  <version>1.2.3</version>
</dependency>
<!-- spring jdbc -->
<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-jdbc</artifactId>
  <version>4.2.5.RELEASE</version>
</dependency>
<!-- msyql的驱动 -->
<dependency>
  <groupId>mysql</groupId>
  <artifactId>mysql-connector-java</artifactId>
  <version>5.1.25</version>
</dependency>
```
#### 2. 建以下文件（具体看项目github代码）
```
 1）建com.hh.test.entity包
 2) 建User类（POJO对象）
 3）建com.hh.test.dao包
 4) 建userDao接口
 5）建com.hh.test.service包
 6）建UserService接口
 7）建com.hh.test.service.impl包
 8）建具体实现类UserServiceImpl
 9）在com.hh.test.service.controller下建MybatisTestController类
 10）在resource文件夹下建mapper文件夹
```
#### 3. 在resouces/mapper下建UserMapper.xml文件
```
 <mapper namespace="com.hh.test.dao.UserDao">

    <!-- 定义好返回类型 -->
    <resultMap type="com.hh.test.entity.User" id="userResultMap" >
        <!-- 映射数据库的字段 -->
        <id property="id" column="id"></id>
        <result property="name" column="name"></result>
        <result property="address" column="address"></result>
        <result property="city" column="city"></result>
    </resultMap>

    <!-- 查找所有记录 -->
    <select id="selectAll" resultMap="userResultMap">
        select * from user
    </select>

</mapper> 
```

#### 4.在resources目录下新建mybatis-config.xml文件
```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">

<configuration>

    
</configuration>
```

#### 5. 在resources目录下新建jdbc.propertites（写好连接mysql的参数）
```
jdbc.driverClassName=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://127.0.0.1:3306/hh?characterEncoding=utf-8
jdbc.username=root
jdbc.password=	

```


#### 6. 在spring.xml文件中配置
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

    <!-- 新增扫描service层的包 -->
    <context:component-scan base-package="com.hh.test.service" ></context:component-scan>

    <!-- 引入数据库配置参数文件jdbc.properties -->
    <context:property-placeholder location="classpath:jdbc.properties"/>

    <!-- 数据源，加载jdbc驱动参数 -->
    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="${jdbc.driverClassName}"/>
        <property name="url" value="${jdbc.url}"/>
        <property name="username" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
    </bean>

    <!-- spring和MyBatis整合 -->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource" />
        <!-- 自动扫描mapper.xml文件 -->
        <property name="mapperLocations" value="classpath:mapper/*.xml"></property>
        <property name="configLocation" value="classpath:mybatis-config.xml"></property>
    </bean>

    <!-- Mapper接口所在包名，Spring会自动查找其下的类 -->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="com.hh.test.dao" />
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"></property>
    </bean>

</beans>	
```

#### 7. 启动tomcat, 在浏览器中输入 http://localhost:8080/test/mybatis/select， 在控制台打印出从数据库获取的数据表示成功	


<hr>

#### **参考网址**
[Spring框架 4.3.6环境搭建](https://www.cnblogs.com/Mr-ww/p/6403501.html?utm_source=itdadao&utm_medium=referral)

[SSM框架开发web项目系列（一） 环境搭建篇](https://www.cnblogs.com/young-z/p/7923180.html)




<font color=#FF4500 size=3>注：文章是经过参考其他的文章然后自己整理出来的，有可能是小部分参考，也有可能是大部分参考，但绝对不是直接转载，觉得侵权了我会删，我只是把这个用于自己的笔记，顺便整理下知识的同时，能帮到一部分人。
ps : 有错误的还望各位大佬指正,小弟不胜感激</font>