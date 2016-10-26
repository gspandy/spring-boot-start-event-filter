package top.guyi.event.filter.handler;

import java.lang.reflect.Method;

/**
 * @author 古逸
 * 拦截器上下文
 */
public class EventFilterContext {

	
	/**
	 * 被拦截对象，即拦截服务类
	 */
	static ThreadLocal<Object> service = new ThreadLocal<Object>();
	
	/**
	 * 拦截方法执行参数
	 */
	static ThreadLocal<Object[]> args = new ThreadLocal<Object[]>();
	
	/**
	 * 拦截方法
	 */
	static ThreadLocal<Method> method = new ThreadLocal<Method>();
	
	/**
	 * 获取拦截对象
	 * @return 拦截对象
	 */
	public static Object getService(){
		return service.get();
	}
	
	/**
	 * 获取参数
	 * @return 参数
	 */
	public static Object[] getArgs(){
		return args.get();
	}
	
	/**
	 * 获取方法
	 * @return 方法
	 */
	public static Method getMethod(){
		return method.get();
	}
	
}
