public class Test {
    public static void main(String[] args) {
        final int[][] arr = {{1,2,3},{4,5,6}};

        int[][] temp = arr.clone();

        

        for (int[] is : temp) {
            for (int i = 0; i < is.length; i++) {
                System.out.println(is[i]);
            }
        }

        for (int[] is : arr) {
            for (int i = 0; i < is.length; i++) {
                System.out.println(is[i]);
            }
        }

    }
}
