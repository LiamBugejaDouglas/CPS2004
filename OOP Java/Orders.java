import java.util.*;
public class Orders{

    //Creating a private ArrayList
    private ArrayList <OrderBook> orders;

    //Constructing a order ArrayList
    public Orders(){

        orders = new ArrayList <OrderBook>();
    }

    //Get size of order ArrayList
    public int getSize(){

        return orders.size();
    }

    //Add order to ArrayList
    public void addOrder(OrderBook order){

        orders.add(order);
    }

    //Remove order from ArrayList
    public void removeOrder(OrderBook order){

        orders.remove(order);
    }

    //Clear ArrayList
    public void clearOrderBook(){

        orders.clear();
    }

    public float returnVolume(OrderBook order){

        return order.getVolumeExecuted();
    }

    //Get transaction by transaction ID
    public OrderBook getTransactionByID(String transID){

        for(OrderBook order : orders){
            if(order.getTransID().equals(transID)){

                return order;
            }
        }

        return null;
    }

    //Print all orders in ArrayList
    public void printOrders(){

        for(OrderBook order : orders){

            System.out.println(order.toString());
        }
    }

    //Print all buy orders in ArrayList
    public void printBuyOrders(){

        for(OrderBook tempOrder : orders){
            if(tempOrder.getFunction().equals("Buy")){

                System.out.println(tempOrder.toString());
            }
        }
    }

    //Print all sell orders in ArrayList
    public void printSellOrders(){

        for(OrderBook tempOrder : orders){
            if(tempOrder.getFunction().equals("Sell")){

                System.out.println(tempOrder.toString());
            }
        }
    }

    public void setTotal(){

        for(OrderBook tempOrder: orders){

            float total = tempOrder.getQuantity() * tempOrder.getSellBuyPrice();
            tempOrder.setTotal(total);
        }
    }

    public void sort(){

        Collections.sort(orders, OrderBook.OrderBookPrice);
    }

    public void setTransactions(){

        for(OrderBook tempOrder1: orders){
            if(tempOrder1.getFunction().equals("Sell")){

                for(OrderBook tempOrder2: orders){
                    if(tempOrder2.getFunction().equals("Buy")){

                        if(tempOrder1.getSymbol().equals(tempOrder2.getSymbol())){

                            if(tempOrder1.getType().equals("Market") && tempOrder2.getType().equals("Market")){

                                if((tempOrder1.getQuantity()-tempOrder2.getQuantity()>=0f || tempOrder2.getQuantity()-tempOrder1.getQuantity()>=0f) && (tempOrder1.getQuantity()!=0f || tempOrder2.getQuantity()!=0f)){

                                    if(tempOrder1.getQuantity() > tempOrder2.getQuantity()){

                                        tempOrder1.setVolumeExecuted(tempOrder2.getTotal());
                                        tempOrder2.setVolumeExecuted(tempOrder2.getTotal());
                                        
                                        float setQuantity = tempOrder1.getQuantity() - tempOrder2.getQuantity();

                                        tempOrder1.setQuantity(setQuantity);
                                        tempOrder2.setQuantity(0f);

                                        tempOrder1.setStatus("Partial");
                                        tempOrder2.setStatus("Complete");
                                    }
                                    else if(tempOrder1.getQuantity()<tempOrder2.getQuantity()){

                                        tempOrder1.setVolumeExecuted(tempOrder1.getTotal());
                                        tempOrder2.setVolumeExecuted(tempOrder1.getTotal());

                                        float setQuantity = tempOrder2.getQuantity() - tempOrder1.getQuantity();

                                        tempOrder1.setQuantity(0f);
                                        tempOrder2.setQuantity(setQuantity);

                                        tempOrder1.setStatus("Complete");
                                        tempOrder2.setStatus("Partial");
                                    }
                                    else{

                                        tempOrder1.setVolumeExecuted(tempOrder1.getTotal());
                                        tempOrder2.setVolumeExecuted(tempOrder2.getTotal());

                                        tempOrder1.setQuantity(0f);
                                        tempOrder2.setQuantity(0f);

                                        tempOrder1.setStatus("Complete");
                                        tempOrder2.setStatus("Complete");
                                    }
                                }
                                
                            }
                            else if(tempOrder1.getType().equals("Limit") && tempOrder2.getType().equals("Limit")){

                                if(tempOrder1.getSellBuyPrice()-tempOrder2.getSellBuyPrice()<=0f){

                                    if((tempOrder1.getQuantity()-tempOrder2.getQuantity()>=0f || tempOrder2.getQuantity()-tempOrder1.getQuantity()>=0f) && (tempOrder1.getQuantity()!=0f || tempOrder2.getQuantity()!=0f)){

                                        if(tempOrder1.getQuantity() > tempOrder2.getQuantity()){

                                            tempOrder1.setVolumeExecuted(tempOrder2.getTotal());
                                            tempOrder2.setVolumeExecuted(tempOrder2.getTotal());
                                            
                                            float setQuantity = tempOrder1.getQuantity() - tempOrder2.getQuantity();
    
                                            tempOrder1.setQuantity(setQuantity);
                                            tempOrder2.setQuantity(0f);
    
                                            tempOrder1.setStatus("Partial");
                                            tempOrder2.setStatus("Complete");
                                        }
                                        else if(tempOrder1.getQuantity()<tempOrder2.getQuantity()){
    
                                            tempOrder1.setVolumeExecuted(tempOrder1.getTotal());
                                            tempOrder2.setVolumeExecuted(tempOrder1.getTotal());
    
                                            float setQuantity = tempOrder2.getQuantity() - tempOrder1.getQuantity();
    
                                            tempOrder1.setQuantity(0f);
                                            tempOrder2.setQuantity(setQuantity);
    
                                            tempOrder1.setStatus("Complete");
                                            tempOrder2.setStatus("Partial");
                                        }
                                        else{
    
                                            tempOrder1.setVolumeExecuted(tempOrder1.getTotal());
                                            tempOrder2.setVolumeExecuted(tempOrder2.getTotal());
    
                                            tempOrder1.setQuantity(0f);
                                            tempOrder2.setQuantity(0f);
    
                                            tempOrder1.setStatus("Complete");
                                            tempOrder2.setStatus("Complete");
                                        }
                                    }
                                }
                            }
                        }
                    } 
                }
            }
        }
    }

    //Cancel incomplete orders
    public void cancelTransactions(){

        for(OrderBook order: orders){

            if(order.getStatus().equals("Active") || order.getStatus().equals("Partial")){

                order.setStatus("Cancelled");
            }
        }
    }
}