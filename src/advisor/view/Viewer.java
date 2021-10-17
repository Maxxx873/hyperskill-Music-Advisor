package advisor.view;


public interface Viewer {

    void printPage ();

    Viewer handle();
    StateViewer getState();
}
