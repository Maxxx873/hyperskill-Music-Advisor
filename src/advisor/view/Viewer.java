package advisor.view;


import java.util.List;
import java.util.Scanner;

public interface Viewer <T> {

    void viewerSet(List t, String recordsPerPage, Scanner scanner);

    void printPage ();

    Viewer handle();
    StateViewer getState();
    void setState(StateViewer state);
    String getCategoryName();
}
