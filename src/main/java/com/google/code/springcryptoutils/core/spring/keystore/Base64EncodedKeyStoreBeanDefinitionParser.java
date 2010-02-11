package com.google.code.springcryptoutils.core.spring.keystore;

import com.google.code.springcryptoutils.core.keystore.Base64EncodedKeyStoreFactoryBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

public class Base64EncodedKeyStoreBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class getBeanClass(Element element) {
        return Base64EncodedKeyStoreFactoryBean.class;
    }

    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        bean.addPropertyValue("base64EncodedKeyStoreFile", element.getChildNodes().item(1).getTextContent());
        bean.addPropertyValue("password", element.getAttribute("password"));
        bean.addPropertyValue("type", element.getAttribute("type"));
    }

}