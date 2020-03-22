package in.lingayat.we.models.enums;

public enum BloodGroup {
    BLOOD_GROUP_A_POS("A+"),
    BLOOD_GROUP_A_NEG("A-"),
    BLOOD_GROUP_B_POS("B+"),
    BLOOD_GROUP_B_NEG("B-"),
    BLOOD_GROUP_AB_POS("AB+"),
    BLOOD_GROUP_AB_NEG("AB-"),
    BLOOD_GROUP_O_POS("O+"),
    BLOOD_GROUP_O_NEG("O-"),
    BLOOD_GROUP_DONT_KNOW("DontKnow");

    private String stringValue;

    BloodGroup(String stringValue){
        this.stringValue = stringValue;
    }

    public String getStringValue(){
        return stringValue;
    }
}
