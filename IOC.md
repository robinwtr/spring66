

spring创建对象，执行无参数构造

如何使用反射创建对象？

1. 加载bean.xml配置文件
2. 对xml文件进行解析操作
3. 获取xml文件的bean标签属性值 id和class属性值
4. 使用反射根据了全路径创建对象

```java
public void testUserBean() throws Exception{
        Class clazz = Class.forName("com.atguigu.spring66.User");
        User user = (User)clazz.getDeclaredConstructor().newInstance();
        System.out.println(user);
    }
```

 创建的对象放到了哪里？

```java
Map<string,BeanDefinition> beanDefinitionMap  
Key：唯一标识
value：类的定义描述信息
```

###### Log4j2：

1. 日志信息优先级：**TRACE< DEBUG< INFO< WARN< ERROR< FATAL**

TRACE: 追踪，最低的日志级别，相当于追踪程序的执行

DEBUG：调试，一般在开发中，将其设置为最低的日志级别

INFO：信息，输出重要的信息，使用较多

WARN：警告，输出警告的信息

ERROR：错误，输出错误的信息

FATAL：严重错误



## IOC

###### 什么是IOC？

1. 控制反转，把对象创建和对象之间的调用过程，交给spring进行管理
2. 使用IOC的目的，为了耦合度降低 ，解耦

###### IOC底层原理

1. xml解析、工厂模式、反射

```java
class UserFactory{
   public static UserDao getDao(){
       String classValue = class属性值; //xml解析
       Class clazz = Class.forName(className); //反射创建对象
       return (UserDao)clazz.new UserDao();
   } 
}
```

IOC思想基于IOC容器完成，IOC容器底层就是对象工厂

###### Spring 提供IOC容器的两种方式（两个接口）：

```java
//第一个
BeanFactory
(1) IOC容器基本实现，是Spring内部的使用接口，开发不用
    * 加载配置文件时  不会创建对象 获取或调用时才创建对象
//第二个
ApplicationContext
(1) BeanFactory的子接口
    * 加载配置文件时  就会创建对象
```

###### ApplicationContext的实现类

```Java
FileSystemXmlApplicationContext  绝对路径
ClassPathXmlApplicationContext   相对路径
```

###### IOC操作Bean管理

Bean管理：

1. spring创建对象
2. spring注入属性

#### Bean管理操作方式：

##### IOC中基于xml配置文件方式（实际开发中一般不用）

```xml
//创建对象
<bean id="user" class="com.atguigu.spring66.User"></bean>
id:  key  对象的唯一标识
class: 类的全路径
name: 与id相似 可以使用特殊符号
默认执行无参构造
//注入属性
DI（Dependency Injection）:依赖注入，就是注入属性 IOC中的一种具体实现
（1）有参构造注入
<bean id="order" class="com.atguigu.spring66.Order">
        <constructor-arg name="oname" value="computer" ></constructor-arg>
        <constructor-arg name="address" value="China"></constructor-arg>
</bean>
（2）set方法注入
<bean id = "book" class="com.atguigu.spring66.Book">
        <property name="bookName" value="阳光灿烂"></property>
        <property name="author" value="mhn"></property>
</bean>
（3）P名称空间注入 底层还是set方法注入
```

字面量： 设置的固定内容   

```xml
//空值null
<property name="publisher">
	<null></null>
</property>
//特殊符号
<property name="publisher">
	<value>
		<![CDATA[<南京>]]>
	</value>
</property>
```

注入属性-外部Bean

外部Bean是指当spring属性注入的配置对象的成员属性是其他类对象时，则对该成员对象也要声明创建，创建好后通过ref属性进行注入

```java
public interface UserDao {
    public void insertUser();
}
public class UserDaoImpl implements UserDao {
    @Override
    public void insertUser() {
        System.out.println("insert user.........");
    }
}
public class UserService {
    private UserDao userDao;
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    public void add(){
        System.out.println("service add............");
        userDao.insertUser();
    }
}
	@Test
	public void testAdd(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        UserService userService = (UserService) context.getBean("userService");
        userService.add();
    }
```

```xml
<bean id="userService" class="com.atguigu.spring66.service.UserService">
	<property name="userDao" ref="userDaoImpl"></property>
</bean>
<bean id="userDaoImpl" class="com.atguigu.spring66.dao.UserDaoImpl"></bean>
```

