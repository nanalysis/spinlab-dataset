package com.nanalysis.spinlab.dataset;

import com.nanalysis.spinlab.dataset.enums.Parameter;
import com.nanalysis.spinlab.dataset.values.ListNumberValue;
import com.nanalysis.spinlab.dataset.values.NumberValue;
import org.junit.Ignore;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ManualTest {
    @Test
    @Ignore("Manual test")
    public void parseSampleHeader() throws IOException, ParserConfigurationException, SAXException {
        File dir = new File("C:/Temp");
        File file = new File(dir, "header.xml");

        Header header = new HeaderParser().parse(new FileInputStream(file));
        NumberValue sw = header.get(Parameter.SPECTRAL_WIDTH);
        System.out.println(sw.getNumberEnum());
        System.out.println(sw.getValue());
        System.out.println(sw.getValueAsHertz(header));

        ListNumberValue tau = header.get("Tau_2D");
        System.out.println(tau.getValue());
        System.out.println(tau.getOrder());
    }
}
