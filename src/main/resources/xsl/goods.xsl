<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output method="html" />

	<xsl:param name="categoryName" />
	<xsl:param name="subcategoryName" />


	<xsl:template match="/">
		<head>
			<title>Goods list</title>
		</head>
		<xsl:apply-templates select="//Category/Subcategory" />
	</xsl:template>

	<xsl:template match="Subcategory">
		<xsl:if test="@name=$subcategoryName and ../@name=$categoryName">

			<H1>
				Category:
				<xsl:value-of select="../@name" />
			</H1>
			<H2>
				Subcategory:
				<xsl:value-of select="@name"></xsl:value-of>
			</H2>
			<table>
				<tr>
					<td>
						<h4>Producer</h4>
					</td>
					<td>
						<h4>Model</h4>
					</td>
					<td>
						<h4>Date of issue</h4>
					</td>
					<td>
						<h4>Color</h4>
					</td>
					<td>
						<h4>Price</h4>
					</td>
				</tr>
				<xsl:apply-templates select="good" />
				<tr>
					<td>
                        <a href="/{$categoryName}">Back</a>
					</td>
					<td>
                        <a href="{$subcategoryName}/add">Add Product</a>
					</td>
				</tr>
			</table>
		</xsl:if>
	</xsl:template>


	<xsl:template match="Category/Subcategory/good">
		<tr>
			<td>
				<xsl:value-of select="producer" />
			</td>
			<td>
				<xsl:value-of select="model" />
			</td>
			<td>
				<xsl:value-of select="date_of_issue" />
			</td>
			<td>
				<xsl:value-of select="color" />
			</td>
			<td>
				<xsl:choose>
					<xsl:when test="not-in-stock">
						Not in stock
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="price" />
					</xsl:otherwise>
				</xsl:choose>
			</td>
		</tr>
	</xsl:template>

</xsl:stylesheet>