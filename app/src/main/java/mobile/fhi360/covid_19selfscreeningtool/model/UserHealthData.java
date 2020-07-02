package mobile.fhi360.covid_19selfscreeningtool.model;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@IgnoreExtraProperties
public class UserHealthData {

    @SerializedName("fullname")
    @Expose
    private String fullname;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("stateVisited")
    @Expose
    private String stateVisited;

    @SerializedName("feverSymptom")
    @Expose
    private String feverSymptom;

    @SerializedName("coughSymptom")
    @Expose
    private String coughSymptom;

    @SerializedName("difficultyInBreathingSymptom")
    @Expose
    private String difficultyInBreathingSymptom;

    @SerializedName("sneezingSymptoms")
    @Expose
    private String sneezingSymptoms;

    @SerializedName("chestPainSymptoms")
    @Expose
    private String chestPainSymptoms;

    @SerializedName("diarrhoeaSymptoms")
    @Expose
    private String diarrhoeaSymptoms;

    @SerializedName("fluSymptoms")
    @Expose
    private String fluSymptoms;

    @SerializedName("soreThroatSymptoms")
    @Expose
    private String soreThroatSymptoms;

    @SerializedName("lossOfSmellSymptoms")
    @Expose
    private String lossOfSmellSymptoms;

    @SerializedName("contactWithSomeoneWithSymptoms")
    @Expose
    private String contactWithSomeoneWithSymptoms;

    @SerializedName("contactWithFever")
    @Expose
    private String contactWithFever;

    @SerializedName("contactWithCough")
    @Expose
    private String contactWithCough;

    @SerializedName("contactWithDifficultBreathing")
    @Expose
    private String contactWithDifficultBreathing;

    @SerializedName("contactWithSneeze")
    @Expose
    private String contactWithSneeze;

    @SerializedName("contactWithChestpain")
    @Expose
    private String contactWithChestpain;

    @SerializedName("contactWithDiarrhoea")
    @Expose
    private String contactWithDiarrhoea;

    @SerializedName("contactWithOtherFLu")
    @Expose
    private String contactWithOtherFLu;

    @SerializedName("contactWithSoreThroat")
    @Expose
    private String contactWithSoreThroat;

    @SerializedName("underlyingConditions")
    @Expose
    private String underlyingConditions;

    @SerializedName("specifyKidney")
    @Expose
    private String specifyKidney;

    @SerializedName("specifyPregnancy")
    @Expose
    private String specifyPregnancy;

    @SerializedName("specifyTB")
    @Expose
    private String specifyTB;

    @SerializedName("specifyDiabetes")
    @Expose
    private String specifyDiabetes;

    @SerializedName("specifyLiver")
    @Expose
    private String specifyLiver;

    @SerializedName("specifyChronicLungDisease")
    private String specifyChronicLungDisease;

    @SerializedName("specifyCancer")
    private String specifyCancer;

    @SerializedName("specifyHeartDisease")
    @Expose
    private String specifyHeartDisease;

    @SerializedName("specifyHIV")
    @Expose
    private String specifyHIV;

    @SerializedName("treatment")
    @Expose
    private String treatment;

    @SerializedName("enoughDrugsForThreeMonths")
    @Expose
    private String enoughDrugsForThreeMonths;

    @SerializedName("exposedToFacilityWithConfirmedCase")
    @Expose
    private String exposedToFacilityWithConfirmedCase;

    @SerializedName("someoneHelpingYouManageHIV")
    @Expose
    private String someoneHelpingYouManageHIV;

    @SerializedName("covid19CareFromSomeoneInHousehold")
    @Expose
    private String covid19CareFromSomeoneInHousehold;

    public UserHealthData() {
    }

