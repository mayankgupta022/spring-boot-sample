package com.oyo.rms.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oyo.rms.entity.Item;
import com.oyo.rms.manager.ItemManager;
import com.oyo.rms.request.ItemRequest;
import com.oyo.rms.response.ItemResponse;

@RestController
@RequestMapping(value = "/items", produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemController {

	@Autowired
	ItemManager manager;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<ItemResponse> index() {
		List<Item> items = manager.findAllActive();
		return items.stream().map(item -> entityToResponse(item)).collect(Collectors.toList());
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Item> all() {
		return manager.findAll();
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public Item show(@PathVariable("id") Integer id) {
		return manager.findById(id);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public Item create(@RequestBody ItemRequest request) {
		Item item = requestToEntity(request);
		return manager.create(item);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ItemResponse update(@PathVariable("id") Integer id, @RequestBody ItemRequest request) {
		Item item = requestToEntity(request);
		return entityToResponse(manager.update(id, item));
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public Item destroy(@PathVariable("id") Integer id) {
		return manager.destroy(id);
	}

	private ItemResponse entityToResponse(Item item) {
		ItemResponse resp = new ItemResponse();
		resp.setId(item.getId());
		resp.setName(item.getName());
		resp.setLastUpdatedOn(item.getUpdatedAt());
		return resp;
	}

	private Item requestToEntity(ItemRequest req) {
		Item item = new Item();
		item.setName(req.getName());
		return item;
	}

}