注入属性-内部Bean和级联赋值

```java
//一对多关系：部门和员工
public class Dept {
    private String deptName;
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}

public class Emp {
    private String empname;
    private String empgender;
    private Dept dept;
    public void setEmpname(String empname) {
        this.empname = empname;
    }
    public void setEmpgender(String empgender) {
        this.empgender = empgender;
    }
    public void setDept(Dept dept) {
        this.dept = dept;
    }
    
    // ★★★ 级联赋值必须要有 Getter！否则 Spring 拿不到 dept 对象 ★★★
    public Dept getDept() {
        return dept;
    }
}
```

```xml
<!--内部Bean-->
<bean id="emp" class="com.atguigu.spring66.inner.Emp">
        <property name="empname" value="lucy"></property>
        <property name="empgender" value="nv"></property>
        <property name="dept">
            <bean id="dept" class="com.atguigu.spring66.inner.Dept">
                <property name="deptName" value="IT"></property>
            </bean>
        </property>
    </bean>
<!--级联赋值-->
<bean id="emp" class="com.atguigu.spring66.inner.Emp">
        <property name="empname" value="lucy"/>
        <property name="empgender" value="nv"/>
        <property name="dept" ref="deptBean"/>
        <property name="dept.deptName" value="yanfabu"/>
    </bean>
    <bean id="deptBean" class="com.atguigu.spring66.inner.Dept">
        <property name="deptName" value="caiwubu"/>
    </bean>
```

IOC操作注入集合属性

```java
public class Stu {
    private String[] courses;

    private List<String> list;

    private Map<String,String> map;

    private Set<String> set;
    public void setCourses(String[] courses) {
        this.courses = courses;
    }
    public void setList(List<String> list) {
        this.list = list;
    }
    public void setMap(Map<String, String> map) {
        this.map = map;
    }
    public void setSet(Set<String> set) {
        this.set = set;
    }
    public void print() {
        System.out.println(courses+"::"+list+"::"+map+"::"+set);
    }
}
```

```xml
<bean id="stu" class="com.atguigu.spring66.collectiontype.Stu">
        <!--数组类型注入-->
        <property name="courses">
            <array>
                <value>java课程</value>
                <value>数据库</value>
            </array>
        </property>
        <!--list类型注入-->
        <property name="list">
            <list>
                <value>张三</value>
                <value>小三</value>
            </list>
        </property>
        <!--map类型注入-->
        <property name="map">
            <map>
                <entry key="JAVA" value="java"></entry>
                <entry key="PHP" value="php"></entry>
            </map>
        </property>
        <!--set类型注入-->
        <property name="set">
            <set>
                <value>Mysql</value>
                <value>Redis</value>
            </set>
        </property>
    </bean>
```

集合里设置对象类型

```xml
<!--List注入 值是对象-->
        <property name="courseList">
            <list>
                <ref bean="course1"></ref>
                <ref bean="course2"></ref>
            </list>
        </property>
<bean id="course1" class="com.atguigu.spring66.collectiontype.Course">
        <property name="courseName" value="Spring666"></property>
    </bean>
    <bean id="course2" class="com.atguigu.spring66.collectiontype.Course">
        <property name="courseName" value="Mybatis666"></property>
    </bean>
```

把集合注入部分提取出来

```xml
(1)在spring配置文件中引入名称空间util
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:util="http://www.springframework.org/schema/util"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util.xsd">
(2)使用util标签完成list集合注入提取
<!--util提取list集合类型属性注入-->
    <util:list id="booklist">
        <value>基金经</value>
        <value>九阳设个</value>
        <value>你个大胆</value>
    </util:list>
    <!--util提取list集合类型属性注入使用-->
    <bean id="bookbook" class="com.atguigu.spring66.collectiontype.Bookbook">
        <property name="books" ref="booklist"></property>
    </bean>
```

###### FactoryBean & 普通Bean

普通Bean：在配置文件中定义的Bean类型就是返回类型

工厂Bean：在配置文件中定义的Bean类型可以和返回类型不一样

- 创建类 让这个类作为工厂Bean 实现接口FactoryBean
- 实现接口里面的方法，在实现的方法中定义返回的Bean类型

