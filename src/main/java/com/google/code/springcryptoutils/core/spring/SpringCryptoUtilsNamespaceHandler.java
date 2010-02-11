package com.google.code.springcryptoutils.core.spring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class SpringCryptoUtilsNamespaceHandler extends NamespaceHandlerSupport {

    public void init() {
        registerBeanDefinitionParser("keystore", new KeyStoreBeanDefinitionParser());
        registerBeanDefinitionParser("publicKey", new PublicKeyBeanDefinitionParser());
        registerBeanDefinitionParser("privateKey", new PrivateKeyBeanDefinitionParser());
        registerBeanDefinitionParser("keystoreRegistry", new KeyStoreRegistryBeanDefinitionParser());
        registerBeanDefinitionParser("publicKeyRegistryByAlias", new PublicKeyRegistryByAliasBeanDefinitionParser());
        registerBeanDefinitionParser("privateKeyRegistryByAlias", new PrivateKeyRegistryByAliasBeanDefinitionParser());
        registerBeanDefinitionParser("signer", new SignerBeanDefinitionParser());
        registerBeanDefinitionParser("verifier", new VerifierBeanDefinitionParser());
        registerBeanDefinitionParser("signerWithChoosersByAlias", new SignerWithChoosersByAliasBeanDefinitionParser());
        registerBeanDefinitionParser("verifierWithChoosersByAlias", new VerifierWithChoosersByAliasBeanDefinitionParser());
        registerBeanDefinitionParser("signerWithChooserByPrivateKeyId", new SignerWithChooserByPrivateKeyIdBeanDefinitionParser());
        registerBeanDefinitionParser("verifierWithChooserByPublicKeyId", new VerifierWithChooserByPublicKeyIdBeanDefinitionParser());
        registerBeanDefinitionParser("base64EncodedKeystore", new Base64EncodedKeyStoreBeanDefinitionParser());
        registerBeanDefinitionParser("base64EncodedSigner", new Base64EncodedSignerBeanDefinitionParser());
        registerBeanDefinitionParser("base64EncodedVerifier", new Base64EncodedVerifierBeanDefinitionParser());
        registerBeanDefinitionParser("base64EncodedSignerWithChoosersByAlias", new Base64EncodedSignerWithChoosersByAliasBeanDefinitionParser());
        registerBeanDefinitionParser("base64EncodedVerifierWithChoosersByAlias", new Base64EncodedVerifierWithChoosersByAliasBeanDefinitionParser());
        registerBeanDefinitionParser("base64EncodedSignerWithChooserByPrivateKeyId", new Base64EncodedSignerWithChooserByPrivateKeyIdBeanDefinitionParser());
        registerBeanDefinitionParser("base64EncodedVerifierWithChooserByPublicKeyId", new Base64EncodedVerifierWithChooserByPublicKeyIdBeanDefinitionParser());
        registerBeanDefinitionParser("symmetricKeyGenerator", new SymmetricKeyGeneratorBeanDefinitionParser());
    }

}
