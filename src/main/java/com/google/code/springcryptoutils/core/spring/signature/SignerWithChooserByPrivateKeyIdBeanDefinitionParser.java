package com.google.code.springcryptoutils.core.spring.signature;

import com.google.code.springcryptoutils.core.signature.SignerWithChooserByPrivateKeyIdImpl;
import com.google.code.springcryptoutils.core.signature.SignerWithChoosersByAliasImpl;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

public class SignerWithChooserByPrivateKeyIdBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class getBeanClass(Element element) {
        return SignerWithChooserByPrivateKeyIdImpl.class;
    }

    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        bean.addPropertyReference("privateKeyMap", element.getAttribute("privateKeyMap-ref"));
        bean.addPropertyValue("algorithm", element.getAttribute("algorithm"));
    }

}