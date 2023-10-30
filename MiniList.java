package ass2;

import java.util.NoSuchElementException;

public class MiniList<E> {
    private Node head;
    private Node tail;
    public MiniList() {
        this.head = null;
        this.tail = null;
    }

    public E getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("the list is empty");
        }
        return this.head.data;
    }

    public E getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("the list is empty");
        }
        return this.tail.data;
    }

    private class Node {
        public E data;
        public Node next;

        public Node(E data, Node next) {
            this.data = data;
            this.next = next;
        }


        public String toString() {
            return this.data.toString();
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    public static void main(String[] args) {
        MiniList<String> list = new MiniList<String>();
        MiniList<String>.Node thirdNode = list.new Node("third", null);
        MiniList<String>.Node secondNode = list.new Node("second", thirdNode);
        MiniList<String>.Node firstNode = list.new Node("first", secondNode);
        list.head = firstNode;
        list.tail = thirdNode;
        list.addFirst("a");
        list.addFirst("b");
        System.out.println(list.isEmpty());
        list.printList();
        System.out.println("-------------");
        list.removeFirst();
        list.printList();
        System.out.println("-------------");
        list.addLast("c");
        list.printList();
        System.out.println("-------------");
        list.removeLast();
        list.printList();

    }

    public void addFirst(E element){
        head = new Node(element, head);
        if(this.tail == null){
            this.tail = this.head;
        }
    }

    private void printList(){
        Node node = this.head;
        while(node != null){
            System.out.println(node);
            node = node.next;
        }
    }

    public E removeFirst(){
        if(isEmpty()) {
            throw new NoSuchElementException("the list is empty");
        }
        E removed = head.data;
        this.head = this.head.next;
        if(this.head == null){
            this.tail = null;
        }
        return removed;
    }

    public void addLast(E element){
        Node newNode = new Node(element, null);
        if(isEmpty()){
            this.addFirst(element);
            this.head = newNode = this.tail;
        }else{
            this.tail.next = newNode;
            this.tail = newNode;
        }
        //while(newNode.next != null){
        //    newNode = newNode.next;
        //}
        //newNode.next = new Node(element, null);
    }

    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("the list is empty");
        }

        if (this.head.next == null) {
            return this.removeFirst();
        }

        Node newNode2 = this.head;
        while (newNode2.next.next != null) {
            newNode2 = newNode2.next;
        }

        E removed2 = newNode2.next.data;
        newNode2.next = null;
        return removed2;
    }
}