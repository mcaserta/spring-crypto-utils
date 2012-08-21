package com.google.code.springcryptoutils.core.spring.cipher.asymmetric;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

import com.google.code.springcryptoutils.core.cipher.asymmetric.CiphererImpl;

public class AsymmetricCiphererBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

	@Override
	protected Class getBeanClass(Element element) {
		return CiphererImpl.class;
	}

	@Override
	protected void doParse(Element element, BeanDefinitionBuilder bean) {
		bean.addPropertyValue("algorithm", element.getAttribute("algorithm"));
		bean.addPropertyValue("mode", element.getAttribute("mode"));
		bean.addPropertyReference("key", element.getAttribute("key-ref"));
		bean.addPropertyValue("provider", element.getAttribute("provider"));
	}

}