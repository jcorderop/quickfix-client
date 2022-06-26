package org.quickfix.lmax.marketdata.model;

import org.xml.sax.SAXException;


import javax.script.*;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Price  {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        String xml = readFile("price.xml");

        //System.out.println(xml);

        XmlToFix xmlMessage = new XmlToFix(xml, "|");
        System.out.println(xmlMessage.toFixMessage());




    }


    public static String readFile(String path) throws IOException{
        return Files.lines(Paths.get(path)).collect(Collectors.joining("\n"));
    }

}

interface ScriptLoader {
    public String getScriptContent(String relativePath);
}

