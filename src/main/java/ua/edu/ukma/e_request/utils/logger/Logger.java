package ua.edu.ukma.e_request.utils.logger;


import java.util.Date;

import static ua.edu.ukma.e_request.utils.logger.FileUtils.writeToFile;

public class Logger {
    public static String loggerPrefixStatic= "";
    public static boolean loggerEnabledStatic=true;

    public static void log(String message){
        System.out.println(message);
        String callerName = Thread.currentThread().getStackTrace()[2].getClassName();
        callerName=callerName.substring(callerName.lastIndexOf(".")+1);
        String messageCompleted = new Date()+"["+callerName+"]"+message+"\n";
        if(!loggerEnabledStatic){
            System.out.println(messageCompleted);
            return;
        }
        try {
            writeToFile(loggerPrefixStatic+"/Logs/"+callerName+".txt", messageCompleted);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void logException(String location, Throwable e, boolean printStackTrace){
        StringBuilder message = new StringBuilder("\n\n").append(location);
        if(printStackTrace) message.append("\nSOE-----------").append(new Date().toString()).append("------------------------------------");
        message.append("\n").append(e.getClass()).append(", message: ").append(e.getMessage()).append("\n");

        if(printStackTrace)
            for(StackTraceElement ste: e.getStackTrace()){
                message.append(ste.toString()).append("\n");
            }
        Throwable cause = e.getCause();
        while(cause != null){
            message.append("\n**Caused by**\n").append(e.getClass()).append(", message: ").append(cause.getMessage()).append("\n");

            if(printStackTrace)
                for(StackTraceElement ste: cause.getStackTrace()){
                    message.append(ste.toString()).append("\n");
                }
            cause = cause.getCause();
        }
        if(printStackTrace) message.append("EOE-------------------------------------------------");

        if(!loggerEnabledStatic){
            System.out.println(message);
            return;
        }

        try {
            String callerName = null;
            for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
                if(!stackTraceElement.getClassName().contains("Logger")) {
                    callerName = stackTraceElement.getClassName();
                    break;
                }
            }
            writeToFile(loggerPrefixStatic+"/exceptions/"+(callerName!=null?callerName.substring(callerName.lastIndexOf(".")+1)+".txt":"Unknown"), message.toString());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}