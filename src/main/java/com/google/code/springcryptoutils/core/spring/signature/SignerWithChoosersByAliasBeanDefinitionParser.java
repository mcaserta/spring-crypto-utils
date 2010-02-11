package com.google.code.springcryptoutils.core.spring.signature;

import com.google.code.springcryptoutils.core.signature.SignerWithChoosersByAliasImpl;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

public class SignerWithChoosersByAliasBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class getBeanClass(Element element) {
        return SignerWithChoosersByAliasImpl.class;
    }

    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        bean.addPropertyReference("privateKeyRegistryByAlias", element.getAttribute("privateKeyRegistryByAlias-ref"));
        bean.addPropertyValue("algorithm", element.getAttribute("algorithm"));
    }

}