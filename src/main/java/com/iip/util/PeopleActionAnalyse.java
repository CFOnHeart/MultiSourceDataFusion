package com.iip.util;

import com.iip.data.space_time.PeopleOrientation;

/**
 * @Author Junnor.G
 * @Date 2019/1/12 下午4:05
 * @Description 对人的行为的分析
 */

public class PeopleActionAnalyse {
    // 根据一个人的旅行路线，判断他的经济状况级别以此作为贫困户的依据
    public static enum FinalncialSituationLevel{
        RICH,           // 富余
        FAIRY_WELL_OF,  // 小康
        SUBSISTENCE,    // 温饱
        POVERTY         // 贫困
    }
    // 日均20, 15, 10, 5旅行公里数
    final static double AVE_DISTANCE_THRESH[] = {
        20,
        15,
        10,
        5
    };
    public static FinalncialSituationLevel financialSituationLevel(PeopleOrientation peopleOrientation){
        peopleOrientation.sort();
        // 计算日均旅行距离
        double averageDayDis =
                peopleOrientation.allDistance() / peopleOrientation.throughDays();
        System.out.println("debug: " + peopleOrientation.allDistance() + " " + peopleOrientation.throughDays());
        if (averageDayDis > AVE_DISTANCE_THRESH[0])
            return FinalncialSituationLevel.RICH;
        else if(averageDayDis > AVE_DISTANCE_THRESH[1])
            return FinalncialSituationLevel.FAIRY_WELL_OF;
        else if(averageDayDis > AVE_DISTANCE_THRESH[2])
            return FinalncialSituationLevel.SUBSISTENCE;
        else
            return FinalncialSituationLevel.POVERTY;
    }
}
