package com.jxlx.carcar.entity.result;

import java.util.List;

/**
 * Created by jayden on 16/11/4.
 * eg:
 * {
 *   status: "1",
 *   info: "OK",
 *   infocode: "10000",
 *   results: [
 *       {
 *          origin_id: "1",
 *          dest_id: "1",
 *          distance: "4734",
 *          duration: "960"
 *       }
 *   ]
 * }
 */
public class DistanceResult extends BaseResult {
    private static final long serialVersionUID = 8501157814867534608L;

    List<DistanceDo> results;
    /** 出发地 */
    private String oriId;
    private String oriLocation;
    /** 目的地 */
    private String destId;
    private String destLocation;

    public List<DistanceDo> getResults() {
        return results;
    }

    public void setResults(List<DistanceDo> results) {
        this.results = results;
    }

    public String getOriId() {
        return oriId;
    }

    public void setOriId(String oriId) {
        this.oriId = oriId;
    }

    public String getDestId() {
        return destId;
    }

    public void setDestId(String destId) {
        this.destId = destId;
    }

    public String getOriLocation() {
        return oriLocation;
    }

    public void setOriLocation(String oriLocation) {
        this.oriLocation = oriLocation;
    }

    public String getDestLocation() {
        return destLocation;
    }

    public void setDestLocation(String destLocation) {
        this.destLocation = destLocation;
    }
}
