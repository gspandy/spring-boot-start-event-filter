package top.guyi.event.filter.conf;

/**
 * @author 古逸
 * 用于配置自定义事件
 */
public interface EventFilterConfig {

	/**
	 * 事件拦截器Class
	 * @return Class
	 */
	public Class<?> value();
	
	/**
	 * 之前执行的方法
	 * @return 方法名
	 */
	public String before();
	
	/**
	 * 之后执行的方法
	 * @return 方法名
	 */
	public String after();
	
}
