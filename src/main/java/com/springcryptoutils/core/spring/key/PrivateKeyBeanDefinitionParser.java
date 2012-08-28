/*
 * Copyright 2012 Mirko Caserta
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this software except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.springcryptoutils.core.spring.key;

import com.springcryptoutils.core.key.PrivateKeyFactoryBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

public class PrivateKeyBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class getBeanClass(Element element) {
        return PrivateKeyFactoryBean.class;
    }

    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        bean.addPropertyReference("keystore", element.getAttribute("keystore-ref"));
        bean.addPropertyValue("alias", element.getAttribute("alias"));
        bean.addPropertyValue("password", element.getAttribute("password"));
    }

}