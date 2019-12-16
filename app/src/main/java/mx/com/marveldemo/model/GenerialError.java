package mx.com.marveldemo.model;

import java.io.Serializable;

public class GenerialError implements Serializable {


    private int errorCode;
    private int description;


    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getDescription() {
        return description;
    }

    public void setDescription(int description) {
        this.description = description;
    }
}
