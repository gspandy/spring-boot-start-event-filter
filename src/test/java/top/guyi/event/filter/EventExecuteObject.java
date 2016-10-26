package top.guyi.event.filter;

import org.springframework.stereotype.Component;

import top.guyi.event.filter.handler.EventFilterContext;

@Component
public class EventExecuteObject {

	public boolean before_false(){
		
		System.out.println(EventFilterContext.getMethod()+",被拦截");
		
		return false;
	}
	
	public boolean before(){
		
		System.out.println(EventFilterContext.getMethod()+",开始执行");
		
		return true;
	}
	
	
	public void after(){
		System.out.println(EventFilterContext.getMethod()+",执行执行完毕");
	}
}
