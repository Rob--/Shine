package io.github.rob__.shine.DarkSky.Models;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Datum_ {

    private Integer time;
    private String summary;
    private String icon;
    private Double precipIntensity;
    private Double precipProbability;
    private Double temperature;
    private Double apparentTemperature;
    private Double dewPoint;
    private Double humidity;
    private Double windSpeed;
    private Integer windBearing;
    private Double visibility;
    private Double cloudCover;
    private Double pressure;
    private Double ozone;
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
     * The summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     *
     * @param summary
     * The summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     *
     * @return
     * The icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     *
     * @param icon
     * The icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
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

    /**
     *
     * @return
     * The temperature
     */
    public Double getTemperature() {
        return temperature;
    }

    /**
     *
     * @param temperature
     * The temperature
     */
    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    /**
     *
     * @return
     * The apparentTemperature
     */
    public Double getApparentTemperature() {
        return apparentTemperature;
    }

    /**
     *
     * @param apparentTemperature
     * The apparentTemperature
     */
    public void setApparentTemperature(Double apparentTemperature) {
        this.apparentTemperature = apparentTemperature;
    }

    /**
     *
     * @return
     * The dewPoint
     */
    public Double getDewPoint() {
        return dewPoint;
    }

    /**
     *
     * @param dewPoint
     * The dewPoint
     */
    public void setDewPoint(Double dewPoint) {
        this.dewPoint = dewPoint;
    }

    /**
     *
     * @return
     * The humidity
     */
    public Double getHumidity() {
        return humidity;
    }

    /**
     *
     * @param humidity
     * The humidity
     */
    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    /**
     *
     * @return
     * The windSpeed
     */
    public Double getWindSpeed() {
        return windSpeed;
    }

    /**
     *
     * @param windSpeed
     * The windSpeed
     */
    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    /**
     *
     * @return
     * The windBearing
     */
    public Integer getWindBearing() {
        return windBearing;
    }

    /**
     *
     * @param windBearing
     * The windBearing
     */
    public void setWindBearing(Integer windBearing) {
        this.windBearing = windBearing;
    }

    /**
     *
     * @return
     * The visibility
     */
    public Double getVisibility() {
        return visibility;
    }

    /**
     *
     * @param visibility
     * The visibility
     */
    public void setVisibility(Double visibility) {
        this.visibility = visibility;
    }

    /**
     *
     * @return
     * The cloudCover
     */
    public Double getCloudCover() {
        return cloudCover;
    }

    /**
     *
     * @param cloudCover
     * The cloudCover
     */
    public void setCloudCover(Double cloudCover) {
        this.cloudCover = cloudCover;
    }

    /**
     *
     * @return
     * The pressure
     */
    public Double getPressure() {
        return pressure;
    }

    /**
     *
     * @param pressure
     * The pressure
     */
    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    /**
     *
     * @return
     * The ozone
     */
    public Double getOzone() {
        return ozone;
    }

    /**
     *
     * @param ozone
     * The ozone
     */
    public void setOzone(Double ozone) {
        this.ozone = ozone;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}