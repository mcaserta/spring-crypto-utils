package com.google.code.springcryptoutils.core.spring.digest;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

import com.google.code.springcryptoutils.core.digest.DigesterImpl;

public class DigesterBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

	@Override
	protected Class getBeanClass(Element element) {
		return DigesterImpl.class;
	}

	@Override
	protected void doParse(Element element, BeanDefinitionBuilder bean) {
		bean.addPropertyValue("algorithm", element.getAttribute("algorithm"));
		bean.addPropertyValue("outputMode", element.getAttribute("outputMode"));
		bean.addPropertyValue("charsetName", element.getAttribute("charset"));
		bean.addPropertyValue("provider", element.getAttribute("provider"));
	}

}