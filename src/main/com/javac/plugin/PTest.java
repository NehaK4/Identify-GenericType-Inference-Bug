package com.javac.plugin;


import java.util.List;

class PTest<T>
{   T t1;

        public static void main(String ar[])
    {

        @GCheck 
		PTest<? super Integer> obj=new  PTest<>(9);

    }


   public List<T> serv(List<T> k){

           return k;
    }

    public PTest(T i)
    {  t1=i;
           serv2(t1);
        System.out.println(i);

}
    public T serv2(T k){


        return k;
    }
}

