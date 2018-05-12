package org.lehmenkuehler.calculator.Enums;


import java.math.BigDecimal;

import org.lehmenkuehler.calculator.R;
import org.lehmenkuehler.calculator.Utility;

public enum Converter {

    // DISTANCE
    // SI and GCS system
    UNIT_FEMTOMETRE(Tag.DISTANCE, "fm", Utility.getContext().getString(R.string.UNIT_FEMTOMETRE), new BigDecimal("1E-15")),
    UNIT_PICOMETRE(Tag.DISTANCE, "pm", Utility.getContext().getString(R.string.UNIT_PICOMETRE), new BigDecimal("1E-12")),
    UNIT_ANGSTROEM(Tag.DISTANCE, "Å", Utility.getContext().getString(R.string.UNIT_ANGSTROEM), new BigDecimal("1E-10")),
    UNIT_NANOMETRE(Tag.DISTANCE, "nm", Utility.getContext().getString(R.string.UNIT_NANOMETRE), new BigDecimal("1E-9")),
    UNIT_MICROMETRE(Tag.DISTANCE, "μm", Utility.getContext().getString(R.string.UNIT_MICROMETRE), new BigDecimal("1E-6")),
    UNIT_MILLIMETRE(Tag.DISTANCE, "mm", Utility.getContext().getString(R.string.UNIT_MILLIMETRE), new BigDecimal("0.001")),
    UNIT_CENTIMETRE(Tag.DISTANCE, "cm", Utility.getContext().getString(R.string.UNIT_CENTIMETRE), new BigDecimal("0.01")),
    UNIT_DECIMETRE(Tag.DISTANCE, "dm", Utility.getContext().getString(R.string.UNIT_DECIMETRE), new BigDecimal("0.1")),
    UNIT_METRE(Tag.DISTANCE, "m", Utility.getContext().getString(R.string.UNIT_METRE), BigDecimal.ONE),
    UNIT_DECAMETRE(Tag.DISTANCE, "dam", Utility.getContext().getString(R.string.UNIT_DECAMETRE), new BigDecimal("10")),
    UNIT_HECTOMETRE(Tag.DISTANCE, "hm", Utility.getContext().getString(R.string.UNIT_HECTOMETRE), new BigDecimal("100")),
    UNIT_KILOMETRE(Tag.DISTANCE, "km", Utility.getContext().getString(R.string.UNIT_KILOMETRE), new BigDecimal("1000")),
    UNIT_MEGAMETRE(Tag.DISTANCE, "Mm", Utility.getContext().getString(R.string.UNIT_MEGAMETRE), new BigDecimal("1E6")),
    UNIT_GIGAMETRE(Tag.DISTANCE, "Gm", Utility.getContext().getString(R.string.UNIT_GIGAMETRE), new BigDecimal("1E9")),

    // imperial / US
    UNIT_THOU(Tag.DISTANCE, "th", Utility.getContext().getString(R.string.UNIT_THOU), new BigDecimal("2.54E-5")),
    UNIT_INCH(Tag.DISTANCE, "in", Utility.getContext().getString(R.string.UNIT_INCH), new BigDecimal("0.0254")),
    UNIT_FOOT(Tag.DISTANCE, "ft", Utility.getContext().getString(R.string.UNIT_FOOT), new BigDecimal("0.3048")),
    UNIT_YARD(Tag.DISTANCE, "yd", Utility.getContext().getString(R.string.UNIT_YARD), new BigDecimal("0.9144")),
    UNIT_INTERNATIONAL_MILE(Tag.DISTANCE, "mi", Utility.getContext().getString(R.string.UNIT_INTERNATIONAL_MILE), new BigDecimal("1609.344")),

    // marine
    UNIT_FATHOM(Tag.DISTANCE, "ftm", Utility.getContext().getString(R.string.UNIT_FATHOM), new BigDecimal("1.8288")),
    UNIT_CABLE(Tag.DISTANCE, "cab", Utility.getContext().getString(R.string.UNIT_CABLE), new BigDecimal("185.2")),
    UNIT_NAUTICAL_MILE(Tag.DISTANCE, "nmi", Utility.getContext().getString(R.string.UNIT_NAUTICAL_MILE), new BigDecimal("1852")),

