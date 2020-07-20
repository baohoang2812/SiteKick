<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : sites.xsl
    Created on : July 19, 2020, 7:18 PM
    Author     : Gia Bảo Hoàng
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    version="2.0">
    <xsl:output method="html"/>
    <xsl:template match="/">
        <html>
            <body>
                <xsl:apply-templates/>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="TechStacks">
        
        <xsl:if test="TechStack">
            <table id="site-table">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Url</th>
                    </tr>
                </thead>
                <tbody>
                    <xsl:for-each select="TechStack" >
                        <tr>
                            <td>
                                <xsl:number level="single" count="TechStack"/>
                            </td>
                            <td>
                                <xsl:variable name="siteName" select="site/name" />
                                <xsl:variable name="siteDetail" select="'siteDetail.jsp?siteName='" />
                                <a>
                                    <xsl:attribute name="href">
                                        <xsl:value-of select="concat($siteDetail,$siteName)" />
                                    </xsl:attribute>
                                    <xsl:value-of select="$siteName"/>
                                </a>
                            </td>
                        </tr>
                    </xsl:for-each>
                </tbody>
            </table>
        </xsl:if>
        <xsl:if test="not(TechStack)">
            <h4>No Record Found</h4>
        </xsl:if>

    </xsl:template>
</xsl:stylesheet>
