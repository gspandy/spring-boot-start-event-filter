package top.guyi.event.filter.conf;

import java.lang.reflect.Method;

import org.springframework.util.StringUtils;

/**
 * @author 古逸
 * 拦截方法的配置对象
 */
public class MethodHandlerConf {

	private Object filter;
	
	private Method method;
	
	private String before;
	
	private String after;

	private Method beforeMethod;
	private Method afterMethod;
	
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}
	public String getAfter() {
		return after;
	}
	public void setAfter(String after) {
		this.after = after;
	}
	public void setFilter(Object filter) {
		this.filter = filter;
	}
	public void setBefore(String before) {
		this.before = before;
	}
	
	
	public Method getBeforeMethod() {
		return beforeMethod;
	}
	public Method getAfterMethod() {
		return afterMethod;
	}
	public Object getFilter() {
		return filter;
	}
	
	
	/**
	 * 初始化配置
	 * @throws Exception 在EventFilter注解中,brfore和after都为空,value为空时,都会抛出异常
	 */
	public void init() throws Exception{
		
		if(filter == null){
			throw new ClassNotFoundException("event filter must be not null");
		}
		
		if(StringUtils.isEmpty(after) && StringUtils.isEmpty(before)){
			throw new Exception("this EventFilte`s config must have before or after");
		}
		
		if(!StringUtils.isEmpty(before)){
			beforeMethod = filter.getClass().getMethod(before);
		}
		
		if(!StringUtils.isEmpty(after)){
			afterMethod = filter.getClass().getMethod(after);
		}
	}
	
}