```java
public class MyBean implements FactoryBean<Course> {

    //定义返回bean
    @Override
    public Course getObject() throws Exception {
        Course course = new Course();
        course.setCourseName("abc");
        return course;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return FactoryBean.super.isSingleton();
    }
}

@Test
    public void testAdd5(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        Course course = context.getBean("mybean", Course.class);
        System.out.println(course);
    }
```

```xml
<bean id="mybean" class="com.atguigu.spring66.factorybean.MyBean"></bean>
```

###### bean的作用域

在Spring里面 可以设置bean是单实例还是多实例（默认单实例）

```java
@Test
    public void testAdd4(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        Bookbook bookbook1 = (Bookbook) context.getBean("bookbook");
        Bookbook bookbook2 = (Bookbook) context.getBean("bookbook");
        //bookbook.print();
        System.out.println(bookbook1);
        System.out.println(bookbook2);
    }
output:
com.atguigu.spring66.collectiontype.Bookbook@7728643a
com.atguigu.spring66.collectiontype.Bookbook@7728643a
```

可以看到两个对象的地址是相同的 此时应该是一个对象

```xml
//在spring配置文件bean标签中里面有属性用于设置单实例还是多实例
//scope属性值 默认值-->singleton，表示是单实例对象 -->prototype 表示是多实例对象
<!--xml-->
<bean id="bookbook" class="com.atguigu.spring66.collectiontype.Bookbook" scope="prototype">
        <property name="books" ref="booklist"></property>
    </bean>

output:
com.atguigu.spring66.collectiontype.Bookbook@7728643a
com.atguigu.spring66.collectiontype.Bookbook@320e400

singleton和prototype的区别
设置scope值为singleton加载spring配置文件时就会创建单实例对象
设置scope值为prototype不是在加载spring配置文件是创建对象 使用getBean()获取对象时才创建对象
//request web一次请求
//session web一次会话
```

###### bean的生命周期

生命周期就是对象从创建到销毁的过程

1. 通过构造器创建bean实例（无参构造）
2. 为bean属性进行注入以及对其他外部bean的引用（调用set方法）
3. 调用bean的初始化的方法（要进行配置）
4. bean可以使用（对象获取）
5. 容器关闭时，调用bean的销毁方法（要进行配置）

```java
public class Orders {
    private String oname;
    public Orders(){System.out.println("第一步 执行无参构造创建bean实例");}
    public void setOname(String oname) {
        this.oname = oname;
        System.out.println("第二步 使用set方法设置属性值");
    }
    public void initMethod(){System.out.println("第三步 执行初始化方法");}
    public void destroyMethod(){System.out.println("第五步 执行销毁方法");}
}

@Test
    public void testAdd6(){
        //ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
        //context.close()
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
        Orders orders = context.getBean("orders", Orders.class);
        System.out.println("第四步 得到创建的bean实例"+orders);
        ((ClassPathXmlApplicationContext)context).close();
    }
```

```xml
	<bean id="orders" class="com.atguigu.spring66.bean.Orders" init-method="initMethod" destroy-method="destroyMethod">
        <property name="oname" value="abc"></property>
    </bean>
```

```java
output:
第一步 执行无参构造创建bean实例
第二步 使用set方法设置属性值
第三步 执行初始化方法
第四步 得到创建的bean实例com.atguigu.spring66.bean.Orders@127e70c5
第五步 执行销毁方法


bean的后置处理器
1. 通过构造器创建bean实例（无参构造）
2. 为bean属性进行注入以及对其他外部bean的引用（调用set方法）
3. 把bean实例传递到bean后置处理器的方法-->postProcessBeforeInitialization
4. 调用bean的初始化的方法（要进行配置）
5. 把bean实例传递到bean后置处理器的方法-->postProcessAfterInitialization
6. bean可以使用（对象获取）
7. 容器关闭时，调用bean的销毁方法（要进行配置）

public class MyBeanPost implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("第三步之前 把bean实例传递到bean后置处理器的方法-->postProcessBeforeInitialization");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("第四步之后 把bean实例传递到bean后置处理器的方法-->postProcessAfterInitialization");
        return bean;
    }
}
<bean id="mybean" class="com.atguigu.spring66.factorybean.MyBean"></bean>
    
第一步 执行无参构造创建bean实例
第二步 使用set方法设置属性值
第三步之前 把bean实例传递到bean后置处理器的方法-->postProcessBeforeInitialization
第三步 执行初始化方法
第三步之后 把bean实例传递到bean后置处理器的方法-->postProcessAfterInitialization
第四步 得到创建的bean实例com.atguigu.spring66.bean.Orders@7e0aadd0
第五步 执行销毁方法
```

