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
package com.nanalysis.spinlab.dataset.enums;

/**
 * For multivalued values (ex: ListNumberValue), the value can vary depending on acquisition counter.
 * Which counter used for variation is the Order in spinlab data.
 */
public enum Order {
    Unknown("Unknown"),
    One("1D"),
    Two("2D"),
    Three("3D"),
    Four("4D"),
    Loop("Loop"),
    OneLoop("1D+Loop"),
    TwoLoop("2D+Loop"),
    ThreeLoop("3D+Loop"),
    FourLoop("4D+Loop"),
    LoopB("LoopB"),
    OneLoopB("1D+LoopB"),
    TwoLoopB("2D+LoopB"),
    ThreeLoopB("3D+LoopB"),
    FourLoopB("4D+LoopB");

    final String name;

    Order(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
