package com.google.code.springcryptoutils.core.spring.digest;

import com.google.code.springcryptoutils.core.digest.DigesterImpl;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

public class DigesterBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class getBeanClass(Element element) {
        return DigesterImpl.class;
    }

    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        bean.addPropertyValue("algorithm", element.getAttribute("algorithm"));
        bean.addPropertyValue("outputMode", element.getAttribute("outputMode"));
        bean.addPropertyValue("charsetName", element.getAttribute("charset"));
    }

}