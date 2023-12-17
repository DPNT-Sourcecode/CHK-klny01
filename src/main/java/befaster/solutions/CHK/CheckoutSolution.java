package befaster.solutions.CHK;

import befaster.runner.SolutionNotImplementedException;

import java.util.HashMap;
import java.util.Map;

public class CheckoutSolution {
    private static Map<Character, Integer> prices = new HashMap<>();
    private static Map<Character, SpecialOffer> specialOffers= new HashMap<>();

    static {
        prices.put('A', 50);
        prices.put('B', 30);
        prices.put('C', 20);
        prices.put('D', 15);

        specialOffers.put('A', new SpecialOffer(3, 130));
        specialOffers.put('B', new SpecialOffer(2, 45));
    }

    public Integer checkout(String skus) {

        if (skus == null){
            return -1;
        }
        //int totalPrice = 0;
//        for(char sku : skus.toCharArray()){
//            if(prices.containsKey(sku)){
//
//            }
//        }

        Map<Character, Integer> itemCounts = new HashMap<>();

        for(char sku : skus.toCharArray()){
//            if(sku != 'A' || sku != 'B' || sku != 'C' ||sku != 'D'){
//                return -1;
//            }
            itemCounts.put(sku, itemCounts.getOrDefault(sku, 0) + 1);
        }

        int totalPrice = 0;

        for(Map.Entry<Character, Integer> entry : itemCounts.entrySet()) {
            char sku = entry.getKey();
            int count = entry.getValue();

            if(!prices.containsKey(sku) || count < 0){
                return -1;
            }

            int price = prices.get(sku);
            SpecialOffer specialOffer = specialOffers.getOrDefault(sku, null);

            if (specialOffer != null){
                int specialOfferCount = specialOffer.getCount();
                int specialOfferPrice = specialOffer.getPrice();
                totalPrice += (count / specialOfferCount) * specialOfferPrice + (count % specialOfferCount) * price;
            }
            else{
               totalPrice += count * price;
            }

        }

        return totalPrice;

    }


    private static class SpecialOffer{
        private int count;
        private int price;

        public SpecialOffer(int count, int price){
            this.count = count;
            this.price = price;
        }

        public int getCount(){
            return count;
        }

        public int getPrice(){
            return price;
        }
    }
}



