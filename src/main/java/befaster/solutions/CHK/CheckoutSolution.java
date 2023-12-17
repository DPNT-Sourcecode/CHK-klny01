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
        prices.put('E', 40);

        specialOffers.put('A', new SpecialOffer(3, 130));
        specialOffers.put('A', new SpecialOffer(5, 200));
        specialOffers.put('B', new SpecialOffer(2, 45));
        specialOffers.put('E', new SpecialOffer(2, 0));

    }

    public Integer checkout(String skus) {

        if (skus == null){
            return -1;
        }

        Map<Character, Integer> itemCounts = new HashMap<>();
        Map<Character, Integer> freeItemCount = new HashMap<>();

        for(char sku : skus.toCharArray()){
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
            //SpecialOffer specialOffer = specialOffers.getOrDefault(sku, null);
            SpecialOffer specialOffer = specialOffers.getOrDefault(sku, new SpecialOffer(1, 0, '\0'));


            if (specialOffer != null){
                int specialOfferCount = specialOffer.getCount();
                int specialOfferPrice = specialOffer.getPrice();

                int totalSpecialPrice = (count / specialOfferCount) * specialOfferPrice + (count % specialOfferCount) * price;
                totalPrice += totalSpecialPrice;

                if(specialOffer.getFreeItem() != null){
                    char freeItemSku = specialOffer.getFreeItem();
                    int freeItemQuantity = (count / specialOfferCount);
                    freeItemCount.put(freeItemSku, freeItemCount.getOrDefault(freeItemSku, 0) + freeItemQuantity);
                }

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
        private char freeItem;

        public SpecialOffer(int count, int price, char freeItem){
            this.count = count;
            this.price = price;
            this.freeItem = freeItem;
        }

        public int getCount(){
            return count;
        }

        public int getPrice(){
            return price;
        }

        public char getFreeItem(){
            return freeItem;
        }
    }
}



