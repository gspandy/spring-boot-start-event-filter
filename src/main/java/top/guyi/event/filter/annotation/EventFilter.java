package top.guyi.event.filter.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import top.guyi.event.filter.conf.EventFilterConfig;

/**
 * @author 古逸
 * 用于方法，指定拦截配置
 */
@Target({ElementType.METHOD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface EventFilter {

	/**
	 * 事件处理对象的Class
	 * @return 事件处理对象的Class
	 */
	public Class<?> value() default Class.class;
	
	/**
	 * 在拦截方法执行前执行的方法
	 * @return 在拦截方法执行前执行的方法
	 */
	public String before() default "";
	
	/**
	 * 在拦截方法执行后执行的方法
	 * @return 在拦截方法执行后执行的方法
	 */
	public String after() default "";
	
	/**
	 * <pre>
	 * 自定义事件的配置对象
	 * 当它存在值时，优先使用其配置
	 * </pre>
	 * @return 自定义事件的配置对象
	 */
	public Class<? extends EventFilterConfig> config() default EventFilterConfig.class;
	
}
