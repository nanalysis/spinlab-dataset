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

import java.util.List;

/**
 * Predefined parameters found on most spinlab datasets.
 */
public enum Parameter {
    // Common parameters
    MATRIX_DIMENSION_1D,
    MATRIX_DIMENSION_2D,
    MATRIX_DIMENSION_3D,
    MATRIX_DIMENSION_4D,
    OBSERVED_FREQUENCY,
    DUMMY_SCAN,
    ACQUISITION_MATRIX_DIMENSION_1D,
    ACQUISITION_MATRIX_DIMENSION_2D,
    ACQUISITION_MATRIX_DIMENSION_3D,
    ACQUISITION_MATRIX_DIMENSION_4D,
    USER_MATRIX_DIMENSION_1D,
    USER_MATRIX_DIMENSION_2D,
    USER_MATRIX_DIMENSION_3D,
    USER_MATRIX_DIMENSION_4D,
    MAGNETIC_FIELD_STRENGTH,
    DATA_REPRESENTATION,
    ACQUISITION_MODE,
    MODALITY,
    RECEIVER_COUNT,
    NUMBER_OF_AVERAGES,
    STATE,
    RECEIVER_GAIN,
    PULSE_LENGTH,
    PULSE_POWER,
    PAROPT_PARAM,
    SPECTRAL_WIDTH,
    DWELL_TIME,
    ACQUISITON_TIME_PER_SCAN,
    PHASE_FIELD_OF_VIEW_RATIO,
    AMPLITUDE_AND_PHASE,
    TRANSFORM_PLUGIN,
    SETUP_MODE,
    SEQUENCE_NAME,
    SEQUENCE_TIME,
    DIGITAL_FILTER_SHIFT,
    DIGITAL_FILTER_REMOVED,
    OBSERVED_NUCLEUS,
    NUCLEUS_1,
    NUCLEUS_2,
    NUCLEUS_3,
    NUCLEUS_4,
    BASE_FREQ_1,
    BASE_FREQ_2,
    BASE_FREQ_3,
    BASE_FREQ_4,
    OFFSET_FREQ_1,
    OFFSET_FREQ_2,
    OFFSET_FREQ_3,
    OFFSET_FREQ_4,
    TRANSMIT_FREQ_1,
    TRANSMIT_FREQ_2,
    TRANSMIT_FREQ_3,
    TRANSMIT_FREQ_4,
    TX_ROUTE,
    MANUFACTURER,
    SOFTWARE_VERSION,
    STATION_NAME,
    MODEL_NAME,
    LAST_PUT,
    INTERMEDIATE_FREQUENCY,
    PROBES,
    PROBE_MAX_GRADIENT,
    PHASE_0,
    PHASE_1,
    PHASE_APPLIED,
    ACCU_DIM,

    // Hardware settings
    INSTRUMENT_PREEMPHASIS_LABEL,
    INSTRUMENT_PREEMPHASIS,
    INSTRUMENT_DC,
    INSTRUMENT_A0,
    INSTRUMENT_SHIM_LABEL,
    INSTRUMENT_SHIM,

    INSTRUMENT_PROBE_CALIB_WIDTH_NUC_1,
    INSTRUMENT_PROBE_CALIB_WIDTH_NUC_2,
    INSTRUMENT_PROBE_CALIB_WIDTH_NUC_3,
    INSTRUMENT_PROBE_CALIB_WIDTH_NUC_4,

    INSTRUMENT_PROBE_CALIB_POWER_NUC_1,
    INSTRUMENT_PROBE_CALIB_POWER_NUC_2,
    INSTRUMENT_PROBE_CALIB_POWER_NUC_3,
    INSTRUMENT_PROBE_CALIB_POWER_NUC_4,

    // NMR specific
    SOLVENT,
    LOCK,
    SPECTRAL_WIDTH_2D,
    SPECTRAL_WIDTH_3D,
    SPECTRAL_WIDTH_4D,
    FID_RES,
    FID_RES_2D,
    SAMPLE_TEMPERATURE,
    SPIN_RATE,
    SR,
    PHASE_MOD;

    // param groups
    public static final List<Parameter> DIMENSION_PARAMS =
            List.of(MATRIX_DIMENSION_1D, MATRIX_DIMENSION_2D, MATRIX_DIMENSION_3D, MATRIX_DIMENSION_4D);
    public static final List<Parameter> ACQUISITION_DIMENSION_PARAMS =
            List.of(ACQUISITION_MATRIX_DIMENSION_1D, ACQUISITION_MATRIX_DIMENSION_2D, ACQUISITION_MATRIX_DIMENSION_3D, ACQUISITION_MATRIX_DIMENSION_4D);
    public static final List<Parameter> NUCLEUS_PARAMS =
            List.of(NUCLEUS_1, NUCLEUS_2, NUCLEUS_3, NUCLEUS_4);
    public static final List<Parameter> BASE_FREQ_PARAMS =
            List.of(BASE_FREQ_1, BASE_FREQ_2, BASE_FREQ_3, BASE_FREQ_4);
    public static final List<Parameter> OFFSET_FREQ_PARAMS =
            List.of(OFFSET_FREQ_1, OFFSET_FREQ_2, OFFSET_FREQ_3, OFFSET_FREQ_4);
    public static final List<Parameter> TRANSMIT_FREQ_PARAMS =
            List.of(TRANSMIT_FREQ_1, TRANSMIT_FREQ_2, TRANSMIT_FREQ_3, TRANSMIT_FREQ_4);
    public static final List<Parameter> SW_PARAMS =
            List.of(SPECTRAL_WIDTH, SPECTRAL_WIDTH_2D, SPECTRAL_WIDTH_3D, SPECTRAL_WIDTH_4D);
}
