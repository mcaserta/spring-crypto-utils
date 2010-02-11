package com.google.code.springcryptoutils.core.spring.signature;

import com.google.code.springcryptoutils.core.signature.SignerImpl;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

public class SignerBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class getBeanClass(Element element) {
        return SignerImpl.class;
    }

    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        bean.addPropertyReference("privateKey", element.getAttribute("privateKey-ref"));
        bean.addPropertyValue("algorithm", element.getAttribute("algorithm"));
    }

}