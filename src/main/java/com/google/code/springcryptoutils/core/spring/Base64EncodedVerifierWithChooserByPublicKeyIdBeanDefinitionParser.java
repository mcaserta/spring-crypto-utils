package com.google.code.springcryptoutils.core.spring;

import com.google.code.springcryptoutils.core.signature.Base64EncodedVerifierWithChooserByPublicKeyIdImpl;
import com.google.code.springcryptoutils.core.signature.Base64EncodedVerifierWithChoosersByAliasImpl;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

public class Base64EncodedVerifierWithChooserByPublicKeyIdBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class getBeanClass(Element element) {
        return Base64EncodedVerifierWithChooserByPublicKeyIdImpl.class;
    }

    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        bean.addPropertyReference("publicKeyMap", element.getAttribute("publicKeyMap-ref"));
        bean.addPropertyValue("algorithm", element.getAttribute("algorithm"));
        bean.addPropertyValue("charsetName", element.getAttribute("charsetName"));
    }

}