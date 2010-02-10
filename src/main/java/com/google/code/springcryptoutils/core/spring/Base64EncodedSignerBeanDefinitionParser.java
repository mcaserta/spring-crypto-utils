package com.google.code.springcryptoutils.core.spring;

import com.google.code.springcryptoutils.core.signature.Base64EncodedSignerImpl;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

public class Base64EncodedSignerBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class getBeanClass(Element element) {
        return Base64EncodedSignerImpl.class;
    }

    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        bean.addPropertyReference("privateKey", element.getAttribute("privateKey-ref"));
        bean.addPropertyValue("algorithm", element.getAttribute("algorithm"));
    }

}