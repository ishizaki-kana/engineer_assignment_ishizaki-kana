import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import merge.Employee;
import merge.MergeSort;
import merge.SortOrder;
import quick.QuickSort;

/**
 * テスト
 */
public class Test {

    public static void main(String[] args) {
        System.out.println("---------- テスト開始 ----------");

        testMergeSort();
        testQuickSort();

        System.out.println("---------- テスト終了 ----------");
    }

    //
    // マージソート
    //

    private static void testMergeSort() {

        //
        // 正常系
        //

        // 昇順かつ同じ年齢の社員の順番を保つこと
        testMergeSortAscAndStability();
        // 降順かつ同じ年齢の社員の順番を保つこと
        testMergeSortDescAndStability();
        // 入力リストが更新されないこと
        testMergeSortDoesNotModifyInput();
        // 入力順が null のとき降順となること
        testMergeSortOrderNullDose();
        // 空リストのとき
        testMergeSortEmpty();
        // 要素数が1のとき
        testMergeSortOnce();

        //
        // 異常系
        //

        // 入力データが null のとき例外エラーとなること
        testMergeSortNull();
    }

    private static void testMergeSortAscAndStability() {
        List<Employee> input = List.of(
                new Employee("001", "田中", 20),
                new Employee("002", "鈴木", 10),
                new Employee("003", "佐藤", 25),
                new Employee("004", "高橋", 15),
                new Employee("005", "安井", 25));

        List<Employee> result = MergeSort.sort(input, SortOrder.ASC);

        List<String> expected = List.of("002", "004", "001", "003", "005");

        assertMergeSort(result, expected);
    }

    private static void testMergeSortDescAndStability() {
        List<Employee> input = List.of(
                new Employee("001", "田中", 20),
                new Employee("002", "鈴木", 10),
                new Employee("003", "佐藤", 25),
                new Employee("004", "高橋", 15),
                new Employee("005", "安井", 25));

        List<Employee> result = MergeSort.sort(input, SortOrder.DESC);

        List<String> expected = List.of("003", "005", "001", "004", "002");

        assertMergeSort(result, expected);
    }

    private static void testMergeSortEmpty() {
        List<Employee> input = new ArrayList<>();

        List<Employee> result = MergeSort.sort(input, SortOrder.ASC);

        List<String> expected = new ArrayList<>();

        assertMergeSort(result, expected);
    }

    private static void testMergeSortDoesNotModifyInput() {
        List<Employee> input = List.of(
                new Employee("001", "田中", 20),
                new Employee("002", "鈴木", 10));

        MergeSort.sort(input, SortOrder.ASC);

        List<String> expected = List.of("001", "002");

        assertMergeSort(input, expected);
    }

    private static void testMergeSortOnce() {
        List<Employee> input = List.of(
                new Employee("001", "田中", 20));

        List<Employee> result = MergeSort.sort(input, SortOrder.ASC);

        List<String> expected = List.of("001");

        assertMergeSort(result, expected);
    }

    private static void testMergeSortOrderNullDose() {
        List<Employee> input = List.of(
                new Employee("001", "田中", 20),
                new Employee("002", "鈴木", 10),
                new Employee("003", "佐藤", 25),
                new Employee("004", "高橋", 15));

        List<Employee> result = MergeSort.sort(input, null);

        List<String> expected = List.of("003", "001", "004", "002");

        assertMergeSort(result, expected);
    }

    private static void testMergeSortNull() {
        assertThrows(() -> MergeSort.sort(null, SortOrder.ASC));
    }

    private static void assertMergeSort(List<Employee> result, List<String> expected) {
        List<String> resultIds = result.stream().map(r -> r.getId()).toList();

        if (!resultIds.equals(expected)) {
            throw new AssertionError("マージソートの結果が正しくありません。"
                    + " 期待する結果=" + expected + ", 実際の結果=" + resultIds);
        }
    }

    //
    // クイックソート
    //

    private static void testQuickSort() {

        //
        // 正常系
        //

        // 空配列のとき
        assertQuickSort(new int[] {}, new int[] {});
        // 要素数が1のとき
        assertQuickSort(new int[] { 5 }, new int[] { 5 });
        // 重複データがあるとき
        assertQuickSort(new int[] { 3, 1, 3, 2, 1 }, new int[] { 1, 1, 2, 3, 3 });
        // 負の数があるとき
        assertQuickSort(new int[] { -1, 3, 0, -5 }, new int[] { -5, -1, 0, 3 });
        // 整列済みのデータのとき
        assertQuickSort(new int[] { 1, 2, 3, 4 }, new int[] { 1, 2, 3, 4 });
        // 逆順に整列済みのデータのとき
        assertQuickSort(new int[] { 4, 3, 2, 1 }, new int[] { 1, 2, 3, 4 });

        //
        // 異常系
        //

        // 入力データが null のとき、例外エラーとなること
        testQuickSortNull();
    }

    private static void testQuickSortNull() {
        assertThrows(() -> QuickSort.sort(null));
    }

    private static void assertQuickSort(int[] input, int[] expected) {
        QuickSort.sort(input);

        if (!Arrays.equals(input, expected)) {
            throw new AssertionError("クイックソートの結果が正しくありません。"
                    + " 期待する結果=" + Arrays.toString(expected)
                    + ", 実際の値=" + Arrays.toString(input));
        }
    }

    private static void assertThrows(Runnable runnable) {
        try {
            runnable.run();
        } catch (IllegalArgumentException exception) {
            return;
        }

        throw new AssertionError("IllegalArgumentException が発生しませんでした。");
    }
}
