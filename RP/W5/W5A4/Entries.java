package RP.W5.W5A4;

public class Entries {
    public int entrynumber;
    private String firstname = "";
    private String lastname = "";
    private int telno = 000000000;
    
    public Entries(String firstname, String lastname, int telno) {
        
        this.firstname = firstname;
        this.lastname = lastname;
        this.telno = telno;
        this.entrynumber = Telefonbuch.entries.size();
        Telefonbuch.entries.add(this);
        
    }


    
    public int getEntrynumber() {
        return entrynumber;
    }


    public String getFirstname() {
        return firstname;
    }



    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }



    public String getLastname() {
        return lastname;
    }


    public String getFullName(){
        return firstname + " " + lastname;
    }


    public void setLastname(String lastname) {
        this.lastname = lastname;
    }



    public int getTelno() {
        return telno;
    }



    public void setTelno(int telno) {
        this.telno = telno;
    }

    @Override
    public String toString(){
        return (firstname + " " + lastname + " telefonnummer: " + telno);
    }
}
