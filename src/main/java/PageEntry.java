public class PageEntry implements Comparable<PageEntry> {
    private final String pdfName;
    private final int page;
    private final int count;

    public PageEntry(String pdfName, int page, int count) {
        this.pdfName = pdfName;
        this.page = page;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "pdfName " + pdfName + " page " + page + " count " + count;
    }

    @Override
    public int compareTo(PageEntry o) {
        if(count == o.getCount())
            return 0;
        else if(count < o.getCount())
            return 1;
        else
            return -1;
    }
}
