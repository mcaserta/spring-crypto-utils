package com.google.code.springcryptoutils.core.spring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class SpringCryptoUtilsNamespaceHandler extends NamespaceHandlerSupport {

    public void init() {
        registerBeanDefinitionParser("keystore", new KeyStoreBeanDefinitionParser());
        registerBeanDefinitionParser("publicKey", new PublicKeyBeanDefinitionParser());
        registerBeanDefinitionParser("privateKey", new PrivateKeyBeanDefinitionParser());
        registerBeanDefinitionParser("keystoreRegistry", new KeyStoreRegistryBeanDefinitionParser());
        registerBeanDefinitionParser("publicKeyRegistry", new PublicKeyRegistryBeanDefinitionParser());
        registerBeanDefinitionParser("privateKeyRegistry", new PrivateKeyRegistryBeanDefinitionParser());
        registerBeanDefinitionParser("signer", new SignerBeanDefinitionParser());
        registerBeanDefinitionParser("verifier", new VerifierBeanDefinitionParser());
        registerBeanDefinitionParser("signerWithChoosers", new SignerWithChoosersBeanDefinitionParser());
        registerBeanDefinitionParser("verifierWithChoosers", new VerifierWithChoosersBeanDefinitionParser());
        registerBeanDefinitionParser("base64EncodedSigner", new Base64EncodedSignerBeanDefinitionParser());
        registerBeanDefinitionParser("base64EncodedVerifier", new Base64EncodedVerifierBeanDefinitionParser());
        registerBeanDefinitionParser("base64EncodedSignerWithChoosers", new Base64EncodedSignerWithChoosersBeanDefinitionParser());
        registerBeanDefinitionParser("base64EncodedVerifierWithChoosers", new Base64EncodedVerifierWithChoosersBeanDefinitionParser());
    }

}
