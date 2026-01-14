# SpringMVC

### 1.  什么是MVC

MVC是一种软件架构思想，将软件按照模型、视图、控制器来划分。

###### M：model，模型层，指的是工程中的javaBean，作用是来处理数据

javaBean分两类：

- 一类是实体类Bean：专门存储业务数据的
- 一类是业务处理Bean：指的是Service或dao对象，专门处理业务逻辑和数据访问

###### V：view，视图层，指的是工程中的html或jsp等页面，作用是与用户进行交互，展示数据

###### C：controller，控制层，指的是工程中的Servlet，作用是接收请求和响应浏览器

###### MVC的工作流程：

用户通过view发送请求到服务器，在服务器中请求被controller接收，controller调用相应的的model层处理请求，处理完毕后将结果返回到controller，controller再根据请求处理的结果找到相应的view，渲染后返回给浏览器。

###### 配置web.xml 扩展配置方式

可通过init-param标签设置SpringMVC配置文件的位置和名称，通过load-on-startup标签设置SpringMVC前端控制器DispatcherServelet的初始化时间

###### 总结

浏览器发送请求，若请求地址符合前端控制器的url-pattern，该请求就会被前端控制器DispatcherServlet处理。前端控制器会读取SpringMVC的核心配置文件，通过扫描组件找到控制器，将请求地址和控制器中的@RequestMapping注解的value属性值进行匹配，若匹配成功，该注解所标识的控制器方法就是处理请求的方法。处理请求的方法需要返回一个字符串类型的视图名称，该视图名称会被视图解析器解析，加上前缀和后缀组成视图的路径，通过Thymeleaf对视图进行渲染，最终转发到视图所对应的页面。

###### @RequestMapping注解的位置
- @RequestMapping标识一个类：设置映射请求的请求路径的初始信息
- @RequestMapping标识一个方法：设置映射请求路径的具体信息

###### @RequestParam 
是将请求参数和控制器方法的形参创建映射关系 ，@RequestParam 一共有三个属性
- value：指定为参数赋值的请求参数的参数名
- required：设置是否必须传输此请求参数，默认值为true。若设置为true，则当前请求必须传输value所指定的请求参数，若没有传输该请求参数，且没有defaultValue属性，则页面会报400错误：
- defaultValue：不管required属性值，当value所指定的请求参数没有传输时，默认使用该属性值

###### Model、ModelMap、Map的关系
model,modelmap,map类型的参数本质上都是BindingAwareModelMap类型的

```java
import java.util.LinkedHashMap;

public interface Model {}
public class ModelMap extends LinkedHashMap<String,Object>{}
public class ExtendedModelMap extends ModelMap implements Model{}
public class BindingAwareModelMap extends ExtendedModelMap{}
```
###### 转发和重定向的区别

| 特性       | 转发（Forward）                          | 重定向（Redirect）                           |
| ---------- | ---------------------------------------- | -------------------------------------------- |
| 发生位置   | 服务器端                                 | 客户端/浏览器端                              |
| 请求次数   | 1次                                      | 2次                                          |
| 浏览器地址 | 不变（显示第一次请求的URL）              | 改变（显示跳转后的新URL）                    |
| 数据共享   | 共享request域中的数据                    | 不共享request域中的数据（数据丢失）          |
| 跳转范围   | 只能在当前web应用内跳转                  | 可以跳转到任何URL（包括google，baidu等外部） |
| 核心代码   | request.getRequestDispatcher().forward() | response.sendRedirect()                      |
| 效率       | 较高（服务器内部直接流转）               | 较低（多了一次网络往返）                     |

**总结**

- 查询/读取数据（Get）：通常是采用转发（forword），为了带数据方便
- 增删改（Post/Put/Delete）：处理完之后，必须使用重定向，为了防止重复操作
