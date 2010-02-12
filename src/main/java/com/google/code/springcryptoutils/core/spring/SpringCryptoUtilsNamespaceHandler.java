package com.google.code.springcryptoutils.core.spring;

import com.google.code.springcryptoutils.core.spring.cipher.symmetric.*;
import com.google.code.springcryptoutils.core.spring.key.PrivateKeyBeanDefinitionParser;
import com.google.code.springcryptoutils.core.spring.key.PrivateKeyRegistryByAliasBeanDefinitionParser;
import com.google.code.springcryptoutils.core.spring.key.PublicKeyBeanDefinitionParser;
import com.google.code.springcryptoutils.core.spring.key.PublicKeyRegistryByAliasBeanDefinitionParser;
import com.google.code.springcryptoutils.core.spring.keystore.Base64EncodedKeyStoreBeanDefinitionParser;
import com.google.code.springcryptoutils.core.spring.keystore.KeyStoreBeanDefinitionParser;
import com.google.code.springcryptoutils.core.spring.keystore.KeyStoreRegistryBeanDefinitionParser;
import com.google.code.springcryptoutils.core.spring.signature.*;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class SpringCryptoUtilsNamespaceHandler extends NamespaceHandlerSupport {

    public void init() {
        // keystore
        registerBeanDefinitionParser("keystore", new KeyStoreBeanDefinitionParser());
        registerBeanDefinitionParser("keystoreRegistry", new KeyStoreRegistryBeanDefinitionParser());
        registerBeanDefinitionParser("base64EncodedKeystore", new Base64EncodedKeyStoreBeanDefinitionParser());

        // key
        registerBeanDefinitionParser("publicKey", new PublicKeyBeanDefinitionParser());
        registerBeanDefinitionParser("privateKey", new PrivateKeyBeanDefinitionParser());
        registerBeanDefinitionParser("publicKeyRegistryByAlias", new PublicKeyRegistryByAliasBeanDefinitionParser());
        registerBeanDefinitionParser("privateKeyRegistryByAlias", new PrivateKeyRegistryByAliasBeanDefinitionParser());

        // signature
        registerBeanDefinitionParser("signer", new SignerBeanDefinitionParser());
        registerBeanDefinitionParser("verifier", new VerifierBeanDefinitionParser());
        registerBeanDefinitionParser("signerWithChoosersByAlias", new SignerWithChoosersByAliasBeanDefinitionParser());
        registerBeanDefinitionParser("verifierWithChoosersByAlias", new VerifierWithChoosersByAliasBeanDefinitionParser());
        registerBeanDefinitionParser("signerWithChooserByPrivateKeyId", new SignerWithChooserByPrivateKeyIdBeanDefinitionParser());
        registerBeanDefinitionParser("verifierWithChooserByPublicKeyId", new VerifierWithChooserByPublicKeyIdBeanDefinitionParser());
        registerBeanDefinitionParser("base64EncodedSigner", new Base64EncodedSignerBeanDefinitionParser());
        registerBeanDefinitionParser("base64EncodedVerifier", new Base64EncodedVerifierBeanDefinitionParser());
        registerBeanDefinitionParser("base64EncodedSignerWithChoosersByAlias", new Base64EncodedSignerWithChoosersByAliasBeanDefinitionParser());
        registerBeanDefinitionParser("base64EncodedVerifierWithChoosersByAlias", new Base64EncodedVerifierWithChoosersByAliasBeanDefinitionParser());
        registerBeanDefinitionParser("base64EncodedSignerWithChooserByPrivateKeyId", new Base64EncodedSignerWithChooserByPrivateKeyIdBeanDefinitionParser());
        registerBeanDefinitionParser("base64EncodedVerifierWithChooserByPublicKeyId", new Base64EncodedVerifierWithChooserByPublicKeyIdBeanDefinitionParser());

        // symmetric ciphers
        registerBeanDefinitionParser("symmetricKeyGenerator", new SymmetricKeyGeneratorBeanDefinitionParser());
        registerBeanDefinitionParser("base64EncodedSymmetricKeyGenerator", new Base64EncodedSymmetricKeyGeneratorBeanDefinitionParser());
        registerBeanDefinitionParser("symmetricCipherer", new SymmetricCiphererBeanDefinitionParser());
        registerBeanDefinitionParser("symmetricCiphererWithStaticKey", new SymmetricCiphererWithStaticKeyBeanDefinitionParser());
        registerBeanDefinitionParser("base64EncodedSymmetricCipherer", new Base64EncodedSymmetricCiphererBeanDefinitionParser());
    }

}
