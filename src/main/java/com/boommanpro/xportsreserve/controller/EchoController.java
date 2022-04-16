package com.boommanpro.xportsreserve.controller;

import com.boommanpro.xportsreserve.config.AccountInfo;
import com.boommanpro.xportsreserve.config.VenuePageConfigProperties;
import com.boommanpro.xportsreserve.model.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("echo")
public class EchoController {

    @Autowired
    private AccountInfo accountInfo;

    @Autowired
    private VenuePageConfigProperties venuePageConfigProperties;

    @Autowired
    private Environment environment;

    @GetMapping
    public ResultVo<AccountInfo> echo() {
        return ResultVo.success(accountInfo);
    }
}
