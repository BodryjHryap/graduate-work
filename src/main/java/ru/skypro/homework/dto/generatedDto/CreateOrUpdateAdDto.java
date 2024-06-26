package ru.skypro.homework.dto.generatedDto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

/**
 * CreateOrUpdateAd
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-04-07T11:59:23.949615055Z[GMT]")


public class CreateOrUpdateAdDto {
  @JsonProperty("title")
  private String title = null;

  @JsonProperty("price")
  private Integer price = null;

  @JsonProperty("description")
  private String description = null;

  public CreateOrUpdateAdDto title(String title) {
    this.title = title;
    return this;
  }

  /**
   * заголовок объявления
   * @return title
   **/
  @Schema(description = "заголовок объявления")
  
  @Size(min=4,max=32)   public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public CreateOrUpdateAdDto price(Integer price) {
    this.price = price;
    return this;
  }

  /**
   * цена объявления
   * minimum: 0
   * maximum: 10000000
   * @return price
   **/
  @Schema(description = "цена объявления")
  
  @Min(0) @Max(10000000)   public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  public CreateOrUpdateAdDto description(String description) {
    this.description = description;
    return this;
  }

  /**
   * описание объявления
   * @return description
   **/
  @Schema(description = "описание объявления")
  
  @Size(min=8,max=64)   public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateOrUpdateAdDto createOrUpdateAd = (CreateOrUpdateAdDto) o;
    return Objects.equals(this.title, createOrUpdateAd.title) &&
        Objects.equals(this.price, createOrUpdateAd.price) &&
        Objects.equals(this.description, createOrUpdateAd.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, price, description);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateOrUpdateAd {\n");
    
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
