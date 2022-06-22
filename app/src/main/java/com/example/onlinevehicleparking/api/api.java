package com.example.onlinevehicleparking.api;

import com.example.onlinevehicleparking.models.DefaultResponse;
import com.example.onlinevehicleparking.models.LoginResponse;
import com.example.onlinevehicleparking.models.ParkListResponse;
import com.example.onlinevehicleparking.models.ParkOwnerPhnCapResponse;
import com.example.onlinevehicleparking.models.ParkOwnerSensorResponse;
import com.example.onlinevehicleparking.models.ParkStatusResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface api {

  @FormUrlEncoded
  @Headers({"Accept: application/json"})
  @POST("registerUser.php")
  Call<DefaultResponse> createUser(
          @Field("fullName") String fullName,
          @Field("phoneNum") String phoneNum,
          @Field("address") String address,
          @Field("vehicleType") String vehicleType,
          @Field("vehicleRegNo") String vehicleRegNo,
          @Field("password") String password
  );

  @FormUrlEncoded
  @POST("userLogin.php")
  Call<LoginResponse> userLoginCheck(
          @Field("phoneNum") String phoneNum,
          @Field("password") String password
  );


  @GET("parkList.php")
  Call<ParkListResponse> getParkList();

  @FormUrlEncoded
  @POST("parkOwnerSensorStatus.php")
  Call<ParkOwnerSensorResponse> getParkOwnerSensorData(
          @Field("park_name") String park_name
  );

  @FormUrlEncoded
  @POST("bookNow.php")
  Call<DefaultResponse> bookNow(
          @Field("park_name") String park_name,
          @Field("user_fullName") String user_fullName,
          @Field("user_phoneNum") String user_phoneNum,
          @Field("vehicleType") String vehicleType,
          @Field("vehicleRegNo") String vehicleRegNo
  );

  @FormUrlEncoded
  @POST("rentYourSpace.php")
  Call<DefaultResponse> rentYourSpaceReg(
          @Field("park_name") String park_name,
          @Field("owner_name") String owner_name,
          @Field("phone") String phone,
          @Field("capacity") String capacity,
          @Field("address") String address,
          @Field("password") String password,
          @Field("latitude") Double latitude,
          @Field("longitude") Double longitude,
          @Field("map_location") String map_location
  );


  @FormUrlEncoded
  @POST("phnNcapacity.php")
  Call<ParkOwnerPhnCapResponse> phnNcapacityByParkNmNAdd_park_owner(
          @Field("park_name") String park_name
  );


  @FormUrlEncoded
  @POST("parkStatus.php")
  Call<ParkStatusResponse> parkStatus_parkedNwhere(
          @Field("user_phoneNum") String user_phoneNum,
          @Field("vehicleType") String vehicleType,
          @Field("vehicleRegNo") String vehicleRegNo
  );
}






   /* @GET("parkList.php")
    Call<ParkListResponse> getParkList();

    @FormUrlEncoded
    @POST("parkOwnerSensorStatus.php")
    Call<ParkOwnerSensorResponse> getParkOwnerSensorData(
            @Field("park_name") String park_name
    );

    @FormUrlEncoded
    @POST("bookNow.php")
    Call<DefaultResponse> bookNow(
            @Field("park_name") String park_name,
            @Field("user_fullName") String user_fullName,
            @Field("user_phoneNum") String user_phoneNum,
            @Field("vehicleType") String vehicleType,
            @Field("vehicleRegNo") String vehicleRegNo
    );

    @FormUrlEncoded
    @POST("rentYourSpace.php")
    Call<DefaultResponse> rentYourSpaceReg(
            @Field("park_name") String park_name,
            @Field("owner_name") String owner_name,
            @Field("phone") String phone,
            @Field("capacity") String capacity,
            @Field("address") String address,
            @Field("password") String password,
            @Field("latitude") Double latitude,
            @Field("longitude") Double longitude,
            @Field("map_location") String map_location
    );



    @FormUrlEncoded
    @POST("phnNcapacity.php")
    Call<ParkOwnerPhnCapResponse> phnNcapacityByParkNmNAdd_park_owner(
            @Field("park_name") String park_name
    );


    @FormUrlEncoded
    @POST("parkStatus.php")
    Call<ParkStatusResponse> parkStatus_parkedNwhere(
            @Field("user_phoneNum") String user_phoneNum,
            @Field("vehicleType") String vehicleType,
            @Field("vehicleRegNo") String vehicleRegNo
    );

*/



