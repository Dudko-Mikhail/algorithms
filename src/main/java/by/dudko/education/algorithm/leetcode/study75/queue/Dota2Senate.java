package by.dudko.education.algorithm.leetcode.study75.queue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

/**
 * https://leetcode.com/problems/dota2-senate/description/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * 649. Dota2 Senate
 * <p>
 * In the world of Dota2, there are two parties: the Radiant and the Dire.
 * The Dota2 senate consists of senators coming from two parties. Now the Senate wants to decide on a change in the Dota2 game. The voting for this change is a round-based procedure. In each round, each senator can exercise one of the two rights:
 * Ban one senator's right: A senator can make another senator lose all his rights in this and all the following rounds.
 * Announce the victory: If this senator found the senators who still have rights to vote are all from the same party, he can announce the victory and decide on the change in the game.
 * <p>
 * Given a string senate representing each senator's party belonging. The character 'R' and 'D' represent the Radiant party and the Dire party. Then if there are n senators, the size of the given string will be n.
 * The round-based procedure starts from the first senator to the last senator in the given order. This procedure will last until the end of voting. All the senators who have lost their rights will be skipped during the procedure.
 * Suppose every senator is smart enough and will play the best strategy for his own party. Predict which party will finally announce the victory and change the Dota2 game. The output should be "Radiant" or "Dire".
 * <p>
 * Example 1:
 * Input: senate = "RD"
 * Output: "Radiant"
 * Explanation:
 * The first senator comes from Radiant and he can just ban the next senator's right in round 1.
 * And the second senator can't exercise any rights anymore since his right has been banned.
 * And in round 2, the first senator can just announce the victory since he is the only guy in the senate who can vote.
 * <p>
 * Example 2:
 * Input: senate = "RDD"
 * Output: "Dire"
 * Explanation:
 * The first senator comes from Radiant and he can just ban the next senator's right in round 1.
 * And the second senator can't exercise any rights anymore since his right has been banned.
 * And the third senator comes from Dire and he can ban the first senator's right in round 1.
 * And in round 2, the third senator can just announce the victory since he is the only guy in the senate who can vote.
 * <p>
 * Constraints:
 * n == senate.length
 * 1 <= n <= 104
 * senate[i] is either 'R' or 'D'.
 */


public class Dota2Senate {
    private static final String RADIANT_VICTORY = "Radiant";
    private static final String DIRE_VICTORY = "Dire";
    private static final int RADIANT = 1;
    private static final int DIRE = 2;
    private static final int NONE = 0;

    public String predictPartyVictory(String senateStr) {
        int[] senate = new int[senateStr.length()];

        int k = 0;
        for (char member : senateStr.toCharArray()) {
            senate[k++] = member == 'R' ? RADIANT : DIRE;
        }

        while (true) {
            for (int i = 0; i < senate.length; i++) {
                int member = senate[i];
                if (member == NONE) {
                    continue;
                }

                boolean isRadiant = member == RADIANT;
                if (doMove(senate, i + 1, isRadiant ? DIRE : RADIANT)) {
                    return isRadiant ? RADIANT_VICTORY : DIRE_VICTORY;
                }
            }

            senate = IntStream.of(senate)
                    .filter(i -> i != NONE)
                    .toArray();
        }
    }

    boolean doMove(int[] members, int start, int target) {
        for (int i = start; i < members.length; i++) {
            if (members[i] == target) {
                members[i] = NONE;
                return false;
            }
        }
        for (int i = 0; i < start; i++) {
            if (members[i] == target) {
                members[i] = NONE;
                return false;
            }
        }
        return true;
    }
}

class SecondSolution {
    private static final String RADIANT_VICTORY = "Radiant";
    private static final String DIRE_VICTORY = "Dire";
    private static final int RADIANT = 0;
    private static final int DIRE = 1;


    public String predictPartyVictory(String senateStr) {
        List<Integer> senate = new ArrayList<>(senateStr.length());
        for (char member : senateStr.toCharArray()) {
            senate.add(member == 'R' ? RADIANT : DIRE);
        }

        while (true) {
            int radiantCount = 0;
            int direCount = 0;
            List<Integer> nextRound = new ArrayList<>();
            for (Integer member : senate) {
                if (member == RADIANT) {
                    if (direCount == 0) {
                        radiantCount++;
                        nextRound.add(member);
                    } else {
                        direCount--;
                    }
                } else {
                    if (radiantCount == 0) {
                        direCount++;
                        nextRound.add(member);
                    } else {
                        radiantCount--;
                    }
                }
            }

            if (radiantCount == 0) {
                if (finishMove(nextRound, RADIANT, direCount)) {
                    return DIRE_VICTORY;
                }
            } else if (finishMove(nextRound, DIRE, radiantCount)) {
                return RADIANT_VICTORY;
            }
            senate = nextRound;
        }

    }


    private boolean finishMove(List<Integer> members, int target, int toRemove) {
        if (toRemove == 0) {
            return false;
        }
        int count = 0;
        Iterator<Integer> iterator = members.iterator();
        while (iterator.hasNext() && count < toRemove) {
            Integer current = iterator.next();
            if (current == target) {
                count++;
                iterator.remove();
            }
        }
        return count <= toRemove;
    }
}
