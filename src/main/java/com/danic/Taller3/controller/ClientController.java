package com.danic.Taller3.controller;

import com.danic.Taller3.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService clientService;

}
