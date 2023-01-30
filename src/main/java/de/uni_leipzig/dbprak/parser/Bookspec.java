package de.uni_leipzig.dbprak.parser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "bookspec")
public class Bookspec {

    @JacksonXmlProperty
    private int pages;

    private Publication publication;

    private Isbn isbn;

    public Bookspec() {
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public Isbn getIsbn() {
        return isbn;
    }

    public void setIsbn(Isbn isbn) {
        this.isbn = isbn;
    }
}
