package com.google.code.springcryptoutils.core.spring;

import com.google.code.springcryptoutils.core.cipher.symmetric.KeyGeneratorImpl;
import com.google.code.springcryptoutils.core.signature.Base64EncodedVerifierWithChooserByPublicKeyIdImpl;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

public class SymmetricKeyGeneratorBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class getBeanClass(Element element) {
        return KeyGeneratorImpl.class;
    }

    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        bean.addPropertyValue("algorithm", element.getAttribute("algorithm"));
    }

}