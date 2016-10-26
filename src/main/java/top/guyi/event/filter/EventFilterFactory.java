package top.guyi.event.filter;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;

import net.sf.cglib.proxy.Enhancer;
import top.guyi.event.filter.annotation.EventFilter;
import top.guyi.event.filter.conf.EventFilterConfig;
import top.guyi.event.filter.conf.MethodHandlerConf;
import top.guyi.event.filter.handler.EventHandler;
import top.guyi.event.filter.utils.MethodUtils;

/**
 * @author 古逸
 * 拦截器工厂
 */
public class EventFilterFactory implements FactoryBean<Object>{

	private Class<?> handlerClass;
	private Object handler;
	private ApplicationContext context;
	
	private static Enhancer enhancer = new Enhancer();
	
	/**
	 * 注入被拦截对象
	 * @param handler 被拦截对象
	 */
	public void setHandler(Object handler) {
		this.handler = handler;
	}
	/**
	 * 注入Spring上下文
	 * @param context Spring上下文
	 */
	public void setContext(ApplicationContext context) {
		this.context = context;
	}
	/**
	 * 注入被拦截对象的Class
	 * @param handlerClass 被拦截对象的Class
	 */
	public void setHandlerClass(Class<?> handlerClass) {
		this.handlerClass = handlerClass;
	}
	
	
	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.FactoryBean#getObject()
	 */
	@Override
	public synchronized Object getObject() throws Exception {
		
		Map<Method,MethodHandlerConf> mhcs = new HashMap<Method,MethodHandlerConf>();
		Method[] ms = handler.getClass().getMethods();
		for (Method method : ms) {
			EventFilter an = MethodUtils.getAnnotation(method,EventFilter.class);
			if(an != null){
				
				MethodHandlerConf mhc = new MethodHandlerConf();
				mhc.setMethod(method);
				
				if(an.config() != EventFilterConfig.class){
					EventFilterConfig config = (EventFilterConfig) this.getObject(an.config());
					
					mhc.setFilter(this.getObject(config.value()));
					mhc.setBefore(config.before());
					mhc.setAfter(config.after());
				}else{
					if(an.value() == Class.class){
						throw new Exception("this EventFilter annotation attribute must be not null");
					}
					mhc.setFilter(this.getObject(an.value()));
					mhc.setBefore(an.before());
					mhc.setAfter(an.after());
				}
				
				try{
					mhc.init();
					mhcs.put(method, mhc);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		
		EventHandler eh = new EventHandler(mhcs,this.handler);
		enhancer.setSuperclass(this.handler.getClass());
		enhancer.setCallback(eh);
		
		return enhancer.create();
	}
	
	private Object getObject(Class<?> clazz) throws InstantiationException, IllegalAccessException{
		Object obj = null;
		try{
			obj = context.getBean(clazz);
		}catch(Exception e){
			obj = clazz.newInstance();
		}
		
		return obj;
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.FactoryBean#getObjectType()
	 */
	@Override
	public Class<?> getObjectType() {
		return this.handlerClass;
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.FactoryBean#isSingleton()
	 */
	@Override
	public boolean isSingleton() {
		return true;
	}
	
}
