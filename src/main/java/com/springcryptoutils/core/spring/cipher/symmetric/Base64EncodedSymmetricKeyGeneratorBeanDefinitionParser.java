package com.springcryptoutils.core.spring.cipher.symmetric;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

import com.springcryptoutils.core.cipher.symmetric.Base64EncodedKeyGeneratorImpl;

public class Base64EncodedSymmetricKeyGeneratorBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

	@Override
	protected Class getBeanClass(Element element) {
		return Base64EncodedKeyGeneratorImpl.class;
	}

	@Override
	protected void doParse(Element element, BeanDefinitionBuilder bean) {
		bean.addPropertyValue("algorithm", element.getAttribute("algorithm"));
		bean.addPropertyValue("chunkOutput", element.getAttribute("chunkOutput"));
		bean.addPropertyValue("provider", element.getAttribute("provider"));
	}

}