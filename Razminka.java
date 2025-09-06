import java.util.Scanner;

public class Razminka{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите два целых числа: ");
        int a = sc.nextInt(), b = sc.nextInt();

        System.out.println("1: " + RussiaAdd(a, b));
        System.out.println("2: " + RussiaAddRec(a, b));
        System.out.println("3: " + mulHornerMSB(a, b));
        System.out.println("4: " + Add(a, b));
        System.out.println("5: " + Recursion(a, b));
    }

    //цикл сложений
    static int Add(int a, int b) {
        if (b < 0){
            a = -a; b = -b;
        }
        int r = 0;
        for (int i = 0; i < b; i++){
            r += a;
        }
        return r;
    }

    //рекурсия
    static int Recursion(int a, int b) {
        if (b < 0){
            return -Recursion(a, -b);
        }
        if (b == 0){
            return 0;
        }
        return a + Recursion(a, b - 1);
    }

    // Классическое «русское умножение»: пока b>0, сдвигаем и добавляем по младшему биту
    static int RussiaAdd(int a, int b) {
        int sign = 1;
        long x = a;
        long y = b;
        long r = 0;

        if (x < 0){
            x = -x; sign = -sign;
        }
        if (y < 0){
            y = -y; sign = -sign;
        }
        while (y > 0) {
            if ((y & 1L) != 0) r += x;
            x <<= 1; y >>= 1;
        }
        return (int)(sign < 0 ? -r : r);
    }

    // Классическое «русское умножение», но рекурсивно: на каждом шаге делим множитель пополам
    static int RussiaAddRec(int a, int b) {
        int sign = ((a ^ b) < 0) ? -1 : 1;
        long x = Math.abs((long)a);
        long y = Math.abs((long)b);
        long r = rec(x, y);
        return (int)(sign < 0 ? -r : r);
    }
    private static long rec(long x, long y) {
        if (y == 0) return 0;
        long half = rec(x << 1, y >> 1);         //
        return ((y & 1L) != 0) ? (half + x) : half;
    }

    // «Хорнер по битам»
    static int mulHornerMSB(int a, int b) {
        int sign = ((a ^ b) < 0) ? -1 : 1;
        long x = Math.abs((long)a), y = Math.abs((long)b);
        if (y == 0) return 0;

        int msb = 63 - Long.numberOfLeadingZeros(y); // индекс старшего установленного бита
        long r = 0;
        for (int i = msb; i >= 0; i--) {
            r <<= 1;
            if (((y >>> i) & 1L) != 0) r += x;
        }
        return (int)(sign < 0 ? -r : r);
    }
}
