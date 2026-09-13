import java.util.Arrays;
import java.util.List;

import merge.Employee;
import merge.MergeSort;
import merge.SortOrder;
import quick.QuickSort;

public class Main {

    // ソート対象のリスト
    private static final List<Employee> data1 = List.of(
            new Employee("0001", "山田太郎", 20),
            new Employee("0002", "鈴木花子", 25),
            new Employee("0003", "佐藤次郎", 15),
            new Employee("0004", "田中四郎", 35),
            new Employee("0005", "高橋五郎", 10),
            new Employee("0006", "伊藤六郎", 30),
            new Employee("0007", "中村七郎", 25),
            new Employee("0008", "渡辺八郎", 20),
            new Employee("0009", "吉村拓郎", 20),
            new Employee("0010", "斎藤静香", 10));

    private static final int[] data2 = {
            25, 40, 15, 30, 10, 35, 20, 30, 45, 30
    };

    public static void main(String[] args) {

        // マージソート
        System.out.println("---------- マージソート ソート前 ----------");
        System.out.println(data1);

        List<Employee> ascSortedList = MergeSort.sort(data1, SortOrder.ASC);
        List<Employee> descSortedList = MergeSort.sort(data1, SortOrder.DESC);

        System.out.println("---------- マージソート ソート後（昇順） ----------");
        System.out.println(ascSortedList);
        System.out.println("---------- マージソート ソート後（降順） ----------");
        System.out.println(descSortedList);

        // クイックソート
        System.out.println("---------- クイックソート ソート前 ----------");
        System.out.println(Arrays.toString(data2));

        QuickSort.sort(data2);

        System.out.println("---------- クイックソート ソート後 ----------");
        System.out.println(Arrays.toString(data2));
    }
}
