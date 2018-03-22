/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler.pkg3;

//import static compiler.pkg3.Compiler3.DefinitionExtractor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author steve
 */
public class Pass2 {
    
    public static String[] fundec = new String[30];
    
    public static int fundeccount=0;
    
    public static void main(String[] args) {
        Pass1.search();
        //System.out.println(Compiler3.fundeclaration[0]);
        //defSearch(currentLine);
        String currentLine;
//        System.out.println(Compiler3.values[1]);
        
        try{
            File file=new File("D:\\collegeDocuments\\LabExperiments\\test_web\\Compiler 3\\src\\compiler\\pkg3\\Input");
            FileReader fread=new FileReader(file);
            BufferedReader bf=new BufferedReader(fread);
            while((currentLine=bf.readLine())!=null){
                //System.out.println(currentLine);
                if(currentLine.contains("#define")){
                    //ReadLine();
                    //System.out.println(currentLine);
                    //DefinitionExtractor(currentLine);

                }
                else{
                    defSearch(currentLine);
                    //System.out.println(defReplacer(currentLine));
                    //System.out.println(curr);
                            
                }
            }
        }catch(IOException e){
            System.out.println(e + "this");
        }
        
    }
    
    static void defSearch(String inline) {
        
        int count = 0;
        
        for(int i=0;Pass1.variable[i]!=null;i++){
            if(inline.contains(Pass1.variable[i])){
                String replaceAll = inline.replaceAll(Pass1.variable[i], Pass1.values[i]);
                count++;
                System.out.println(defReplacer(replaceAll));
                //System.out.println(count);
                
            }
        }
        
        //char[] dst = new char[100];
//        System.out.println(Compiler3.fundeclaration[0]);
        for(int i=0;Pass1.fundeclaration[i]!=null;i++){
            
            if(inline.contains(Pass1.fundeclaration[i])){
                //System.out.println(Compiler3.fundeclaration[i]);
                String[] lineArray = inline.split("");
                //inline.getChars(inline.indexOf('('), inline.indexOf('('), dst, 0);
//                System.out.println(dst[1]);
                StringBuilder funextract = new StringBuilder();
                boolean b = false;
                for (String element : lineArray) {
                    
                    if(element.contains(")")) {
                        //b = false;
                        break;
                    }
                    
                    if (b == true){
                        funextract.append(element);
                    }
                    
                    if (element.contains("(")){
                        b = true;
                    }
                }
                //System.out.println(funextract);
                fundec[fundeccount] = funextract.toString();
                //System.out.println(fundec[fundeccount] + " fundec");
                fundeccount++;
                //String Replaceall = inline.replaceAll(inline, inline);
                //System.out.println(fundec.length);
                count++;
                System.out.println(defReplacer(inline));
            }
        }
//        System.out.println(funextract);
        //String toString = dst.toString();
//        System.out.println(toString + " st990");
        //return null;
//        if(inline.indexOf("pi", 0) != -1)
//            System.out.println(inline +" yes"+inline.indexOf("pi", 0));

        //System.out.println(inline);
        if(count == 0){
            System.out.println(defReplacer(inline));
        }
    }
    
    static int n=0;
    static String defReplacer(String currentLine) {
        //String[] replacement = new
        
        for(int i=0;Pass1.fundeclaration[i]!=null;i++){
            if(currentLine.contains(Pass1.fundeclaration[i])){
                //System.out.println(Compiler3.fundeclaration[i] + " here");
                
                String wholedec = Pass1.fundeclaration[i] + "(" + fundec[n] + ")";
                
                //System.out.println(wholedec + "this");
                String replaceme = currentLine.replace(wholedec, Pass1.funimplementation[i]);
                //System.out.println(replaceAll);
                //System.out.println(Compiler3.funarg[1] + " funarge here");
//                for (int j=0;Compiler3.funarg[j]!=null;i++){
//                    if(replaceme.contains(Compiler3.funarg[j])){
                        String replaceAgain = replaceme.replaceAll(Pass1.funarg[i], fundec[n]);
                        n++;
                        return replaceAgain;
//                    }
//                }
            }
        }
        return currentLine;
        
        
    }
}
