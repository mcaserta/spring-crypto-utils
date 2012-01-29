package com.google.code.springcryptoutils.core.spring;

import com.google.code.springcryptoutils.core.spring.cipher.asymmetric.AsymmetricCiphererBeanDefinitionParser;
import com.google.code.springcryptoutils.core.spring.cipher.asymmetric.AsymmetricCiphererWithChooserByKeyIdBeanDefinitionParser;
import com.google.code.springcryptoutils.core.spring.cipher.asymmetric.Base64EncodedAsymmetricCiphererBeanDefinitionParser;
import com.google.code.springcryptoutils.core.spring.cipher.asymmetric.Base64EncodedAsymmetricCiphererWithChooserByKeyIdBeanDefinitionParser;
import com.google.code.springcryptoutils.core.spring.cipher.symmetric.*;
import com.google.code.springcryptoutils.core.spring.digest.DigesterBeanDefinitionParser;
import com.google.code.springcryptoutils.core.spring.key.*;
import com.google.code.springcryptoutils.core.spring.keystore.Base64EncodedKeyStoreBeanDefinitionParser;
import com.google.code.springcryptoutils.core.spring.keystore.DefaultKeyStoreBeanDefinitionParser;
import com.google.code.springcryptoutils.core.spring.keystore.KeyStoreBeanDefinitionParser;
import com.google.code.springcryptoutils.core.spring.keystore.KeyStoreRegistryBeanDefinitionParser;
import com.google.code.springcryptoutils.core.spring.mac.Base64EncodedMacBeanDefinitionParser;
import com.google.code.springcryptoutils.core.spring.mac.MacBeanDefinitionParser;
import com.google.code.springcryptoutils.core.spring.signature.*;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class SpringCryptoUtilsNamespaceHandler extends NamespaceHandlerSupport {

    public void init() {
        // keystore
        registerBeanDefinitionParser("keystore", new KeyStoreBeanDefinitionParser());
        registerBeanDefinitionParser("defaultKeystore", new DefaultKeyStoreBeanDefinitionParser());
        registerBeanDefinitionParser("keystoreRegistry", new KeyStoreRegistryBeanDefinitionParser());
        registerBeanDefinitionParser("b64Keystore", new Base64EncodedKeyStoreBeanDefinitionParser());

        // key
        registerBeanDefinitionParser("publicKey", new PublicKeyBeanDefinitionParser());
        registerBeanDefinitionParser("privateKey", new PrivateKeyBeanDefinitionParser());
        registerBeanDefinitionParser("secretKey", new SecretKeyBeanDefinitionParser());
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

        // asymmetric ciphers
        registerBeanDefinitionParser("asymmetricCipherer", new AsymmetricCiphererBeanDefinitionParser());
        registerBeanDefinitionParser("asymmetricCiphererWithChooserByKeyId", new AsymmetricCiphererWithChooserByKeyIdBeanDefinitionParser());
        registerBeanDefinitionParser("b64AsymmetricCipherer", new Base64EncodedAsymmetricCiphererBeanDefinitionParser());
        registerBeanDefinitionParser("b64AsymmetricCiphererWithChooserByKeyId", new Base64EncodedAsymmetricCiphererWithChooserByKeyIdBeanDefinitionParser());

        // message digests
        registerBeanDefinitionParser("digester", new DigesterBeanDefinitionParser());

        // message authentication codes
        registerBeanDefinitionParser("mac", new MacBeanDefinitionParser());
        registerBeanDefinitionParser("b64Mac", new Base64EncodedMacBeanDefinitionParser());
    }

}
