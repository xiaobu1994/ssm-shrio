一、Pointcut、Advice、Advisor的区别
（1）切入点  Pointcut
在介绍Pointcut之前，有必要先介绍  Join  Point（连接点）概念。
连接点：程序运行中的某个阶段点，比如方法的调用、异常的抛出等。比如方法doSome();
Pointcut是JoinPoint的集合，它是程序中需要注入Advice 的位置的集合，指明Advice要在什么样的条件下才能被触发。
org.springframework.aop.Pointcut接口用来指定到特定的类和方法。

（2）通知Advice
 它是某个连接点所采用的处理逻辑，也就是向连接点注入的代码。例如：输出的日志信息   就是一个Advice

（3）Advisor顾问
Advisor是Pointcut和Advice的配置器，它包括Pointcut和Advice，是将Advice注入程序中Pointcut位置的代码

二、DefaultAdvisorAutoProxyCreator的作用
DefaultAdvisorAutoProxyCreator实现spring的自动代理 
当ApplicationContext读如所有的Bean配置信息后,这个类将扫描上下文,寻找所有的Advistor(一个Advisor是一个切入点和一个通知的组成),将这些Advisor应用到所有符合切入点的Bean中
三、spring两种代理方式
1、若目标对象实现了若干接口，spring使用JDK的java.lang.reflect.Proxy类代理。 
优点：因为有接口，所以使系统更加松耦合 
缺点：为每一个目标类创建接口
2、若目标对象没有实现任何接口，spring使用CGLIB库生成目标对象的子类。 
优点：因为代理类与目标类是继承关系，所以不需要有接口的存在。 
缺点：因为没有使用接口，所以系统的耦合性没有使用JDK的动态代理好。

四、@Autowired和@Qualifier的区别
@Autowired默认是根据类型进行注入的，因此如果有多个类型一样的Bean候选者，则需要限定其中一个候选者，
否则将抛出异常  
@Qualifier限定描述符除了能根据名字进行注入，更能进行更细粒度的控制如何选择候选者
  @Bean(name = "shiroRealm")
    public ShiroRealm shiroRealm(@Qualifier("credentialsMatcher") RetryLimitCredentialsMatcher matcher) {
        ShiroRealm shiroRealm = new ShiroRealm();
        shiroRealm.setCredentialsMatcher(credentialsMatcher());
        return shiroRealm;
    }
五、 @DependsOn("lifecycleBeanPostProcessor")
使用了depends-on的时候，依赖他人的bean是先于被依赖bean销毁的 
被依赖的bean要先于依赖他人的bean先初始化

六 、@Order(-1)设置类的加载顺序为最高
@Configuration
@Order(-1)
public class ShiroConfig{}

七、@GetMapping(value ={"/index","/"})的含义
/**
    * @author xiaobu
    * @date 2018/12/18 10:48
    * @return org.springframework.web.servlet.ModelAndView
    * @descprition   表示index或者空字符串进入 index页面
    * @version 1.0
    */
    @GetMapping(value ={"/index","/"})
    public ModelAndView index() {
        return ResultUtil.view("index");
    }
    
    
    
八、redis
--打开个客户端连接
redis-cli.exe -h 127.0.0.1 -p 6379 

--密码连接认证
auth xiaobu1994 
