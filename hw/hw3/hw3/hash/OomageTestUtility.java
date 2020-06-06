package hw3.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        Map<Integer, Integer> hashMap = new HashMap<>();

        for (Oomage oo : oomages) {
            int bucketNum = (oo.hashCode() & 0x7FFFFFFF) % M;
            if (hashMap.containsKey(bucketNum)) {
                int numInBucket = hashMap.get(bucketNum);
                hashMap.put(bucketNum, numInBucket+1);
            } else {
                hashMap.put(bucketNum, 1);
            }
        }

        int N = oomages.size();
        for (int bucketKey : hashMap.keySet()) {
            int numInBucket = hashMap.get(bucketKey);
            if (numInBucket < N / 50) return false;
            if (numInBucket > N / 2.5) return false;
        }

        return true;
    }
}