    // surveying
    UNIT_FURLONG(Tag.DISTANCE, "fur", Utility.getContext().getString(R.string.UNIT_FURLONG), new BigDecimal("201.168")),
    UNIT_CHAIN(Tag.DISTANCE, "ch", Utility.getContext().getString(R.string.UNIT_CHAIN), new BigDecimal("20.1168")),
    UNIT_ROD(Tag.DISTANCE, "rd", Utility.getContext().getString(R.string.UNIT_FURLONG), new BigDecimal("5.0292")),

    // astronomy
    UNIT_EARTH_RADIUS(Tag.DISTANCE, "R<sub><small>⊕</small></sub>", Utility.getContext().getString(R.string.UNIT_EARTH_RADIUS), new BigDecimal("6371000")),
    UNIT_LUNAR_DISTANCE(Tag.DISTANCE, "LD", Utility.getContext().getString(R.string.UNIT_LUNAR_DISTANCE), new BigDecimal("384402000")),
    UNIT_ASTRONOMICAL_UNIT(Tag.DISTANCE, "au", Utility.getContext().getString(R.string.UNIT_ASTRONOMICAL_UNIT), new BigDecimal("149597870700")),
    UNIT_LIGHT_YEAR(Tag.DISTANCE, "ly", Utility.getContext().getString(R.string.UNIT_LIGHT_YEAR), new BigDecimal("9460730472580800")),
    UNIT_PARSEC(Tag.DISTANCE, "pc", Utility.getContext().getString(R.string.UNIT_PARSEC), new BigDecimal("3.08567758149137E16")),
    UNIT_HUBBLE_LENGTH(Tag.DISTANCE, "cH<sub><small>0</small></sub><sup><small>-1</small><sup>", Utility.getContext().getString(R.string.UNIT_HUBBLE_LENGTH), new BigDecimal("1.3640556839E26")),


    // AREA
    // SI and GCS system
    UNIT_SQUARE_MILLIMETRE(Tag.AREA, "mm<sup><small>2</small></sup>", Utility.getContext().getString(R.string.UNIT_SQUARE_MILLIMETRE), new BigDecimal("1E-6")),
    UNIT_SQUARE_CENTIMETRE(Tag.AREA, "cm<sup><small>2</small></sup>", Utility.getContext().getString(R.string.UNIT_SQUARE_CENTIMETRE), new BigDecimal("0.0001")),
    UNIT_SQUARE_DECIMETRE(Tag.AREA, "dm<sup><small>2</small></sup>", Utility.getContext().getString(R.string.UNIT_SQUARE_DECIMETRE), new BigDecimal("0.01")),
    UNIT_SQUARE_METRE(Tag.AREA, "m<sup><small>2</small></sup>", Utility.getContext().getString(R.string.UNIT_SQUARE_METRE), BigDecimal.ONE),
    UNIT_ARE(Tag.AREA, "a", Utility.getContext().getString(R.string.UNIT_ARE), new BigDecimal("100")),
    UNIT_HECTARE(Tag.AREA, "ha", Utility.getContext().getString(R.string.UNIT_HECTARE), new BigDecimal("10000")),
    UNIT_SQUARE_KILOMETRE(Tag.AREA, "km<sup><small>2</small></sup>", Utility.getContext().getString(R.string.UNIT_SQUARE_KILOMETRE), new BigDecimal("1000000")),

    // imperial
    UNIT_SQUARE_INCH(Tag.AREA, "in<sup><small>2</small></sup>", Utility.getContext().getString(R.string.UNIT_SQUARE_INCH), new BigDecimal("0.00064516")),
    UNIT_SQUARE_FOOT(Tag.AREA, "ft<sup><small>2</small></sup>", Utility.getContext().getString(R.string.UNIT_SQUARE_FOOT), new BigDecimal("0.09290304")),
    UNIT_SQUARE_YARD(Tag.AREA, "yd<sup><small>2</small></sup>", Utility.getContext().getString(R.string.UNIT_SQUARE_YARD), new BigDecimal("0.83612736")),
    UNIT_ROOD(Tag.AREA, "ro", Utility.getContext().getString(R.string.UNIT_ROOD), new BigDecimal("1011.7141056")),
    UNIT_ACRE(Tag.AREA, "ac", Utility.getContext().getString(R.string.UNIT_ACRE), new BigDecimal("4046.8564224")),
    UNIT_SQUARE_MILE(Tag.AREA, "mi<sup><small>2</small></sup>", Utility.getContext().getString(R.string.UNIT_SQUARE_MILE), new BigDecimal("2589988.110336")),

