package top.guyi.event.filter;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import top.guyi.event.filter.annotation.EventService;

/**
 * @author 古逸
 * spring-boot 自动配置类
 */
@Configuration
@ConditionalOnClass(EventFilterFactory.class)
public class EventFilterAutoConfigure implements InitializingBean{

	@Autowired
	private ApplicationContext context;
	
	private DefaultListableBeanFactory beanFactory;
	
	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		
		beanFactory = (DefaultListableBeanFactory)context.getAutowireCapableBeanFactory();
		
		Map<String, Object> filters = context.getBeansWithAnnotation(EventService.class);
		for (Entry<String, Object> e : filters.entrySet()) {
			registerEventHandler(e);
		}
		
	}

	private void registerEventHandler(Entry<String, Object> e) {
		BeanDefinitionBuilder bean = BeanDefinitionBuilder.genericBeanDefinition(EventFilterFactory.class);
		bean.addPropertyValue("context",this.context);
		bean.addPropertyValue("handler", e.getValue());
		bean.addPropertyValue("handlerClass", e.getValue().getClass());
		
		beanFactory.removeBeanDefinition(e.getKey());
		beanFactory.registerBeanDefinition(e.getKey(), bean.getBeanDefinition());
	}
	
	

	
	
}
