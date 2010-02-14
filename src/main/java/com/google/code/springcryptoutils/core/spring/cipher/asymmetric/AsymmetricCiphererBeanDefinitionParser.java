package com.google.code.springcryptoutils.core.spring.cipher.asymmetric;

import com.google.code.springcryptoutils.core.cipher.asymmetric.CiphererImpl;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

public class AsymmetricCiphererBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class getBeanClass(Element element) {
        return CiphererImpl.class;
    }

    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        bean.addPropertyValue("algorithm", element.getAttribute("algorithm"));
        bean.addPropertyValue("mode", element.getAttribute("mode"));
        bean.addPropertyReference("key", element.getAttribute("key-ref"));
    }

}