    // VOLUME
    // SI and GCS system
    UNIT_CUBIC_MILLIMETRE(Tag.VOLUME, "mm<sup><small>3</small></sup>", Utility.getContext().getString(R.string.UNIT_CUBIC_MILLIMETRE), new BigDecimal("1E-9")),
    UNIT_CUBIC_CENTIMETRE(Tag.VOLUME, "cm<sup><small>3</small></sup>", Utility.getContext().getString(R.string.UNIT_CUBIC_CENTIMETRE), new BigDecimal("1E-6")),
    UNIT_CUBIC_DECIMETRE(Tag.VOLUME, "dm<sup><small>3</small></sup>", Utility.getContext().getString(R.string.UNIT_CUBIC_DECIMETRE), new BigDecimal("0.001")),
    UNIT_CUBIC_METRE(Tag.VOLUME, "m<sup><small>3</small></sup>", Utility.getContext().getString(R.string.UNIT_CUBIC_METRE), BigDecimal.ONE),
    UNIT_CUBIC_DECAMETRE(Tag.VOLUME, "dam<sup><small>3</small></sup>", Utility.getContext().getString(R.string.UNIT_CUBIC_DECAMETRE), new BigDecimal("1000")),
    UNIT_CUBIC_HECTOMETRE(Tag.VOLUME, "hm<sup><small>3</small></sup>", Utility.getContext().getString(R.string.UNIT_CUBIC_HECTOMETRE), new BigDecimal("1000000")),
    UNIT_CUBIC_KILOMETRE(Tag.VOLUME, "km<sup><small>3</small></sup>", Utility.getContext().getString(R.string.UNIT_CUBIC_KILOMETRE), new BigDecimal("1000000000")),

    // litres
    UNIT_MILLILITRE(Tag.VOLUME, "ml", Utility.getContext().getString(R.string.UNIT_MILLILITRE), new BigDecimal("1E-6")),
    UNIT_CENTILITRE(Tag.VOLUME, "cl", Utility.getContext().getString(R.string.UNIT_CENTILITRE), new BigDecimal("1E-5")),
    UNIT_DECILITRE(Tag.VOLUME, "dl", Utility.getContext().getString(R.string.UNIT_DECILITRE), new BigDecimal("0.0001")),
    UNIT_LITRE(Tag.VOLUME, "l", Utility.getContext().getString(R.string.UNIT_LITRE), new BigDecimal("0.001")),
    UNIT_DECALITRE(Tag.VOLUME, "dal", Utility.getContext().getString(R.string.UNIT_DECALITRE), new BigDecimal("0.01")),
    UNIT_HECTOLITRE(Tag.VOLUME, "hl", Utility.getContext().getString(R.string.UNIT_HECTOLITRE), new BigDecimal("0.1")),
    UNIT_KILOLITRE(Tag.VOLUME, "kl", Utility.getContext().getString(R.string.UNIT_KILOLITRE), BigDecimal.ONE),

    // imperial
    UNIT_CUBIC_INCH(Tag.VOLUME, "in<sup><small>3</small></sup>", Utility.getContext().getString(R.string.UNIT_CUBIC_INCH), new BigDecimal("16.387064E-6")),
    UNIT_CUBIC_FOOT(Tag.VOLUME, "ft<sup><small>3</small></sup>", Utility.getContext().getString(R.string.UNIT_CUBIC_FOOT), new BigDecimal("0.028316846592")),
    UNIT_CUBIC_YARD(Tag.VOLUME, "yd<sup><small>3</small></sup>", Utility.getContext().getString(R.string.UNIT_CUBIC_YARD), new BigDecimal("0.764554857984")),
    UNIT_GALLON_UK(Tag.VOLUME, "Imp gal", Utility.getContext().getString(R.string.UNIT_GALLON_UK), new BigDecimal("4.54609E-3")),
    UNIT_GALLON_US(Tag.VOLUME, "US liq gal", Utility.getContext().getString(R.string.UNIT_GALLON_US), new BigDecimal("3.785411784E-3")),
    UNIT_GALLON_US_DRY(Tag.VOLUME, "US dry gal", Utility.getContext().getString(R.string.UNIT_GALLON_US_DRY), new BigDecimal("4.40488377086E-3")),

