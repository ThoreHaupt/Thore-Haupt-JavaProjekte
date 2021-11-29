package RP.W5.W5A4;

public class Entry {
    public int id;
    private String firstname = "";
    private String lastname = "";
    private int telno = 000000000;
    
    public Entry(String firstname, String lastname, int telno) {
        
        this.firstname = firstname;
        this.lastname = lastname;
        this.telno = telno;
        Telefonbuch.entriesID.put(this.id, this);
        Telefonbuch.entriesName.put(this.getFullName(), this);
        System.out.println("added " + getFirstname() + " " + getLastname());
    }


    
    public int getEntrynumber() {
        return id;
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
        return (firstname + " " + lastname);
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
        return (firstname + " " + lastname + ": " + telno);
    }
}
