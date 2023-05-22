package com.haw.se1lab.Quest.logic.impl.usecase;

import com.haw.se1lab.Quest.dataaccess.api.entity.Quest;
import com.haw.se1lab.Quest.strategy.MergeSortStrategy;
import com.haw.se1lab.Quest.strategy.QuestSortingStrategy;
import com.haw.se1lab.Quest.strategy.QuickSortStrategy;

import java.util.List;

public class QuestSortingUtil {

    public static QuestSortingStrategy getSortingStrategy(List<Quest> list){

        if(list.size() <= 100){
            return new MergeSortStrategy();
        }else{
            return new QuickSortStrategy();
        }

    }
}
