package com.google.code.springcryptoutils.core.spring.keystore;

import com.google.code.springcryptoutils.core.keystore.KeyStoreRegistryImpl;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

public class KeyStoreRegistryBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class getBeanClass(Element element) {
        return KeyStoreRegistryImpl.class;
    }

    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        bean.addPropertyReference("entries", element.getAttribute("entries-ref"));
    }

}