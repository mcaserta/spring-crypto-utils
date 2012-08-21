package com.google.code.springcryptoutils.core.spring.signature;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

import com.google.code.springcryptoutils.core.signature.SignerWithChoosersByAliasImpl;

public class SignerWithChoosersByAliasBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

	@Override
	protected Class getBeanClass(Element element) {
		return SignerWithChoosersByAliasImpl.class;
	}

	@Override
	protected void doParse(Element element, BeanDefinitionBuilder bean) {
		bean.addPropertyReference("privateKeyRegistryByAlias", element.getAttribute("privateKeyRegistryByAlias-ref"));
		bean.addPropertyValue("algorithm", element.getAttribute("algorithm"));
		bean.addPropertyValue("provider", element.getAttribute("provider"));
	}

}