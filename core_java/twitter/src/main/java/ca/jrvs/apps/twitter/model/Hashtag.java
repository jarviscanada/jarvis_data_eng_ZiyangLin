package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({
        "text",
        "indices"
})
public class Hashtag {
    @JsonProperty("text")
    private String text;
    @JsonProperty("indices")
    private List<Long> indices;

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

    @JsonProperty("indices")
    public List<Long> getIndices() {
        return indices;
    }

    @JsonProperty("indices")
    public void setIndices(List<Long> indices) {
        this.indices = indices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hashtag hashtag = (Hashtag) o;
        return Objects.equals(text, hashtag.text) &&
                Objects.equals(indices, hashtag.indices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, indices);
    }

    @Override
    public String toString() {
        return "Hashtag{" +
                "text='" + text + '\'' +
                ", indices=" + indices +
                '}';
    }
}
