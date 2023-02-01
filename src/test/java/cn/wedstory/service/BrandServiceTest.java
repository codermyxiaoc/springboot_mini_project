package cn.wedstory.service;

import cn.wedstory.daoMain.Brand;
import cn.wedstory.service.serviceImpl.BrandServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BrandServiceTest {
  @Autowired
  private BrandServiceImpl brandMapper;
  @Test
  void getBrandAll(){
    List<Brand> brandAll = brandMapper.getBrandAll();
  }
  @Test
  void addBrand() {
    Brand brand = new Brand();
    brand.setBrand_name("czl");
    brand.setCompany_name("czl");
    brand.setDescription("czl");
    brand.setOrdered(100);
    brand.setStatus(1);
    brandMapper.addBrand(brand);
  }
  @Test
  void deleteBrand() {
    Boolean aBoolean = brandMapper.deleteBrand(49);
  }
  @Test
  void updateBrand() {
    Brand brand = new Brand();
    brand.setId(50);
    brand.setBrand_name("czl");
    brand.setCompany_name("zyh");
    brand.setDescription("zyh");
    brand.setOrdered(100);
    brand.setStatus(1);
    brandMapper.updateBrand(brand);
  }
  @Test
  void searchBrand(){
    Brand brand = new Brand();
    brand.setBrand_name("小米");
    brandMapper.searchBrand(brand);
  }
}
