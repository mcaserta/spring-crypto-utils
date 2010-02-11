package com.google.code.springcryptoutils.core.spring;

import com.google.code.springcryptoutils.core.signature.Base64EncodedSignerWithChooserByPrivateKeyIdImpl;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

public class Base64EncodedSignerWithChooserByPrivateKeyIdBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class getBeanClass(Element element) {
        return Base64EncodedSignerWithChooserByPrivateKeyIdImpl.class;
    }

    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        bean.addPropertyReference("privateKeyMap", element.getAttribute("privateKeyMap-ref"));
        bean.addPropertyValue("algorithm", element.getAttribute("algorithm"));
        bean.addPropertyValue("charsetName", element.getAttribute("charsetName"));
    }

}