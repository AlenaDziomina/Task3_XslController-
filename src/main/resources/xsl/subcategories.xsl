<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output method="html" />

	<xsl:param name="categoryName" />

	<xsl:template match="/">
		<head>
			<title>Subcategories list</title>
		</head>
		<H1>
			Category:
			<xsl:value-of select=" $categoryName" />
		</H1>
		<xsl:apply-templates select="//Category/Subcategory" />
		<a href="/">Back</a>
	</xsl:template>

	<xsl:template match="Category/Subcategory">
		<xsl:if test="../@name=$categoryName">
			<table>
				<tr>
					<a
						href="{$categoryName}/{@name}">
						<xsl:value-of select="@name"></xsl:value-of>
						(
						<xsl:value-of select="count(./good)" />
						)
					</a>
				</tr>
			</table>
		</xsl:if>
	</xsl:template>

</xsl:stylesheet>