package com.google.code.springcryptoutils.core.spring.cipher.asymmetric;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

import com.google.code.springcryptoutils.core.cipher.asymmetric.Base64EncodedCiphererImpl;

public class Base64EncodedAsymmetricCiphererBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

	@Override
	protected Class getBeanClass(Element element) {
		return Base64EncodedCiphererImpl.class;
	}

	@Override
	protected void doParse(Element element, BeanDefinitionBuilder bean) {
		bean.addPropertyValue("algorithm", element.getAttribute("algorithm"));
		bean.addPropertyValue("mode", element.getAttribute("mode"));
		bean.addPropertyValue("charsetName", element.getAttribute("charset"));
		bean.addPropertyReference("key", element.getAttribute("key-ref"));
		bean.addPropertyValue("provider", element.getAttribute("provider"));
	}

}