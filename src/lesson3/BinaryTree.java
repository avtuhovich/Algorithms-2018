package lesson3;

import kotlin.NotImplementedError;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

// Attention: comparable supported but comparator is not
@SuppressWarnings("WeakerAccess")
public class BinaryTree<T extends Comparable<T>> extends AbstractSet<T> implements CheckableSortedSet<T> {

    private BinaryTree(Node<T> newRoot, T left, T right) {
        this.root = newRoot;
        this.left = left;
        this.right = right;
    }

    public BinaryTree() {
    }

    private static class Node<T> {
        T value;

        Node<T> left = null;

        Node<T> right = null;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root = null;

    private T left = null;
    private T right = null;

    private int size = 0;

    @Override
    public boolean add(T t) {
        Node<T> closest = find(t);
        int comparison = closest == null ? -1 : t.compareTo(closest.value);
        if (comparison == 0) {
            return false;
        }
        Node<T> newNode = new Node<>(t);
        if (closest == null) {
            root = newNode;
        } else if (comparison < 0) {
            assert closest.left == null;
            closest.left = newNode;
        } else {
            assert closest.right == null;
            closest.right = newNode;
        }
        size++;
        return true;
    }

    public boolean checkInvariant() {
        return root == null || checkInvariant(root);
    }

    private boolean checkInvariant(Node<T> node) {
        Node<T> left = node.left;
        if (left != null && (left.value.compareTo(node.value) >= 0 || !checkInvariant(left))) return false;
        Node<T> right = node.right;
        return right == null || right.value.compareTo(node.value) > 0 && checkInvariant(right);
    }

    /**
     * Удаление элемента в дереве
     * Средняя
     * Трудоемкость  - O(nlogn)
     * Ресурсоемкость  - R(1)
     */
    @Override
    public boolean remove(Object o) {
        Node<T> x = root, y = null;
        while (x != null) {
            if (((T) o).compareTo(x.value) == 0)
                break;
            else {
                y = x;
                if (((T) o).compareTo(x.value) < 0)
                    x = x.left;
                else
                    x = x.right;
            }
        }
        if (x == null)
            return false;
        remove(y, x);
        return true;
    }

    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        if ((left != null && left.compareTo(t) > 0) || (right != null && right.compareTo(t) <= 0))
            return false;
        Node<T> closest = find(t);
        return closest != null && t.compareTo(closest.value) == 0;
    }

    private Node<T> find(T value) {
        if (root == null) return null;
        return find(root, value);
    }

    private Node<T> find(Node<T> start, T value) {
        int comparison = value.compareTo(start.value);
        if (comparison == 0) {
            return start;
        } else if (comparison < 0) {
            if (start.left == null) return start;
            return find(start.left, value);
        } else {
            if (start.right == null) return start;
            return find(start.right, value);
        }
    }

    public class BinaryTreeIterator implements Iterator<T> {

        private Node<T> current = null;
        private Queue<Node<T>> work = new LinkedList<>();

        private BinaryTreeIterator() {
            if (root == null)
                throw new NoSuchElementException();
            createQueue(root);
            if (left != null)
                while (left.compareTo(work.peek().value) > 0)
                    work.poll();
        }

        private void createQueue(Node<T> node) {
            if (node.left != null)
                createQueue(node.left);
            work.add(node);
            if (node.right != null)
                createQueue(node.right);
        }

        /**
         * Поиск следующего элемента
         * Средняя
         * Трудоемкость  - O(n)
         * Ресурсоемкость  - R(1)
         */
        private Node<T> findNext() {
            current = work.poll();
            return current;
        }

        @Override
        public boolean hasNext() {
            if (right != null && right.compareTo(work.peek().value) < 0)
                work.clear();
            return work.size() > 0;
        }

        @Override
        public T next() {
            if (findNext() == null) throw new NoSuchElementException();
            return current.value;
        }

        /**
         * Удаление следующего элемента
         * Сложная
         * Ресудсоемкость - O(nlogn)
         * Трудоемкость - R(1)
         */
        @Override
        public void remove() {
            if (current == null)
                throw new NoSuchElementException();
            Node<T> parent = findParent(current);
            BinaryTree.this.remove(parent, current);
            size--;
        }

        @Nullable
        private Node<T> findParent(Node<T> node) {
            Node<T> search = root;
            while (search.left != node && search.right != node) {
                if (node.value.compareTo(search.value) < 0) {
                    if (search.left != null)
                        search = search.left;
                    else
                        return null;
                } else {
                    if (search.right != null)
                        search = search.right;
                    else
                        return null;
                }
            }
            return search;
        }
    }

    private void remove(Node<T> parent, Node<T> current) {
        if (current.right == null) {
            if (parent == null) {
                root = current.left;
            } else {
                if (current == parent.left) {
                    parent.left = current.left;
                } else {
                    parent.right = current.left;
                }
            }
        } else {
            Node<T> leftMost = current.right;
            parent = null;
            while (leftMost.left != null) {
                parent = leftMost;
                leftMost = leftMost.left;
            }
            if (parent != null) {
                parent.left = leftMost.right;
            } else {
                current.right = leftMost.right;
            }
            current.value = leftMost.value;
        }
        size--;
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator();
    }

    @Override
    public int size() {
        int s = 0;
        for (T val : this) {
            if (left == null || left.compareTo(val) <= 0)
                if (right == null || right.compareTo(val) > 0)
                    s++;
        }
        return s;
    }

    @Nullable
    @Override
    public Comparator<? super T> comparator() {
        return null;
    }

    /**
     * Для этой задачи нет тестов (есть только заготовка subSetTest), но её тоже можно решить и их написать
     * Очень сложная
     * Трудоемкость  - O(n)
     * Ресурсоемкость  - R(1)
     */
    @NotNull
    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        return new BinaryTree<T>(root, fromElement, toElement);
    }

    /**
     * Найти множество всех элементов меньше заданного
     * Сложная
     * Трудоемкость  - O(n)
     * Ресурсоемкость  - R(1)
     */
    @NotNull
    @Override
    public SortedSet<T> headSet(T toElement) {
        return new BinaryTree<T>(root, null, toElement);
    }

    /**
     * Найти множество всех элементов больше или равных заданного
     * Сложная
     * Трудоемкость  - O(n)
     * Ресурсоемкость  - R(1)
     */
    @NotNull
    @Override
    public SortedSet<T> tailSet(T fromElement) {
        return new BinaryTree<T>(root, fromElement, null);
    }

    @Override
    public T first() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    @Override
    public T last() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }
}