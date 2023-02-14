import java.util.Scanner;

public class TheGame {
    Field gameField; // это поле создает объект gameField типа Field, который явл. игровым полем.
    Scanner sc1 = new Scanner(System.in); // это поле создает сканнер
    char whoMakeTurn; // это поле создает переменную, котороая
    // хранит информацию о том, кто в данный момент ходит(символ х или о).
    boolean gameOver = false; // это поле создает переменную, в которой записано, закончина игра или нет

    void setupNewGame() { /* этот метод создает и выводит на консоль поле. В нем используются методы
     initField и printField из класса Field*/
        System.out.println("You'll play new XO game!");
        this.gameField = new Field();
        this.gameField.initField();
        this.gameField.printField();
    }
    void play() { /*этот метод содержит код игры в крестики-нолики - сначала в переменную first записывается, кто
    первым ходит (это же значение записывается в переменную whoMakeTurn, и после каждого хода ее значение меняется )
     затем, пока игра не завершена (то есть пока метод isGameOver класса Field возвращает false)
      выполняется метод turn и проходит проверка, завершена ли игра*/
        this.setupNewGame();
        System.out.print(" Whose turn is first: ");
        char first = this.sc1.next().charAt(0);
        if (first == 'X' || first == 'O') {
            this.whoMakeTurn = first;
        } else {
            System.out.println("Only 'X' and 'O' are available here, so 'X' will be the first" );
            this.whoMakeTurn = 'X';
        } while (!gameOver) {
            turn();
            this.gameOver = this.gameField.isGameOver(whoMakeTurn);
            if (this.gameOver) {
                System.out.println(this.whoMakeTurn + " wins!");
            }
            if (this.whoMakeTurn == 'X') {
                this.whoMakeTurn = 'O';
            } else {
                this.whoMakeTurn = 'X';
            }
        }

    } void turn() { /* этот метод содержит код, который выполняется при каждом ходе, пока игра не завершена.
     в переменную rowNumber заносится номер ряда, в colNumber - номер колонки, после чего из этих переменных
     вычислется индекс колонки и ряда, в которые пользователь хочет записать символ. Если ячейка свободна и игра
     не окончена, символ записывается в ячейку, если нет, пользователю предлагается выбрать другую ячеку, после чего
     метод начинает работу сначала. */
        System.out.println(this.whoMakeTurn + ", your turn!");
        System.out.println("Choose row(ряд): ");
        int rowNumber = this.sc1.nextInt();
        System.out.println("Choose col(колонку): ");
        int colNumber = this.sc1.nextInt();
        int rowIndex = rowNumber - 1;
        int colIndex = colNumber - 1;
        if (this.gameField.isPlaceFree(rowIndex, colIndex)) {
            this.gameField.setValue(rowIndex, colIndex, this.whoMakeTurn);
            this.gameField.printField();
        } else {
            System.out.println("Wrong number (maybe this place is not free( ). Make turn again!");
            turn();
        }
    }
}
