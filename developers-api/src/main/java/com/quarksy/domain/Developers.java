package com.quarksy.domain;

import java.util.List;

public class Developers {

    boolean hasNext;
    List<Developer> items;

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public List<Developer> getItems() {
        return items;
    }

    public void setItems(List<Developer> items) {
        this.items = items;
    }
}
