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
import com.nanalysis.spinlab.dataset.enums.Unit;
import com.nanalysis.spinlab.dataset.util.DOM;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

public class NumberValue extends NumericValue<Number> {
    public static final String XSI_TYPE = "numberParam";

    /**
     * An optional list of suggested values. Before acquisition, this is displayed as a combo-box to the user.
     */
    private List<Number> suggestedValues = new ArrayList<>();

    /**
     * Whether to force the value as one of the suggested ones, or to let the user type a free-form value.
     */
    private boolean restrictedToSuggested;

    public NumberValue() {
        // empty
    }

    public NumberValue(String name, Number value) {
        super(name, value);
    }

    public NumberValue(Node node) {
        super(node);
        this.value = DOM.getNumberContent(node, "value", getNumberClass());
        this.defaultValue = DOM.getNumberContent(node, "defaultValue", getNumberClass());
        this.suggestedValues = DOM.getListNumberContent(node, "suggestedValues", getNumberClass());
        this.restrictedToSuggested = DOM.getBooleanContent(node, "restrictedToSuggested");
    }

    @Override
    public void toDOM(Element parent) {
        super.toDOM(parent);
        parent.setAttribute("xsi:type", XSI_TYPE);
        DOM.addElement(parent, "value", value);
        DOM.addElement(parent, "defaultValue", defaultValue);
        DOM.addNumberElements(parent, "suggestedValues", suggestedValues);
        DOM.addElement(parent, "restrictedToSuggested", restrictedToSuggested);
    }

    public double getValueAs(Unit desired, Header header) {
        Unit unit = numberType != null ? numberType.getUnit() : null;
        if (unit == desired) {
            return getValue().doubleValue();
        }

        throw new IllegalStateException("Trying read a " + desired + " value from unit type " + unit);
    }

    @Override
    public int intValue() {
        return value.intValue();
    }

    @Override
    public double doubleValue() {
        return value.doubleValue();
    }
}
