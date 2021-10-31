package heap;

public abstract class PrimHeap extends HeapADT implements PrimPushPop {

    public static class Int extends PrimHeap {

        private int[] arr;

        @Override
        public boolean compareIndex(int a, int b) {
            return arr[a] < arr[b];
        }

        @Override
        public void swap(int a, int b) {
            int temp = arr[a];
            arr[a] = arr[b];
            arr[b] = temp;
        }

    }

}
