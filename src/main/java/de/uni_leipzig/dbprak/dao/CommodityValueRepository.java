package de.uni_leipzig.dbprak.dao;

import de.uni_leipzig.dbprak.entity.CommodityValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommodityValueRepository extends JpaRepository<CommodityValue, Long> {

    @Query("select c.price from CommodityValue c where c.productId = ?1")
    public int returnPriceForProduct(Long id);

    @Query("select c from CommodityValue c where c.productId = ?1")
    public List<CommodityValue> getByProductId(Long id);
}
