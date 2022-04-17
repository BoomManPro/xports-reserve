package com.boommanpro.xportsreserve.controller;

import com.boommanpro.xportsreserve.model.ResultVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("echo")
public class EchoController {

    @GetMapping
    public ResultVo<String> alive(){
        return ResultVo.success("alive");
    }
}
