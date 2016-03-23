package com.inin;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            new AppMenu().start();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
