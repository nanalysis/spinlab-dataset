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
 * Units used by spinlab data.
 */
public enum Unit {
    Hertz("Hz"),
    HertzPerPoint("Hz/pt"),
    HertzPerCentimeterPerShimDigit("Hz/cm/u"),
    Degree("°"),
    Pourcent("%"),
    Second("s"),
    MillisSecond("ms"),
    MicroSecond("µs"),
    Null(""),
    DB("dB"),
    Volt("V"),
    VoltPerADC("V/ADC"),
    Amper("A"),
    Watt("W"),
    DBm("dBm"),
    DBperV("dB/V"),
    Point("pts"),
    Meter("m"),
    Siemens("S"),
    Henry("H"),
    Ohm("\u03A9"),
    Tesla("T"),
    Celsius("°C"),
    Kelvin("K"),
    Fahrenheit("°F"),
    TeslaPerMeter("T/m"),
    TeslaPerMeterPerAmp("T/m/A"),
    VoltPerUs("V/us"),
    LiterPerHour("L/h"),
    MeterPerSecond("m/s"),
    Ppm("ppm"),
    Bar("bar"),
    PSI("PSI"),
    BPM("BPM"),
    SecondPermm2("s/mm\u00B2"),
    Diffusion("\u00B5m\u00B2/s");

    private final String symbol;

    Unit(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
