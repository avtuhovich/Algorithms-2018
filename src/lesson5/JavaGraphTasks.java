package lesson5;

import kotlin.NotImplementedError;
import lesson5.impl.GraphBuilder;

import java.util.*;

@SuppressWarnings("unused")
public class JavaGraphTasks {
    /**
     * Эйлеров цикл.
     * Средняя
     *
     * Дан граф (получатель). Найти по нему любой Эйлеров цикл.
     * Если в графе нет Эйлеровых циклов, вернуть пустой список.
     * Соседние дуги в списке-результате должны быть инцидентны друг другу,
     * а первая дуга в списке инцидентна последней.
     * Длина списка, если он не пуст, должна быть равна количеству дуг в графе.
     * Веса дуг никак не учитываются.
     *
     * Пример:
     *
     *      G -- H
     *      |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     *
     * Вариант ответа: A, E, J, K, D, C, H, G, B, C, I, F, B, A
     *
     * Справка: Эйлеров цикл -- это цикл, проходящий через все рёбра
     * связного графа ровно по одному разу
     Ресурсоемкость - R(n)
     Трудоемкость - O(n^2)
     */
    static Map<Graph.Vertex, Set<Graph.Edge>> con = null;
    public static List<Graph.Edge> findEulerLoop(Graph graph) {
        con = graph.getCon();
        for (Set<Graph.Edge> edge: con.values()) {
            if (edge.size() % 2 != 0) return new LinkedList<>();
        }
        return createEulerCon((Graph.Vertex) con.keySet().toArray()[0], new HashSet<>());
    }

    private static List<Graph.Edge> createEulerCon(Graph.Vertex vertex, HashSet<Graph.Edge> visited){
        List<Graph.Edge> result = new LinkedList<>();
        for (Graph.Edge edge : con.get(vertex)) {
            if (visited.contains(edge)) continue;
            visited.add(edge);
            result.addAll(createEulerCon(edge.getBegin() == vertex ? edge.getEnd() : edge.getBegin(),visited));
            result.add(edge);
        }
        return result;
}

    /**
     * Минимальное остовное дерево.
     * Средняя
     *
     * Дан граф (получатель). Найти по нему минимальное остовное дерево.
     * Если есть несколько минимальных остовных деревьев с одинаковым числом дуг,
     * вернуть любое из них. Веса дуг не учитывать.
     *
     * Пример:
     *
     *      G -- H
     *      |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     *
     * Ответ:
     *
     *      G    H
     *      |    |
     * A -- B -- C -- D
     * |    |    |
     * E    F    I
     * |
     * J ------------ K
     * Ресурсоемкость - R(n)
     * Трудоемкость - O(n)
     */
    public static Graph minimumSpanningTree(Graph graph) {
            GraphBuilder res = new GraphBuilder();
            LinkedList<Graph.Vertex> vertexList = new LinkedList<>();
            vertexList.add((Graph.Vertex) graph.getVertices().toArray()[0]);
            while (vertexList.size() != 0){
                for (Map.Entry<Graph.Vertex, Graph.Edge> it :
                        graph.getConnections(vertexList.pop()).entrySet()){
                    if (res.getVertex(it.getKey()) == null){
                        res.addVertex(it.getKey().getName());
                        vertexList.addLast(it.getKey());
                        res.addConnection(it.getValue().getBegin(), it.getValue().getEnd(), 1);
                    }
                }
            }
            return res.build();
        }


    /**
     * Максимальное независимое множество вершин в графе без циклов.
     * Сложная
     *
     * Дан граф без циклов (получатель), например
     *
     *      G -- H -- J
     *      |
     * A -- B -- D
     * |         |
     * C -- F    I
     * |
     * E
     *
     * Найти в нём самое большое независимое множество вершин и вернуть его.
     * Никакая пара вершин в независимом множестве не должна быть связана ребром.
     *
     * Если самых больших множеств несколько, приоритет имеет то из них,
     * в котором вершины расположены раньше во множестве this.vertices (начиная с первых).
     *
     * В данном случае ответ (A, E, F, D, G, J)
     *
     * Эта задача может быть зачтена за пятый и шестой урок одновременно
     */
    public static Set<Graph.Vertex> largestIndependentVertexSet(Graph graph) {
        throw new NotImplementedError();
    }

    /**
     * Наидлиннейший простой путь.
     * Сложная
     *
     * Дан граф (получатель). Найти в нём простой путь, включающий максимальное количество рёбер.
     * Простым считается путь, вершины в котором не повторяются.
     * Если таких путей несколько, вернуть любой из них.
     *
     * Пример:
     *
     *      G -- H
     *      |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     *
     * Ответ: A, E, J, K, D, C, H, G, B, F, I
     */
    public static Path longestSimplePath(Graph graph) {
        throw new NotImplementedError();
    }
}
