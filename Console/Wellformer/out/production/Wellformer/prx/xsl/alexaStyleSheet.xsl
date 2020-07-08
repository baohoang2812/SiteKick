<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : alexaStyleSheet.xsl
    Created on : July 7, 2020, 9:35 PM
    Author     : Gia Bảo Hoàng
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns="http://www.sitekick.com"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                version="1.0">
    <xsl:output method="xml" omit-xml-declaration="no"/>
    <xsl:param name="categoryName" />
    <xsl:template match="/">
        <site>
            <url>
                <xsl:value-of select="//*[contains(@class,'smalltitle')]//strong"/>
            </url>
            <globalRank>
                <xsl:value-of select="translate(normalize-space(substring-after(//*[@id='card_rank']//*[contains(@class,'rank-global')]//p[contains(@class,'data')],'#')),',','')"/>
            </globalRank>
            <country>
                <xsl:value-of select="normalize-space(substring-before(substring-after(//*[@id='card_rank']//*[contains(@id,'CountryRank')]//*[contains(@id,'countrydropdown')]//ul[contains(@class,'container')]/li[1],' '),'#'))"/>
            </country>
            <countryRank>
                <xsl:value-of select="translate(//*[@id='card_rank']//*[contains(@id,'CountryRank')]//*[contains(@id,'countrydropdown')]//ul[contains(@class,'container')]/li[1]/@data-value,',','')"/>
            </countryRank>
            <category>
                <name>
                    <xsl:value-of select="$categoryName"/>
                </name>
            </category>
        </site>
    </xsl:template>

</xsl:stylesheet>