###### IOC操作Bean管理（xml自动装配）

什么是自动装配？ 根据指定的装配规则（属性名称或者属性类型），spring自动将匹配的属性值进行注入 （基本不用）

```xml
<!--实现自动装配
        bean标签里面的autowire，配置自动装配
        autowire属性常用两个值，
        byName根据属性名称注入  注入值bean的id值和类的属性名称要一致
        byType根据属性的类型注入
    -->
```

###### IOC操作Bean管理（外部属性文件）

连接数据库

```java
public class TestJdbc {
    @Test
    public void test1() {
        DruidDataSource ds = new DruidDataSource();
        ds.setUrl("jdbc:mysql://localhost:3306/spring?serverTimezone=UTC");
        ds.setUsername("root");
        ds.setPassword("atguigu");
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
    }
    @Test
    public void test2() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("jdbc.xml");
        DruidDataSource ds = ctx.getBean("druidDataSource", DruidDataSource.class);
        System.out.println(ds.getUrl());
    }
}

output:
jdbc:mysql://localhost:3306/spring?serverTimezone=UTC
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">

    <context:property-placeholder location="classpath:jdbc.properties"></context:property-placeholder>
    <bean id="druidDataSource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="url" value="${jdbc.url}"></property>
        <property name="username" value="${jdbc.user}"></property>
        <property name="password" value="${jdbc.password}"></property>
        <property name="driverClassName" value="${jdbc.driver}"></property>
    </bean>
</beans>
```

```json
jdbc.user=root
jdbc.password=123456
jdbc.url=jdbc:mysql://localhost:3306/spring?serverTimezone=UTC
jdbc.driver=com.mysql.cj.jdbc.Driver
```

##### IOC基于注解管理Bean

##### @Autowired注入

单独使用@Autowired注解，默认根据与类型装配 即  默认是byType

```java
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {
    boolean required() default true;
}
注解可以标注在哪里？
1.构造方法上
2.方法上
3.形参上
4.属性上
5.注解上
该注解有一个required属性，默认值是true，表示在注入的时候要求被注入的Bean必须是存在的，如果不存在则报错。如果required属性设置为false，表示注入的Bean存在或不存在都没有关系，存在的话就注入，不存在也不报错
```

###### 属性注入

```java
@Controller
public class UserController {
    //注入service 属性注入
    @Autowired //根据类型找到对应对象，完成注入
    private UserService userService;

    public void add(){
        System.out.println("controller.......");
        userService.add();
    }
}

public interface UserDao {
    public void add();
}
@Repository
public class UserDaoImpl implements UserDao {
    @Override
    public void add() {
        System.out.println("dao.......................");
    }
}

public interface UserService {
    public void add();
}
@Service
public class UserServiceImpl implements UserService{
    //注入dao
    //属性注入
    @Autowired //根据类型找到对应对象，完成注入
    private UserDao userDao;
    @Override
    public void add() {
        System.out.println("service...................");
        userDao.add();
    }
}

public class AllTestAutoWired {
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("bean3.xml");
        UserController userController = context.getBean(UserController.class);
        userController.add();

    }
}

output:
controller.......
service...................
dao.......................
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!--开启组件扫描-->
    <context:component-scan base-package="com.atguigu.spring66.autowired"></context:component-scan>
</beans>
```

###### set方法注入

```java
@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void add(){
        System.out.println("controller.......");
        userService.add();
    }
}
@Service
public class UserServiceImpl implements UserService{
    //注入dao
    //属性注入

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void add() {
        System.out.println("service...................");
        userDao.add();
    }
}

```

###### 构造方法注入

```java
@Controller
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    public void add(){
        System.out.println("controller.......");
        userService.add();
    }
}
@Service
public class UserServiceImpl implements UserService{
    private UserDao userDao;
    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }
    @Override
    public void add() {
        System.out.println("service...................");
        userDao.add();
    }
}

```

###### 形参注入

```java
@Controller
public class UserController {

    private UserService userService;
    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    public void add(){
        System.out.println("controller.......");
        userService.add();
    }
}
@Service
public class UserServiceImpl implements UserService{
    private UserDao userDao;

    public UserServiceImpl(@Autowired UserDao userDao) {
        this.userDao = userDao;
    }
    @Override
    public void add() {
        System.out.println("service...................");
        userDao.add();
    }
}
```

