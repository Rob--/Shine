package io.github.rob__.shine.DarkSky.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Forecast {

    private Double latitude;
    private Double longitude;
    private String timezone;
    private Integer offset;
    private Currently currently;
    private Minutely minutely;
    private Hourly hourly;
    private Daily daily;
    private List<Alert> alerts = new ArrayList<Alert>();
    private Flags flags;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     *
     * @param latitude
     * The latitude
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @return
     * The longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     *
     * @param longitude
     * The longitude
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     *
     * @return
     * The timezone
     */
    public String getTimezone() {
        return timezone;
    }

    /**
     *
     * @param timezone
     * The timezone
     */
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    /**
     *
     * @return
     * The offset
     */
    public Integer getOffset() {
        return offset;
    }

    /**
     *
     * @param offset
     * The offset
     */
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    /**
     *
     * @return
     * The currently
     */
    public Currently getCurrently() {
        return currently;
    }

    /**
     *
     * @param currently
     * The currently
     */
    public void setCurrently(Currently currently) {
        this.currently = currently;
    }

    /**
     *
     * @return
     * The minutely
     */
    public Minutely getMinutely() {
        return minutely;
    }

    /**
     *
     * @param minutely
     * The minutely
     */
    public void setMinutely(Minutely minutely) {
        this.minutely = minutely;
    }

    /**
     *
     * @return
     * The hourly
     */
    public Hourly getHourly() {
        return hourly;
    }

    /**
     *
     * @param hourly
     * The hourly
     */
    public void setHourly(Hourly hourly) {
        this.hourly = hourly;
    }

    /**
     *
     * @return
     * The daily
     */
    public Daily getDaily() {
        return daily;
    }

    /**
     *
     * @param daily
     * The daily
     */
    public void setDaily(Daily daily) {
        this.daily = daily;
    }

    /**
     *
     * @return
     * The alerts
     */
    public List<Alert> getAlerts() {
        return alerts;
    }

    /**
     *
     * @param alerts
     * The alerts
     */
    public void setAlerts(List<Alert> alerts) {
        this.alerts = alerts;
    }

    /**
     *
     * @return
     * The flags
     */
    public Flags getFlags() {
        return flags;
    }

    /**
     *
     * @param flags
     * The flags
     */
    public void setFlags(Flags flags) {
        this.flags = flags;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
