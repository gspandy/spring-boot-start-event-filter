package top.guyi.event.filter.handler;

import java.lang.reflect.Method;
import java.util.Map;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import top.guyi.event.filter.conf.MethodHandlerConf;

/**
 * @author 古逸
 * 拦截处理对象
 */
public class EventHandler implements MethodInterceptor{

	private Map<Method,MethodHandlerConf> mhcs;
	private Object handler;
	
	
	
	/**
	 * 唯一构造器
	 * @param mhcs 拦截配置
	 * @param handler 被拦截对象
	 */
	public EventHandler(Map<Method, MethodHandlerConf> mhcs, Object handler) {
		super();
		this.mhcs = mhcs;
		this.handler = handler;
	}

	/* (non-Javadoc)
	 * @see net.sf.cglib.proxy.MethodInterceptor#intercept(java.lang.Object, java.lang.reflect.Method, java.lang.Object[], net.sf.cglib.proxy.MethodProxy)
	 */
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		
		MethodHandlerConf mhc = mhcs.get(method);
		if(mhc == null){
			return method.invoke(this.handler, args);
		}
		
		EventFilterContext.service.set(this.handler);
		EventFilterContext.args.set(args);
		EventFilterContext.method.set(method);
		
		boolean isrun = true;
		if(mhc.getBeforeMethod() != null){
			Object res = mhc.getBeforeMethod().invoke(mhc.getFilter());
			if(res != null && res instanceof Boolean){
				isrun = (boolean)res;
			}
		}
		
		Object result = null;
		//判断是否被阻断
		if(isrun){
			result = method.invoke(handler, args);
		}
		
		if(mhc.getAfterMethod() != null){
			mhc.getAfterMethod().invoke(mhc.getFilter());
		}
		
		return result;
	}

}
