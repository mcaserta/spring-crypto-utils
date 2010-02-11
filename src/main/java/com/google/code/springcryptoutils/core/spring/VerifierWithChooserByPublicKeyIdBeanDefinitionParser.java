package com.google.code.springcryptoutils.core.spring;

import com.google.code.springcryptoutils.core.signature.VerifierWithChooserByPublicKeyIdImpl;
import com.google.code.springcryptoutils.core.signature.VerifierWithChoosersByAliasImpl;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

public class VerifierWithChooserByPublicKeyIdBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class getBeanClass(Element element) {
        return VerifierWithChooserByPublicKeyIdImpl.class;
    }

    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        bean.addPropertyReference("publicKeyMap", element.getAttribute("publicKeyMap-ref"));
        bean.addPropertyValue("algorithm", element.getAttribute("algorithm"));
    }

}