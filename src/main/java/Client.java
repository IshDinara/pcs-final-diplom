import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket client = new Socket("127.0.0.1", 8989);
             BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
             PrintWriter out = new PrintWriter(client.getOutputStream(), true);) {

            Scanner scanner = new Scanner(System.in);
            String word = scanner.nextLine();
            out.println(word);

            String json = in.readLine();
            System.out.println(json);
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
}
