package com.springcryptoutils.core.spring.keystore;

import com.springcryptoutils.core.keystore.DefaultKeyStoreFactoryBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

public class DefaultKeyStoreBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class getBeanClass(Element element) {
        return DefaultKeyStoreFactoryBean.class;
    }

}
