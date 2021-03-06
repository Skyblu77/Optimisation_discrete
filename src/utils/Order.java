package utils;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Order {

    private final int SIZE;
    private final Integer[] orders;

    public Order(String solutions){
        String[] orderL = solutions.split(",");
        SIZE = orderL.length;
        orders = new Integer[SIZE];
        for (int i = 0; i < orderL.length; i++) {
            orders[i] = Integer.parseInt(orderL[i])-1;
        }
    }

    public Order(int SIZE){
        this.SIZE = SIZE;
        orders = new Integer[SIZE];
        for (int i = 0; i < SIZE; i++) {
            orders[i] = i;
        }
    }

    public Order(Integer[] orders){
        SIZE = orders.length;
        this.orders = orders.clone();
    }

    public int size(){
        return SIZE;
    }

    public Integer get(Integer index){
        assert (index > 0);
        assert (index < this.SIZE);
        return orders[index];
    }

    public Order set(Integer index, Integer value){
        assert (index > 0);
        assert (index < this.SIZE);
        assert (value > 0);
        assert (value < this.SIZE);
        orders[index] = value;
        return this;
    }

    public Order shuffle(){
        Random rnd = ThreadLocalRandom.current();
        for (int i = orders.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = orders[index];
            orders[index] = orders[i];
            orders[i] = a;
        }
        return this;
    }

    @Override
    public String toString() {
        return Arrays.toString(orders);
    }

    @Override
    public Order clone() {
        return new Order(this.orders);
    }
}
