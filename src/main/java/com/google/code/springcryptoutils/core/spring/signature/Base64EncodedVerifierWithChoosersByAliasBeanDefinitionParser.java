package com.google.code.springcryptoutils.core.spring.signature;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

import com.google.code.springcryptoutils.core.signature.Base64EncodedVerifierWithChoosersByAliasImpl;

public class Base64EncodedVerifierWithChoosersByAliasBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

	@Override
	protected Class getBeanClass(Element element) {
		return Base64EncodedVerifierWithChoosersByAliasImpl.class;
	}

	@Override
	protected void doParse(Element element, BeanDefinitionBuilder bean) {
		bean.addPropertyReference("publicKeyRegistryByAlias", element.getAttribute("publicKeyRegistryByAlias-ref"));
		bean.addPropertyValue("algorithm", element.getAttribute("algorithm"));
		bean.addPropertyValue("charsetName", element.getAttribute("charset"));
		bean.addPropertyValue("provider", element.getAttribute("provider"));
	}

}