    // MASS
    // SI
    UNIT_MICROGRAM(Tag.MASS, "μg", Utility.getContext().getString(R.string.UNIT_MICROGRAM), new BigDecimal("1E-9")),
    UNIT_MILLIGRAM(Tag.MASS, "mg", Utility.getContext().getString(R.string.UNIT_MILLIGRAM), new BigDecimal("1E-6")),
    UNIT_CENTIGRAM(Tag.MASS, "cg", Utility.getContext().getString(R.string.UNIT_MILLIGRAM), new BigDecimal("1E-5")),
    UNIT_DECIGRAM(Tag.MASS, "dg", Utility.getContext().getString(R.string.UNIT_MILLIGRAM), new BigDecimal("0.0001")),
    UNIT_GRAM(Tag.MASS, "g", Utility.getContext().getString(R.string.UNIT_GRAM), new BigDecimal("0.001")),
    UNIT_KILOGRAM(Tag.MASS, "kg", Utility.getContext().getString(R.string.UNIT_KILOGRAM), BigDecimal.ONE),
    UNIT_METRIC_TON(Tag.MASS, "t", Utility.getContext().getString(R.string.UNIT_METRIC_TON), new BigDecimal("1000")),

    // imperial
    UNIT_GRAIN(Tag.MASS, "gr", Utility.getContext().getString(R.string.UNIT_GRAIN), new BigDecimal("64.79891E-6")),
    UNIT_DRAM(Tag.MASS, "dr", Utility.getContext().getString(R.string.UNIT_DRAM), new BigDecimal("1.7718451953125E-3")),
    UNIT_OUNCE(Tag.MASS, "oz", Utility.getContext().getString(R.string.UNIT_OUNCE), new BigDecimal("28.349523125E-3")),
    UNIT_POUND(Tag.MASS, "lb", Utility.getContext().getString(R.string.UNIT_POUND), new BigDecimal("0.45359237")),
    UNIT_STONE(Tag.MASS, "st", Utility.getContext().getString(R.string.UNIT_STONE), new BigDecimal("6.35029318")),
    UNIT_QUARTER(Tag.MASS, "qr", Utility.getContext().getString(R.string.UNIT_QUARTER), new BigDecimal("12.70058636")),
    UNIT_LONG_HUNDREDWEIGHT_UK(Tag.MASS, "cwt l", Utility.getContext().getString(R.string.UNIT_LONG_HUNDREDWEIGHT_UK), new BigDecimal("50.80234544")),
    UNIT_SHORT_HUNDREDWEIGHT_US(Tag.MASS, "cwt sh", Utility.getContext().getString(R.string.UNIT_SHORT_HUNDREDWEIGHT_US), new BigDecimal("45.359237")),
    UNIT_LONG_TON_UK(Tag.MASS, "tn l", Utility.getContext().getString(R.string.UNIT_LONG_TON_UK), new BigDecimal("1016.04")),
    UNIT_SHORT_TON_US(Tag.MASS, "tn sh", Utility.getContext().getString(R.string.UNIT_SHORT_TON_US), new BigDecimal("907.18")),

    // gravitational
    UNIT_HYL(Tag.MASS, "hyl", Utility.getContext().getString(R.string.UNIT_HYL), new BigDecimal("9.80665")),
    UNIT_SLUG(Tag.MASS, "slug", Utility.getContext().getString(R.string.UNIT_SLUG), new BigDecimal("14.594")),

    UNIT_CARAT(Tag.MASS, "ct", Utility.getContext().getString(R.string.UNIT_CARAT), new BigDecimal("0.0002")),

