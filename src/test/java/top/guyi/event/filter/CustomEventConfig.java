package top.guyi.event.filter;

import top.guyi.event.filter.conf.EventFilterConfig;

public class CustomEventConfig implements EventFilterConfig{

	@Override
	public Class<?> value() {
		return EventExecuteObject.class;
	}

	@Override
	public String before() {
		return "before_false";
	}

	@Override
	public String after() {
		return "after";
	}

}
