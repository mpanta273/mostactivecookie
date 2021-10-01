public class Cookie {
    private String id;
    private int frequency;
    public  Cookie(String id){
        this.id=id;
        this.frequency=1;
    }  
    public String getid(){
        return this.id;
    }
    public int getfrequency(){
        return this.frequency;
    }
    public void setid(String id){
        this.id=id;
    }
    public void setfrequency(int num){
        this.frequency=num;
    }
    public void raisefrequency(){
        this.frequency++;
    }

}
