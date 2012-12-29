package com.gdxh.web.controllers;

import java.io.IOException;

import org.atmosphere.cpr.AtmosphereResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdxh.web.config.utils.AtmosphereUtils;

@Controller
public class ChatController {
	
	@RequestMapping("/websockets")
    @ResponseBody
    public void subscribe(AtmosphereResource resource) throws IOException {
        resource.getResponse().setContentType("text/html");
        AtmosphereUtils.suspend(resource);
    }

}
