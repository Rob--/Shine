package io.github.rob__.shine.DarkSky.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Flags {

    private List<String> sources = new ArrayList<String>();
    private List<String> darkskyStations = new ArrayList<String>();
    private List<String> lampStations = new ArrayList<String>();
    private List<String> isdStations = new ArrayList<String>();
    private List<String> madisStations = new ArrayList<String>();
    private String units;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The sources
     */
    public List<String> getSources() {
        return sources;
    }

    /**
     *
     * @param sources
     * The sources
     */
    public void setSources(List<String> sources) {
        this.sources = sources;
    }

    /**
     *
     * @return
     * The darkskyStations
     */
    public List<String> getDarkskyStations() {
        return darkskyStations;
    }

    /**
     *
     * @param darkskyStations
     * The darksky-stations
     */
    public void setDarkskyStations(List<String> darkskyStations) {
        this.darkskyStations = darkskyStations;
    }

    /**
     *
     * @return
     * The lampStations
     */
    public List<String> getLampStations() {
        return lampStations;
    }

    /**
     *
     * @param lampStations
     * The lamp-stations
     */
    public void setLampStations(List<String> lampStations) {
        this.lampStations = lampStations;
    }

    /**
     *
     * @return
     * The isdStations
     */
    public List<String> getIsdStations() {
        return isdStations;
    }

    /**
     *
     * @param isdStations
     * The isd-stations
     */
    public void setIsdStations(List<String> isdStations) {
        this.isdStations = isdStations;
    }

    /**
     *
     * @return
     * The madisStations
     */
    public List<String> getMadisStations() {
        return madisStations;
    }

    /**
     *
     * @param madisStations
     * The madis-stations
     */
    public void setMadisStations(List<String> madisStations) {
        this.madisStations = madisStations;
    }

    /**
     *
     * @return
     * The units
     */
    public String getUnits() {
        return units;
    }

    /**
     *
     * @param units
     * The units
     */
    public void setUnits(String units) {
        this.units = units;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}