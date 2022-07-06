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
import org.w3c.dom.Node;

public class TextValue extends Value<String> {
    public static final String XSI_TYPE = "textParam";

    public TextValue() {
        // empty
    }

    public TextValue(Node node) {
        super(node);
        this.value = DOM.getTextContent(node, "value");
    }
}
