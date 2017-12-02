package sax;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

import java.util.*;
import java.io.*;


public class SAXLocalNameCount extends DefaultHandler {
    static public void main(String[] args) throws Exception {
    	String filename = null;

        for (int i = 0; i < args.length; i++) {
            filename = args[i];
            if (i != args.length - 1) {
                usage();
            }
        }

        if (filename == null) {
            usage();
        }
    	
    }
    private Hashtable tags;

    public void startDocument() throws SAXException {
        tags = new Hashtable();
    }
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
    	String key = localName;
    	Object value = tags.get(key);
    	if (value == null) {
    		tags.put(key, new Integer(1));
    		} 
    	else {
    		int count = ((Integer)value).intValue();
    		count++;
    		tags.put(key, new Integer(count));
    		}
    }
    public void endDocument() throws SAXException {
        Enumeration e = tags.keys();
        while (e.hasMoreElements()) {
            String tag = (String)e.nextElement();
            int count = ((Integer)tags.get(tag)).intValue();
            System.out.println("Local Name \"" + tag + "\" occurs " 
                               + count + " times");
        }    
    }
    private static String convertToFileURL(String filename) {
        String path = new File(filename).getAbsolutePath();
        if (File.separatorChar != '/') {
            path = path.replace(File.separatorChar, '/');
        }

        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        return "file:" + path;
    }
    private static void usage() {
        System.err.println("Usage: SAXLocalNameCount <file.xml>");
        System.err.println("       -usage or -help = this message");
        System.exit(1);
    }
}