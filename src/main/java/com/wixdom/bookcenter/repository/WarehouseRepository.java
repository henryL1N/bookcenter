package com.wixdom.bookcenter.repository;

import com.wixdom.bookcenter.domain.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Henry Lin badcop@163.com
 */
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}
