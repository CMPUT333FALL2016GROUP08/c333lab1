/**
 * Created by michaelxi on 2016-09-30.
 */
public class fileType {
    public fileType(){

    }

    public Integer getHeaderLength() {
        return headerLength;
    }

    public void setHeaderLength(Integer headerLength) {
        this.headerLength = headerLength;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Integer headerLength;
    private String header;
    private String name;

}
