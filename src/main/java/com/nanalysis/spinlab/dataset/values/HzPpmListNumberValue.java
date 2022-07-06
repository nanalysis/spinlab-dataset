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
package com.nanalysis.spinlab.dataset.values;

import com.nanalysis.spinlab.dataset.enums.NumberType;
import com.nanalysis.spinlab.dataset.util.DOM;
import org.w3c.dom.Node;

public class HzPpmListNumberValue extends ListNumberValue {
    public static final String XSI_TYPE = "hzPpmListNumberParam";

    /**
     * Enable to keep the link to the base frequency on deserialization.
     */
    private String uuidBaseFrequency;

    /**
     * Initial number enum of this parameter, before conversions.
     */
    private NumberType initialNumberType;

    public HzPpmListNumberValue() {
        // empty
    }

    public HzPpmListNumberValue(Node node) {
        super(node);
        this.uuidBaseFrequency = DOM.getTextContent(node, "uuidBaseFrequency");
        this.initialNumberType = DOM.getEnumContent(node, "initialNumberEnum", NumberType.class, NumberType.Double);
    }

    public String getUuidBaseFrequency() {
        return uuidBaseFrequency;
    }

    public void setUuidBaseFrequency(String uuidBaseFrequency) {
        this.uuidBaseFrequency = uuidBaseFrequency;
    }

    public NumberType getInitialNumberEnum() {
        return initialNumberType;
    }

    public void setInitialNumberEnum(NumberType initialNumberType) {
        this.initialNumberType = initialNumberType;
    }
}
