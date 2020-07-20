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
                <xsl:variable name="techStack" select="//name[text()=$siteName]/ancestor::TechStack"/>
                <h1 class="color-green">
                    Site Name: <xsl:value-of select="$techStack//name"/>
                </h1>
                <xsl:if test="$techStack/technologyGroup">
                    <form action="SiteProcessServlet" method="post" >
                        <input type="submit" name="action" value="Recommend"/>
                        <xsl:for-each select="$techStack/technologyGroup">
                            <h3 class="color-blue">Group Name: <xsl:value-of select="groupName"/></h3>
                            <xsl:for-each select="technology">
                                
                                <input type="checkbox" name="txtTechIds" value="{id}" />
                                <h4>Techname: <xsl:value-of select="techName"/></h4>
                                <h4>Description: </h4>
                                <p>
                                    <xsl:value-of select="description"/>
                                </p>
                            <hr></hr>
                            </xsl:for-each>
                        <hr></hr>
                        </xsl:for-each>
                    </form>
                   
                </xsl:if>
                <xsl:if test="not($techStack/technologyGroup)">
                    <h4>No Record Found</h4>
                </xsl:if>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
