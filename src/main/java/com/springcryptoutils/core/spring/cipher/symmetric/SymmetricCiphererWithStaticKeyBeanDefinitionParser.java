package com.springcryptoutils.core.spring.cipher.symmetric;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

import com.springcryptoutils.core.cipher.symmetric.CiphererWithStaticKeyImpl;

public class SymmetricCiphererWithStaticKeyBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

	@Override
	protected Class getBeanClass(Element element) {
		return CiphererWithStaticKeyImpl.class;
	}

	@Override
	protected void doParse(Element element, BeanDefinitionBuilder bean) {
		bean.addPropertyValue("keyAlgorithm", element.getAttribute("keyAlgorithm"));
		bean.addPropertyValue("cipherAlgorithm", element.getAttribute("cipherAlgorithm"));
		bean.addPropertyValue("key", element.getAttribute("key"));
		bean.addPropertyValue("initializationVector", element.getAttribute("initializationVector"));
		bean.addPropertyValue("mode", element.getAttribute("mode"));
		bean.addPropertyValue("provider", element.getAttribute("provider"));
	}

}