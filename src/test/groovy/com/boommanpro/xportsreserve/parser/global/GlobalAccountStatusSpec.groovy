package com.boommanpro.xportsreserve.parser.global

import com.boommanpro.xportsreserve.global.GlobalAccountStatus
import org.junit.runner.RunWith
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.modules.junit4.PowerMockRunnerDelegate
import org.spockframework.runtime.Sputnik
import spock.lang.Specification

@RunWith(PowerMockRunner)
@PowerMockRunnerDelegate(Sputnik)
@PrepareForTest([GlobalAccountStatus])
//if have this annotation accountStatus will to default value is false
//@SuppressStaticInitializationFor(["com.boommanpro.xportsreserve.global.GlobalAccountStatus"])
class GlobalAccountStatusSpec extends Specification {


    def "isAccountStatusTest"() {
        expect:
        GlobalAccountStatus.isAccountStatus()

        when:
        PowerMockito.mockStatic(GlobalAccountStatus)
        PowerMockito.when(GlobalAccountStatus.isAccountStatus()).thenReturn(false)
        then:
        !GlobalAccountStatus.isAccountStatus()
    }

}