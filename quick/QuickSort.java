package quick;

/**
 * クイックソート
 * 
 * 基準値を決めて、それより大きい値と小さい値へ分けることを繰り返しデータを並び替えます。 <br>
 * 整列済みや同じ値が多い大規模な入力では、{@code StackOverflowError} が発生することがあります。
 */
public class QuickSort {

    /**
     * ソート
     * 
     * 受け取った配列を直接並び替えます。
     * 
     * @param data ソート対象の配列
     * @throws IllegalArgumentException 配列が {@code null} のときの例外
     */
    public static void sort(int[] data) {
        if (data == null) {
            throw new IllegalArgumentException("ソート対象のデータがありません。");
        }

        quickSort(data, 0, data.length - 1);
    }

    /**
     * ソート
     * 
     * 指定された範囲を再帰的に並び替えます。
     * 
     * @param data  ソート対象の配列
     * @param left  処理対象範囲の開始位置
     * @param right 処理対象範囲の終了位置
     */
    private static void quickSort(int[] data, int left, int right) {

        // 処理対象が1つ以下のとき、再帰処理を終了
        if (left >= right) {
            return;
        }

        int pivotIndex = partition(data, left, right);

        quickSort(data, left, pivotIndex - 1);
        quickSort(data, pivotIndex + 1, right);
    }

    /**
     * 基準値の位置取得
     * 
     * 指定された範囲の要素の中で基準値以下の値を左側に集め、基準値の位置を決めます。
     * 
     * @param data  ソート対象の配列
     * @param left  開始位置
     * @param right 終了位置
     * @return 基準値の確定位置
     */
    private static int partition(int[] data, int left, int right) {
        int pivot = data[right]; // 基準値（処理対象の最後尾）
        int index = left; // 基準値以下の値を置く位置

        for (int i = left; i < right; i++) {

            // 比較する値が基準値以下のとき、左側に移動
            if (data[i] <= pivot) {
                swap(data, index, i);
                index++;
            }
        }

        // 左側に集めた値のすぐ後ろに基準値をおいて位置を確定
        swap(data, index, right);
        return index;
    }

    /**
     * 値入れ替え
     * 
     * 配列内の２つの位置の値を交換します。
     * 
     * @param data 入れ替え対象の配列
     * @param i    交換する値の位置
     * @param j    交換する値の位置
     */
    private static void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
}
