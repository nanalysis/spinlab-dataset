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

import com.nanalysis.spinlab.dataset.util.DOM;
import com.nanalysis.spinlab.dataset.values.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.OutputStream;
import java.util.Collection;

public class HeaderWriter {
    public Document toDom(Header header) throws ParserConfigurationException {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document doc = docBuilder.newDocument();
        Element root = doc.createElement("header");
        doc.appendChild(root);

        Element params = DOM.addElement(root, "params");
        fillParameters(doc, params, header.all());

        //TODO variation params
        return doc;
    }


    // write doc to output stream
    public void writeXml(Document document, OutputStream output) throws TransformerException {
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(output);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");

        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        transformer.transform(source, result);
    }

    private void fillParameters(Document doc, Element params, Collection<Value<?>> values) {
        for (Value<?> value : values) {
            Element entry = DOM.addElement(params, "entry");
            Element key = DOM.addElement(entry, "key", value.getName());
            Element element = DOM.addElement(entry, "value");

            value.toDOM(element);

            //TODO continue, see how to add xsi:type
            //TODO convert value classes
        }
    }

}
