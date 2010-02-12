package com.google.code.springcryptoutils.core.spring.cipher.symmetric;

import com.google.code.springcryptoutils.core.cipher.symmetric.CiphererWithStaticKeyImpl;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

public class SymmetricCiphererWithStaticKeyBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class getBeanClass(Element element) {
        return CiphererWithStaticKeyImpl.class;
    }

    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        bean.addPropertyValue("keyAlgorithm", element.getAttribute("keyAlgorithm"));
        bean.addPropertyValue("cipherAlgorithm", element.getAttribute("cipherAlgorithm"));
        bean.addPropertyValue("key", element.getAttribute("key"));
        bean.addPropertyValue("initializationVector", element.getAttribute("initializationVector"));
        bean.addPropertyValue("mode", element.getAttribute("mode"));
    }

}