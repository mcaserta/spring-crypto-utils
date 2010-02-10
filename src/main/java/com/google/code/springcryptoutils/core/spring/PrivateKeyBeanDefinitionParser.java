package com.google.code.springcryptoutils.core.spring;

import com.google.code.springcryptoutils.core.key.PrivateKeyFactoryBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

public class PrivateKeyBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class getBeanClass(Element element) {
        return PrivateKeyFactoryBean.class;
    }

    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        bean.addPropertyReference("keystore", element.getAttribute("keystore-ref"));
        bean.addPropertyValue("alias", element.getAttribute("alias"));
        bean.addPropertyValue("password", element.getAttribute("password"));
    }

}