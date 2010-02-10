package com.google.code.springcryptoutils.core.spring;

import com.google.code.springcryptoutils.core.key.PublicKeyFactoryBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

public class PublicKeyBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class getBeanClass(Element element) {
        return PublicKeyFactoryBean.class;
    }

    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        bean.addPropertyReference("keystore", element.getAttribute("keystore-ref"));
        bean.addPropertyValue("alias", element.getAttribute("alias"));
    }

}