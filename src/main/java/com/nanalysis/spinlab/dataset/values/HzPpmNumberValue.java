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

import com.nanalysis.spinlab.dataset.Header;
import com.nanalysis.spinlab.dataset.enums.NumberType;
import com.nanalysis.spinlab.dataset.enums.Unit;
import com.nanalysis.spinlab.dataset.util.DOM;
import com.nanalysis.spinlab.dataset.util.UnitConversions;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class HzPpmNumberValue extends NumberValue {
    public static final String XSI_TYPE = "hzPpmNumberParam";

    /**
     * Enable to keep the link to the base frequency on deserialization.
     */
    private String uuidBaseFrequency;

    /**
     * Initial number enum of this parameter, before conversions.
     */
    private NumberType initialNumberType;

    public HzPpmNumberValue() {
        // empty
    }

    public HzPpmNumberValue(Node node) {
        super(node);
        this.uuidBaseFrequency = DOM.getTextContent(node, "uuidBaseFrequency");
        this.initialNumberType = DOM.getEnumContent(node, "initialNumberEnum", NumberType.class, NumberType.Double);
    }

    @Override
    public void toDOM(Element parent) {
        super.toDOM(parent);
        parent.setAttribute("xsi:type", XSI_TYPE);
        DOM.addElement(parent, "uuidBaseFrequency", uuidBaseFrequency);
        DOM.addElement(parent, "initialNumberType", initialNumberType);
    }

    @Override
    public double getValueAsHertz(Header header) {
        Unit unit = numberType != null ? numberType.getUnit() : null;
        if (unit == Unit.Ppm) {
            double ppm = getValue().doubleValue();
            NumberValue baseFreq = header.getFromUUID(uuidBaseFrequency);
            return UnitConversions.ppmToHertz(ppm, baseFreq.getValue().doubleValue());
        }

        return super.getValueAsHertz(header);
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
