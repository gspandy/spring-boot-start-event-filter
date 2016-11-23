package top.guyi.event.filter.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import org.springframework.core.annotation.AnnotationUtils;

/**
 * @author 古逸
 * 方法工具
 */
public class MethodUtils {

	/**
	 * 根据注解获取方法
	 * @param clazz 要获取方法的类
	 * @param annotationClass 注解的Class
	 * @return 方法集合
	 */
	public static List<Method> getMethods(Class<?> clazz,Class<? extends Annotation> annotationClass){
		
		List<Method> list = new LinkedList<Method>();
		
		Method[] ms = clazz.getMethods();
		for (Method method : ms) {
			if(getAnnotation(method, annotationClass)!=null){
				list.add(method);
			}
		}
		
		return list;
	}
	
	/**
	 * 获取方法上的注解
	 * @param method 要获取注解的方法
	 * @param annotationClass 要获取注解的class
	 * @return 注解
	 */
	public static <T extends Annotation> T getAnnotation(Method method,Class<T> annotationClass){
		
		T a = method.getAnnotation(annotationClass);
		if(a != null){
			return a;
		}
		
		Annotation[] as = method.getDeclaredAnnotations();
		for (Annotation an : as) {
			a = getAnnotation(an.annotationType(), annotationClass);
			if(a != null && a.annotationType() == annotationClass){
				return a;
			}
		}
		
		return null;
	}
	
	/**
	 * 获取类上注解
	 * @param clazz 要获取注解的类
	 * @param annotationClass 要获取注解的class
	 * @return 注解
	 */
	public static <T extends Annotation> T getAnnotation(Class<?> clazz,Class<T> annotationClass){
		
		T a = clazz.getAnnotation(annotationClass);
		if(a != null){
			return a;
		}
		
		Annotation[] as = clazz.getDeclaredAnnotations();
		for (Annotation annotation : as) {
			if(!AnnotationUtils.isInJavaLangAnnotationPackage(annotation)){
				a = getAnnotation(annotation.annotationType(), annotationClass);
				if(a != null && a.annotationType() == annotationClass){
					return a;
				}
			}
		}
		
		Class<?> superClass = clazz.getSuperclass();
		if(superClass != null){
			return getAnnotation(superClass, annotationClass);
		}
		
		return null;
	}
	
}
