package merge;

import java.util.ArrayList;
import java.util.List;

/**
 * マージソート
 * 
 * データを小さく分けてからそれぞれを並び替え、最後に一つへ結合しデータを並び替えます。
 */
public class MergeSort {

    /**
     * ソート
     * 
     * 指定された並び順で並び替えられたデータを作成し返却します。
     * 並び順が {@code null} の場合は降順でソートされます。
     * 
     * @param data  ソート対象のリスト
     * @param order 並び順
     * @return ソート後のリスト
     * @throws IllegalArgumentException リストが {@code null} のときの例外
     */
    public static List<Employee> sort(List<Employee> data, SortOrder order) {
        if (data == null) {
            throw new IllegalArgumentException("ソート対象のデータがありません。");
        }

        if (order == null) {
            order = SortOrder.DESC;
        }

        return mergeSort(data, order);
    }

    /**
     * ソート
     * 
     * リストを再帰的に分割し、ソートしながら結合します。
     * 
     * @param data  ソート対象のリスト
     * @param order 並び順
     * @return ソート後のリスト
     */
    private static List<Employee> mergeSort(List<Employee> data, SortOrder order) {

        // リストの要素が1つ以下のとき、再帰処理を終了
        if (data.size() <= 1) {
            return data;
        }

        // 中央で半分に分割
        int halfIdx = data.size() / 2;
        List<Employee> left = data.subList(0, halfIdx);
        List<Employee> right = data.subList(halfIdx, data.size());

        // 左右のリストを再帰的にソート
        List<Employee> sortedLeft = mergeSort(left, order);
        List<Employee> sortedRight = mergeSort(right, order);

        // ソート済みのリストを結合
        return merge(sortedLeft, sortedRight, order);
    }

    /**
     * 結合
     * 
     * @param left  左側のソート済みリスト
     * @param right 右側のソート済みリスト
     * @param order 並び順
     * @return 結合後のリスト
     */
    private static List<Employee> merge(List<Employee> left, List<Employee> right, SortOrder order) {
        List<Employee> mergedList = new ArrayList<>(left.size() + right.size());
        int leftIdx = 0;
        int rightIdx = 0;

        // 左右の先頭の要素から比較し、並び順に従ってひとつずつ追加
        while (leftIdx < left.size() && rightIdx < right.size()) {
            Employee leftEmp = left.get(leftIdx);
            Employee rightEmp = right.get(rightIdx);

            // 年齢が同じ場合は元のリストの並び順を優先する
            boolean takeLeft = order == SortOrder.ASC
                    ? leftEmp.getAge() <= rightEmp.getAge()
                    : leftEmp.getAge() >= rightEmp.getAge();

            if (takeLeft) {
                mergedList.add(leftEmp);
                leftIdx++;
            } else {
                mergedList.add(rightEmp);
                rightIdx++;
            }
        }

        // 片方のリストに残った要素を追加
        while (leftIdx < left.size()) {
            mergedList.add(left.get(leftIdx));
            leftIdx++;
        }

        while (rightIdx < right.size()) {
            mergedList.add(right.get(rightIdx));
            rightIdx++;
        }

        return mergedList;
    }
}
