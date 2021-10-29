package com.esad.group.assignment.two.dev.modal;

public class AppUserSingleton {
    private static AppUserSingleton INSTANCE = null;

    private long userId;
    private String lastname;
    private String firstName;
    private String nic;
    private String mobileNumber;
    private String email;
    private String profileImage;

    private AppUserSingleton() {
    }

    public AppUserSingleton(long userId, String lastname, String firstName, String nic,
                            String mobileNumber, String email, String profileImage) {
        this.userId = userId;
        this.lastname = lastname;
        this.firstName = firstName;
        this.nic = nic;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.profileImage = profileImage;
    }

    public static AppUserSingleton getINSTANCE() {
        return INSTANCE;
    }

    public static void setINSTANCE(AppUserSingleton INSTANCE) {
        AppUserSingleton.INSTANCE = INSTANCE;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public static AppUserSingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AppUserSingleton();
        }
        return (INSTANCE);
    }
}
