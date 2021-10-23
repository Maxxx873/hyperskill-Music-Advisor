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
    String categoryName;
    final String KEY_WORD_PLAYLIST = "playlists ";

    public ViewerImpl(Scanner scanner) {
        state = StateViewer.INPUT_COMMAND;
        this.scanner = scanner;
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

    public String getCategoryName() {
        return categoryName;
    }

    @Override
    public Viewer handle() {

        while (!getState().equals(StateViewer.EXIT)) {
            String command;

            if (getState().equals(StateViewer.INPUT_COMMAND)) {
                command = scanner.nextLine();
                switch (command) {
                    case "auth":
                        state = StateViewer.AUTH;
                        return this;
                    case "categories":
                        state = StateViewer.CATEGORIES;
                        return this;
                    case "new":
                        state = StateViewer.NEW;
                        return this;
                    case "featured":
                        state = StateViewer.FEATURED;
                        return this;
                    case  "exit":
                        state = StateViewer.EXIT;
                        return this;
                    }
                if (command.contains(KEY_WORD_PLAYLIST)) {
                    state = StateViewer.PLAYLISTS;
                    categoryName = command.substring(KEY_WORD_PLAYLIST .length());
                    return this;
                }

                }


            while (getState().equals(StateViewer.FIRST) ||
                    getState().equals(StateViewer.INTERMEDIATE) ||
                    getState().equals(StateViewer.LAST)) {
                printPage();
                command = scanner.nextLine();
                if (command.contains("playlists ")) {
                    state = StateViewer.PLAYLISTS;
                    return this;
                }
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
                    case "auth":
                        state = StateViewer.AUTH;
                        return this;
                    case "categories":
                        state = StateViewer.CATEGORIES;
                        return this;
                    case "featured":
                        state = StateViewer.FEATURED;
                        return this;
                    case "new":
                        state = StateViewer.NEW;
                        return this;
                    case "exit":
                        state = StateViewer.EXIT;
                        return this;
                }

            }
        }

        return this;
    }

    @Override
    public StateViewer getState() {
        return state;
    }

    @Override
    public void setState(StateViewer state) {
        this.state = state;
    }
}
