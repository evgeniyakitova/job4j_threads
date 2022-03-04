package ru.job4j.linked;

public final class Node<T> {
    private final Node<T> next;
    private final T value;

    public Node(Node<T> next, T value) {
        this.next = next;
        this.value = value;
    }

    public Node<T> getNext() {
        return next;
    }

    public T getValue() {
        return value;
    }

    public static void main(String[] args) {
        User user = new User(1, "first");
        Node<User> node = new Node<>(null, user);
        System.out.println(node.getValue().getName());
        user.setName("second");
        System.out.println(node.getValue().getName());
    }
}
