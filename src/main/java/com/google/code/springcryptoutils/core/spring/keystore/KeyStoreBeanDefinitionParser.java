package com.google.code.springcryptoutils.core.spring.keystore;

import com.google.code.springcryptoutils.core.keystore.KeyStoreFactoryBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

public class KeyStoreBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class getBeanClass(Element element) {
        return KeyStoreFactoryBean.class;
    }

    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        bean.addPropertyValue("location", element.getAttribute("location"));
        bean.addPropertyValue("password", element.getAttribute("password"));
        bean.addPropertyValue("type", element.getAttribute("type"));
    }

}
