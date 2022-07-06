/*
 * RS2D-data-format: a library for RS2D dataset files.
 * Copyright (C) 2022 - Nanalysis Scientific Corp.
 * -
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.nanalysis.spinlab.dataset.util;

public class UnitConversions {
    private UnitConversions() {
        throw new IllegalAccessError("Utility class");
    }

    /**
     * Convert frequencies from ppm to Hertz.
     *
     * @param ppm               The frequency in ppm.
     * @param observedFrequency The observed frequency in Hz.
     * @return The converted frequency value in Hz.
     */
    public static double ppmToHertz(double ppm, double observedFrequency) {
        return ppm * observedFrequency / 1e6;
    }

    /**
     * Convert frequencies from Hertz to ppm.
     *
     * @param hertz The frequency in Hz.
     * @param observedFrequency The observed frequency in Hz.
     * @return The converted frequency in ppm.
     */
    public static double hertzToPpm(double hertz, double observedFrequency) {
        return (hertz * 1e6) / observedFrequency;
    }
}
