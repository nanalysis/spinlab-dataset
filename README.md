# Spinlab dataset

A Java library to read RS2D / Spinlab datasets.

## Sample usage

```
// parse XML header
Header header = new HeaderParser().parse(new FileInputStream("/path/to/header.xml"));

// Access predefined parameters - using the Parameter enum
NumberValue sw = header.get(Parameter.SPECTRAL_WIDTH); 
System.out.println(sw.getNumberEnum());
System.out.println(sw.getValue());
System.out.println(sw.getValueAsHertz(header)); // handles hz conversion from ppm if needed

// Access sequence-specific parameters - using String
ListNumberValue tau = header.get("Tau_2D");
System.out.println(tau.getValue());
System.out.println(tau.getOrder());
```

## Licensing

This library is published under the GNU GPL v3, and an internal proprietary license for use in Nanalysis, RS2D and OneMoonScientific projects.
