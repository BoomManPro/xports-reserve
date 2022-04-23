package com.boommanpro.xportsreserve.parser

import com.boommanpro.xportsreserve.model.VenuePage
import org.apache.commons.io.IOUtils
import org.springframework.core.io.ClassPathResource
import spock.lang.Specification

class HtmlParserUtilSpec extends Specification {

    def "parserFromHtmlForClassType"() {
        given:
        def html = IOUtils.toString(new ClassPathResource("venuesPage.html").getInputStream(), "UTF-8")

        def venuePage = HtmlParserUtil.parserFromHtml(html, VenuePage.class)
        with() {
            venuePage.getName() == "2022羽毛球场地（蓝羽区）"
            venuePage.getTitle() == null
            venuePage.getVenuesInfoList() != null
        }

        cleanup:
        html = IOUtils.toString(new ClassPathResource("error.html").getInputStream(), "UTF-8")

        venuePage = HtmlParserUtil.parserFromHtml(html, VenuePage.class)
        with() {
            venuePage.getName() == null
            venuePage.getTitle() == "失败"
            venuePage.getVenuesInfoList() == null
        }
    }
}
