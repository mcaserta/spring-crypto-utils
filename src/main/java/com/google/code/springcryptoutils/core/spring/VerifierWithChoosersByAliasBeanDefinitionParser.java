package com.google.code.springcryptoutils.core.spring;

import com.google.code.springcryptoutils.core.signature.VerifierWithChoosersByAliasImpl;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

public class VerifierWithChoosersByAliasBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class getBeanClass(Element element) {
        return VerifierWithChoosersByAliasImpl.class;
    }

    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        bean.addPropertyReference("publicKeyRegistryByAlias", element.getAttribute("publicKeyRegistryByAlias-ref"));
        bean.addPropertyValue("algorithm", element.getAttribute("algorithm"));
    }

}