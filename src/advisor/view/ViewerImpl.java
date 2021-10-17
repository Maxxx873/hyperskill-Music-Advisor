package advisor.view;
import java.util.List;
import java.util.Scanner;

public class ViewerImpl <T> implements Viewer {
    List<T> content;
    int recordsPerPage;
    int pageCount;
    StateViewer state;
    int currentPageNumber;
    Scanner scanner;
    final int FIRST_PAGE = 1;


    public ViewerImpl(List<T> t, String recordsPerPage, Scanner scanner) {
        this.content = t;
        this.scanner = scanner;
        this.recordsPerPage = Integer.parseInt(recordsPerPage);
        this.state = StateViewer.FIRST;
        this.currentPageNumber = FIRST_PAGE;
        int pages = Integer.parseInt(recordsPerPage);
        if (pages > 0 && pages < t.size()) {
            this.pageCount = t.size() / pages;
        } else {
            this.pageCount = FIRST_PAGE;
        }

    }

    public void printPage() {
        int skip = currentPageNumber * recordsPerPage - recordsPerPage;
        content.stream().skip(skip).limit(recordsPerPage)
                .map(Object::toString)
                .forEach(System.out::println);
        System.out.println("---PAGE " + currentPageNumber + " OF " + pageCount + "---");
    }

    @Override
    public Viewer handle() {

        while (getState().equals(StateViewer.FIRST) ||
                getState().equals(StateViewer.INTERMEDIATE) ||
                getState().equals(StateViewer.LAST)) {
            printPage();
            switch (scanner.nextLine()) {
                case "next":
                    currentPageNumber++;
                    if (currentPageNumber >= pageCount) {
                        state = StateViewer.LAST;
                        currentPageNumber = pageCount;
                        System.out.println("No more pages.");
                    }
                    break;
                case "prev":
                    currentPageNumber--;
                    if (currentPageNumber <= FIRST_PAGE) {
                        state = StateViewer.LAST;
                        currentPageNumber = FIRST_PAGE;
                        System.out.println("No more pages.");
                    }
                    break;
                default:

            }

        }

        return this;
    }

    @Override
    public StateViewer getState() {
        return state;
    }


}
