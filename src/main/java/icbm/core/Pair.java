package icbm.core;

public class Pair<T> {
    private final T left;
    private final T right;

    public Pair(final T left, final T right) {
        this.left = left;
        this.right = right;
    }

    public T getKey() {
        return this.left;
    }

    public T getValue() {
        return this.right;
    }

    @Override
    public int hashCode() {
        return this.left.hashCode() ^ this.right.hashCode();
    }

    @Override
    public boolean equals(final Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof Pair)) {
            return false;
        }

        final Pair<?> pairo = (Pair<?>) o;
        return this.left.equals(pairo.getKey()) && this.right.equals(pairo.getValue());
    }
}
