package com.springcryptoutils.core.spring.certificate;

import com.springcryptoutils.core.certificate.CertificateRegistryByAliasImpl;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

/**
 * @author Chad Johnston (cjohnston@megatome.com)
 */
public class CertificateRegistryByAliasBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class getBeanClass(Element element) {
        return CertificateRegistryByAliasImpl.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        bean.addPropertyReference("keyStoreRegistry", element.getAttribute("keystoreRegistry-ref"));
    }
}
