package com.google.code.springcryptoutils.core.spring.cipher.symmetric;

import com.google.code.springcryptoutils.core.cipher.symmetric.Base64EncodedCiphererImpl;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

public class Base64EncodedSymmetricCiphererBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class getBeanClass(Element element) {
        return Base64EncodedCiphererImpl.class;
    }

    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        bean.addPropertyValue("keyAlgorithm", element.getAttribute("keyAlgorithm"));
        bean.addPropertyValue("cipherAlgorithm", element.getAttribute("cipherAlgorithm"));
        bean.addPropertyValue("mode", element.getAttribute("mode"));
        bean.addPropertyValue("chunkOutput", element.getAttribute("chunkOutput"));
        bean.addPropertyValue("charsetName", element.getAttribute("charset"));
    }

}