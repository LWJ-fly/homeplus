package utils;

        import java.util.ArrayList;
        import java.util.List;

/**
 * @program: homeplus
 * @description: 基于物品的推荐算法
 * @author: ZEK
 * @create: 2019-05-15 15:41
 **/
public final class RecommendUtil {

    /**
     * 得到用户与物品之间的喜爱度矩阵
     * @param score 用户给类别的打分矩阵
     * @param thingsSimilarity 类别与类别之间的相似度矩阵
     * @return
     */
    public static List<List<Integer>> getRecommendResult (List<List<Integer>> score, List<List<Integer>> thingsSimilarity) {
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < score.size(); i ++) {
            List<Integer> singleRow = new ArrayList<>();
            for (int n = 0; n < thingsSimilarity.get(0).size(); n ++) {
                int middleElementResult = 0;
                for (int j = 0; j < score.get(i).size(); j ++) {
                    int minElementResult = score.get(i).get(j) * thingsSimilarity.get(j).get(n);
                    middleElementResult += minElementResult;
                }
                singleRow.add(middleElementResult);
            }
            result.add(singleRow);
        }
        return result;
    }

    /**
     * 测试主函数
     * @param args
     */
    public static void main (String [] args) {
        List<List<Integer>> testOne = new ArrayList<>();
        for (int i = 0; i < 3; i ++) {
            List<Integer> singleTest = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                singleTest.add(i + j);
            }
            testOne.add(singleTest);
        }
        System.out.println(testOne);

        List<List<Integer>> testTwo = new ArrayList<>();
        for (int i = 0; i < 4; i ++) {
            List<Integer> singleTest = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                singleTest.add(i + j);
            }
            testTwo.add(singleTest);
        }
        System.out.println(testTwo);

        System.out.println(getRecommendResult(testOne, testTwo));
    }
}
