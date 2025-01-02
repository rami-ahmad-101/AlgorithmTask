import java.util.*;

class GraphG {
    private int V;
    private int[][] capacity;
    private int[][] flow; 
    private List<Integer>[] adj;

    public GraphG(int v) {
        V = v;
        capacity = new int[v][v];
        flow = new int[v][v];
        adj = new ArrayList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public void addEdge(int from, int to, int cap) {
        capacity[from][to] += cap; 
        adj[from].add(to);
        adj[to].add(from);
    }

    public int maxFlow(int source, int sink) {
        int totalFlow = 0;

        while (true) {
            int[] parent = new int[V];
            Arrays.fill(parent, -1);
            parent[source] = source;

            Queue<Integer> queue = new LinkedList<>();
            queue.add(source);

            while (!queue.isEmpty()) {
                int u = queue.poll();

                for (int v : adj[u]) {
                    if (parent[v] == -1 && capacity[u][v] > flow[u][v]) {
                        parent[v] = u;
                        queue.add(v);
                        if (v == sink) break;
                    }
                }

                if (parent[sink] != -1) break;
            }

            if (parent[sink] == -1) break; 

            int increment = Integer.MAX_VALUE;
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                increment = Math.min(increment, capacity[u][v] - flow[u][v]);
            }

            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                flow[u][v] += increment;
                flow[v][u] -= increment; 
            }

            totalFlow += increment;
        }

        return totalFlow;
    }
}


public class MaxFlowExample {
    public static void main(String[] args) {
        GraphG graph = new GraphG(6);

        graph.addEdge(0, 1, 16);
        graph.addEdge(0, 2, 13);
        graph.addEdge(1, 2, 10);
        graph.addEdge(1, 3, 12);
        graph.addEdge(2, 1, 4);
        graph.addEdge(2, 4, 14);
        graph.addEdge(3, 2, 9);
        graph.addEdge(3, 5, 20);
        graph.addEdge(4, 3, 7);
        graph.addEdge(4, 5, 4);

        int maxFlow = graph.maxFlow(0, 5);
        System.out.println("Maximum Flow: " + maxFlow);
    }
}
