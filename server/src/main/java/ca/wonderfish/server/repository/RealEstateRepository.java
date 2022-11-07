package ca.wonderfish.server.repository;

import ca.wonderfish.server.domain.RealEstate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RealEstateRepository {

    //get real estates from table_a
    @Select("SELECT * FROM table_a")
    public List<RealEstate> getRealEstateFromTableA();

    //get real estates from table_b
    @Select("SELECT * FROM table_b")
    public List<RealEstate> getRealEstateFromTableB();
}
