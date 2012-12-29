package com.gdxh.web.config;

import org.atmosphere.cpr.BroadcasterFactory;
import org.codehaus.jackson.map.MapperConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.PropertyNamingStrategy;
import org.codehaus.jackson.map.introspect.AnnotatedField;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.util.StringUtils;

@Configuration
@Import(WebAppConfiguration.class)
@ComponentScan(basePackages = "com.gdxh.web", excludeFilters = @ComponentScan.Filter(Configuration.class))
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ComponentConfiguration {

	@Bean
	public BroadcasterFactory broadcasterFactory() {
		return BroadcasterFactory.getDefault();
	}

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setPropertyNamingStrategy(new PropertyNamingStrategy() {
			@Override
			public String nameForField(MapperConfig<?> config,
					AnnotatedField field, String defaultName) {
				return StringUtils.uncapitalize(field.getName());
			}
		});
		return mapper;
	}

}
