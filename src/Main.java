import java.util.Scanner;
public class Main {
    static Matrix read(Scanner sc){
        System.out.print("Введите число строк: ");
        int rows=sc.nextInt();
        
        System.out.print("Введите число столбцов: ");
        int cols=sc.nextInt();
        
        Complex[][] data=new Complex[rows][cols];
        System.out.println("Введите элементы (для каждого элемента введите Re и Im через пробел):");
        
        for (int row=0; row<rows; row++) {
            
            for (int col=0; col<cols; col++) {
                
                System.out.print("Элемент ["+row+"]["+col+"]: ");
                double r=sc.nextDouble();
                double i=sc.nextDouble();
                data[row][col]=new Complex(r, i);
            }
        
        }
        
        return new Matrix(data);
    }


    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        Matrix cur=null;
        while (true){
            System.out.println("\n--- Меню ---");
            System.out.println("1. Ввести матрицу");
            System.out.println("2. Сложить");
            System.out.println("3. Умножить");
            System.out.println("4. Разделить");
            System.out.println("5. Транспонировать");
            System.out.println("6. Определитель");
            System.out.println("7. Показать матрицу");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");
            
            int cmd=sc.nextInt();
            if (cmd==0) {
                
                break;

            }

            if (cmd>1&&cmd<=7&&cur==null) {
                System.out.println("Текущая матрица не задана. Сначала создайте ее (пункт 1).");
                
                continue;
            }
            try{
                if (cmd==1) {
                    cur=read(sc);
                    System.out.println("Матрица сохранена как текущая:");
                    cur.print();

                } else if (cmd==2){
                    System.out.println("Введите вторую матрицу для сложения:");
                    Matrix b=read(sc);
                    cur=cur.add(b);
                    System.out.println("Результат сложения (сохранен как текущая матрица):");
                    cur.print();
                } else if (cmd==3){
                    System.out.println("Введите вторую матрицу для умножения:");
                    Matrix b=read(sc);
                    cur=cur.mul(b);
                    System.out.println("Результат умножения (сохранен как текущая матрица):");
                    cur.print();
                } else if (cmd==4){
                    System.out.println("Введите вторую матрицу (делитель):");
                    Matrix b=read(sc);
                    cur=cur.div(b);
                    System.out.println("Результат деления (сохранен как текущая матрица):");
                    cur.print();
                } else if (cmd==5) {
                    
                    cur=cur.trans();
                    System.out.println("Транспонированная матрица (сохранена как текущая):");
                    cur.print();
                } else if (cmd==6) {
                    Complex d=cur.det();
                    System.out.println("Определитель текущей матрицы: "+d);
                } else if (cmd==7) {
                    System.out.println("Текущая матрица:");
                    cur.print();

                } else{
                    System.out.println("Неизвестная команда.");
                }
            }catch (Exception e){
                
                System.out.println("Ошибка: "+e.getMessage());
            }
        }
        
        sc.close();
    }
}

