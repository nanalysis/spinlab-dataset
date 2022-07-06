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
 * Types of number that can be used in spinlab data.
 * Each number type has a specific Unit and can hold either integer or floating-point numbers.
 */
public enum NumberType {
    Bit(Unit.Null, Integer.class),
    Integer(Unit.Null, Integer.class),
    Double(Unit.Null, Double.class),
    PERCENT(Unit.Pourcent, Double.class),
    Frequency(Unit.Hertz, Double.class),
    Phase(Unit.Degree, Double.class),
    TxAmp(Unit.Pourcent, Double.class),
    TxAtt(Unit.DB, Integer.class),
    TxShape(Unit.Pourcent, Double.class),
    GradAmp(Unit.Pourcent, Double.class),
    GradShape(Unit.Pourcent, Double.class),
    Time(Unit.Second, Double.class),
    Millis(Unit.MillisSecond, Double.class),
    Micros(Unit.MicroSecond, Double.class),
    AcquPoint(Unit.Point, Integer.class),
    RxGain(Unit.DB, Double.class),
    SW(Unit.Hertz, Double.class),
    FrequencyOffset(Unit.Hertz, Double.class),
    FrequencyShift(Unit.Ppm, Double.class),
    ShimCalibration(Unit.HertzPerCentimeterPerShimDigit, Double.class),
    FidRes(Unit.HertzPerPoint, Double.class),
    Scan(Unit.Null, Integer.class),
    Angle(Unit.Degree, Double.class),
    PulseAngle(Unit.Degree, Double.class),
    RotationAngle(Unit.Degree, Double.class),
    Length(Unit.Meter, Double.class),
    LengthOffset(Unit.Meter, Double.class),
    Shim(Unit.Null, Integer.class),
    ShimRange(Unit.Null, Integer.class),
    Vpp(Unit.Volt, Double.class),
    Power(Unit.Watt, Double.class),
    PowerDBm(Unit.DBm, Double.class),
    RfPower(Unit.Hertz, Double.class),
    Gain(Unit.DB, Double.class),
    Amps(Unit.Amper, Double.class),
    Volts(Unit.Volt, Double.class),
    Conductance(Unit.Siemens, Double.class),
    Inductance(Unit.Henry, Double.class),
    Impedance(Unit.Ohm, Double.class),
    FieldGradient(Unit.TeslaPerMeter, Double.class),
    CoilStrength(Unit.TeslaPerMeterPerAmp, Double.class),
    Field(Unit.Tesla, Double.class),
    SlewRate(Unit.VoltPerUs, Double.class),
    Temperature(Unit.Kelvin, Double.class),
    Airflow(Unit.LiterPerHour, Double.class),
    SweepAmp(Unit.Pourcent, Double.class),
    SweepRate(Unit.Hertz, Double.class),
    UnsignedByte(Unit.Null, Integer.class),
    SignedByte(Unit.Null, Integer.class),
    UnsignedShort(Unit.Null, Integer.class),
    SignedShort(Unit.Null, Integer.class),
    Location(Unit.Meter, Double.class),
    Rate(Unit.BPM, Double.class),
    Speed(Unit.MeterPerSecond, Double.class),
    FlowSpeed(Unit.MeterPerSecond, Double.class),
    Diffusion_B(Unit.SecondPermm2, Double.class),
    Diffusion(Unit.Diffusion, Double.class),
    Pressure(Unit.Bar, Double.class),
    DBperVolt(Unit.DBperV, Double.class),
    VppPerADC(Unit.VoltPerADC, Double.class);

    // --

    private final Unit unit;
    private final Class<? extends Number> numberClass;

    NumberType(Unit unit, Class<? extends Number> numberClass) {
        this.unit = unit;
        this.numberClass = numberClass;
    }

    public Unit getUnit() {
        return unit;
    }

    public Class<? extends Number> getNumberClass() {
        return numberClass;
    }
}
