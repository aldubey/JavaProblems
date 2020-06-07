package src.leetcode;

import java.util.LinkedList;
import java.util.List;

public class MyStack<T> {

    private List<T> myStack = new LinkedList<>();

    public void push(T item) {
        myStack.add(item);
    }

    public void pop() {
        myStack.remove(myStack.size() - 1);
    }

    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(1);
        stack.push(11);
        stack.push(12);
        stack.push(13);
        System.out.println(stack.toString());
        stack.pop();
        System.out.println(stack.toString());


    }

    @Override
    public String toString() {
        return "MyStack{" +
                "myStack=" + myStack +
                '}';
    }
}
