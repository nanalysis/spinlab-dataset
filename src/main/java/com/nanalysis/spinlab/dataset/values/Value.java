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
     * The parameter description.
     */
    protected String description;

    /**
     * Identifier, generated randomly and supposed unique for each params. Lazy initialization to keep compliancy with old Param.
     * After creation, this identifier will be kept and saved by serialization/deserialization, should never change.
     */
    protected String uuid;

    public Value() {
        // empty
    }

    public Value(Node node) {
        this.name = DOM.getTextContent(node, "name");
        this.displayedName = DOM.getTextContent(node, "displayedName");
        this.description = DOM.getTextContent(node, "description");
        this.uuid = DOM.getTextContent(node, "uuid");

        // value and defaultValue must be done in subclasses for typing
    }

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
