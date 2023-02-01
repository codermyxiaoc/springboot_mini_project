package cn.wedstory.service;

import cn.wedstory.daoMain.Brand;

import java.util.List;

public interface BrandService {
  List<Brand>  getBrandAll();
  Boolean addBrand(Brand brand);
  Boolean deleteBrand(Integer id);
  Boolean updateBrand(Brand brand);
  List<Brand> searchBrand(Brand brand);
}
