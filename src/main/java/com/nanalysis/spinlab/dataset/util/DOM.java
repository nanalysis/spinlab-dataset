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
package com.nanalysis.spinlab.dataset.util;

import com.nanalysis.spinlab.dataset.DataFormatException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DOM {
    public static List<Node> findChildNodes(Node parent, String tag) {
        List<Node> result = new ArrayList<>();

        NodeList children = parent.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeName().equals(tag)) {
                result.add(child);
            }
        }
        return result;
    }

    public static Node findUniqueChild(Node parent, String tag) {
        List<Node> children = findChildNodes(parent, tag);
        if (children.size() != 1) {
            throw new DataFormatException("Expected a single child with tag '" + tag + "', found " + children.size());
        }

        return children.get(0);
    }

    public static Optional<Node> findOptionalChild(Node parent, String tag) {
        List<Node> children = findChildNodes(parent, tag);
        if (children.size() > 1) {
            throw new DataFormatException("Expected a single child with tag '" + tag + "', found " + children.size());
        }

        return children.isEmpty() ? Optional.empty() : Optional.of(children.get(0));
    }

    public static String getTextContent(Node parent, String tag) {
        return findOptionalChild(parent, tag).map(Node::getTextContent).orElse("");
    }

    public static List<String> getListTextContent(Node parent, String tag) {
        return findChildNodes(parent, tag).stream().map(Node::getTextContent).toList();
    }

    public static boolean getBooleanContent(Node parent, String tag) {
        return findOptionalChild(parent, tag).map(Node::getTextContent).map(Boolean::valueOf).orElse(false);
    }

    public static Number getNumberContent(Node parent, String tag, Class<? extends Number> numberClass) {
        String value = findOptionalChild(parent, tag).map(Node::getTextContent).orElse("0");
        return parseNumber(value, numberClass);
    }

    public static List<Number> getListNumberContent(Node parent, String tag, Class<? extends Number> numberClass) {
        return findChildNodes(parent, tag).stream().map(Node::getTextContent)
                .map(value -> parseNumber(value, numberClass))
                .toList();
    }

    public static <T extends Enum<T>> T getEnumContent(Node parent, String tag, Class<T> enumClass, T defaultValue) {
        try {
            return findOptionalChild(parent, tag)
                    .map(Node::getTextContent)
                    .map(s -> Enum.valueOf(enumClass, s))
                    .orElse(defaultValue);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage() + ", using " + defaultValue + " instead.");
            return defaultValue;
        }
    }

    public static String getAttribute(Node node, String attributeName) {
        return node.getAttributes().getNamedItem(attributeName).getTextContent();
    }


    private static Number parseNumber(String value, Class<? extends Number> numberClass) {
        // try to parse integers specifically for better precision
        if (Integer.class.isAssignableFrom(numberClass)) {
            try {
                return Integer.valueOf(value);
            } catch (NumberFormatException e) {
                // ignore and fallback to double
            }
        }

        return Double.valueOf(value);
    }
}
