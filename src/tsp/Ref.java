package tsp;

/**
 * @author Voki
 */
public class Ref<T> {

    public T object;

    public Ref() {
    }

    public Ref(T object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return object.toString();
    }
    
}
