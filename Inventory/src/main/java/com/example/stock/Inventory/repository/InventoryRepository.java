package com.example.stock.Inventory.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.stock.Inventory.domain.Inventory;



@Repository
public interface InventoryRepository extends JpaRepository<Inventory, String>{
	List<Inventory> findByCompanyOrSupplier(
		      @Param("company") String name,
		      @Param("supplier") String supplier);

	  List<Inventory>   findByCompanyOrSupplierAndCode(@Param("company") String name,
		      @Param("supplier") String supplier,@Param("code") String code
		     );
	  
	  List<Inventory> findByExpLessThan(@Param("exp") Date exp);
}
