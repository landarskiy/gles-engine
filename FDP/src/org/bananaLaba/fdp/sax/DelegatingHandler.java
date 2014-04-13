package org.bananaLaba.fdp.sax;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class DelegatingHandler extends DefaultHandler {

    private ContentHandler contentHandler;
    private ErrorHandler errorHandler;

    public void setContentHandler(final ContentHandler handler) {
        this.contentHandler = handler;
    }

    public void setErrorHandler(final ErrorHandler handler) {
        this.errorHandler = handler;
    }

    @Override
    public void setDocumentLocator(final Locator locator) {
        if (this.contentHandler != null) {
            this.contentHandler.setDocumentLocator(locator);
        }
    }

    @Override
    public void startDocument() throws SAXException {
        if (this.contentHandler != null) {
            this.contentHandler.startDocument();
            ;
        }
    }

    @Override
    public void endDocument() throws SAXException {
        if (this.contentHandler != null) {
            this.contentHandler.endDocument();
        }
    }

    @Override
    public void startPrefixMapping(final String prefix, final String uri)
        throws SAXException {
        if (this.contentHandler != null) {
            this.contentHandler.startPrefixMapping(prefix, uri);
        }
    }

    @Override
    public void endPrefixMapping(final String prefix) throws SAXException {
        if (this.contentHandler != null) {
            this.contentHandler.endPrefixMapping(prefix);
        }
    }

    @Override
    public void startElement(final String uri, final String localName,
        final String qName, final Attributes atts) throws SAXException {
        if (this.contentHandler != null) {
            this.contentHandler.startElement(uri, localName, qName, atts);
        }
    }

    @Override
    public void endElement(final String uri, final String localName,
        final String qName) throws SAXException {
        if (this.contentHandler != null) {
            this.contentHandler.endElement(uri, localName, qName);
        }
    }

    @Override
    public void characters(final char[] ch, final int start, final int length)
        throws SAXException {
        if (this.contentHandler != null) {
            this.contentHandler.characters(ch, start, length);
        }
    }

    @Override
    public void ignorableWhitespace(final char[] ch, final int start,
        final int length) throws SAXException {
        if (this.contentHandler != null) {
            this.contentHandler.ignorableWhitespace(ch, start, length);
        }
    }

    @Override
    public void processingInstruction(final String target, final String data)
        throws SAXException {
        if (this.contentHandler != null) {
            this.contentHandler.processingInstruction(target, data);
        }
    }

    @Override
    public void skippedEntity(final String name) throws SAXException {
        if (this.contentHandler != null) {
            this.contentHandler.skippedEntity(name);
        }
    }

    @Override
    public void error(final SAXParseException e) throws SAXException {
        if (this.errorHandler != null) {
            this.errorHandler.error(e);
        }
    }

    @Override
    public void fatalError(final SAXParseException e) throws SAXException {
        if (this.errorHandler != null) {
            this.errorHandler.fatalError(e);
        }
    }

    @Override
    public void warning(final SAXParseException e) throws SAXException {
        if (this.errorHandler != null) {
            this.errorHandler.warning(e);
        }
    }

}
