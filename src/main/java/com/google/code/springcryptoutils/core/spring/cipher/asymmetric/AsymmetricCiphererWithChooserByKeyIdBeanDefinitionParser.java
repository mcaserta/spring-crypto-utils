package com.google.code.springcryptoutils.core.spring.cipher.asymmetric;

import com.google.code.springcryptoutils.core.cipher.asymmetric.CiphererImpl;
import com.google.code.springcryptoutils.core.cipher.asymmetric.CiphererWithChooserByKeyIdImpl;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

public class AsymmetricCiphererWithChooserByKeyIdBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class getBeanClass(Element element) {
        return CiphererWithChooserByKeyIdImpl.class;
    }

    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        bean.addPropertyValue("algorithm", element.getAttribute("algorithm"));
        bean.addPropertyValue("mode", element.getAttribute("mode"));
        bean.addPropertyReference("keyMap", element.getAttribute("keyMap-ref"));
    }

}