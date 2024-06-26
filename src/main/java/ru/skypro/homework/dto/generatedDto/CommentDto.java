package ru.skypro.homework.dto.generatedDto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

/**
 * Comment
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-04-07T11:59:23.949615055Z[GMT]")


public class CommentDto {
  @JsonProperty("author")
  private Integer author = null;

  @JsonProperty("authorImage")
  private String authorImage = null;

  @JsonProperty("authorFirstName")
  private String authorFirstName = null;

  @JsonProperty("createdAt")
  private String createdAt = null;

  @JsonProperty("pk")
  private Integer pk = null;

  @JsonProperty("text")
  private String text = null;

  public CommentDto author(Integer author) {
    this.author = author;
    return this;
  }

  /**
   * id автора комментария
   * @return author
   **/
  @Schema(description = "id автора комментария")
  
    public Integer getAuthor() {
    return author;
  }

  public void setAuthor(Integer author) {
    this.author = author;
  }

  public CommentDto authorImage(String authorImage) {
    this.authorImage = authorImage;
    return this;
  }

  /**
   * ссылка на аватар автора комментария
   * @return authorImage
   **/
  @Schema(description = "ссылка на аватар автора комментария")
  
    public String getAuthorImage() {
    return authorImage;
  }

  public void setAuthorImage(String authorImage) {
    this.authorImage = authorImage;
  }

  public CommentDto authorFirstName(String authorFirstName) {
    this.authorFirstName = authorFirstName;
    return this;
  }

  /**
   * имя создателя комментария
   * @return authorFirstName
   **/
  @Schema(description = "имя создателя комментария")
  
    public String getAuthorFirstName() {
    return authorFirstName;
  }

  public void setAuthorFirstName(String authorFirstName) {
    this.authorFirstName = authorFirstName;
  }

  public CommentDto createdAt(String createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  /**
   * дата и время создания комментария0
   * @return createdAt
   **/
  @Schema(description = "дата и время создания комментария")
  
    public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public CommentDto pk(Integer pk) {
    this.pk = pk;
    return this;
  }

  /**
   * id комментария
   * @return pk
   **/
  @Schema(description = "id комментария")
  
    public Integer getPk() {
    return pk;
  }

  public void setPk(Integer pk) {
    this.pk = pk;
  }

  public CommentDto text(String text) {
    this.text = text;
    return this;
  }

  /**
   * текст комментария
   * @return text
   **/
  @Schema(description = "текст комментария")
  
    public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CommentDto comment = (CommentDto) o;
    return Objects.equals(this.author, comment.author) &&
        Objects.equals(this.authorImage, comment.authorImage) &&
        Objects.equals(this.authorFirstName, comment.authorFirstName) &&
        Objects.equals(this.createdAt, comment.createdAt) &&
        Objects.equals(this.pk, comment.pk) &&
        Objects.equals(this.text, comment.text);
  }

  @Override
  public int hashCode() {
    return Objects.hash(author, authorImage, authorFirstName, createdAt, pk, text);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Comment {\n");
    
    sb.append("    author: ").append(toIndentedString(author)).append("\n");
    sb.append("    authorImage: ").append(toIndentedString(authorImage)).append("\n");
    sb.append("    authorFirstName: ").append(toIndentedString(authorFirstName)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    pk: ").append(toIndentedString(pk)).append("\n");
    sb.append("    text: ").append(toIndentedString(text)).append("\n");
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
