package cn.wedstory.Controller;

import cn.wedstory.daoMain.Brand;
import cn.wedstory.service.serviceImpl.BrandServiceImpl;
import cn.wedstory.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {
  @Autowired
  private BrandServiceImpl brandMapper;
  @GetMapping("/getBrandAll")
  public Result getBrandAll() {
    List<Brand> brandList = brandMapper.getBrandAll();
    return new Result(200,"查询成功",brandList);
  }
  @PostMapping("/addBrand")
  public Result addBrand(@RequestBody Brand brand) {
    if(brand.getBrand_name() == null || brand.getCompany_name() == null || brand.getOrdered() == null || brand.getStatus() == null){
      throw new RuntimeException("属性不能为空");
    }
    Boolean status = brandMapper.addBrand(brand);
    if(status) {
      return new Result(200, "添加成功", null);
    } else {
      return new Result(201, "添加失败", null);
    }
  }
  @GetMapping("/deleteBrand/{id}")
  public Result deleteBrand(@PathVariable Integer id){
    Boolean status = brandMapper.deleteBrand(id);
    if (status) {
      return new Result(200, "删除成功", null);
    } else {
      return new Result(201, "删除失败", null);
    }
  }
  @PostMapping("/updateBrand")
  public Result updateBrand(@RequestBody Brand brand) {
    if (brand.getId() == null) {
        throw new RuntimeException("修改id不能为空");
    }
    Boolean status = brandMapper.updateBrand(brand);
    if(status) {
      return new Result(200, "修改成功", null);
    } else {
      return new Result(201, "修改失败", null);
    }
  }
  @PostMapping("/searchBrand")
  public Result searchBrand(@RequestBody Brand brand) {
    List<Brand> brands = brandMapper.searchBrand(brand);
    return new Result(200, "查询成功", brands);
  }
}
