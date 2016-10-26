package top.guyi.event.filter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import top.guyi.event.filter.annotation.EventFilter;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@EventFilter(value=EventExecuteObject.class,after="after",before="before")
public @interface CustomEventFilter {

}
