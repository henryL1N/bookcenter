package com.wixdom.bookcenter.repository;

import com.wixdom.bookcenter.domain.StockItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Henry Lin badcop@163.com
 */
public interface StockItemRepository extends JpaRepository<StockItem, Long> {
}
