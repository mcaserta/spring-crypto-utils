package com.google.code.springcryptoutils.core.spring;

import com.google.code.springcryptoutils.core.key.PublicKeyRegistryImpl;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

public class PublicKeyRegistryBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class getBeanClass(Element element) {
        return PublicKeyRegistryImpl.class;
    }

    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        bean.addPropertyReference("keyStoreRegistry", element.getAttribute("keystoreRegistry-ref"));
    }

}