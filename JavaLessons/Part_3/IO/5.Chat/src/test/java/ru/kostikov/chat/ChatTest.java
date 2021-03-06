package ru.kostikov.chat;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Алексей on 10.08.2016.
 */
public class ChatTest {
    @Test
    public void run() throws Exception {

        Chat chat = null;
        String questions = Joiner.on("").join("Привет\r\n","я тут\r\n", "закончить\r\n");
        String answers;
        ArrayList<String> stringBuf = new ArrayList<String>();
        boolean expected = true;
        boolean result   = false;

        // Переназначаем выходной поток в бууффер байт
        CharArrayWriter charArray = new CharArrayWriter();
        Writer output = new BufferedWriter(charArray );

        String url = Chat.class.getClassLoader().getResource("answers.txt").getFile();

        try {
            chat = new Chat(new StringReader(questions),
                            output,
                            new FileReader(new File(url)));
            chat.run();
        } catch (FileNotFoundException e) {
            System.out.println("Файл c ответами не найден");

        }

        // Читаем еще раз файл и вычитываем его
        BufferedReader fileReader = null;
        try {
            fileReader = new BufferedReader(new FileReader(new File(url)));
        }catch (FileNotFoundException e) {
            System.out.println("Файл c ответами не найден");

        }
        // Переводим весь файл в массив строк
        while ((answers = fileReader.readLine()) != null){
            stringBuf.add(answers);
        }

        // Переводим результат нашего чата в массив строк
        String[] lines = new String(charArray.toString()).split("\r\n");

        // Проверяем вхождение полученных строк в наш файл
        for (String line: lines){
            for (int i = 0; i < stringBuf.size(); i++){
                if (line.equals(stringBuf.get(i))){
                    result = true;
                    break;
                }
                result = false;
            }
        }

        assertThat(expected,  is(result));
    }

    @Test
    public void runPause() throws Exception {

        Chat chat = null;
        String questions = Joiner.on("").join("Привет\r\n","я тут\r\n", "стоп\r\n", "я тут\r\n", "продолжить\r\n", "ты где?\r\n",  "закончить\r\n");
        String answers;
        ArrayList<String> stringBuf = new ArrayList<String>();
        boolean expected = true;
        boolean result   = false;

        // Переназначаем выходной поток в бууффер байт
        CharArrayWriter charArray = new CharArrayWriter();
        Writer output = new BufferedWriter(charArray );

        String url = Chat.class.getClassLoader().getResource("answers.txt").getFile();

        try {
            chat = new Chat(new StringReader(questions),
                            output,
                            new FileReader(new File(url)));
            chat.run();
        } catch (FileNotFoundException e) {
            System.out.println("Файл c ответами не найден");

        }

        // Читаем еще раз файл и вычитываем его
        BufferedReader fileReader = null;
        try {
            fileReader = new BufferedReader(new FileReader(new File(url)));
        }catch (FileNotFoundException e) {
            System.out.println("Файл c ответами не найден");

        }
        // Переводим весь файл в массив строк
        while ((answers = fileReader.readLine()) != null){
            stringBuf.add(answers);
        }

        // Переводим результат нашего чата в массив строк
        String[] lines = new String(charArray.toString()).split("\r\n");


        // Проверяем вхождение полученных строк в наш файл
        for (String line: lines){
            for (int i = 0; i < stringBuf.size(); i++){
                if (line.equals(stringBuf.get(i))){
                    result = true;
                    break;
                }
                result = false;
            }
        }

        assertThat(expected,  is(result));
    }


}