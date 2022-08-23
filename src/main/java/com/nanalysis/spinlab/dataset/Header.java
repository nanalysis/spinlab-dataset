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
package com.nanalysis.spinlab.dataset;

import com.nanalysis.spinlab.dataset.enums.Parameter;
import com.nanalysis.spinlab.dataset.values.ListNumberValue;
import com.nanalysis.spinlab.dataset.values.Value;

import java.util.*;

public class Header {
    private final Map<String, Value<?>> values = new TreeMap<>(String::compareTo);
    private final Map<String, ListNumberValue> variation1D = new TreeMap<>(String::compareTo);
    private final Map<String, ListNumberValue> variation2D = new TreeMap<>(String::compareTo);
    private final Map<String, ListNumberValue> variation3D = new TreeMap<>(String::compareTo);
    private final Map<String, ListNumberValue> variation4D = new TreeMap<>(String::compareTo);

    public void add(Value<?> value) {
        this.values.put(value.getName(), value);
    }

    public void addVariation(Value<?> value, int dim) {
        if (value instanceof ListNumberValue listNumberValue) {
            addVariation(listNumberValue, dim);
        } else {
            throw new IllegalArgumentException("Only ListNumberValue can be stored as variations");
        }
    }

    public void addVariation(ListNumberValue value, int dim) {
        getVariationMapForDimension(dim).put(value.getName(), value);
    }

    private Map<String, ListNumberValue> getVariationMapForDimension(int dim) {
        return switch (dim) {
            case 1 -> this.variation1D;
            case 2 -> this.variation2D;
            case 3 -> this.variation3D;
            case 4 -> this.variation4D;
            default -> throw new IllegalArgumentException("Invalid dimension: " + dim + ", must be between 1 and 4.");
        };
    }

    public Collection<Value<?>> all() {
        return Collections.unmodifiableCollection(values.values());
    }

    public <T extends Value<?>> T get(Parameter parameter) {
        return get(parameter.name());
    }

    @SuppressWarnings("unchecked")
    public <T extends Value<?>> T get(String name) {
        return (T) this.values.get(name);
    }

    @SuppressWarnings("unchecked")
    public <T extends Value<?>> T getFromUUID(String uuid) {
        return (T) this.values.values().stream()
                .filter(p -> uuid.equals(p.getUuid()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No parameter with UUID '" + uuid + "'"));
    }
}
