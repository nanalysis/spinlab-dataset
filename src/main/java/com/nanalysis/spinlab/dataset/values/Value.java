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

import java.util.List;

public class Value<T> {
    /**
     * Name of this parameter.
     */
    protected String name;

    /**
     * Eventual alternative name to display for this parameter.
     */
    protected String displayedName;

    /**
     * The parameter value.
     */
    protected T value;

    /**
     * The parameter default value.
     */
    protected T defaultValue;

    /**
     * The parameter description.
     */
    protected String description;

    /**
     * Identifier, generated randomly and supposed unique for each params. Lazy initialization to keep compliancy with old Param.
     * After creation, this identifier will be kept and saved by serialization/deserialization, should never change.
     */
    protected String uuid;

    /**
     * Whether the parameter was user-editable before the acquisition.
     */
    protected boolean locked;

    /**
     * Whether the parameter was locked to its default value (mostly for instrument-dependent parameters).
     */
    protected boolean lockedToDefault;

    /**
     * The parameter group - used for display only.
     */
    protected String group;

    /**
     * The parameter category - used for display only.
     */
    protected String category;

    public Value() {
        // empty
    }

    public Value(String name, T value) {
        this.name = name;
        this.displayedName = name;
        this.value = value;
        this.defaultValue = value;
    }

    public Value(Node node) {
        this.name = DOM.getTextContent(node, "name");
        this.displayedName = DOM.getTextContent(node, "displayedName");
        this.description = DOM.getTextContent(node, "description");
        this.uuid = DOM.getTextContent(node, "uuid");
        this.locked = DOM.getBooleanContent(node, "locked");
        this.lockedToDefault = DOM.getBooleanContent(node, "lockedToDefault");
        this.group = DOM.getTextContent(node, "group");
        this.category = DOM.getTextContent(node, "category");

        // value and defaultValue must be done in subclasses for typing
    }

    public void toDOM(Element parent) {
        DOM.addElement(parent, "name", name);
        DOM.addElement(parent, "uuid", uuid);
        DOM.addElement(parent, "description", description);
        DOM.addElement(parent, "displayedName", displayedName);
        DOM.addElement(parent, "locked", locked);
        DOM.addElement(parent, "lockedToDefault", lockedToDefault);
        DOM.addElement(parent, "group", group);
        DOM.addElement(parent, "category", category);

        // value and defaultValue must be done in subclasses for typing
    }

    // easy accessors - may not make sense for all hierarchy but simplifies calling code

    public String stringValue() {
        return value == null ? null : String.valueOf(value);
    }

    public List<String> stringListValue() {
        return List.of(stringValue());
    }

    public int intValue() {
        throw new UnsupportedOperationException("Not a numeric value: " + getName());
    }

    public List<Integer> intListValue() {
        return List.of(intValue());
    }

    public double doubleValue() {
        throw new UnsupportedOperationException("Not a numeric value: " + getName());
    }

    public List<Double> doubleListValue() {
        return List.of(doubleValue());
    }

    public boolean booleanValue() {
        throw new UnsupportedOperationException("Not a boolean value: " + getName());
    }

    // generated getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayedName() {
        return displayedName;
    }

    public void setDisplayedName(String displayedName) {
        this.displayedName = displayedName;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