###### 使用两个注解，根据名称注入

```java
@Repository
public class UserDaoImpl implements UserDao {
    @Override
    public void add() {
        System.out.println("dao.......................");
    }
}
@Repository
public class UserRedisDaoImpl implements UserDao {
    @Override
    public void add() {
        System.out.println("dao redis.........");
    }
}
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    @Qualifier(value = "userRedisDaoImpl")
    private UserDao userDao;

    @Override
    public void add() {
        System.out.println("service...................");
        userDao.add();
    }
}
output:
controller.......
service...................
dao redis.........
```

##### @Resource注入

@Resource也可以完成属性注入，那他和@Autowired有什么区别？

- @Resource是JDK扩展包中的，也就是说属于JDK的一部分，所以该注解是标准注解，更加具有通用性；
- @Autowired是spring框架提出来的；
- @Resource注解默认根据名称装配byName，未指定name时，使用属性名作为name，通过name找不到时，会自动启动通过类型byType进行装配；
- @Autowired是默认是根据类型来装配byType，如果想根据名称装配需要配合@Qualifier一起使用；
- @Resource注解只能用在属性上和set方法上。

```java
@Controller
public class UserController {
    //根据名称注入
    @Resource(name = "myUserService")
    private UserService userService;
    public UserController( UserService userService) {
        this.userService = userService;
    }
    public void add(){
        System.out.println("controller.......");
        userService.add();
    }
}
@Repository("myUserDao")
public class UserDaoImpl implements UserDao {
    @Override
    public void add() {
        System.out.println("dao.......................");
    }
}
@Service("myUserService")
public class UserServiceImpl implements UserService{
    //不指定名称，根据属性名称进行注入
    @Resource
    private UserDao myUserDao;

    @Override
    public void add() {
        System.out.println("service...................");
        myUserDao.add();
    }
}

byType注入
@Controller
public class UserController {
    //根据名称注入
    @Resource
    private UserService userService;
    public UserController( UserService userService) {
        this.userService = userService;
    }

    public void add(){
        System.out.println("controller.......");
        userService.add();
    }
}
```

##### spring全注解开发

```java
@Configuration
@ComponentScan("com.atguigu.spring66.autowired")
public class SpringConfig {
}

public class AllTestAutoWired {
    public static void main(String[] args) {
        //加载配置类
        ApplicationContext  context = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserController userController = context.getBean(UserController.class);
        userController.add();

    }
}
```

#### 原理-手写IOC

```java
//反射回顾
public class TestCar {

    //获取Class对象
    @Test
    public void test1() throws Exception {
        //类名.class
        Class clazz1 = Car.class;
        //对象.getClass()
        Class clazz2 = new Car().getClass();
        //Class.forName("全路径")
        Class clazz3 = Class.forName("com.atguigu.reflect.Car");
        //实例化
        Car car = (Car) clazz3.getDeclaredConstructor().newInstance();
        System.out.println(car);
    }
    //获取构造方法
    @Test
    public void test2() throws Exception {
        Class clazz1 = Car.class;
        Constructor[] constructors = clazz1.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor.getName()+"::"+constructor.getParameterTypes().length);
        }
        //指定有参构造
        //public构造函数
        Constructor constructors1 = clazz1.getConstructor(String.class, int.class, String.class);
        Car car = (Car)constructors1.newInstance("A6L", 10, "red");
        System.out.println(car.toString());

        //private构造函数
        Constructor declaredConstructor = clazz1.getDeclaredConstructor(String.class, int.class, String.class);
        declaredConstructor.setAccessible(true);
        Car car1 = (Car)constructors1.newInstance("E300l", 5, "white");
        System.out.println(car1.toString());
    }

    //获取属性
    @Test
    public void test3() throws Exception {
        Class clazz = Car.class;
        Car car = (Car) clazz.getDeclaredConstructor().newInstance();
        //获取所有的public属性
        Field[] fields = clazz.getFields();
        //获取所有的属性
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.getName().equals("name")){
                //允许访问
                field.setAccessible(true);
                field.set(car,"Maybach");
            }
            System.out.println(field.getName());
            System.out.println(car.toString());
        }
    }
    //获取方法
    @Test
    public void test4() throws Exception {
        Car car = new Car("A8", 1, "pray");
        Class clazz = car.getClass();
        //public方法
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.getName().equals("toString")){
                method.setAccessible(true);
                String invoke = (String) method.invoke(car);
                System.out.println("toString方法执行了" + invoke);
            }
        }   
        //所有方法(private)
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (method.getName().equals("run")){
                method.setAccessible(true);
                method.invoke(car);
            }
        }
    }
}
```

