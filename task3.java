import java.util.*;

public class Graph {
    private int V; // هاد عدد الرؤوس
    private LinkedList<Integer>[] adj; // هي قائمة الجوار 

    // منبني الرسم البياني
    public Graph(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    // هون ضفنا حافة
    public void addEdge(int v, int w) {
        adj[v].add(w);
    }

    // DFS نتحقق من ترابط
    private void DFS(int v, boolean[] visited) {
        visited[v] = true;
        for (int neighbor : adj[v]) {
            if (!visited[neighbor]) {
                DFS(neighbor, visited);
            }
        }
    }

    // نتحقق اذا البيان مضاعف الترابط عقديا
    public boolean isStronglyConnected() {
        boolean[] visited = new boolean[V];

        // من اول عقدة DFS
        DFS(0, visited);

        // منتحقق اذا بمر عكل العقد
        for (boolean v : visited) {
            if (!v) return false;
        }

        // هون عكس البيان
        Graph transposedGraph = getTransposedGraph();

        // إعادة تعيين الزيارات
        Arrays.fill(visited, false);

        // مبدء DFS من الرأس الأول في الرسم البياني المعكوس
        transposedGraph.DFS(0, visited);

        // منتحقق مرة تانية من المرور لكل الرؤوس
        for (boolean v : visited) {
            if (!v) return false;
        }

        return true;
    }

    // الحصول على الرسم البياني المعكوس
    private Graph getTransposedGraph() {
        Graph g = new Graph(V);
        for (int v = 0; v < V; v++) {
            for (int neighbor : adj[v]) {
                g.addEdge(neighbor, v);
            }
        }
        return g;
    }

    // منتحقق إذا كان الرسم البياني مضاعف الترابط وصليا
    public boolean isWeaklyConnected() {
        boolean[] visited = new boolean[V];

        // منبدأ DFS من الرأس الأول
        DFS(0, visited);

        // التحقق مما إذا كانت جميع الرؤوس قد تم الوصول إليها
        for (boolean v : visited) {
            if (!v) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        Graph g = new Graph(5);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(1, 3);
        g.addEdge(3, 4);

        if (g.isStronglyConnected()) {
            System.out.println("الرسم البياني مضاعف الترابط عقديا.");
        } else if (g.isWeaklyConnected()) {
            System.out.println("الرسم البياني مضاعف الترابط وصليا.");
        } else {
            System.out.println("الرسم البياني غير مضاعف الترابط.");
        }
    }
}
