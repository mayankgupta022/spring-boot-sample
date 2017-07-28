package com.oyo.rms.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.oyo.rms.entity.Item;
import com.oyo.rms.entity.Item.Status;
import com.oyo.rms.exception.NotFoundException;
import com.oyo.rms.repo.ItemRepo;

@Component
public class ItemManager {

	@Autowired
	ItemRepo repo;

	public JpaRepository<Item, Integer> getRepo() {
		return repo;
	}

	public Item findById(Integer id) {
		Item item = getRepo().findOne(id);
		if (item != null) {
			return item;
		}
		// Ideally manager shouldn't directly throw a runtime exception. It
		// should rather throw a specific exception or return null and
		// service/controller should handle that
		throw new NotFoundException("Item not found for id: " + id);
	}

	public Item create(Item entity) {
		return getRepo().save(entity);
	}

	public Item update(Integer id, Item entity) {
		if (repo.exists(id)) {
			entity.setId(id);
			return getRepo().save(entity);
		}
		throw new NotFoundException("Item not found for id: " + id);
	}

	public List<Item> findAll() {
		return getRepo().findAll();
	}

	public Item destroy(Integer id) {
		Item entity = findById(id);
		if (entity != null) {
			entity.setStatus(Item.Status.INACTIVE);
			return getRepo().save(entity);
		}
		throw new NotFoundException("Item not found for id: " + id);
	}

	/**
	 * Returns {@link List} of {@link Status#ACTIVE ACTIVE} {@link Item}
	 * @return {@link List} of {@link Item}
	 */
	public List<Item> findAllActive() {
		return repo.findByStatus(Item.Status.ACTIVE);
	}

}