    // troy weight
    UNIT_TROY_OUNCE(Tag.MASS, "oz t", Utility.getContext().getString(R.string.UNIT_TROY_OUNCE), new BigDecimal("0.0311034768")),
    UNIT_TROY_POUND(Tag.MASS, "lb t", Utility.getContext().getString(R.string.UNIT_TROY_POUND), new BigDecimal("0.37324")),
    UNIT_PENNYWEIGHT(Tag.MASS, "dwt", Utility.getContext().getString(R.string.UNIT_PENNYWEIGHT), new BigDecimal("0.00155517384")),
    UNIT_TROY_GRAIN(Tag.MASS, "gr t", Utility.getContext().getString(R.string.UNIT_TROY_GRAIN), new BigDecimal("0.00006479891")),

    // DENSITY
    // SI
    UNIT_GRAM_PER_CUBIC_CENTIMETRE(Tag.DENSITY, "g/cm<sup><small>3</small></sup>", Utility.getContext().getString(R.string.UNIT_GRAM_PER_CUBIC_CENTIMETRE), new BigDecimal("1000")),
    UNIT_KILOGRAM_PER_CUBIC_DECIMETRE(Tag.DENSITY, "kg/dm<sup><small>3</small></sup>", Utility.getContext().getString(R.string.UNIT_KILOGRAM_PER_CUBIC_DECIMETRE), new BigDecimal("1000")),
    UNIT_KILOGRAM_PER_CUBIC_METRE(Tag.DENSITY, "kg/m<sup><small>3</small></sup>", Utility.getContext().getString(R.string.UNIT_KILOGRAM_PER_CUBIC_METRE), BigDecimal.ONE),
    UNIT_METRIC_TON_PER_CUBIC_METRE(Tag.DENSITY, "t/m<sup><small>3</small></sup>", Utility.getContext().getString(R.string.UNIT_METRIC_TON_PER_CUBIC_METRE), new BigDecimal("1000")),

    // litres
    UNIT_KILOGRAM_PER_LITRE(Tag.DENSITY, "kg/l", Utility.getContext().getString(R.string.UNIT_KILOGRAM_PER_LITRE), new BigDecimal("1000")),
    UNIT_GRAM_PER_MILLILITRE(Tag.DENSITY, "g/ml", Utility.getContext().getString(R.string.UNIT_GRAM_PER_MILLILITRE), new BigDecimal("1000")),

    // imperial
    UNIT_POUND_PER_CUBIC_INCH(Tag.DENSITY, "lb/in<sup><small>3</small></sup>", Utility.getContext().getString(R.string.UNIT_POUND_PER_CUBIC_INCH), new BigDecimal("27679.9047")),
    UNIT_POUND_PER_CUBIC_FOOT(Tag.DENSITY, "lb/ft<sup><small>3</small></sup>", Utility.getContext().getString(R.string.UNIT_POUND_PER_CUBIC_FOOT), new BigDecimal("16.0184634")),

    // VELOCITY
    // SI
    UNIT_METRES_PER_SECOND(Tag.VELOCITY, "m/s", Utility.getContext().getString(R.string.UNIT_METRES_PER_SECOND), BigDecimal.ONE),
    UNIT_KILOMETRES_PER_SECOND(Tag.VELOCITY, "km/s", Utility.getContext().getString(R.string.UNIT_KILOMETRES_PER_SECOND), new BigDecimal("1000")),
    UNIT_KILOMETRES_PER_HOUR(Tag.VELOCITY, "km/h", Utility.getContext().getString(R.string.UNIT_KILOMETRES_PER_HOUR), BigDecimal.TEN.divide(new BigDecimal("36"), 150, BigDecimal.ROUND_HALF_UP)),
    UNIT_METRES_PER_MINUTE(Tag.VELOCITY, "m/min", Utility.getContext().getString(R.string.UNIT_METRES_PER_MINUTE), BigDecimal.ONE.divide(new BigDecimal("60"), 150, BigDecimal.ROUND_HALF_UP)),

    // imperial
    UNIT_FEET_PER_SECOND(Tag.VELOCITY, "ft/s", Utility.getContext().getString(R.string.UNIT_FEET_PER_SECOND), new BigDecimal("0.3047999902464")),
    UNIT_MILES_PER_SECOND(Tag.VELOCITY, "mi/s", Utility.getContext().getString(R.string.UNIT_MILES_PER_SECOND), new BigDecimal("1609.344")),
    UNIT_MILES_PER_HOUR(Tag.VELOCITY, "mi/h", Utility.getContext().getString(R.string.UNIT_MILES_PER_HOUR), new BigDecimal("0.44704")),
    UNIT_FEET_PER_MINUTE(Tag.VELOCITY, "ft/min", Utility.getContext().getString(R.string.UNIT_FEET_PER_MINUTE), new BigDecimal("0.00507999983744")),

