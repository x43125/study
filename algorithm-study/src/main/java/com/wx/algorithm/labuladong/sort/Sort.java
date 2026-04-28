package alg.sort;

import java.util.function.Consumer;

/*
冒泡排序
*/
public class Sort {
    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
                // 从左向右冒泡
                // 每轮固定一个最大的数
                if (nums[j] > nums[j + 1]) {
                    // 交换
                    swap(nums, j, j + 1);
                }
            }
        }
    }

    /*
     * 选择排序
     * 每一轮都从剩下未排序的里面选择一个最小的，排到之前的末尾
     */
    public void selectSort(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            int min = nums[i];
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (nums[j] < min) {
                    min = nums[j];
                    minIndex = j;
                }
            }
            swap(nums, i, minIndex);
        }
    }

    /*
     * 插入排序
     */
    public void insertSort(int[] nums) {
        // 在已经排序的数据里找到合适的位置插进去
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j >= 1; j--) {
                if (nums[j] < nums[j - 1]) {
                    swap(nums, j - 1, j);
                } else {
                    break;
                }
            }
        }
    }

    /*
     * 归并排序
     */
    public void mergeSort(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
    }

    private void mergeSort(int[] nums, int l, int r) {
        if (r <= l) {
            return;
        }
        int mid = (r - l) / 2 + l;
        mergeSort(nums, 0, mid);
        mergeSort(nums, mid + 1, r);
        merge(nums, 0, mid, r);
    }

    private void merge(int[] nums, int l, int mid, int r) {
        int[] temp = new int[r - l + 1];
        int i = l, j = mid + 1;
        int cur = 0;
        while (i <= mid && j <= r) {
            if (nums[i] < nums[j]) {
                temp[cur++] = nums[i++];
            } else {
                temp[cur++] = nums[j++];
            }
        }

        while (i <= mid) {
            temp[cur++] = nums[i++];
        }
        while (j <= r) {
            temp[cur++] = nums[j++];
        }
        for (int k = l; k <= r; k++) {
            nums[k] = temp[k - l];
        }
    }

    /*
     * 快速排序
     */
    public void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    private void quickSort(int[] nums, int l, int r) {
        if (r <= l) {
            return;
        }
        int pivotIndex = partition(nums, l, r);
        quickSort(nums, l, pivotIndex - 1);
        quickSort(nums, pivotIndex + 1, r);
    }

    private int partition(int[] nums, int l, int r) {
        int pivot = nums[l];
        int i = l, j = r;
        while (i < j) {
            while (i < j && nums[j] >= pivot) {
                j--;
            }
            while (i < j && nums[i] <= pivot) {
                i++;
            }
            if (i < j) {
                swap(nums, i, j);
            }
        }
        swap(nums, l, i);
        return i;
    }

    /*
     * 希尔排序
     */
    public void shellSort(int[] nums) {
        int time = 2;
        int n = nums.length;

        // 最外层迭代每轮排序的gap大小，逐渐缩小
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // 中层循环每次需要排序的那个值，需要选择插入到最终位置的那个值
            for (int i = 0; i < n; i++) {
                // 内存循环，从当前值下标到第一个下标，依次比较，如果小于就交换
                for (int j = i; j >= gap; j -= gap) {
                    if (nums[j] < nums[j - gap]) {
                        swap(nums, j - gap, j);
                    } else {
                        break;
                    }
                }
            }
        }
    }

    /*
     * 堆排序
     */
    public void heapSort(int[] nums) {
        // 初始构建第一个大顶堆
        int n = nums.length;
        for (int i = n / 2; i >= 0; i--) {
            // 调整当前i值到适合他的大顶堆位置，长度是n
            heapify(nums, n, i);
        }
        // 然后挨个出堆，再构建调整整个nums有序
        for (int i = n-1; i >=0 ; i--) {
            swap(nums, i, 0);
            heapify(nums, i, 0);
        }
    }

    public void heapify(int[] nums, int n, int i) {
        int biggestIndex = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        if (l < n && nums[l] > nums[biggestIndex]) {
            biggestIndex = l;
        }
        if (r < n && nums[r] > nums[biggestIndex]) {
            biggestIndex = r;
        }
        if (i != biggestIndex) {
            swap(nums, i, biggestIndex);
            // 递归向下去调整到适合他的位置
            heapify(nums, n, biggestIndex);
        }
    }

    public static void main(String[] args) {
        Sort sort = new Sort();

        // // 冒泡排序
        // test(sort::bubbleSort, "bubbleSort");
        // // 选择排序
        // test(sort::selectSort, "selectSort");
        // // 插入排序
        // test(sort::insertSort, "insertSort");
        // // 归并排序
        // test(sort::mergeSort, "mergeSort");
        // // 快速排序
        // test(sort::quickSort, "quickSort");
        // 希尔排序
        // test(sort::shellSort, "shellSort");
        // // 堆排序
        test(sort::heapSort, "heapSort");
    }

    public static void test(Consumer<int[]> action, String methodName) {
        System.out.println("methodName:[" + methodName + "] sorting");
        int[] nums1 = { 5, 2, 7, 1, 3, 6, 4 };
        int[] nums2 = { 5, 2 };
        int[] nums3 = { 5 };
        test(nums1, action);
        test(nums2, action);
        test(nums3, action);
        System.out.println();
    }

    public static void test(int[] nums, Consumer<int[]> action) {
        action.accept(nums);
        print(nums);
    }

    private static void print(int[] nums) {
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

}
