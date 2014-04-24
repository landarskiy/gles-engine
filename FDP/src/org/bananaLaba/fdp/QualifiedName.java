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

    @Override
    public int hashCode() {
        int result = 7;
        int hash = (this.name == null ? 0 : this.name.hashCode());
        result = 31 * result + hash;

        hash = (this.uri == null ? 0 : this.uri.hashCode());
        result = 31 * result + hash;

        return result;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == null) {
            return false;
        }

        if (o.getClass() != QualifiedName.class) {
            return false;
        }

        final QualifiedName qName = (QualifiedName) o;

        String string1 = this.name == null ? "" : this.name;
        String string2 = qName.name == null ? "" : qName.name;

        if (!string1.equals(string2)) {
            return false;
        }

        string1 = this.uri == null ? "" : this.uri;
        string2 = qName.uri == null ? "" : qName.uri;

        return string1.equals(string2);
    }

    @Override
    public String toString() {
        return (this.uri == null ? "" : this.uri.concat(":")).concat(this.name == null ? "" : this.name);
    }

}
