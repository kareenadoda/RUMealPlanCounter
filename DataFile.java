public class DataFile { 

    public double time;
    public double date;
    public double month;
    public DataFile next;
     
    //default constructor
    public DataFile(){
    this.time=0.0;
    this.date=0.0;
    this.month=0.0;
    this.next=null;
    }
    

    //parameterized constructor that initialises user inputted values
   public DataFile( double time, double date, double month, DataFile next){
    this.time=time;
    this.date=date;
    this.month=month;
    this.next=next;

   }


   //now we begin with getter and setter methods
   //time
   public void setTime( double time){
    this.time=time;
   }

   public double getTime(){
    return this.time;
   }


   public void setDate( double date){
    this.date=date;

   }

   public double getDate(){
    return this.date;
   }
    

   public void setMonth( double month){
    this.month=month;
   }

   public double getMonth(){
    return this.month;

   }

   public void setNext(DataFile next){
    this.next=next;
   }

   public DataFile getNext(){
    return next;
   }


  
}
