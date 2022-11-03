package com.nanalysis.spinlab.dataset;

import com.nanalysis.spinlab.dataset.enums.Parameter;
import com.nanalysis.spinlab.dataset.enums.Unit;
import com.nanalysis.spinlab.dataset.values.ListNumberValue;
import com.nanalysis.spinlab.dataset.values.NumberValue;
import org.junit.Ignore;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
        System.out.println(sw.getValueAs(Unit.Hertz, header));

        ListNumberValue tau = header.get("Tau_2D");
        System.out.println(tau.getValue());
        System.out.println(tau.getOrder());
    }

    @Test
    @Ignore("Manual test")
    public void readAndWrite() throws IOException, ParserConfigurationException, SAXException, TransformerException {
        File dir = new File("C:/Temp");
        File in = new File(dir, "header.xml");
        File out = new File(dir, "header.out.xml");

        Header header = new HeaderParser().parse(new FileInputStream(in));
        Document document = new HeaderWriter().toDom(header);
        new HeaderWriter().writeXml(document, new FileOutputStream(out));
    }
}
