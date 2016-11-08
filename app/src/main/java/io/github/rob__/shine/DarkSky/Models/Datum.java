package io.github.rob__.shine.DarkSky.Models;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Datum {

    private Integer time;
    private Double precipIntensity;
    private Double precipProbability;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
     * The precipIntensity
     */
    public Double getPrecipIntensity() {
        return precipIntensity;
    }

    /**
     *
     * @param precipIntensity
     * The precipIntensity
     */
    public void setPrecipIntensity(Double precipIntensity) {
        this.precipIntensity = precipIntensity;
    }

    /**
     *
     * @return
     * The precipProbability
     */
    public Double getPrecipProbability() {
        return precipProbability;
    }

    /**
     *
     * @param precipProbability
     * The precipProbability
     */
    public void setPrecipProbability(Double precipProbability) {
        this.precipProbability = precipProbability;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}