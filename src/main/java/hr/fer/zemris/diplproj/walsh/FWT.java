package hr.fer.zemris.diplproj.walsh;

import java.util.ArrayList;
import java.util.List;

/**
 * Uses Fast Walsh-Hadamard transform to calculate the coefficients in <code>O(n log(n))</code>.
 *
 * @author Kristijan Vulinovic
 */
public class FWT implements ITransform {
    @Override
    public List<Integer> transform(List<Integer> values) {
        List<Integer> result = new ArrayList<>();
        values.forEach(x -> result.add(x > 0 ? x : -1));
        int n = values.size();

        for (int step = 1; step < n; step *= 2){
            int blockSize = step * 2;
            for (int block = 0; block < n / blockSize; ++block){
                for (int i = block * blockSize; i < block * blockSize + step; ++i){
                    int a = result.get(i);
                    int b = result.get(i + step);

                    result.set(i, a + b);
                    result.set(i + step, a - b);
                }
            }
        }

        return result;
    }
}
