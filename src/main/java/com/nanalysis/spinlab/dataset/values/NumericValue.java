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
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public abstract class NumericValue<T> extends Value<T> {
    /**
     * The maximum value that could be set before acquisition.
     */
    protected Number maxValue;

    /**
     * The minimum value that could be set by the user before acquisition.
     */
    protected Number minValue;

    /**
     * The number enumeration.
     */
    protected NumberType numberType;

    public NumericValue() {
        // empty
    }

    public NumericValue(String name, T value) {
        super(name, value);
    }

    public NumericValue(Node node) {
        super(node);
        this.numberType = DOM.getEnumContent(node, "numberEnum", NumberType.class, NumberType.Double);
        this.minValue = DOM.getNumberContent(node, "minValue", getNumberClass());
        this.maxValue = DOM.getNumberContent(node, "maxValue", getNumberClass());
        // value and defaultValue must be done in subclasses for typing
    }

    @Override
    public void toDOM(Element parent) {
        super.toDOM(parent);
        DOM.addElement(parent, "numberEnum", numberType);
        DOM.addElement(parent, "minValue", minValue);
        DOM.addElement(parent, "maxValue", maxValue);
    }

    protected Class<? extends Number> getNumberClass() {
        return numberType != null ? numberType.getNumberClass() : Double.class;
    }

    public NumberType getNumberEnum() {
        return numberType;
    }

    public void setNumberEnum(NumberType numberType) {
        this.numberType = numberType;
    }
}
