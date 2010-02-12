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
        registerBeanDefinitionParser("b64Keystore", new Base64EncodedKeyStoreBeanDefinitionParser());

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
        registerBeanDefinitionParser("b64Signer", new Base64EncodedSignerBeanDefinitionParser());
        registerBeanDefinitionParser("b64Verifier", new Base64EncodedVerifierBeanDefinitionParser());
        registerBeanDefinitionParser("b64SignerWithChoosersByAlias", new Base64EncodedSignerWithChoosersByAliasBeanDefinitionParser());
        registerBeanDefinitionParser("b64VerifierWithChoosersByAlias", new Base64EncodedVerifierWithChoosersByAliasBeanDefinitionParser());
        registerBeanDefinitionParser("b64SignerWithChooserByPrivateKeyId", new Base64EncodedSignerWithChooserByPrivateKeyIdBeanDefinitionParser());
        registerBeanDefinitionParser("b64VerifierWithChooserByPublicKeyId", new Base64EncodedVerifierWithChooserByPublicKeyIdBeanDefinitionParser());

        // symmetric ciphers
        registerBeanDefinitionParser("symmetricKeyGenerator", new SymmetricKeyGeneratorBeanDefinitionParser());
        registerBeanDefinitionParser("symmetricCipherer", new SymmetricCiphererBeanDefinitionParser());
        registerBeanDefinitionParser("symmetricCiphererWithStaticKey", new SymmetricCiphererWithStaticKeyBeanDefinitionParser());
        registerBeanDefinitionParser("b64SymmetricKeyGenerator", new Base64EncodedSymmetricKeyGeneratorBeanDefinitionParser());
        registerBeanDefinitionParser("b64SymmetricCipherer", new Base64EncodedSymmetricCiphererBeanDefinitionParser());
        registerBeanDefinitionParser("b64SymmetricCiphererWithStaticKey", new Base64EncodedSymmetricCiphererWithStaticKeyBeanDefinitionParser());
    }

}
