package sv.ues.fia.tarea1;

/**
 * Created by TruenoBlanco on 11/5/2016.
 */
public class Contact {

    String name ,email,uname,pass;
    int id,rol;

    public void setName(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return this.name;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getEmail()
    {
        return this.email;
    }
    public void setUname(String uname)
    {
        this.uname = uname;
    }
    public String getUname()
    {
        return this.uname;
    }
    public void setPass(String pass)
    {
        this.pass = pass;
    }
    public String getPass()
    {
        return this.pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }
}
