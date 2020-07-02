<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : similarwebStyleSheet.xsl
    Created on : July 2, 2020, 2:12 PM
    Author     : Gia Bảo Hoàng
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" encoding="UTF-8" omit-xml-declaration="no"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <site>
            <favicon>
                <xsl:value-of select="//*[contains(@class,'websiteHeader-captionIconContainer')]//img/@src"/>
            </favicon>
            <url>
                <xsl:value-of select="//*[contains(@itemprop,'headline')]"/>
            </url>
            <description>
                <xsl:value-of select="//*[contains(@itemprop,'description')]"/>
            </description>
            <globalRank>
                <xsl:value-of select="normalize-space(//*[contains(@class,'js-globalRank')]//*[contains(@class,'js-websiteRanksValue')]/text()[1])"/>
            </globalRank>
            <country>
                <xsl:value-of select="//*[contains(@class,'js-countryRank')]//*[contains(@class,'websiteRanks-name')]"/>
            </country>
            <countryRank>
                <xsl:value-of select="normalize-space(//*[contains(@class,'js-countryRank')]//*[contains(@class,'js-websiteRanksValue')]/text()[1])"/>
            </countryRank>
            <category>
                <name>
                    <xsl:value-of select="normalize-space(substring-after(//*[contains(@class,'js-categoryRank')]//*[contains(@class,'websiteRanks-nameText')],'>'))"/>
                </name>
            </category>
            <categoryRank>
                <xsl:value-of select="normalize-space(//*[contains(@class,'js-categoryRank')]//*[contains(@class,'js-websiteRanksValue')]/text()[1])"/>
            </categoryRank>
            <totalVisit>
                <xsl:value-of select="//*[contains(@class,'engagementInfo-value')][not(@data-type)]//*[contains(@class,'engagementInfo-valueNumber js-countValue')]"/>
            </totalVisit>
        </site>
    </xsl:template>
</xsl:stylesheet>
