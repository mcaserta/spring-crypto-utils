package com.google.code.springcryptoutils.core.spring;

import com.google.code.springcryptoutils.core.key.PrivateKeyRegistryImpl;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

public class PrivateKeyRegistryBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class getBeanClass(Element element) {
        return PrivateKeyRegistryImpl.class;
    }

    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        bean.addPropertyReference("keyStoreRegistry", element.getAttribute("keystoreRegistry-ref"));
    }

}