    public UserHealthData(String fullname, String date, String stateVisited, String feverSymptom, String coughSymptom,
                          String difficultyInBreathingSymptom, String sneezingSymptoms, String chestPainSymptoms,
                          String diarrhoeaSymptoms, String fluSymptoms, String soreThroatSymptoms, String lossOfSmellSymptoms,
                          String contactWithSomeoneWithSymptoms, String contactWithFever, String contactWithCough,
                          String contactWithDifficultBreathing, String contactWithSneeze, String contactWithChestpain,
                          String contactWithDiarrhoea, String contactWithOtherFLu, String contactWithSoreThroat, String underlyingConditions,
                          String specifyKidney, String specifyPregnancy, String specifyTB, String specifyDiabetes, String specifyLiver,
                          String specifyChronicLungDisease, String specifyCancer, String specifyHeartDisease, String specifyHIV,
                          String treatment, String enoughDrugsForThreeMonths, String exposedToFacilityWithConfirmedCase,
                          String someoneHelpingYouManageHIV, String covid19CareFromSomeoneInHousehold) {

        this.fullname = fullname;
        this.date = date;
        this.stateVisited = stateVisited;
        this.feverSymptom = feverSymptom;
        this.coughSymptom = coughSymptom;
        this.difficultyInBreathingSymptom = difficultyInBreathingSymptom;
        this.sneezingSymptoms = sneezingSymptoms;
        this.chestPainSymptoms = chestPainSymptoms;
        this.diarrhoeaSymptoms = diarrhoeaSymptoms;
        this.fluSymptoms = fluSymptoms;
        this.soreThroatSymptoms = soreThroatSymptoms;
        this.lossOfSmellSymptoms = lossOfSmellSymptoms;
        this.contactWithSomeoneWithSymptoms = contactWithSomeoneWithSymptoms;
        this.contactWithFever = contactWithFever;
        this.contactWithCough = contactWithCough;
        this.contactWithDifficultBreathing = contactWithDifficultBreathing;
        this.contactWithSneeze = contactWithSneeze;
        this.contactWithChestpain = contactWithChestpain;
        this.contactWithDiarrhoea = contactWithDiarrhoea;
        this.contactWithOtherFLu = contactWithOtherFLu;
        this.contactWithSoreThroat = contactWithSoreThroat;
        this.underlyingConditions = underlyingConditions;
        this.specifyKidney = specifyKidney;
        this.specifyPregnancy = specifyPregnancy;
        this.specifyTB = specifyTB;
        this.specifyDiabetes = specifyDiabetes;
        this.specifyLiver = specifyLiver;
        this.specifyChronicLungDisease = specifyChronicLungDisease;
        this.specifyCancer = specifyCancer;
        this.specifyHeartDisease = specifyHeartDisease;
        this.specifyHIV = specifyHIV;
        this.treatment = treatment;
        this.enoughDrugsForThreeMonths = enoughDrugsForThreeMonths;
        this.exposedToFacilityWithConfirmedCase = exposedToFacilityWithConfirmedCase;
        this.someoneHelpingYouManageHIV = someoneHelpingYouManageHIV;
        this.covid19CareFromSomeoneInHousehold = covid19CareFromSomeoneInHousehold;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStateVisited() {
        return stateVisited;
    }

    public void setStateVisited(String stateVisited) {
        this.stateVisited = stateVisited;
    }

    public String getFeverSymptom() {
        return feverSymptom;
    }

    public void setFeverSymptom(String feverSymptom) {
        this.feverSymptom = feverSymptom;
    }

    public String getCoughSymptom() {
        return coughSymptom;
    }

    public void setCoughSymptom(String coughSymptom) {
        this.coughSymptom = coughSymptom;
    }

    public String getDifficultyInBreathingSymptom() {
        return difficultyInBreathingSymptom;
    }

    public void setDifficultyInBreathingSymptom(String difficultyInBreathingSymptom) {
        this.difficultyInBreathingSymptom = difficultyInBreathingSymptom;
    }

    public String getSneezingSymptoms() {
        return sneezingSymptoms;
    }

    public void setSneezingSymptoms(String sneezingSymptoms) {
        this.sneezingSymptoms = sneezingSymptoms;
    }

    public String getChestPainSymptoms() {
        return chestPainSymptoms;
    }

    public void setChestPainSymptoms(String chestPainSymptoms) {
        this.chestPainSymptoms = chestPainSymptoms;
    }

    public String getDiarrhoeaSymptoms() {
        return diarrhoeaSymptoms;
    }

    public void setDiarrhoeaSymptoms(String diarrhoeaSymptoms) {
        this.diarrhoeaSymptoms = diarrhoeaSymptoms;
    }

    public String getFluSymptoms() {
        return fluSymptoms;
    }

    public void setFluSymptoms(String fluSymptoms) {
        this.fluSymptoms = fluSymptoms;
    }

    public String getSoreThroatSymptoms() {
        return soreThroatSymptoms;
    }

    public void setSoreThroatSymptoms(String soreThroatSymptoms) {
        this.soreThroatSymptoms = soreThroatSymptoms;
    }

    public String getLossOfSmellSymptoms() {
        return lossOfSmellSymptoms;
    }

    public void setLossOfSmellSymptoms(String lossOfSmellSymptoms) {
        this.lossOfSmellSymptoms = lossOfSmellSymptoms;
    }

    public String getContactWithSomeoneWithSymptoms() {
        return contactWithSomeoneWithSymptoms;
    }

    public void setContactWithSomeoneWithSymptoms(String contactWithSomeoneWithSymptoms) {
        this.contactWithSomeoneWithSymptoms = contactWithSomeoneWithSymptoms;
    }

    public String getContactWithFever() {
        return contactWithFever;
    }

    public void setContactWithFever(String contactWithFever) {
        this.contactWithFever = contactWithFever;
    }

    public String getContactWithCough() {
        return contactWithCough;
    }

    public void setContactWithCough(String contactWithCough) {
        this.contactWithCough = contactWithCough;
    }

    public String getContactWithDifficultBreathing() {
        return contactWithDifficultBreathing;
    }

    public void setContactWithDifficultBreathing(String contactWithDifficultBreathing) {
        this.contactWithDifficultBreathing = contactWithDifficultBreathing;
    }

    public String getContactWithSneeze() {
        return contactWithSneeze;
    }

    public void setContactWithSneeze(String contactWithSneeze) {
        this.contactWithSneeze = contactWithSneeze;
    }

    public String getContactWithChestpain() {
        return contactWithChestpain;
    }

    public void setContactWithChestpain(String contactWithChestpain) {
        this.contactWithChestpain = contactWithChestpain;
    }

    public String getContactWithDiarrhoea() {
        return contactWithDiarrhoea;
    }

    public void setContactWithDiarrhoea(String contactWithDiarrhoea) {
        this.contactWithDiarrhoea = contactWithDiarrhoea;
    }

    public String getContactWithOtherFLu() {
        return contactWithOtherFLu;
    }

    public void setContactWithOtherFLu(String contactWithOtherFLu) {
        this.contactWithOtherFLu = contactWithOtherFLu;
    }

    public String getContactWithSoreThroat() {
        return contactWithSoreThroat;
    }

    public void setContactWithSoreThroat(String contactWithSoreThroat) {
        this.contactWithSoreThroat = contactWithSoreThroat;
    }

    public String getUnderlyingConditions() {
        return underlyingConditions;
    }

    public void setUnderlyingConditions(String underlyingConditions) {
        this.underlyingConditions = underlyingConditions;
    }

    public String getSpecifyKidney() {
        return specifyKidney;
    }

    public void setSpecifyKidney(String specifyKidney) {
        this.specifyKidney = specifyKidney;
    }

    public String getSpecifyPregnancy() {
        return specifyPregnancy;
    }

    public void setSpecifyPregnancy(String specifyPregnancy) {
        this.specifyPregnancy = specifyPregnancy;
    }

    public String getSpecifyTB() {
        return specifyTB;
    }

    public void setSpecifyTB(String specifyTB) {
        this.specifyTB = specifyTB;
    }

    public String getSpecifyDiabetes() {
        return specifyDiabetes;
    }

    public void setSpecifyDiabetes(String specifyDiabetes) {
        this.specifyDiabetes = specifyDiabetes;
    }

    public String getSpecifyLiver() {
        return specifyLiver;
    }

    public void setSpecifyLiver(String specifyLiver) {
        this.specifyLiver = specifyLiver;
    }

    public String getSpecifyChronicLungDisease() {
        return specifyChronicLungDisease;
    }

    public void setSpecifyChronicLungDisease(String specifyChronicLungDisease) {
        this.specifyChronicLungDisease = specifyChronicLungDisease;
    }

    public String getSpecifyCancer() {
        return specifyCancer;
    }

    public void setSpecifyCancer(String specifyCancer) {
        this.specifyCancer = specifyCancer;
    }

    public String getSpecifyHeartDisease() {
        return specifyHeartDisease;
    }

    public void setSpecifyHeartDisease(String specifyHeartDisease) {
        this.specifyHeartDisease = specifyHeartDisease;
    }

    public String getSpecifyHIV() {
        return specifyHIV;
    }

    public void setSpecifyHIV(String specifyHIV) {
        this.specifyHIV = specifyHIV;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getEnoughDrugsForThreeMonths() {
        return enoughDrugsForThreeMonths;
    }

    public void setEnoughDrugsForThreeMonths(String enoughDrugsForThreeMonths) {
        this.enoughDrugsForThreeMonths = enoughDrugsForThreeMonths;
    }

    public String getExposedToFacilityWithConfirmedCase() {
        return exposedToFacilityWithConfirmedCase;
    }

    public void setExposedToFacilityWithConfirmedCase(String exposedToFacilityWithConfirmedCase) {
        this.exposedToFacilityWithConfirmedCase = exposedToFacilityWithConfirmedCase;
    }

    public String getSomeoneHelpingYouManageHIV() {
        return someoneHelpingYouManageHIV;
    }

    public void setSomeoneHelpingYouManageHIV(String someoneHelpingYouManageHIV) {
        this.someoneHelpingYouManageHIV = someoneHelpingYouManageHIV;
    }

    public String getCovid19CareFromSomeoneInHousehold() {
        return covid19CareFromSomeoneInHousehold;
    }

    public void setCovid19CareFromSomeoneInHousehold(String covid19CareFromSomeoneInHousehold) {
        this.covid19CareFromSomeoneInHousehold = covid19CareFromSomeoneInHousehold;
    }

}