    UNIT_KNOTS(Tag.VELOCITY, "kn", Utility.getContext().getString(R.string.UNIT_KNOTS), new BigDecimal("463").divide(new BigDecimal("900"), 150, BigDecimal.ROUND_HALF_UP)),

    UNIT_SPEED_OF_LIGHT(Tag.VELOCITY, "c", Utility.getContext().getString(R.string.UNIT_SPEED_OF_LIGHT),new BigDecimal("299792458")),
    UNIT_MACH_0(Tag.VELOCITY, "Ma", Utility.getContext().getString(R.string.UNIT_MACH_0),new BigDecimal("331")),

    // TIME

    UNIT_NANOSECOND(Tag.TIME, "ns", Utility.getContext().getString(R.string.UNIT_NANOSECOND), new BigDecimal("1E-9")),
    UNIT_MICROSECOND(Tag.TIME, "µs", Utility.getContext().getString(R.string.UNIT_MICROSECOND), new BigDecimal("1E-6")),
    UNIT_MILLISECOND(Tag.TIME, "ms", Utility.getContext().getString(R.string.UNIT_MILLISECOND), new BigDecimal("0.001")),
    UNIT_SECOND(Tag.TIME, "s", Utility.getContext().getString(R.string.UNIT_SECOND), BigDecimal.ONE),
    UNIT_MINUTE(Tag.TIME, "min", Utility.getContext().getString(R.string.UNIT_MINUTE), new BigDecimal("60")),
    UNIT_HOUR(Tag.TIME, "h", Utility.getContext().getString(R.string.UNIT_HOUR), new BigDecimal("3600")),
    UNIT_DAY(Tag.TIME, "d", Utility.getContext().getString(R.string.UNIT_DAY), new BigDecimal("86400")),
    UNIT_WEEK(Tag.TIME, "wk", Utility.getContext().getString(R.string.UNIT_WEEK), new BigDecimal("604800")),
    UNIT_YEAR(Tag.TIME, "a", Utility.getContext().getString(R.string.UNIT_YEAR), new BigDecimal("31536000"));



    private final Tag tag;
    private String symbol = "";
    private String description = "";
    private BigDecimal value = BigDecimal.ONE;

    Converter(Tag type, String symbol, String description, BigDecimal value) {
        this.tag = type;
        this.symbol = symbol;
        this.description = description;
        this.value = value;
    }

    public Tag getTag() {
        return tag;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getValue() {
        return value;
    }

    public enum Tag {
        DISTANCE(Utility.getContext().getString(R.string.CONVERT_DISTANCE)),
        AREA(Utility.getContext().getString(R.string.CONVERT_AREA)),
        VOLUME(Utility.getContext().getString(R.string.CONVERT_VOLUME)),
        MASS(Utility.getContext().getString(R.string.CONVERT_MASS)),
        DENSITY(Utility.getContext().getString(R.string.CONVERT_DENSITY)),
        VELOCITY(Utility.getContext().getString(R.string.CONVERT_VELOCITY)),
        TIME(Utility.getContext().getString(R.string.CONVERT_TIME));
        //ANGLE(Utility.getContext().getString(R.string.CONVERT_ANGLE)),
        //FREQUENCY(Utility.getContext().getString(R.string.CONVERT_FREQUENCY)),
        //FORCE(Utility.getContext().getString(R.string.CONVERT_FORCE)),
        //TORQUE(Utility.getContext().getString(R.string.CONVERT_TORQUE)),
        //PRESSURE(Utility.getContext().getString(R.string.CONVERT_PRESSURE)),
        //ENERGY(Utility.getContext().getString(R.string.CONVERT_ENERGY)),
        //POWER(Utility.getContext().getString(R.string.CONVERT_POWER)),
        //TEMPERATURE(Utility.getContext().getString(R.string.CONVERT_TEMPERATURE)),
        //DATA_SIZE(Utility.getContext().getString(R.string.CONVERT_DATA_SIZE));

        private final String name;

        Tag(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

    }
}
