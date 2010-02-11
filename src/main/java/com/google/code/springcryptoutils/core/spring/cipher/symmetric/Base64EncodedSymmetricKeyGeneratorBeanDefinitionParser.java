package com.google.code.springcryptoutils.core.spring.cipher.symmetric;

import com.google.code.springcryptoutils.core.cipher.symmetric.Base64EncodedKeyGeneratorImpl;
import com.google.code.springcryptoutils.core.cipher.symmetric.KeyGeneratorImpl;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

public class Base64EncodedSymmetricKeyGeneratorBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class getBeanClass(Element element) {
        return Base64EncodedKeyGeneratorImpl.class;
    }

    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        bean.addPropertyValue("algorithm", element.getAttribute("algorithm"));
        bean.addPropertyValue("chunkOutput", element.getAttribute("chunkOutput"));
    }

}