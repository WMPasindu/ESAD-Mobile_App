package com.esad.group.assignment.two.dev.interfaces;

import com.esad.group.assignment.two.dev.modal.request.Absence;
import com.esad.group.assignment.two.dev.modal.request.RegisterUser;
import com.esad.group.assignment.two.dev.modal.request.SmartCard;
import com.esad.group.assignment.two.dev.modal.request.TimeTable;
import com.esad.group.assignment.two.dev.modal.response.AbsenceResponse;
import com.esad.group.assignment.two.dev.modal.response.RegisterUserResponse;
import com.esad.group.assignment.two.dev.modal.response.SmartCardResponse;
import com.esad.group.assignment.two.dev.modal.response.TicketResponse;
import com.esad.group.assignment.two.dev.modal.response.TimeTableResponse;
import com.esad.group.assignment.two.dev.modal.response.VerifyTicketResponse;
import com.esad.group.assignment.two.dev.utils.AppConstants;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {
    String BASE_URL = AppConstants.BASE_URL;

    @GET("account/login/{username}/{password}/{type}")
    Call<Boolean> login( @Path("username") String username,  @Path("password") String password,  @Path("type") String type);

    @POST("passenger/signup")
    Call<RegisterUserResponse> createUser(@Body RegisterUser body);

    @POST("driver/signup")
    Call<RegisterUserResponse> createDriver(@Body RegisterUser body);

    @POST("driver/absence")
    Call<AbsenceResponse> addAbsence(@Body Absence body);

    @POST("smartcard/create")
    Call<SmartCardResponse> smartCard(@Body SmartCard body);

    @GET("ticket/{ticket_number}")
    Call<VerifyTicketResponse> getTicket(@Path("ticket_number") int ticketNumber);

    @POST("timetable/create")
    Call<TimeTableResponse> createTimeTable(@Body TimeTable timeTable);
}
