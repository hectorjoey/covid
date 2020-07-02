package mobile.fhi360.covid_19selfscreeningtool.Api;


import java.util.List;

import mobile.fhi360.covid_19selfscreeningtool.model.Supervisor;
import mobile.fhi360.covid_19selfscreeningtool.model.UserHealthData;
import mobile.fhi360.covid_19selfscreeningtool.model.Users;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @FormUrlEncoded
    @POST("users")
    Call<Users> createUser(
            @Field("firstname") String firstname,
            @Field("lastname") String lastname,
            @Field("age") String age,
            @Field("phone") String phone,
            @Field("email") String email,
            @Field("password") String password,
            @Field("userType") String userType,
            @Field("gender") String gender,
            @Field("state") String state,
            @Field("designation") String designation,
            @Field("supervisorId") String supervisor);

    @FormUrlEncoded
    @POST("userHealthData")
    Call<UserHealthData> createUserHealthData(
            @Field("fullname") String fullname,
            @Field("date") String date,
            @Field("stateVisited") String stateVisited,
            @Field("feverSymptom") String feverSymptom,
            @Field("coughSymptom") String coughSymptom,
            @Field("difficultyInBreathingSymptom") String difficultyInBreathingSymptom,
            @Field("sneezingSymptoms") String sneezingSymptoms,
            @Field("chestPainSymptoms") String chestPainSymptoms,
            @Field("diarrhoeaSymptoms") String diarrhoeaSymptoms,
            @Field("fluSymptoms") String fluSymptoms,
            @Field("soreThroatSymptoms") String soreThroatSymptoms,
            @Field("lossOfSmellSymptoms") String lossOfSmellSymptoms,
            @Field("contactWithSomeoneWithSymptoms") String contactWithSomeoneWithSymptoms,
            @Field("exposedToFacilityWithConfirmedCase") String exposedToFacilityWithConfirmedCase,
            @Field("contactWithFever") String contactWithFever,
            @Field("contactWithCough") String contactWithCough,
            @Field("contactWithDifficultBreathing") String contactWithDifficultBreathing,
            @Field("contactWithSneeze") String contactWithSneeze,
            @Field("contactWithChestpain") String contactWithChestpain,
            @Field("contactWithDiarrhoea") String contactWithDiarrhoea,
            @Field("contactWithOtherFLu") String contactWithOtherFLu,
            @Field("contactWithSoreThroat") String contactWithSoreThroat,
            @Field("underlyingConditions") String underlyingConditions,
            @Field("specifyKidney") String specifyKidney,
            @Field("specifyPregnancy") String specifyPregnancy,
            @Field("specifyTB") String specifyTB,
            @Field("specifyDiabetes") String specifyDiabetes,
            @Field("specifyLiver") String specifyLiver,
            @Field("specifyChronicLungDisease") String specifyChronicLungDisease,
            @Field("specifyCancer") String specifyCancer,
            @Field("specifyHeartDisease") String specifyHeartDisease,
            @Field("specifyHIV") String specifyHIV,
            @Field("treatment") String treatment,
            @Field("enoughDrugsForThreeMonths") String enoughDrugsForThreeMonths,
            @Field("someoneHelpingYouManageHIV") String someoneHelpingYouManageHIV,
            @Field("covid19CareFromSomeoneInHousehold") String covid19CareFromSomeoneInHousehold);

    //the signin call
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @FormUrlEncoded
    @POST("login")
    Call<Users> login(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("users")
    Call<List<Users>> getUsers();

    @GET("userHealthData")
    Call<List<UserHealthData>> getUserHealthData();

    @GET("users/{id}")
    Call<List<Users>> getUsersById(@Path("id") Long id);


    @Headers({"Content-Type: application/json","Accept: application/json"})
    @FormUrlEncoded
    @POST("supLogin")
    Call<ResponseBody> supLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @FormUrlEncoded
    @POST("supervisor")
    Call<Supervisor> createSupervisor(
            @Field("fullname") String fullname,
            @Field("phone") String phone,
            @Field("email") String email,
            @Field("password") String password,
            @Field("userType") String userType,
            @Field("state") String state,
            @Field("designation") String designation);

    @GET("users/{supervisorId}")
    Call<List<Users>> getUsersBySupervisorId(@Path("supervisorId") Long supervisorId);

    @GET("supervisors")
    Call<Supervisor> getSupervisors();
}