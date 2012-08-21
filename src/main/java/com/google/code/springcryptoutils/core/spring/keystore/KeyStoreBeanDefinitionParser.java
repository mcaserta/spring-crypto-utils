package com.google.code.springcryptoutils.core.spring.keystore;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

import com.google.code.springcryptoutils.core.keystore.KeyStoreFactoryBean;

public class KeyStoreBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

	@Override
	protected Class getBeanClass(Element element) {
		return KeyStoreFactoryBean.class;
	}

	@Override
	protected void doParse(Element element, BeanDefinitionBuilder bean) {
		bean.addPropertyValue("location", element.getAttribute("location"));
		bean.addPropertyValue("password", element.getAttribute("password"));
		bean.addPropertyValue("type", element.getAttribute("type"));
		bean.addPropertyValue("provider", element.getAttribute("provider"));
	}

}
