public class Field {
    char[][] field; // это поле создает двухмерный массив, в котором располагается поле игры.
    int size = 3; // в эту переменную записывается количество строк и столбцов в поле игры
    int count = 3;
    
    void initField() { // этот метод заполняет массив this.field пробелами
        this.field = new char[this.size][this.size];
        for (int row = 0; row < this.size; row++) {
            for (int col = 0; col < this.size; col++) {
                this.field[row][col] = ' ';
            }
        }
        System.out.println("Field initialized!");
    }
    void printField() { // этот метод выводит в консоль массив this.field, заполненный пробелами
          System.out.print("  ");
          for (int i = 1; i <= this.size; i++) {
              System.out.print(i + "  ");
          }
        System.out.println();
        for (int row = 0; row < this.size; row++) {
            System.out.print(row + 1);
            for (int col = 0; col < this.size; col++) {
                System.out.print("[" + this.field[row][col] + "]");
            }
            System.out.println();
        }
    }
    boolean isPlaceFree(int rowIndex, int colIndex) { /* этот метод проверяет, свободна ли ячейка массива
    (клетка поля), в которую пользователь хочет ввести х или о */
        if (rowIndex < 0 || colIndex < 0 || rowIndex >= this.size || colIndex >= this.size) {
            return false;
        }
        if (this.field[rowIndex][colIndex] == ' ') {
            return true;
        } else {
            return false;
        }
    }
    void setValue (int rowIndex, int colIndex, char value) { /* этот метод записывает х или о в ячейку массива,
    которую ввел пользователь, если эта ячейка свободна
    */
        this.field[rowIndex][colIndex] = value;
    }
    boolean isGameOver(char value) { /* этот метод проверяет, окончена ли игра - он проверяет, записаны ли в
    четырех направлениях (по вертикали, по горизонтали и в двух направлениях по диагонали) одинаковые знаки (х или о)
    и возвращает значение переменной isGameOver  в зависимости от итога проверки*/
        for (int row = 0; row < this.size; row++) {
            for (int col = 0; col < this.size; col++) {
                if (checkRightDir(row, col, value)) {
                    return true;
                } if (checkDownDir(row, col, value)) {
                    return true;
                } if (checkRightDiagonal(row, col, value)) {
                    return true;
                } if (checkLeftDiagonal(row, col, value)) {
                    return true;
                }
            }
        } return false;
    }

    boolean checkRightDir(int row, int col, char player) { /*этот метод проверяет, записаны ли хотя бы в одной колонке
    по вертикали (все символы должны быть одинаковы). Он используется в методе isGameOver*/
        if (col > this.size - this.count) {
            return false;
        }
        for (int i = col; i < col + this.count; i++) {
            if (this.field[row][i] != player) {
                return false;
            }
        } return true;
    }

    boolean checkDownDir(int row, int col, char player) { /*этот метод проверяет, все ли символы одинаковы
    хотя бы в одной строке по горизонтали. Этот метод используется в методе isGameOver*/
        if (row > this.size - this.count) {
            return false;
        }
        for (int i = row; i < row + this.count; i++) {
            if (this.field[i][col] != player) {
                return false;
            }
        } return true;
    }
    boolean checkRightDiagonal(int row, int col, char player) { /*этот метод проверяет, звсе ли символы
     диагонали, идущей слева сверху направо вниз, одинаковы. Этот метод также используется в методе isGameOver*/
        if (row > this.size - this.count) {
            return false;
        } if (col > this.size - this.count) {
            return false;
        }
        for (int i = 0; i < this.count; i++) {
            int indexRow = row + i;
            int indexCol = col + i;
            if (this.field[indexRow][indexCol] != player) {
                return false;
            }
        } return true;

    } boolean checkLeftDiagonal(int row, int col, char player) { /*этот метод проверят, все ли значения
     диагонали, идущей справа сверху налево вниз, одинаковы. Этот метод также используется в методе isGameOver*/
        if (row > this.size - this.count) {
            return false;
        } if (col < this.size - 1) {
            return false;
        }
        for (int i = 0; i < this.count; i++) {
            int indexRow = row + i;
            int indexCol = col - i;
            if (this.field[indexRow][indexCol] != player) {
                return false;
            }
        } return true;
    }
}
