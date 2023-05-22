package com.haw.se1lab.Quest.strategy;

import com.haw.se1lab.Quest.dataaccess.api.entity.Quest;

import java.util.List;

public interface QuestSortingStrategy {

    /**
     * sorts quest by their id in ascending order
     * returns a copy of the list
     * to a list size of 100 mergesort is used
     * for a list containing more than 100 elements quicksort is used
     *
     * @param list the unsorted list
     * @return returns sorted list
     */
    List<Quest> sort(List<Quest> list);
}
