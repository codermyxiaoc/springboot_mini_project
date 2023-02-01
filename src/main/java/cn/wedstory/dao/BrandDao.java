package cn.wedstory.dao;

import cn.wedstory.daoMain.Brand;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BrandDao {
  @Select("select * from tb_brand;")
  List<Brand> getBrandAll();

  @Insert("insert into tb_brand (brand_name,company_name,ordered,description,status) values (#{brand_name},#{company_name},#{ordered},#{description},#{status});")
  Integer addBrand(Brand brand);

  @Delete("delete from tb_brand where id = #{id};")
  Integer deleteBrand(Integer id);

  Integer updateBrand(Brand brand);

  List<Brand> searchBrand(Brand brand);

}
