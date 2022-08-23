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

import com.nanalysis.spinlab.dataset.util.DOM;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

public class ListTextValue extends Value<List<String>> {
    public static final String XSI_TYPE = "listTextParam";

    /**
     * An optional list of suggested values. Before acquisition, this is displayed as a combo-box to the user.
     */
    private List<String> suggestedValues = new ArrayList<>();

    /**
     * Whether to force the value as one of the suggested ones, or to let the user type a free-form value.
     */
    private boolean restrictedToSuggested;

    public ListTextValue() {
        // empty
    }

    public ListTextValue(Node node) {
        super(node);
        this.value = DOM.getListTextContent(node, "value");
        this.defaultValue = DOM.getListTextContent(node, "defaultValue");
        this.suggestedValues = DOM.getListTextContent(node, "suggestedValues");
        this.restrictedToSuggested = DOM.getBooleanContent(node, "restrictedToSuggested");
    }

    @Override
    public void toDOM(Element parent) {
        super.toDOM(parent);
        parent.setAttribute("xsi:type", XSI_TYPE);
        DOM.addTextElements(parent, "value", value);
        DOM.addTextElements(parent, "defaultValue", defaultValue);
        DOM.addTextElements(parent, "suggestedValues", suggestedValues);
        DOM.addElement(parent, "restrictedToSuggested", restrictedToSuggested);
    }
}
