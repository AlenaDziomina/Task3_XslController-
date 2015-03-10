package com.epam.testapp.util.source;

/**
 * Created by Alena_Grouk on 2/19/2015.
 */
public enum TemplateSourceName {

    XML_SOURCE {
        @Override
        public String getCurrentSourceName() {
            return PRODUCT_XML_SOURCE;
        }
    },
    CATEGORY {
        @Override
        public String getCurrentSourceName() {
            return CATEGORY_XSL_SOURCE;
        }
    },
    SUBCATEGORY {
        @Override
        public String getCurrentSourceName() {
            return SUBCATEGORY_XSL_SOURCE;
        }
    },
    PRODUCT_LIST {
        @Override
        public String getCurrentSourceName() {
            return PRODUCT_LIST_XSL_SOURCE;
        }
    },
    ADD_PRODUCT {
        @Override
        public String getCurrentSourceName() {
            return ADD_PRODUCT_XSL_SOURCE;
        }
    };

    private static final String CATEGORY_XSL_SOURCE = "/xsl/categories.xsl";
    private static final String SUBCATEGORY_XSL_SOURCE = "/xsl/subcategories.xsl";
    private static final String PRODUCT_LIST_XSL_SOURCE = "/xsl/goods.xsl";
    private static final String ADD_PRODUCT_XSL_SOURCE = "/xsl/add-product.xsl";
    private static final String PRODUCT_XML_SOURCE = "/xml/products.xml";


    public String getCurrentSourceName() {
        return null;
    }

}
