package ru.sfedu.tavern.utils;

import com.opencsv.bean.CsvToBeanFilter;
import com.opencsv.bean.MappingStrategy;

/**
 *
 * @author entropy
 */
public class CsvFilter implements CsvToBeanFilter {

    private final MappingStrategy strategy;
    private String col;
    private String val;
    
    /**
     *
     * @param strategy
     */
    public CsvFilter(MappingStrategy strategy) {
        this.strategy = strategy;
    }
    
    /**
     *
     * @param strategy
     * @param col
     * @param val
     */
    public CsvFilter(MappingStrategy strategy, String col, String val) {
        this.strategy = strategy;
        this.col = col;
        this.val = val;
    }

    /**
     *
     * @param lines
     * @return
     */
    @Override
    public boolean allowLine(String[] lines) {
        int idx = strategy.getColumnIndex("id");
        String idxVal = lines[idx];
        boolean result = !"id".equals(idxVal);
        if(val != null && col != null) {
            int idxCol = strategy.getColumnIndex(col);
            String idxColVal = lines[idxCol];
            result &= val.equals(idxColVal);
        }
        return result;
    }

}
