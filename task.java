import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//خوارزمية البحث بعمق DFS

class Graph {
    private int V; // عدد العقد
    private List<List<Integer>> adj; // هي مصفوفة الجوار

    // Constructor
    public Graph(int vertices) {
        this.V = vertices;
        adj = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            adj.add(new ArrayList<>());
        }
    }}

    // هون منضيف الحافة
    public void addEdge(int u, int v) {
        adj.get(u).add(v); 
    }

    // وهي خوارزمية ال DFs

    private void dfs(int v, boolean[] visited) {
        visited[v] = true;
        for (int neighbor : adj.get(v)) {
            if (!visited[neighbor]) {
                dfs(neighbor, visited);
            }
        }
    }

    // DFSالتحقق من خوارزمية ال
    public boolean isConnected() {
        boolean[] visited = new boolean[V];
        dfs(0, visited);

        return Arrays.stream(visited).allMatch(visitedVertex -> visitedVertex);
    }

    public static void main(String[] args) {
        
        int[][] edges = {{0, 1}, {1, 2}, {2, 0}, {0, 3}, {3, 4}};
        int maxVertex = Arrays.stream(edges)
                              .flatMapToInt(Arrays::stream)
                              .max()
                              .orElse(-1) + 1;

        Graph g = new Graph(maxVertex); // هون انشأنا البيان الموجه

        // هون ضفنا الوصلات
        for (int[] edge : edges) {
            g.addEdge(edge[0], edge[1]);
        }

        if (g.isConnected()) {
            System.out.println("البيان متصل بقوة");
        } else {
            System.out.println("البيان غير متصل بقوة");
        }
}
