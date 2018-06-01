package com.stardust.guangzhou.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stardust on 2017/4/16.
 */

public class OrderList {

    interface Filter<T> {
        boolean accept(T t);
    }

    public static List<Order> list = new ArrayList<>();
    public static List<Order> unpay = new ArrayList<>();
    public static List<Order> payed = new ArrayList<>();
    public static List<Order> refund = new ArrayList<>();

    static {
        list.add(new Order(Post.TYPE_CONSUMER, "带你领略羊城风采", "孔令爽", "广州大学城", "123", Order.STATUS_COMPLETE,60,"2017.12.3","2017.12.4",""));
        list.add(new Order(Post.TYPE_PRODUCER, "中大南校区一日游", "孔令爽", "广州大学城", "123", Order.STATUS_UNPAY,60,"2017.12.3","2017.12.4",""));
        list.add(new Order(Post.TYPE_CONSUMER, "早茶", "孔令爽","广州大学城", "123", Order.STATUS_PAYED,60,"2017.12.3","2017.12.4",""));
        list.add(new Order(Post.TYPE_CONSUMER, "冬游横渡珠江", "孔令爽", "广州大学城", "123", Order.STATUS_REFUND,60,"2017.12.3","2017.12.4",""));
        list.add(new Order(Post.TYPE_CONSUMER, "啦啦啦啦啦", "孔令爽","广州大学城", "123", Order.STATUS_REFUND,60,"2017.12.3","2017.12.4",""));
        list.add(new Order(Post.TYPE_CONSUMER, "呵呵呵呵呵", "孔令爽", "广州大学城", "123", Order.STATUS_PAYED,60,"2017.12.3","2017.12.4",""));
        list.add(new Order(Post.TYPE_PRODUCER, "喵喵喵喵喵","孔令爽", "广州大学城", "123", Order.STATUS_PAYED,60,"2017.12.3","2017.12.4",""));
        list.add(new Order(Post.TYPE_CONSUMER, "咩咩咩咩咩","孔令爽", "广州大学城", "123", Order.STATUS_PAYED,60,"2017.12.3","2017.12.4",""));
        list.add(new Order(Post.TYPE_PRODUCER, "汪汪汪汪汪","孔令爽",  "广州大学城", "123", Order.STATUS_UNPAY,60,"2017.12.3","2017.12.4",""));
        list.add(new Order(Post.TYPE_CONSUMER, "嘎嘎嘎嘎嘎", "孔令爽", "广州大学城", "123", Order.STATUS_UNPAY,60,"2017.12.3","2017.12.4",""));
        list.add(new Order(Post.TYPE_PRODUCER, "咯咯咯咯咯", "孔令爽", "广州大学城", "123", Order.STATUS_UNPAY,60,"2017.12.3","2017.12.4",""));
        list.add(new Order(Post.TYPE_CONSUMER, "哦哦哦哦哦","孔令爽",  "广州大学城", "123", Order.STATUS_COMPLETE,60,"2017.12.3","2017.12.4",""));
        list.add(new Order(Post.TYPE_CONSUMER, "噢噢噢哦哦噢噢噢","孔令爽",  "广州大学城", "123", Order.STATUS_COMPLETE,60,"2017.12.3","2017.12.4",""));
        list.add(new Order(Post.TYPE_CONSUMER, "嗯嗯嗯嗯呃", "孔令爽", "广州大学城", "123", Order.STATUS_UNPAY,60,"2017.12.3","2017.12.4",""));

        filter(list, unpay, new Filter<Order>() {
            @Override
            public boolean accept(Order order) {
                return order.status.equals(Order.STATUS_UNPAY);
            }
        });
        filter(list, payed, new Filter<Order>() {
            @Override
            public boolean accept(Order order) {
                return order.status.equals(Order.STATUS_PAYED);
            }
        });
        filter(list, refund, new Filter<Order>() {
            @Override
            public boolean accept(Order order) {
                return order.status.equals(Order.STATUS_REFUND);
            }
        });
    }

    public static List<Order> get(int type) {
        if (type == 0) {
            return list;
        }
        if (type == 1) {
            return payed;
        }
        if (type == 2) {
            return unpay;
        }
        return refund;
    }
    public static void add2list(Order order){
        list.add(0,order);
    }
    private static void filter(List<Order> list, List<Order> to, Filter<Order> filter) {
        for (Order order : list) {
            if (filter.accept(order))
                to.add(order);
        }
    }
}
