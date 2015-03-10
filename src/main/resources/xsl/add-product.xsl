<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xalan="http://xml.apache.org/xslt"
                xmlns:validator="xalan://com.epam.testapp.util.validation.ProductValidator"
                xmlns:product="xalan://com.epam.testapp.util.validation.ProductValidationForm"
                version="1.0"
                extension-element-prefixes="validator">

    <xsl:output method="html"/>

    <xsl:param name="categoryName" />
    <xsl:param name="subcategoryName" />
    <xsl:param name="validator" />
    <xsl:param name="product" />

    <xsl:output method="html" />

    <xsl:template match="/">
        <xsl:choose>
            <xsl:when test="validator:validateProduct($validator, $product)">
                <xsl:copy>
                    <xsl:apply-templates />
                </xsl:copy>
            </xsl:when>
            <xsl:otherwise>

                <xsl:variable name="producerErrorMessage" select="product:getProducerErrorMessage($product)" />
                <xsl:variable name="modelErrorMessage" select="product:getModelErrorMessage($product)" />
                <xsl:variable name="dateErrorMessage" select="product:getDateErrorMessage($product)" />
                <xsl:variable name="colorErrorMessage" select="product:getColorErrorMessage($product)" />
                <xsl:variable name="priceErrorMessage" select="product:getPriceErrorMessage($product)" />

                <html>
                    <head>
                        <title>Add</title>
                        <script type="text/javascript" src="javascript/script.js" />
                    </head>
                    <body>
                        <xsl:apply-templates select="//Category/Subcategory" />
                        <h3>Add product</h3>
                        <form action="save">
                            <input type="hidden" name="command" value="addGoods" />
                            <input type="hidden" name="categoryName" value="{$categoryName}" />
                            <input type="hidden" name="subcategoryName" value="{$subcategoryName}" />
                            <table>
                                <tr>
                                    <td>
                                        <label>Producer</label>
                                    </td>
                                    <td>
                                        <input type="text" name="producer" value="{product:getProducer($product)}" class="textField" />
                                    </td>
                                    <td></td>
                                    <td>
                                        <xsl:if test="$producerErrorMessage">
                                            <xsl:value-of select="$producerErrorMessage" />
                                        </xsl:if>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Model</label>
                                    </td>
                                    <td>
                                        <input type="text" name="model" value="{product:getModel($product)}" class="textField" />
                                    </td>
                                    <td></td>
                                    <td>
                                        <xsl:if test="$modelErrorMessage">
                                            <xsl:value-of select="$modelErrorMessage" />
                                        </xsl:if>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Date</label>
                                    </td>
                                    <td>
                                        <input type="text" name="date" value="{product:getDate($product)}" class="textField" />
                                    </td>
                                    <td></td>
                                    <td>
                                        <xsl:if test="$dateErrorMessage">
                                            <xsl:value-of select="$dateErrorMessage" />
                                        </xsl:if>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Color</label>
                                    </td>
                                    <td>
                                        <input type="text" name="color" value="{product:getColor($product)}" class="textField" />
                                    </td>
                                    <td></td>
                                    <td>
                                        <xsl:if test="$colorErrorMessage">
                                            <xsl:value-of select="$colorErrorMessage" />
                                        </xsl:if>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Price</label>
                                    </td>
                                    <xsl:choose>
                                        <xsl:when test="product:isNotInStock($product)">
                                            <td>
                                                <input type="text" name="price" value="{product:getPrice($product)}" class="textField" disabled="true" />
                                            </td>
                                            <td>
                                                or not in stock
                                                <input type="checkbox" name="notinstock" onclick="test(this,price)" checked="true" />
                                            </td>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <td>
                                                <input type="text" name="price" value="{product:getPrice($product)}" class="textField" disabled="false" />
                                            </td>
                                            <td>
                                                or not in stock
                                                <input type="checkbox" name="notinstock" onclick="test(this,price)" checked="false" />
                                            </td>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                    <td>
                                        <xsl:if test="$priceErrorMessage">
                                            <xsl:value-of select="$priceErrorMessage" />
                                        </xsl:if>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <a href="/{$categoryName}">Back</a>
                                    </td>
                                    <td>
                                        <input type="submit">Save</input>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </body>
                </html>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>


</xsl:stylesheet>
