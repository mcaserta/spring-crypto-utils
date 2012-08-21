package com.google.code.springcryptoutils.core.spring.cipher.symmetric;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

import com.google.code.springcryptoutils.core.cipher.symmetric.CiphererImpl;

public class SymmetricCiphererBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

	@Override
	protected Class getBeanClass(Element element) {
		return CiphererImpl.class;
	}

	@Override
	protected void doParse(Element element, BeanDefinitionBuilder bean) {
		bean.addPropertyValue("keyAlgorithm", element.getAttribute("keyAlgorithm"));
		bean.addPropertyValue("cipherAlgorithm", element.getAttribute("cipherAlgorithm"));
		bean.addPropertyValue("mode", element.getAttribute("mode"));
		bean.addPropertyValue("provider", element.getAttribute("provider"));
	}

}