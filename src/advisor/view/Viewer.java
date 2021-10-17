package advisor.view;

import java.util.ArrayList;
import java.util.List;

public interface Viewer {

    void printPage (int pageNumber);

    Viewer handle();
    Viewer getState();
}
