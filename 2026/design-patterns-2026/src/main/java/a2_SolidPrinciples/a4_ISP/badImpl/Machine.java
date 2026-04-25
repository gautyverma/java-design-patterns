package a2_SolidPrinciples.a4_ISP.badImpl;

import a2_SolidPrinciples.a4_ISP.Document;

interface Machine {

    void print(Document document);
    void scan(Document document);
    void fax(Document document);
    void copy(Document document);

}
