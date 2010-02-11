package com.google.code.springcryptoutils.core.spring;

import com.google.code.springcryptoutils.core.signature.Base64EncodedVerifierWithChoosersByAliasImpl;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

public class Base64EncodedVerifierWithChoosersByAliasBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class getBeanClass(Element element) {
        return Base64EncodedVerifierWithChoosersByAliasImpl.class;
    }

    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        bean.addPropertyReference("publicKeyRegistryByAlias", element.getAttribute("publicKeyRegistryByAlias-ref"));
        bean.addPropertyValue("algorithm", element.getAttribute("algorithm"));
    }

}