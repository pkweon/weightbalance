package org.netusers.temp.weightbalance;

import java.util.*;

public class Main {

    class N2 {
        public int weight;
        public int count;
        public N2(int weight, int count) { this.weight = weight; this.count = count; }
        public String toString() { return "[" + weight + "(" + count + ")] "; }
    }

    public int max(List<N2> nodes) {
        int max = 0;
        for (N2 n : nodes) {
            if (n.count > max) max = n.count;
        }
        return max;
    }

    public void print(List<N2> nodes) {
        for (N2 n : nodes) {
            System.out.print(n.toString() + " ");
        }
        System.out.println();
    }

    public int solution(int[] weights) {
        System.out.println("trial - #0");

        if (weights.length == 1) return 1;

        Arrays.sort(weights);

        List<N2> nodes = new ArrayList<>();

        for (int i = 0; i < weights.length; i++) {
            if (i == weights.length - 1) {
                nodes.add(new N2(weights[i], 1));
            }
            else if (weights[i] == weights[i+1]) {
                nodes.add(new N2(weights[i], 2));
                i++;
            } else {
                nodes.add(new N2(weights[i], 1));
            }
        }
        print(nodes);

        boolean modified = true;
        for (int count = 1; modified; count++) {
            modified = false;
            System.out.println("trial - #" + count);
            for (int i = 0; i < nodes.size()-1; i++) {
                if (nodes.get(i).weight == nodes.get(i+1).weight) {
                    N2 n2 = new N2(nodes.get(i).weight * 2, nodes.get(i).count + nodes.get(i+1).count);
                    for (int ni = i+2; ni < nodes.size(); ni++) {
                        if (nodes.get(ni).weight > n2.weight) {
                            modified = true; nodes.add(ni, n2); break;
                        } else if (nodes.get(ni).weight == n2.weight) {
                            if (nodes.get(ni).count > n2.count) {
                                modified = true;
                                nodes.add(ni, n2); break;
                            }
                        }
                    }
                    if (!modified) nodes.add(n2);
                    nodes.remove(i+1);
                    nodes.remove(i);
                    modified = true;
                    break;
                }
            }
            print(nodes);
            if (modified) continue;
            break;
        }

        return max(nodes);
    }

    public static void main(String[] args) {
        int[] weights = new int[] {2, 3, 3, 3, 3, 3, 3, 12, 4, 12, 2, 16, 16, 16, 16, 16, 16, 16, 16, 16, 5, 19, 6};
        int max = new Main().solution(weights);
        System.out.println("maximum = " + max);
    }
}
