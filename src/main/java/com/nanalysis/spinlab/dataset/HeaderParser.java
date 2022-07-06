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
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class HeaderParser {
    public Header parse(InputStream input) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

        Document doc = factory.newDocumentBuilder().parse(input);
        return headerFromDom(doc.getFirstChild());
    }


    private Header headerFromDom(Node node) {
        Header header = new Header();

        getValuesFromMapNode(DOM.findUniqueChild(node, "params"))
                .forEach(header::add);

        DOM.findOptionalChild(node, "variationParams1D").ifPresent(child -> getValuesFromMapNode(child).forEach(v -> header.addVariation(v, 1)));
        DOM.findOptionalChild(node, "variationParams2D").ifPresent(child -> getValuesFromMapNode(child).forEach(v -> header.addVariation(v, 2)));
        DOM.findOptionalChild(node, "variationParams3D").ifPresent(child -> getValuesFromMapNode(child).forEach(v -> header.addVariation(v, 3)));
        DOM.findOptionalChild(node, "variationParams4D").ifPresent(child -> getValuesFromMapNode(child).forEach(v -> header.addVariation(v, 4)));

        return header;
    }

    private List<Value<?>> getValuesFromMapNode(Node params) {
        List<Node> entries = DOM.findChildNodes(params, "entry");
        List<Value<?>> values = new ArrayList<>(entries.size());
        for (Node entry : entries) {
            Node paramNode = DOM.findUniqueChild(entry, "value");
            Value<?> value = paramFromDom(paramNode);
            values.add(value);
        }
        return values;
    }

    private Value<?> paramFromDom(Node node) {
        String type = DOM.getAttribute(node, "xsi:type");

        return switch (type) {
            case BooleanValue.XSI_TYPE -> new BooleanValue(node);
            case NumberValue.XSI_TYPE -> new NumberValue(node);
            case HzPpmNumberValue.XSI_TYPE -> new HzPpmNumberValue(node);
            case ListNumberValue.XSI_TYPE -> new ListNumberValue(node);
            case HzPpmListNumberValue.XSI_TYPE -> new HzPpmListNumberValue(node);
            case TextValue.XSI_TYPE -> new TextValue(node);
            case ListTextValue.XSI_TYPE -> new ListTextValue(node);
            case EnumValue.XSI_TYPE -> new EnumValue(node);
            default -> throw new DataFormatException("Unknown param type: " + type);
        };
    }
}
