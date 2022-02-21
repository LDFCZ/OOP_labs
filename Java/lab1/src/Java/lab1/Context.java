package Java.lab1;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Map.Entry;

public class Context {
    private Map<String, Integer> context;
    private int wordCount;

    static private final int shift = 1;

    {
        context = new HashMap<String, Integer>();
        wordCount = 0;
    }

    public void addNewWord(String word) {
        if (context.containsKey(word)) context.put(word, context.get(word) + shift);
        else context.put(word, 1);
        wordCount++;
    }

    public List<Map.Entry<String, Integer>> getSortedList() {
        List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(context.entrySet());
        list.sort(Entry.comparingByValue());
        return list;
    }

    public int getValueByWord(String word) {
        return context.get(word);
    }

    public int getWordCount() {
        return wordCount;
    }
}
