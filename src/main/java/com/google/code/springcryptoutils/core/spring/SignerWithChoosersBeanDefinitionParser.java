package com.google.code.springcryptoutils.core.spring;

import com.google.code.springcryptoutils.core.signature.SignerWithChoosersImpl;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

public class SignerWithChoosersBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class getBeanClass(Element element) {
        return SignerWithChoosersImpl.class;
    }

    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        bean.addPropertyReference("privateKeyRegistry", element.getAttribute("privateKeyRegistry-ref"));
        bean.addPropertyValue("algorithm", element.getAttribute("algorithm"));
    }

}