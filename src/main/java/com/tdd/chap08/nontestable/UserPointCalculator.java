package com.tdd.chap08.nontestable;

import com.tdd.chap07.user.User;
import com.tdd.chap08.subs.*;

import java.time.LocalDate;

import static com.tdd.chap08.subs.Grade.*;

public class UserPointCalculator {
    private SubscriptionDao subscriptionDao;
    private ProductDao productDao;

    public UserPointCalculator(SubscriptionDao subscripttionDao,
                               ProductDao productDao) {
        this.subscriptionDao = subscripttionDao;
        this.productDao = productDao;
    }

    public int calculatePoint(User u) {
        Subscription s = subscriptionDao.selectByUser(u.getId());
        if (s == null) throw new NoSubscriptionException();
        Product p = productDao.selectById(s.getProductId());
        LocalDate now = LocalDate.now();
        int point = 0;
        if (s.isFinished(now)) {
            point += p.getDefaultPoint();
        } else {
            point += p.getDefaultPoint() + 10;
        }
        if (s.getGrade() == GOLD) {
            point += 100;
        }
        return point;
    }
}
