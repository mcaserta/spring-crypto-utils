package com.springcryptoutils.core.spring.certificate;

import com.springcryptoutils.core.certificate.CertificateFactoryBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

/**
 * @author Chad Johnston (cjohnston@megatome.com)
 */
public class CertificateBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class getBeanClass(Element element) {
        return CertificateFactoryBean.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        bean.addPropertyReference("keystore", element.getAttribute("keystore-ref"));
        bean.addPropertyValue("alias", element.getAttribute("alias"));
    }
}
