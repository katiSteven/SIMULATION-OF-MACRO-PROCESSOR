/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler.pkg3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
//import java.io.IOException;
//import 

/**
 *
 * @author steve
 */
public class Pass1 {
    
    //public static String[] dataType = new String[10];
    public static String[] variable = new String[30];
    public static String[] values = new String[30];
    public static String[] fundeclaration = new String[30];
    public static String[] funarg = new String[30];
    public static String[] funimplementation = new String[30];
    
    //public static int dataTypecount=0;
    public static int variablecount=0;
    public static int valuescount=0;
    public static int fundeclarationcount=0;
    public static int funargcount=0;
    public static int funimplementationcount=0;
    
    public static String keyword[] = {"int", "char", "float","double","boolean","String"};
    
    
    public static void search() {
        Arrays.sort(keyword);
        
        String currentLine;
        
        
        try{
            File file=new File("D:\\collegeDocuments\\LabExperiments\\test_web\\Compiler 3\\src\\compiler\\pkg3\\Input");
            FileReader fread=new FileReader(file);
            BufferedReader bf=new BufferedReader(fread);
            while((currentLine=bf.readLine())!=null){
                //System.out.println(currentLine);
                if(currentLine.contains("#define")){
                        //ReadLine();
                        //System.out.println(currentLine);
                        DefinitionExtractor(currentLine);
                        
                }
            }
        }catch(Exception e){
            System.out.println(e + "this");
        }
    }
    
    static void DefinitionExtractor(String currentLine) {
        String[] lineArray = currentLine.split(" ");
        StringBuilder extract = new StringBuilder();
        //StringBuilder funname = new StringBuilder();
        boolean b = false;
        for (String element : lineArray) {
            if (b == true)
                extract.append(element).append(" ");
                //System.out.println(extract + "extract");
            if (element.contains("#define"))
                b = true;
//            if(extract.indexOf("(") != -1){
//                fundec[fundeccount] = 
//            }
        }
        //System.out.println(extract);
        
        if(extract.indexOf("(") != -1 && currentLine.contains("#define") && extract.indexOf("=") != -1){
            //System.out.println(extract);
            //fundec[fundeccount] = funname.
            FunctionProcessor(extract);
            
        }
        else if (extract.indexOf("=") != -1 && currentLine.contains("#define")){
            VariableProcessor(extract);
            
        }
    }
    
    static void VariableProcessor(StringBuilder ex){
        String StringConvert = ex.toString();       // converter stringBuilder to normal String
        String[] arr = StringConvert.split(" ");
        for(int l=0;l<arr.length;l++){
//            if(arr[l].contains("#define")){
//                System.out.println("i was here");
//                continue;
//            }
//            if(Arrays.binarySearch(keyword, arr[l]) > -1) {
//                
//                dataType[dataTypecount] = arr[0];
//                //System.out.println(dataType[count]);
//                dataTypecount++;
//                continue;
//            }
            arr[l]=arr[l].replaceAll(","," ");
            String[] definition;
            definition = arr[l].split(" ");
            //System.out.println("\t\t");
            for (String definition1 : definition) {
                if (definition1.contains("=")) {
                    String[] s;
                    s = definition1.split("=");
                    for(int j=0;j<s.length;j++){
                        //System.out.print(variable[1]);
                        if(j == 0){
                            variable[variablecount] = s[j];
                            variablecount++;
                            //variable[j] = s[j].replace(';',' ');
                            //System.out.println(variable[j]);
                            //System.out.println(s[j].replace(';',' '));
                            //System.out.println(s[j]);
                        }
                        if(1 == j){
                            //System.out.println(s[j].replace(';',' ') + "after var");
                            values[valuescount] = s[j].replace(';',' ');
                            valuescount++;
                        }
                        
                        
                        //System.out.print(definition[j].replace(';', ' '));
                        //System.out.print("\t\t");
                    }
                    //System.out.print("\n" + "\t\t ");
                }
//                else {
//                    System.out.print(definition1.replace(';', ' '));
//                }
                //System.out.println("\top");
            }
        }
        //System.out.println(variable[0] + "yololl");
    }
    
    static void FunctionProcessor(StringBuilder ex){
        //return;
        String StringConvert = ex.toString();       // converter stringBuilder to normal String
        String[] lineArray = StringConvert.split("");
        StringBuilder funextract = new StringBuilder();
        StringBuilder impleextract = new StringBuilder();
        StringBuilder funargs = new StringBuilder();
        boolean b = false;
        int bracketcount = 0;
        for (String element : lineArray) {
            
            if(bracketcount==0) {
                b = true;
            }
            
            if(element.contains(")")) {
                b = false;
//                bracketcount++;
            }
            if (element.contains("(")){
                b = true;
                bracketcount++;
            }
            if (b == true){
                if(bracketcount == 0){
                    funextract.append(element);
                    //System.out.println(funextract + "fun1");
                    //bracketcount++;
                } else if(bracketcount>1){
                    impleextract.append(element.replace('(', ' '));
                    //System.out.println(impleextract + "imple1");
                } else {
                    //continue;
                }
            }
//            if (element.contains("(")){
//                b = true;
//                bracketcount++;
//            }
        
        }
        for (String element : lineArray) {
                    
                    if(element.contains(")")) {
                        //b = false;
                        break;
                    }
                    
                    
                    
                    if (b == true){
                        funargs.append(element);
                    }
                    
                    
                    if (element.contains("(")){
                        b = true;
                    }
                    
                }
        fundeclaration[fundeclarationcount] = funextract.toString();
        funarg[funargcount] = funargs.toString();
        funimplementation[funimplementationcount] = impleextract.toString();
//        System.out.println(fundeclaration[fundeclarationcount]);
//        System.out.println(funimplementation[funimplementationcount]);
        funargcount++;
        fundeclarationcount++;
        funimplementationcount++;
    }
    
//    public static void main(String args[] ) {
//        Arrays.sort(keyword);
//        
//        String currentLine ="";
//        
//        
//        try{
//            File file=new File("D:\\collegeDocuments\\LabExperiments\\SystemSoftware\\Ex3\\test.txt");
//            FileReader fread=new FileReader(file);
//            BufferedReader bf=new BufferedReader(fread);
//            while((currentLine=bf.readLine())!=null){
//                //System.out.println(currentLine);
//                if(currentLine.contains("#define")){
//                        //ReadLine();
//                        //System.out.println(currentLine);
//                        DefinitionExtractor(currentLine);
//                        
//                }
//            }
//        }catch(Exception e){
//            System.out.println(e + "this");
//        }
//    }
}
