package com.wixdom.bookcenter.repository;

import com.wixdom.bookcenter.domain.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Henry Lin badcop@163.com
 */
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}
