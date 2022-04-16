// import edu.princeton.cs.algs4.StdIn;
// import edu.princeton.cs.algs4.StdOut;
// import edu.princeton.cs.algs4.StdRandom;

// public class RandomWord {
//     public static void main(String[] args) {
//         String s = StdIn.readString();
//         double p = 1.0;
//         String champion = s;
//         while (!StdIn.isEmpty()) {
//             boolean flag = StdRandom.bernoulli(1/p);
//             if (flag) {
//                 champion = s;
//             }
//             s = StdIn.readString();
//             p++;
//         }
//         System.out.println(champion);
//     }
// }
