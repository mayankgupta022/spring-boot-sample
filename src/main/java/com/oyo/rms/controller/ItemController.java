package com.oyo.rms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oyo.rms.entity.Item;
import com.oyo.rms.manager.ItemManager;

@RestController
@RequestMapping(value = "/items", produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemController {

	@Autowired
	ItemManager manager;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Item> index() {
		return manager.findAllActive();
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Item> all() {
		return manager.findAll();
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public Item show(@PathVariable("id") Integer id) {
		return manager.findById(id);
	}

}
