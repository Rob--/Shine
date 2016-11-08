package io.github.rob__.shine.DarkSky.Models;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Alert {

    private String title;
    private Integer time;
    private Integer expires;
    private String description;
    private String uri;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The time
     */
    public Integer getTime() {
        return time;
    }

    /**
     *
     * @param time
     * The time
     */
    public void setTime(Integer time) {
        this.time = time;
    }

    /**
     *
     * @return
     * The expires
     */
    public Integer getExpires() {
        return expires;
    }

    /**
     *
     * @param expires
     * The expires
     */
    public void setExpires(Integer expires) {
        this.expires = expires;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The uri
     */
    public String getUri() {
        return uri;
    }

    /**
     *
     * @param uri
     * The uri
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}