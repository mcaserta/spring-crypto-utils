package com.google.code.springcryptoutils.core.spring.signature;

import com.google.code.springcryptoutils.core.signature.VerifierImpl;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

public class VerifierBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class getBeanClass(Element element) {
        return VerifierImpl.class;
    }

    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        bean.addPropertyReference("publicKey", element.getAttribute("publicKey-ref"));
        bean.addPropertyValue("algorithm", element.getAttribute("algorithm"));
    }

}