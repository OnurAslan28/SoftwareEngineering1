package com.haw.se1lab.Quest.strategy;

import com.haw.se1lab.Quest.dataaccess.api.entity.Quest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MergeSortStrategy implements QuestSortingStrategy{

    @Override
    public List<Quest> sort(List<Quest> list) {

        List<Quest> outputList = new ArrayList<>(list);
        Collections.sort(outputList, Comparator.comparing(Quest::getId));

        return outputList;
    }

}
