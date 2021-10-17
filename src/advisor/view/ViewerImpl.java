package advisor.view;
import java.util.List;

public class ViewerImpl <T> implements Viewer {
    List<T> content;
    int recordsPerPage;
    int pageCount;
    StateViewer stateViewer;
    int currentPageNumber;

    public ViewerImpl(List<T> t, String recordsPerPage) {
        this.content = t;
        this.recordsPerPage = Integer.parseInt(recordsPerPage);
        this.stateViewer = StateViewer.FIRST;
        this.currentPageNumber = 1;
        int pages = Integer.parseInt(recordsPerPage);
        if (pages > 0 && pages < t.size()) {
            this.pageCount = t.size() / pages;
        } else {
            this.pageCount = 1;
        }

    }

    public void printPage(int pageNumber) {
        content.stream().limit(recordsPerPage)
                .map(Object::toString)
                .forEach(System.out::println);
        System.out.println("---PAGE " + currentPageNumber + " OF " + pageCount + "---");
    }

    @Override
    public Viewer handle() {
        return null;
    }

    @Override
    public Viewer getState() {
        return null;
    }
}
