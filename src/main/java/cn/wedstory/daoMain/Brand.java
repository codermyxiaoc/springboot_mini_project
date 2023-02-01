package cn.wedstory.daoMain;

import lombok.Data;

@Data
public class Brand {
  private Integer id;
  private String brand_name;
  private String company_name;
  private Integer ordered;
  private String description;
  private Integer status;

  public Brand(Integer id, String brand_name, String company_name, Integer ordered, String description, Integer status) {
    this.id = id;
    this.brand_name = brand_name;
    this.company_name = company_name;
    this.ordered = ordered;
    this.description = description;
    this.status = status;
  }

  public Brand() {
  }
}
