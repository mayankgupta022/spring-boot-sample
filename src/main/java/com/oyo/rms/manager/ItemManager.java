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

	public Item update(Integer id, Item entity) {
		if (repo.exists(id)) {
			entity.setId(id);
			return getRepo().save(entity);
		}
		return null;
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
		return null;
	}

	/**
	 * Returns {@link List} of {@link Status#ACTIVE ACTIVE} {@link Item}
	 * @return {@link List} of {@link Item}
	 */
	public List<Item> findAllActive() {
		return repo.findByStatus(Item.Status.ACTIVE);
	}

}
