package com.web.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.app.model.BillItem;

public interface BillItemRepository extends JpaRepository<BillItem, Long>{

}
