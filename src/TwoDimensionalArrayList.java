import java.util.ArrayList;

class TwoDimensionalArrayList<T> extends ArrayList<ArrayList<T>> {
    public void addToInnerArray(int index, T element) {
        while (index >= this.size()) {
            this.add(new ArrayList<T>());
        }
        this.get(index).add(element);
    }

    public void addToInnerArray(int index, int index2, T element) {
        while (index >= this.size()) {
            this.add(new ArrayList<T>());
        }

        ArrayList<T> inner = this.get(index);
        while (index2 >= inner.size()) {
            inner.add(null);
        }

        inner.set(index2, element);
    }

    public T getFromInnerArray(int index, int index2) {
        T object = null;
        if (index < this.size()) {
            if (index2 < this.get(index).size()) {

                object = this.get(index).get(index2);
            }
        }
        return object;
    }

    public void removeFromInnerArray(int index, int index2) {
        this.get(index).remove(index2);
    }
}