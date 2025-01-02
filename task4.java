import java.util.*;

public class StronglyConnectedComponents {
    private int vertices; // عدد العقد
    private List<List<Integer>> adjList; // قائمة الجوار

    public StronglyConnectedComponents(int v) {
        vertices = v;
        adjList = new ArrayList<>(v);
        for (int i = 0; i < v; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v) {
        adjList.get(u).add(v); // إضافة حافة من u إلى v
    }

    public void findSCCs() {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[vertices];

        // خطوة 1: ملء الستاك حسب انتهاء الوقت
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                fillOrder(i, visited, stack);
            }
        }

        //  منعكس الرسم البياني
        List<List<Integer>> transposedGraph = getTranspose();

        //  منستخرج المكونات المتصلة بقوة
        Arrays.fill(visited, false);
        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (!visited[v]) {
                List<Integer> component = new ArrayList<>();
                DFS(transposedGraph, v, visited, component);
                System.out.println("SCC: " + component);
            }
        }
    }

    private void fillOrder(int v, boolean[] visited, Stack<Integer> stack) {
        visited[v] = true;
        for (int neighbor : adjList.get(v)) {
            if (!visited[neighbor]) {
                fillOrder(neighbor, visited, stack);
            }
        }
        stack.push(v); // ادخال الرأس إلى الستاك بعد زيارة جميع جيرانه
    }

    private List<List<Integer>> getTranspose() {
        List<List<Integer>> transposedGraph = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            transposedGraph.add(new ArrayList<>());
        }
        
        for (int v = 0; v < vertices; v++) {
            for (int neighbor : adjList.get(v)) {
                transposedGraph.get(neighbor).add(v); // هةن منعكس الحواف
            }
        }
        return transposedGraph;
    }

    private void DFS(List<List<Integer>> graph, int v, boolean[] visited, List<Integer> component) {
        visited[v] = true;
        component.add(v);
        
        for (int neighbor : graph.get(v)) {
            if (!visited[neighbor]) {
                DFS(graph, neighbor, visited, component);
            }
        }
    }

    public static void main(String[] args) {
        StronglyConnectedComponents scc = new StronglyConnectedComponents(5);
        scc.addEdge(0, 1);
        scc.addEdge(1, 2);
        scc.addEdge(2, 0);
        scc.addEdge(1, 3);
        scc.addEdge(3, 4);

        System.out.println("المكونات المتصلة بقوة هي:");
        scc.findSCCs();
    }
}
