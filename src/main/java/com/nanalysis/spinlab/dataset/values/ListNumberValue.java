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
import com.nanalysis.spinlab.dataset.enums.Order;
import com.nanalysis.spinlab.dataset.enums.Unit;
import com.nanalysis.spinlab.dataset.util.DOM;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.List;
import java.util.stream.Collectors;

public class ListNumberValue extends NumericValue<List<Number>> {
    public static final String XSI_TYPE = "listNumberParam";

    private Order order;

    public ListNumberValue() {
        // empty
    }

    public ListNumberValue(String name, List<Number> value) {
        super(name, value);
    }

    public ListNumberValue(Node node) {
        super(node);
        this.value = DOM.getListNumberContent(node, "value", getNumberClass());
        this.defaultValue = DOM.getListNumberContent(node, "defaultValue", getNumberClass());
        this.order = DOM.getEnumContent(node, "order", Order.class, Order.Unknown);
    }

    @Override
    public void toDOM(Element parent) {
        super.toDOM(parent);
        parent.setAttribute("xsi:type", XSI_TYPE);
        DOM.addNumberElements(parent, "value", value);
        DOM.addNumberElements(parent, "defaultValue", defaultValue);
        DOM.addElement(parent, "order", order);
    }

    @Override
    public int intValue() {
        if (value.size() != 1) {
            throw new UnsupportedOperationException("Not a single numeric value: " + getName());
        }

        return value.get(0).intValue();
    }

    @Override
    public double doubleValue() {
        if (value.size() != 1) {
            throw new UnsupportedOperationException("Not a single numeric value: " + getName());
        }

        return value.get(0).doubleValue();
    }

    @Override
    public List<Integer> intListValue() {
        return value.stream().map(Number::intValue).toList();
    }

    @Override
    public List<Double> doubleListValue() {
        return value.stream().map(Number::doubleValue).toList();
    }

    @Override
    public String stringValue() {
        return value.stream().map(String::valueOf).collect(Collectors.joining(";"));
    }

    public List<Double> getValueAs(Unit desired, Header header) {
        Unit unit = numberType != null ? numberType.getUnit() : null;
        if (unit == desired) {
            return doubleListValue();
        }

        throw new IllegalStateException("Trying read a " + desired + " value from unit type " + unit);
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
