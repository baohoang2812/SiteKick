<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : buildwithStyleSheet.xsl
    Created on : July 2, 2020, 4:19 PM
    Author     : Gia Bảo Hoàng
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" 
                xmlns="http://www.sitekick.com"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
    <xsl:output method="xml" omit-xml-declaration="no"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <techStack>
            <site>
                <name>
                    <xsl:value-of select="//form[@id='mainForm']//div[@class='container'][1]//h1[contains(@class,'text-truncate')]"/>
                </name>
            </site>
            <xsl:for-each select="//form[@id='mainForm']//div[@class='container'][2]//*[contains(@class,'col-md-8')]/div[not(contains(.//h6,'Misleading Technology Profile Warning'))]">
                <technologyGroup>
                    <groupName>
                        <xsl:value-of select=".//*[contains(@class,'card-title')]"/> 
                    </groupName>
                    <xsl:for-each select=".//*[contains(@class, 'card-body')]//div[(contains(@class,'mt-2'))]">
                        <technology>
                            <techName>
                                <xsl:value-of select=".//h2/a"/>
                            </techName>
                            <description>
                                <xsl:value-of select=".//p[2] | .//p[contains(@class,'mb-0 small')]"/>
                            </description>
                        </technology>
                    </xsl:for-each>

                </technologyGroup>
            </xsl:for-each>
        </techStack>
    </xsl:template>

</xsl:stylesheet>
