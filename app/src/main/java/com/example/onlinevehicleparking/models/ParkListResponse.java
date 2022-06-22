package com.example.onlinevehicleparking.models;

import java.util.List;

public class ParkListResponse {
    private boolean error;
    private String message;
    private List<parkList> parkList;

    public ParkListResponse(boolean error, List<parkList> parkList) {
        this.error = error;
        this.parkList = parkList;
    }

    public boolean isError() {
        return error;
    }



    public List<parkList> getParkList() {
        return parkList;
    }
}
