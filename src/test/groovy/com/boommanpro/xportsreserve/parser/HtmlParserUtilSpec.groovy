package com.boommanpro.xportsreserve.parser

import com.boommanpro.xportsreserve.model.OrderDetail
import com.boommanpro.xportsreserve.model.OrderQueryPage
import com.boommanpro.xportsreserve.model.VenuePage
import org.apache.commons.io.IOUtils
import org.springframework.core.io.ClassPathResource
import org.springframework.util.CollectionUtils
import spock.lang.Specification

class HtmlParserUtilSpec extends Specification {

    def "parseForVenuePageClass"() {
        given:
        def html = IOUtils.toString(new ClassPathResource("html/venuesPage.html").getInputStream(), "UTF-8")

        def venuePage = HtmlParserUtil.parserFromHtml(html, VenuePage.class)
        with() {
            venuePage.getName() == "2022羽毛球场地（蓝羽区）"
            venuePage.getTitle() == null
            venuePage.getVenuesInfoList() != null
        }

        cleanup:
        html = IOUtils.toString(new ClassPathResource("html/error.html").getInputStream(), "UTF-8")

        venuePage = HtmlParserUtil.parserFromHtml(html, VenuePage.class)
        with() {
            venuePage.getName() == null
            venuePage.getTitle() == "失败"
            venuePage.getVenuesInfoList() == null
        }
    }

    def "parseForOrderQueryPageClass"() {
        given:
        def html = IOUtils.toString(new ClassPathResource("html/orderQueryPage.html").getInputStream(), "UTF-8")

        def orderQueryPage = HtmlParserUtil.parserFromHtml(html, OrderQueryPage.class)
        with() {
            !CollectionUtils.isEmpty(orderQueryPage.getOrderList())
            def order = orderQueryPage.getOrderList().get(0)
            order.getOrderState() == 2
            order.getReserveDateTime() == "2022-05-06 13:31:37"
        }
    }

    def "parseForOrderDetailClass"() {
        given:
        def html = IOUtils.toString(new ClassPathResource("html/orderDetail.html").getInputStream(), "UTF-8")

        def orderDetail = HtmlParserUtil.parserFromHtml(html, OrderDetail.class)
        with() {
            !Objects.isNull(orderDetail)
            orderDetail.getOrderNo() == "2022050603879158"
            orderDetail.getOrderDateTime() == "2022-05-06 13:31:37"
            orderDetail.getUseDate() == "2022-5-7"
            orderDetail.getUseTime() == "21:00 -- 22:00"
            orderDetail.getVenueSiteInfo() == "羽毛球（蓝羽区）-临11"
        }
    }
}