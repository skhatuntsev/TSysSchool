package com.tsystems.javaschool.tasks.subsequence;


import java.util.List;

public class Subsequence {

    public boolean find(List list1, List list2) {
        if ((list1 == null) || (list2 == null)) {
            throw new IllegalArgumentException();
        }
        else {
            if (list1.isEmpty()) return true;
            if (list2.isEmpty()) return false;

            int index = 0;
            boolean result = false;
            for (int i = 0; i < list1.size(); i++) {
                if (!list2.contains(list1.get(i))) {
                    result = false;
                    break;
                } else {
                    int j = index;
                    while (j < list2.size()) {

                        if (list1.get(i) != list2.get(j)) {
                            j++;
                            if (result==true) {
                                result = false;
                            }
                        } else {
                            result = true;
                            index = j;
                            break;
                        }
                    }
                    if ((j == list2.size()) && (i != list1.size())) {
                        result = false;
                        break;
                    }
                }
            }
            return result;

        }
    }
}