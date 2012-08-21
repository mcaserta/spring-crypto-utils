package com.google.code.springcryptoutils.core.spring.cipher.asymmetric;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

import com.google.code.springcryptoutils.core.cipher.asymmetric.CiphererWithChooserByKeyIdImpl;

public class AsymmetricCiphererWithChooserByKeyIdBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

	@Override
	protected Class getBeanClass(Element element) {
		return CiphererWithChooserByKeyIdImpl.class;
	}

	@Override
	protected void doParse(Element element, BeanDefinitionBuilder bean) {
		bean.addPropertyValue("algorithm", element.getAttribute("algorithm"));
		bean.addPropertyValue("mode", element.getAttribute("mode"));
		bean.addPropertyReference("keyMap", element.getAttribute("keyMap-ref"));
		bean.addPropertyValue("provider", element.getAttribute("provider"));
	}

}