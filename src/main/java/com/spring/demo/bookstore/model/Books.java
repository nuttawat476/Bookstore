
package com.spring.demo.bookstore.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "price",
    "author_name",
    "book_name",
    "id"
})
@Generated("jsonschema2pojo")
public class Books {

    @JsonProperty("price")
    private BigDecimal price;
    @JsonProperty("author_name")
    private String authorName;
    @JsonProperty("book_name")
    private String bookName;
    @JsonProperty("id")
    private Long id;
    @JsonProperty("is_recommended")
    private Boolean isRecommended;
    

	@JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("price")
    public BigDecimal getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @JsonProperty("author_name")
    public String getAuthorName() {
        return authorName;
    }

    @JsonProperty("author_name")
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @JsonProperty("book_name")
    public String getBookName() {
        return bookName;
    }

    @JsonProperty("book_name")
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }
    
    @JsonProperty("is_recommended")
    public Boolean getIsRecommended() {
		return isRecommended;
	}

    @JsonProperty("is_recommended")
	public void setIsRecommended(Boolean isRecommended) {
		this.isRecommended = isRecommended;
	}

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
