class Test extends Thread{
    public void run(){
        System.out.println(Runtime.getRuntime().availableProcessors());
        for(int i=1;i<5;i++){
            try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println(e);}
            System.out.println(i);
        }
    }
    public static void main(String args[]){
        Test t1=new Test();
        Test t2=new Test();

        t1.run();
        t2.run();
    }
} 