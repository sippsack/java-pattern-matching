package de.sippsack.list.fp;

public sealed interface LinkedList<T> {
    record Element<T>(T value, LinkedList<T> next) implements LinkedList<T> {
        @Override
        public String toString() {
            return "[%s] --> %s".formatted(value, next);
        }
    }

    final class Empty<T> implements LinkedList<T> {
        @Override
        public String toString() {
            return "NIL";
        }
    }

    static LinkedList EMPTY = new Empty<>();

    public static void main(String[] args) {
        LinkedList<Integer> list = of(1, 2, 3);
        System.out.println(list);
        System.out.println(head(list));
        System.out.println(tail(list));
        System.out.println(contains(5, list));
        System.out.println(contains(1, list));
    }

    @SafeVarargs
    static <T> LinkedList<T> of(T... values) {
        if (values.length == 0) return LinkedList.EMPTY;

        LinkedList<T> current = LinkedList.EMPTY;
        for (int i = values.length - 1; i >= 0; i--) {
            current = new LinkedList.Element<>(values[i], current);
        }
        return current;
    }

    static <T> T head(LinkedList<T> list) {
        // TODO
        return null;
    }

    static <T> LinkedList<T> tail(LinkedList<T> list) {
        // TODO
        return LinkedList.of();
    }

    static <T> boolean contains(T value, LinkedList<T> list) {
        // TODO
        return false;
    }
}
