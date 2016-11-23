package top.guyi.event.filter.handler;

import java.lang.reflect.Method;
import java.util.Map;

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
	 * 附加内容
	 */
	static ThreadLocal<Map<String,Object>> attach = new ThreadLocal<Map<String,Object>>();
	
	
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
	
	
	/**
	 * 设置附加属性值
	 * @param name
	 * @param value
	 */
	public static void setAttach(String name,Object value){
		attach.get().put(name, value);
	}
	
	/**
	 * 获取附加属性值
	 * @param name
	 * @return
	 */
	public static Object getAttach(String name){
		return attach.get().get(name);
	}
	
	/**
	 * 设置返回值，方法执行被拦截后生效
	 * @param value
	 */
	public static void setResult(Object value){
		attach.get().put("filter-server-result",value);
	}
	
	/**
	 * 获取拦截器返回值
	 * @return
	 */
	public static Object getResult(){
		return attach.get().get("filter-server-result");
	}
}
