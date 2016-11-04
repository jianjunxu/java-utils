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

    public List<DistanceDo> getResults() {
        return results;
    }

    public void setResults(List<DistanceDo> results) {
        this.results = results;
    }
}
