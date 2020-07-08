package solutions;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class CookiesProblem {
    public Integer solve(int requireSweetness, int[] cookiesSweetness) {
        Queue<Integer> cookies = new PriorityQueue<>();
        for (int sweetness : cookiesSweetness) {
            cookies.add(sweetness);
        }

        int currentMinSweetness = cookies.peek();
        int steps = 0;
        while (currentMinSweetness < requireSweetness && cookies.size()>1){
            int lastSweetCookie = cookies.poll();
            int secondSweetCookie = cookies.poll();
            int combinedSweetness = lastSweetCookie + 2 * secondSweetCookie;
            cookies.add(combinedSweetness);
            currentMinSweetness = cookies.peek();
            steps++;
        }

        return currentMinSweetness > requireSweetness ? steps: -1;

    }
}
