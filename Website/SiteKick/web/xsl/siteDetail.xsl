<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : siteDetail.xsl
    Created on : July 19, 2020, 7:16 PM
    Author     : Gia Bảo Hoàng
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>
    <xsl:param name="siteName"/>
    <xsl:template match="/">
        <html>
            <body>
                <h1 class="color-green">
                    Site Name: <xsl:value-of select="//name[text()=$siteName]/ancestor::TechStack//name"/>
                </h1>
                <xsl:for-each select="//name[text()=$siteName]/ancestor::TechStack/technologyGroup">
                    <h3 class="color-blue">Group Name: <xsl:value-of select="groupName"/></h3>
                    <xsl:for-each select="technology">
                        <h4>Techname: <xsl:value-of select="techName"/></h4>
                        <h4>Description: </h4>
                        <p><xsl:value-of select="description"/></p>
                    </xsl:for-each>
                </xsl:for-each>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
