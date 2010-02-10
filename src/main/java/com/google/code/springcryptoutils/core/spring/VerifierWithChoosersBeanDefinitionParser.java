package com.google.code.springcryptoutils.core.spring;

import com.google.code.springcryptoutils.core.signature.VerifierWithChoosersImpl;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

public class VerifierWithChoosersBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class getBeanClass(Element element) {
        return VerifierWithChoosersImpl.class;
    }

    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        bean.addPropertyReference("publicKeyRegistry", element.getAttribute("publicKeyRegistry-ref"));
        bean.addPropertyValue("algorithm", element.getAttribute("algorithm"));
    }

}