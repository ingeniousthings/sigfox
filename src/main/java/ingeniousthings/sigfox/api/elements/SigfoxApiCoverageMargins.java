/*
 *
 *  Copyright 2017 Ingeniousthings
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ingeniousthings.sigfox.api.elements;

import java.lang.Override;
import java.lang.String;
import java.util.Arrays;

/**
 * Summary
 *
 * Object retruned by sigfox API corresponding to a coverage margins.
 * ----------------------------------------------------------------------------------
 * Response Format :
 * {
 *      "margins":[
 *                  42.12,
 *                  27.49,
 *                  5.87
 *              ]
 * }
 *
 * @author Paul Pinault
 */
public class SigfoxApiCoverageMargins {

    private double [] margins;

    public double[] getMargins() {
        return margins;
    }

    public void setMargins(double[] margins) {
        this.margins = margins;
    }

    @Override
    public String toString() {
        return "SigfoxApiCoverageMargins{" +
                "margins=" + Arrays.toString(margins) +
                '}';
    }
}