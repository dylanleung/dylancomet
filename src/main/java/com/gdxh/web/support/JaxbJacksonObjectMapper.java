package com.gdxh.web.support;

import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;

public class JaxbJacksonObjectMapper extends ObjectMapper {

	public JaxbJacksonObjectMapper() {
		final AnnotationIntrospector introspector = new JaxbAnnotationIntrospector();
		super.getDeserializationConfig().withAnnotationIntrospector(
				introspector);
		super.getSerializationConfig().withAnnotationIntrospector(introspector);

		this.enableDefaultTypingAsProperty(DefaultTyping.JAVA_LANG_OBJECT,
				JsonTypeInfo.Id.CLASS.getDefaultPropertyName());
	}

}
