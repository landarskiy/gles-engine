package org.bananaLaba.fdp.sax;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.bananaLaba.fdp.XMLProcessor;
import org.xml.sax.ContentHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public abstract class SAXProcessorCore implements XMLProcessor {

    private SAXParser parser;
    private DelegatingHandler handler = new DelegatingHandler();

    public SAXProcessorCore() {
        final SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            factory.setFeature("http://xml.org/sax/features/namespaces", true);
            this.parser = factory.newSAXParser();
        } catch (Exception e) {
            // TODO: throw a custom exception here.
            throw new RuntimeException(e);
        }
    }

    public void setContentHandler(final ContentHandler handler) {
        this.handler.setContentHandler(handler);
    }

    public void setErrorHandler(final ErrorHandler handler) {
        this.handler.setErrorHandler(handler);
    }

    public void setDocumentLocator(final Locator locator) {
        this.handler.setDocumentLocator(locator);
    }

    @Override
    public void process(final InputStream xmlStream) throws IOException {
        try {
            this.parser.parse(xmlStream, this.handler);
        } catch (SAXException e) {
            // TODO: throw a custom exception here.
            throw new RuntimeException(e);
        }
    }

}
