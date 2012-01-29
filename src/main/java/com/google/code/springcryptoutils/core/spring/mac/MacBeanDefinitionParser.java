package com.google.code.springcryptoutils.core.spring.mac;

import com.google.code.springcryptoutils.core.mac.MacImpl;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

public class MacBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class getBeanClass(Element element) {
        return MacImpl.class;
    }

    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        bean.addPropertyReference("secretKey", element.getAttribute("secretKey-ref"));
        bean.addPropertyValue("algorithm", element.getAttribute("algorithm"));
    }

}