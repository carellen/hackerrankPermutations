import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {

    public static List<List<Integer>> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numLength = Integer.parseInt(reader.readLine());
        List<Integer> list = new ArrayList<>();
        for (String s : reader.readLine().split(" ")) {
            list.add(Integer.parseInt(s));
        }
        reader.close();
        Collections.sort(list);
        if (numLength > 1 && numLength <= 3000 && list.get(0) >= 0 && list.get(numLength - 1) <= 1000000000) {
            my_permutationOf(list, numLength, null, true);
            int maxValOfPerm = 0;
            int resMinSum = Integer.MAX_VALUE;
            for(List<Integer> l : result) {
                int minSum = 0;
                int currMaxValOfPerm = 0;
                for(int i = 0 ; i < numLength - 1 ; i++) {
                    int currVal = l.get(i)^l.get(i + 1);
                    minSum += currVal;
                    if (currVal > currMaxValOfPerm) {
                        currMaxValOfPerm = currVal;
                    }
                }
                if(minSum < resMinSum) {
                    resMinSum = minSum;
                    maxValOfPerm = currMaxValOfPerm;
                }
            }
            System.out.println(maxValOfPerm);
        }

    }
    public static void my_permutationOf(List<Integer> uniqueList, int permutationSize, List<Integer> permutation, boolean only) {
        if (permutation == null) {
            assert 0 < permutationSize && permutationSize <= uniqueList.size();
            permutation = new ArrayList<>(permutationSize);
            if (!only) {
                result.add(new ArrayList<>(permutation));
            }
        }
        for (int i : uniqueList) {
            if (permutation.contains(i)) {
                continue;
            }
            permutation.add(i);
            if (!only) {
                result.add(new ArrayList<>(permutation));
            } else if (permutation.size() == permutationSize) {
                result.add(new ArrayList<>(permutation));
            }
            if (permutation.size() < permutationSize) {
                my_permutationOf(uniqueList, permutationSize, permutation, only);
            }
            permutation.remove(permutation.size() - 1);
        }
    }
}