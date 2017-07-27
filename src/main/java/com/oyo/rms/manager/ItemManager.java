package com.oyo.rms.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.oyo.rms.entity.Item;
import com.oyo.rms.entity.Item.Status;
import com.oyo.rms.repo.ItemRepo;

@Component
public class ItemManager {

	@Autowired
	ItemRepo repo;
	
	public JpaRepository<Item, Integer> getRepo() {
		return repo;
	}	

	public Item findById(Integer id) {
		return getRepo().findOne(id);
	}

	public Item create(Item entity) {
		return getRepo().save(entity);
	}

	public Item update(Item entity) {
		return getRepo().save(entity);
	}

	public List<Item> findAll() {
		return getRepo().findAll();
	}

	public Item destroy(Item entity) {
		entity.setStatus(Item.Status.INACTIVE);
		return getRepo().save(entity);
	}

	/**
	 * Returns {@link List} of {@link Status#ACTIVE ACTIVE} {@link Item}
	 * @return {@link List} of {@link Item}
	 */
	public List<Item> findAllActive() {
		return repo.findByStatus(Item.Status.ACTIVE);
	}

}
