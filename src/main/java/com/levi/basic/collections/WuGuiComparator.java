package com.levi.basic.collections;

import java.util.Comparator;

public class WuGuiComparator implements Comparator<WuGui> {
    @Override
    public int compare(WuGui o1, WuGui o2) {
        return o1.name.compareTo(o2.name);
    }
}
