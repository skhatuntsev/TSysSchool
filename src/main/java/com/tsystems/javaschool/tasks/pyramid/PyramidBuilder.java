package com.tsystems.javaschool.tasks.pyramid;

import java.util.Collections;
import java.util.List;

public class PyramidBuilder {


    public int[][] buildPyramid(List<Integer> input) {
        if (input.contains(null) || (input.size() > 1024)) throw new CannotBuildPyramidException();
        Collections.sort(input);

        int size = input.size();
        int height = 0;
        while (size > 0) {
            size -= height + 1;
            if (size < 0) {
                throw new CannotBuildPyramidException();
            } else {
                height++;
            }

        }

        int width = height * 2 - 1;
        int[][] pyramidMatrix = new int[height][width];
        int link = 0;
        for (int i = 0; i < height; i++) {
            int l = height - i - 1;
            for (int j = 0; j <= i; j++) {
                pyramidMatrix[i][l] = input.get(link);
                link++;
                l = l + 2;
            }
        }

        return pyramidMatrix;
    }


}
