package com.oyo.rms.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/items", produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemController {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {
		return "hello";
	}
}
