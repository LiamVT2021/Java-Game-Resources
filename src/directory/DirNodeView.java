package directory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DirNodeView {

    private List<DirNode<?>> sibList;
    private int index;

    public DirNodeView(DirNode<?> root) {
        sibList = List.of(root);
        getIndex(root);
    }

    public DirNodeView(DirNode<?> parrent, DirNode<?> node) {
        makeList(parrent);
        getIndex(node);
    }

    public DirNodeView(DirNode<?> parrent, int index) {
        makeList(parrent);
        this.index = index;
    }

    private void makeList(DirNode<?> parrent) {
        sibList = new ArrayList<DirNode<?>>(parrent.children().values());
    }

    private void getIndex(DirNode<?> node) {
        index = sibList.indexOf(node);
    }

    public DirNode<?> node() {
        return sibList.get(index);
    }

    public String peekNext() {
        return index >= sibList.size() - 1 ? null : sibList.get(index + 1).entry();
    }

    public String peekPrev() {
        return index <= 0 ? null : sibList.get(index - 1).entry();
    }

    public boolean moveNext() {
        if (index >= sibList.size() - 1)
            return false;
        index++;
        return true;
    }

    public boolean movePrev() {
        if (index <= 0)
            return false;
        index--;
        return true;
    }

    public void sort(Comparator<DirNode<?>> comp) {
        DirNode<?> node = node();
        sibList.sort(comp);
        getIndex(node);
    }

}
