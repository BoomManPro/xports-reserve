package com.boommanpro.xportsreserve.parser.rest


import com.boommanpro.xportsreserve.model.VenuePage
import com.boommanpro.xportsreserve.rest.VenueReserveClient
import spock.lang.Specification

class VenueReserveClientSpec extends Specification {


    def "test queryPage"() {
        given:
        def pageMock = new VenuePage()
        pageMock.setTitle("失败")
        VenueReserveClient client = Stub(VenueReserveClient) {
            queryPage(_ as String, _ as String, _ as String) >> pageMock
        }

        when:
        def page = client.queryPage("1601", "20220423", "xxx-cookie")

        then:
        page.getTitle() == "失败"
    }
}
