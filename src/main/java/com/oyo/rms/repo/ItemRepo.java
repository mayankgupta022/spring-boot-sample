package com.oyo.rms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oyo.rms.entity.Item;

@Repository
public interface ItemRepo extends JpaRepository<Item, Integer> {

    /**
     * Returns {@link List} of {@link Item} for the {@link Status
     * status} provided
     * @param status - {@link Status}
     * @return {@link List} of {@link Item}
     */
    List<Item> findByStatus(Item.Status status);

}
