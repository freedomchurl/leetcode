package FourSum_18;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {

    public static List<List<Integer>> result = new ArrayList<>();

    public static void main(String[] args) {
//        List<List<Integer>> test = fourSum(new int[]{1, 0, -1, 0, -2, 2} , 0);

//        System.out.println(test);

        List<List<Integer>> test = fourSum(new int[]{
                -489,-475,-469,-468,-467,-462,-456,-443,-439,-425,-425,-410,-401,-342,-341,-331,-323,-307,-299,-262,-254,-245,-244,-238,-229,-227,-225,-224,-221,-197,-173,-171,-160,-142,-142,-136,-134,-125,-114,-100,-86,-81,-66,-47,-37,-34,4,7,11,34,60,76,99,104,113,117,124,139,141,143,144,146,157,157,178,183,185,189,192,194,221,223,226,232,247,249,274,281,284,293,298,319,327,338,340,368,375,377,379,388,390,392,446,469,480,490
        } , 2738);
        System.out.println(test);
    }

    public static List<Integer> removeDuplicatedOver4(int[] nums) {
        int dupCount = 1;
        int targetNum = nums[0];

        List<Integer> result = new ArrayList<>(nums.length);
        result.add(nums[0]);

        for (int i= 1 ; i<nums.length; i++) {
            if (targetNum == nums[i]) {
                dupCount++;
            } else {
                dupCount = 1;
                targetNum = nums[i];
            }

            if (dupCount <= 4) {
                result.add(nums[i]);
            }
        }

        return result;
    }

    public static boolean checkIsSame(List<Integer> existResult, List<Integer> newResult) {
        for (int i = 0 ; i < 4 ;i++) {
            if (!existResult.get(i).equals(newResult.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static void checkExistenceAndAdd(List<Integer> newCal) {
        for (int i=0; i< result.size(); i++) {
            System.out.println(result.get(i));
            System.out.println("뭘까요");
            if (checkIsSame(result.get(i), newCal)) {
                System.out.println("같아유");
                return;
            }
        }

        System.out.println("추가");
        result.add(newCal);
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        /**
         * 선 정렬
         */
        Arrays.sort(nums);
        // -2 -1 0 0 1 2

        /**
         * 포함하고, 포함안하고하는 과정을 통해서 -> DFS 방식으로 풀어나간다.
         * 4개여야하므로 4개까지만, 반대로 남은걸 다 넣어도 4개가 안되면 검사를 할 필요가 없음. -> 즉, lengths가 10이면 (last index 9, 6789 idx 6까지만 검사 시작하면 됨)
         * 그리고 정렬했으니까, 이번걸 넣었는데 넘어버리면 더 나아갈 필요가 없음.
         */
        checkStart(nums, target);

        return result;
    }

    public static void checkStart(int[] nums, int target) {
        checkNext(nums, target, target, new ArrayList<>(), 0);
    }



    public static void checkNext(int[] nums, int target, int left, List<Integer> checkedList, int checkingIdx) {
//        System.out.println(left);
//        System.out.println(checkedList);
//        System.out.println(checkingIdx);
        if (left == 0 && checkedList.size() == 4) {
//            System.out.println("Add");
            checkExistenceAndAdd(checkedList);
            return;
        }

        if (checkedList.size() == 4) { // 4지만, left가 0은 아님. 더 이상 진행 불가
//            System.out.println("사이즈만 4고 left 0 실패");
            return;
        }

        // checkedList => 지금까지 저장한거.
        // length는 전체 element의 개수, checkingIdx는 지금 체크할 idx
        // 10 - length, checkingIdx가 8이면 -> 남은건 8, 9 =>> length - checkingIdx 가 남은 elements 개수
        if ((checkedList.size() + (nums.length - checkingIdx)) < 4) {
//            System.out.println("남은 애들을 다 넣어도 4가 안됨");
            return;
        }

//        // 이번걸 넣은거다. 근데 over 했으면 그대로 종료 -> 정렬되어있으므로 오름차순이라 앞으로는 다 넘을거임.
//        if (left - nums[checkingIdx] < 0) {
//            System.out.println("이번걸 넣었더니 이미 target을 오바했다. 스탑");
//            return;
//        }

        // 넣는 케이스
        List<Integer> addThisList = new ArrayList<>(checkedList);
        addThisList.add(nums[checkingIdx]);
        checkNext(nums, target, left - nums[checkingIdx], addThisList, checkingIdx+1);
        // 안넣는 케이스
        checkNext(nums, target, left, new ArrayList<>(checkedList), checkingIdx+1);
    }
}
