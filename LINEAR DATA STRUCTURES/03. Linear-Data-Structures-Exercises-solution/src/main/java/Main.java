import implementations.ArrayDeque;
import implementations.BalancedParentheses;
import implementations.DoublyLinkedList;
import implementations.ReversedList;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //{[(])}
//        String input = "{[()]}";
//        BalancedParentheses balancedParentheses = new BalancedParentheses(input);
//        System.out.println(balancedParentheses.solve());
        ReverseddList<Integer> reverseddList = new ReverseddList<>();
        reverseddList.add(1);
        reverseddList.add(2);
        reverseddList.add(3);
        reverseddList.add(4);
        reverseddList.add(5);

        reverseddList.removeAt(1);
        //System.out.println(reverseddList.capacity());
        //System.out.println(reverseddList.size());
        for (Integer integer : reverseddList) {
            System.out.println(integer);
        }

        ReversedList<Integer> reversedList = new ReversedList<>();
        reversedList.add(1);
        reversedList.add(2);
        reversedList.add(3);
        reversedList.add(4);
        reversedList.add(5);


        reversedList.removeAt(1);

        //System.out.println(reversedList.capacity());
        //System.out.println(reversedList.size());
        for (Integer integer : reversedList) {
            System.out.println(integer);
        }

        Reversedd<Integer> list = new Reversedd<>();
        list.get(-1);
    }
}
