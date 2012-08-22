package com.springcryptoutils.core.spring.key;

import com.springcryptoutils.core.key.PrivateKeyRegistryByAliasImpl;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

public class PrivateKeyRegistryByAliasBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class getBeanClass(Element element) {
        return PrivateKeyRegistryByAliasImpl.class;
    }

    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        bean.addPropertyReference("keyStoreRegistry", element.getAttribute("keystoreRegistry-ref"));
    }

}