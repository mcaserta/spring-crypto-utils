package com.springcryptoutils.core.spring.keystore;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

import com.springcryptoutils.core.keystore.Base64EncodedKeyStoreFactoryBean;

public class Base64EncodedKeyStoreBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

	@Override
	protected Class getBeanClass(Element element) {
		return Base64EncodedKeyStoreFactoryBean.class;
	}

	@Override
	protected void doParse(Element element, BeanDefinitionBuilder bean) {
		bean.addPropertyValue("base64EncodedKeyStoreFile", element.getChildNodes().item(1).getTextContent());
		bean.addPropertyValue("password", element.getAttribute("password"));
		bean.addPropertyValue("type", element.getAttribute("type"));
		bean.addPropertyValue("provider", element.getAttribute("provider"));
	}

}