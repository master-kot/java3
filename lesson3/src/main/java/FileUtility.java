import java.io.*;
import java.util.*;

public class FileUtility {

    /**
     * Структура файла ввода: в первой строке одно целое число - N
     * далее следует N целых чисел через пробел
     * Метод должен отсортировать элементы с четным значением,
     * а нечетные оставить на своих местах и вывести результат через пробел в файл вывода
     * Пример:
     * in:
     * 5
     * 5 4 2 1 3    // 2 4
     * out:
     * 5 2 4 1 3
     */
    public void sortEvenElements(File in, File out) {
        //TODO
        try {
            Scanner input = new Scanner(in);
            int counter = input.nextInt();
            int[] inputArray = new  int[counter];
            LinkedList<Integer> evenArray = new LinkedList<>();
            for (int i = 0; i < counter; i++) {
                inputArray[i] = input.nextInt();
                if (inputArray[i] % 2 == 0) evenArray.add(inputArray[i]);
            }
            Collections.sort(evenArray);
            for (int i = 0; i < counter; i++) {
                if (inputArray[i] % 2 == 0) inputArray[i] = evenArray.pollFirst();;
            }
            StringBuilder output = new StringBuilder();
            for (int i = 0; i < counter; i++) {
                output.append(inputArray[i]).append(" ");
            }
            fileWriter(out, output.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Генератор паролей, пароль должен отвечать требованиям:
     * длина не менее 6 и не более 12, включает минимум по одному символу
     * из наборов: a-z, A-Z, 0-9, {*,!,%}
     * все пароли должны быть разными
     * На вход метод получает файл с логинами пользователей
     * Метод должен записать в файл вывода записи с логинами и паролями
     * для каждого пользователя
     */

    static Random random = new Random();

    private String generatePassword (int counter) {
        StringBuilder password = new StringBuilder();
        password.append((char)('a' + random.nextInt(26)));
        password.append((char)('A' + random.nextInt(26)));
        password.append(('0'  + random.nextInt(10)));
        char[] symbols = {'*','!','%'};
        password.append(symbols[random.nextInt(3)]);
        for (int i = 0; i < counter; i++) {
             password.append((char)('a' + random.nextInt(26)));
        }
        return password.toString();
     }

    public void passwordGen(File in, File out) {
        //TODO
        StringBuilder content = new StringBuilder();
        try {
            Scanner input = new Scanner(in);
            while (input.hasNextLine()) {
                content.append(input.nextLine());
                content.append(" ");
                content.append(generatePassword(random.nextInt(6)));
                content.append("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        fileWriter(out, content.toString());
    }

    /**
     *  Метод должен дописать в переданный файл все
     * записи из списка по одной записи в строке
     * */
    public void appender(File file, List<String> records) {
        //TODO
        try {
            FileWriter writer = new FileWriter(file, true);
            for (String rec : records) {
                writer.write("\n" + rec);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод возвращает список из N последних строк файла
     * строки можно дополнять пробелами до длины 80
     * тогда количество символов в файле будет предсказуемо
     * 10 строк это ровно 800 символов
     * Изучите класс RandomAccessFile для эффективного решения данной
     * задачи
     * Альтернативное решение: использование очереди или стека
     * */
    public List<String> getNString(String pathToFile, int n) {
        //TODO
        try {
            RandomAccessFile input = new RandomAccessFile(pathToFile, "rwd");
            input.seek(input.length() - 81 * 100);
            LinkedList<String> list = new LinkedList<>();
            while (true) {
                String str = input.readLine();
                if (str == null) break;
                list.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String fileReader(File file) {
        StringBuilder content = new StringBuilder();
        try {
            Scanner in = new Scanner(file);
            while (in.hasNextLine()) {
                content.append(in.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    private void fileWriter(File output, String content) {
        try {
            FileWriter writer = new FileWriter(output);
            //BufferedWriter bufferedWriter = new BufferedWriter(writer);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FileUtility fu = new FileUtility();
        String path ="//home//nikolay//IdeaProjects//java3//task3//src//main//java//";
        File input = new File(path + "file.txt");
        File output = new File(path + "output.txt");
        File input2 = new File(path + "file2.txt");
        File output2 = new File(path + "output2.txt");
        File append = new File(path + "append.txt");
        fu.sortEvenElements(input, output);
        fu.passwordGen(input2, output2);
        ArrayList<String> example = new ArrayList<>();
        for (int i = 4; i < 11; i++) {
            example.add("str" + i);
        }
        fu.appender(append, example);
    }
}