package com.google.code.springcryptoutils.core.spring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class SpringCryptoUtilsNamespaceHandler extends NamespaceHandlerSupport {

    public void init() {
        registerBeanDefinitionParser("keystore", new KeyStoreBeanDefinitionParser());
        registerBeanDefinitionParser("keystoreRegistry", new KeyStoreRegistryBeanDefinitionParser());
    }

}
