package s4.spring.td5.entities;

import javax.persistence.*;


@Entity
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Script scripts;

    private String date;

    private String content;

    private String comment;
    
    private int script_id;

    public History()
    {

    }

    public History(String date, String content, String comment,int script_id)
    {
        this.date=date;
        this.content=content;
        this.comment=comment;
        this.script_id=script_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Script getScripts() {
        return scripts;
    }

    public void setScripts(Script scripts) {
        this.scripts = scripts;
    }

    /**
     * @return String return the scripts_id
     */
    public int getScript_id() {
        return script_id;
    }

    /**
     * @param scripts_id the scripts_id to set
     */
    public void setScript_id(int script_id) {
        this.script_id = script_id;
    }

}