```java
package com.atguigu.bean;

import com.atguigu.anno.Bean;
import com.atguigu.anno.Di;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AnnotationApplicationContext implements ApplicationContext {

    private Map<Class, Object> beans = new HashMap<>();
    private static String rootPath;
    @Override
    public Object getBean(Class clazz) {
        return beans.get(clazz) ;
    }

    //创建有参数构造，传递包路径，设置包扫描规则
    //当前包及其子包，哪个类有@Bean注解，把这个类通过反射实例化

    public AnnotationApplicationContext(String basePackage){
        try {
            String packagePath = basePackage.replaceAll("\\.","\\\\");
            Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(packagePath);
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                //System.out.println(filePath);
                //获取包前面路径的部分，进行字符串截取
                rootPath = filePath.substring(0,filePath.length()-packagePath.length());
                //System.out.println(rootPath);
                //包扫描
                loadBean(new File(filePath));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        loadDi();

    }


    //
    private void loadDi(){
        //实例化对象在map集合中，得到，每个对象value，每个对象的属性都获得
        Set<Map.Entry<Class, Object>> entries = beans.entrySet();
        for (Map.Entry<Class, Object> entry : entries) {
            Object object = entry.getValue();
            //获取class对象
            Class<?> clazz = object.getClass();
            //获取每个对象属性
            Field[] declaredFields = clazz.getDeclaredFields();
            //遍历得到每个属性
            for (Field field : declaredFields) {
                Di annotation = field.getAnnotation(Di.class);
                if (annotation != null) {
                    //私有属性 设置为可以设置
                    field.setAccessible(true);
                    //如果有Di注解，把对象进行设置（注入）
                    try {
                        //往对象中设置属性   这其实是对象的注入 也就是注入的是一个实例化的对象
                        field.set(object,beans.get(field.getType()));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        //判断属性是否有@Di标签
    }
    //包扫描 Bean实例化
    private void loadBean(File file) throws Exception {
        //判断当前是否是文件夹
        if(file.isDirectory()) {
            //获取文件夹里面所有的内容
            File[] childrenfiles = file.listFiles();
            //判断文件夹里面为空则返回
            if (childrenfiles == null || childrenfiles.length == 0)  { return;}
            //不为空就遍历文件夹所有内容
            for (File child : childrenfiles) {
                //对于遍历得到的每一个File对象，继续判断，如果还是文件夹，递归
                if (child.isDirectory()){ loadBean(child);}
                else {
                    //遍历得到的file对象不是文件夹，是文件
                    //得到包路径+类名称-字符串截取
                    String pathWithClass = child.getAbsolutePath().substring(rootPath.length() - 1);
                    //判断当前文件类型是否是.class文件
                    if (pathWithClass.endsWith(".class")) {
                        //如果是class类型，把路径中的\换成.  把.class去掉 得到 com.atguigu.service.UserServiceImpl
                        String allName = pathWithClass.replaceAll("\\\\", "\\.")
                                .replace(".class", "");

                        //判断类上面是否有注解@Bean，如果有则实例化
                        //获取类的Class
                        Class<?> clazz = Class.forName(allName);
                        //判断不是接口
                        if (!clazz.isInterface()) {
                            //判断类上面是否有注解@Bean
                            Bean annotation = clazz.getAnnotation(Bean.class);
                            if (annotation != null) {
                                //实例化
                                Object instance = clazz.getConstructor().newInstance();
                                //放入到map中
                                //当前类有接口  让接口的class作为map的key  没有接口则自己的class作为key
                                if (clazz.getInterfaces().length > 0) {
                                    beans.put(clazz.getInterfaces()[0],instance);
                                } else {
                                    beans.put(clazz,instance);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


package com.atguigu.bean;

public interface ApplicationContext {

    Object getBean(Class clazz);
}

package com.atguigu.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Bean {
}

package com.atguigu.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Di {
}


```

