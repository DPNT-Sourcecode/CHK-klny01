package befaster.solutions.CHK;

import befaster.runner.SolutionNotImplementedException;

import java.util.HashMap;
import java.util.Map;

public class CheckoutSolution {
    public Integer checkout(String skus) {

        if (skus == null){
            return -1;
        }

        Map<Character, Integer> itemCounts = new HashMap<>();

        for(char sku : skus.toCharArray()){
            itemCounts.put(sku, itemCounts.getOrDefault(sku, 0) + 1);
        }

        int totalPrice = 0;

        for(Map.Entry<Character, Integer> entry : itemCounts.entrySet()){
            
        }

    }
}


