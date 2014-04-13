package org.bananaLaba.fdp;

public class QualifiedName {

    private String uri;
    private String name;

    public QualifiedName(final String uri, final String name) {
        this.uri = uri;
        this.name = name;
    }

    public String getUri() {
        return this.uri;
    }

    public void setUri(final String uri) {
        this.uri = uri;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

}
