package com.google.code.springcryptoutils.core.spring.cipher.symmetric;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

import com.google.code.springcryptoutils.core.cipher.symmetric.KeyGeneratorImpl;

public class SymmetricKeyGeneratorBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

	@Override
	protected Class getBeanClass(Element element) {
		return KeyGeneratorImpl.class;
	}

	@Override
	protected void doParse(Element element, BeanDefinitionBuilder bean) {
		bean.addPropertyValue("algorithm", element.getAttribute("algorithm"));
		bean.addPropertyValue("provider", element.getAttribute("provider"));
	}

}