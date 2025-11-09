
package com.ecoembes.tw1.dto;

import com.ecoembes.tw1.model.FillLevel;

import java.time.LocalDate;
import java.util.List;

public class DumpsterStatusResponse {
    public int postalCode;
    public LocalDate date;
    public List<Item> items;

    public static class Item {
        public int id;
        public FillLevel fillLevel;
        public int containersEstimate;
    }
}
