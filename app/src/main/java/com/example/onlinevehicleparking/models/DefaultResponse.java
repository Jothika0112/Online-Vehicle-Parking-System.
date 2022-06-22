package com.example.onlinevehicleparking.models;
import com.google.gson.annotations.SerializedName;

public class DefaultResponse {
    @SerializedName("error")
    private boolean error;

    @SerializedName("message")
    private String message;
    @SerializedName("response")
    private String response;




    public DefaultResponse(boolean error, String message) {
        this.error = error;
        this.message = message;
    }


    public DefaultResponse(String response)
    {
      this.response = response;

    }
    public boolean isErr() {
        return error;
    }

    public String getMsg() {
        return message;
    }
}

