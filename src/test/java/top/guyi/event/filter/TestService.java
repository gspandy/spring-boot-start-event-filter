package top.guyi.event.filter;

import org.springframework.stereotype.Component;

import top.guyi.event.filter.annotation.EventFilter;
import top.guyi.event.filter.annotation.EventService;

@Component
@EventService
public class TestService {

	@EventFilter(value=EventExecuteObject.class,after="after")
	public void test(){
		System.out.println("this is TestService test method");
	}
	
	@CustomEventFilter
	public void test1(){
		System.out.println("this is TestService test1 method");
	}
	
	@EventFilter(config=CustomEventConfig.class)
	public void test2(){
		System.out.println("this is TestService test2 method");
	}
	
}
