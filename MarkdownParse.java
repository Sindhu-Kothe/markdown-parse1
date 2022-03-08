// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;    
        //int loopcount = 0;
        //System.out.print(markdown.length());
        while(currentIndex < markdown.length()) {   
        //while(loopcount < 3) {
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            //System.out.println(nextOpenBracket);
            if(nextOpenBracket != -1){
                int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
                if(nextCloseBracket == -1){return toReturn;}
                //System.out.println(nextCloseBracket);
                int openParen = markdown.indexOf("(", nextCloseBracket);
                //System.out.println(openParen);
                if(openParen != -1){
                    int closeParen = markdown.indexOf(")", openParen);
                    //System.out.println(closeParen);
                    System.out.println(markdown.substring(openParen + 1, closeParen));
                    toReturn.add(markdown.substring(openParen + 1, closeParen));
                    currentIndex = closeParen + 1;
                }else{currentIndex= nextCloseBracket;}
                //System.out.println("CurrentIndex:" + currentIndex);
                //loopcount= loopcount+1;
            } else {currentIndex =  markdown.length()+1;}
            
        }
        //System.out.println(toReturn);
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}