<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="text"/>

    <xsl:variable name="delimiter" select="','"/>

    <!-- define an array containing the fields we are interested in -->
    <xsl:variable name="fieldArray">
        <field>identificadorCliente</field>
        <field>codigoMoneda</field>
        <field>monto</field>
        <field>fechaCobro</field>
        <field>horaCobro</field>
    </xsl:variable>
    <xsl:param name="fields" select="document('')/*/xsl:variable[@name='fieldArray']/*"/>

    <xsl:template match="/xmlPagoInfo">

        <!-- output the header row -->
        <xsl:for-each select="$fields">
            <xsl:if test="position() != 1">
                <xsl:value-of select="$delimiter"/>
            </xsl:if>
            <xsl:value-of select="."/>
        </xsl:for-each>
        <!-- output newline -->
    <xsl:text>
</xsl:text>

        <xsl:value-of select="identificadorCliente"/>,<xsl:value-of select="codigoMoneda"/>,<xsl:value-of select="monto"/>,<xsl:value-of select="fechaCobro"/>,<xsl:value-of select="horaCobro"/>

        <!-- output newline -->
    <xsl:text>
</xsl:text>



    </xsl:template>


</xsl:stylesheet>