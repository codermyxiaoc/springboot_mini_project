package cn.wedstory.service.serviceImpl;

import cn.wedstory.dao.BrandDao;
import cn.wedstory.daoMain.Brand;
import cn.wedstory.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BrandServiceImpl implements BrandService {

  @Autowired
  private BrandDao brandDao;
  @Override
  public List<Brand> getBrandAll() {
    List<Brand> brandList = brandDao.getBrandAll();
    return brandList;
  }

  @Override
  public Boolean addBrand(Brand brand) {
    Integer integer = brandDao.addBrand(brand);
    return integer == 1;
  }

  @Override
  public Boolean deleteBrand(Integer id) {
    Integer integer = brandDao.deleteBrand(id);
    return integer == 1;
  }

  @Override
  public Boolean updateBrand(Brand brand) {
    Integer integer = brandDao.updateBrand(brand);
    return integer == 1;
  }

  @Override
  public List<Brand> searchBrand(Brand brand) {
    List<Brand> brands = brandDao.searchBrand(brand);
    return brands;
  }
}
