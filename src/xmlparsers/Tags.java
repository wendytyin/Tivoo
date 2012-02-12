package xmlparsers;

//to save parsed info from sax. 
//hard-coded for DukeBasketBall.xml
//tutorial 
//http://www.java-samples.com/showtutorial.php?tutorialid=152
public class Tags {
    private String subject;
    private String date;
    private String loc;
    
//    public void setType(String type){
//        //I don't know what this does
//    }
    
    public void setSubject(String s){
        subject=s;
    }
    
    public void setDate(String s){
        date=s;
    }

    public void setLoc(String qName) {
        loc=qName;
    }

    public String getDate() {
        return date;
    }

    public String getSubject() {
        return subject;
    }

    public String getLoc() {
        return loc;
    }
    

    
}
