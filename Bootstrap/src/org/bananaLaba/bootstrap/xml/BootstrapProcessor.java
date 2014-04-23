package org.bananaLaba.bootstrap.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Stack;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.bananaLaba.bootstrap.common.AttributeMap;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class BootstrapProcessor {

    private SAXParser parser;

    public BootstrapProcessor() {
        final SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            factory.setFeature("http://xml.org/sax/features/namespaces", true);
            this.parser = factory.newSAXParser();
        } catch (Exception e) {
            // TODO: throw a custom exception here.
            throw new RuntimeException(e);
        }
    }

    public void process(final InputStream xmlStream, final TagLogicsIterator modelIterator) throws IOException {
        try {
            this.parser.parse(xmlStream, new DefaultHandler() {

                // TODO: re-design TagLogicsIterator to completely hide separate tag handlers and their stack from
                // BootstrapProcessor.
                private Stack<TagLogics> logicsStack = new Stack<>();

                @Override
                public void startElement(final String uri, final String localName,
                    final String qName, final Attributes atts) throws SAXException {
                    final TagLogics logics = modelIterator.enterTag(uri, localName);
                    logics.handle(new AttributeMap() {

                        @Override
                        public String getAttribute(final String name) {
                            if (atts.getIndex(name) < 0) {
                                // TODO: throw a custom exception here.
                                throw new IllegalArgumentException("The \"" + name + "\" attribute is missing!");
                            }

                            return atts.getValue(name);
                        }

                        @Override
                        public boolean isPresent(final String name) {
                            return (atts.getIndex(name) != -1);
                        }

                    });

                    this.logicsStack.push(logics);
                }

                @Override
                public void endElement(final String uri, final String localName,
                    final String qName) throws SAXException {
                    this.logicsStack.pop();
                    modelIterator.leaveTag();
                }

                @Override
                public void startPrefixMapping(final String prefix, final String uri)
                    throws SAXException {
                    modelIterator.enterNamespace(uri, prefix);
                }

                @Override
                public void endPrefixMapping(final String prefix) throws SAXException {
                    modelIterator.leaveNamespace(prefix);
                }

                @Override
                public void characters(final char[] data, final int offset, final int length) {
                    if (!this.logicsStack.isEmpty()) {
                        this.logicsStack.peek().handleCharacterData(new String(data, offset, length));
                    }
                }

            });
        } catch (SAXException e) {
            throw new RuntimeException("SAX parser has failed!", e);
        }
    }

}
