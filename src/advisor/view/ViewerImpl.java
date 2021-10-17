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

    public ViewerImpl() {
        state = StateViewer.AUTH;
    }

    @Override
    public void viewerSet(List t, String recordsPerPage, Scanner scanner) {
        this.content = t;
        this.scanner = scanner;
        this.recordsPerPage = Integer.parseInt(recordsPerPage);
        this.state = StateViewer.FIRST;
        this.currentPageNumber = FIRST_PAGE;
        int pages = Integer.parseInt(recordsPerPage);
        if (pages > 0 && pages <= t.size()) {
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
            String command = scanner.nextLine();
            switch (command) {
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
            }
            if (command.equals("exit")) {
                state = StateViewer.EXIT;
                return this;
            }

        }

        return this;
    }

    @Override
    public StateViewer getState() {
        return state;
    }

    public void setState(StateViewer state) {
        this.state = state;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getRecordsPerPage() {
        return recordsPerPage;
    }

    public void setRecordsPerPage(int recordsPerPage) {
        this.recordsPerPage = recordsPerPage;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getCurrentPageNumber() {
        return currentPageNumber;
    }

    public void setCurrentPageNumber(int currentPageNumber) {
        this.currentPageNumber = currentPageNumber;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getFIRST_PAGE() {
        return FIRST_PAGE;
    }
}
