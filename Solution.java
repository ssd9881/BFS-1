// Time Compelxity - O(n+p) n-number of courses,p-prerequietes
// Space Compelxity - O(n+p)
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        HashMap<Integer,List<Integer>> map = new HashMap<>();
        int [] indegrees = new int[numCourses];
        for(int [] prerequisite:prerequisites){
            int in = prerequisite[0];
            int out = prerequisite[1];
            indegrees[in]++;
            if(!map.containsKey(out)){
                map.put(out,new ArrayList<>());
            }
            map.get(out).add(in);
        }

        Queue<Integer> q = new LinkedList<>();
        int count = 0;
        for(int i=0;i<numCourses;i++){
            if(indegrees[i]==0){
                q.add(i);
                count++;
            }
        }

        if(count==0) return false;
        if(count==numCourses) return true;

        while (!q.isEmpty()) {
            int parent = q.poll();
            if (!map.containsKey(parent)) continue;
            List<Integer> children = map.get(parent);
            for (int child : children) {
                indegrees[child]--;
                if (indegrees[child] == 0) {
                    q.add(child);
                    count++;
                    if (count == numCourses) return true;
                }
            }
        }
        return false;
    }
} 
