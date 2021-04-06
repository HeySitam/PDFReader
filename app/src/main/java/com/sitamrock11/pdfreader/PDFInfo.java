package com.sitamrock11.pdfreader;

public class PDFInfo {
    public PDFInfo(String pdfName) {
        this.pdfName = pdfName;
    }

    String pdfName;
    String pdfPath;
    public String getPdfName() {
        return pdfName;
    }

    public void setPdfName(String pdfName) {
        this.pdfName = pdfName;
    }

    public String getPdfPath() {
        return pdfPath;
    }

    public void setPdfPath(String pdfPath) {
        this.pdfPath = pdfPath;
    }
}
