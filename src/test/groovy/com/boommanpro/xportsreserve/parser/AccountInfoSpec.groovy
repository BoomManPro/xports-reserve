package com.boommanpro.xportsreserve.parser

import com.boommanpro.xportsreserve.config.AccountInfo
import com.boommanpro.xportsreserve.model.ReserveRequire
import spock.lang.Specification

class AccountInfoSpec extends Specification {

    def "getRequireTimeKeyTest"() {
        given:
        def accountInfo = new AccountInfo()
        expect:
        accountInfo.getTargetDateRequireTimeKey(targetDate) == sets

        where:
        targetDate || sets
        "20220420" || []
        "20220421" || []
        "20220422" || []
    }

    def "getRequireTimeKeyTest0"() {
        given:
        def accountInfo = new AccountInfo()
        accountInfo.setRequires(buildRequireList(input))
        expect:
        accountInfo.getTargetDateRequireTimeKey(targetDate) == sets

        where:
        input                                                                                                    || targetDate || sets
        []                                                                                                       || "20220420" || []
        ["22-24-1-20220420-false", "22-24-1-20220421-false", "24-26-1-20220421-false", "22-24-1-20220422-false"] || "20220421" || ["22-24", "24-26"]
        ["22-24-1-20220420-false", "22-24-1-20220421-true", "24-26-1-20220421-false", "22-24-1-20220422-false"]  || "20220421" || ["24-26"]
        ["22-24-1-20220420-false", "22-24-1-20220421-false", "24-26-1-20220421-false", "22-24-1-20220422-false"] || "20220422" || ["22-24"]
    }

    List<ReserveRequire> buildRequireList(List<String> requireStringList) {
        List<ReserveRequire> ans = new ArrayList<>()
        for (final def require in requireStringList) {
            def value = require.split("-")
            def temp = new ReserveRequire(Integer.parseInt(value[0]), Integer.parseInt(value[1]), Integer.parseInt(value[2]), value[3])
            temp.setReserved(Boolean.parseBoolean(value[4]))
            ans.add(temp)
        }

        return ans
    }


}