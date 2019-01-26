package com.shariqparwez.orderfulfillment.catalog;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository access for CatalogItemEntity data.
 * 
 * @author Shariq Parwez
 *
 */
public interface CatalogItemRepository extends JpaRepository<CatalogItemEntity, Integer> {
   
}
