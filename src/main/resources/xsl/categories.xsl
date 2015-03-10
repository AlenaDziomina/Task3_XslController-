<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output method="html" />

	<xsl:template match="/">
		<head>
			<title>Categories list</title>
		</head>
		<H1>
			Categories:
		</H1>
		<xsl:apply-templates select="//Category" />
	</xsl:template>

	<xsl:template match="Category">
		<table>
			<tr>
				<a href="{@name}">
					<xsl:value-of select="@name" />
					(
					<xsl:value-of select="count(./Subcategory/good)" />
					)
				</a>
			</tr>
		</table>
	</xsl:template>

</xsl:stylesheet>