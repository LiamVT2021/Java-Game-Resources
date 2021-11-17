package heap;

public abstract class PrimHeap extends HeapADT implements PrimPushPop {

    public static class Int extends PrimHeap {

        private int[] arr;

        @Override
        public boolean push(int i) {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public int pop() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public int peek() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        protected void heapUp(int i) {
            // TODO Auto-generated method stub

        }

        @Override
        protected void heapDown(int i) {
            // TODO Auto-generated method stub

        }

    }

}
