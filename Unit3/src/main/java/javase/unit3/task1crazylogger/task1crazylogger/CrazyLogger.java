package javase.unit3.task1crazylogger.task1crazylogger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;


public class CrazyLogger {
    private StringBuilder container = new StringBuilder();
    private int counter = 0;

    public void write(Level level, String message) {
        counter++;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY hh:mm");
        LocalDateTime timeNow =  LocalDateTime.now();
        String strDate = formatter.format(timeNow);
        if (message==null||message.equals(""))
            container.append(strDate).append(" ").append(level).append(" ").append("Something happened...").append("\n");
        else
        container.append(strDate).append(" ").append(level).append(" ").append(message).append("\n");
    }

    public void printOnConsole(Level level){
        if (level== null)
            return;
        String[] split = container.toString().split("\n");
        Arrays.stream(split).filter(x->x.substring(17,17+ level.toString().length()).equals(level.toString())).forEach(System.out::println);
    }
    public void printOnConsole(String message){
        if (message== null)
            return;
        String[] split = container.toString().split("\n");
        Arrays.stream(split).filter(x->x.substring(x.indexOf(32, 16)).contains(message)).forEach(System.out::println);

    }

    public void printOnConsole(Level level, String message){
        if (message== null||level==null)
            return;
        String[] split = container.toString().split("\n");
        Arrays.stream(split).filter(x->{
            return x.substring(17,17+ level.toString().length()).equals(level.toString())&& x.substring(x.indexOf(32, 16)).contains(message);})
                .forEach(System.out::println);

    }

    public void printOnConsole(){
       System.out.println(container);
    }

    public int countLogs(){
        return counter;

    }



}