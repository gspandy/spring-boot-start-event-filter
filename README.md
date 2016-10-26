# spring-boot-start-event-filter

  基于spring-boot的一个事件拦截，通过自定义事件，指定在这之前或之后触发的方法（类似AOP）。并支持根据自定义逻辑拦截方法执行

# 基本使用
	
	* 在需要拦截Method的类上使用注解 [EventService](https://github.com/guyimaplegithub/spring-boot-start-event-filter/blob/master/src/main/java/top/guyi/event/filter/annotation/EventService.java)，标识这是一个启用事件拦截的对象
	
  * 在需要拦截的Method上使用注解 [EventFilter](https://github.com/guyimaplegithub/spring-boot-start-event-filter/blob/master/src/main/java/top/guyi/event/filter/annotation/EventFilter.java)，配置参数
	
  * 具体使用，请参见 [TestService](https://github.com/guyimaplegithub/spring-boot-start-event-filter/blob/master/src/test/java/top/guyi/event/filter/TestService.java)


# 事件处理器
	
	* 普通类中的普通方法
	
  * 方法需要为无参方法
	
  * 当方法指定before时触发，需要有boolean类型的返回值。如果返回false,则Method的执行被拦截
	
  * 具体使用，请参见 [EventExecuteObject](https://github.com/guyimaplegithub/spring-boot-start-event-filter/blob/master/src/test/java/top/guyi/event/filter/EventExecuteObject.java)

# 创建无需配置即可使用的自定义事件

	## 注解方式
		
		* 创建一个注解类，继承EventFilter
		
    * 具体使用，请参见 [CustomEventFilter](https://github.com/guyimaplegithub/spring-boot-start-event-filter/blob/master/src/test/java/top/guyi/event/filter/CustomEventFilter.java)
		
	## Java类方式
		
		* 创建一个普通类，实现接口 [EventFilterConfig](https://github.com/guyimaplegithub/spring-boot-start-event-filter/blob/master/src/main/java/top/guyi/event/filter/conf/EventFilterConfig.java)
		
    * 具体使用，请参见 [CustomEventConfig](https://github.com/guyimaplegithub/spring-boot-start-event-filter/blob/master/src/test/java/top/guyi/event/filter/CustomEventConfig.java)
		
	
