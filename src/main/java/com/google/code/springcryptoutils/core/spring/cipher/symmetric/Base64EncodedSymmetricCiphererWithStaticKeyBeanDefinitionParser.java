package com.google.code.springcryptoutils.core.spring.cipher.symmetric;

import com.google.code.springcryptoutils.core.cipher.symmetric.Base64EncodedCiphererWithStaticKeyImpl;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

public class Base64EncodedSymmetricCiphererWithStaticKeyBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class getBeanClass(Element element) {
        return Base64EncodedCiphererWithStaticKeyImpl.class;
    }

    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        bean.addPropertyValue("key", element.getAttribute("key"));
        bean.addPropertyValue("initializationVector", element.getAttribute("initializationVector"));
        bean.addPropertyValue("keyAlgorithm", element.getAttribute("keyAlgorithm"));
        bean.addPropertyValue("cipherAlgorithm", element.getAttribute("cipherAlgorithm"));
        bean.addPropertyValue("mode", element.getAttribute("mode"));
        bean.addPropertyValue("chunkOutput", element.getAttribute("chunkOutput"));
        bean.addPropertyValue("charsetName", element.getAttribute("charset"));
    }

}